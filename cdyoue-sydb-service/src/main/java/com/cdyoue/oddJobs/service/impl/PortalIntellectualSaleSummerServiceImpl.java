package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.PortalIntellectualSaleSummerResponsitory;
import com.cdyoue.oddJobs.dto.zscq.IntellectualSaleSummary;
import com.cdyoue.oddJobs.entity.lgsq.PortalIntellectualSaleSummerEntity;
import com.cdyoue.oddJobs.mapper.IntellectualSaleMapper;
import com.cdyoue.oddJobs.service.PortalIntellectualSaleSummerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2017/5/17.
 */
@Service
@Transactional
public class PortalIntellectualSaleSummerServiceImpl implements PortalIntellectualSaleSummerService {
    @Autowired
    private PortalIntellectualSaleSummerResponsitory summerResponsitory;
    @Autowired
    private IntellectualSaleMapper intellectualSaleMapper;
    @Override
    public List<IntellectualSaleSummary> getIntellectualSalesSummer() {

        List<PortalIntellectualSaleSummerEntity> tePage = summerResponsitory.getIntellectualSalesSummer();

        List<IntellectualSaleSummary> saleSummaries = tePage.stream().map(p -> intellectualSaleMapper.SummerEntityToSummary(p)).collect(Collectors.toList());
        return saleSummaries;
    }
}
