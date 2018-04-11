package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.OutsourcingProjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by liaoyoule on 2017/4/20.
 */
public interface OutsourcingProjectResponsitory extends JpaCustomResponsitory<OutsourcingProjectEntity,Integer> {
    @Query("select pre from OutsourcingProjectEntity pre where pre.createBy.id = ?1 ")
    Page<OutsourcingProjectEntity> findMyRequires(Integer id, Pageable pr);
    @Query("select pre from OutsourcingProjectEntity pre where pre.createBy.id = ?1 and  pre.name like ?2")
    Page<OutsourcingProjectEntity> findMyRequires(Integer userId, String query, Pageable pr);

    @Query("select pre from OutsourcingProjectEntity pre where pre.reviewStatus = 1 order by pre.publishTime DESC ")
    Page<OutsourcingProjectEntity> getProjectNews(Pageable pageRequest);
}
