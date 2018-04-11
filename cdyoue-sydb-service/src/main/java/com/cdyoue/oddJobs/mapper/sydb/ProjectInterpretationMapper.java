package com.cdyoue.oddJobs.mapper.sydb;

import com.cdyoue.oddJobs.dto.zlcx.ProjectInterpretationBase;
import com.cdyoue.oddJobs.dto.zlcx.ProjectInterpretationDetail;
import com.cdyoue.oddJobs.dto.zlcx.ProjectInterpretationSummary;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import com.cdyoue.oddJobs.entity.syData.ProjectInterpretationEntity;
import com.cdyoue.oddJobs.service.UserService;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by sky on 2017/9/19.
 */
@Component
public class ProjectInterpretationMapper {
    @Autowired
    UserService userService;

    public ProjectInterpretationDetail projectInterpretationEntityToProjectInterpretationDetail(ProjectInterpretationEntity projectInterpretationEntity){
        ProjectInterpretationDetail projectInterpretationDetail=new ProjectInterpretationDetail();
        projectInterpretationDetail.setContent(projectInterpretationEntity.getContent());
        projectInterpretationDetail.setTitle(projectInterpretationEntity.getTitle());
        projectInterpretationDetail.setViewCount(projectInterpretationEntity.getViewCount());
        projectInterpretationDetail.setPublishTime(new SimpleDateFormat("yyyy-MM-dd").format(projectInterpretationEntity.getPublishTime()));
        return projectInterpretationDetail;
    }

    public ProjectInterpretationSummary projectInterpretationEntityToProjectInterpretationSummary(ProjectInterpretationEntity projectInterpretationEntity){
        ProjectInterpretationSummary projectInterpretationSummary=new ProjectInterpretationSummary();
        projectInterpretationSummary.setId(projectInterpretationEntity.getId());
        projectInterpretationSummary.setTitle(projectInterpretationEntity.getTitle());
        projectInterpretationSummary.setViewCount(projectInterpretationEntity.getViewCount());
        projectInterpretationSummary.setPublishTime(new SimpleDateFormat("yyyy-MM-dd").format(projectInterpretationEntity.getPublishTime()));
        //查询发布人
        Integer userId=projectInterpretationEntity.getPublishPeople();
        UserEntity userEntity=userService.findOne(userId);
        projectInterpretationSummary.setPublishPeople(userEntity.getUserName());
        return projectInterpretationSummary;
    }

    public ProjectInterpretationEntity projectInterpretationBaseToProjectInterpretationEntity(ProjectInterpretationBase projectInterpretationBase){
        ProjectInterpretationEntity projectInterpretationEntity=new ProjectInterpretationEntity();
        projectInterpretationEntity.setContent(projectInterpretationBase.getContent());
        projectInterpretationEntity.setTitle(projectInterpretationBase.getTitle());
        projectInterpretationEntity.setViewCount(0);
        projectInterpretationEntity.setPublishTime(new Timestamp(System.currentTimeMillis()));
        projectInterpretationEntity.setUpdateTime(projectInterpretationEntity.getPublishTime());
        projectInterpretationEntity.setPublishPeople(SecurityUtils.getCurrentUserLogin().getId());
        return projectInterpretationEntity;
    }
}
