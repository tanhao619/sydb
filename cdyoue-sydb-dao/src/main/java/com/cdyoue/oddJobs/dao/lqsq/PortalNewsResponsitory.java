package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.PortalNewsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Administrator on 2017/5/6.
 */
public interface PortalNewsResponsitory extends JpaCustomResponsitory<PortalNewsEntity, Integer> {

    @Query("select n from PortalNewsEntity n where n.createBy = ?1")
    Page<PortalNewsEntity> findByUid(Integer userid, Pageable pageable);
}
