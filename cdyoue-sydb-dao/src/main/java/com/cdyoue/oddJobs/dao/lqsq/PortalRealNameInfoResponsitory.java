package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.PortalRealNameInfoEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2017/5/6.
 */
public interface PortalRealNameInfoResponsitory extends JpaCustomResponsitory<PortalRealNameInfoEntity, Integer> {

    @Query("select p from PortalRealNameInfoEntity p where p.userId = ?1 and p.applyType = ?2")
    List<PortalRealNameInfoEntity> findByUseridAndApplytype(Integer userid, Integer applytypevalue);

    @Query("select p from PortalRealNameInfoEntity p where p.userId = ?1 and p.reviewStatus = ?2")
    List<PortalRealNameInfoEntity> findByUseridAndReviewstatus(Integer userId, Byte rereviewStatus);

    @Query("select p.applyType from PortalRealNameInfoEntity p where p.userId = ?1 and p.reviewStatus = ?2")
    List<Integer> findCertsByUseridAndReviewstatus(Integer userId, Byte rereviewStatus);

    List<PortalRealNameInfoEntity> findByUserIdAndReviewStatus(int id, Byte i);

    @Query("select p from PortalRealNameInfoEntity p where p.userId=?1")
    List<PortalRealNameInfoEntity> findByUserId(Integer id);

    PortalRealNameInfoEntity findByUserIdAndApplyType(Integer userId, Integer applyType);

    @Query("select p from PortalRealNameInfoEntity p where  p.userId=?1 and p.reviewStatus =?2 and p.applyType = ?3")
    PortalRealNameInfoEntity findAyylyInfo(Integer id, Byte aByte, int i);
    @Query("select p from PortalRealNameInfoEntity p where p.userId=?1 and p.reviewStatus = ?2 ")
    List<PortalRealNameInfoEntity> findByUserIdAndStatus(Integer id,Byte rereviewStatus);
}
