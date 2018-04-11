package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.PortalIntellectualSalePatentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/12.
 */
public interface PortalIntellectualSalePatentResponsitory extends JpaCustomResponsitory<PortalIntellectualSalePatentEntity, Integer> {
    @Modifying
    @Query("UPDATE PortalIntellectualSalePatentEntity SET viewCount=viewCount+1 WHERE id=?1")
    void addViewcount(Integer id);

    @Query("select pb from PortalIntellectualSalePatentEntity pb where pb.reviewStatus like ?1 and pb.name like ?2 and pb.businessType like ?3")
    Page<PortalIntellectualSalePatentEntity> getIntellectualSalesPatent(Integer status,String q, Integer tt, Pageable pr);
    @Query("select pb from PortalIntellectualSalePatentEntity pb where pb.name like ?1 and pb.businessType like ?2")
    Page<PortalIntellectualSalePatentEntity> getIntellectualSalesPatentt(String q, Integer tt, Pageable pr);

    @Query("select pb from PortalIntellectualSalePatentEntity pb where pb.reviewStatus like ?1 and pb.name like ?2")
    Page<PortalIntellectualSalePatentEntity> getIntellectualSalesPatent1(Integer status,String q, Pageable pr);
    @Query("select pb from PortalIntellectualSalePatentEntity pb where pb.name like ?1")
    Page<PortalIntellectualSalePatentEntity> getIntellectualSalesPatent11(String q, Pageable pr);

    @Modifying
    @Query("UPDATE PortalIntellectualSalePatentEntity SET top=1,updateTime=?2 WHERE id=?1")
    void topSalePatent(Integer id,Date date);

    @Modifying
    @Query("UPDATE PortalIntellectualSalePatentEntity SET top=0 WHERE id=?1")
    void removeSalePatent(Integer id);
}
