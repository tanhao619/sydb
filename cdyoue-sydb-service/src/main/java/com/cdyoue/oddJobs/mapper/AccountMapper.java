package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dao.lqsq.UserenterpriseResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.UserpersonalResponsitory;
import com.cdyoue.oddJobs.dto.account.EnterAccountSumary;
import com.cdyoue.oddJobs.dto.account.PersonAccountDetail;
import com.cdyoue.oddJobs.dto.account.PersonAccountSumary;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserenterpriseEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserpersonalEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Administrator on 2017/5/8.
 */
@Component
public class AccountMapper {
    @Autowired
    private UserenterpriseResponsitory userenterpriseResponsitory;

    @Autowired
    private UserpersonalResponsitory userpersonalResponsitory;

    public PersonAccountDetail twoEntityToDto(UserEntity userEntity, UserpersonalEntity personalEntity, List<Integer> expertTypes){
        PersonAccountDetail accountDTO = new PersonAccountDetail();
        BeanUtils.copyProperties(userEntity,accountDTO);
        BeanUtils.copyProperties(personalEntity,accountDTO);
        accountDTO.setUserId(userEntity.getId());
        accountDTO.setExpertType(expertTypes);
        accountDTO.setRole(userEntity.getRole());
        Integer a = userEntity.getExpectFunctionCategory();
        accountDTO.setExpectFunctionCategory(a);
        return accountDTO;
    }

    public UserpersonalEntity dtoToPersonal(PersonAccountDetail accountDTO){
        UserpersonalEntity entity = new UserpersonalEntity();
        BeanUtils.copyProperties(accountDTO,entity);
        return entity;
    }

    public UserEntity dtoToUser(PersonAccountDetail accountDTO){
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(accountDTO,entity);
        return entity;
    }


    public UserenterpriseEntity toEntity( EnterAccountSumary enterAccountSumary,Integer uId) {
        UserenterpriseEntity byUid = userenterpriseResponsitory.findByUid(uId);
        if (null != enterAccountSumary.getInfo() ) byUid.setInfo(enterAccountSumary.getInfo());
        if ( null  != enterAccountSumary.getName()) byUid.setName(enterAccountSumary.getName());
        if (null != enterAccountSumary.getBusiness() ) byUid.setBusiness(enterAccountSumary.getBusiness());
        if (null != enterAccountSumary.getEnterpriseDetail() ) byUid.setEnterpriseDetail(enterAccountSumary.getEnterpriseDetail());
        if (null != enterAccountSumary.getEstablishmentTime() ) byUid.setEstablishmentTime(Timestamp.valueOf(enterAccountSumary.getEstablishmentTime()));
        if (null != enterAccountSumary.getHeadImg() ) byUid.setHeadImg(enterAccountSumary.getHeadImg());
        if (null != enterAccountSumary.getLegalPerson() ) byUid.setLegalPerson(enterAccountSumary.getLegalPerson());
        if (null != enterAccountSumary.getRegisteredCapital() ) byUid.setRegisteredCapital(enterAccountSumary.getRegisteredCapital());
        if (null != enterAccountSumary.getEnterType() ) byUid.setEnterType(enterAccountSumary.getEnterType());
        if (null != enterAccountSumary.getOrganizationCode() ) byUid.setOrganizationCode(enterAccountSumary.getOrganizationCode());
        if (null != enterAccountSumary.getLocation() ) byUid.setLocation(enterAccountSumary.getLocation());
        return byUid;
    }


    public UserpersonalEntity toPAEntity( PersonAccountSumary p,  Integer uId) {
        UserpersonalEntity byUid = userpersonalResponsitory.findByUid(uId);

        if (null != p.getExpectFunctionCategory())
        if (null != p.getHeadImg()) byUid.setHeadImg(p.getHeadImg());
        if (null != p.getHomeTown()) byUid.setHomeTown(p.getHomeTown());
        if (null != p.getInfo()) byUid.setInfo(p.getInfo());
        if (null != p.getIntroduction()) byUid.setIntroduction(p.getIntroduction());
        if (null != p.getLocation()) byUid.setLocation(p.getLocation());
        if (null != p.getName()) byUid.setName(p.getName());
        if (null != p.getNickName()) byUid.setNickName(p.getNickName());
        if (null != p.getOutsourcingprojectType()) byUid.setOutsourcingprojectType(p.getOutsourcingprojectType());
        if (null != p.getParttimeCategoryId()) byUid.setParttimeCategoryId(p.getParttimeCategoryId());
        if (null != p.getRecruitmentCategoryId()) byUid.setRecruitmentCategoryId(p.getRecruitmentCategoryId());

        return byUid;
    }

}
