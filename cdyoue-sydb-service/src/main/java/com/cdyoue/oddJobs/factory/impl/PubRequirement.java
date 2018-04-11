package com.cdyoue.oddJobs.factory.impl;

import com.cdyoue.oddJobs.dao.lqsq.OutsourcingProjectResponsitory;
import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.common.message.AuthenticationMessage;
import com.cdyoue.oddJobs.entity.lgsq.OutsourcingProjectEntity;
import com.cdyoue.oddJobs.exception.UnAuthenticationMessageException;
import com.cdyoue.oddJobs.factory.abs.RequiresAbstract;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

/**
 * Created by liaoyoule on 2017/4/25.
 */
@Component
public class PubRequirement extends RequiresAbstract {
    @Autowired
    private OutsourcingProjectResponsitory requirementResponsitory;
    @Override
    public Page<OutsourcingProjectEntity> getMyRequires(PageRequest pr, String query) {
        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();
        if(currentUserLogin ==null){
            throw new UnAuthenticationMessageException(AuthenticationMessage.USERUNLOGIN);
        }

        Integer userId = currentUserLogin.getId();

        if(StringUtils.isNotBlank(query)){
          return   requirementResponsitory.findMyRequires(userId,"%"+query+"%", pr);
        }

        return requirementResponsitory.findMyRequires(userId, pr);
    }

    @Override
    @Deprecated
    public Boolean isBehaviorOf(Integer reId) {
        return null;
    }
}
