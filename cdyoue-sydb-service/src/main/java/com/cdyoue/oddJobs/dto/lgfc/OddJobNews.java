package com.cdyoue.oddJobs.dto.lgfc;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by tr on 2017/6/7.
 */
public class OddJobNews {
    @JsonProperty("name")
    private String name;

    @JsonProperty("content")
    private String content;

    @JsonProperty("type")
    private Integer type;

    @JsonProperty("time")
    private String time;

    @ApiModelProperty(example = "唐某", value = "姓名")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ApiModelProperty(example = "", value = "根据type不同：1：实名认证 2：文章名称 3：提问问题名称")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @ApiModelProperty(example = "", value = "类型：1：实名认证 2：写文章 3：提问题")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @ApiModelProperty(example = "", value = "时间")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
