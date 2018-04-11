package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.PortalIntellectualBuyEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


/**
 * Created by Administrator on 2017/5/6.
 */
public interface PortalIntellectualBuyResponsitory extends JpaCustomResponsitory<PortalIntellectualBuyEntity , Integer> {

    @Modifying
    @Query("UPDATE PortalIntellectualBuyEntity SET viewCount=viewCount+1 WHERE id=?1")
    void addViewcount(Integer id);
}
