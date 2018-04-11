package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dto.oauth.ApplyInfoDto;
import com.cdyoue.oddJobs.entity.lgsq.PortalRealNameInfoEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserenterpriseEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserpersonalEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * Created by Tangguang on 2017/6/3.
 */
@Component
public class ApplyInfoMapper {

    public ApplyInfoDto setApplyInfo(final PortalRealNameInfoEntity applyInfo) {
        ApplyInfoDto applyInfoDto = new ApplyInfoDto();
        if(null != applyInfo){
            if(applyInfo.getId() !=0){
                applyInfoDto.setId(applyInfo.getId());
            }
            if(StringUtils.isNotBlank(applyInfo.getFrontImg())){
                applyInfoDto.setFrontImg(applyInfo.getFrontImg());
            }
            if(StringUtils.isNotBlank(applyInfo.getBackImg())){
                applyInfoDto.setBackImg(applyInfo.getBackImg());
            }
            if(StringUtils.isNotBlank(applyInfo.getCardNo())){
                applyInfoDto.setCardNo(applyInfo.getCardNo());
            }
            if(StringUtils.isNotBlank(applyInfo.getReviewReason())){
                applyInfoDto.setReviewReason(applyInfo.getReviewReason());
            }
            if(null != applyInfo.getReviewStatus()){
                applyInfoDto.setReviewStatus(Integer.parseInt(applyInfo.getReviewStatus()+""));
            }
            if(null != applyInfo.getApplyType()){
                applyInfoDto.setType(applyInfo.getApplyType());
            }
            if(null != applyInfo.getLook()){
                applyInfoDto.setLook(applyInfo.getLook());
            }
        }

        return applyInfoDto;
    }
}
