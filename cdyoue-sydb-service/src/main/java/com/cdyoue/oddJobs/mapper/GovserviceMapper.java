package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dao.lqsq.UserpersonalResponsitory;
import com.cdyoue.oddJobs.dto.ggfw.Govservice;
import com.cdyoue.oddJobs.entity.lgsq.GovServiceEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserpersonalEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by tangrui on 2017/5/15.
 */

@Component
public class GovserviceMapper {
    /**
     * GovServiceEntity转Govservice
     * @param govServiceEntity
     * @return Govservice
     */
    @Autowired
    private UserpersonalResponsitory userpersonalResponsitory;

    public Govservice govServiceEntityToGovService(GovServiceEntity govServiceEntity){
        Govservice govservice = new Govservice();
        govservice.setId(govServiceEntity.getId());
        govservice.setTitle(govServiceEntity.getName());
        govservice.setIntroduction(govServiceEntity.getInfo());
        govservice.setLink(govServiceEntity.getLink());
        govservice.setLogoUrl(govServiceEntity.getCoverImg());
        govservice.setViewscount(govServiceEntity.getViewCount());
        if (govServiceEntity.getCreateTime() != null) govservice.setCreateTime(govServiceEntity.getCreateTime().toString());
        try {
            UserpersonalEntity upe = userpersonalResponsitory.findByUid(govServiceEntity.getCreateBy().getId());
            govservice.setCreateBy(upe.getName());
        } catch (Exception e){
            govservice.setCreateBy(null);
        }
        return govservice;
    }

    /**
     * Govservice转GovServiceEntity
     * @param govService
     * @return GovServiceEntity
     */
    public GovServiceEntity govServiceToGovServiceEntity(Govservice govService){
        GovServiceEntity govServiceEntity = new GovServiceEntity();
        govServiceEntity.setId(govService.getId());
        govServiceEntity.setName(govService.getTitle());
        govServiceEntity.setInfo(govService.getIntroduction());
        govServiceEntity.setLink(govService.getLink());
        govServiceEntity.setCoverImg(govService.getLogoUrl());
        govServiceEntity.setViewCount(govService.getViewscount());
        return govServiceEntity;
    }
}
