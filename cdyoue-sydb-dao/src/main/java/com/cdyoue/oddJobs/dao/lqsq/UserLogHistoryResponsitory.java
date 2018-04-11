package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.UserLogHistoryEntity;

/**
 * Created by liaoyoule on 2017/5/15.
 */
public interface UserLogHistoryResponsitory extends JpaCustomResponsitory<UserLogHistoryEntity,Integer> {

    UserLogHistoryEntity findByUserIdAndStatusAndTokenType(Integer id, int i, String token_type);
}
