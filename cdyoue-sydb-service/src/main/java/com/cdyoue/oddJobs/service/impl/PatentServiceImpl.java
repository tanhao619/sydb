package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.PatentResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.PortalPatentDataResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.PortalPricesInfoResponsitory;
import com.cdyoue.oddJobs.dto.zscq.PatentBigdataPriceComp;
import com.cdyoue.oddJobs.dto.zscq.PatentBigdataSummary;
import com.cdyoue.oddJobs.dto.zscq.PatentNationDetail;
import com.cdyoue.oddJobs.dto.zscq.PatentNationSummary;
import com.cdyoue.oddJobs.entity.lgsq.PatentEntity;
import com.cdyoue.oddJobs.entity.lgsq.PortalPatentDataEntity;
import com.cdyoue.oddJobs.entity.lgsq.PortalPricesInfoEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.PatentMapper;
import com.cdyoue.oddJobs.service.PatentService;
import com.cdyoue.oddJobs.spec.Operator;
import com.cdyoue.oddJobs.spec.QueryRequest;
import com.cdyoue.oddJobs.spec.SpecificationHelper;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dengshaojun on 2017/5/22.
 */
@Service
public class PatentServiceImpl implements PatentService {



    @Autowired
    private SpecificationHelper specificationHelper;

    @Autowired
    private PatentResponsitory patentResponsitory;

    @Autowired
    private PortalPatentDataResponsitory portalPatentDataResponsitory;

    @Autowired
    private PortalPricesInfoResponsitory portalPricesInfoResponsitory;

    @Autowired
    private PatentMapper patentMapper;

    @Override
    public PageInfo<PatentNationSummary> findByPatentname(String q, String type, Pageable pageRequest) {
        List<QueryRequest> queryRequest = new ArrayList<>();
        QueryRequest qe = new QueryRequest();
        qe.setF("name");
        qe.setO(Operator.LIKE);
        qe.setV(q);
        queryRequest.add(qe);

        if (type != null && !type.trim().equals("")) {
            QueryRequest qe1 = new QueryRequest();
            qe1.setF("patentType");
            qe1.setO(Operator.EQ);
            qe1.setV(type);
            queryRequest.add(qe1);
        }

        Specification specifica = specificationHelper.getSpecifica(PatentEntity.class, queryRequest);
        Page<PatentEntity> patentEntityPage = patentResponsitory.findAll(specifica, pageRequest);
        if (patentEntityPage.getContent().size() == 0) {
            throw new NotFoundEntityException("数据不存在");
        }
        List<PatentNationSummary> patentNationSummaries = patentEntityPage.getContent().stream().map(p -> patentMapper.patentEntityToPatentNationSummary(p)).collect(Collectors.toList());
        return new PageInfo<PatentNationSummary>(new PageImpl(patentNationSummaries, pageRequest, patentEntityPage.getTotalElements()));
    }

    @Override
    public PatentNationDetail findById(Integer id) {
        PatentEntity patentEntity = patentResponsitory.findOne(id);
        if (patentEntity == null) {
            throw new NotFoundEntityException("数据不存在");
        }
        PatentNationDetail patentNationDetail = patentMapper.patentEntityToPatentNationDetail(patentEntity);
        return patentNationDetail;
    }

    @Override
    public PageInfo<PatentBigdataSummary> findBdsByPatentname(String q, Pageable pageRequest) {
        List<QueryRequest> queryRequest = new ArrayList<>();
        QueryRequest qe = new QueryRequest();
        qe.setF("name");
        qe.setO(Operator.LIKE);
        qe.setV(q);
        queryRequest.add(qe);

        Specification specifica = specificationHelper.getSpecifica(PortalPatentDataEntity.class, queryRequest);
        Page<PortalPatentDataEntity> page = portalPatentDataResponsitory.findAll(specifica, pageRequest);
        if (page.getContent().size() == 0) {
            throw new NotFoundEntityException("数据不存在");
        }
        List<PatentBigdataSummary> patentBigdataSummaries = page.getContent().stream().map(p -> patentMapper.portalPatentDataEntityToPatentBigdataSummary(p)).collect(Collectors.toList());
        return new PageInfo<PatentBigdataSummary>(new PageImpl(patentBigdataSummaries, pageRequest, page.getTotalElements()));
    }

    @Override
    public List<PatentBigdataPriceComp> findBdpriByPatentname(String name) {
        List<PortalPricesInfoEntity> portalPricesInfoEntities = portalPricesInfoResponsitory.findByName(name);
        if (portalPricesInfoEntities.size() == 0 || portalPricesInfoEntities == null) {
            throw new NotFoundEntityException("数据不存在");
        }
        List<PatentBigdataPriceComp> patentBigdataPriceComps = portalPricesInfoEntities.stream().map(p -> patentMapper.portalPricesInfoEntityToPatentBigdataPriceComp(p)).collect(Collectors.toList());
        return  patentBigdataPriceComps;
    }

    @Override
    public List<PatentNationSummary> findByIds(List<Integer> patentids) {
        List<PatentEntity> patentEntities = patentResponsitory.findByIds(patentids);
        if (patentEntities.size() == 0 || patentEntities == null) {
            throw new NotFoundEntityException("数据不存在");
        }
        List<PatentNationSummary> patentNationSummaries = patentEntities.stream().map(p -> patentMapper.patentEntityToPatentNationSummary(p)).collect(Collectors.toList());
        return patentNationSummaries;
    }

    @Override
    public void deletePatentBdpById(Integer id) {
        portalPricesInfoResponsitory.delete(id);
    }

    @Override
    public void deletePatentBdById(Integer id) {
        portalPatentDataResponsitory.delete(id);
    }

    @Override
    public void deletePatentById(Integer id) {
        patentResponsitory.delete(id);
    }

}
