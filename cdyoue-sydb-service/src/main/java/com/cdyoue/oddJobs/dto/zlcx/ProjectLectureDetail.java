package com.cdyoue.oddJobs.dto.zlcx;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by sky on 2017/9/19.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-18T06:24:34.814Z")
public class ProjectLectureDetail {
    @JsonProperty("name")
    private String name = null;

    @JsonProperty("publishTime")
    private String publishTime = null;

    @JsonProperty("content")
    private String content = null;

    @JsonProperty("viewCount")
    private Integer viewCount = null;

    @JsonProperty("time")
    private String time = null;

    @JsonProperty("info")
    private String info = null;

    @JsonProperty("address")
    private String address = null;

    @JsonProperty("image")
    private String image = null;
    /**
     * 项目讲座时间
     * @return department
     **/
    @ApiModelProperty(example = "2017-09-09", value = "项目讲座时间")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    /**
     * 项目讲座简介
     * @return department
     **/
    @ApiModelProperty(example = "讲座1", value = "项目讲座简介")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    /**
     * 项目讲座地点
     * @return department
     **/
    @ApiModelProperty(example = "北京上海", value = "项目讲座地点")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * 项目讲座图片
     * @return department
     **/
    @ApiModelProperty(example = "xxxxx.jpg", value = "项目讲座图片")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ProjectLectureDetail name(String name) {
        this.name = name;
        return this;
    }
    /**
     * 项目讲座名称
     * @return department
     **/
    @ApiModelProperty(example = "讲座1", value = "项目讲座名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProjectLectureDetail publishTime(String publishTime) {
        this.publishTime = publishTime;
        return this;
    }
    /**
     * 项目讲座发布时间
     * @return department
     **/
    @ApiModelProperty(example = "2017-09-09", value = "项目讲座发布时间")
    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public ProjectLectureDetail content(String content) {
        this.content = content;
        return this;
    }
    /**
     * 项目讲座详情
     *
     * @return department
     **/
    @ApiModelProperty(example = "这就是一个项目讲座详情", value = "项目讲座详情")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ProjectLectureDetail name(Integer viewCount) {
        this.viewCount = viewCount;
        return this;
    }
    /**
     * 项目讲座浏览量
     * @return department
     **/
    @ApiModelProperty(example = "1000", value = "项目讲座浏览量")
    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }
}
