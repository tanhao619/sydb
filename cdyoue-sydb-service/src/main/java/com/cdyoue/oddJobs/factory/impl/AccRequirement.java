package com.cdyoue.oddJobs.factory.impl;

import com.cdyoue.oddJobs.dao.lqsq.PortalRequirementMessageResponsitory;
import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.common.message.AuthenticationMessage;
import com.cdyoue.oddJobs.en.MessageEventTypeEnum;
import com.cdyoue.oddJobs.en.MessageMsgTypeEnum;
import com.cdyoue.oddJobs.entity.lgsq.RequirementMessageEntity;
import com.cdyoue.oddJobs.entity.lgsq.OutsourcingProjectEntity;
import com.cdyoue.oddJobs.exception.UnAuthenticationMessageException;
import com.cdyoue.oddJobs.factory.abs.RequiresAbstract;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by liaoyoule on 2017/4/25.
 */
@Component
public class AccRequirement extends RequiresAbstract {
    @Autowired
    PortalRequirementMessageResponsitory portalRequirementMessageResponsitory;

    @Override
    public Page<OutsourcingProjectEntity> getMyRequires(PageRequest pr, String query) {
        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();
        if(currentUserLogin ==null){
            throw new UnAuthenticationMessageException(AuthenticationMessage.USERUNLOGIN);
        }
        Page<RequirementMessageEntity> pageInfo = portalRequirementMessageResponsitory.findByOperaIdAndMsgTypeAndEventType(currentUserLogin.getId(), new Byte(MessageMsgTypeEnum.RequirementAcceptProject.getMsgType()+""), new Byte(MessageEventTypeEnum.REQUIREMENT.getValue()+""),pr);
        List<OutsourcingProjectEntity> pres =    pageInfo.getContent().stream().map(pme -> pme.getRequirementEntity()).collect(Collectors.toList());
        return new PageImpl(pres, pr, pageInfo.getTotalElements());
    }

    @Override
    public Boolean isBehaviorOf(Integer reId) {
        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();

        if(currentUserLogin == null){
            return false;
        }

        Integer id = currentUserLogin.getId();
        Integer count = portalRequirementMessageResponsitory.findByOperaIdAndReIdAndMsgTypeAndEventType(id,reId,new Byte(MessageMsgTypeEnum.RequirementAcceptProject.getMsgType()+""), new Byte(MessageEventTypeEnum.REQUIREMENT.getValue()+""));
        return count != null && count > 0 ? true:false;
    }
}
