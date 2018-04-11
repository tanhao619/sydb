package com.cdyoue.oddJobs.service.sydb.impl;

import com.cdyoue.oddJobs.dao.syData.ProjectLectureResponsitory;
import com.cdyoue.oddJobs.dto.zlcx.ProjectLectureCreate;
import com.cdyoue.oddJobs.dto.zlcx.ProjectLectureDetail;
import com.cdyoue.oddJobs.dto.zlcx.ProjectLectureSummary;
import com.cdyoue.oddJobs.entity.syData.ProjectLectureEntity;
import com.cdyoue.oddJobs.mapper.sydb.ProjectLectureMapper;
import com.cdyoue.oddJobs.service.support.ServiceSupport;
import com.cdyoue.oddJobs.service.sydb.ProjectLectureService;
import com.cdyoue.spring.page.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sky on 2017/9/19.
 */
@Service
public class ProjectLectureServiceImpl extends ServiceSupport<ProjectLectureEntity> implements ProjectLectureService {
    @Autowired
    ProjectLectureResponsitory projectLectureResponsitory;
    @Autowired
    ProjectLectureMapper projectLectureMapper;
    @Autowired
    JdbcTemplate jdbcTemplate;
    private static final String TAG = "ProjectLectureServiceImpl";
    private static final Logger log = LoggerFactory.getLogger(ProjectLectureServiceImpl.class);

    @Override
    public ProjectLectureDetail getProjectLectureById(Integer id) {
        ProjectLectureEntity projectLectureEntity=projectLectureResponsitory.findOne(id);
        return projectLectureMapper.projectLectureEntityToProjectLectureDetail(projectLectureEntity);
    }

    @Override
    public boolean getProjectLectureCheck(Integer id) {
        if(projectLectureResponsitory.findOne(id)==null){
            return false;
        }else {
            return true;
        }

    }

    @Override
    public PageInfo<ProjectLectureSummary> findList(PageRequest pr, String q) {
        Page<ProjectLectureEntity> rpPage = super.findByOneLike("name",q,pr);
        List<ProjectLectureSummary> projectLectureSummaryList = rpPage.getContent().stream().map(p -> projectLectureMapper.projectLectureEntityToProjectLectureSummary(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(projectLectureSummaryList, pr, rpPage.getTotalElements()));
    }

    @Override
    public Integer createProjectLecture(ProjectLectureCreate projectLectureCreate) {
        ProjectLectureEntity projectLectureEntity=projectLectureMapper.projectLectureCreateToProjectLectureEntity(projectLectureCreate);
        return projectLectureResponsitory.save(projectLectureEntity).getId();
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        String s= Arrays.toString(ids);
        s=s.substring(1,s.length()-1);
        String sql="delete from sy_project_lecture where id in ("+s+")";
        jdbcTemplate.batchUpdate(sql);
    }

    @Override
    public void updateProjectLecture(Integer id, ProjectLectureCreate projectLectureCreate){
        ProjectLectureEntity projectLectureEntity=projectLectureResponsitory.findOne(id);
        BeanUtils.copyProperties(projectLectureCreate,projectLectureEntity);
        try {
            projectLectureEntity.setTime(new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(projectLectureCreate.getTime()).getTime()));
        } catch (ParseException e) {
            log.error(TAG, e);
        }
        projectLectureEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        projectLectureEntity.setPublishTime(projectLectureEntity.getUpdateTime());
        projectLectureResponsitory.saveAndFlush(projectLectureEntity);
    }

    @Override
    public ProjectLectureSummary getTopLecture() {
        String sql="select * from sy_project_lecture where top=1 order by updateTime desc limit 1";
        ProjectLectureEntity projectLectureEntity=jdbcTemplate.queryForObject(sql,new Object[]{}, new BeanPropertyRowMapper<ProjectLectureEntity>(ProjectLectureEntity.class));
        ProjectLectureSummary projectLectureSummary=projectLectureMapper.projectLectureEntityToProjectLectureSummary(projectLectureEntity);
        return projectLectureSummary;
    }

    @Override
    public void updateProjectLectureViewCount(Integer id) {
        ProjectLectureEntity projectLectureEntity=projectLectureResponsitory.findOne(id);
        projectLectureEntity.setViewCount(projectLectureEntity.getViewCount()+1);
        projectLectureResponsitory.saveAndFlush(projectLectureEntity);
    }

    @Override
    public void topLecture(Integer id,String topImage) {
        ProjectLectureEntity projectLectureEntity=projectLectureResponsitory.findOne(id);
        int top=projectLectureEntity.getTop();
        if(top==0){
            //置顶
            if(topImage!=null && topImage!=""){
                projectLectureEntity.setTopImage(topImage);
            }
            projectLectureEntity.setTop(1);
        }else {
            //取消置顶
            projectLectureEntity.setTop(0);
        }
        projectLectureEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        projectLectureEntity.setPublishTime(projectLectureEntity.getUpdateTime());
        projectLectureResponsitory.saveAndFlush(projectLectureEntity);
    }

    @Override
    public Class getJpaRepositoryClazz() {
        return ProjectLectureResponsitory.class;
    }
}
