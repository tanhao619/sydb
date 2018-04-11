package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.BigProjectRepository;
import com.cdyoue.oddJobs.dto.xqdt.BigProjectDetail;
import com.cdyoue.oddJobs.entity.lgsq.PortalBigProjectEntity;
import com.cdyoue.oddJobs.mapper.BigProjectMapper;
import com.cdyoue.oddJobs.service.BigPorjectService;
import com.cdyoue.oddJobs.service.support.ServiceSupport;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by luanyu on 2017/5/20.
 */
@Service
public class BigProjectServiceImpl extends ServiceSupport<PortalBigProjectEntity> implements BigPorjectService {

    @Autowired
    BigProjectRepository bigProjectRepository;
    @Autowired
    BigProjectMapper bigProjectMapper;

    @Override
    public BigProjectDetail getBigProjectById(Integer id) {
        PortalBigProjectEntity entity = bigProjectRepository.findOne(id);
        return bigProjectMapper.entityToDto(entity);
    }

    @Override
    public boolean getBigProjectCheck(Integer id) {
        if(bigProjectRepository.findOne(id)==null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public String createBigProject(BigProjectDetail bigProjectDetail) {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        PortalBigProjectEntity entity = bigProjectMapper.dtoToEntity(bigProjectDetail,null);
        entity.setCreateUserId(SecurityUtils.getCurrentUserLogin().getId());
        entity.setCreateTime(time);
        entity.setViewsCount(0);
        entity.setIsTop(0);
        return String.valueOf(bigProjectRepository.save(entity).getId());
    }

    @Override
    public void updateBigProject(Integer id, BigProjectDetail bigProject) {
        PortalBigProjectEntity entity = bigProjectMapper.dtoToEntity(bigProject,id);
        bigProjectRepository.save(entity);
    }

    @Override
    public void topBigProject(Integer id,String topImg) {
        PortalBigProjectEntity entity =bigProjectRepository.findOne(id);
        entity.setIsTop(1);
        entity.setTopImg(topImg);
        bigProjectRepository.save(entity);
    }

    @Override
    public void cancelTopBigProject(Integer id) {
        PortalBigProjectEntity entity =bigProjectRepository.findOne(id);
        entity.setIsTop(0);
        bigProjectRepository.save(entity);
    }

    @Override
    public void cancelTopBigProjects() {
        String id = bigProjectRepository.getTopBigProjectId();
        if(id!=null){
            PortalBigProjectEntity entity =bigProjectRepository.findOne(Integer.valueOf(id));
            entity.setIsTop(0);
            bigProjectRepository.save(entity);
        }
    }

    @Override
    public PageInfo<BigProjectDetail> findList(PageRequest pr, String q) {
        Page<PortalBigProjectEntity> rpPage = super.findByOneLike("name",q,pr);
        List<BigProjectDetail> incubatorDetailList = rpPage.getContent().stream().map(p -> bigProjectMapper.entityToDto(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(incubatorDetailList, pr, rpPage.getTotalElements()));
    }

    @Override
    public Integer getCreatorIdBy(Integer id) {
        return bigProjectRepository.findOne(id).getCreateUserId();
    }

    @Override
    @Transactional
    public void deleteBigProject(Integer id) {
        PortalBigProjectEntity entity = bigProjectRepository.findOne(id);
        bigProjectRepository.delete(entity);
    }

    @Override
    public BigProjectDetail getTopBigProject() {
        String id = bigProjectRepository.getTopBigProjectId();
        PortalBigProjectEntity entity;
        if(id==null){
            entity = bigProjectRepository.getLastBigProjectId();
        } else {
            entity = bigProjectRepository.findOne(Integer.valueOf(id));
        }
        return bigProjectMapper.entityToDto(entity);
    }

    @Override
    public List<PortalBigProjectEntity> findAll() {
        return bigProjectRepository.findAll();
    }

    @Override
    public Class getJpaRepositoryClazz() {
        return BigProjectRepository.class;
    }
}
