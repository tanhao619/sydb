package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.PortalIntellectualSaleMineEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Administrator on 2017/5/18.
 */
public interface PortalIntellectualSaleMineResponsitory extends JpaCustomResponsitory<PortalIntellectualSaleMineEntity, Integer> {
    @Query(value = "SELECT id,name,reviewStatus,publishTime,price,'1' as category from lg_portal_intellectual_sale_brand WHERE createBy=?1 " +
            "UNION SELECT id,name,reviewStatus,publishTime,price,'2' as category from lg_portal_intellectual_sale_patent WHERE createBy=?1 " +
            "UNION SELECT id,name,reviewStatus,publishTime,price,'3' as category from lg_portal_intellectual_sale_work WHERE createBy=?1 " +
            "ORDER BY publishTime DESC \n-- #pageable\n",nativeQuery=true,
            countQuery = "SELECT count(1) from (SELECT a.id,a.name,a.reviewStatus,a.publishTime,a.price,'1' as category from lg_portal_intellectual_sale_brand a WHERE createBy=?1 " +
            "UNION SELECT b.id,b.name,b.reviewStatus,b.publishTime,b.price,'2' as category from lg_portal_intellectual_sale_patent b WHERE createBy=?1 " +
            "UNION SELECT c.id,c.name,c.reviewStatus,c.publishTime,c.price,'3' as category from lg_portal_intellectual_sale_work c WHERE createBy=?1 ) d")
    Page<PortalIntellectualSaleMineEntity> getMyIntellectualSales(Integer uid,Pageable pageable);
}
