package com.cdyoue.oddJobs.service.sydb.impl;

import com.cdyoue.oddJobs.constant.DataTypeConstant;
import com.cdyoue.oddJobs.dao.lqsq.UserenterpriseResponsitory;
import com.cdyoue.oddJobs.dao.syData.ApplicationDirectionRepository;
import com.cdyoue.oddJobs.dao.syData.ApplicationDirectionViewRepository;
import com.cdyoue.oddJobs.dto.zlcy.ApplicationDirectionDetail;
import com.cdyoue.oddJobs.dto.zlcy.ApplicationDirectionMini;
import com.cdyoue.oddJobs.entity.syData.SyApplicationDirectionMessageEntity;
import com.cdyoue.oddJobs.entity.syData.SyApplicationDirectionView;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.sydb.ApplicationDirectionMapper;
import com.cdyoue.oddJobs.service.sydb.ApplicationDirectionService;
import com.cdyoue.oddJobs.spec.Operator;
import com.cdyoue.oddJobs.spec.QueryRequest;
import com.cdyoue.oddJobs.spec.SpecificationHelper;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dengshaojun on 2017/10/12.
 */
@Service
@Transactional
public class ApplicationDirectionServiceImpl implements ApplicationDirectionService {

    @Autowired
    private ApplicationDirectionRepository applicationDirectionRepository;

    @Autowired
    private ApplicationDirectionViewRepository applicationDirectionViewRepository;

    @Autowired
    private ApplicationDirectionMapper applicationDirectionMapper;

    @Autowired
    private SpecificationHelper specificationHelper;

    @Override
    public PageInfo<ApplicationDirectionMini> findApplicationDirections(String q, PageRequest pageRequest) {
        List<QueryRequest> queryRequest = new ArrayList<>();
        QueryRequest qe = new QueryRequest();
        qe.setF("enterName");
        qe.setO(Operator.LIKE);
        qe.setV(q);
        queryRequest.add(qe);
        Specification specification = specificationHelper.getSpecifica(SyApplicationDirectionView.class, queryRequest);
        Page<SyApplicationDirectionView> page = applicationDirectionViewRepository.findAll(specification, pageRequest);
        if (page.getContent().size() == 0) {
            throw new NotFoundEntityException("数据不存在");
        }
        List<ApplicationDirectionMini> list =  page.getContent().stream().map(p -> applicationDirectionMapper.viewToMini(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(list, pageRequest, page.getTotalElements()));
    }

    @Override
    public ApplicationDirectionDetail findApplicationDirection(Integer id) {
        SyApplicationDirectionMessageEntity entity = applicationDirectionRepository.findOne(id);
        if (entity == null) {
            throw new NotFoundEntityException("数据不存在");
        }
        // 改变状态
        entity.setStatus(new Byte("1"));
        applicationDirectionRepository.save(entity);
        ApplicationDirectionDetail applicationDirectionDetail = applicationDirectionMapper.entityToDetail(entity);
        return applicationDirectionDetail;
    }

    @Override
    public void deleteApplicationDirection(Integer[] ids) {
        List<SyApplicationDirectionMessageEntity> list = applicationDirectionRepository.findAll(Arrays.asList(ids));
        if (list.size() == 0) {
            throw new NotFoundEntityException("操作对象不存在");
        }
        applicationDirectionRepository.delete(list);
    }
}
