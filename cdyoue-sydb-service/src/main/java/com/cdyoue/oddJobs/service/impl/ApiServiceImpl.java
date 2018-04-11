package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.PortalApiRepository;
import com.cdyoue.oddJobs.dto.other.InterfaceApiRequest;
import com.cdyoue.oddJobs.dto.other.PortalApiSumary;
import com.cdyoue.oddJobs.entity.lgsq.PortalApiEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.ApiMapper;
import com.cdyoue.oddJobs.service.ApiService;
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
 * Created by liaoyoule on 2017/6/16.
 */
@Service
public class ApiServiceImpl implements ApiService {
    @Autowired
    private PortalApiRepository portalApiRepository;
    @Autowired
    private SpecificationHelper specificationHelper;
    @Autowired
    private ApiMapper apiMapper;
    @Override
    public PageInfo<PortalApiSumary> getApis(String q, PageRequest pr) {
        List<QueryRequest> qrs = new ArrayList<>();
        QueryRequest qre = new QueryRequest();
        qre.setO(Operator.LIKE);
        qre.setF("name");
        qre.setV(q);
        qrs.add(qre);

        Specification specifica = specificationHelper.getSpecifica(PortalApiEntity.class, qrs);
        Page<PortalApiEntity> paePage = portalApiRepository.findAll(specifica, pr);

        List<PortalApiEntity> paes = paePage.getContent();

        if(paes.size() == 0){
            throw new NotFoundEntityException();
        }

        List<PortalApiSumary>  pass =   paes.stream().map(portalApiEntity -> apiMapper.portalApiEntityToPortalApiSumary(portalApiEntity))
                .collect(Collectors.toList());


        return new PageInfo<>(new PageImpl<PortalApiSumary>(pass, pr, paePage.getTotalElements()));
    }

    @Override
    public void delete(Integer id) {
        portalApiRepository.delete(id);
    }

    @Override
    public PortalApiSumary getApis(Integer id) {
        PortalApiEntity pae = portalApiRepository.findOne(id);
        if(pae ==null){
            throw new NotFoundEntityException();
        }


        return apiMapper.portalApiEntityToPortalApiSumary(pae);
    }

    @Override
    public void updateApi(Integer id, InterfaceApiRequest interfaceApiRequest) {
        PortalApiEntity pae = portalApiRepository.findOne(id);
        if(pae ==null){
            throw new NotFoundEntityException();
        }

        pae.setAuth(interfaceApiRequest.getAuth());
        pae.setDescript(interfaceApiRequest.getDescript());
        pae.setMethod(interfaceApiRequest.getMethod());
        pae.setName(interfaceApiRequest.getName());

        pae.setParams(interfaceApiRequest.getParams());
        pae.setType(interfaceApiRequest.getType());
        pae.setUrl(interfaceApiRequest.getUrl());
        pae.setVersionCode(interfaceApiRequest.getVersionCode());

        pae.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        pae.setUpdateBy(SecurityUtils.getCurrentUserLogin().getId());
        pae.setId(id);
        portalApiRepository.save(pae);

    }

    @Override
    public void createApi(InterfaceApiRequest interfaceApiRequest) {
        PortalApiEntity pae = new PortalApiEntity();
        pae.setAuth(interfaceApiRequest.getAuth());
        pae.setDescript(interfaceApiRequest.getDescript());
        pae.setMethod(interfaceApiRequest.getMethod());
        pae.setName(interfaceApiRequest.getName());

        pae.setParams(interfaceApiRequest.getParams());
        pae.setType(interfaceApiRequest.getType());
        pae.setUrl(interfaceApiRequest.getUrl());
        pae.setVersionCode(interfaceApiRequest.getVersionCode());

        pae.setCreateTime(new Timestamp(System.currentTimeMillis()));
        UserEntity ue = new UserEntity();
        ue.setId(SecurityUtils.getCurrentUserLogin().getId());
        pae.setCreateBy(ue);
        portalApiRepository.save(pae);
    }
}
