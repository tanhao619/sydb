package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.oauth.TokenSumary;
import com.cdyoue.oddJobs.en.LoginTypeEnum;

/**
 * Created by liaoyoule on 2017/5/15.
 */
public interface UserLogHistoryService {
    void recordLoginHistory(TokenSumary ts);

    void logout(Integer id, String loginType);

    void update(String sujectId, TokenSumary ts);

    TokenSumary getStoreToken(int i, LoginTypeEnum loginType);
}
