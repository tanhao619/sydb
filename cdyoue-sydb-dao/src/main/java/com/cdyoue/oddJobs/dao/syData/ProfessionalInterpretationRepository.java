package com.cdyoue.oddJobs.dao.syData;

import com.cdyoue.oddJobs.dao.lqsq.JpaCustomResponsitory;
import com.cdyoue.oddJobs.entity.syData.SyProfessionalInterpretationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by dengshaojun on 2017/09/18.
 */
public interface ProfessionalInterpretationRepository extends JpaCustomResponsitory<SyProfessionalInterpretationEntity, Integer> {

    List<SyProfessionalInterpretationEntity> findTop3ByTopOrderByUpdateTimeDesc(Byte top);

    @Modifying
    @Query("update SyProfessionalInterpretationEntity pi set pi.syExpertEntity.id = null where pi.syExpertEntity.id = ?1")
    void updateForeign(Integer expertId);

    @Query("select e from SyProfessionalInterpretationEntity e where e.id in ?1")
    Page<SyProfessionalInterpretationEntity> findByIds(List<Integer> interpretationids, Pageable pageRequest);
}
