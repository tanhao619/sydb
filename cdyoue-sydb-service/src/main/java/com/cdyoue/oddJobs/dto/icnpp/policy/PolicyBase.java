package com.cdyoue.oddJobs.dto.icnpp.policy;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by liaoyoule on 2017/6/21.
 */
public class PolicyBase {

    private String title;
    private String organization;
    private String publishTime;

    @ApiModelProperty(value = "标题")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @ApiModelProperty(value = "发文单位")
    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }
    @ApiModelProperty(value = "发文时间")
    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }
}
