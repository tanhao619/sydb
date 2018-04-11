package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.PortalBigProjectEntity;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by luanyu on 2017/5/20.
 */
public interface BigProjectRepository extends JpaCustomResponsitory<PortalBigProjectEntity, Integer>{
    @Query("SELECT id FROM PortalBigProjectEntity WHERE isTop=1")
     String getTopBigProjectId();

    @Query("SELECT p1 FROM PortalBigProjectEntity p1 WHERE p1.createTime = (select max(p2.createTime) from PortalBigProjectEntity p2)")
    PortalBigProjectEntity getLastBigProjectId();
}
