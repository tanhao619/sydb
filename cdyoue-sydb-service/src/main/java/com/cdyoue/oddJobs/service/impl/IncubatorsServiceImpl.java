package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.PortalIncubatorLevelResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.PortalIncubatorResponsitory;
import com.cdyoue.oddJobs.dto.scfw.IncubatorDetail;
import com.cdyoue.oddJobs.entity.lgsq.PortalIncubatorEntity;
import com.cdyoue.oddJobs.entity.lgsq.PortalIncubatorLevelEntity;
import com.cdyoue.oddJobs.mapper.IncubatorsMapper;
import com.cdyoue.oddJobs.service.IncubatorsService;
import com.cdyoue.oddJobs.service.support.ServiceSupport;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by luanyu on 2017/5/18.
 */
@Service
public class IncubatorsServiceImpl extends ServiceSupport<PortalIncubatorEntity> implements IncubatorsService {

    @Autowired
    PortalIncubatorResponsitory incubatorResponsitory;
    @Autowired
    IncubatorsMapper incubatorsMapper;

    @Autowired
    PortalIncubatorLevelResponsitory portalIncubatorLevelResponsitory;

    @Override
    public IncubatorDetail getIncubatorsById(Integer id) {
        IncubatorDetail incubatorDetail = incubatorsMapper.entityToDto(incubatorResponsitory.findOne(id));
        PortalIncubatorLevelEntity one = portalIncubatorLevelResponsitory.findOne(incubatorDetail.getLevel());
        incubatorDetail.setLevelName(one.getName());
        return incubatorDetail;
    }

    @Override
    public boolean getIncubatorsCheck(Integer id) {
        if(incubatorResponsitory.findOne(id)==null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public String createIncubators(IncubatorDetail Incubators) {
        PortalIncubatorEntity entity = incubatorsMapper.dtoToEntity(Incubators,null);
        entity.setCreateBy(SecurityUtils.getCurrentUserLogin().getId());
        entity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return String.valueOf(incubatorResponsitory.save(entity).getId());
    }

    @Override
    public void updateIncubators(Integer id, IncubatorDetail Incubators) {
        PortalIncubatorEntity entity = incubatorsMapper.dtoToEntity(Incubators,id);
        incubatorResponsitory.save(entity);
    }

    @Override
    public PageInfo<IncubatorDetail> findList(PageRequest pr, String q) {
        Page<PortalIncubatorEntity> rpPage = super.findByOneLike("name",q,pr);
        List<IncubatorDetail> incubatorDetailList = rpPage.getContent().stream().map(p ->
                incubatorsMapper.entityToDto(p))
                .collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(incubatorDetailList, pr, rpPage.getTotalElements()));
    }

    @Override
    public void deleteIncubators(Integer id) {
        PortalIncubatorEntity entity = incubatorResponsitory.findOne(id);
        incubatorResponsitory.delete(entity);
    }

    @Override
    public Integer getCreatorIdBy(Integer id) {
        PortalIncubatorEntity entity = incubatorResponsitory.findOne(id);
        return entity.getCreateBy();
    }

    @Override
    public Class getJpaRepositoryClazz() {
        return PortalIncubatorResponsitory.class;
    }
}
