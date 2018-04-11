package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.PortalCommonPageDetailEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Administrator on 2017/5/2.
 */
public interface ArticleResponsitory extends JpaCustomResponsitory<PortalCommonPageDetailEntity,Integer>{
    @Modifying
    @Query("DELETE FROM PortalCommonPageDetailEntity WHERE id=?1 AND pageType=?2")
    void deleteArticle(Integer id,Integer type);//删除文章

    @Query("select p FROM PortalCommonPageDetailEntity p WHERE id=?1 AND pageType=?2")
    PortalCommonPageDetailEntity findByIdAndType(Integer id, Integer type);


}
