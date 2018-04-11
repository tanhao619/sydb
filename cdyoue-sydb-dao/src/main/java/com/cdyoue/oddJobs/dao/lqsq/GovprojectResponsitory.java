package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.PortalGovprojectReportEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

/**
 * Created by tr on 2017/5/22.
 */
public interface GovprojectResponsitory extends JpaCustomResponsitory<PortalGovprojectReportEntity, Integer> {
    @Query("SELECT ppr.createBy from PortalGovprojectReportEntity ppr where ppr.id = ?1")
    UserEntity findUsernameById(Integer id);

    @Query("SELECT ppr from PortalGovprojectReportEntity ppr where ppr.publishTime >= ?1 and ppr.publishTime <= ?2")
    Page<PortalGovprojectReportEntity> findWithTimeFilter(Date startDate, Date endDate, Pageable requestPage);

    @Query("SELECT ppr from PortalGovprojectReportEntity ppr where ppr.publishTime >= ?1 and ppr.publishTime <= ?2 and ppr.title LIKE CONCAT('%',?3,'%')")
    Page<PortalGovprojectReportEntity> findWithTimeFilterAndKeyword(Date startDate, Date endDate, String q, Pageable requestPage);
}
