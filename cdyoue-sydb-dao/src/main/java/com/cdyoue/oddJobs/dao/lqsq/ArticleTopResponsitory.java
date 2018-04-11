package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.PortalArticleTopEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by sky on 2017/5/26.
 */
public interface ArticleTopResponsitory extends JpaCustomResponsitory<PortalArticleTopEntity,Integer> {
    @Modifying
    @Query("delete from PortalArticleTopEntity p where p.articleId=?1")
    void deleteByArticleId(Integer id);

    PortalArticleTopEntity findByArticleId(int id);
}
