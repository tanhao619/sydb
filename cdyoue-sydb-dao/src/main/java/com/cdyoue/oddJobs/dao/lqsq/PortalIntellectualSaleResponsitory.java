package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.PortalIntellectualSaleBrandEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by dengshaojun on 2017/6/1.
 */
public interface PortalIntellectualSaleResponsitory extends JpaCustomResponsitory<PortalIntellectualSaleBrandEntity, Integer> {

    @Query(value = "SELECT * FROM (SELECT businessType,1 AS category,contact,tel,publishTime AS createTime,id,introduction,NAME,price,createBy AS publisher,reviewStatus,viewCount,1 AS `type`,top,topImg  FROM lg_portal_intellectual_sale_brand UNION ALL \n" +
            "SELECT businessType,2 AS category,contact,tel,publishTime AS createTime,id,introduction,NAME,price,createBy AS publisher,reviewStatus,viewCount , 2 AS  `type`,top,topImg FROM lg_portal_intellectual_sale_patent UNION ALL \n" +
            "SELECT businessType,3 AS category,contact,tel,publishTime AS createTime,id,introduction,NAME,price,createBy AS publisher,reviewStatus,viewCount , 3 AS  `type`,top,topImg FROM lg_portal_intellectual_sale_work) AS t1 " +
            "WHERE t1.reviewStatus like ?1 and t1.name LIKE ?2 AND t1.businessType LIKE ?3 ORDER BY createTime DESC LIMIT ?4,?5", nativeQuery = true)
    Object[] findIntellectualSales(String reviewStatus,String name, String businessType, int start, int limit);

    @Query(value = "SELECT COUNT(*) FROM (SELECT id,NAME,businessType FROM lg_portal_intellectual_sale_brand UNION ALL\n" +
            "SELECT id,NAME,businessType FROM lg_portal_intellectual_sale_patent UNION ALL \n" +
            "SELECT id,NAME,businessType FROM lg_portal_intellectual_sale_work) AS t1 WHERE t1.name LIKE ?1 AND t1.businessType LIKE ?2 ", nativeQuery = true)
    int findCount(String name, String businessType);

    @Query(value = "SELECT * FROM (SELECT id,1 AS category,top,topImg,name,price,reviewStatus,updateTime,1 AS `type`  FROM lg_portal_intellectual_sale_brand UNION ALL \n" +
            "                       SELECT id,2 AS category,top,topImg,name,price,reviewStatus,updateTime , 2 AS  `type` FROM lg_portal_intellectual_sale_patent UNION ALL \n" +
            "                       SELECT id,3 AS category,top,topImg,name,price,reviewStatus,updateTime , 3 AS  `type` FROM lg_portal_intellectual_sale_work) AS t1\n" +
            "                       WHERE t1.reviewStatus=1 and top=1 ORDER BY updateTime DESC LIMIT 4", nativeQuery = true)
    Object[] getBanners();

    @Query(value = " SELECT id,name,topImg,reviewStatus FROM lg_portal_intellectual_buy WHERE reviewStatus=1 and top=1 ORDER BY updateTime DESC LIMIT 4", nativeQuery = true)
    Object[] getBuyBanners();

    @Modifying
    @Query(value = "UPDATE lg_portal_intellectual_buy set top=1,updateTime=?2 where id=?1",nativeQuery = true)
    void buyTop(Integer id,Date date);

    @Modifying
    @Query(value = "UPDATE lg_portal_intellectual_buy set top=1,updateTime=?2,topImg=?3 where id=?1",nativeQuery = true)
    void buyTopImg(Integer id, Date date, String topImg);

    @Modifying
    @Query(value = "UPDATE lg_portal_intellectual_buy set top=0 where id=?1",nativeQuery = true)
    void removeBuyTop(Integer id);

    @Query(value = "SELECT * FROM (SELECT businessType,1 AS category,contact,tel,publishTime AS createTime,id,introduction,NAME,price,createBy AS publisher,reviewStatus,viewCount,1 AS `type`,top,topImg,updateTime  FROM lg_portal_intellectual_sale_brand UNION ALL \n" +
            "            SELECT businessType,2 AS category,contact,tel,publishTime AS createTime,id,introduction,NAME,price,createBy AS publisher,reviewStatus,viewCount , 2 AS  `type`,top,topImg,updateTime FROM lg_portal_intellectual_sale_patent UNION ALL \n" +
            "            SELECT businessType,3 AS category,contact,tel,publishTime AS createTime,id,introduction,NAME,price,createBy AS publisher,reviewStatus,viewCount , 3 AS  `type`,top,topImg,updateTime FROM lg_portal_intellectual_sale_work) AS t1 \n" +
            "            WHERE t1.reviewStatus like ?1 and t1.name LIKE ?2 AND t1.businessType LIKE ?3 ORDER BY t1.top DESC,t1.updateTime DESC LIMIT ?4,?5", nativeQuery = true)
    Object[] findIntellectualSalesAdmin(String reviewStatus,String name, String businessType, int start, int limit);
}
