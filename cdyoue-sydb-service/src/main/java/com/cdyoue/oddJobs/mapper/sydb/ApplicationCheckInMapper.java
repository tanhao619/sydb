package com.cdyoue.oddJobs.mapper.sydb;

import com.cdyoue.oddJobs.dao.lqsq.UserenterpriseResponsitory;
import com.cdyoue.oddJobs.dto.zlcy.ApplicationCheckInDetail;
import com.cdyoue.oddJobs.dto.zlcy.ApplicationCheckInMini;
import com.cdyoue.oddJobs.entity.lgsq.user.UserenterpriseEntity;
import com.cdyoue.oddJobs.entity.syData.SyApplicationCheckInMessageEntity;
import com.cdyoue.oddJobs.entity.syData.SyApplicationCheckinView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * Created by dengshaojun on 2017/10/12.
 */
@Component
public class ApplicationCheckInMapper {

    @Autowired
    private UserenterpriseResponsitory userenterpriseResponsitory;

    public ApplicationCheckInMini viewToMini(SyApplicationCheckinView view) {
        ApplicationCheckInMini mini = new ApplicationCheckInMini();
        mini.setId(view.getId());
        mini.setCreateEnter(view.getEnterName());
        mini.setCreateTime(view.getCreateTime().getTime());
        mini.setStatus(view.getStatus().intValue());
        return mini;
    }

    public ApplicationCheckInDetail entityToDetail(SyApplicationCheckInMessageEntity entity) {
        ApplicationCheckInDetail detail = new ApplicationCheckInDetail();
        detail.setId(entity.getId());
        UserenterpriseEntity userenterpriseEntity = userenterpriseResponsitory.findByUid(entity.getEnterpriseUserId());
        if (userenterpriseEntity != null) {
            detail.setCreateEnter(userenterpriseEntity.getName());
        }
        detail.setEnterpriseLogo(entity.getEnterpriseLogo());
        detail.setEnterpriseInfo(entity.getEnterpriseInfo());
        detail.setEnterpriseUrl(entity.getEnterpriseUrl());
        detail.setContactPeople(entity.getContactPeople());
        detail.setContactNumber(entity.getContactNumber());
        return detail;
    }
}
