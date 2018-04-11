package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.PortalCommonPageDetailEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/15.
 */
public interface PortalCommonPageDetailResponsitory extends JpaCustomResponsitory<PortalCommonPageDetailEntity, Integer> {
    // pageType = 3 为文章
    @Query("select pd from PortalCommonPageDetailEntity pd where pd.createBy =?1 and pd.pageType=3")
    List<PortalCommonPageDetailEntity> findArticleByUserId(Integer userId);

    // 关注能人时间 >= 发布时间的文章
    // status 文章状态： 0未审中 1审核成功 2审核失败
    // status = 1审核成功的文章
    @Query(value = "select * from lg_portal_common_page_detail WHERE createBy = ?1 AND pageType=3 AND createTime >= ?2 AND status = 1",nativeQuery = true)
    List<PortalCommonPageDetailEntity> findArticleByUserIdAndTime(Integer userId,Date createTime);
}
