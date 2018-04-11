package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.homePage.PortalHomePageEntity;
import com.cdyoue.oddJobs.entity.lgsq.homePage.PortalHomePageInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by liaoyoule on 2017/5/6.
 */
public interface PortalHomePageResponsitory extends  JpaCustomResponsitory<PortalHomePageEntity,Integer> {

    List<PortalHomePageEntity> findByCodeLikeOrderByCode(String concat);

    PortalHomePageEntity findByCode(String code);

    @Query("select phi from  PortalHomePageInfoEntity phi inner join phi.portalHomePage ph where ph.code like ?1 ")
    Page<PortalHomePageInfoEntity> findHomePage(String page, Pageable pr);
    @Query("select phi from  PortalHomePageInfoEntity phi inner join phi.portalHomePage ph where ph.code like ?1 and phi.title like ?2")
    Page<PortalHomePageInfoEntity> findHomePage(String s, String q, Pageable pr);
}
