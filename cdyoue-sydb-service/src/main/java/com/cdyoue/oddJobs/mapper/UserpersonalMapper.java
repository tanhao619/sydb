package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dao.lqsq.UserResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.UserpersonalResponsitory;
import com.cdyoue.oddJobs.dto.User;
import com.cdyoue.oddJobs.dto.lgfc.TalentAbility;
import com.cdyoue.oddJobs.dto.lgfc.TalentBase;
import com.cdyoue.oddJobs.dto.lgfc.TalentInfo;
import com.cdyoue.oddJobs.dto.lgfc.TalentSummary;
import com.cdyoue.oddJobs.en.MessageEventTypeEnum;
import com.cdyoue.oddJobs.en.MessageMsgTypeEnum;
import com.cdyoue.oddJobs.entity.lgsq.PortalMessageEntity;
import com.cdyoue.oddJobs.entity.lgsq.PortalTechnologyEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserpersonalEntity;
import com.cdyoue.oddJobs.utils.DataIntegrityUtils;
import com.cdyoue.oddJobs.utils.MessageUtils;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by sky on 2017/5/2.
 *
 */
@Component
public class UserpersonalMapper {
    @Autowired
    private UserpersonalResponsitory userpersonalResponsitory;
    @Autowired
    private UserResponsitory userResponsitory;

    /**
     * UserpersonalEntity 转 TalentAbility
     *
     * @param userpersonalEntity
     * @return
     */
    public TalentAbility userpersonalEntityToTalentAbility(UserpersonalEntity userpersonalEntity) {
        TalentAbility ability = new TalentAbility();
        TalentSummary summary = new TalentSummary();
        ability.setTalentSummary(summary);
        TalentBase base = new TalentBase();
        base.setId(userpersonalEntity.getUserId());
        base.setName(userpersonalEntity.getName());
        base.setCoverImgUrl(userpersonalEntity.getHeadImg());
        base.setInfo(userpersonalEntity.getInfo());
        base.setLink("/H5/findexpert.html?id="+ userpersonalEntity.getUserId());
        summary.setTalentBase(base);
        summary.setPosition(userpersonalEntity.getJob());
        return ability;
    }

    /**
     * UserEntity 转 TalentInfo
     *
     * @param userEntity
     * @return
     */
    public TalentInfo userEntityToTalentInfo(UserEntity userEntity,Boolean isFollow) {
        TalentInfo talentInfo = new TalentInfo();
        TalentSummary summary = new TalentSummary();
        if(null != userEntity.getUserpersonalEntity().getJob()){
            summary.setPosition(userEntity.getUserpersonalEntity().getJob());
        }
        if(null != userEntity.getUserpersonalEntity().getSignature()){
            summary.setSentence(userEntity.getUserpersonalEntity().getSignature());
        }
        summary.setSex(userEntity.getSex());
        summary.setAge(userEntity.getAge());
        summary.setCountry(userEntity.getUserpersonalEntity().getHomeTown());
        summary.setLocation(userEntity.getUserpersonalEntity().getLocation());
        summary.setIndustry(userEntity.getFunctioncategoryEntity()== null ? null:userEntity.getFunctioncategoryEntity().getName());
        summary.setIntroduction(userEntity.getUserpersonalEntity().getIntroduction());
        summary.setWorkingLife(userEntity.getWorkingLife());
        talentInfo.setTalentSummary(summary);
        TalentBase base = new TalentBase();
        base.setName(userEntity.getUserpersonalEntity().getName());
        base.setCoverImgUrl(userEntity.getUserpersonalEntity().getHeadImg());
        base.setInfo(userEntity.getUserpersonalEntity().getInfo());
        base.setIsFollow(isFollow);
        summary.setTalentBase(base);
        return talentInfo;
    }

    /**
     * PortalTechnologyEntity 转 TalentSummary
     *
     * @param technolog
     * @return
     */
    public TalentSummary PortalTechnologyEntityToTalentSummary(PortalTechnologyEntity technolog) {
        TalentSummary summary = new TalentSummary();
        summary.setExperienceName(technolog.getName());
        summary.setMajor(technolog.getMajor());
        summary.setEducation(technolog.getEducation());
        summary.setStartTime(technolog.getStartTime());
        summary.setEndTime(technolog.getEndTime());
        summary.setIntroduction(technolog.getIntroduction());
        return summary;
    }

    /**
     * UserpersonalEntity 转 TalentBase
     *
     * @param user
     * @return
     */
    public TalentBase UserpersonalEntityToTalentBase(UserpersonalEntity user) {
        TalentBase base = new TalentBase();
        base.setId(user.getUserId());
        base.setCoverImgUrl(user.getHeadImg());
        base.setName(user.getName());
        base.setInfo(user.getInfo());
        base.setPosition(user.getJob());
        base.setInvitedNum(user.getInvitedNum());
        base.setIndustry(userpersonalResponsitory.getIndustry(user.getUserId()));
        UserEntity ue = userpersonalResponsitory.getTalentBaseInfo(user.getUserId());
//        if (ue != null) base.setDataComp(DataIntegrityUtils.getDataComp(user.getUserId(),ue.getUserType()));
        if(ue!=null){
            //得到资料完整度
            base.setDataComp(ue.getIntegrityDegree().intValue());
        }
        base.setLink("/H5/findexpert.html?id=" + user.getUserId());
        List<PortalMessageEntity> messages = new ArrayList<>();
        if(null != SecurityUtils.getCurrentUserLogin()){
            messages = userpersonalResponsitory.isInvited(SecurityUtils.getCurrentUserLogin().getId(),user.getUserId());
        }
        if(messages.size() != 0){
            base.setIsInvited(true);
        }

        return base;
    }

    public TalentBase UserpersonalEntityToTalentBase2(UserpersonalEntity user,String type, Integer eventId) {
        TalentBase base = new TalentBase();
        base.setId(user.getUserId());
        base.setCoverImgUrl(user.getHeadImg());
        base.setName(user.getName());
        base.setInfo(user.getInfo());
        base.setPosition(user.getJob());
        base.setInvitedNum(user.getInvitedNum());
        base.setIndustry(userpersonalResponsitory.getIndustry(user.getUserId()));
        UserEntity ue = userpersonalResponsitory.getTalentBaseInfo(user.getUserId());
//        if (ue != null) base.setDataComp(DataIntegrityUtils.getDataComp(user.getUserId(),ue.getUserType()));
        if(ue!=null){
            //得到资料完整度
            base.setDataComp(ue.getIntegrityDegree().intValue());
        }
        base.setLink("/H5/findexpert.html?id=" + user.getUserId());
        List<PortalMessageEntity> messages = new ArrayList<>();
//        if(null != SecurityUtils.getCurrentUserLogin()){
//            messages = userpersonalResponsitory.isInvited(SecurityUtils.getCurrentUserLogin().getId(),user.getUserId());
//        }
//        if(messages.size() != 0){
//            base.setIsInvited(true);
//        }
        if(type.equals("full")){
            Boolean messageExist = MessageUtils.isMessageExist(user.getUserId(), eventId, MessageEventTypeEnum.INVITATION, MessageMsgTypeEnum.InvitationFullTimeJob);
            base.setIsInvited(messageExist);
        }else if(type.equals("part")){
            Boolean messageExist = MessageUtils.isMessageExist(user.getUserId(), eventId, MessageEventTypeEnum.INVITATION, MessageMsgTypeEnum.ApplyForPartTimeJob);
            base.setIsInvited(messageExist);
        }else{
            base.setIsInvited(false);
        }
        return base;
    }

    public UserpersonalEntity userpersonalEntityToMap(UserpersonalEntity entity,Map<String ,Object> map){
        UserpersonalEntity userpersonalEntity = new UserpersonalEntity();
//        userpersonalEntity.setActivity(getString(map,"activity"));
        return  userpersonalEntity;

    }


    public String getString(Map<String, Object> map, String key) {
        try {
            Object o = map.get(key.toUpperCase());
            if(o==null){
                return null;
            }
            return o.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    public Integer getInteger(Map<String,Object> map,String key){
        try {
            Object o = map.get(key.toUpperCase());
            if(o instanceof Integer){
                return (Integer) o;
            }
            Integer str = Integer.parseInt((String)o);
            return str;
        } catch (ClassCastException e) {
            BigDecimal decimal = (BigDecimal) map.get(key);
            return decimal.intValue();
        } catch (Exception e) {
            return null;
        }
    }
}
