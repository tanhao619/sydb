package com.cdyoue.oddJobs.dto.icnpp.policy;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by liaoyoule on 2017/6/21.
 */
public class PolicySumary {
    private String id;
    private String link;
    private PolicyBase base;
    @ApiModelProperty(value = "政策id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @ApiModelProperty(value = "对应政策详情页的页面url",example = "/H5/policyDetails.html?id=1")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public PolicyBase getBase() {
        return base;
    }

    public void setBase(PolicyBase base) {
        this.base = base;
    }
}
