package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dao.lqsq.PortalActivityResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.UserenterpriseResponsitory;
import com.cdyoue.oddJobs.dto.Contact;
import com.cdyoue.oddJobs.dto.ggfw.ActivityDetail;
import com.cdyoue.oddJobs.dto.ggfw.ActivityRequest;
import com.cdyoue.oddJobs.dto.ggfw.ActivitySummary;
import com.cdyoue.oddJobs.entity.lgsq.PortalActivityEntity;
import com.cdyoue.oddJobs.entity.lgsq.PortalMessageEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserenterpriseEntity;
import com.cdyoue.oddJobs.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by luanyu on 2017/5/11.
 */
@Component
public class ActivityMapper {

    @Autowired
    private PortalActivityResponsitory activityResponsitory;

    @Autowired
    private UserenterpriseResponsitory userenterpriseResponsitory;

    //PortalActivityEntity转换成ActivityDetail
    public ActivityDetail entityToDto(PortalActivityEntity entity){
        ActivityDetail activityDto = new ActivityDetail();

        activityDto.setBriefing(entity.getInfo());
        activityDto.setTitle(entity.getTitle());

        Contact contact = new Contact();
        contact.setPerson(entity.getContact());
        contact.setPhone(entity.getTel());
        activityDto.setContact(contact);
        activityDto.setReviewStatus(Byte.toString(entity.getReviewStatus()));
        activityDto.setContent(entity.getIntroduction());
        activityDto.setCoverImgUrl(entity.getCoverImg());
        activityDto.setLocation(entity.getAddress());
        activityDto.setPeopleNumber(String.valueOf(entity.getPeopleNumber()));
        activityDto.setViewsCount(entity.getViewCount());
        try {
            UserenterpriseEntity ue = userenterpriseResponsitory.findByUid(entity.getCreateBy());
            activityDto.setCreateBy(ue.getName());
        } catch (Exception e){
            activityDto.setCreateBy(null);
        }


        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        if(entity.getStartTime()!=null){
            String time = df.format(entity.getStartTime());
            activityDto.setStartTime(time);
        }
        if(entity.getEndTime()!=null){
            String time = df.format(entity.getEndTime());
            activityDto.setEndTime(time);
        }
        if(entity.getPublishTime()!=null){
            String time = sf.format(entity.getPublishTime());
            activityDto.setPublishTime(time);
        }

        return activityDto;
    }

    //PortalActivityEntity转换成ActivitySummary
    public ActivitySummary entityToDtoSummary(PortalActivityEntity entity){
        ActivitySummary activityDto = new ActivitySummary();

        activityDto.setId(String.valueOf(entity.getId()));
        activityDto.setBriefing(entity.getInfo());
        activityDto.setTitle(entity.getTitle());
        activityDto.setReviewStatus(entity.getReviewStatus());
        activityDto.setCreateBy(UserUtils.getUserName(entity.getCreateBy()));
        Contact contact = new Contact();
        contact.setPerson(entity.getContact());
        contact.setPhone(entity.getTel());
        activityDto.setCoverImgUrl(entity.getCoverImg());
        activityDto.setLocation(entity.getAddress());
        activityDto.setPeopleNumber(String.valueOf(entity.getPeopleNumber()));
        activityDto.setTop(entity.getTop());
        activityDto.setTopImg(entity.getTopImg());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

        if(entity.getPublishTime()!=null){
            String time = sf.format(entity.getPublishTime());
            activityDto.setPublishTime(time);
        }
        if(entity.getStartTime()!=null){
            String time = df.format(entity.getStartTime());
            activityDto.setStartTime(time);
        }
        activityDto.setLink("/H5/activityDetails.html?id=" + entity.getId());
        return activityDto;
    }
    //
    public PortalActivityEntity requestToEntity(ActivityRequest request, Integer id) {
        PortalActivityEntity entity;
        if(id==null){
             entity = new PortalActivityEntity();
        }else{
             entity = activityResponsitory.findOne(id);
        }

        entity.setTitle(request.getTitle());
        entity.setIntroduction(request.getContent());
        if(request.getContact()!=null){
            entity.setContact(request.getContact().getPerson());
            entity.setTel(request.getContact().getPhone());
        }

        entity.setAddress(request.getLocation());
        entity.setCoverImg(request.getCoverImgUrl());
        entity.setInfo(request.getBriefing());
        entity.setPeopleNumber(Integer.valueOf(request.getPeopleNumber()));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale.ENGLISH);
        if(request.getStartTime()!=null) {
            java.util.Date timeDate = null;
            try {
                timeDate = dateFormat.parse(request.getStartTime().replace("/", "-"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            java.sql.Timestamp dateTime = new java.sql.Timestamp(timeDate.getTime());
            entity.setStartTime(dateTime);
        }
        if(request.getEndTime()!=null) {
            java.util.Date timeDate = null;
            try {
                timeDate = dateFormat.parse(request.getEndTime().replace("/", "-"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            java.sql.Timestamp dateTime = new java.sql.Timestamp(timeDate.getTime());
            entity.setEndTime(dateTime);
        }

        return entity;
    }

    //messageEntity转化成ActivityDTO
    public ActivityDetail messageToActivity(PortalMessageEntity entity){
        //No use for now
        PortalActivityEntity entity1 = activityResponsitory.findOne(entity.getEventId());
        ActivityDetail activityDTO = this.entityToDto(entity1);
        return activityDTO;
    }
}
