package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.SuccaseRepository;
import com.cdyoue.oddJobs.dto.xqdt.SuccaseDetail;
import com.cdyoue.oddJobs.dto.xqdt.SuccaseSummary;
import com.cdyoue.oddJobs.entity.lgsq.SuccaseEntity;
import com.cdyoue.oddJobs.mapper.SuccaseMapper;
import com.cdyoue.oddJobs.service.SuccaseService;
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
 * Created by luanyu on 2017/5/24.
 */
@Service
public class SuccaseServiceImpl extends ServiceSupport<SuccaseEntity> implements SuccaseService {
    @Autowired
    private SuccaseMapper mapper;

    @Autowired
    private SuccaseRepository succaseRepository;

    @Override
    public SuccaseSummary getSuccaseById(Integer id) {
        SuccaseEntity entity = succaseRepository.findOne(id);
        return mapper.entityToDto(entity);
    }

    @Override
    public boolean getSuccaseCheck(Integer id) {
        if(succaseRepository.findOne(id)==null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public String createSuccase(SuccaseDetail succase) {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        SuccaseEntity entity = mapper.dtoToEntity(succase,null);
        entity.setCreateBy(SecurityUtils.getCurrentUserLogin().getId());
        entity.setPublishTime(time);
        entity.setViewCount(0);
        return String.valueOf(succaseRepository.save(entity).getId());
    }

    @Override
    public void updateSuccase(Integer id, SuccaseDetail succase) {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        SuccaseEntity entity = mapper.dtoToEntity(succase,id);
        entity.setUpdateBy(SecurityUtils.getCurrentUserLogin().getId());
        entity.setUpdateTime(time);
        succaseRepository.save(entity);
    }

    @Override
    public PageInfo<SuccaseSummary> findList(PageRequest pr, String q) {
        Page<SuccaseEntity> rpPage = super.findByOneLike("name",q,pr);
        List<SuccaseSummary> DetailList = rpPage.getContent().stream().map(p -> mapper.entityToDto(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(DetailList, pr, rpPage.getTotalElements()));
    }

    @Override
    public Integer getCreatorIdBy(Integer id) {
        return succaseRepository.findOne(id).getCreateBy();
    }

    @Override
    public void deleteSuccase(Integer id) {
        SuccaseEntity entity = succaseRepository.findOne(id);
        succaseRepository.delete(entity);
    }

    @Override
    public Class getJpaRepositoryClazz() {
        return SuccaseRepository.class;
    }
}
