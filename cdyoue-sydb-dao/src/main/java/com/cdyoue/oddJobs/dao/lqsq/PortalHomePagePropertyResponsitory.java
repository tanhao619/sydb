package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.homePage.PortalHomePagePropertyEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by liaoyoule on 2017/5/10.
 */
public interface PortalHomePagePropertyResponsitory extends JpaCustomResponsitory<PortalHomePagePropertyEntity,Integer> {
    @Modifying
    @Query("delete from PortalHomePagePropertyEntity phpe where phpe.pageInfoId = ?1")
    void deleteByPid(Integer id);
}
