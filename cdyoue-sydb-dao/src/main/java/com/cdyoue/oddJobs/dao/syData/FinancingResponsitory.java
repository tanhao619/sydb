package com.cdyoue.oddJobs.dao.syData;

import com.cdyoue.oddJobs.dao.lqsq.JpaCustomResponsitory;
import com.cdyoue.oddJobs.entity.syData.FinancingEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2017/9/20.
 */
public interface FinancingResponsitory extends JpaCustomResponsitory<FinancingEntity, Integer> {
    @Query(value = "select e FROM FinancingEntity e where e.top =1 and e.status = 2 order by e.updateTime desc")
    List<FinancingEntity> getFinancingTop();

    @Query(value = "select e FROM FinancingEntity e where e.id =?1")
    FinancingEntity getFinancingById(Integer id);

}
