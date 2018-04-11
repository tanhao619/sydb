package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.PortalWord4RecruitmentEntity;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by liaoyoule on 2017/4/25.
 */
public interface PortalWord4RecruitmentResponsitory extends JpaCustomResponsitory<PortalWord4RecruitmentEntity, Integer> {

    @Query("select p from PortalWord4RecruitmentEntity p where p.eventId=?1 and p.wordType=1 and p.userId=?2")
    PortalWord4RecruitmentEntity getFullWordById(int eId, Integer userid);

    @Query("select p from PortalWord4RecruitmentEntity p where p.eventId=?1 and p.wordType=2 and p.userId=?2")
    PortalWord4RecruitmentEntity getPartWordById(int eId, Integer userid);
}
