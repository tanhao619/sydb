package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.PatentEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by dengshaojun on 2017/5/22.
 */
public interface PatentResponsitory extends JpaCustomResponsitory<PatentEntity, Integer>{

    @Query("select p from PatentEntity p where p.id in ?1")
    List<PatentEntity> findByIds(List<Integer> patentids);
}
