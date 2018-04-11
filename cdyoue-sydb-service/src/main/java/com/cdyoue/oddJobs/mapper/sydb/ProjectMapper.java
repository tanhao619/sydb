package com.cdyoue.oddJobs.mapper.sydb;

import com.cdyoue.oddJobs.dao.syData.ProjectLecturePeopleResponsitory;
import com.cdyoue.oddJobs.dto.zlcx.*;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import com.cdyoue.oddJobs.entity.syData.ProjectEntity;
import com.cdyoue.oddJobs.entity.syData.SyDeclarationProjectMessageEntity;
import com.cdyoue.oddJobs.service.UserService;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by sky on 2017/9/18.
 */
@Component
public class ProjectMapper {
    @Autowired
    UserService userService;
    @Autowired
    ProjectLecturePeopleResponsitory projectLecturePeopleResponsitory;

    public ProjectSummary ProjectEntityToProjectSummary(ProjectEntity projectEntity){
        ProjectSummary projectSummary=new ProjectSummary();
        projectSummary.setId(projectEntity.getId());
        projectSummary.setContent(projectEntity.getContent());
        projectSummary.setName(projectEntity.getName());
        Integer userId=projectEntity.getPublishPeople();
        UserEntity userEntity=userService.findOne(userId);
        projectSummary.setPublishPeople(userEntity.getUserName());//发布人
        projectSummary.setDepartment(projectEntity.getDepartment());//发布部门
        projectSummary.setPublishTime(new SimpleDateFormat("yyyy-MM-dd").format(projectEntity.getPublishTime()));
        projectSummary.setStartTime(new SimpleDateFormat("yyyy-MM-dd").format(projectEntity.getStartTime()));
        projectSummary.setEndTime(new SimpleDateFormat("yyyy-MM-dd").format(projectEntity.getEndTime()));
        projectSummary.setAttachmentUrl(projectEntity.getAttachmentUrl());
        projectSummary.setViewCount(projectEntity.getViewCount());
        return projectSummary;
    }
    public ProjectDetail ProjectEntityToProjectDetail(ProjectEntity projectEntity){
        ProjectDetail projectDetail=new ProjectDetail();
        projectDetail.setId(projectEntity.getId());
        projectDetail.setContent(projectEntity.getContent());
        projectDetail.setDepartment(projectEntity.getDepartment());
        projectDetail.setStartTime(new SimpleDateFormat("yyyy-MM-dd").format(projectEntity.getStartTime()));
        projectDetail.setEndTime(new SimpleDateFormat("yyyy-MM-dd").format(projectEntity.getEndTime()));
        projectDetail.setPublishTime(new SimpleDateFormat("yyyy-MM-dd").format(projectEntity.getPublishTime()));
        projectDetail.setName(projectEntity.getName());
        projectDetail.setViewCount(projectEntity.getViewCount());
        projectDetail.setProjectNumber(projectEntity.getProjectNumber());//发文号
        projectDetail.setSource(projectEntity.getSource());//信息来源
        projectDetail.setAttachmentUrl(projectEntity.getAttachmentUrl());//附件
        return projectDetail;
    }

    public ProjectEntity projectCreateToProject(ProjectCreate projectCreate){
        ProjectEntity projectEntity=new ProjectEntity();
        projectEntity.setName(projectCreate.getName());
        projectEntity.setAttachmentUrl(projectCreate.getAttachmentUrl());
        projectEntity.setContent(projectCreate.getContent());
        projectEntity.setDepartment(projectCreate.getDepartment());
        projectEntity.setProjectNumber(projectCreate.getProjectNumber());
        projectEntity.setSource(projectCreate.getSource());
        try {
            projectEntity.setStartTime(new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(projectCreate.getStartTime()).getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            projectEntity.setEndTime(new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(projectCreate.getEndTime()).getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        projectEntity.setPublishTime(new Timestamp(System.currentTimeMillis()));
        projectEntity.setUpdateTime(projectEntity.getPublishTime());
        projectEntity.setViewCount(0);
        projectEntity.setPublishPeople(SecurityUtils.getCurrentUserLogin().getId());
        return projectEntity;
    }

    public ProjectEntity projectCreateToProjectEdit(ProjectCreate projectCreate){
        ProjectEntity projectEntity=new ProjectEntity();
        projectEntity.setName(projectCreate.getName());
        projectEntity.setAttachmentUrl(projectCreate.getAttachmentUrl());
        projectEntity.setContent(projectCreate.getContent());
        projectEntity.setDepartment(projectCreate.getDepartment());
        projectEntity.setProjectNumber(projectCreate.getProjectNumber());
        projectEntity.setSource(projectCreate.getSource());
        try {
            projectEntity.setStartTime(new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(projectCreate.getStartTime()).getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            projectEntity.setEndTime(new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(projectCreate.getEndTime()).getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        projectEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        return projectEntity;
    }

    public  DeclarationProjectMessageDTO proEntityToDTO(SyDeclarationProjectMessageEntity p) {
        DeclarationProjectMessageDTO dto = new DeclarationProjectMessageDTO();
        DeclarationProjectDTO projectDTO = new DeclarationProjectDTO();
        DeclarationProjectEnterpriseDTO enterpriseDTO = new DeclarationProjectEnterpriseDTO();
        BeanUtils.copyProperties(p,dto);
        String proName = projectLecturePeopleResponsitory.getProjectNameById(p.getProjectId());
        String eprName = projectLecturePeopleResponsitory.getEnterpriseNameById(p.getEnterpriseUserId());
        projectDTO.setName(proName);
        enterpriseDTO.setName(eprName);
        dto.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(p.getCreateTime()));
        dto.setProjectInfo(projectDTO);
        dto.setEnterpriseInfo(enterpriseDTO);
        return dto;
    }

    public DeclarationProjectMessageDTO proEntityToDetail(SyDeclarationProjectMessageEntity pb) {
        DeclarationProjectMessageDTO dto = new DeclarationProjectMessageDTO();
        DeclarationProjectDTO projectDTO = new DeclarationProjectDTO();
        BeanUtils.copyProperties(pb,dto);
        dto.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(pb.getCreateTime()));
        String proName = projectLecturePeopleResponsitory.getProjectNameById(pb.getProjectId());
        projectDTO.setName(proName);
        dto.setProjectInfo(projectDTO);
        return dto;
    }
}
