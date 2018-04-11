package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dao.lqsq.PortalAttachmentResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.UserpersonalResponsitory;
import com.cdyoue.oddJobs.dto.Contact;
import com.cdyoue.oddJobs.dto.scfw.FinProjectDetail;
import com.cdyoue.oddJobs.dto.scfw.FinProjectSummary;
import com.cdyoue.oddJobs.entity.lgsq.PortalAttachmentEntity;
import com.cdyoue.oddJobs.entity.lgsq.PortalProjectEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * Created by tr on 2017/5/23.
 */

@Component
public class FinprojectMapper {
    @Autowired
    private UserpersonalResponsitory userpersonalResponsitory;

    @Autowired
    private PortalAttachmentResponsitory portalAttachmentResponsitory;

    public FinProjectDetail projectEntityToFinProjectDetail(PortalProjectEntity pp) {
        if (pp ==null ){
            return  null;
        }
        FinProjectDetail fpd = new FinProjectDetail();
        fpd.setReviewReason(pp.getReviewReason());
        fpd.setTitle(pp.getName());
        fpd.setViewCount(pp.getViewCount());
        if (pp.getImgUrl() !=null)fpd.setCoverImgUrl(pp.getImgUrl());

        String publishTime = null;
        if (pp.getPublishTime() != null){
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
            publishTime = sd.format(pp.getPublishTime());
        }
        fpd.setPublishTime(publishTime);
        Contact contact = new Contact();
        contact.setPerson(pp.getContact());
        contact.setMail(pp.getEmail());
        contact.setPhone(pp.getTel());
        fpd.setContact(contact);

        fpd.setFinance(pp.getFinance());
        fpd.setIntroduction(pp.getIntroduction());
        fpd.setInfo(pp.getInfo());

        /**
         *项目附件url，根据referId和sourceType查询attachmentEntity
         */
        PortalAttachmentEntity pae = portalAttachmentResponsitory.findEntityByReferIdAndType(pp.getId(), (byte)5);
        if(pae != null && StringUtils.isNotBlank(pae.getUrl())){
            //返回附件URL
            fpd.setAttachUrl(pae.getUrl());
        }
        if(pae != null && StringUtils.isNotBlank(pae.getName())){
            //返回附件名
            fpd.setAttachName(pae.getName());
        }

        return fpd;
    }

    public PortalProjectEntity finProjectDetailToEntity(FinProjectDetail finProjectDetail) {
        PortalProjectEntity p = new PortalProjectEntity();
        //项目名称
        p.setName(finProjectDetail.getTitle());
        //融资需求
        p.setFinance(finProjectDetail.getFinance());
        //上传照片
        p.setImgUrl(finProjectDetail.getCoverImgUrl());
        //项目简介
        p.setInfo(finProjectDetail.getInfo());
        //项目详情
        p.setIntroduction(finProjectDetail.getIntroduction());

        //联系方式
        if (finProjectDetail.getContact() != null) {
            p.setContact(finProjectDetail.getContact().getPerson());
            p.setTel(finProjectDetail.getContact().getPhone());
            p.setEmail(finProjectDetail.getContact().getMail());
        }
        return p;
    }

    public FinProjectSummary projectEntityToFinProjectSummary(PortalProjectEntity ppe) {
        FinProjectSummary fps = new FinProjectSummary();
        fps.setId(ppe.getId());
        fps.setName(ppe.getName());
        fps.setCoverImgUrl(ppe.getImgUrl());
        fps.setFinance(ppe.getFinance());
        fps.setApproveStatus(ppe.getReviewStatus());
        fps.setViewsCount(ppe.getViewCount());
        //项目描述
        fps.setIntroduction(ppe.getInfo());
        //发布人
        fps.setPublishPepole(ppe.getContact());
        //创建时间
        if (ppe.getCreateTime() != null) fps.setCreateTime(ppe.getCreateTime().toString());
        //发布时间
        if (ppe.getPublishTime() != null)fps.setPublishTime(ppe.getPublishTime().toString());
        fps.setLink("/H5/financingprojectDetails.html?id=" + ppe.getId());
        return fps;
    }
}
