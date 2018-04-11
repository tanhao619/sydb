package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.PortalRealNameInfoResponsitory;
import com.cdyoue.oddJobs.en.ApplyTypeEnum;
import com.cdyoue.oddJobs.entity.lgsq.PortalRealNameInfoEntity;
import com.cdyoue.oddJobs.service.RealNameInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dengshaojun on 2017/5/12.
 */
@Service
public class RealNameInfoServiceImpl implements RealNameInfoService {

    @Autowired
    private PortalRealNameInfoResponsitory portalRealNameInfoResponsitory;

    @Override
    public PortalRealNameInfoEntity findById(Integer id) {
        return portalRealNameInfoResponsitory.findOne(id);
    }

    @Override
    public Boolean existByUseridAndApplytype(Integer userid, ApplyTypeEnum realname) {
        List<PortalRealNameInfoEntity> portalRealNameInfoEntities = portalRealNameInfoResponsitory.findByUseridAndApplytype(userid, realname.getValue());
        if (portalRealNameInfoEntities == null || portalRealNameInfoEntities.size() < 1) {
            return false;
        }
        return true;
    }
}
