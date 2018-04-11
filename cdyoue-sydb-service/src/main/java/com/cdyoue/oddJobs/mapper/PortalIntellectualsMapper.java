package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dao.lqsq.UserpersonalResponsitory;
import com.cdyoue.oddJobs.dto.Contact;
import com.cdyoue.oddJobs.dto.SyCooperativePartnerDTO;
import com.cdyoue.oddJobs.dto.zscq.AssessDetailDTO;
import com.cdyoue.oddJobs.dto.zscq.IntellectualMine;
import com.cdyoue.oddJobs.dto.zscq.IntellectualSummary;
import com.cdyoue.oddJobs.entity.lgsq.PortalIntellectualBuyEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserpersonalEntity;
import com.cdyoue.oddJobs.entity.syData.SyCooperativePartnerEntity;
import com.cdyoue.oddJobs.entity.syData.SyPortalAssessEntity;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.oddJobs.utils.UserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liaoyoule on 2017/4/21.
 */
@Component
public class PortalIntellectualsMapper {
    @Autowired
    private UserpersonalResponsitory userpersonalResponsitory;
    /**
     * requirement 转 RequireSummary
     * @param pre
     * @return
     */
    public IntellectualSummary  intellectualToIntellectualSummary(PortalIntellectualBuyEntity pre) {

        IntellectualSummary is = new IntellectualSummary();
        is.setName(pre.getName());
        Contact contact = new Contact();
        contact.setPerson(pre.getContact());
        contact.setPhone(pre.getTel());
        is.setContact(contact);
        is.setId(pre.getId());
        is.setReviewStatus(pre.getReviewStatus());
        is.setViewCount(pre.getViewCount());
        UserpersonalEntity ue = null;
        if (pre.getCreateBy() != null) /*ue = userpersonalResponsitory.findByUid(pre.getCreateBy().getId());
        if (ue != null) is.setCreateBy(ue.getName());*/
        is.setCreateBy(UserUtils.getUserName(pre.getCreateBy().getId()));
        if (pre.getPublishTime() !=null) is.setPublishTime(pre.getPublishTime().toString());

        is.setIntroduction(pre.getIntroduction());
        is.setBusinessType(pre.getBusinessType());
        is.setIntellType(pre.getIntellType());
        is.setTop(pre.getTop());
        is.setTopImg(pre.getTopImg());
        is.setLink("/H5/patentaskbuyDetails.html?id=" + pre.getId());
        return is;
    }

    /**
     * requirement 转 RequireSummary
     * @param pib
     * @return im
     */
    public IntellectualMine intellectualBuyEntityToIntellectualMine(PortalIntellectualBuyEntity pib) {
        IntellectualMine im = new IntellectualMine();
        im.setId(pib.getId());
        im.setApproveStatus(pib.getReviewStatus());
        im.setName(pib.getName());
        Contact c = new Contact();
        c.setPerson(pib.getContact());
        c.setPhone(pib.getTel());
        im.setContact(c);
        im.setApproveStatus(pib.getReviewStatus());
        if (pib.getPublishTime() !=null) im.setPublishTime(pib.getPublishTime().toString());
        return im;
    }

    public SyCooperativePartnerDTO EntityToDTO(SyCooperativePartnerEntity p) {
        SyCooperativePartnerDTO dto = new SyCooperativePartnerDTO();
        BeanUtils.copyProperties(p,dto);
        dto.setCreatBy(UserUtils.getUserName(p.getCreatBy()));
        return dto;
    }

    public AssessDetailDTO AssessEntityToDTO(SyPortalAssessEntity p) {
        AssessDetailDTO dto = new AssessDetailDTO();
        BeanUtils.copyProperties(p,dto);
        SimpleDateFormat smp = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String pt = smp.format(p.getPublishTime());
        dto.setPublishTime(pt);
        return dto;
    }
}
