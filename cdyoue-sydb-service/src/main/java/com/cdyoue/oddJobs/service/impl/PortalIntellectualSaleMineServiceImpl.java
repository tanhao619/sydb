package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.PortalIntellectualSaleMineResponsitory;
import com.cdyoue.oddJobs.dto.zscq.IntellectualSaleMine;
import com.cdyoue.oddJobs.entity.lgsq.PortalIntellectualSaleMineEntity;
import com.cdyoue.oddJobs.mapper.IntellectualSaleMapper;
import com.cdyoue.oddJobs.service.PortalIntellectualSaleMineService;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2017/5/18.
 */
@Service
@Transactional
public class PortalIntellectualSaleMineServiceImpl implements PortalIntellectualSaleMineService {
    @Autowired
    private PortalIntellectualSaleMineResponsitory mineResponsitory;
    @Autowired
    private IntellectualSaleMapper intellectualSaleMapper;
    @Override
    public PageInfo<IntellectualSaleMine> getMyIntellectualSales(Pageable requestPage) {
        Integer uid = SecurityUtils.getCurrentUserLogin().getId();
        Page<PortalIntellectualSaleMineEntity> tePage = mineResponsitory.getMyIntellectualSales(uid,requestPage);
        List<IntellectualSaleMine> saleSummaries = tePage.getContent().stream().map(p -> intellectualSaleMapper.MineEntityToMine(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(saleSummaries, requestPage, tePage.getTotalElements()));
//        return null;
    }
}
