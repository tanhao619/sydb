package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.PortalProjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Administrator on 2017/5/6.
 */
public interface PortalProjectResponsitory extends JpaCustomResponsitory<PortalProjectEntity, Integer> {
    Page<PortalProjectEntity> findByCreateBy(Integer userId, Pageable pageRequest);

    Page<PortalProjectEntity> findByCreateByAndNameLike(Integer userId, String q, Pageable pageRequest);

    @Query("SELECT ppe FROM PortalProjectEntity ppe where ppe.reviewStatus = ?1")
    Page<PortalProjectEntity> findByStatusFilter(Byte statusFilter, Pageable requestPage);

    @Query("SELECT ppe FROM PortalProjectEntity ppe where ppe.name  LIKE CONCAT('%',?1,'%') AND reviewStatus = ?2")
    Page<PortalProjectEntity> findByKeywordAndStatus(String q, Byte statusFilter, Pageable requestPage);
}
