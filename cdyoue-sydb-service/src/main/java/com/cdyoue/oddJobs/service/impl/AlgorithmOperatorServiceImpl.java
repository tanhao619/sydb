package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.AlgorithmOperatorResponsitory;
import com.cdyoue.oddJobs.dto.algorithm.AlgorithmBase;
import com.cdyoue.oddJobs.dto.algorithm.AlgorithmRequest;
import com.cdyoue.oddJobs.dto.algorithm.AlgorithmSumary;
import com.cdyoue.oddJobs.entity.lgsq.AlgorithmOperatorEntity;
import com.cdyoue.oddJobs.entity.lgsq.AlgorithmTypeEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.AlgorithmMapper;
import com.cdyoue.oddJobs.service.AlgorithmOperatorService;
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
 * Created by liaoyoule on 2017/6/14.
 */
@Service
public class AlgorithmOperatorServiceImpl implements AlgorithmOperatorService {
    @Autowired
    private AlgorithmOperatorResponsitory algorithmOperatorResponsitory;
    @Autowired
    private SpecificationHelper specificationHelper;
    @Autowired
    private AlgorithmMapper algorithmMapper;

    @Override
    public PageInfo<AlgorithmBase> getAlgorithms(String q, PageRequest pr) {
        List<QueryRequest> qrs = new ArrayList<>();

        QueryRequest qrL = new QueryRequest();
        qrL.setO(Operator.LIKE);
        qrL.setF("name");
        qrL.setV(q);
        qrs.add(qrL);
        Specification<AlgorithmOperatorEntity> specifica = specificationHelper.getSpecifica(AlgorithmOperatorEntity.class, qrs);

        Page<AlgorithmOperatorEntity> aoPage = algorithmOperatorResponsitory.findAll(specifica, pr);

        List<AlgorithmOperatorEntity> aoes = aoPage.getContent();

        if (aoes.size() == 0) {
            throw new NotFoundEntityException();
        }


        List<AlgorithmBase> abs = aoes.stream().map(aoe -> algorithmMapper.AlgorithmOperatorEntityToAlgorithmBase(aoe))
                .collect(Collectors.toList());

        return new PageInfo(new PageImpl(abs, pr, aoPage.getTotalElements()));
    }

    @Override
    public void delete(Integer id) {
        algorithmOperatorResponsitory.delete(id);
    }

    @Override
    public AlgorithmSumary getAlgorithm(Integer id) {
        AlgorithmOperatorEntity aoe = algorithmOperatorResponsitory.findOne(id);
        if(aoe == null){
            throw new NotFoundEntityException();
        }

        return algorithmMapper.AlgorithmOperatorEntityToAlgorithmSumary(aoe);
    }

    @Override
    public void createAlgorithm(AlgorithmRequest algorithmRequest) {
        AlgorithmOperatorEntity aoe = algorithmMapper.algorithmRequestToAlgorithmOperatorEntity(algorithmRequest);
        UserEntity ue = new UserEntity();
        ue.setId(SecurityUtils.getCurrentUserLogin().getId());
        aoe.setCreateBy(ue);
        aoe.setCreateTime(new Timestamp(System.currentTimeMillis()));
        algorithmOperatorResponsitory.save(aoe);
    }

    @Override
    public void updateAlgorithm(Integer id, AlgorithmRequest algorithmRequest) {

        AlgorithmOperatorEntity aoeF = algorithmOperatorResponsitory.findOne(id);

        if(aoeF == null){
            throw new NotFoundEntityException();
        }

        aoeF.setDataUrl(algorithmRequest.getDataUrl());
        aoeF.setIntro(algorithmRequest.getIntro());
        aoeF.setOperatorUrl(algorithmRequest.getOperatorUrl());
        aoeF.setName(algorithmRequest.getName());
        AlgorithmTypeEntity ae = new AlgorithmTypeEntity();
        ae.setId(algorithmRequest.getType());
        aoeF.setType(ae);


        aoeF.setId(id);
        aoeF.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        UserEntity ue = new UserEntity();
        ue.setId(SecurityUtils.getCurrentUserLogin().getId());
        aoeF.setUpdateBy(ue);
        algorithmOperatorResponsitory.save(aoeF);
    }
}
