package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.constant.DataTypeConstant;
import com.cdyoue.oddJobs.dao.lqsq.PortalIndependentTextResponsitory;
import com.cdyoue.oddJobs.dto.independent.IndependentMine;
import com.cdyoue.oddJobs.dto.independent.IndependentSumary;
import com.cdyoue.oddJobs.dto.independent.RequestIndependent;
import com.cdyoue.oddJobs.entity.lgsq.PortalIndependentText;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.PortalIndependentTextMapper;
import com.cdyoue.oddJobs.service.PortalIndependentTextService;
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

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by liaoyoule on 2017/5/11.
 */
@Service
public class PortalIndependentTextServiceImpl implements PortalIndependentTextService {
    @Autowired
    private PortalIndependentTextResponsitory portalIndependentTextResponsitory;
    @Autowired
    private PortalIndependentTextMapper portalIndependentTextMapper;
    @Autowired
    private SpecificationHelper specificationHelper;

    @Override
    public Integer save(Integer type, RequestIndependent publish) {
        PortalIndependentText ppe = portalIndependentTextMapper.requestPublishTOPortalPublishEntity(publish);
        ppe.setCreateTime(new Timestamp(System.currentTimeMillis()));
        UserEntity ue = new UserEntity();
        ue.setId(SecurityUtils.getCurrentUserLogin().getId());
        ppe.setCreator(ue);
        ppe.setType(type);
        ppe.setViewNum(0);
        portalIndependentTextResponsitory.save(ppe);
        return ppe.getId();
    }

    @Override
    public void delete(Integer id) {
        portalIndependentTextResponsitory.delete(id);
    }

    @Override
    public IndependentSumary findById(Integer id) {
        PortalIndependentText pit = portalIndependentTextResponsitory.findOne(id);
        if (pit == null) {
            throw new NotFoundEntityException();
        }
        return portalIndependentTextMapper.portalIndependentTextTOPublishSumary(pit);
    }

    @Override
    public PageInfo<IndependentMine> getIndependents(Integer type, String q, PageRequest pr) {
        List<QueryRequest> queryRequest = new ArrayList<>();
        QueryRequest ql = new QueryRequest();
        ql.setF("title");
        ql.setO(Operator.LIKE);
        ql.setV(q);
        queryRequest.add(ql);


        QueryRequest qe = new QueryRequest();
        qe.setF("type");
        qe.setO(Operator.EQ);
        qe.setV(type+"");
        qe.setT(DataTypeConstant.INTEGER);
        queryRequest.add(qe);

        Specification specifica = specificationHelper.getSpecifica(PortalIndependentText.class, queryRequest);
        Page<PortalIndependentText> pitPage = portalIndependentTextResponsitory.findAll(specifica, pr);
        List<PortalIndependentText> pits = pitPage.getContent();
        if (pits.size() == 0) {
            throw new NotFoundEntityException();
        }
        List<IndependentMine> pms = pits.stream().map(pit -> portalIndependentTextMapper.portalIndependentTextTOPublishMine(pit)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(pms, pr, pitPage.getTotalElements()));
    }

    @Override
    @Transactional
    public Integer updateIndependent(Integer id, RequestIndependent publish) {
        PortalIndependentText pit = portalIndependentTextResponsitory.findOne(id);
        pit.setTitle(publish.getTitle());
        pit.setCoverImg(publish.getCoverImg());
        pit.setBrief(publish.getBrief());
        pit.setIntro(publish.getIntro());
        UserEntity ue = new UserEntity();
        ue.setId(SecurityUtils.getCurrentUserLogin().getId());
        pit.setUpdator(ue);
        pit.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        PortalIndependentText pitAF = portalIndependentTextResponsitory.save(pit);
        return pitAF.getId();
    }

    @Override
    public Integer addViewNum(Integer id) {
        PortalIndependentText pit = portalIndependentTextResponsitory.findOne(id);
        pit.setViewNum(pit.getViewNum()+1);
        portalIndependentTextResponsitory.save(pit);
        return pit.getId();
    }
}
