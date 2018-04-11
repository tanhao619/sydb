package com.cdyoue.oddJobs.dao.syData;

import com.cdyoue.oddJobs.dao.lqsq.JpaCustomResponsitory;
import com.cdyoue.oddJobs.entity.syData.SyProfessionalInterpretationCollectEntity;
import com.cdyoue.oddJobs.entity.syData.SyProfessionalInterpretationEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by dengshaojun on 2017/09/18.
 */
public interface ProfessionalInterpretationCollectRepository extends JpaCustomResponsitory<SyProfessionalInterpretationCollectEntity, Integer> {

    List<SyProfessionalInterpretationCollectEntity> findByUserIdAndInterpretationId(Integer userId, Integer interpretationid);

    @Query("select e.interpretationId from SyProfessionalInterpretationCollectEntity e where e.userId = ?1")
    List<Integer> findInterpretationidsByUserId(Integer userId);
}
