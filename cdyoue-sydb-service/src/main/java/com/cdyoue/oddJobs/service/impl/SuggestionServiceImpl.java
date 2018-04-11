package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.SuggestionResponsitory;
import com.cdyoue.oddJobs.dto.other.SuggestionDetail;
import com.cdyoue.oddJobs.dto.other.SuggestionRequest;
import com.cdyoue.oddJobs.dto.other.SuggestionSummary;
import com.cdyoue.oddJobs.entity.lgsq.SuggestionEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.SuggestionMapper;
import com.cdyoue.oddJobs.service.SuggestionService;
import com.cdyoue.oddJobs.service.support.ServiceSupport;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tr on 2017/6/12.
 */

@Service
public class SuggestionServiceImpl  extends ServiceSupport<SuggestionEntity> implements SuggestionService {
    @Autowired
    private SuggestionResponsitory suggestionResponsitory;

    @Autowired
    private SuggestionMapper suggesionMapper;

    @Override
    public Class getJpaRepositoryClazz() {
        return SuggestionResponsitory.class;
    }

    @Override
    public Integer createSuggestion(Integer userId, SuggestionRequest suggestionRequest) {
        return suggestionResponsitory.save(suggesionMapper.suggestionRequestToEntity(userId, suggestionRequest)).getId();
    }

    @Override
    public void deleteSuggestion(Integer id) {
        suggestionResponsitory.delete(id);
    }

    @Override
    public SuggestionDetail getSuggestionById(Integer id) {
        SuggestionEntity suggestionEntity = suggestionResponsitory.findOne(id);
        if (suggestionEntity == null) throw new NotFoundEntityException("该id对应的数据没找到");
        return suggesionMapper.entityToDetail(suggestionEntity);
    }

    @Override
    public PageInfo<SuggestionSummary> getSuggestions(String q, PageRequest pageRequest) {
            Page<SuggestionEntity> rpPage =  super.findByStrLike("content", q, pageRequest);
            List<SuggestionSummary> responseDetails =  rpPage.getContent().stream().map(p -> suggesionMapper.toSuggestionSummary(p)).collect(Collectors.toList());
            return new PageInfo<>(new PageImpl(responseDetails, pageRequest, rpPage.getTotalElements()));
    }
}
