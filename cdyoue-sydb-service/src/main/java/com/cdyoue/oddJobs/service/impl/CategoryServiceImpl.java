package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.FunctioncategoryResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.OutsourcingProjectTypeResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.PortalDomainResponsitory;
import com.cdyoue.oddJobs.dto.common.Category;
import com.cdyoue.oddJobs.entity.lgsq.FunctioncategoryEntity;
import com.cdyoue.oddJobs.entity.lgsq.common.OutsourcingProjectTypeEntity;
import com.cdyoue.oddJobs.entity.lgsq.common.PortalDomainEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.CategoryMapper;
import com.cdyoue.oddJobs.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by liaoyoule on 2017/4/20.
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private OutsourcingProjectTypeResponsitory outsourcingProjectTypeResponsitory;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private PortalDomainResponsitory portalDomainResponsitory;
    @Autowired
    private FunctioncategoryResponsitory functioncategoryResponsitory;

    @Override
    @Cacheable(value = "categorys")
    public List<Category> findRequirementType() {
        List<OutsourcingProjectTypeEntity> optes = outsourcingProjectTypeResponsitory.findAll();
        if (optes.size() == 0) {
            throw new NotFoundEntityException();
        }
        return optes.stream().map(pde -> categoryMapper.outsourcingProjectTypeEntityToCategory(pde)).collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "trades")
    public List<Category> findTrades() {
        List<PortalDomainEntity> pdes = portalDomainResponsitory.findAll();
        if (pdes.size() == 0) {
            throw new NotFoundEntityException();
        }

        return pdes.stream().map(pde -> categoryMapper.portalDomainEntityToCategory(pde)).collect(Collectors.toList());
    }

    @Override
    public List<Category> findPros(Integer id) {

        List<FunctioncategoryEntity> ftes = id == null ? functioncategoryResponsitory.findByParentIdIsNull() : functioncategoryResponsitory.findByParentId(id);
        if (ftes.size() == 0) {
            throw new NotFoundEntityException();
        }
        return ftes.stream().map(pde -> categoryMapper.functioncategoryToCategory(pde)).collect(Collectors.toList());
    }
}
