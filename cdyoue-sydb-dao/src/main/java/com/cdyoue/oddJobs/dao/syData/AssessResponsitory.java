package com.cdyoue.oddJobs.dao.syData;

import com.cdyoue.oddJobs.dao.lqsq.JpaCustomResponsitory;
import com.cdyoue.oddJobs.entity.syData.SyPortalAssessEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Tanhao on 2017/9/22.
 */
public interface AssessResponsitory extends JpaCustomResponsitory<SyPortalAssessEntity, Integer> {

    @Query(nativeQuery = true,value = "select * from sy_portal_assess s where s.id = ?1")
    SyPortalAssessEntity getDetail(Integer id);

    @Query("SELECT sa FROM SyPortalAssessEntity sa WHERE sa.brandName LIKE ?1 or sa.patentNum LIKE ?1 or sa.assessProject LIKE ?1")
    Page<SyPortalAssessEntity> findListByName(String q, Pageable requestPage);
}
