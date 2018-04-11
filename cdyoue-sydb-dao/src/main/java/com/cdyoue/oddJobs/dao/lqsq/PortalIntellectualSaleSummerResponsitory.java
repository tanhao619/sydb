package com.cdyoue.oddJobs.dao.lqsq;


import com.cdyoue.oddJobs.entity.lgsq.PortalIntellectualSaleSummerEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2017/5/17.
 */
public interface PortalIntellectualSaleSummerResponsitory extends JpaCustomResponsitory<PortalIntellectualSaleSummerEntity, Integer>{

    @Query(nativeQuery = true,value = "SELECT id,name,publishTime,'1' as num from lg_portal_intellectual_sale_brand where reviewStatus = 1 UNION SELECT id,name,publishTime,'2' as num from lg_portal_intellectual_sale_patent where reviewStatus = 1 UNION SELECT id,name,publishTime,'3' as num from lg_portal_intellectual_sale_work where reviewStatus = 1 ORDER BY publishTime DESC LIMIT 0,6")
    List<PortalIntellectualSaleSummerEntity> getIntellectualSalesSummer();
}
