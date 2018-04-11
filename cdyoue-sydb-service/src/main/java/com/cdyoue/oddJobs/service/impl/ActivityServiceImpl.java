package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.PortalActivityResponsitory;
import com.cdyoue.oddJobs.dto.ActivityBanner;
import com.cdyoue.oddJobs.dto.ggfw.ActivityDetail;
import com.cdyoue.oddJobs.dto.ggfw.ActivityRequest;
import com.cdyoue.oddJobs.dto.ggfw.ActivitySummary;
import com.cdyoue.oddJobs.dto.Reason;
import com.cdyoue.oddJobs.dto.zscq.IntellectualTop;
import com.cdyoue.oddJobs.en.MessageEventTypeEnum;
import com.cdyoue.oddJobs.en.MessageMsgTypeEnum;
import com.cdyoue.oddJobs.entity.lgsq.PortalActivityEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.ActivityMapper;
import com.cdyoue.oddJobs.service.ActivityService;
import com.cdyoue.oddJobs.service.support.ServiceSupport;
import com.cdyoue.oddJobs.utils.MessageUtils;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by luanyu on 2017/5/11.
 */
@Service
@Transactional
public class ActivityServiceImpl extends ServiceSupport<PortalActivityEntity> implements ActivityService {

    @Autowired
    private ActivityMapper mapper;

    @Autowired
    private PortalActivityResponsitory activityResponsitory;

    @Override
    public ActivityDetail getActivityById(Integer id) {

        PortalActivityEntity entity = activityResponsitory.getOne(id);
        if(entity!=null){
            activityResponsitory.addViewcount(id);
        }
        ActivityDetail activityDTO = mapper.entityToDto(entity);
        List<Integer> focusActivityIds = MessageUtils.getMessageEventIds(MessageEventTypeEnum.FOCUS, MessageMsgTypeEnum.FoucsActivity);
        if (focusActivityIds != null && focusActivityIds.size() > 0) {
            if(focusActivityIds.contains(id)){
                activityDTO.setForcusFlag("1");
            }else{
                activityDTO.setForcusFlag("0");
            }
        } else {
            activityDTO.setForcusFlag("0");
        }
        return activityDTO;
    }

    @Override
    public boolean getActivityCheck(Integer id) {

        if(activityResponsitory.findOne(id)==null){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public String createActivity(ActivityRequest activityDto) {

        Timestamp time = new Timestamp(System.currentTimeMillis());
        PortalActivityEntity entity = mapper.requestToEntity(activityDto,null);
        entity.setCreateTime(time);
        entity.setUpdateTime(time);
        entity.setPublishTime(time);
        entity.setViewCount(0);
        entity.setUpdateBy(SecurityUtils.getCurrentUserLogin().getId());
        entity.setCreateBy(SecurityUtils.getCurrentUserLogin().getId());
        Byte b = 1;
        entity.setReviewStatus(b);

        return String.valueOf(activityResponsitory.save(entity).getId());
    }

    @Override
    public void updateActivity(Integer id, ActivityRequest activityDto) {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        PortalActivityEntity entity = mapper.requestToEntity(activityDto,id);
//        entity.setUpdateTime(time);
        entity.setUpdateBy(SecurityUtils.getCurrentUserLogin().getId());
        byte code = 1;
        entity.setReviewStatus(code);
        activityResponsitory.save(entity);
    }

    @Override//关注活动
    public void focusActivity(Integer id) {
        MessageUtils.createMessage(id, MessageEventTypeEnum.FOCUS, MessageMsgTypeEnum.FoucsActivity);
    }

    @Override
    @Transactional//取消关注活动
    public void cancelfocusActivity(Integer id) {
        MessageUtils.cancelEventAction(id,MessageEventTypeEnum.FOCUS, MessageMsgTypeEnum.FoucsActivity);
    }

    @Override
    public PageInfo<ActivitySummary> findMyList(PageRequest pr, String q) {
        Integer uId = SecurityUtils.getCurrentUserLogin().getId();
        Page<PortalActivityEntity> rpPage = super.findByTwoLike("createBy",String.valueOf(uId),"title",q,pr);
        List<ActivitySummary> activities = rpPage.getContent().stream().map(p -> mapper.entityToDtoSummary(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(activities, pr, rpPage.getTotalElements()));
    }

    @Override
    public PageInfo<ActivitySummary> findList(PageRequest pr, String q,String s) {
        //Page<PortalActivityEntity> rpPage = super.findByOneLike("title",q,pr);
        Page<PortalActivityEntity> rpPage = super.findByTwoLike("title",q,"reviewStatus",s,pr);
        List<ActivitySummary> activities = rpPage.getContent().stream().map(p -> mapper.entityToDtoSummary(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(activities, pr, rpPage.getTotalElements()));
    }

    @Override
    public PageInfo<ActivitySummary> findMyCollectionsListByStrKey(List<Integer> focusQuestionIds, String q, Pageable pr) {
        Page<PortalActivityEntity> entityPage=activityResponsitory.findMyCollectionsListByStrKey(focusQuestionIds,"%"+q+"%",pr);
        List<ActivitySummary> focusQuestion = entityPage.getContent().stream().map(p -> mapper.entityToDtoSummary(p)).collect(Collectors.toList());
        PageInfo<ActivitySummary> pageInfo=new PageInfo<>(new PageImpl(focusQuestion, pr, entityPage.getTotalElements()));
        return pageInfo;
    }

    @Override
    public Integer getCreatorIdBy(Integer id) {
        PortalActivityEntity entity = activityResponsitory.findOne(id);
        return entity.getCreateBy();
    }

    @Override
    public Integer getStatus(Integer id) {
        PortalActivityEntity entity = activityResponsitory.findOne(id);
        return Integer.valueOf(Byte.toString(entity.getReviewStatus()));
    }

    @Override
    @Transactional
    public void approveActivity(Integer id,Reason activity) {
        Integer uId = SecurityUtils.getCurrentUserLogin().getId();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        PortalActivityEntity entity = activityResponsitory.findOne(id);
        byte code = 1;
        entity.setReviewStatus(code);
        entity.setReviewTime(time);
        entity.setReviewUserId(uId);
        entity.setReviewReason(activity.getReason());
        activityResponsitory.save(entity);
        MessageUtils.createAuditMessage(entity.getCreateBy(),id,MessageEventTypeEnum.AUDIT,MessageMsgTypeEnum.AuditActivity,"1");//创建系统消息
    }

    @Override
    @Transactional
    public void rejectActivity(Integer id,Reason activity) {
        Integer uId = SecurityUtils.getCurrentUserLogin().getId();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        PortalActivityEntity entity = activityResponsitory.findOne(id);
        byte code = 2;
        entity.setReviewStatus(code);
        entity.setReviewTime(time);
        entity.setReviewUserId(uId);
        entity.setReviewReason(activity.getReason());
        MessageUtils.createAuditMessage(entity.getCreateBy(),id,MessageEventTypeEnum.AUDIT,MessageMsgTypeEnum.AuditActivity,"2");//创建系统消息
        activityResponsitory.save(entity);
    }

    @Override
    public void deleteActivity(Integer id) {
        PortalActivityEntity entity = activityResponsitory.findOne(id);
        activityResponsitory.delete(entity);
    }

    @Override
    public List<ActivityBanner> getActivityBanners() {
        List<ActivityBanner> banners = new ArrayList<>();
        Object[] list = activityResponsitory.getActivityBanners();
        if (list == null || list.length < 1) {
            throw new NotFoundEntityException("数据不存在");
        }else {
            for (int i = 0;i < list.length;i++){
                Object[] cel = (Object[]) list[i];
                ActivityBanner banner = new ActivityBanner();
                banner.setId((Integer) cel[0]);
                banner.setAddress(String.valueOf(cel[2]));
                banner.setTitle(String.valueOf(cel[1]));
                banner.setTopImg(String.valueOf(cel[3]));
                banners.add(banner);
            }
        }
        return banners;
    }

    @Override
    public void deleteAllActivities(Integer[] ids) {
        for(Integer id:ids){
            PortalActivityEntity entity = activityResponsitory.findOne(id);
            if(entity == null){
                throw new NotFoundEntityException("数据不存在");
            }else {

                activityResponsitory.delete(id);
            }
        }
    }

    @Override
    public void activityTop(IntellectualTop top) {
        PortalActivityEntity pe = activityResponsitory.findOne(top.getId());
        if (pe!=null){
            pe.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            pe.setTop(1);
            if(top.getTopImg().length()>0 && top.getTopImg()!=null){
                pe.setTopImg(top.getTopImg());
            }
            activityResponsitory.save(pe);
        }else {
            throw new NotFoundEntityException("数据不存在");
        }
    }

    @Override
    public void removeActivityTop(Integer id) {
        PortalActivityEntity pe = activityResponsitory.findOne(id);
        if (pe!=null){
            pe.setTop(0);
            activityResponsitory.save(pe);
        }else {
            throw new NotFoundEntityException("数据不存在");
        }
    }

    @Override
    public Class getJpaRepositoryClazz() {
        return PortalActivityResponsitory.class;
    }
}
