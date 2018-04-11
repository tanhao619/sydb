package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.PortalAreaServiceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Administrator on 2017/5/6.
 */
public interface PortalAreaServiceResponsitory extends JpaCustomResponsitory<PortalAreaServiceEntity, Integer> {
    @Query("select pas from PortalAreaServiceEntity pas where pas.areaType =?1 and pas.name like ?2")
    Page<PortalAreaServiceEntity> getPortalAreaServicePage(Integer type,String q,Pageable requestPage);

    @Query("select pas from PortalAreaServiceEntity pas where pas.name like ?1")
    Page<PortalAreaServiceEntity> getPortalAreaServicePageAll(String q,Pageable requestPage);

    @Query(value = "SELECT id,name,price,topImg,address,reviewStatus,updateTime,top FROM lg_portal_area_service WHERE reviewStatus=1 and top=1 ORDER BY updateTime DESC LIMIT 4",nativeQuery = true)
    Object[] getAreaBanners();

    @Modifying
    @Query("UPDATE PortalAreaServiceEntity SET viewCount=viewCount+1 WHERE id=?1")
    void addViewCount(Integer id);
}
