package com.cdyoue.oddJobs.mapper.sydb;

import com.cdyoue.oddJobs.dao.lqsq.UserpersonalResponsitory;
import com.cdyoue.oddJobs.dto.zlcx.*;
import com.cdyoue.oddJobs.entity.lgsq.user.UserpersonalEntity;
import com.cdyoue.oddJobs.entity.syData.SyExpertCareerEntity;
import com.cdyoue.oddJobs.entity.syData.SyExpertEntity;
import com.cdyoue.oddJobs.entity.syData.SyIndustryTypeEntity;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by dengshaojun on 2017/09/18.
 */
@Component
public class ExpertMapper {

    @Autowired
    private CareerMapper careerMapper;

    @Autowired
    private UserpersonalResponsitory userpersonalResponsitory;

    public ExpertSummary entityToSummary(SyExpertEntity entity) {
        ExpertSummary summary = new ExpertSummary();
        summary.setId(entity.getId());
        summary.setTopImgUrl(entity.getTopImg());
        summary.setHeadImgUrl(entity.getHeadImg());
        summary.setName(entity.getName());
        summary.setJob(entity.getJob());
        summary.setIntroduction(entity.getBriefIntro());
        UserpersonalEntity userpersonalEntity = userpersonalResponsitory.findOneByUid(entity.getUserId());
        if (userpersonalEntity != null) {
            summary.setPublisher(userpersonalEntity.getName());
        }
        summary.setCreateTime(entity.getCreateTime().getTime());
        summary.setViewCount(entity.getViewCount());
        summary.setContactCount(entity.getContactCount());
        summary.setTop(entity.getTop());
        return summary;
    }

    public ExpertBaseinfo entityToBaseinfo(SyExpertEntity entity) {
        ExpertBaseinfo baseinfo = new ExpertBaseinfo();
        baseinfo.setId(entity.getId());
        baseinfo.setHeadImgUrl(entity.getHeadImg());
        baseinfo.setName(entity.getName());
        baseinfo.setJob(entity.getJob());
        baseinfo.setGender(entity.getGender());
        baseinfo.setAge(entity.getAge());
        baseinfo.setHomePlace(entity.getHomePlace());
        baseinfo.setLivePlace(entity.getLivePlace());
        baseinfo.setWorkYear(entity.getWorkYear() != null ? entity.getWorkYear() : "");
        baseinfo.setIndustry(entity.getSyIndustryTypeEntity().getName());
        baseinfo.setIntroduction(entity.getBriefIntro());
        baseinfo.setContent(entity.getDetailIntro());
        baseinfo.setIndustryType(entity.getSyIndustryTypeEntity().getId());
        return baseinfo;
    }

    public ExpertTop entityToTop(SyExpertEntity entity) {
        ExpertTop top = new ExpertTop();
        top.setId(entity.getId());
        top.setTopImgUrl(entity.getTopImg());
        top.setName(entity.getName());
        top.setJob(entity.getJob());
        return top;
    }

    public SyExpertEntity requestToEntity(ExpertRequest request) {
        SyExpertEntity entity = new SyExpertEntity();
        entity.setName(request.getName());
        entity.setHeadImg(request.getHeadImgUrl());
        entity.setJob(request.getJob());
        entity.setGender(request.getGender().byteValue());
        entity.setWorkYear(request.getWorkYear());
        SyIndustryTypeEntity industryTypeEntity = new SyIndustryTypeEntity();
        industryTypeEntity.setId(request.getIndustryType());
        entity.setSyIndustryTypeEntity(industryTypeEntity);
        entity.setBriefIntro(request.getIntroduction());
        entity.setDetailIntro(request.getContent());
        Set<SyExpertCareerEntity> set= request.getCareers().stream().map(p -> careerMapper.requestToEntity(p)).collect(Collectors.toSet());
        entity.setSyExpertCareerEntitySet(set);
        entity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        entity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        entity.setTop(new Byte("0"));
        entity.setViewCount(0);
        entity.setContactCount(0);
        entity.setUserId(SecurityUtils.getCurrentUserLogin().getId());
        return entity;
    }

    public SyExpertEntity requestToEntity(ExpertRequest request, SyExpertEntity entity) {
        entity.setName(request.getName());
        entity.setHeadImg(request.getHeadImgUrl());
        entity.setJob(request.getJob());
        entity.setGender(request.getGender().byteValue());
        entity.setWorkYear(request.getWorkYear());
        SyIndustryTypeEntity industryTypeEntity = new SyIndustryTypeEntity();
        industryTypeEntity.setId(request.getIndustryType());
        entity.setSyIndustryTypeEntity(industryTypeEntity);
        entity.setBriefIntro(request.getIntroduction());
        entity.setDetailIntro(request.getContent());
        Set<SyExpertCareerEntity> set= request.getCareers().stream().map(p -> careerMapper.requestToEntity(p, entity.getId())).collect(Collectors.toSet());
        entity.setSyExpertCareerEntitySet(set);
        entity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        return entity;
    }

    public ExpertMini entityToMini(SyExpertEntity entity) {
        ExpertMini mini = new ExpertMini();
        mini.setId(entity.getId());
        mini.setName(entity.getName());
        return mini;
    }
}
