package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.PortalPricesInfoEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by dengshaojun on 2017/5/23.
 */
public interface PortalPricesInfoResponsitory extends JpaCustomResponsitory<PortalPricesInfoEntity, Integer> {

    @Query("select p from PortalPricesInfoEntity p where p.name = ?1")
    List<PortalPricesInfoEntity> findByName(String name);

}
