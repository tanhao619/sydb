package com.cdyoue.oddJobs.service.sydb.impl;

import com.cdyoue.oddJobs.dao.syData.WebsiteRepository;
import com.cdyoue.oddJobs.dto.zlcy.WebsiteRequest;
import com.cdyoue.oddJobs.dto.zlcy.WebsiteSummary;
import com.cdyoue.oddJobs.entity.syData.SyWebsiteEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.sydb.WebsiteMapper;
import com.cdyoue.oddJobs.service.sydb.WebsiteService;
import com.cdyoue.oddJobs.spec.Operator;
import com.cdyoue.oddJobs.spec.QueryRequest;
import com.cdyoue.oddJobs.spec.SpecificationHelper;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dengshaojun on 2017/10/10.
 */
@Service
@Transactional
public class WebsiteServiceImpl implements WebsiteService {

    @Autowired
    private WebsiteRepository websiteRepository;

    @Autowired
    private SpecificationHelper specificationHelper;

    @Autowired
    private WebsiteMapper websiteMapper;

    @Override
    public PageInfo<WebsiteSummary> findWebsites(String q, Pageable pageRequest) {
        List<QueryRequest> queryRequest = new ArrayList<>();
        QueryRequest qr = new QueryRequest();
        qr.setF("name");
        qr.setO(Operator.LIKE);
        qr.setV(q);
        queryRequest.add(qr);
        Specification specifica = specificationHelper.getSpecifica(SyWebsiteEntity.class, queryRequest);
        Page<SyWebsiteEntity> page = websiteRepository.findAll(specifica, pageRequest);
        if (page.getContent().size() == 0) {
            throw new NotFoundEntityException("没有数据");
        }
        List<WebsiteSummary> list = page.getContent().stream().map(p -> websiteMapper.entityToSummary(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl<WebsiteSummary>(list, pageRequest, page.getTotalElements()));
    }

    @Override
    public void saveWebsite(WebsiteRequest request) {
        SyWebsiteEntity entity = websiteMapper.requestToEntity(request);
        websiteRepository.save(entity);
    }

    @Override
    public void updateWebsite(Integer id, WebsiteRequest request) {
        SyWebsiteEntity entity = websiteRepository.findOne(id);
        if (entity == null) {
            throw new NotFoundEntityException("操作对象不存在");
        }
        entity = websiteMapper.requestToEntity(request, entity);
        websiteRepository.save(entity);
    }

    @Override
    public void deleteWebsite(Integer[] ids) {
        List<SyWebsiteEntity> list = websiteRepository.findAll(Arrays.asList(ids));
        if (list.size() == 0) {
            throw new NotFoundEntityException("操作对象不存在");
        }
        websiteRepository.delete(list);
    }

    @Override
    public List<WebsiteSummary> findWebsite(Sort sort) {
        List<SyWebsiteEntity> list = websiteRepository.findAll(sort);
        if (list.size() == 0) {
            throw new NotFoundEntityException("没有数据");
        }
        List<WebsiteSummary> list1 = list.stream().map(p -> websiteMapper.entityToSummary(p)).collect(Collectors.toList());
        return list1;
    }
}
