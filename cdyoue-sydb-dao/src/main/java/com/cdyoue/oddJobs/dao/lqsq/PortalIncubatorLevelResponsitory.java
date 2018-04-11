package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.PortalIncubatorLevelEntity;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Administrator on 2017/5/11.
 */
public interface PortalIncubatorLevelResponsitory extends JpaCustomResponsitory<PortalIncubatorLevelEntity,Integer>{
    @Query("select p.id FROM PortalIncubatorLevelEntity p WHERE p.name =?1")
    Integer findLevelId(String level);
}
