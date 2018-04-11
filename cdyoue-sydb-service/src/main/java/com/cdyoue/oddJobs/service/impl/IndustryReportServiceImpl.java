package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.PortalIndustryReportsResponsitory;
import com.cdyoue.oddJobs.dto.zscq.IndustryReportDetail;
import com.cdyoue.oddJobs.dto.zscq.IndustryReportSummary;
import com.cdyoue.oddJobs.entity.lgsq.PortalIndustryReportsEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.IndustryReportMapper;
import com.cdyoue.oddJobs.service.IndustryReportService;
import com.cdyoue.oddJobs.service.support.ServiceSupport;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tr on 2017/5/23.
 */

@Service
public class IndustryReportServiceImpl extends ServiceSupport<PortalIndustryReportsEntity> implements IndustryReportService{

    @Autowired
    private PortalIndustryReportsResponsitory portalIndustryReportsResponsitory;

    @Autowired
    private IndustryReportMapper industryReportMapper;

    @Override
    public Class getJpaRepositoryClazz() {return PortalIndustryReportsResponsitory.class;}

    @Override
    public Integer createReport(IndustryReportDetail industryReportDetail) {
        PortalIndustryReportsEntity p = new PortalIndustryReportsEntity();
        p.setName(industryReportDetail.getName());
        p.setCoverImg(industryReportDetail.getCoverImgUrl());
        p.setInfo(industryReportDetail.getIntroduction());
        p.setIntroduction(industryReportDetail.getContent());

        Integer createBy = SecurityUtils.getCurrentUserLogin().getId();
        UserEntity u = new UserEntity(); u.setId(createBy);
        p.setCreateBy(u);

        p.setPublishTime(new Timestamp(System.currentTimeMillis()));
        return portalIndustryReportsResponsitory.save(p).getId();
    }

    @Override
    public Integer updateReport(Integer id, IndustryReportDetail industryReportDetail) {
       PortalIndustryReportsEntity p = findOne(id);
       p.setId(id);
       Integer updateBy = SecurityUtils.getCurrentUserLogin().getId();
       UserEntity u = new UserEntity();
       u.setId(updateBy);
       p.setUpdateBy(u);
       p.setUpdateTime(new Timestamp(System.currentTimeMillis()));
       p.setName(industryReportDetail.getName());
       p.setCoverImg(industryReportDetail.getCoverImgUrl());
       p.setIntroduction(industryReportDetail.getContent());
       p.setInfo(industryReportDetail.getIntroduction());
       return portalIndustryReportsResponsitory.saveAndFlush(p).getId();
    }

    @Override
    public PortalIndustryReportsEntity findOne(Integer id){
        PortalIndustryReportsEntity ppe = null;
        ppe = portalIndustryReportsResponsitory.findOne(id);
        if (ppe == null) throw new NotFoundEntityException("数据不存在");
        return ppe;
    }

    @Override
    public PageInfo<IndustryReportSummary> findByKeyWord(Pageable requestPage, String q) {
        Page<PortalIndustryReportsEntity> rpPage= super.findByStrLike("name", q, requestPage);
        List<IndustryReportSummary> industryReportSummaries =  rpPage.getContent().stream().map(p -> industryReportMapper.industryReportEntityToSummary(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(industryReportSummaries, requestPage, rpPage.getTotalElements()));
    }

    @Override
    public IndustryReportDetail getReportById(Integer id) {
            PortalIndustryReportsEntity p = findOne(id);
            IndustryReportDetail ird = industryReportMapper.industryReportEntityToDetail(p);
            return ird;
    }

    @Override
    public void deleteReport(Integer id) {
         portalIndustryReportsResponsitory.delete(id);
    }

}
