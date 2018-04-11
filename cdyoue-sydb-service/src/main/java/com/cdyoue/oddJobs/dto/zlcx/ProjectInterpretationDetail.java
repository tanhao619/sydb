package com.cdyoue.oddJobs.dto.zlcx;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by sky on 2017/9/19.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-18T06:24:34.814Z")
public class ProjectInterpretationDetail {
    @JsonProperty("title")
    private String title = null;

    @JsonProperty("publishTime")
    private String publishTime = null;

    @JsonProperty("viewCount")
    private Integer viewCount = null;

    @JsonProperty("content")
    private String content = null;


    /**
     * 项目解读名称
     * @return department
     **/
    @ApiModelProperty(example = "项目解读1", value = "项目解读名称")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    /**
     * 项目解读发布时间
     * @return department
     **/
    @ApiModelProperty(example = "2017-09-09", value = "项目解读发布时间")
    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }


    /**
     * 项目解读浏览量
     * @return department
     **/
    @ApiModelProperty(example = "1000", value = "项目解读浏览量")
    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }


    /**
     * 项目解读内容
     * @return department
     **/
    @ApiModelProperty(example = "这是项目解读的内容", value = "项目解读内容")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
