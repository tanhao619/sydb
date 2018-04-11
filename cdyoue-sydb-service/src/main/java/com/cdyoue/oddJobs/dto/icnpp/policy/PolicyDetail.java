package com.cdyoue.oddJobs.dto.icnpp.policy;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by liaoyoule on 2017/6/21.
 */
public class PolicyDetail {
    private PolicyBase base;
    private List<String> contentAddress;

    public PolicyBase getBase() {
        return base;
    }

    public void setBase(PolicyBase base) {
        this.base = base;
    }
    @ApiModelProperty("内容图片地址")
    public List<String> getContentAddress() {
        return contentAddress;
    }

    public void setContentAddress(List<String> contentAddress) {
        this.contentAddress = contentAddress;
    }
}
