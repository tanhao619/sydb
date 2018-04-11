package com.cdyoue.oddJobs.service.sydb.impl;

import com.cdyoue.oddJobs.dao.syData.ProjectLecturePeopleResponsitory;
import com.cdyoue.oddJobs.dao.syData.ProjectResponsitory;
import com.cdyoue.oddJobs.dto.zlcx.*;
import com.cdyoue.oddJobs.entity.syData.ProjectEntity;
import com.cdyoue.oddJobs.entity.syData.SyDeclarationProjectMessageEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.sydb.DeclarationProjectMessageMapper;
import com.cdyoue.oddJobs.mapper.sydb.ProjectMapper;
import com.cdyoue.oddJobs.service.sydb.DeclarationProjectMessageService;
import com.cdyoue.oddJobs.service.sydb.ProjectService;
import com.cdyoue.oddJobs.service.support.ServiceSupport;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.page.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by sky on 2017/9/18.
 */
@Service
public class ProjectServiceImpl extends ServiceSupport<ProjectEntity> implements ProjectService {
    @Autowired
    ProjectResponsitory projectResponsitory;
    @Autowired
    ProjectMapper projectMapper;
    @Autowired
    ProjectLecturePeopleResponsitory projectLecturePeopleResponsitory;
    @Autowired
    JdbcTemplate jdbcTemplate;
    private static final String TAG = "ProjectServiceImpl";
    private static final Logger log = LoggerFactory.getLogger(ProjectServiceImpl.class);

    @Override
    public ProjectDetail getProjectById(Integer id) {
        ProjectEntity projectEntity=projectResponsitory.findOne(id);
        return projectMapper.ProjectEntityToProjectDetail(projectEntity);
    }

    @Override
    public boolean getProjectCheck(Integer id) {
        if(projectResponsitory.findOne(id)==null){
            return false;
        }else {
          return true;
        }
    }

    @Override
    public PageInfo<ProjectSummary> findList(PageRequest pr, String q,String time) {
        Page<ProjectEntity> rpPage=null;
        if(q==null){
            q="";
        }
        if(time!=null){
            if(time.equals("latestWeek")){
                String sql="select *  from sy_project where TIMESTAMPDIFF(day,updateTime,SYSDATE()) < 7 AND name LIKE \"%"+q+"%\" order by updateTime desc  limit "+pr.getPageNumber()*pr.getPageSize()+","+pr.getPageSize()+"";
                String sql2="SELECT COUNT(1) FROM sy_project where TIMESTAMPDIFF(day,updateTime,SYSDATE()) < 7 AND name LIKE \"%"+q+"%\"";
                Integer count=jdbcTemplate.queryForObject(sql2,new Object[]{},Integer.class);
                List<ProjectEntity> projectEntityList=jdbcTemplate.query(sql,new BeanPropertyRowMapper(ProjectEntity.class));
                List<ProjectSummary> projectSummaryList=projectEntityList.stream().map(p -> projectMapper.ProjectEntityToProjectSummary(p)).collect(Collectors.toList());
                return new PageInfo<>(new PageImpl(projectSummaryList, pr,count));
            }else if(time.equals("latestMonth")){
                String sql="select *  from sy_project where TIMESTAMPDIFF(day,updateTime,SYSDATE()) < 30 AND name LIKE \"%"+q+"%\" order by updateTime desc  limit "+pr.getPageNumber()*pr.getPageSize()+","+pr.getPageSize()+"";
                String sql2="SELECT COUNT(1) FROM sy_project where TIMESTAMPDIFF(day,updateTime,SYSDATE()) < 30 AND name LIKE \"%"+q+"%\"";
                Integer count=jdbcTemplate.queryForObject(sql2,new Object[]{},Integer.class);
                List<ProjectEntity> projectEntityList=jdbcTemplate.query(sql,new BeanPropertyRowMapper(ProjectEntity.class));
                List<ProjectSummary> projectSummaryList=projectEntityList.stream().map(p -> projectMapper.ProjectEntityToProjectSummary(p)).collect(Collectors.toList());
                return new PageInfo<>(new PageImpl(projectSummaryList, pr,count));
            }
        } else {
             rpPage = super.findByOneLike("name",q,pr);
        }

        List<ProjectSummary> projectSummaryList = rpPage.getContent().stream().map(p -> projectMapper.ProjectEntityToProjectSummary(p)).collect(Collectors.toList());

        System.out.println(rpPage.getTotalElements());
        return new PageInfo<>(new PageImpl(projectSummaryList, pr, rpPage.getTotalElements()));
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        List<ProjectEntity> projectEntityList=projectResponsitory.findAll(Arrays.asList(ids));
        projectResponsitory.delete(projectEntityList);
    }

    @Override
    public Integer savePeople(DeclarationPeopleSummary declarationPeopleSummary) {
        SyDeclarationProjectMessageEntity syDeclarationProjectMessageEntity=new SyDeclarationProjectMessageEntity();
        syDeclarationProjectMessageEntity.setEnterpriseUserId(SecurityUtils.getCurrentUserLogin().getId());
        syDeclarationProjectMessageEntity.setProjectId(declarationPeopleSummary.getProjectId());
        syDeclarationProjectMessageEntity.setAttachmentUrl(declarationPeopleSummary.getAttachmentUrl());
        syDeclarationProjectMessageEntity.setContactPeople(declarationPeopleSummary.getName());
        syDeclarationProjectMessageEntity.setContactNumber(declarationPeopleSummary.getPhoneNumber());
        syDeclarationProjectMessageEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));//创建时间
        syDeclarationProjectMessageEntity.setStatus(new Byte("0"));//审核状态，默认0，未审核
       return projectLecturePeopleResponsitory.save(syDeclarationProjectMessageEntity).getId();
    }

    @Override
    public String getNameById(Integer id) {
       String name=projectResponsitory.findProjectName(id);
        return name;
    }

    @Override
    public Integer createProject(ProjectCreate projectCreate) {
        ProjectEntity projectEntity=projectMapper.projectCreateToProject(projectCreate);
        return  projectResponsitory.save(projectEntity).getId();
    }

    @Override
    public void updateProject(Integer id, ProjectCreate projectCreate) {
        ProjectEntity projectEntity=projectResponsitory.findOne(id);
        BeanUtils.copyProperties(projectCreate,projectEntity);
        try {
            projectEntity.setStartTime(new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(projectCreate.getStartTime()).getTime()));
            projectEntity.setEndTime(new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(projectCreate.getEndTime()).getTime()));
        } catch (ParseException e) {
            log.error(TAG, e);
        }
        projectEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        projectEntity.setPublishTime(projectEntity.getUpdateTime());
            projectResponsitory.saveAndFlush(projectEntity);
    }

    @Override
    public PageInfo<DeclarationProjectMessageDTO> getProjectsList(String q, Pageable requestPage) {
        if(q==null||q.trim().equals("")){
            q="%%";
        }else{
            q="%"+q+"%";
        }
        Page<SyDeclarationProjectMessageEntity> rpPage = projectLecturePeopleResponsitory.findListByName(q, requestPage);
        List<DeclarationProjectMessageDTO> assessDetailDTOs = rpPage.getContent().stream().map(p -> projectMapper.proEntityToDTO(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(assessDetailDTOs, requestPage, rpPage.getTotalElements()));
    }

    @Override
    public void deleteProject(Integer id) {
        SyDeclarationProjectMessageEntity entity = projectLecturePeopleResponsitory.findOne(id);
        if(entity == null){
            throw new NotFoundEntityException("数据不存在");
        }else {
            projectLecturePeopleResponsitory.delete(id);
        }
    }

    @Override
    public void deleteAllProjects(Integer[] ids) {
        for(Integer id:ids){
            SyDeclarationProjectMessageEntity entity = projectLecturePeopleResponsitory.findOne(id);
            if(entity == null){
                throw new NotFoundEntityException("数据不存在");
            }else {
                projectLecturePeopleResponsitory.delete(id);
            }
        }
    }

    @Override
    public DeclarationProjectMessageDTO getProjectDetail(Integer id) {
            SyDeclarationProjectMessageEntity pb = projectLecturePeopleResponsitory.findOne(id);
        if(pb==null){
            throw new NotFoundEntityException("数据不存在");
        }
        DeclarationProjectMessageDTO projectMessageDTO = projectMapper.proEntityToDetail(pb);
        return projectMessageDTO;
    }

    @Override
    public void updateProjectViewCount(Integer id) {
        ProjectEntity projectEntity=projectResponsitory.findOne(id);
        projectEntity.setViewCount(projectEntity.getViewCount()+1);
        projectResponsitory.saveAndFlush(projectEntity);
    }

//    @Override
//    public SyDeclarationProjectMessageEntity findProjectMessage(Integer userId) {
//        SyDeclarationProjectMessageEntity syDeclarationProjectMessageEntity=projectResponsitory.findProjectMessageEntity(userId);
//        return syDeclarationProjectMessageEntity;
//    }


    @Override
    public Class getJpaRepositoryClazz() {
        return ProjectResponsitory.class;
    }
}
