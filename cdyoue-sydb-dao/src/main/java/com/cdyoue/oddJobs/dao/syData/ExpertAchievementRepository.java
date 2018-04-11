package com.cdyoue.oddJobs.dao.syData;

import com.cdyoue.oddJobs.dao.lqsq.JpaCustomResponsitory;
import com.cdyoue.oddJobs.entity.syData.SyExpertAchievementEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by dengshaojun on 2017/09/18.
 */
public interface ExpertAchievementRepository extends JpaCustomResponsitory<SyExpertAchievementEntity, Integer> {

    List<SyExpertAchievementEntity> findTop2ByTopOrderByUpdateTimeDesc(Byte top);

    @Modifying
    @Query("update SyExpertAchievementEntity ea set ea.syExpertEntity.id = null where ea.syExpertEntity.id = ?1")
    void updateForeign(Integer expertId);

    @Query("select ea from SyExpertAchievementEntity ea where ea.id in ?1")
    Page<SyExpertAchievementEntity> findByIds(List<Integer> achievementids, Pageable pageRequest);
}
