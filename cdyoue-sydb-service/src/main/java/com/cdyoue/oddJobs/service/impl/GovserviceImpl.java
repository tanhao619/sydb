package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.GovserviceResponsitory;
import com.cdyoue.oddJobs.dto.ggfw.Govservice;
import com.cdyoue.oddJobs.entity.lgsq.GovServiceEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.GovserviceMapper;
import com.cdyoue.oddJobs.service.GovserviceService;
import com.cdyoue.oddJobs.service.support.ServiceSupport;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tr on 2017/5/15.
 */

@Service
public class GovserviceImpl extends ServiceSupport<GovServiceEntity> implements GovserviceService {
    @Autowired
    private GovserviceResponsitory govserviceResponsitory;
    @Autowired
    private GovserviceMapper govserviceMapper;

    @Override
    public Class getJpaRepositoryClazz() {
        return GovserviceResponsitory.class;
    }

    @Override
    public PageInfo<Govservice> findByKeyWord(Pageable requestPage, String q) {
        Page<GovServiceEntity> rpPage = super.findByStrLike("name", q, requestPage);
        List<Govservice> govservices =  rpPage.getContent().stream().map( p -> govserviceMapper.govServiceEntityToGovService(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(govservices, requestPage, rpPage.getTotalElements()));
    }

    @Override
    public Integer createGovservice(Govservice news) {
        Integer createBy = SecurityUtils.getCurrentUserLogin().getId();
        GovServiceEntity govServiceEntity = govserviceMapper.govServiceToGovServiceEntity(news);
        UserEntity userEntity = new UserEntity();
        userEntity.setId(createBy);
        govServiceEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        govServiceEntity.setCreateBy(userEntity);
        Integer id = govserviceResponsitory.save(govServiceEntity).getId();
        return id;
    }

    @Override
    public GovServiceEntity findGovserviceById(Integer id) {
        try{
            return  govserviceResponsitory.getOne(id);
        }catch(Exception e){
            throw new EntityNotFoundException("数据没找到");
        }
    }

    @Override
    public boolean updateOrDeleteAuthority(Integer id) {
        GovServiceEntity govServiceEntity = govserviceResponsitory.getOne(id);
        return SecurityUtils.getCurrentUserLogin().getId() == govServiceEntity.getCreateBy().getId();
    }

    @Override
    public void updateGovservice(Integer id, Govservice govservice) {
        GovServiceEntity govServiceEntity = findGovserviceById(id);
        govServiceEntity.setName(govservice.getTitle());
        govServiceEntity.setLink(govservice.getLink());
        govServiceEntity.setCoverImg(govservice.getLogoUrl());
        govServiceEntity.setInfo(govservice.getIntroduction());
        govserviceResponsitory.save(govServiceEntity);
    }

    @Override
    public void deleteGovService(Integer id) {
        govserviceResponsitory.delete(id);
    }

    @Override
    public Govservice getGovserviceById(Integer id) {
        try {
            return govserviceMapper.govServiceEntityToGovService(govserviceResponsitory.getOne(id));
        }catch(Exception e){
            throw new NotFoundEntityException("数据不存在");
        }
    }
}
