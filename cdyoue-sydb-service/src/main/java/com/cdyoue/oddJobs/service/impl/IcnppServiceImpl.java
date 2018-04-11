package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.icnpp.PolicyRespository;
import com.cdyoue.oddJobs.dto.icnpp.policy.PolicyDetail;
import com.cdyoue.oddJobs.dto.icnpp.policy.PolicySumary;
import com.cdyoue.oddJobs.entity.icnpp.PolicyEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.IcnppMapper;
import com.cdyoue.oddJobs.service.IcnppService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dengshaojun on 2017/5/9.
 */
@Service
public class IcnppServiceImpl implements IcnppService {

    @Autowired
    private PolicyRespository policyRespository;
    @Autowired
    private SpecificationHelper specificationHelper;
    @Autowired
    private IcnppMapper icnppMapper;
    @Override
    public PageInfo<PolicySumary> getPolices(String q, PageRequest pr) {
        List<QueryRequest> qrs = new ArrayList<>();
        QueryRequest qrE = new QueryRequest();
        qrE.setF("title");
        qrE.setV(q);
        qrE.setO(Operator.LIKE);
        qrs.add(qrE);
        Specification specifica  = specificationHelper.getSpecifica(PolicyEntity.class, qrs);

        Page<PolicyEntity> pePager = policyRespository.findAll(specifica, pr);

        List<PolicyEntity> pes = pePager.getContent();
        if(pes.size() == 0){
            throw new NotFoundEntityException();
        }


        List<PolicySumary> pss = pes.stream().map(policyEntity -> icnppMapper.policyEntityToPolicySumary(policyEntity))
                .collect(Collectors.toList());

        return new PageInfo<>(new PageImpl(pss,pr,pePager.getTotalElements()));
    }

    @Override
    public PolicyDetail getPolice(String id) {
        PolicyEntity pe = policyRespository.findOne(id);
        if(pe ==null){
            throw new NotFoundEntityException();
        }

        return icnppMapper.policyEntityToPolicyDetail(pe);
    }
}
