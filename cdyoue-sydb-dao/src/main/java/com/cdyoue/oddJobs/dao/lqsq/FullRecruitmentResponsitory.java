package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.EnterpriserecruitmentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2017/5/8.
 */
public interface FullRecruitmentResponsitory extends  JpaCustomResponsitory<EnterpriserecruitmentEntity,Integer> {
    @Query("select fj from EnterpriserecruitmentEntity fj where fj.id in ?1")
    Page<EnterpriserecruitmentEntity> getFullJobPage(List<Integer> fullJobIds, Pageable requestPage);

    @Query("select fj from EnterpriserecruitmentEntity fj where fj.entId=?1 and fj.jobName like ?2")
    Page<EnterpriserecruitmentEntity> getUserEnterpriseFullJobPage(Integer enterpriseId,String q, Pageable requestPage);

    @Query(nativeQuery = true,value = "select name from functioncategory where id = ?1")
    String findCategoryById(Integer entId);

    @Query("select entId from EnterpriserecruitmentEntity where id=?1")
    Integer getFullRecruitmentEntId(Integer id);

    @Query("SELECT id FROM UserenterpriseEntity WHERE userId=?1")
    Integer getEid(Integer uid);

    @Query("SELECT userId FROM UserenterpriseEntity WHERE id=?1")
    Integer getUserId(Integer entId);
}
