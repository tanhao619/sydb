package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dao.lqsq.PortalIncubatorResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.UserResponsitory;
import com.cdyoue.oddJobs.dto.scfw.IncubatorDetail;
import com.cdyoue.oddJobs.entity.lgsq.PortalIncubatorEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by luanyu on 2017/5/18.
 */
@Component
public class IncubatorsMapper {
    @Autowired
    PortalIncubatorResponsitory incubatorResponsitory;

    @Autowired
    private UserResponsitory userResponsitory;


    public IncubatorDetail entityToDto(PortalIncubatorEntity entity){
        if (null == entity) return  null;

        IncubatorDetail incubatorDetail = new IncubatorDetail();
        if (StringUtils.isNotBlank(entity.getInfo()))incubatorDetail.setContent(entity.getInfo());

        if (StringUtils.isNotBlank(entity.getAddress()))incubatorDetail.setAddress(entity.getAddress());
        if (StringUtils.isNotBlank(entity.getIntroduction())) incubatorDetail.setIntroduction(entity.getIntroduction());
        if (null != entity.getLevelId())incubatorDetail.setLevel(entity.getLevelId());
        incubatorDetail.setLogoUrl(entity.getCoverImg());
        if (StringUtils.isNotBlank(entity.getCoverImg())) incubatorDetail.setName(entity.getName());
        if (0 != entity.getId())incubatorDetail.setId(String.valueOf(entity.getId()));
        UserEntity u = new UserEntity();
        if (null != entity.getCreateBy()){
            u= userResponsitory.findOne(entity.getCreateBy());
            if (u != null) incubatorDetail.setCreateBy(u.getUserpersonalEntity().getName());
        }


        incubatorDetail.setLink("/H5/incubatorDetails.html?id=" + entity.getId());
        if (null != entity.getViewCount())incubatorDetail.setViewCount(entity.getViewCount());
        if (entity.getCreateTime() != null)incubatorDetail.setCreateTime(entity.getCreateTime().toString());
        return incubatorDetail;
    }

    public PortalIncubatorEntity dtoToEntity(IncubatorDetail dto,Integer id){
        PortalIncubatorEntity entity;
        if(id==null){
            entity = new PortalIncubatorEntity();
        }else{
            entity = incubatorResponsitory.findOne(id);
        }
        entity.setCoverImg(dto.getLogoUrl());
        entity.setIntroduction(dto.getIntroduction());
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setInfo(dto.getContent());
        entity.setLevelId(dto.getLevel());
        return entity;
    }
}
