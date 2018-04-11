package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.PortalActivityEntity;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by luanyu on 2017/5/23.
 */
public interface CommunityRepository extends JpaCustomResponsitory<PortalActivityEntity, Integer>{
    @Query(value = "SELECT * FROM (SELECT id,title,info,coverImg,publishTime,2 AS recordType,address,peopleNumber,NULL AS company,reviewStatus FROM lg_portal_activity UNION SELECT id,title,summary AS info,coverImg,publishTime,1 AS recordType,NULL AS address,NULL AS peopleNumber, NAME AS company, reviewStatus FROM (SELECT t1.*,t2.name FROM lg_portal_news t1 LEFT JOIN userenterprise t2 ON t1.createBy=t2.UserId) AS t3) AS t4 WHERE t4.reviewStatus = 1 ORDER BY publishTime DESC limit ?1 , ?2", nativeQuery = true)
    Object[] findCommuities(int start,int len);
    @Query(value = "SELECT count(*) FROM (SELECT id,title,reviewStatus FROM lg_portal_activity UNION SELECT id,title,reviewStatus FROM lg_portal_news) AS t1 WHERE t1.reviewStatus = 1", nativeQuery = true)
    int findCount();
}
