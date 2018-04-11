package com.cdyoue.oddJobs.dao.syData;

import com.cdyoue.oddJobs.dao.lqsq.JpaCustomResponsitory;
import com.cdyoue.oddJobs.entity.syData.SyExpertAchievementEntity;
import com.cdyoue.oddJobs.entity.syData.SyExpertCareerEntity;
import com.cdyoue.oddJobs.entity.syData.SyExpertEntity;
import com.cdyoue.oddJobs.entity.syData.SyProfessionalInterpretationEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by dengshaojun on 2017/09/18.
 */
public interface ExpertRepository extends JpaCustomResponsitory<SyExpertEntity,Integer> {

    //@Query("select e from SyExpertEntity e where e.top = ?1 order by e.updateTime desc")
    List<SyExpertEntity> findTop3ByTopOrderByUpdateTimeDesc(Byte top);

    @Query("select e.syExpertCareerEntitySet from SyExpertEntity e where e.id = ?1")
    List<SyExpertCareerEntity> findCareerByEid(Integer expertId);

    @Query("select e.syExpertAchievementEntitySet from SyExpertEntity e where e.id = ?1")
    List<SyExpertAchievementEntity> findExpertAchievementByEid(Integer expertId);

    @Query("select e.syProfessionalInterpretationEntitySet from SyExpertEntity e where e.id = ?1")
    List<SyProfessionalInterpretationEntity> findProfessionalInterpretationByEid(Integer expertId);

    @Query("select e.id from SyExpertEntity e where e.name like ?1")
    List<Integer> findIdsByNameLike(String name);
}
