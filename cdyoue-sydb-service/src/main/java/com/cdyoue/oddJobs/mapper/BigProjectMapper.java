package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dao.lqsq.BigProjectRepository;
import com.cdyoue.oddJobs.dao.lqsq.UserpersonalResponsitory;
import com.cdyoue.oddJobs.dto.xqdt.BigProjectDetail;
import com.cdyoue.oddJobs.entity.lgsq.PortalBigProjectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by luanyu on 2017/5/20.
 */
@Component
public class BigProjectMapper {
    @Autowired
    BigProjectRepository bigProjectRepository;

    @Autowired
    private UserpersonalResponsitory userpersonalResponsitory;

    public BigProjectDetail entityToDto(PortalBigProjectEntity entity){
        BigProjectDetail dto = new BigProjectDetail();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getProBudget());
        dto.setStatus(Integer.valueOf(entity.getProStatus()));
        dto.setCoverImgUrl(entity.getCoverImg());
        dto.setInfo(entity.getInfo());
        dto.setIntroduction(entity.getIntroduction());
        dto.setIsTop(entity.getIsTop());
        dto.setTopImg(entity.getTopImg());
        dto.setPublishTime(entity.getCreateTime().toString());
        dto.setViewsCount(entity.getViewsCount());
        Integer createBy = entity.getCreateUserId();
        if (userpersonalResponsitory.findByUid(createBy) != null) {dto.setPublishPepole(userpersonalResponsitory.findByUid(createBy).getName());}
        return dto;
    }
    public PortalBigProjectEntity dtoToEntity(BigProjectDetail dto,Integer id){
        PortalBigProjectEntity entity;
        if(id==null){
            entity = new PortalBigProjectEntity();
        }else{
            entity = bigProjectRepository.findOne(id);
        }
        entity.setName(dto.getName());
        entity.setProBudget(dto.getPrice());
        byte bt = (byte)dto.getStatus().intValue();
        entity.setProStatus(bt);
        entity.setCoverImg(dto.getCoverImgUrl());
        entity.setInfo(dto.getInfo());
        entity.setIntroduction(dto.getIntroduction());

        return entity;
    }

}
