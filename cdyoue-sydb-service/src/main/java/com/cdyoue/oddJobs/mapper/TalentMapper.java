package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dao.lqsq.TalentResponsitory;
import com.cdyoue.oddJobs.dto.lgfc.TalentCareer;
import com.cdyoue.oddJobs.dto.lgfc.TalentEducation;
import com.cdyoue.oddJobs.dto.lgfc.UserOperation;
import com.cdyoue.oddJobs.dto.lgfc.UserPortrait;
import com.cdyoue.oddJobs.entity.lgsq.PortalTechnologyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by tr on 2017/5/10.
 */

@Component
public class TalentMapper {
    @Autowired
    private TalentResponsitory talentResponsitory;

    /**
     * TalentEducation转PortalTechnologyEntity
     * @param talentEducation
     * @return portalTechnologyEntity
     */
    public PortalTechnologyEntity talentEducationToPortalTechnologyEntity(TalentEducation talentEducation){
        PortalTechnologyEntity portalTechnologyEntity = new PortalTechnologyEntity();
        if(null != talentEducation.getId()){
            portalTechnologyEntity.setId(talentEducation.getId());
        }

        portalTechnologyEntity.setName(talentEducation.getName());
        portalTechnologyEntity.setEducation(talentEducation.getEducation());
        portalTechnologyEntity.setMajor(talentEducation.getMajor());
        portalTechnologyEntity.setType(2);
        //字符串转为Timestamp
        Timestamp ts1 = Timestamp.valueOf(talentEducation.getStartTime());
        portalTechnologyEntity.setStartTime(ts1);
        Timestamp ts2 = Timestamp.valueOf(talentEducation.getEndTime());
        portalTechnologyEntity.setEndTime(ts2);
        return portalTechnologyEntity;
    }

    /**
     * TalentCareer转PortalTechnologyEntity
     * @param talentCareer
     * @return portalTechnologyEntity
     */
    public PortalTechnologyEntity talentCareerToPortalTechnologyEntity(TalentCareer talentCareer){
        PortalTechnologyEntity portalTechnologyEntity = new PortalTechnologyEntity();
        if(null != talentCareer.getId()){
            portalTechnologyEntity.setId(talentCareer.getId());
        }
        if(null != talentCareer.getUserId()){
            portalTechnologyEntity.setUserId(talentCareer.getUserId());
        }

        portalTechnologyEntity.setName(talentCareer.getName());
        portalTechnologyEntity.setMajor(talentCareer.getMajor());
        portalTechnologyEntity.setType(3);
        portalTechnologyEntity.setIntroduction(talentCareer.getIntroduction());
        //字符串转为Timestamp
        Timestamp ts1 = Timestamp.valueOf(talentCareer.getStartTime());
        portalTechnologyEntity.setStartTime(ts1);
        Timestamp ts2 = Timestamp.valueOf(talentCareer.getEndTime());
        portalTechnologyEntity.setEndTime(ts2);
        return portalTechnologyEntity;
    }

    /**
     * PortalTechnologyEntity转TalentCareer
     * @param portalTechnologyEntity
     * @return talentCareer
     */
    public TalentCareer portalTechnologyEntityToTalentCareer(PortalTechnologyEntity portalTechnologyEntity){
        TalentCareer talentCareer = new TalentCareer();
        talentCareer.setId(portalTechnologyEntity.getId());
        talentCareer.setUserId(portalTechnologyEntity.getUserId());
        talentCareer.setName(portalTechnologyEntity.getName());
        talentCareer.setMajor(portalTechnologyEntity.getMajor());
        talentCareer.setStartTime(portalTechnologyEntity.getStartTime() == null ? null : portalTechnologyEntity.getStartTime().toString());
        talentCareer.setEndTime(portalTechnologyEntity.getEndTime() == null ? null : portalTechnologyEntity.getEndTime().toString());
        talentCareer.setIntroduction(portalTechnologyEntity.getIntroduction());
        return talentCareer;
    }

    /**
     * PortalTechnologyEntity转TalentEducation
     * @param portalTechnologyEntity
     * @return talentEducation
     */
    public TalentEducation portalTechnologyEntityToTalentEducation(PortalTechnologyEntity portalTechnologyEntity){
        TalentEducation talentEducation = new TalentEducation();
        talentEducation.setId(portalTechnologyEntity.getId());
        talentEducation.setUserId(portalTechnologyEntity.getUserId());
        talentEducation.setName(portalTechnologyEntity.getName());
        talentEducation.setMajor(portalTechnologyEntity.getMajor());
        talentEducation.setEducation(portalTechnologyEntity.getEducation());
        talentEducation.setStartTime(portalTechnologyEntity.getStartTime() == null ? null : portalTechnologyEntity.getStartTime().toString());
        talentEducation.setEndTime(portalTechnologyEntity.getEndTime() == null ? null : portalTechnologyEntity.getEndTime().toString());
        return talentEducation;
    }

    public static void main(String[] args) {
        Date date1 = new Date();//获取当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = sdf.format(date1);//时间存储为字符串
        System.out.println(str);
        Timestamp.valueOf(str);//转换时间字符串为Timestamp
        System.out.println(Timestamp.valueOf(str));//输出结果
    }

    public List<UserPortrait> UserOperationToLists(UserOperation uo) {
        List<UserPortrait> lists = new ArrayList<>();
        if (uo.getActivitytimes() > 0) {
            UserPortrait up = new UserPortrait(uo.getActivitytimes(), "活动");
            lists.add(up);
        }
        if (uo.getEnterpriserecruitmenttimes() > 0) {
            UserPortrait up = new UserPortrait(uo.getEnterpriserecruitmenttimes(), "企业招聘");
            lists.add(up);
        }
        if (uo.getMoonlightingtimes() > 0) {
            UserPortrait up = new UserPortrait(uo.getMoonlightingtimes(), "兼职工作");
            lists.add(up);
        }
        if (uo.getInvestmenttimes() > 0) {
            UserPortrait up = new UserPortrait(uo.getInvestmenttimes(), "投融资");
            lists.add(up);
        }
        if (uo.getOutsourcingprojecttimes() > 0) {
            UserPortrait up = new UserPortrait(uo.getOutsourcingprojecttimes(), "外包项目");
            lists.add(up);
        }
        if (uo.getQuestiontimes() > 0) {
            UserPortrait up = new UserPortrait(uo.getQuestiontimes(), "问题");
            lists.add(up);
        }
        if (uo.getActivitytimes() > 0) {
            UserPortrait up = new UserPortrait(uo.getActivitytimes(), "活动次数");
            lists.add(up);
        }
        if (uo.getNewstimes() > 0) {
            UserPortrait up = new UserPortrait(uo.getNewstimes(), "新闻");
            lists.add(up);
        }
        if (uo.getPatenttimes() > 0) {
            UserPortrait up = new UserPortrait(uo.getPatenttimes(), "专利");
            lists.add(up);
        }
        if (uo.getPolicytimes() > 0) {
            UserPortrait up = new UserPortrait(uo.getPolicytimes(), "政策");
            lists.add(up);
        }
        if (uo.getOddjobtimes() > 0) {
            UserPortrait up = new UserPortrait(uo.getOddjobtimes(), "零工");
            lists.add(up);
        }
        if (uo.getMastertimes() > 0) {
            UserPortrait up = new UserPortrait(uo.getMastertimes(), "大咖");
            lists.add(up);
        }
        if (uo.getTutortimes() > 0) {
            UserPortrait up = new UserPortrait(uo.getTutortimes(), "导师");
            lists.add(up);
        }
        if (uo.getExperttimes() > 0) {
            UserPortrait up = new UserPortrait(uo.getExperttimes(), "专家");
            lists.add(up);
        }
        if (uo.getAgenttimes() > 0) {
            UserPortrait up = new UserPortrait(uo.getAgenttimes(), "经纪人");
            lists.add(up);
        }
        if (uo.getActivitytimes() > 0) {
            UserPortrait up = new UserPortrait(uo.getActivitytimes(), "活动");
            lists.add(up);
        }
        if (uo.getFieldtimes() > 0) {
            UserPortrait up = new UserPortrait(uo.getFieldtimes(), "场地");
            lists.add(up);
        }
        return lists;
    }
}
