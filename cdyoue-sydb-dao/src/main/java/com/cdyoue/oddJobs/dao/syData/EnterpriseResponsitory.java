package com.cdyoue.oddJobs.dao.syData;

import com.cdyoue.oddJobs.dao.lqsq.JpaCustomResponsitory;
import com.cdyoue.oddJobs.entity.syData.SyEnterpriseEntity;

import java.util.List;

/**
 * Created by sky on 2017/9/23.
 */
public interface EnterpriseResponsitory extends JpaCustomResponsitory<SyEnterpriseEntity, Integer> {
    List<SyEnterpriseEntity> findTop4ByTopOrderByUpdateTimeDesc(Integer top);
}
