package com.cdyoue.oddJobs.utils;

import com.cdyoue.oddJobs.dto.account.PersonAccountSumary;
import com.cdyoue.oddJobs.entity.lgsq.PortalRealNameInfoEntity;
import com.cdyoue.oddJobs.entity.lgsq.PortalTechnologyEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserenterpriseEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserpersonalEntity;
import com.cdyoue.oddJobs.service.TalentService;
import com.cdyoue.oddJobs.service.UserEnterpriseService;
import com.cdyoue.oddJobs.service.UserpersonalService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by liaoyoule on 2017/6/12.
 */
public class DataIntegrityUtils {
    private final static Logger logger = LoggerFactory.getLogger(MessageUtils.class);

    private static UserpersonalService userpersonalService = SpringContextUtil.getBean(UserpersonalService.class);

    private static UserEnterpriseService userEnterpriseService = SpringContextUtil.getBean(UserEnterpriseService.class);

    private static TalentService talentService = SpringContextUtil.getBean(TalentService.class);

    public static Integer countDataComp(PersonAccountSumary personAccountSumary) {
        int count = 0;
        if (StringUtils.isNotBlank(personAccountSumary.getHeadImg())) {
            count = count + 20;
        }
        if (StringUtils.isNotBlank(personAccountSumary.getName())) {
            count = count + 10;
        }
        if (null != personAccountSumary.getSex()) {
            count = count + 10;
        }
        if (StringUtils.isNotBlank(personAccountSumary.getHomeTown())) {
            count = count + 5;
        }
        if (StringUtils.isNotBlank(personAccountSumary.getLocation())) {
            count = count + 5;
        }
        if (null != personAccountSumary.getExpectFunctionCategory()) {
            count = count + 5;
        }
        if (StringUtils.isNotBlank(personAccountSumary.getEmail())) {
            count = count + 10;
        }
        if (StringUtils.isNotBlank(personAccountSumary.getTel())) {
            count = count + 10;
        }
        if (StringUtils.isNotBlank(personAccountSumary.getInfo())) {
            count = count + 5;
        }
        if (StringUtils.isNotBlank(personAccountSumary.getIntroduction())) {
            count = count + 5;
        }
        if (null != personAccountSumary.getRecruitmentCategoryId()) {
            count = count + 5;
        }
        if (null != personAccountSumary.getParttimeCategoryId()) {
            count = count + 5;
        }
        if (null != personAccountSumary.getOutsourcingprojectType()) {
            count = count + 5;
        }
        return count;
    }

    /**
     * 获取用户资料完整度
     *
     * @param uid
     * @param uType 0 个人用户 1 企业用户
     * @return
     */
    public static int getDataComp(Integer uid, int uType) {

        int dataComp = 0;
        List<PortalRealNameInfoEntity> applyInfos = userpersonalService.findApplysByUid(uid);//获取认证信息
        if (uType == 0) {//个人用户
            UserpersonalEntity us = userpersonalService.findByUid(uid);//个人用户获取个人用户信息
            if (us != null) {
//                if (us.getBirthday() != null) dataComp += 5;
                if (us.getName() != null) dataComp += 5;
                if (us.getNickName() != null) dataComp += 5;
                if (us.getHeadImg() != null) dataComp += 5;
                if (us.getHomeTown() != null) dataComp += 5;
                if (us.getLocation() != null) dataComp += 10;
                if (us.getInfo() != null) dataComp += 5;
                if (us.getOutsourcingprojectType() != null) dataComp += 5;
                if (us.getParttimeCategoryId() != null) dataComp += 5;
                if (us.getRecruitmentCategoryId() != null) dataComp += 5;
                if (us.getSignature() != null) dataComp += 5;

            }
            List<PortalTechnologyEntity> careers = talentService.findByUid(uid, 2);
            List<PortalTechnologyEntity> edus = talentService.findByUid(uid, 3);
            if (careers != null && careers.size() > 0) dataComp += 5;
            if (edus != null && edus.size() > 0) dataComp += 5;
            if (applyInfos != null && applyInfos.size() > 0) {
                for (PortalRealNameInfoEntity p : applyInfos) {
                    if (p.getApplyType() == 1) dataComp += 5;
                    if (p.getApplyType() == 2) dataComp += 5;
                    if (p.getApplyType() == 3) dataComp += 5;
                }
            }
        }
        if (uType == 1) {//企业用户
            UserenterpriseEntity ue = userEnterpriseService.findByUid(uid);//企业用户获取企业信息if(us != null){
            if (null != ue) {

                if (ue.getName() != null) dataComp += 5;
                if (ue.getEstablishmentTime() != null) dataComp += 5;
                if (ue.getHeadImg() != null) dataComp += 5;
                if (ue.getEnterpriseDetail() != null) dataComp += 15;
                if (ue.getLocation() != null) dataComp += 5;
                if (ue.getInfo() != null) dataComp += 5;
                if (ue.getEnterType() != null) dataComp += 5;
                if (ue.getRegisteredCapital() != null) dataComp += 5;
                if (ue.getLegalPerson() != null) dataComp += 5;
                if (ue.getOrganizationCode() != null) dataComp += 5;
                if (ue.getBusiness() != null) dataComp += 5;
                if (ue.getEnterType() != null) dataComp += 5;
            }
            if (applyInfos != null && applyInfos.size() > 0) dataComp += 15;
        }
        UserEntity userEntity = userpersonalService.findUserById(uid);//获取用户信息

        if (null != userEntity) {
            if (userEntity.getWorkingLife() != null) dataComp += 5;
            if (userEntity.getMajor() != null) dataComp += 5;
            if (userEntity.getExpectFunctionCategory() != null) dataComp += 5;
            if (userEntity.getEducationalBackground() != null) dataComp += 5;
            if (userEntity.getExpectedSalary() != null) dataComp += 5;
            if (userEntity.getEmail() != null) dataComp += 5;
            if (userEntity.getTel() != null) dataComp += 5;
        }

        return dataComp;
    }



    /**
     * 获取TITLE
     * @param uId
     * @return
     */
    public static String getUserTitle(Integer uId) {
        String title = talentService.findTitleByUidLimitEndTime(uId);
        return title;
    }

    /**
     * 修改资料完整度
     */
    public static Integer getDataComp(UserEntity userEntity,UserpersonalEntity userpersonalEntity,UserenterpriseEntity userenterpriseEntity){
        List<PortalRealNameInfoEntity> applyInfos = userpersonalService.findApplysByUid(userEntity.getId());//获取认证信息
        int dataComp =0;
        if (userpersonalEntity != null) {
            if (userpersonalEntity.getName() != null) dataComp += 5;
            if (userpersonalEntity.getNickName() != null) dataComp += 5;
            if (userpersonalEntity.getHeadImg() != null) dataComp += 5;
            if (userpersonalEntity.getHomeTown() != null) dataComp += 5;
            if (userpersonalEntity.getLocation() != null) dataComp += 10;
            if (userpersonalEntity.getInfo() != null) dataComp += 5;
            if (userpersonalEntity.getOutsourcingprojectType() != null) dataComp += 5;
            if (userpersonalEntity.getParttimeCategoryId() != null) dataComp += 5;
            if (userpersonalEntity.getRecruitmentCategoryId() != null) dataComp += 5;
            if (userpersonalEntity.getSignature() != null) dataComp += 5;
        }

        if (null != userEntity) {
            if (userEntity.getWorkingLife() != null) dataComp += 5;
            if (userEntity.getMajor() != null) dataComp += 5;
            if (userEntity.getExpectFunctionCategory() != null) dataComp += 5;
            if (userEntity.getEducationalBackground() != null) dataComp += 5;
            if (userEntity.getExpectedSalary() != null) dataComp += 5;
            if (userEntity.getEmail() != null) dataComp += 5;
            if (userEntity.getTel() != null) dataComp += 5;
            List<PortalTechnologyEntity> careers = talentService.findByUid(userEntity.getId(), 2);
            List<PortalTechnologyEntity> edus = talentService.findByUid(userEntity.getId(), 3);
            if (careers != null && careers.size() > 0) dataComp += 5;
            if (edus != null && edus.size() > 0) dataComp += 5;
            if (applyInfos != null && applyInfos.size() > 0) {
                for (PortalRealNameInfoEntity p : applyInfos) {
                    if (p.getApplyType() == 1) dataComp += 5;
                    if (p.getApplyType() == 2) dataComp += 5;
                    if (p.getApplyType() == 3) dataComp += 5;
                }
            }
        }

        if (null != userenterpriseEntity) {

            if (userenterpriseEntity.getName() != null) dataComp += 5;
            if (userenterpriseEntity.getEstablishmentTime() != null) dataComp += 5;
            if (userenterpriseEntity.getHeadImg() != null) dataComp += 5;
            if (userenterpriseEntity.getEnterpriseDetail() != null) dataComp += 15;
            if (userenterpriseEntity.getLocation() != null) dataComp += 5;
            if (userenterpriseEntity.getInfo() != null) dataComp += 5;
            if (userenterpriseEntity.getEnterType() != null) dataComp += 5;
            if (userenterpriseEntity.getRegisteredCapital() != null) dataComp += 5;
            if (userenterpriseEntity.getLegalPerson() != null) dataComp += 5;
            if (userenterpriseEntity.getOrganizationCode() != null) dataComp += 5;
            if (userenterpriseEntity.getBusiness() != null) dataComp += 5;
            if (userenterpriseEntity.getEnterType() != null) dataComp += 5;
            if (applyInfos != null && applyInfos.size() > 0) dataComp += 15;
        }

        return dataComp;

    }
}