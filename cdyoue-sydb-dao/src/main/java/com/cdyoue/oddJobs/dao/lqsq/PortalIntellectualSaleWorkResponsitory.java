package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.PortalIntellectualSaleWorkEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/12.
 */
public interface PortalIntellectualSaleWorkResponsitory extends JpaCustomResponsitory<PortalIntellectualSaleWorkEntity, Integer> {
    @Modifying
    @Query("UPDATE PortalIntellectualSaleWorkEntity SET viewCount=viewCount+1 WHERE id=?1")
    void addViewcount(Integer id);

    @Query("select pb from PortalIntellectualSaleWorkEntity pb where pb.reviewStatus like ?1 and pb.name like ?2 and pb.businessType like ?3")
    Page<PortalIntellectualSaleWorkEntity> getIntellectualSalesWork(Integer status,String q, Integer tt, Pageable pr);
    @Query("select pb from PortalIntellectualSaleWorkEntity pb where pb.name like ?1 and pb.businessType like ?2")
    Page<PortalIntellectualSaleWorkEntity> getIntellectualSalesWorkk(String q, Integer tt, Pageable pr);

    @Query("select pb from PortalIntellectualSaleWorkEntity pb where  pb.reviewStatus like ?1 and pb.name like ?2")
    Page<PortalIntellectualSaleWorkEntity> getIntellectualSalesWork1(Integer status,String q, Pageable pr);
    @Query("select pb from PortalIntellectualSaleWorkEntity pb where pb.name like ?1")
    Page<PortalIntellectualSaleWorkEntity> getIntellectualSalesWork11(String q, Pageable pr);

    @Modifying
    @Query("UPDATE PortalIntellectualSaleWorkEntity SET top=1,updateTime=?2 WHERE id=?1")
    void topSaleWork(Integer id,Date date);

    @Modifying
    @Query("UPDATE PortalIntellectualSaleWorkEntity SET top=0 WHERE id=?1")
    void removeSaleWork(Integer id);
}
