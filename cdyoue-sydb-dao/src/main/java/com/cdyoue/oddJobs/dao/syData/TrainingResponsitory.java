package com.cdyoue.oddJobs.dao.syData;

import com.cdyoue.oddJobs.dao.lqsq.JpaCustomResponsitory;
import com.cdyoue.oddJobs.entity.syData.SyTrainingEntity;

import java.util.List;

/**
 * Created by sky on 2017/9/23.
 */
public interface TrainingResponsitory extends JpaCustomResponsitory<SyTrainingEntity, Integer> {
    List<SyTrainingEntity> findTop4ByTopOrderByUpdateTimeDesc(Integer top);
}
