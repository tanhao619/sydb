package com.cdyoue.oddJobs.dao.syData;

import com.cdyoue.oddJobs.dao.lqsq.JpaCustomResponsitory;
import com.cdyoue.oddJobs.entity.syData.SyExpertAchievementEntity;
import com.cdyoue.oddJobs.entity.syData.SyExpertCareerEntity;
import com.cdyoue.oddJobs.entity.syData.SyExpertEntity;
import com.cdyoue.oddJobs.entity.syData.SyProfessionalInterpretationEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by dengshaojun on 2017/09/18.
 */
public interface ExpertCareerRepository extends JpaCustomResponsitory<SyExpertCareerEntity,Integer> {

    @Modifying
    void deleteSyExpertCareerEntitiesByExpertIdIsNull();
}
