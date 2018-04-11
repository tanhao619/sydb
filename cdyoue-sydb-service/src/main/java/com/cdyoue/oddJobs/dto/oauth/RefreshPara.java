package com.cdyoue.oddJobs.dto.oauth;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by m2m on 2017/3/27.
 *
 */
public class RefreshPara {
    private String refreshToken;
    @ApiModelProperty(value = "刷新令牌")
    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
