package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.homePage.PortalHomePageInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by liaoyoule on 2017/5/10.
 */
public interface PortalHomePageInfoRespository extends  JpaCustomResponsitory<PortalHomePageInfoEntity,Integer> {

    @Query("select ppi from PortalHomePageInfoEntity ppi where ppi.id = ?1 and ppi.portalHomePage.id =?2")
    PortalHomePageInfoEntity findByIdAndPid(Integer id, int i);
    @Query("select ppi from PortalHomePageInfoEntity ppi where  ppi.portalHomePage.id =?1 and ppi.sort < ?2")
    Page<PortalHomePageInfoEntity> findByPidAndSortLessThan(int i, Double sort, Pageable pr);
    @Query("select ppi from PortalHomePageInfoEntity ppi where  ppi.portalHomePage.id =?1 and ppi.sort > ?2")
    Page<PortalHomePageInfoEntity> findByPidAndSortGreaterThan(int i, Double sort, Pageable pr);
}
