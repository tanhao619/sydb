package com.cdyoue.oddJobs.dto.zlcx;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by sky on 2017/9/29.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-18T06:24:34.814Z")
public class ProjectLectureCreate {
    /**
     * 项目讲座详情
     *
     * @return department
     **/
    @ApiModelProperty(example = "这就是一个项目讲座详情", value = "项目讲座详情")
    @JsonProperty("name")
    private String name = null;

    @JsonProperty("time")
    private String time = null;

    @JsonProperty("info")
    private String info = null;

    @JsonProperty("address")
    private String address = null;

    @JsonProperty("content")
    private String content = null;

    @JsonProperty("image")
    private String image = null;
    /**
     * 项目讲座名称
     *
     * @return department
     **/
    @ApiModelProperty(example = "这就是一个项目讲座名称", value = "项目讲座名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    /**
     * 项目讲座时间
     *
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
     *
     * @return department
     **/
    @ApiModelProperty(example = "这就是一个项目讲座简介", value = "项目讲座简介")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    /**
     * 项目讲座地址
     *
     * @return department
     **/
    @ApiModelProperty(example = "这就是一个项目讲座地址", value = "项目讲座地址")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
    /**
     * 项目讲座图片
     *
     * @return department
     **/
    @ApiModelProperty(example = "/assets/images/assistsInnovation/pho-Project-lecture.png", value = "项目讲座图片")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
