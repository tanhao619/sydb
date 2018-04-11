package com.cdyoue.oddJobs.mapper.sydb;

import com.cdyoue.oddJobs.dto.zlcx.ProjectLectureCreate;
import com.cdyoue.oddJobs.dto.zlcx.ProjectLectureDetail;
import com.cdyoue.oddJobs.dto.zlcx.ProjectLectureSummary;
import com.cdyoue.oddJobs.entity.syData.ProjectLectureEntity;
import com.cdyoue.oddJobs.service.UserService;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by sky on 2017/9/19.
 */
@Component
public class ProjectLectureMapper {
    @Autowired
    UserService userService;
    public ProjectLectureDetail projectLectureEntityToProjectLectureDetail(ProjectLectureEntity projectLectureEntity){
        ProjectLectureDetail projectLectureDetail=new ProjectLectureDetail();
        projectLectureDetail.setName(projectLectureEntity.getName());
        projectLectureDetail.setContent(projectLectureEntity.getContent());
        projectLectureDetail.setPublishTime(new SimpleDateFormat("yyyy-MM-dd").format(projectLectureEntity.getPublishTime()));
        projectLectureDetail.setViewCount(projectLectureEntity.getViewCount());
        projectLectureDetail.setInfo(projectLectureEntity.getInfo());
        projectLectureDetail.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(projectLectureEntity.getTime()));
        projectLectureDetail.setImage(projectLectureEntity.getImage());
        projectLectureDetail.setAddress(projectLectureEntity.getAddress());
        return projectLectureDetail;
    }

    public ProjectLectureSummary projectLectureEntityToProjectLectureSummary(ProjectLectureEntity projectLectureEntity){
        ProjectLectureSummary projectLectureSummary=new ProjectLectureSummary();
        projectLectureSummary.setId(projectLectureEntity.getId());
        projectLectureSummary.setName(projectLectureEntity.getName());
        projectLectureSummary.setInfo(projectLectureEntity.getInfo());
        projectLectureSummary.setImage(projectLectureEntity.getImage());
        projectLectureSummary.setTopImage(projectLectureEntity.getTopImage());
        projectLectureSummary.setTop(projectLectureEntity.getTop());
        projectLectureSummary.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(projectLectureEntity.getTime()));
        projectLectureSummary.setAddress(projectLectureEntity.getAddress());
        projectLectureSummary.setPublishTime(new SimpleDateFormat("yyyy-MM-dd").format(projectLectureEntity.getPublishTime()));
        projectLectureSummary.setViewCount(projectLectureEntity.getViewCount());
        Integer userId=projectLectureEntity.getPublishPeople();
        String userName=userService.findOne(userId).getUserName();
        projectLectureSummary.setPublishPeople(userName);
        return projectLectureSummary;
    }

    public ProjectLectureEntity projectLectureCreateToProjectLectureEntity(ProjectLectureCreate projectLectureCreate){
        ProjectLectureEntity projectLectureEntity=new ProjectLectureEntity();
        projectLectureEntity.setName(projectLectureCreate.getName());
        try {
            projectLectureEntity.setTime(new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(projectLectureCreate.getTime()).getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        projectLectureEntity.setAddress(projectLectureCreate.getAddress());
        projectLectureEntity.setInfo(projectLectureCreate.getInfo());
        projectLectureEntity.setContent(projectLectureCreate.getContent());
        projectLectureEntity.setImage(projectLectureCreate.getImage());
        projectLectureEntity.setViewCount(0);
        projectLectureEntity.setPublishTime(new Timestamp(System.currentTimeMillis()));
        projectLectureEntity.setUpdateTime(projectLectureEntity.getPublishTime());
        projectLectureEntity.setPublishPeople(SecurityUtils.getCurrentUserLogin().getId());
        projectLectureEntity.setTop(0);//默认非置顶
        return projectLectureEntity;
    }
}
