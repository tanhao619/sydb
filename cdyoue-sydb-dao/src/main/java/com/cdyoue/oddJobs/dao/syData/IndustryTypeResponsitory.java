package com.cdyoue.oddJobs.dao.syData;

import com.cdyoue.oddJobs.dao.lqsq.JpaCustomResponsitory;
import com.cdyoue.oddJobs.entity.syData.SyIndustryTypeEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/9/18.
 */
public interface IndustryTypeResponsitory extends JpaCustomResponsitory<SyIndustryTypeEntity, Integer> {

    List<SyIndustryTypeEntity> findByType(Integer type);
}
