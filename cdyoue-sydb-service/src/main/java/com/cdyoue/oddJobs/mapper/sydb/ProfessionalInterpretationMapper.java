package com.cdyoue.oddJobs.mapper.sydb;

import com.cdyoue.oddJobs.dao.lqsq.UserpersonalResponsitory;
import com.cdyoue.oddJobs.dao.syData.ProfessionalInterpretationCollectRepository;
import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.zlcx.*;
import com.cdyoue.oddJobs.entity.lgsq.user.UserpersonalEntity;
import com.cdyoue.oddJobs.entity.syData.SyExpertEntity;
import com.cdyoue.oddJobs.entity.syData.SyProfessionalInterpretationEntity;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by dengshaojun on 2017/09/18.
 */
@Component
public class ProfessionalInterpretationMapper {

    @Autowired
    private UserpersonalResponsitory userpersonalResponsitory;

    @Autowired
    private ProfessionalInterpretationCollectRepository professionalInterpretationCollectRepository;

    public ProfessionalInterpretationDetail entityToDetail(SyProfessionalInterpretationEntity entity) {
        ProfessionalInterpretationDetail detail = new ProfessionalInterpretationDetail();
        detail.setId(entity.getId());
        detail.setName(entity.getName());
        if (entity.getSyExpertEntity() != null) {
            detail.setAuthor(entity.getSyExpertEntity().getName());
            detail.setExpertId(entity.getSyExpertEntity().getId());
        } else {
            detail.setExpertId(0);
        }
        detail.setCreateTime(entity.getCreateTime().getTime());
        detail.setViewCount(entity.getViewCount());
        detail.setCollectCount(entity.getCollectCount());
        detail.setContent(entity.getContent());
        detail.setIntroduction(entity.getBriefIntro());
        detail.setImgUrl(entity.getTopImg());
        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();
        if (currentUserLogin != null) {
            List list = professionalInterpretationCollectRepository.findByUserIdAndInterpretationId(currentUserLogin.getId(), entity.getId());
            if (list.size() > 0) {
                detail.setCollected(true);
            } else {
                detail.setCollected(false);
            }
        } else {
            detail.setCollected(false);
        }
        return detail;
    }

    public ProfessionalInterpretationSummary entityToSummary(SyProfessionalInterpretationEntity entity) {
        ProfessionalInterpretationSummary summary = new ProfessionalInterpretationSummary();
        summary.setId(entity.getId());
        summary.setName(entity.getName());
        if (entity.getSyExpertEntity() != null) {
            summary.setAuthor(entity.getSyExpertEntity().getName());
        }
        summary.setCreateTime(entity.getCreateTime().getTime());
        summary.setViewCount(entity.getViewCount());
        UserpersonalEntity userpersonalEntity = userpersonalResponsitory.findOneByUid(entity.getUserId());
        if (userpersonalEntity != null) {
            summary.setPublisher(userpersonalEntity.getName());
        }
        summary.setCollectCount(entity.getCollectCount());
        summary.setTop(entity.getTop());
        summary.setTopImgUrl(entity.getTopImg());
        return summary;
    }


    public ProfessionalInterpretationTop entityToTop(SyProfessionalInterpretationEntity entity) {
        ProfessionalInterpretationTop top = new ProfessionalInterpretationTop();
        top.setId(entity.getId());
        top.setTopImgUrl(entity.getTopImg());
        top.setName(entity.getName());
        top.setIntroduction(entity.getBriefIntro());
        return top;
    }

    public SyProfessionalInterpretationEntity requestToEntity(ProfessionalInterpretationRequest request) {
        SyProfessionalInterpretationEntity entity = new SyProfessionalInterpretationEntity();
        entity.setName(request.getName());
        entity.setBriefIntro(request.getIntroduction());
        entity.setContent(request.getContent());
        if (request.getExpertId() != null && request.getExpertId() != 0){
            SyExpertEntity expertEntity = new SyExpertEntity();
            expertEntity.setId(request.getExpertId());
            entity.setSyExpertEntity(expertEntity);
        }
        entity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        entity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        entity.setCollectCount(0);
        entity.setViewCount(0);
        entity.setTop(new Byte("0"));
        entity.setUserId(SecurityUtils.getCurrentUserLogin().getId());
        return entity;
    }

    public SyProfessionalInterpretationEntity requestToEntity(ProfessionalInterpretationRequest request, SyProfessionalInterpretationEntity entity) {
        entity.setName(request.getName());
        entity.setBriefIntro(request.getIntroduction());
        entity.setContent(request.getContent());
        if (request.getExpertId() != null && request.getExpertId() != 0){
            SyExpertEntity expertEntity = new SyExpertEntity();
            expertEntity.setId(request.getExpertId());
            entity.setSyExpertEntity(expertEntity);
        }
        entity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        return entity;
    }
}
