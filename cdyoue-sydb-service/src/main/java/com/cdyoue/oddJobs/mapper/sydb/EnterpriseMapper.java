package com.cdyoue.oddJobs.mapper.sydb;

import com.cdyoue.oddJobs.dto.zlcy.EnterpriseCreate;
import com.cdyoue.oddJobs.dto.zlcy.EnterpriseDetail;
import com.cdyoue.oddJobs.dto.zlcy.EnterpriseSummary;
import com.cdyoue.oddJobs.entity.syData.SyEnterpriseEntity;
import com.cdyoue.oddJobs.service.UserService;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by sky on 2017/9/23.
 */
@Component
public class EnterpriseMapper {
    @Autowired
    UserService userService;
    public EnterpriseDetail syTrainingEntityToTrainingDetail(SyEnterpriseEntity syTrainingEntity){
        EnterpriseDetail enterpriseDetail=new EnterpriseDetail();
        enterpriseDetail.setInfo(syTrainingEntity.getInfo());
        enterpriseDetail.setLogo(syTrainingEntity.getLogo());
        enterpriseDetail.setName(syTrainingEntity.getName());
        enterpriseDetail.setAddress(syTrainingEntity.getAddress());
        enterpriseDetail.setContent(syTrainingEntity.getContent());
        enterpriseDetail.setIndustry(syTrainingEntity.getIndustry());
        return enterpriseDetail;
    }

    public EnterpriseSummary syTrainingEntityToTrainingSummary(SyEnterpriseEntity syTrainingEntity){
        EnterpriseSummary enterpriseSummary=new EnterpriseSummary();
        enterpriseSummary.setInfo(syTrainingEntity.getInfo());
        enterpriseSummary.setLogo(syTrainingEntity.getLogo());
        enterpriseSummary.setTopImage(syTrainingEntity.getTopImage());
        enterpriseSummary.setTop(syTrainingEntity.getTop());
        enterpriseSummary.setName(syTrainingEntity.getName());
        enterpriseSummary.setAddress(syTrainingEntity.getAddress());
        enterpriseSummary.setId(syTrainingEntity.getId());
        Integer userId=syTrainingEntity.getPublishPeople();
        String userName=userService.findOne(userId).getUserName();
        enterpriseSummary.setPublishPeople(userName);
        enterpriseSummary.setViewCount(syTrainingEntity.getViewCount());
        enterpriseSummary.setCreateTime(new SimpleDateFormat("yyyy-MM-dd").format(syTrainingEntity.getCreateTime()));
        return enterpriseSummary;
    }

    public SyEnterpriseEntity enterpriseCreateToSyEnterpriseEntity(EnterpriseCreate enterpriseCreate){
        SyEnterpriseEntity syEnterpriseEntity=new SyEnterpriseEntity();
        syEnterpriseEntity.setName(enterpriseCreate.getName());
        syEnterpriseEntity.setIndustry(enterpriseCreate.getIndustry());
        syEnterpriseEntity.setAddress(enterpriseCreate.getAddress());
        syEnterpriseEntity.setInfo(enterpriseCreate.getInfo());
        syEnterpriseEntity.setContent(enterpriseCreate.getContent());
        syEnterpriseEntity.setLogo(enterpriseCreate.getLogo());
        syEnterpriseEntity.setTop(0);
        syEnterpriseEntity.setPublishPeople(SecurityUtils.getCurrentUserLogin().getId());
        syEnterpriseEntity.setViewCount(0);
        syEnterpriseEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        syEnterpriseEntity.setUpdateTime(syEnterpriseEntity.getCreateTime());
        return syEnterpriseEntity;
    }
}
