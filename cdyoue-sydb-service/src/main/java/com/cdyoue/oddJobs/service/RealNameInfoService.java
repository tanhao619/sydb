package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.en.ApplyTypeEnum;
import com.cdyoue.oddJobs.entity.lgsq.PortalRealNameInfoEntity;

/**
 * Created by dengshaojun on 2017/5/12.
 */
public interface RealNameInfoService {
    PortalRealNameInfoEntity findById(Integer id);

    Boolean existByUseridAndApplytype(Integer userid, ApplyTypeEnum realname);
}
