package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.PortalActivityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by luanyu on 2017/5/6.
 */
public interface PortalActivityResponsitory extends JpaCustomResponsitory<PortalActivityEntity, Integer> {
    @Query("select distinct qe from PortalActivityEntity qe where qe.id in ?1 and qe.title like ?2")
    Page<PortalActivityEntity> findMyCollectionsListByStrKey(List<Integer> focusActivityIds, String q, Pageable pageable);

    @Modifying
    @Query("UPDATE PortalActivityEntity SET viewCount=viewCount+1 WHERE id=?1")
    void addViewcount(Integer id);

    @Query(value = "SELECT id,title,address,topImg,reviewStatus,updateTime,top FROM lg_portal_activity WHERE reviewStatus=1 and top=1 ORDER BY updateTime DESC LIMIT 4",nativeQuery = true)
    Object[] getActivityBanners();
}
