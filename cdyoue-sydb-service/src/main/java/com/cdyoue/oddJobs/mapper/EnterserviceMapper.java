package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dao.lqsq.UserpersonalResponsitory;
import com.cdyoue.oddJobs.dto.scfw.EnterServiceDetail;
import com.cdyoue.oddJobs.dto.scfw.EnterServiceSummary;
import com.cdyoue.oddJobs.entity.lgsq.PortalIntellectualInnovateEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserpersonalEntity;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by tr on 2017/5/16.
 */

@Component
public class EnterserviceMapper {
    @Autowired
    private UserpersonalResponsitory userpersonalResponsitory;
    public PortalIntellectualInnovateEntity enterServiceDetailToPortalIntellectualInnovateEntity(EnterServiceDetail esd){
        PortalIntellectualInnovateEntity piie = new PortalIntellectualInnovateEntity();
        piie.setTel(esd.getTel());
        piie.setServiceInfo(esd.getServiceInfo());
        piie.setCoverImg(esd.getCoverImg());
        piie.setInfo(esd.getInfo());
        piie.setLink(esd.getLink());
        piie.setIntroduction(esd.getIntroduction());
        piie.setName(esd.getName());
        piie.setType(esd.getType().equalsIgnoreCase("zscq") ? 1 : 2);
        UserEntity userEntity = new UserEntity();
        userEntity.setId(SecurityUtils.getCurrentUserLogin().getId());
        piie.setCreateBy(userEntity);
        return piie;
    }

    public EnterServiceDetail portalIntellectualInnovateEntityToEnterServiceDetail (PortalIntellectualInnovateEntity piie){
        EnterServiceDetail esd = new EnterServiceDetail();
        esd.setName(piie.getName());
        esd.setTel(piie.getTel());
        esd.setServiceInfo(piie.getServiceInfo());
        esd.setCoverImg(piie.getCoverImg());
        esd.setInfo(piie.getInfo());
        esd.setLink(piie.getLink());
        esd.setIntroduction(piie.getIntroduction());
        esd.setType(piie.getType() == 1 ? "ZSCQ" : "SCFW");
        esd.setViewCount(piie.getViewCount());
        String type = piie.getType() == 1 ? "ZSCQ" : "SCFW";
        esd.setH5link("patentapplicationDetails.html?id=" + piie.getId() + "&type=" + type);
        return esd;
    }

    public EnterServiceSummary portalIntellectualInnovateToEnterServiceSummary(PortalIntellectualInnovateEntity piie){
        EnterServiceSummary ess = new EnterServiceSummary();
        ess.setId(piie.getId());
        ess.setName(piie.getName());
        ess.setIntroduction(piie.getIntroduction());
        ess.setInfo(piie.getInfo());
        ess.setCoverImg(piie.getCoverImg());
        ess.setLink(piie.getLink());
        ess.setViewCount(piie.getViewCount());
        try {
            UserpersonalEntity upe = userpersonalResponsitory.findByUid(piie.getCreateBy().getId());
            ess.setCreateBy(upe.getName());
        }catch (Exception e){
            ess.setCreateBy(null);
        }
        if (piie.getCreateTime() != null) ess.setCreateTime(piie.getCreateTime().toString());
        ess.setServiceInfo(piie.getServiceInfo());
        ess.setH5link("/H5/patentapplicationDetails.html?id=" + piie.getId());
        return ess;
    }
}
