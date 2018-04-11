package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.AlgorithmTypeResponsitory;
import com.cdyoue.oddJobs.dto.algorithm.AlgorithmTypeRequest;
import com.cdyoue.oddJobs.dto.algorithm.AlgorithmTypeSumary;
import com.cdyoue.oddJobs.entity.lgsq.AlgorithmOperatorEntity;
import com.cdyoue.oddJobs.entity.lgsq.AlgorithmTypeEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.AlgorithmMapper;
import com.cdyoue.oddJobs.service.AlgorithmTypeService;
import com.cdyoue.oddJobs.spec.Operator;
import com.cdyoue.oddJobs.spec.QueryRequest;
import com.cdyoue.oddJobs.spec.SpecificationHelper;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by liaoyoule on 2017/6/21.
 */
@Service
public class AlgorithmTypeServiceImpl implements AlgorithmTypeService {
    @Autowired
    private AlgorithmTypeResponsitory algorithmTypeResponsitory;
    @Autowired
    private SpecificationHelper specificationHelper;
    @Autowired
    private AlgorithmMapper algorithmMapper;

    @Override
    public PageInfo<AlgorithmTypeSumary> getAlgorithmTypes(String q, PageRequest pr) {
        List<QueryRequest> qrs = new ArrayList<>();

        QueryRequest qrL = new QueryRequest();
        qrL.setO(Operator.LIKE);
        qrL.setF("name");
        qrL.setV(q);
        qrs.add(qrL);
        Specification<AlgorithmTypeEntity> specifica = specificationHelper.getSpecifica(AlgorithmOperatorEntity.class, qrs);

        Page<AlgorithmTypeEntity> aoPage = algorithmTypeResponsitory.findAll(specifica, pr);

        List<AlgorithmTypeEntity> aoes = aoPage.getContent();

        if (aoes.size() == 0) {
            throw new NotFoundEntityException();
        }


        List<AlgorithmTypeSumary> abs = aoes.stream().map(aoe -> algorithmMapper.algorithmTypeEntityToAlgorithmTypeSumary(aoe))
                .collect(Collectors.toList());

        return new PageInfo(new PageImpl(abs, pr, aoPage.getTotalElements()));
    }

    @Override
    public void deleteAlgorithmTypes(Integer id) {
        algorithmTypeResponsitory.delete(id);
    }

    @Override
    public AlgorithmTypeSumary getAlgorithmType(Integer id) {
        AlgorithmTypeEntity ate = algorithmTypeResponsitory.findOne(id);
        if(ate==null){
            throw new NotFoundEntityException();
        }

        return algorithmMapper.algorithmTypeEntityToAlgorithmTypeSumary(ate);
    }

    @Override
    public void createAlgorithmType(AlgorithmTypeRequest algorithmTypeRequest) {
        AlgorithmTypeEntity ate = new AlgorithmTypeEntity();
        UserEntity ue = new UserEntity();
        ue.setId(SecurityUtils.getCurrentUserLogin().getId());
        ate.setCreateBy(ue);
        ate.setCreateTime(new Timestamp(System.currentTimeMillis()));
        ate.setIntro(algorithmTypeRequest.getIntro());
        ate.setName(algorithmTypeRequest.getName());
        algorithmTypeResponsitory.save(ate);
    }

    @Override
    public void updateAlgorithmType(Integer id, AlgorithmTypeRequest algorithmTypeRequest) {
        AlgorithmTypeEntity ute = algorithmTypeResponsitory.findOne(id);
        if(ute == null){
            throw new NotFoundEntityException();
        }

        ute.setName(algorithmTypeRequest.getName());
        ute.setIntro(algorithmTypeRequest.getIntro());
        ute.setUpdateBy(SecurityUtils.getCurrentUserLogin().getId());
        ute.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        algorithmTypeResponsitory.save(ute);
    }
}
