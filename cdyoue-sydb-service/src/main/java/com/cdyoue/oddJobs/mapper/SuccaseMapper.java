package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dao.lqsq.SuccaseRepository;
import com.cdyoue.oddJobs.dao.lqsq.UserpersonalResponsitory;
import com.cdyoue.oddJobs.dto.xqdt.SuccaseDetail;
import com.cdyoue.oddJobs.dto.xqdt.SuccaseSummary;
import com.cdyoue.oddJobs.entity.lgsq.SuccaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * Created by luanyu on 2017/5/24.
 */
@Component
public class SuccaseMapper {
    @Autowired
    SuccaseRepository succaseRepository;

    @Autowired
    private UserpersonalResponsitory userpersonalResponsitory;


    public SuccaseSummary entityToDto(SuccaseEntity entity){
        SuccaseSummary dto = new SuccaseSummary();
        dto.setId(entity.getId());
        dto.setTitle(entity.getName());
        dto.setCoverImgUrl(entity.getCoverImg());
        dto.setInfo(entity.getIntroduction());
        dto.setIntroduction(entity.getInfo());
        Integer i = entity.getCreateBy();
        dto.setCreateBy(entity.getCreateBy());
        try {
            dto.setPublishPepole(userpersonalResponsitory.findByUid(i).getName());
        }catch (Exception e){
            dto.setPublishPepole("未知");
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(entity.getPublishTime()!=null){
            String time = df.format(entity.getPublishTime());
            time = time.replace("-","/");
            dto.setPublishTime(time);
        }
        dto.setViewsCount(entity.getViewCount());
        return dto;
    }

    public SuccaseEntity dtoToEntity(SuccaseDetail dto, Integer id){
        SuccaseEntity entity;
        if(id==null){
            entity = new SuccaseEntity();
        }else{
            entity = succaseRepository.findOne(id);
        }
        entity.setName(dto.getTitle());
        entity.setCoverImg(dto.getCoverImgUrl());
        entity.setInfo(dto.getIntroduction());
        entity.setIntroduction(dto.getContent());

        return entity;
    }
}
