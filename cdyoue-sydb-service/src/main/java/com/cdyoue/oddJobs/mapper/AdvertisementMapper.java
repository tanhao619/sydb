package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dao.lqsq.UserResponsitory;
import com.cdyoue.oddJobs.dto.advertisement.Advertisement;
import com.cdyoue.oddJobs.dto.advertisement.advertisementInfo;
import com.cdyoue.oddJobs.entity.lgsq.PartalTopEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.oddJobs.utils.UserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2017/5/10.
 */
@Component
public class AdvertisementMapper {
    @Autowired
    UserResponsitory userResponsitory;

    public Advertisement entityToDto(PartalTopEntity entity){
        Advertisement advertisementDTO = new Advertisement();
        BeanUtils.copyProperties(entity,advertisementDTO);
        advertisementDTO.setRefreshTime(entity.getRefreshTime().getTime());
        int createBy =  entity.getCreateBy();
        UserEntity t = userResponsitory.findOne(createBy);
        //advertisementDTO.setCreateBy(t.getUserName());
        advertisementDTO.setCreateBy(UserUtils.getUserName(createBy));
        return advertisementDTO;
    }


    public PartalTopEntity infoToEntity(advertisementInfo advertisement) {
        PartalTopEntity entity = new PartalTopEntity();
        BeanUtils.copyProperties(advertisement,entity);
        entity.setCreateBy(SecurityUtils.getCurrentUserLogin().getId());
        entity.setRefreshTime(new Timestamp(System.currentTimeMillis()));
        return  entity;
    }
}
