package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.constant.DataTypeConstant;
import com.cdyoue.oddJobs.dao.lqsq.*;
import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.lgfc.*;
import com.cdyoue.oddJobs.en.MessageEventTypeEnum;
import com.cdyoue.oddJobs.en.MessageMsgTypeEnum;
import com.cdyoue.oddJobs.entity.lgsq.PortalMessageEntity;
import com.cdyoue.oddJobs.entity.lgsq.PortalRealNameInfoEntity;
import com.cdyoue.oddJobs.entity.lgsq.PortalTechnologyEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserpersonalEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.UserpersonalMapper;
import com.cdyoue.oddJobs.service.UserpersonalService;
import com.cdyoue.oddJobs.service.support.ServiceSupport;
import com.cdyoue.oddJobs.spec.Operator;
import com.cdyoue.oddJobs.spec.QueryRequest;
import com.cdyoue.oddJobs.spec.SpecificationHelper;
import com.cdyoue.oddJobs.utils.MessageUtils;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.page.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by sky on 2017/5/2.
 *
 */
@Service
public class UserpersonalServiceImpl extends ServiceSupport<UserpersonalEntity> implements UserpersonalService {

    @Autowired
    private UserpersonalResponsitory userpersonalResponsitory;
    @Autowired
    UserpersonalMapper userpersonalMapper;
    @Autowired
    private SpecificationHelper specificationHelper;
    @Autowired
    private JpaSpecificationExecutor<UserpersonalEntity> jpaSpecificationExecutor;
    @Autowired
    private PortalMessageResponsitory2 portalMessageResponsitory2;
    @Value("${default.recommend.remoteUrl}")
    private String recommendRemoteUrl;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private TalentResponsitory talentResponsitory;

    @Autowired
    private UserResponsitory userResponsitory;

    @Autowired
    PortalRealNameInfoResponsitory portalRealNameInfoResponsitory;
    @Autowired
    JdbcTemplate JdbcTemplate;


    @Override
    public Class getJpaRepositoryClazz() {
        return UserpersonalResponsitory.class;
    }

    @Override
    public PageInfo<TalentBase> findByKeyWord(String q, Integer industry, Integer expertType, Pageable requestPage) {
        Page<UserpersonalEntity> rpPage = this.findByNameAndCategoryIdAndExpertType(q, industry, expertType, requestPage);
        List<TalentBase> requireSummaries = rpPage.getContent().stream().map(p ->
                userpersonalMapper.UserpersonalEntityToTalentBase(p)
        ).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(requireSummaries, requestPage, rpPage.getTotalElements()));
    }

    private Page<UserpersonalEntity> findByNameAndCategoryIdAndExpertType( String q,  Integer industry,
                                                                           Integer expertType,
                                                                           Pageable requestPage) {
            Page<UserpersonalEntity>  page = null;
            if(null != industry && !StringUtils.isNotBlank(q)){
                if(requestPage.getSort().toString().equals("dataComp: DESC")){
                    page = userpersonalResponsitory.findTalents(industry,expertType,requestPage);
                }else if(requestPage.getSort().toString().equals("id: DESC")){
                    page = userpersonalResponsitory.findTalentsTimeD(industry,expertType,requestPage);
                }else {
                    page = userpersonalResponsitory.findTalentsA(industry,expertType,requestPage);
                }
            } else if(null != industry && StringUtils.isNotBlank(q)){
                if(requestPage.getSort().toString().equals("dataComp: DESC")){
                    page = userpersonalResponsitory.findTalents(q,industry,expertType,requestPage);
                }else if(requestPage.getSort().toString().equals("id: DESC")){
                    page = userpersonalResponsitory.findTalentsTimeD(q,industry,expertType,requestPage);
                }else {
                    page = userpersonalResponsitory.findTalentsA(q,industry,expertType,requestPage);
                }
            } else if (null == industry && StringUtils.isNotBlank(q)) {
                if(requestPage.getSort().toString().equals("dataComp: DESC")){
                    page = userpersonalResponsitory.findTalents(q, expertType, requestPage);
                }else if(requestPage.getSort().toString().equals("id: DESC")){
                    page = userpersonalResponsitory.findTalentsTimeD(q, expertType, requestPage);
                }else {
                    page = userpersonalResponsitory.findTalentsA(q, expertType, requestPage);
                }
            }else
                {
                    if(requestPage.getSort().toString().equals("dataComp: DESC")){
                        page = userpersonalResponsitory.findTalents(expertType,requestPage);
                    }else if(requestPage.getSort().toString().equals("id: DESC")){
                        page = userpersonalResponsitory.findTalentsTimeD(expertType,requestPage);
                    }else {
                        page = userpersonalResponsitory.findTalentsA(expertType,requestPage);
                    }
                }
            if (page.getContent().size() == 0) {
                throw new NotFoundEntityException("数据不存在");
            }
            return page;
    }

    public Page<UserpersonalEntity> findByNameAndCategoryIdAndReviewStatus(String q, Integer industry, Integer reviewStatus, Pageable rqPage) {
        List<QueryRequest> queryRequest = new ArrayList<>();
        QueryRequest qe = new QueryRequest();
        qe.setF("name");
        qe.setO(Operator.LIKE);
        qe.setV(q);
        queryRequest.add(qe);

        if (Optional.ofNullable(reviewStatus).isPresent()) {
            QueryRequest qeEr = new QueryRequest();
            qeEr.setF("portalRealNameInfoEntity.applyType");
            qeEr.setO(Operator.EQ);
            qeEr.setV(reviewStatus + "");
            qeEr.setT(DataTypeConstant.INTEGER);
            queryRequest.add(qeEr);
        }

        Specification specifica = specificationHelper.getSpecifica(UserpersonalEntity.class, queryRequest);
        Page<UserpersonalEntity> page = null;
        if(null != industry && null == q){
            page = userpersonalResponsitory.getTalentsNameNull(industry,reviewStatus,rqPage);
        } else if(null != industry && null != q){
            page = userpersonalResponsitory.getTalentsNameNotNull(q,industry,reviewStatus,rqPage);
        } else {
            page = userpersonalResponsitory.findAll(specifica, rqPage);
        }
        if (page.getContent().size() == 0) {
            throw new NotFoundEntityException("数据不存在");
        }
        return page;
    }

    @Override
    @Transactional
    public void followTalent(Integer id) {
        PortalMessageEntity messageEntity = null;
        if(null != SecurityUtils.getCurrentUserLogin()){
            messageEntity = userpersonalResponsitory.getMessage(SecurityUtils.getCurrentUserLogin().getId(),id);
        }
        if(null != messageEntity){
            return;
        }
/*        PortalMessageEntity message = new PortalMessageEntity();
        // 关注能人没有接收人
        message.setEventId(id);
        message.setEventType(3);
        message.setMsgType(1);
        UserEntity user = new UserEntity();
        if(null != SecurityUtils.getCurrentUserLogin()){
            user.setId(SecurityUtils.getCurrentUserLogin().getId());
        }
        message.setOpera(user);
        message.setInfo(FocusTalent.getEventDescribe());
        message.setLookStatus(false);
        message.setCreateTime(new Timestamp(System.currentTimeMillis()));
        portalMessageResponsitory2.saveAndFlush(message);*/

        MessageUtils.createMessage(id, MessageEventTypeEnum.FOCUS,MessageMsgTypeEnum.FocusTalent);

    }

    @Override
    @Transactional
    public void unFollowTalent(Integer eventId) {
        if(null != SecurityUtils.getCurrentUserLogin()){
            // 取消关注能人 删除能人相关的文章、问题记录
            MessageUtils.cancelMsgRelFoucs(eventId,MessageEventTypeEnum.FOCUS,MessageMsgTypeEnum.FocusTalent);
            userpersonalResponsitory.unFollowTalent(SecurityUtils.getCurrentUserLogin().getId(),eventId);

        }
    }

    @Override
    public PageInfo<TalentAbility> getMyTalents(Pageable requestPage) {
        List<UserpersonalEntity> users = new ArrayList<>();
        if(null != SecurityUtils.getCurrentUserLogin()){
            users = userpersonalResponsitory.getMyTalents(SecurityUtils.getCurrentUserLogin().getId());
        }
        List<TalentAbility> myTalents = users.stream().map(p -> userpersonalMapper.userpersonalEntityToTalentAbility(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(myTalents, requestPage, users.size()));
    }

    @Override
    public TalentInfo getTalentBaseInfo(Integer id) {
        UserEntity userEntity = userpersonalResponsitory.getTalentBaseInfo(id);
        if(null == userEntity){
            return null;
        }
        PortalMessageEntity message = new PortalMessageEntity();
        Boolean isFollow = null;
        UserMine userMine = SecurityUtils.getCurrentUserLogin();
        if(userMine!=null){
            message = userpersonalResponsitory.getMessage(SecurityUtils.getCurrentUserLogin().getId(),id);
            if (message!=null){
                isFollow = true;
            }
        }
        if (userMine == null) isFollow = false;
        return userpersonalMapper.userEntityToTalentInfo(userEntity,isFollow);
    }

    @Override
    public List<TalentSummary> getTalentExperience(Integer id, Integer type) {
        List<PortalTechnologyEntity> technologs = null;
        if (type == 0) {
            technologs = talentResponsitory.findByUserId(id);
        }else {
            technologs = userpersonalResponsitory.getTalentExperience(id, type);
        }
        return technologs.stream().map(p -> userpersonalMapper.PortalTechnologyEntityToTalentSummary(p)).collect(Collectors.toList());
    }

    @Override
    public List<TalentBase> getRecommandUsers(String type, Integer id) {
        List<Integer> userIds = new ArrayList<>();
        if(type.equals("full")){
            RecommendUser user = restTemplate.getForEntity(recommendRemoteUrl.concat("/recommendUser?id="+id), RecommendUser.class).getBody();
            List<RecommendUserData> users = user.getData();
            for (int i= 0;i<users.size();i++){
                userIds.add(users.get(i).getUserid());
            }
        }
        if(type.equals("part")){
            RecommendUser user = restTemplate.getForEntity(recommendRemoteUrl.concat("/recommendPartTimeUser?id="+id), RecommendUser.class).getBody();
            List<RecommendUserData> users = user.getData();
            for (int i= 0;i<users.size();i++){
                userIds.add(users.get(i).getUserid());
            }
        }
        if(userIds==null||userIds.size()<1){
            return null;
        }
        List<UserpersonalEntity> users = userpersonalResponsitory.getUsers(userIds);
        return users.stream().map(p -> userpersonalMapper.UserpersonalEntityToTalentBase2(p,type,id)).collect(Collectors.toList());
    }

    @Override
    public String getUserPersonalName(Integer id) {
        return userpersonalResponsitory.findOneByUid(id).getName();
    }

    @Override
    public void updateInvitedNum(Integer userId) {
        userpersonalResponsitory.updateInvitedNum(userId);
    }

    @Override
    public void updateDataComp(Integer dataComp, Integer userId) {
        userpersonalResponsitory.updateDataComp(dataComp,userId);
    }

    @Override
    public UserpersonalEntity findByUid(Integer uid) {
        UserpersonalEntity byUid = userpersonalResponsitory.findByUid(uid);
        return byUid;
    }

    @Override
    public UserEntity findUserById(Integer uid) {
        UserEntity one = userResponsitory.findOne(uid);
        return one;
    }

    @Override
    public List<PortalRealNameInfoEntity> findApplysByUid(Integer uid) {
        List<PortalRealNameInfoEntity> byUserId = portalRealNameInfoResponsitory.findByUserIdAndStatus(uid,new Byte("1"));
        return byUserId;
    }

}
