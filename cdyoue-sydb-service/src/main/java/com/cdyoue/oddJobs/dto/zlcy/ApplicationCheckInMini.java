package com.cdyoue.oddJobs.dto.zlcy;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * 申请入驻基地的企业信息列表
 */
public class ApplicationCheckInMini {

    private Integer id;
    private String createEnter;
    private Long createTime;
    private Integer status;

    @ApiModelProperty(example = "1",value = "主键")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ApiModelProperty(example = "某某",value = "创建企业")
    public String getCreateEnter() {
        return createEnter;
    }

    public void setCreateEnter(String createEnter) {
        this.createEnter = createEnter;
    }

    @ApiModelProperty(example = "2018-09-09 11:11",value = "创建时间")
    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @ApiModelProperty(example = "0",value = "0:新;1:不新")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
