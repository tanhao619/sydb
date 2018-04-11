package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dao.lqsq.PortalAttachmentResponsitory;
import com.cdyoue.oddJobs.dto.Contact;
import com.cdyoue.oddJobs.dto.EmployerInfo;
import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.common.Category;
import com.cdyoue.oddJobs.dto.lgfc.TalentSummary;
import com.cdyoue.oddJobs.dto.requirement.*;
import com.cdyoue.oddJobs.en.MessageEventTypeEnum;
import com.cdyoue.oddJobs.en.MessageMsgTypeEnum;
import com.cdyoue.oddJobs.entity.lgsq.OutsourcingProjectEntity;
import com.cdyoue.oddJobs.entity.lgsq.PortalAttachmentEntity;
import com.cdyoue.oddJobs.entity.lgsq.common.OutsourcingProjectTypeEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import com.cdyoue.oddJobs.factory.RequiresFactory;
import com.cdyoue.oddJobs.service.PortalMessageService;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Created by liaoyoule on 2017/4/21.
 */
@Component
public class PortalRequirementMapper {
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private PortalMessageService portalMessageService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RequiresFactory requiresFactory;
    @Autowired
    private PortalAttachmentResponsitory portalAttachmentResponsitory;

    /**
     * requirement 转 RequireMine
     *
     * @param pre
     * @return
     */
    public RequireMine requirementToRequireMine(OutsourcingProjectEntity pre) {
        RequireMine rm = new RequireMine();
        RequireBase requireBase = this.requirementToRequireBase(pre);
        rm.setRequireBase(requireBase);
        rm.setCoverImgUrl(pre.getCoverImg());
        rm.setApproveStatus(pre.getReviewStatus().intValue());
        Category category = this.categoryMapper.outsourcingProjectTypeEntityToCategory(pre.getOutsourcingProjectTypeEntity());
        rm.setCategory(category);
        rm.setInfo(pre.getIntroduction());
        rm.setEmployerName(userMapper.userToEmployeerName(pre.getCreateBy()));
        return rm;
    }

    /**
     * requirement 转 RequireSummary
     *
     * @param pre
     * @return
     */
    public RequireSummary requirementToRequireSummary(OutsourcingProjectEntity pre) {
        RequireSummary rs = new RequireSummary();
        Category cg = categoryMapper.outsourcingProjectTypeEntityToCategory(pre.getOutsourcingProjectTypeEntity());
        rs.setCategory(cg);
        RequireBase requireBase = this.requirementToRequireBase(pre);
        rs.setRequireBase(requireBase);
        rs.setIntroduction(pre.getIntroduction());
        rs.setCoverImgUrl(pre.getCoverImg());
        try{
            UserEntity createBy = pre.getCreateBy();
            rs.setEmployerId(createBy.getId());
            rs.setEmployerName(userMapper.userToEmployeerName(createBy));
        }catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }


    /**
     * RequireRequest 转 RecommandResponse
     *
     * @param pre
     * @return
     */
    public RecommandResponse reqiurementToRecommandResponse(OutsourcingProjectEntity pre) {
        RecommandResponse rr = new RecommandResponse();
        rr.setId(pre.getId());
        rr.setName(pre.getName());
        rr.setCoverImg(pre.getCoverImg());
        rr.setIntroduction(pre.getIntroduction());
        rr.setProBudget(pre.getProBudget());
        rr.setViewNum(pre.getViewNum());
        return rr;
    }


    /**
     * RequireRequest 转 requirement
     *
     * @param rrt
     * @return
     */
    public OutsourcingProjectEntity reqiurementToRequireRequest(RequireRequest rrt) {
        OutsourcingProjectEntity pre = new OutsourcingProjectEntity();
        pre.setName(rrt.getName());
        pre.setIntroduction(rrt.getIntroduction());
        pre.setTel(rrt.getContact().getPhone());
        pre.setContact(rrt.getContact().getPerson());
        pre.setCoverImg(rrt.getCoverImgUrl());
        pre.setProBudget(rrt.getBudget());
        OutsourcingProjectTypeEntity opte = categoryMapper.categoryToOutsourcingProjectTypeEntity(rrt.getCategory());
        pre.setOutsourcingProjectTypeEntity(opte);
        pre.setDeadLine(rrt.getDeadline());
        pre.setAttachUrl(rrt.getAttachUrl());
        return pre;
    }

    /**
     * requirement 转 RequireComp
     *
     * @param pre
     * @return
     */
    public RequireComp requirementToRequireComp(OutsourcingProjectEntity pre) {
        RequireComp rb = new RequireComp();
        UserEntity createBy = pre.getCreateBy();
        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();
        EmployerInfo employerInfo = userMapper.userToEmployerInfo(createBy);

        Boolean isAcc = requiresFactory.isBehaviorOf("acc", pre.getId());

        Boolean isRec = requiresFactory.isBehaviorOf("rec", pre.getId());
        RequireDetails requireDetails = this.requirementToRequireDetails(pre);
        if (isAcc) {
            requireDetails.getRequireBase().setAccStatus(1);
        }
        if (isRec && isAcc) {
            Contact contact = new Contact();
            contact.setPhone(pre.getTel());
            contact.setPerson(pre.getContact());
            requireDetails.setContact(contact);
        }
        rb.setEmployerInfo(employerInfo);
        if (currentUserLogin != null && currentUserLogin.getId().intValue() == createBy.getId().intValue()) {
            List<TalentSummary> talentSummarys = portalMessageService.findAcceptPeoples(pre.getId(), new Byte(MessageMsgTypeEnum.RequirementAcceptProject.getMsgType() + ""), new Byte(MessageEventTypeEnum.REQUIREMENT.getValue() + ""));
            rb.setAcceptPeoples(talentSummarys);
            Contact contact = new Contact();
            contact.setPhone(pre.getTel());
            contact.setPerson(pre.getContact());
            requireDetails.setContact(contact);
            requireDetails.setIsOwner(true);
            requireDetails.getRequireBase().setAccStatus(1);
        }
        rb.setRequireInfo(requireDetails);
        return rb;
    }

    /**
     * requirement 转 RequireDetails
     *
     * @param pre
     * @return
     */
    public RequireDetails requirementToRequireDetails(OutsourcingProjectEntity pre) {
        RequireDetails rd = new RequireDetails();
        RequireBase requireBase = this.requirementToRequireBase(pre);
        rd.setRequireBase(requireBase);
        OutsourcingProjectTypeEntity opte = pre.getOutsourcingProjectTypeEntity();
        Category category = categoryMapper.outsourcingProjectTypeEntityToCategory(opte);
        rd.setCategory(category);
        rd.setIntroduction(pre.getIntroduction());
        rd.setCoverImgUrl(pre.getCoverImg());
        rd.setAttachUrl(pre.getAttachUrl());
        PortalAttachmentEntity pae = portalAttachmentResponsitory.findByUrl(pre.getAttachUrl());
        if (pae != null) {
            rd.setAttachName(pae.getName());
            rd.setAttachId(pae.getId());
        }
        rd.setDeadline(pre.getDeadLine());
        return rd;
    }


    /**
     * requirement 转 RequireBase
     *
     * @param pre
     * @return
     */
    public RequireBase requirementToRequireBase(OutsourcingProjectEntity pre) {
        RequireBase rb = new RequireBase();
        rb.setName(pre.getName());
        rb.setId(pre.getId());
        rb.setBudget(pre.getProBudget());
        rb.setPublishDate(pre.getCreateTime() + "");
        Optional<Byte> acceptType = Optional.of(pre.getAcceptType());
        if (acceptType.isPresent()) {
            rb.setAccStatus(pre.getAcceptType().intValue());
        }
        Optional<Byte> status = Optional.ofNullable(pre.getStatus());
        if (status.isPresent()) {
            rb.setStatus(pre.getStatus().intValue());
        }
        rb.setReadNum(pre.getViewNum());
        rb.setReviewStatus(pre.getReviewStatus());
        return rb;
    }

}
