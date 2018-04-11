package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.CommunityRepository;
import com.cdyoue.oddJobs.dto.ggfw.CommunitySummary;
import com.cdyoue.oddJobs.mapper.CommunityMapper;
import com.cdyoue.oddJobs.service.CommunityService;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luanyu on 2017/5/22.
 */
@Service
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    CommunityRepository communityRepository;
    @Autowired
    CommunityMapper mapper;

    @Override
    public PageInfo<CommunitySummary> findCommunityList(PageRequest pr) {
        Object[] entityPage = communityRepository.findCommuities( pr.getPageNumber()*pr.getPageSize(),pr.getPageSize());
        List<CommunitySummary> resultset = new ArrayList<>();
        for(int i=0;i<entityPage.length;i++){
            resultset.add(mapper.entityToDto(entityPage[i]));
        }
        Integer counter = communityRepository.findCount();
        if(counter==null)counter=0;
        PageInfo<CommunitySummary> pageInfo=new PageInfo<>(new PageImpl(resultset, pr, counter));
        return pageInfo;
    }
}
