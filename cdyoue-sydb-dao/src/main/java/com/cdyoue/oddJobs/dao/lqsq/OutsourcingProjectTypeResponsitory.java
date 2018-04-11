package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.common.OutsourcingProjectTypeEntity;

/**
 * Created by liaoyoule on 2017/4/26.
 */
public interface OutsourcingProjectTypeResponsitory extends  JpaCustomResponsitory<OutsourcingProjectTypeEntity,Integer>  {
    OutsourcingProjectTypeEntity findByName(String name);
}
