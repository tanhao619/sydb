package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.*;
import com.cdyoue.oddJobs.dto.account.*;
import com.cdyoue.oddJobs.entity.lgsq.*;
import com.cdyoue.oddJobs.entity.lgsq.common.OutsourcingProjectTypeEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserenterpriseEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserpersonalEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.exception.ParamterErrorException;
import com.cdyoue.oddJobs.mapper.AccountMapper;
import com.cdyoue.oddJobs.service.AccountsService;
import com.cdyoue.oddJobs.utils.DataIntegrityUtils;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dengshaojun on 2017/5/8.
 */
@Service
public class AccountsServiceImpl implements AccountsService {

    @Autowired
    private UserResponsitory userResponsitory;
    @Autowired
    private UserenterpriseResponsitory userenterpriseResponsitory;
    @Autowired
    private UserpersonalResponsitory userpersonalResponsitory;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private PortalRealNameInfoResponsitory portalRealNameInfoResponsitory;

    @Autowired
    private OutsourcingProjectTypeResponsitory outsourcingProjectTypeResponsitory;

    @Autowired
    private FunctioncategoryResponsitory functioncategoryResponsitory;

    @Autowired
    private MessageResponsitory messageResponsitory;

    @Autowired
    private TalentResponsitory talentResponsitory;


    @Autowired
    private UserRoleResponsitory userRoleResponsitory;


    @Autowired
    private RoleResponsitory roleResponsitory;

    @Override
    @Transactional
    public void addEnterprise(EnterRegisterInfo enterInfo) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserType(1);//用于匹配用户属于：0个人，1企业
        userEntity.setRole(1);//角色：0个人用户 1 企业用户 2超级管理员
        userEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        userEntity.setEmail(enterInfo.getEmail());
        userEntity.setPassword(enterInfo.getPassword());
        UserenterpriseEntity userenterpriseEntity = new UserenterpriseEntity();
        userenterpriseEntity.setName(enterInfo.getName());
        userEntity.setUserenterpriseEntity(userenterpriseEntity);
        userResponsitory.save(userEntity);
    }

    @Override
    @Transactional
    public void addPerson(PersonRegisterInfo personInfo) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserType(0);//用于匹配用户属于：0个人，1企业
        userEntity.setRole(0);//角色：0个人用户 1 企业用户 2超级管理员
        userEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        userEntity.setEmail(personInfo.getEmail());
        userEntity.setPassword(personInfo.getPassword());
        userEntity.setUserName(personInfo.getUsername());
        UserpersonalEntity userpersonalEntity = new UserpersonalEntity();
        userEntity.setUserpersonalEntity(userpersonalEntity);
        userResponsitory.save(userEntity);
    }

    @Override
    public boolean existByEmail(String email) {
        UserEntity userEntity = userResponsitory.findByEmail(email);
        if (userEntity == null) {
            return false;
        }
        return true;
    }

    @Override
    public UserEntity findByid(Integer id) {
        UserEntity userEntity = userResponsitory.findOne(id);
        return userEntity;
    }

    @Override
    @Transactional
    public void updatePwd(UserEntity userEntity) {
        userResponsitory.save(userEntity);
    }

    @Override//获取我的企业账户信息
    public EnterAccountDetail getMyEnterpriseInfo(int uId) {
        EnterAccountDetail accountDTO = new EnterAccountDetail();
        UserenterpriseEntity entity = userenterpriseResponsitory.findOneByUid(uId);
        UserEntity entity1 = userResponsitory.findOne(uId);
        BeanUtils.copyProperties(entity, accountDTO);
        if (null != entity.getEstablishmentTime()) {
            accountDTO.setEstablishmentTime(entity.getEstablishmentTime().toString());
        }
        accountDTO.setUserId(entity.getUserId());
        accountDTO.setEnterType(entity.getEnterType());
//        accountDTO.setDataComp(DataIntegrityUtils.getDataComp(entity.getUserId(),entity1.getUserType()));
        accountDTO.setCategoryId(entity1.getExpectFunctionCategory());
        if (null != entity1) {
            BigDecimal itd = entity1.getIntegrityDegree();
            accountDTO.setDataComp(itd == null ? 0 : itd.intValue());
            if (StringUtils.isNotBlank(entity1.getEmail())) {
                accountDTO.setEmail(entity1.getEmail());
            }
            if (StringUtils.isNotBlank(entity1.getTel())) {
                accountDTO.setTel(entity1.getTel());
            }
        }
        return accountDTO;
    }

    @Override//获取我的个人账户信息
    public PersonAccountDetail getMyPersonInfo(Integer uId) {
        PersonAccountDetail personDTO = new PersonAccountDetail();
        UserpersonalEntity entity = userpersonalResponsitory.findOneByUid(uId);
        UserEntity entity1 = userResponsitory.findOne(uId);
        List<PortalRealNameInfoEntity> expertTypes1 = portalRealNameInfoResponsitory.findByUseridAndReviewstatus(uId, new Byte("1"));
        List<Integer> expertType = new ArrayList<>();
        expertTypes1.forEach(p ->
                expertType.add(p.getApplyType())
        );
//        List<Integer> expertType = portalRealNameInfoResponsitory.findByUseridAndReviewstatus(uId, new Byte("2"));
        personDTO = accountMapper.twoEntityToDto(entity1, entity, expertType);
        List<PortalMessageEntity> myAttentionNum = messageResponsitory.findMyAttentionNum(uId, 3, 1);

        PersonAccountDetail personAccountDetail = setPersonalInfo(personDTO, entity, entity1);
        personAccountDetail.setAttentionNum(myAttentionNum.size());//关注数
        if (null != entity.getDataComp()) {
            personAccountDetail.setIntegrityDegree(entity1.getIntegrityDegree());//资料完整度
//            personAccountDetail.setIntegrityDegree(entity1.getIntegrityDegree().intValue());
        }
        if (personDTO.getRole() == 2) {
            List<UserRoleEntity> ures = userRoleResponsitory.findByUserId(uId);
            List<String> mrns = ures.stream().map(userRoleEntity -> {
                        RoleEntity re = userRoleEntity.getRole();
                        if (re != null) {
                            return re.getName();
                        }
                        return null;
                    }
            ).collect(Collectors.toList());
            personAccountDetail.setMenuRolesName(mrns);
        }

        return personAccountDetail;
    }


    @Override//编辑我的企业账户信息
    public void updateMyEnterAccount(EnterAccountSumary enterAccountSumary, Integer uId) {
//        UserenterpriseEntity entity = userenterpriseResponsitory.findByUid(uId);
        UserenterpriseEntity  entity = accountMapper.toEntity(enterAccountSumary,uId);
        UserEntity userEntity = userResponsitory.findOne(uId);
//
        if (0 != enterAccountSumary.getExpectFunctionCategory ()) userEntity.setExpectFunctionCategory(enterAccountSumary.getExpectFunctionCategory());
//        if (null != enterAccountSumary.getEstablishmentTime()) entity.setEstablishmentTime(Timestamp.valueOf(enterAccountSumary.getEstablishmentTime()));
        userEntity.setIntegrityDegree(new BigDecimal(DataIntegrityUtils.getDataComp(userEntity, null, entity)));
        userEntity.setTel(enterAccountSumary.getTel());
        userEntity.setEmail(enterAccountSumary.getEmail());
        entity.setEnterpriseDetail(enterAccountSumary.getEnterpriseDetail());
        userResponsitory.save(userEntity);
        userenterpriseResponsitory.save(entity);
    }

    @Override
    public void updateMyEnterAccount(EnterEditRequest request, Integer uId) {
        UserenterpriseEntity  entity = userenterpriseResponsitory.findByUid(uId);
        UserEntity userEntity = userResponsitory.findOne(uId);
        userEntity.setTel(request.getTel());
        userEntity.setEmail(request.getEmail());
        entity.setName(request.getName());
        entity.setEnterpriseDetail(request.getEnterpriseDetail());
        userResponsitory.save(userEntity);
        userenterpriseResponsitory.save(entity);
    }

    @Override//编辑我的个人账户信息
    public void updateMyPersonAccount(PersonAccountSumary personAccountSumary, Integer uId) {
//        UserpersonalEntity entity = userpersonalResponsitory.findByUid(uId);
        UserEntity userEntity = userResponsitory.findOne(uId);

        UserpersonalEntity  entity = accountMapper.toPAEntity(personAccountSumary,uId);
        if (null != personAccountSumary.getAge()) userEntity.setAge(personAccountSumary.getAge());
        if (null != personAccountSumary.getTel()) userEntity.setTel(personAccountSumary.getTel());
        if (null != personAccountSumary.getWorkingLife()) userEntity.setWorkingLife(personAccountSumary.getWorkingLife());
        if (null != personAccountSumary.getSex()) userEntity.setSex(personAccountSumary.getSex());
        if (null != personAccountSumary.getExpectFunctionCategory()) userEntity.setExpectFunctionCategory(personAccountSumary.getExpectFunctionCategory());
//        BeanUtils.copyProperties(personAccountSumary, entity);
        entity.setDataComp(DataIntegrityUtils.getDataComp(userEntity,entity,null));
        userEntity.setExpectFunctionCategory(personAccountSumary.getExpectFunctionCategory());
        userEntity.setIntegrityDegree(new BigDecimal(DataIntegrityUtils.getDataComp(userEntity, entity, null)));
        userpersonalResponsitory.save(entity);
        userResponsitory.save(userEntity);

    }

    @Override
    public void applyQulification(EnterQulification enterQulification) {
        Integer enterId = SecurityUtils.getCurrentUserLogin().getId();
        PortalRealNameInfoEntity prie = new PortalRealNameInfoEntity();
        prie.setUserId(enterId);
        prie.setApplyType(1);
        prie.setCardNo(enterQulification.getCodeNo());
        prie.setReviewStatus((byte) 0);
        portalRealNameInfoResponsitory.save(prie);

        UserenterpriseEntity upe = userenterpriseResponsitory.findByUid(enterId);
        upe.setBusinessLicenseUrl(enterQulification.getCertUrl());
        userenterpriseResponsitory.saveAndFlush(upe);

    }

    @Override
    @Transactional
    public boolean deleteExperience(Integer id) {
        Integer b = 0;
        int uId = SecurityUtils.getCurrentUserLogin().getId();
        PortalTechnologyEntity portalTechnologyEntity = talentResponsitory.findByUserIdAndId(uId, id);
        if (null != portalTechnologyEntity) {
            b = talentResponsitory.deleteById(id);
            UserEntity userEntity = userResponsitory.findOne(uId);
            userEntity.setIntegrityDegree(new BigDecimal(DataIntegrityUtils.getDataComp(uId, userEntity.getUserType())));
            userResponsitory.save(userEntity);
        } else {
            throw new ParamterErrorException("参数错误");
        }
        if (b != 0) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public void updatePersonAccount(PersonAccountSumary personAccountSumary, Integer id) {
        UserEntity ue = userResponsitory.findOne(id);
        if (ue == null) {
            throw new NotFoundEntityException();
        }
        personAccountSumary.setEmail(ue.getEmail());
        personAccountSumary.setTel(ue.getTel());
        this.updateMyPersonAccount(personAccountSumary, id);
        userpersonalResponsitory.updateDataComp(DataIntegrityUtils.countDataComp(personAccountSumary), id);
    }


    private PersonAccountDetail setPersonalInfo(PersonAccountDetail personDTO, UserpersonalEntity entity, UserEntity entity1) {
        PersonAccountDetail personAccountDetail = new PersonAccountDetail();
        BeanUtils.copyProperties(personDTO, personAccountDetail);
        //获取外包意向
        OutsourcingProjectTypeEntity out = new OutsourcingProjectTypeEntity();
        if (null != entity.getOutsourcingprojectType()) {
            out = outsourcingProjectTypeResponsitory.findOne(entity.getOutsourcingprojectType());
        }
        //获取兼职意向
        FunctioncategoryEntity par = new FunctioncategoryEntity();
        if (null != entity.getParttimeCategoryId()) {
            par = functioncategoryResponsitory.findOne(entity.getParttimeCategoryId());
        }
        //获取求职意向
        FunctioncategoryEntity rec = new FunctioncategoryEntity();
        if (null != entity.getRecruitmentCategoryId()) {

            rec = functioncategoryResponsitory.findOne(entity.getRecruitmentCategoryId());
        }
        //获取行业详情
        FunctioncategoryEntity exc = new FunctioncategoryEntity();
        if (null != entity1.getExpectFunctionCategory()) {
            exc = functioncategoryResponsitory.findOne(entity1.getExpectFunctionCategory());
        }
        if (out != null && null != out.getName()) {
            personAccountDetail.setOutsourcingprojectInfo(out.getName());//设置外包
        }
        if (par != null && null != par.getName()) {
            personAccountDetail.setParttimeCategoryInfo(par.getName());//兼职意向
        }
        if (rec != null && null != rec.getName()) {
            personAccountDetail.setRecruitmentCategoryInfo(rec.getName());//求职意向
        }
        if (exc != null && null != exc.getName()) {
            personAccountDetail.setExpectFunctionCategoryInfo(exc.getName());//所在行业
        }
        return personAccountDetail;
    }
}
