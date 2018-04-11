package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.PortalIntellectualSaleBrandEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/12.
 */
public interface PortalIntellectualSaleBrandResponsitory extends JpaCustomResponsitory<PortalIntellectualSaleBrandEntity, Integer> {
    @Modifying
    @Query("UPDATE PortalIntellectualSaleBrandEntity SET viewCount=viewCount+1 WHERE id=?1")
    void addViewcount(Integer id);

    @Query("select pb from PortalIntellectualSaleBrandEntity pb where pb.reviewStatus like ?1 and pb.name like ?2 and pb.businessType like ?3")
    Page<PortalIntellectualSaleBrandEntity> getIntellectualSalesBrand(Integer status,String q, Integer tt, Pageable pr);
    @Query("select pb from PortalIntellectualSaleBrandEntity pb where pb.name like ?1 and pb.businessType like ?2")
    Page<PortalIntellectualSaleBrandEntity> getIntellectualSalesBrandd(String q, Integer tt, Pageable pr);

    @Query("select pb from PortalIntellectualSaleBrandEntity pb where pb.reviewStatus like ?1 and pb.name like ?2")
    Page<PortalIntellectualSaleBrandEntity> getIntellectualSalesBrand1(Integer status,String q, Pageable pr);
    @Query("select pb from PortalIntellectualSaleBrandEntity pb where pb.name like ?1")
    Page<PortalIntellectualSaleBrandEntity> getIntellectualSalesBrand11(String q, Pageable pr);

    @Modifying
    @Query("UPDATE PortalIntellectualSaleBrandEntity SET top=1,updateTime=?2 WHERE id=?1")
    void topSaleBrand(Integer id,Date date);

    @Modifying
    @Query("UPDATE PortalIntellectualSaleBrandEntity SET top=0 WHERE id=?1")
    void removeSaleBrand(Integer id);
}
