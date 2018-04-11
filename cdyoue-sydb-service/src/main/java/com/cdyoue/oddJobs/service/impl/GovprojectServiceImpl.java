package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.GovprojectResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.PortalAttachmentResponsitory;
import com.cdyoue.oddJobs.dto.ggfw.Govproject;
import com.cdyoue.oddJobs.dto.ggfw.GovprojectSummary;
import com.cdyoue.oddJobs.entity.lgsq.PortalAttachmentEntity;
import com.cdyoue.oddJobs.entity.lgsq.PortalGovprojectReportEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.GovprojectMapper;
import com.cdyoue.oddJobs.service.GovprojectService;
import com.cdyoue.oddJobs.service.support.ServiceSupport;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.page.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by admin on 2017/5/22.
 */

@Service
public class GovprojectServiceImpl extends ServiceSupport<PortalGovprojectReportEntity> implements GovprojectService {

    @Autowired
    private GovprojectResponsitory govprojectResponsitory;

    @Autowired
    private GovprojectMapper govprojectMapper;

    @Autowired
    private PortalAttachmentResponsitory portalAttachmentResponsitory;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Class getJpaRepositoryClazz() {
        return GovprojectResponsitory.class;
    }

    @Override
    public PageInfo<GovprojectSummary> findGovprojectByKeyWord(Pageable requestPage, String q, Integer timeFilter) {
        //有时间条件
        if (timeFilter != null){
            //创建Date对象
            Date today = new Date();
            //创建基于当前时间的日历对象
            Calendar c = Calendar.getInstance();
            c.setTime(today);
            //距离今天，一个月内数据
            if(timeFilter == 2){
                c.add(Calendar.MONTH, -1);
            }
            //距离今天，一周内的数据
            if(timeFilter == 1){
                c.add(Calendar.DATE, -7);
            }
            java.util.Date startDate = c.getTime();
            Page<PortalGovprojectReportEntity> rpPage = null;
            rpPage = StringUtils.isEmpty(q) ? govprojectResponsitory.findWithTimeFilter(startDate, today, requestPage) :  govprojectResponsitory.findWithTimeFilterAndKeyword(startDate, today, q, requestPage);
            List<GovprojectSummary> govprojects= rpPage.getContent().stream().map(p -> govprojectMapper.projectReportEntityToGovprojectSummary(p)).collect(Collectors.toList());
            return new PageInfo<>(new PageImpl(govprojects, requestPage, rpPage.getTotalElements()));

        }
        //没有时间条件
        else {
            Page<PortalGovprojectReportEntity> rpPage = super.findByStrLike("title", q, requestPage);
            List<GovprojectSummary> govprojects= rpPage.getContent().stream().map(p -> govprojectMapper.projectReportEntityToGovprojectSummary(p)).collect(Collectors.toList());
            return new PageInfo<>(new PageImpl(govprojects, requestPage, rpPage.getTotalElements()));
        }

   }

    @Override
    public Integer createGovproject(Govproject govproject) {
            PortalGovprojectReportEntity ppr = govprojectMapper.govprojectToPortalProjectReportEntity(govproject);
            Integer userId = SecurityUtils.getCurrentUserLogin().getId();
            UserEntity u = new UserEntity();
            u.setId(userId);
            ppr.setCreateBy(u);
            ppr.setPublishTime(new Timestamp(System.currentTimeMillis()));
            Integer referId =  govprojectResponsitory.save(ppr).getId();

            //更新附件表中对应的信息
            if (!StringUtils.isEmpty(govproject.getGovprojectSummary().getAttachUrl())) {
                String url = govproject.getGovprojectSummary().getAttachUrl();
                PortalAttachmentEntity pae = portalAttachmentResponsitory.findByUrl(url);
                pae.setReferId(referId);
                pae.setSourceType((byte) 3);
                portalAttachmentResponsitory.saveAndFlush(pae);
            }
            return referId;
    }

    @Override
    public Govproject getGovprojectById(Integer id) {
        try {
            return govprojectMapper.ProjectReportEntityToGovproject(govprojectResponsitory.getOne(id));
        }catch(Exception e){
            throw new NotFoundEntityException("数据不存在");
        }
    }

    @Override
    public PortalGovprojectReportEntity getGovprojectEntityById(Integer id){
        try {
            return govprojectResponsitory.getOne(id);
        }catch (Exception e){
            throw new NotFoundEntityException("数据不存在");
        }
    }

    @Override
    public void deleteGovProject(Integer id) {
        govprojectResponsitory.delete(id);
    }

    @Override
    @Transactional
    public void updateGovproject(Integer id, Govproject govproject) {
        PortalGovprojectReportEntity pgre = null;
        try {
            pgre = getGovprojectEntityById(id);
        }catch (Exception e){
            throw new NotFoundEntityException("数据不存在");
        }
        pgre.setTitle(govproject.getGovprojectSummary().getTitle());
        pgre.setSourceFrom(govproject.getInforSource());
        pgre.setCode(govproject.getFileNO());
        pgre.setIntroduction(govproject.getIntroduction());
        pgre.setDuration(govproject.getGovprojectSummary().getDuration());
        govprojectResponsitory.saveAndFlush(pgre);

        //如果不上传附件，则不执行附件更新
        if (!StringUtils.isEmpty(govproject.getGovprojectSummary().getAttachUrl())) {
            try {
                String url = govproject.getGovprojectSummary().getAttachUrl();
                PortalAttachmentEntity pae = portalAttachmentResponsitory.findByUrl(url);
                pae.setReferId(pgre.getId());
                pae.setSourceType((byte) 3);
                portalAttachmentResponsitory.saveAndFlush(pae);
                //如果传来新的附件，则在更新前先删除旧的
                portalAttachmentResponsitory.deleteAttach(pgre.getId(), new Byte("3"), url);
            } catch (Exception e) {
                e.printStackTrace();
                throw new NotFoundEntityException("该附件url对应的附件不存在");
            }
        }
    }

    protected UserEntity findUsernameById(Integer id){
        return govprojectResponsitory.findUsernameById(id);
    }
}
