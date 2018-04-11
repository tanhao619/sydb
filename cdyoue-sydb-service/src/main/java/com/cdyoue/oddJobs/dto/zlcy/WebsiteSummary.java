package com.cdyoue.oddJobs.dto.zlcy;

import io.swagger.annotations.ApiModelProperty;

/**
 * 网址站列表
 */
public class WebsiteSummary extends WebsiteRequest {

    private Integer id;
    private String publisher;
    private Long createTime;

    @ApiModelProperty(example = "1", value = "主键")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ApiModelProperty(example = "管理员003", value = "发布人")
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @ApiModelProperty(example = "2017-06-06 12:34", value = "创建时间")
    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
