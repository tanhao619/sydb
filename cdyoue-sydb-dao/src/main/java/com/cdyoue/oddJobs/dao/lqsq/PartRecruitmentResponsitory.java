package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.ParttimejobEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2017/5/8.
 */
public interface PartRecruitmentResponsitory extends  JpaCustomResponsitory<ParttimejobEntity,Integer> {
    @Query("select pj from ParttimejobEntity pj where pj.id in ?1")
    Page<ParttimejobEntity> getPartJobPage(List<Integer> partJobIds,Pageable requestPage);

    @Query("select pj from ParttimejobEntity pj where pj.entId=?1 and pj.jobName like ?2")
    Page<ParttimejobEntity> getUserEnterprisePartJobPage(Integer enterpriseId,String q, Pageable requestPage);

    @Query("select entId from ParttimejobEntity where id=?1")
    Integer getFullRecruitmentEntId(Integer id);
}
