package com.cdyoue.oddJobs.mapper.sydb;

import com.cdyoue.oddJobs.dao.lqsq.UserpersonalResponsitory;
import com.cdyoue.oddJobs.dao.syData.ExpertAchievementCollectRepository;
import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.zlcx.ExpertAchievementDetail;
import com.cdyoue.oddJobs.dto.zlcx.ExpertAchievementRequest;
import com.cdyoue.oddJobs.dto.zlcx.ExpertAchievementSummary;
import com.cdyoue.oddJobs.dto.zlcx.ExpertAchievementTop;
import com.cdyoue.oddJobs.entity.lgsq.user.UserpersonalEntity;
import com.cdyoue.oddJobs.entity.syData.SyExpertAchievementEntity;
import com.cdyoue.oddJobs.entity.syData.SyExpertEntity;
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
public class ExpertAchievementMapper {

    @Autowired
    private UserpersonalResponsitory userpersonalResponsitory;

    @Autowired
    private ExpertAchievementCollectRepository expertAchievementCollectRepository;

    public ExpertAchievementDetail entityToDetail(SyExpertAchievementEntity entity) {
        ExpertAchievementDetail detail = new ExpertAchievementDetail();
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
        detail.setCoverImgUrl(entity.getCoverImg());
        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();
        if (currentUserLogin != null) {
            List list = expertAchievementCollectRepository.findByUserIdAndAchievementId(currentUserLogin.getId(), entity.getId());
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

    public ExpertAchievementSummary entityToSummary(SyExpertAchievementEntity entity) {
        ExpertAchievementSummary summary = new ExpertAchievementSummary();
        summary.setId(entity.getId());
        summary.setTopImgUrl(entity.getTopImg());
        summary.setCoverImgUrl(entity.getCoverImg());
        summary.setName(entity.getName());
        summary.setCreateTime(entity.getCreateTime().getTime());
        summary.setIntroduction(entity.getBriefIntro());
        if (entity.getSyExpertEntity() != null) {
            summary.setExpertName(entity.getSyExpertEntity().getName());
        }
        UserpersonalEntity userpersonalEntity = userpersonalResponsitory.findOneByUid(entity.getUserId());
        if (userpersonalEntity != null) {
            summary.setPublisher(userpersonalEntity.getName());
        }
        summary.setViewCount(entity.getViewCount());
        summary.setCollectCount(entity.getCollectCount());
        summary.setTop(entity.getTop());
        return summary;
    }

    public ExpertAchievementTop entityToTop(SyExpertAchievementEntity entity) {
        ExpertAchievementTop top = new ExpertAchievementTop();
        top.setId(entity.getId());
        top.setTopImgUrl(entity.getTopImg());
        top.setName(entity.getName());
        top.setCreateTime(entity.getCreateTime().getTime());
        return top;
    }

    public SyExpertAchievementEntity requestToEntity(ExpertAchievementRequest request) {
        SyExpertAchievementEntity entity = new SyExpertAchievementEntity();
        entity.setName(request.getName());
        entity.setCoverImg(request.getCoverImgUrl());
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

    public SyExpertAchievementEntity requestToEntity(ExpertAchievementRequest request, SyExpertAchievementEntity entity) {
        entity.setName(request.getName());
        entity.setCoverImg(request.getCoverImgUrl());
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
