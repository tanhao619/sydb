package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.PortalRealNameInfoResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.UserResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.UserenterpriseResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.UserpersonalResponsitory;
import com.cdyoue.oddJobs.dto.Reason;
import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.common.message.AuthenticationMessage;
import com.cdyoue.oddJobs.dto.oauth.ApplyInfoDto;
import com.cdyoue.oddJobs.dto.oauth.RealNameApplyInfo;
import com.cdyoue.oddJobs.en.ApplyInfoTypeEnum;
import com.cdyoue.oddJobs.en.ApplyTypeEnum;
import com.cdyoue.oddJobs.entity.lgsq.PortalRealNameInfoEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserenterpriseEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserpersonalEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.exception.ParamterErrorException;
import com.cdyoue.oddJobs.exception.UnAuthenticationMessageException;
import com.cdyoue.oddJobs.mapper.ApplyInfoMapper;
import com.cdyoue.oddJobs.service.AuthService;
import com.cdyoue.oddJobs.utils.DataIntegrityUtils;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dengshaojun on 2017/5/9.
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private PortalRealNameInfoResponsitory portalRealNameInfoResponsitory;

    @Autowired
    private UserResponsitory userResponsitory;

    @Autowired
    private ApplyInfoMapper applyInfoMapper;

    @Autowired
    private UserpersonalResponsitory userpersonalResponsitory;

    @Autowired
    private UserenterpriseResponsitory userenterpriseResponsitory;


    @Override
    @Transactional
    public void saveRealNameInfo(Integer userid, RealNameApplyInfo realNameApplyInfo, ApplyTypeEnum type) {
        PortalRealNameInfoEntity portalRealNameInfoEntity = new PortalRealNameInfoEntity();
        // 当前登录个人或者企业的id
        portalRealNameInfoEntity.setUserId(userid);
        // 只有实名才能有，如果是个人的话需要在userpersonal加真实姓名
        if (realNameApplyInfo != null) {

            UserEntity userEntity = userResponsitory.findOne(userid);
            UserpersonalEntity userpersonalEntity = userEntity.getUserpersonalEntity();
            UserenterpriseEntity userenterpriseEntity = userEntity.getUserenterpriseEntity();
            if (userpersonalEntity != null) {
                userpersonalEntity.setName(realNameApplyInfo.getName());
                userResponsitory.save(userEntity);
            } else if (userenterpriseEntity != null) {
                userenterpriseEntity.setName(realNameApplyInfo.getName());
                userenterpriseResponsitory.save(userenterpriseEntity);
            }
            portalRealNameInfoEntity.setCardNo(realNameApplyInfo.getCardNo());
            portalRealNameInfoEntity.setFrontImg(realNameApplyInfo.getFrontImg());
            portalRealNameInfoEntity.setBackImg(realNameApplyInfo.getBackImg());

        }

        // 审核状态：0 待审 1审核通过 2审核失败
        portalRealNameInfoEntity.setReviewStatus(new Byte("0"));
        // 认证类型：1实名，2大咖，3导师
        portalRealNameInfoEntity.setApplyType(type.getValue());
        portalRealNameInfoEntity.setLook(0);
        portalRealNameInfoResponsitory.save(portalRealNameInfoEntity);
    }

    @Override
    @Transactional
    public void authApprove(Integer id, Reason reason) {
        PortalRealNameInfoEntity portalRealNameInfoEntity = portalRealNameInfoResponsitory.findOne(id);
        // 审核状态：0 待审 1审核通过 2审核失败
        portalRealNameInfoEntity.setReviewStatus(new Integer(1).byteValue());
        // 审核时间
        portalRealNameInfoEntity.setReviewTime(new Timestamp(System.currentTimeMillis()));
        // 审核人员：当前登录后台系统管理员的id
        portalRealNameInfoEntity.setReviewUserId(SecurityUtils.getCurrentUserLogin().getId());
        portalRealNameInfoEntity.setReviewReason(reason.getReason());
        //修改资料完整度
        UserEntity userEntity = userResponsitory.findOne(portalRealNameInfoEntity.getUserId());
        BigDecimal integrityDegree = userEntity.getIntegrityDegree();
        int i=0;
        if (userEntity.getUserType() == 0) i = (integrityDegree ==null ? 0: integrityDegree.intValue()) +5;
        if(userEntity.getUserType() == 1) i =(integrityDegree ==null ? 0: integrityDegree.intValue()) +15;
        userEntity.setIntegrityDegree(new BigDecimal(i));
//        userEntity.setIntegrityDegree(new BigDecimal(DataIntegrityUtils.getDataComp(userEntity.getId(),userEntity.getUserType()+5)));
        userResponsitory.save(userEntity);

        portalRealNameInfoResponsitory.save(portalRealNameInfoEntity);

    }

    @Override
    public void authReject(Integer id, Reason reason) {
        PortalRealNameInfoEntity portalRealNameInfoEntity = portalRealNameInfoResponsitory.findOne(id);
        // 审核状态：0 待审 1审核通过 2审核失败
        portalRealNameInfoEntity.setReviewStatus(new Integer(2).byteValue());
        // 审核时间
        portalRealNameInfoEntity.setReviewTime(new Timestamp(System.currentTimeMillis()));
        // 审核人员：当前登录后台系统管理员的id
        portalRealNameInfoEntity.setReviewUserId(SecurityUtils.getCurrentUserLogin().getId());
        portalRealNameInfoEntity.setReviewReason(reason.getReason());
        portalRealNameInfoResponsitory.save(portalRealNameInfoEntity);

    }

    @Override
    public void updateRealNameInfo(Integer id, RealNameApplyInfo realNameApplyInfo) {
        PortalRealNameInfoEntity portalRealNameInfoEntity = portalRealNameInfoResponsitory.findOne(id);
        // 如果是个人的话需要在userpersonal加真实姓名
        UserEntity userEntity = userResponsitory.findOne(portalRealNameInfoEntity.getUserId());
        UserpersonalEntity userpersonalEntity = userEntity.getUserpersonalEntity();
        UserenterpriseEntity userenterpriseEntity = userEntity.getUserenterpriseEntity();
        if (userpersonalEntity != null) {
            userpersonalEntity.setName(realNameApplyInfo.getName());
            //userResponsitory.save(userEntity);
            userpersonalResponsitory.save(userpersonalEntity);
        } else if (userenterpriseEntity != null) {
            userenterpriseEntity.setName(realNameApplyInfo.getName());
            userenterpriseResponsitory.save(userenterpriseEntity);
        }

        portalRealNameInfoEntity.setCardNo(realNameApplyInfo.getCardNo());
        portalRealNameInfoEntity.setFrontImg(realNameApplyInfo.getFrontImg());
        portalRealNameInfoEntity.setBackImg(realNameApplyInfo.getBackImg());
        portalRealNameInfoEntity.setReviewStatus(new Integer(0).byteValue());
        portalRealNameInfoResponsitory.save(portalRealNameInfoEntity);
    }

    @Override
    public ApplyInfoDto getApplyInfo(String type) {
        ApplyInfoDto appleInfoDto = new ApplyInfoDto();
        UserMine userMine = SecurityUtils.getCurrentUserLogin();
        if(null == userMine){
            throw new UnAuthenticationMessageException(AuthenticationMessage.USERUNLOGIN);
        }

        Integer typeNum = getTypeNum(type);
        Integer userId = userMine.getId();
        PortalRealNameInfoEntity portalRealNameInfoEntity=  portalRealNameInfoResponsitory.findByUserIdAndApplyType(userId,typeNum);
        if(null == portalRealNameInfoEntity){
            throw new NotFoundEntityException("没有数据");
        }
        appleInfoDto = applyInfoMapper.setApplyInfo(portalRealNameInfoEntity);
        if (userMine.getUserType() == 0){
            UserpersonalEntity userpersonalEntity = userpersonalResponsitory.findByUid(userMine.getId());
            if(null != userpersonalEntity){
                appleInfoDto.setName(userpersonalEntity.getName());
            }

        }else if(userMine.getUserType() == 1){
            UserenterpriseEntity userenterpriseEntity = userenterpriseResponsitory.findByUid(userMine.getId());
            if(null != userenterpriseEntity){
                appleInfoDto.setName(userenterpriseEntity.getName());
            }
        }
        return appleInfoDto;
    }

    @Override
    public List<ApplyInfoDto> getIdentificationInfo(Integer id) {
        List<PortalRealNameInfoEntity> prnes = portalRealNameInfoResponsitory.findByUserId(id);
        if(prnes.size() == 0){
            throw new NotFoundEntityException();
        }
        return prnes.stream().map(pri -> applyInfoMapper.setApplyInfo(pri)).collect(Collectors.toList());
    }

    @Override
    public void updLook(Integer id) {
        PortalRealNameInfoEntity entity = portalRealNameInfoResponsitory.findOne(id);
        entity.setLook(1);
        portalRealNameInfoResponsitory.save(entity);
    }

    private Integer getTypeNum(String type){
        Integer typeNum = null;
        if (type.equals(ApplyInfoTypeEnum.REALNAME.getApplyKey())){
            typeNum = ApplyInfoTypeEnum.REALNAME.getValue();

        }else if(type.equals(ApplyInfoTypeEnum.DAKA.getApplyKey())){
            typeNum =ApplyInfoTypeEnum.DAKA.getValue();

        }else if(type.equals(ApplyInfoTypeEnum.MENTOR.getApplyKey())){
            typeNum=ApplyInfoTypeEnum.MENTOR.getValue();
        }else {
            throw new ParamterErrorException(type);
        }
        return typeNum;
    }
}
