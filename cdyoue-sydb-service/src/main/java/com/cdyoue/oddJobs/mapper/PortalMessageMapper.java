package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dao.lqsq.*;
import com.cdyoue.oddJobs.dto.AcceptPeopleSumary;
import com.cdyoue.oddJobs.dto.common.AppMsgResponse;
import com.cdyoue.oddJobs.dto.common.ResponseDetail;
import com.cdyoue.oddJobs.dto.lgfc.TalentBase;
import com.cdyoue.oddJobs.dto.lgfc.TalentSummary;
import com.cdyoue.oddJobs.dto.message.PersonalCenterMessageDto;
import com.cdyoue.oddJobs.dto.message.RespseMessageOffDto;
import com.cdyoue.oddJobs.en.MessageEventTypeEnum;
import com.cdyoue.oddJobs.en.MessageMsgTypeEnum;
import com.cdyoue.oddJobs.entity.lgsq.*;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserpersonalEntity;
import com.cdyoue.oddJobs.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;

/**
 * Created by liaoyoule on 2017/4/24.
 */
@Component
public class PortalMessageMapper {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PortalWordResponsitory portalWordResponsitory;

    @Autowired
    private UserpersonalResponsitory userpersonalResponsitory;

    @Autowired
    private PortalRealNameInfoResponsitory portalRealNameInfoResponsitory;
    @Autowired
    private TalentResponsitory talentResponsitory;


    public TalentSummary portalMessageToTalentSummary(RequirementMessageEntity pme) {

        TalentSummary ts = new TalentSummary();
        UserEntity opera = pme.getOpera();
        if (Optional.ofNullable(opera).isPresent()) {
            TalentBase talentBase = userMapper.userEntityToTalentBase(opera);
            ts.setTalentBase(talentBase);
            ts.setContact(opera.getTel());
            BigDecimal integrityDegree = opera.getIntegrityDegree();

            if (integrityDegree != null) {
                ts.setDataComp(opera.getIntegrityDegree().intValue());
            }
        }
        return ts;
    }

    public AcceptPeopleSumary portalMessageToAcceptPeopleSumary(RequirementMessageEntity pme) {
        UserEntity opera = pme.getOpera();
        AcceptPeopleSumary aps = new AcceptPeopleSumary();
        TalentBase talentBase = userMapper.userEntityToTalentBase(opera);
        Integer userId = opera.getId();
        //查询用户实名认证类型
        List<PortalRealNameInfoEntity> list = portalRealNameInfoResponsitory.findByUserId(userId);
        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();
        for (PortalRealNameInfoEntity p : list) {
            linkedHashSet.add(p.getApplyType());
        }
        aps.setApplyTypes(linkedHashSet);
        aps.setId(userId);
        aps.setHeadImage(talentBase.getCoverImgUrl());
        FunctioncategoryEntity fce = opera.getFunctioncategoryEntity();
        if (fce != null) {
            aps.setCategoryName(fce.getName());
        }

        aps.setName(talentBase.getName());
        //读取一句话留言
        PortalWordEntity pwe = portalWordResponsitory.findWord(opera.getId(), pme.getRequirementEntity().getId(), 3);
        if (pwe != null) {
            aps.setIntro(pwe.getInfo());
            aps.setTel(pwe.getTel());
        }
        //major
        PageRequest pr = new PageRequest(0,1,Sort.Direction.DESC,"startTime");

        org.springframework.data.domain.Page<PortalTechnologyEntity> pteAger  = talentResponsitory.findByMajor(opera.getId(),3,pr);

        if(pteAger.getContent().size() != 0){
            aps.setMajor(pteAger.getContent().get(0).getMajor());
        }

        return aps;
    }


    public AppMsgResponse toUnreadAppMessage(Object entity) {
        AppMsgResponse r = new AppMsgResponse();
        Object[] cells = (Object[]) entity;
        r.setMsgType((int) cells[0]);
        r.setUnreadCount(((BigInteger) cells[1]).intValue());
        return r;
    }

    public ResponseDetail toResponseDetail(PortalMessageEntity p) {
        ResponseDetail rd = new ResponseDetail();
        rd.setId(p.getId());
        rd.setInfo(p.getInfo());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        rd.setCreateTime(sdf.format(p.getCreateTime()));
        rd.setMsgType(p.getMsgType());
        rd.setEventId(p.getEventId());
        rd.setEventType(p.getEventType());
        // 关注活动
        if (p.getEventType() == MessageEventTypeEnum.FOCUS.getValue() && p.getMsgType() == MessageMsgTypeEnum.FoucsActivity.getMsgType()) {
            rd.setH5link("/H5/activityDetails.html?id=" + p.getEventId());
        }
        // 官方通知，具体是什么东东，是不是一对多？要加H5链接
        /*else if (p.getEventType() == MessageEventTypeEnum.OFFICIAL.getValue() && p.getMsgType() == MessageMsgTypeEnum.OfficialNotification.getMsgType()) {
            rd.setH5link("什么鬼");
        }*/
        return rd;
    }



    public ResponseDetail messageRelEntity2ResponseDetail(PortalMessageRelEntity portalMessageRelEntity) {
        ResponseDetail rd = new ResponseDetail();
        if (portalMessageRelEntity.getLookStatus() == true) {
            return null;
        }
        rd.setId(portalMessageRelEntity.getMessageId());
        // 1文章，2问题，3通知...自行扩展
        if (portalMessageRelEntity.getType() == 1) {
            rd.setH5link("/H5/articleDetails.html?id=" + portalMessageRelEntity.getRelEventId());
            rd.setInfo("关注->能人(刚刚发表了一篇新的文章)");
        } else if (portalMessageRelEntity.getType() == 2) {
            rd.setInfo("关注->能人(刚刚提出了一个新的问题)");
        }
        /*else if (portalMessageRelEntity.getType() == 3) {
            rd.setH5link("什么鬼");
            rd.setInfo("官方->通知");
        }*/
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        rd.setCreateTime(sdf.format(portalMessageRelEntity.getCreateTime()));
        rd.setMsgType(portalMessageRelEntity.getType());
        rd.setEventId(portalMessageRelEntity.getRelEventId());
        rd.setEventType(portalMessageRelEntity.getType());
        return rd;
    }

    public ResponseDetail toResponseDetailByDto(PersonalCenterMessageDto p) {
        ResponseDetail rd = new ResponseDetail();
        if (p != null) {
            rd.setId(p.getMessageId());
            if (null != p.getMessageDetail()) rd.setInfo(p.getMessageDetail());
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//                rd.setCreateTime(sdf.format(p.get()));
            rd.setMsgType(p.getMsgTypeEnum());
            rd.setEventId(p.getEventId());
            rd.setEventType(p.getEventTypeEnum());
            rd.setType(p.getType());
            rd.setCreateTime(switchStrDate(p.getMessageTime()));
            rd.setLookStatus(p.getLookStatus());
            rd.setQuesReplyId(p.getQuesReplyId());
            if (p.getEventTypeEnum() == MessageEventTypeEnum.FOCUS.getValue()){
                if (p.getMsgTypeEnum() == MessageMsgTypeEnum.FoucsActivity.getMsgType()){
                    rd.setH5link("/H5/activityDetails.html?id="+p.getEventId());
                }
                if (p.getMsgTypeEnum() == 11){
                    rd.setH5link("/H5/articleDetails.html?id="+p.getEventId());
                }
            }
            if (p.getEventTypeEnum() == MessageEventTypeEnum.OFFICIAL.getValue()){
                if (p.getMsgTypeEnum() == MessageMsgTypeEnum.OfficialNotification.getMsgType()){
                   if (null != p.getExtra()) rd.setH5link(p.getExtra());
                }
            }
        }
        return rd;
    }


    public RespseMessageOffDto toRespseMessageOffDto(PortalMessageEntity p) {
        RespseMessageOffDto ro = new RespseMessageOffDto();
        if (null != p){
            if (null != p.getInfo()) ro.setInfo(p.getInfo());
            if (null != p.getExtra()) ro.setLink(p.getExtra());
            if (null != p.getCreateTime()) ro.setCreateTime(CommonUtils.getDateString(p.getCreateTime(),"yyyy/MM/dd HH:mm:ss"));
            if (null != p.getEventId()) ro.setEventId(p.getEventId());
            if (null != p.getId()) ro.setId(p.getId());
            ro.setEventType(p.getEventType());
            ro.setMsgType(p.getMsgType());
            UserpersonalEntity byUid = userpersonalResponsitory.findByUid(p.getOpera().getId());
            if (null != byUid){
                ro.setCreateBy(byUid.getName());
            }
        }
        return ro;
    }

    private String switchStrDate(Date date){
        String strDate = null;
        if (date != null){
            strDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        }
        return  strDate;
    }
}
