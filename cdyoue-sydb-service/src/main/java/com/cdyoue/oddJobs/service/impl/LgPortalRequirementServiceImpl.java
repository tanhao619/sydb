package com.cdyoue.oddJobs.service.impl;


import com.cdyoue.oddJobs.dao.lqsq.OutsourcingProjectResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.PortalRequirementMessageResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.PortalWordResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.UserenterpriseResponsitory;
import com.cdyoue.oddJobs.dto.AcceptPeopleSumary;
import com.cdyoue.oddJobs.dto.Contact;
import com.cdyoue.oddJobs.dto.Reason;
import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.common.Category;
import com.cdyoue.oddJobs.dto.common.message.RequirementMessage;
import com.cdyoue.oddJobs.dto.message.PersonalCenterMessageDto;
import com.cdyoue.oddJobs.dto.requirement.*;
import com.cdyoue.oddJobs.dto.xqdt.ProjectNews;
import com.cdyoue.oddJobs.en.MessageEventTypeEnum;
import com.cdyoue.oddJobs.en.MessageMsgTypeEnum;
import com.cdyoue.oddJobs.entity.lgsq.OutsourcingProjectEntity;
import com.cdyoue.oddJobs.entity.lgsq.PortalWordEntity;
import com.cdyoue.oddJobs.entity.lgsq.RequirementMessageEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import com.cdyoue.oddJobs.exception.BadRequestMessageException;
import com.cdyoue.oddJobs.exception.LogicException;
import com.cdyoue.oddJobs.exception.LogicalException;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.factory.RequiresFactory;
import com.cdyoue.oddJobs.mapper.PortalMessageMapper;
import com.cdyoue.oddJobs.mapper.PortalRequirementMapper;
import com.cdyoue.oddJobs.mapper.PortalWordMapper;
import com.cdyoue.oddJobs.mapper.UserMapper;
import com.cdyoue.oddJobs.service.LgPortalRequirementService;
import com.cdyoue.oddJobs.service.support.ServiceSupport;
import com.cdyoue.oddJobs.utils.*;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by liaoyoule on 2017/4/20.
 */
@Service
public class LgPortalRequirementServiceImpl extends ServiceSupport<OutsourcingProjectEntity> implements LgPortalRequirementService {
    @Autowired
    private OutsourcingProjectResponsitory outsourcingProjectResponsitory;
    @Autowired
    private RequiresFactory requiresFactory;
    @Autowired
    private PortalRequirementMessageResponsitory portalRequirementMessageResponsitory;
    @Autowired
    private PortalRequirementMapper portalRequirementMapper;
    @Autowired
    private PortalWordResponsitory portalWordResponsitory;
    @Autowired
    private PortalWordMapper portalWordMapper;
    @Autowired
    private PortalMessageMapper portalMessageMapper;
    @Autowired
    private UserenterpriseResponsitory userenterpriseResponsitory;

    @Autowired
    private UserMapper userMapper;



    @Override
    public PageInfo<RequireSummary> findByKeyWord(String q, Integer categoryId, Integer reviewStatus, Pageable rqPage) {
        Page<OutsourcingProjectEntity> rpPage = super.findByNameAndCategoryIdAndReviewStatus(q, categoryId, reviewStatus, rqPage);
        List<RequireSummary> requireSummaries = rpPage.getContent().stream().map(p -> portalRequirementMapper.requirementToRequireSummary(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(requireSummaries, rqPage, rpPage.getTotalElements()));
    }

    @Override
    public Integer save(RequireRequest reqiurement) {
        OutsourcingProjectEntity preBe = portalRequirementMapper.reqiurementToRequireRequest(reqiurement);
        preBe.setCreateTime(new Timestamp(System.currentTimeMillis()));
        preBe.setAcceptType(new Byte("0"));
        preBe.setStatus(new Byte("0"));
        preBe.setReviewStatus(new Byte("0"));
        preBe.setViewNum(0);
        preBe.setDeadLine(reqiurement.getDeadline());
        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();
        UserEntity ue = new UserEntity();
        ue.setId(currentUserLogin.getId());
        preBe.setCreateBy(ue);
        OutsourcingProjectEntity preAf = outsourcingProjectResponsitory.save(preBe);
        return preAf.getId();
    }

    @Override
    @Transactional
    public void deleteByPrimary(Integer id) {
        OutsourcingProjectEntity ope = outsourcingProjectResponsitory.findOne(id);

        if(ope == null){
            throw new NotFoundEntityException();
        }
//        if(ope.getStatus()!=0){
//            throw new BadRequestMessageException(RequirementMessage.DELETEFAIL);
//        }

        AuthenticationHelper.isAdminORCreator(ope.getCreateBy().getId());

        //删除 需求
        outsourcingProjectResponsitory.delete(id);
        //删除 承接关系
        portalRequirementMessageResponsitory.deleteByReId(id);
        //删除 联系
        portalWordResponsitory.deleteByReId(id);

    }

    @Override
    public RequireComp getRequirementById(Integer id) {
        OutsourcingProjectEntity pre = outsourcingProjectResponsitory.findOne(id);
        if (pre == null) {
            throw new NotFoundEntityException();
        }

        RequireComp requireComp = portalRequirementMapper.requirementToRequireComp(pre);
        //统计承接该需求人数
        requireComp.getRequireInfo().getRequireBase().setAccRequireCount(portalRequirementMessageResponsitory.countAccRequire(id));
        return requireComp;
    }

    @Override
    public void close(Integer id) {
        OutsourcingProjectEntity entity = outsourcingProjectResponsitory.findOne(id);

        if (entity == null) {
            throw new NotFoundEntityException();
        }

        AuthenticationHelper.isAdminORCreator(entity.getCreateBy().getId());

        entity.setStatus(new Byte("0"));
        outsourcingProjectResponsitory.save(entity);
    }

    @Override
    @Transactional
    public Integer approve(Integer id, Reason reason) {
        OutsourcingProjectEntity entity = outsourcingProjectResponsitory.findOne(id);
        if (entity == null) {
            throw new NotFoundEntityException();
        }
        entity.setReviewStatus(new Byte("1"));
        entity.setStatus(new Byte("1"));
        entity.setPublishTime(new Timestamp(System.currentTimeMillis()));
        entity.setReviewTime(new Timestamp(System.currentTimeMillis()));
        entity.setReviewUserId(SecurityUtils.getCurrentUserLogin().getId());
        entity.setReviewReason(reason.getReason());
        OutsourcingProjectEntity preAf = outsourcingProjectResponsitory.save(entity);
        // 生成审核成功消息
        UserEntity createBy = entity.getCreateBy();
        if (createBy != null){
            MessageUtils.createAuditMessage(createBy.getId(),id,MessageEventTypeEnum.AUDIT,
                    MessageMsgTypeEnum.AuditRequirement,entity.getReviewStatus()+"");
        }
        return preAf.getId();
    }

    @Override
    public Integer reject(Integer id, Reason reason) {
        OutsourcingProjectEntity entity = outsourcingProjectResponsitory.findOne(id);
        if (entity == null) {
            throw new NotFoundEntityException();
        }
        entity.setPublishTime(null);
        entity.setReviewStatus(new Byte("2"));
        entity.setReviewTime(new Timestamp(System.currentTimeMillis()));
        entity.setReviewUserId(SecurityUtils.getCurrentUserLogin().getId());
        entity.setReviewReason(reason.getReason());
        OutsourcingProjectEntity preAf = outsourcingProjectResponsitory.save(entity);
        // 生成审核失败消息
        UserEntity createBy = entity.getCreateBy();
        if (createBy != null){
            MessageUtils.createAuditMessage(createBy.getId(),id,MessageEventTypeEnum.AUDIT,
                    MessageMsgTypeEnum.AuditRequirement,entity.getReviewStatus()+"");
        }
        return preAf.getId();
    }

    @Override
    public PageInfo<RequireMine> getMyReqiures(String type, String query, PageRequest pr) {
        Page<OutsourcingProjectEntity> myReqiures = requiresFactory.getMyReqiures(type,query, pr);
        if (myReqiures == null) {
            throw new NotFoundEntityException();
        }
        List<OutsourcingProjectEntity> content = myReqiures.getContent();
        if (content.size() == 0) {
            throw new NotFoundEntityException();
        }
        List<RequireMine> requireMines = content.stream().map(pre -> {
            RequireMine requireMine = portalRequirementMapper.requirementToRequireMine(pre);
            if (type.equalsIgnoreCase("pub")) {
                Integer acceptNum = portalRequirementMessageResponsitory.findAcceptPeoplesCount(pre.getId(), new Byte(MessageMsgTypeEnum.RequirementAcceptProject.getMsgType() + ""), new Byte(MessageEventTypeEnum.REQUIREMENT.getValue() + ""));
                requireMine.setAcceptNum(acceptNum);
            }
            return requireMine;
        }).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(requireMines, pr, myReqiures.getTotalElements()));
    }

    @Override
    @Transactional
    public void inviteReqiurement(String idStr, Integer userid) {


        String[] ids = idStr.split(",");

        if (ids.length == 0) {
            throw new BadRequestMessageException(RequirementMessage.BADREQUESTERROR);
        }

        Arrays.stream(ids).forEach(id-> {


                    RequirementMessageEntity pme = portalRequirementMessageResponsitory.findByRequireIdAndReceiverId(Integer.parseInt(id), userid);
                    if (pme != null) {
                        throw new BadRequestMessageException(RequirementMessage.BADREQUESTERRORFORUSER);
                    }
                    Integer operId = SecurityUtils.getCurrentUserLogin().getId();
                    if(operId.equals(userid)){
                        throw new LogicalException("自己不能邀请自己");
                    }

                    RequirementMessageEntity pmeBe = new RequirementMessageEntity();
                    pmeBe.setCreateTime(new Timestamp(System.currentTimeMillis()));
                    OutsourcingProjectEntity pre = new OutsourcingProjectEntity();
                    pre.setId(Integer.parseInt(id));
                    pmeBe.setRequirementEntity(pre);
                    //receve
                    UserEntity ueRe = new UserEntity();
                    ueRe.setId(userid);
                    pmeBe.setReceiver(ueRe);
                    //oper
                    UserEntity ueOp = new UserEntity();
                    ueOp.setId(operId);
                    pmeBe.setOpera(ueOp);

                    pmeBe.setMsgType(new Byte(MessageMsgTypeEnum.InvitationAcceptRequirement.getMsgType() + ""));
                    pmeBe.setEventType(new Byte(MessageEventTypeEnum.INVITATION.getValue() + ""));
                    pmeBe.setLookStatus(new Byte("0"));
                    pmeBe.setInfo(MessageMsgTypeEnum.InvitationAcceptRequirement.getEventDescribe());
                    RequirementMessageEntity pmeAf = portalRequirementMessageResponsitory.save(pmeBe);
                }

        );


    }

    @Override
    @Transactional
    public void acceptReqiurement(Integer id, Contact contactInfo) {


        //判断用户是否承接
        Boolean acc = requiresFactory.isBehaviorOf("acc", id);
        if (acc) {
            throw new LogicException("需求以承接不能重复承接");
        }

        PortalWordEntity portalWordEntity = portalWordMapper.ContactToPortalWordEntity(contactInfo);
        portalWordEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        portalWordEntity.setEventId(id);
        portalWordEntity.setUserId(SecurityUtils.getCurrentUserLogin().getId());
        portalWordResponsitory.save(portalWordEntity);
        //message

        RequirementMessageEntity pme = new RequirementMessageEntity();

        OutsourcingProjectEntity ope = outsourcingProjectResponsitory.findOne(id);
        if (ope == null) {
            throw new NotFoundEntityException();
        }

        if (ope.getCreateBy().getId() == SecurityUtils.getCurrentUserLogin().getId()) {
            throw new LogicException("发布人不能承接自己需求");
        }

        OutsourcingProjectEntity pre = new OutsourcingProjectEntity();
        pre.setId(id);

        UserEntity ueO = new UserEntity();
        ueO.setId(SecurityUtils.getCurrentUserLogin().getId());

        pme.setOpera(ueO);


        pme.setRequirementEntity(pre);
        pme.setMsgType(new Byte(MessageMsgTypeEnum.RequirementAcceptProject.getMsgType() + ""));
        pme.setEventType(new Byte(MessageEventTypeEnum.REQUIREMENT.getValue() + ""));
        pme.setLookStatus(new Byte("0"));
        pme.setReceiver(ope.getCreateBy());
        pme.setInfo(MessageMsgTypeEnum.RequirementAcceptProject.getEventDescribe());
        pme.setCreateTime(new Timestamp(System.currentTimeMillis()));
        portalRequirementMessageResponsitory.save(pme);

    }

    @Override
    @Transactional
    public void cancelReqiurement(Integer id) {

        Integer userId = SecurityUtils.getCurrentUserLogin().getId();
        portalWordResponsitory.deleteByUserAndReId(userId, id);
        portalRequirementMessageResponsitory.deleteByUserAndOpId(userId, id);

    }

    @Override
    @Transactional
    public Integer updateRequirements(Integer id, RequireRequest reqiurement) {
        OutsourcingProjectEntity preBe = portalRequirementMapper.reqiurementToRequireRequest(reqiurement);
        OutsourcingProjectEntity preFl = outsourcingProjectResponsitory.findOne(id);
        if (preFl == null) {
            throw new NotFoundEntityException();
        }
        BeanPropertieUtils.copyPropertiesIgnoreNull(preBe, preFl);
        preFl.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();
        preFl.setUpdateBy(currentUserLogin.getId());
        //修改需求后审核状态置为0
        preFl.setReviewStatus((byte)0);
        preFl.setStatus((byte)0);
        OutsourcingProjectEntity preAf = outsourcingProjectResponsitory.save(preFl);
        return preAf.getId();
    }

    @Override
    public List<RecommandResponse> findRecommand(List<RequireData> data) {
        List<RecommandResponse> rrs = new ArrayList<>();
        data.forEach(rd -> {
            OutsourcingProjectEntity pre = outsourcingProjectResponsitory.findOne(rd.getOutsourcingprojectid());
            if (pre != null) {
                if (pre.getReviewStatus().intValue() == 1) {
                    RecommandResponse recommandResponse = portalRequirementMapper.reqiurementToRecommandResponse(pre);
                    recommandResponse.setRecommendeddegree(rd.getRecommendeddegree());
                    rrs.add(recommandResponse);
                }
            }
        });
        return rrs;
    }

    @Override
    public void openReqiurement(Integer id) {
        OutsourcingProjectEntity entity = outsourcingProjectResponsitory.findOne(id);
        if (entity == null) {
            throw new NotFoundEntityException();
        }
        entity.setStatus(new Byte("1"));
        outsourcingProjectResponsitory.save(entity);
    }

    @Override
    public PageInfo<AcceptPeopleSumary> findAcceptPeoples(Integer id, Pageable requestPage) {
        Page<RequirementMessageEntity> apPage = portalRequirementMessageResponsitory.findAcceptPeoples(id, new Byte(MessageMsgTypeEnum.RequirementAcceptProject.getMsgType() + ""), new Byte(MessageEventTypeEnum.REQUIREMENT.getValue() + ""), requestPage);
        if (apPage.getContent().size() == 0) {
            throw new NotFoundEntityException();
        }
        List<AcceptPeopleSumary> tss = apPage.getContent().stream().map(pe -> portalMessageMapper.portalMessageToAcceptPeopleSumary(pe)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(tss, requestPage, apPage.getTotalElements()));
    }

    @Override
    public PageInfo<RequireMine> getReqiure(Integer userId, PageRequest pr) {
        Page<OutsourcingProjectEntity> myReqiures = outsourcingProjectResponsitory.findMyRequires(userId, pr);
        if (myReqiures == null) {
            throw new NotFoundEntityException();
        }
        List<OutsourcingProjectEntity> content = myReqiures.getContent();
        if (content.size() == 0) {
            throw new NotFoundEntityException();
        }
        List<RequireMine> requireMines = content.stream().map(pre -> portalRequirementMapper.requirementToRequireMine(pre)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(requireMines, pr, myReqiures.getTotalElements()));
    }

    @Override
    public PageInfo<RequireMini> getRequirementByUserId(Integer userId, Pageable pr) {
        Page<RequirementMessageEntity> someoneRequires = portalRequirementMessageResponsitory.findAcceptRequirByUserId(userId, new Byte(MessageMsgTypeEnum.RequirementAcceptProject.getMsgType() + ""), new Byte(MessageEventTypeEnum.REQUIREMENT.getValue() + ""), pr);
        List<RequireMini> requireMinis = new ArrayList<>();
        for (RequirementMessageEntity p : someoneRequires.getContent()){
            RequireMini rm = new RequireMini();
            if (p.getCreateTime()!= null) rm.setPublishTime(p.getCreateTime().toString());
            OutsourcingProjectEntity ope = p.getRequirementEntity();
            rm.setId(ope.getId());
            rm.setName(ope.getName());
            Integer createBy = ope.getCreateBy().getId();
            String name = UserUtils.getUserName(createBy);
            rm.setCreateBy(name);
            requireMinis.add(rm);
        }
        return new PageInfo<>(new PageImpl(requireMinis, pr, someoneRequires.getTotalElements()));
    }

    @Override
    public List<ProjectNews> getProjectNews() {
        PageRequest pageRequest = new PageRequest(0,6);
        Page<OutsourcingProjectEntity> entities = outsourcingProjectResponsitory.getProjectNews(pageRequest);
        if (entities == null) {
            throw new NotFoundEntityException();
        }
        List<OutsourcingProjectEntity> list = entities.getContent();
        List<ProjectNews> projectNewses = new ArrayList<>();
        for (OutsourcingProjectEntity o : list)
        {
            ProjectNews p = new ProjectNews();
            Category category = new Category();
            category.setId(o.getOutsourcingProjectTypeEntity().getId());
            category.setName(o.getOutsourcingProjectTypeEntity().getName());
            p.setRequireName(o.getName());
            p.setFinance(o.getProBudget());
            if (o.getCreateBy().getUserType() == 0) p.setEnterName(o.getCreateBy().getUserpersonalEntity().getName());
            if (o.getCreateBy().getUserType() == 1) p.setEnterName(o.getCreateBy().getUserenterpriseEntity().getName());
            p.setCategory(category);
            projectNewses.add(p);
        }
        return projectNewses;
    }

    @Override
    public PageInfo<InviteMeRequireSummary> findInviteMeRequirement(Pageable requestPage) {
        List<PersonalCenterMessageDto> list = MessageUtils.getMessageByType(MessageEventTypeEnum.INVITATION, MessageMsgTypeEnum.InvitationAcceptRequirement);
        List<InviteMeRequireSummary> inviteMeRequireSummaries = new ArrayList<>();
        for (PersonalCenterMessageDto p : list){
            InviteMeRequireSummary rs = new InviteMeRequireSummary();
            OutsourcingProjectEntity ope = outsourcingProjectResponsitory.findOne(p.getEventId());
            if (ope != null) {
                rs.setId(ope.getId());
                rs.setName(ope.getName());
                rs.setFin(ope.getProBudget());
                rs.setStatus(ope.getStatus());
                if (ope.getPublishTime() != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    rs.setPublishTime(sdf.format(ope.getPublishTime()));
                }
                UserEntity ue = ope.getCreateBy();
                if(ue != null){
                    rs.setEnterprise(userMapper.userToEmployeerName(ue));
                }

                inviteMeRequireSummaries.add(rs);
            }
        }
        return new PageInfo<InviteMeRequireSummary>(new PageImpl(inviteMeRequireSummaries,requestPage, inviteMeRequireSummaries.size()));
    }

    @Override
    public Class getJpaRepositoryClazz() {
        return OutsourcingProjectResponsitory.class;
    }
}
