package com.cdyoue.oddJobs.dao.syData;

import com.cdyoue.oddJobs.dao.lqsq.JpaCustomResponsitory;
import com.cdyoue.oddJobs.entity.syData.SyExpertAchievementCollectEntity;
import com.cdyoue.oddJobs.entity.syData.SyExpertAchievementEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by dengshaojun on 2017/09/18.
 */
public interface ExpertAchievementCollectRepository extends JpaCustomResponsitory<SyExpertAchievementCollectEntity, Integer> {

    List<SyExpertAchievementCollectEntity> findByUserIdAndAchievementId(Integer userId, Integer achievementId);

    @Query("select e.achievementId from SyExpertAchievementCollectEntity e where e.userId = ?1")
    List<Integer> findAchievementidsByUserId(Integer userId);

}
