package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.PortalInvestorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by tr on 2017/5/6.
 */
public interface PortalInvestorResponsitory extends JpaCustomResponsitory<PortalInvestorEntity, Integer> {

    @Query("SELECT pie FROM PortalInvestorEntity pie WHERE pie.name LIKE CONCAT('%',?1,'%') OR pie.tel LIKE CONCAT('%',?1,'%') or pie.orgName LIKE CONCAT('%',?1,'%') ")
    Page<PortalInvestorEntity> findPortalInvestorEntitysWithQ(String q, Pageable requestPage);

    @Query(value = "SELECT * FROM (SELECT name, info as infoAndDept, 1 as itype, publishTime FROM lg_portal_project UNION SELECT name, deptName as infoAndDept, 2 as itype, createTime as publishTime from lg_portal_investor) as t1 order by publishTime DESC LIMIT 0,12", nativeQuery = true)
    Object[] getInvestAndFinNews();
}
