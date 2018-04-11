package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.AdvertisementResponsitory;
import com.cdyoue.oddJobs.dto.advertisement.Advertisement;
import com.cdyoue.oddJobs.dto.advertisement.advertisementInfo;
import com.cdyoue.oddJobs.en.AdvertisementTypeEnum;
import com.cdyoue.oddJobs.entity.lgsq.PartalTopEntity;
import com.cdyoue.oddJobs.mapper.AdvertisementMapper;
import com.cdyoue.oddJobs.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/10.
 */
@Service
public class AdvertisementServiceImpl implements AdvertisementService {
    @Autowired
    AdvertisementResponsitory advertisementResponsitory;
    @Autowired
    AdvertisementMapper advertisementMapper;

    @Override//查询一个广告信息
    public Advertisement getAdvertisement(String view, Integer id) {
        int pageNumber =-1;
        for (AdvertisementTypeEnum e : AdvertisementTypeEnum.values()) {
            if (e.getValue().equals(view)) {
                pageNumber = e.getId();
            }
        }
        PartalTopEntity entity  = advertisementResponsitory.findByIdAndView(id,pageNumber);
        Advertisement AdvertisementDTO = advertisementMapper.entityToDto(entity);
        return AdvertisementDTO;
    }

    @Override//编辑一条广告信息
    public void updateAdvertisement(String view, Integer id, advertisementInfo advertisement) {
        int pageNumber =-1;
        for (AdvertisementTypeEnum e : AdvertisementTypeEnum.values()) {
            if (e.getValue().equals(view)) {
                pageNumber = e.getId();
            }
        }
        PartalTopEntity entity = advertisementMapper.infoToEntity(advertisement);
        entity.setPageNum(pageNumber);
        entity.setOrderBy(id);
        advertisementResponsitory.save(entity);
    }

    @Override//查询一个页面所有广告
    public List<Advertisement> findPageAll(String view) {
        int pageNumber =-1;
        for (AdvertisementTypeEnum e : AdvertisementTypeEnum.values()) {
            if (e.getValue().equals(view)) {
                pageNumber = e.getId();
            }
        }
        List<PartalTopEntity> partalTopEntities = advertisementResponsitory.findPageAll(pageNumber);
        List<Advertisement> advertisements = new ArrayList<>();
       for (int i = 0;i< partalTopEntities.size();i++){
           advertisements.add(advertisementMapper.entityToDto(partalTopEntities.get(i)));
       }
        return advertisements;
    }
}
