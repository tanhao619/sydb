package com.cdyoue.oddJobs.dto.zlcx;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by sky on 2017/9/19.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-18T06:24:34.814Z")
public class ProjectInterpretationSummary {
    @JsonProperty("id")
    private Integer id = null;

    @JsonProperty("title")
    private String title = null;

    @JsonProperty("publishPeople")
    private String publishPeople = null;

    @JsonProperty("viewCount")
    private Integer viewCount = null;

    @JsonProperty("publishTime")
    private String publishTime = null;

    /**
     * 项目解读发布人
     * @return department
     **/
    @ApiModelProperty(example = "admin", value = "项目解读发布人")
    public String getPublishPeople() {
        return publishPeople;
    }

    public void setPublishPeople(String publishPeople) {
        this.publishPeople = publishPeople;
    }

    /**
     * 项目解读id
     * @return department
     **/
    @ApiModelProperty(example = "1", value = "项目解读id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
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
}
