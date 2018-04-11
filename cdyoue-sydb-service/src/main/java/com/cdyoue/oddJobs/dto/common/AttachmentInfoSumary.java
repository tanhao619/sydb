package com.cdyoue.oddJobs.dto.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by liaoyoule on 2017/4/24.
 */
public class AttachmentInfoSumary {

    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("url")
    private String url;
    @ApiModelProperty(value = "附件服务器主键id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @ApiModelProperty(value = "上传文件名")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @ApiModelProperty(value = "上传路径")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
