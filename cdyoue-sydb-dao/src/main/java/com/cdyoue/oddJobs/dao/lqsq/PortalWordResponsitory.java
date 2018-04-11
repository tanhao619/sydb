package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.PortalWordEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by liaoyoule on 2017/4/25.
 */
public interface PortalWordResponsitory extends JpaCustomResponsitory<PortalWordEntity, Integer> {
    @Modifying
    @Query("delete FROM PortalWordEntity pwe where  pwe.user.id = ?1 and pwe.requirementEntity.id =?2")
    void deleteByUserAndReId(Integer userId, Integer id);
    @Modifying
    @Query("delete FROM PortalWordEntity pwe where  pwe.requirementEntity.id =?1")
    void deleteByReId(Integer id);

    Page<PortalWordEntity> findByUserId(Integer userId, Pageable pr);

    @Query("select pwe  FROM PortalWordEntity pwe where  pwe.userId =?1 and  pwe.eventId = ?2 and pwe.wordType=?3")
    PortalWordEntity findWord(Integer userId, Integer eventId, Integer type);
}
