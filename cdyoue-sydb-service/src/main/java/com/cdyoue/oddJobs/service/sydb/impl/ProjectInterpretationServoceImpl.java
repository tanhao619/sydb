package com.cdyoue.oddJobs.service.sydb.impl;

import com.cdyoue.oddJobs.dao.syData.ProjectInterpretationResponsitory;
import com.cdyoue.oddJobs.dto.zlcx.ProjectInterpretationBase;
import com.cdyoue.oddJobs.dto.zlcx.ProjectInterpretationDetail;
import com.cdyoue.oddJobs.dto.zlcx.ProjectInterpretationSummary;
import com.cdyoue.oddJobs.entity.syData.ProjectInterpretationEntity;
import com.cdyoue.oddJobs.mapper.sydb.ProjectInterpretationMapper;
import com.cdyoue.oddJobs.service.support.ServiceSupport;
import com.cdyoue.oddJobs.service.sydb.ProjectInterpretationService;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sky on 2017/9/19.
 */
@Service
public class ProjectInterpretationServoceImpl extends ServiceSupport<ProjectInterpretationEntity> implements ProjectInterpretationService {
   @Autowired
    ProjectInterpretationResponsitory projectInterpretationResponsitory;
   @Autowired
    ProjectInterpretationMapper projectInterpretationMapper;
   @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public ProjectInterpretationDetail getProjectInterpretationById(Integer id) {
        ProjectInterpretationEntity projectInterpretationEntity=projectInterpretationResponsitory.findOne(id);
        return projectInterpretationMapper.projectInterpretationEntityToProjectInterpretationDetail(projectInterpretationEntity);
    }

    @Override
    public boolean getProjectInterpretationCheck(Integer id) {
        if(projectInterpretationResponsitory.findOne(id)==null){
            return false;
        }else {
            return true;
        }

    }

    @Override
    public PageInfo<ProjectInterpretationSummary> findList(PageRequest pr, String q) {
        Page<ProjectInterpretationEntity> rpPage = super.findByOneLike("title",q,pr);
        List<ProjectInterpretationSummary> projectInterpretationSummaryList = rpPage.getContent().stream().map(p -> projectInterpretationMapper.projectInterpretationEntityToProjectInterpretationSummary(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(projectInterpretationSummaryList, pr, rpPage.getTotalElements()));
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        String s = Arrays.toString(ids);
        s=s.substring(1, s.length()-1);
        String sql="delete from sy_project_interpretation where id in ("+s+")";
        jdbcTemplate.batchUpdate(sql);
    }

    @Override
    public Integer createProjectInterpretation(ProjectInterpretationBase projectInterpretationBase) {
        ProjectInterpretationEntity projectInterpretationEntity=projectInterpretationMapper.projectInterpretationBaseToProjectInterpretationEntity(projectInterpretationBase);
        return projectInterpretationResponsitory.save(projectInterpretationEntity).getId();
    }

    @Override
    public void updateProjectInterpretation(Integer id, ProjectInterpretationBase projectInterpretationBase) {
        ProjectInterpretationEntity projectInterpretationEntity=projectInterpretationResponsitory.findOne(id);
        BeanUtils.copyProperties(projectInterpretationBase,projectInterpretationEntity);
        projectInterpretationEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        projectInterpretationEntity.setPublishTime(projectInterpretationEntity.getUpdateTime());
        projectInterpretationResponsitory.saveAndFlush(projectInterpretationEntity);
    }

    @Override
    public void updateViewCount(Integer id) {
        ProjectInterpretationEntity projectInterpretationEntity=projectInterpretationResponsitory.findOne(id);
        projectInterpretationEntity.setViewCount(projectInterpretationEntity.getViewCount()+1);
        projectInterpretationResponsitory.saveAndFlush(projectInterpretationEntity);
    }

    @Override
    public Class getJpaRepositoryClazz() {
        return ProjectInterpretationResponsitory.class;
    }
}
