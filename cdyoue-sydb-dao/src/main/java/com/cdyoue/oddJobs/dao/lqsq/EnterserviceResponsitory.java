package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.PortalIntellectualInnovateEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by tr on 2017/5/16.
 */
public interface EnterserviceResponsitory extends JpaCustomResponsitory<PortalIntellectualInnovateEntity, Integer> {
    @Query("SELECT piie from PortalIntellectualInnovateEntity piie where piie.type=?1")
    Page<PortalIntellectualInnovateEntity> findWithoutKeyword(Integer type, Pageable requestPage);

    @Query("SELECT piie from PortalIntellectualInnovateEntity piie where piie.type=?1 and piie.name like CONCAT('%',?2,'%')")
    Page<PortalIntellectualInnovateEntity> findWithKeyword(Integer type, String q, Pageable requestPage);
}
