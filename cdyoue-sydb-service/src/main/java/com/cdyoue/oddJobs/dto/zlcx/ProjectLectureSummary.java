package com.cdyoue.oddJobs.dto.zlcx;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by sky on 2017/9/19.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-18T06:24:34.814Z")
public class ProjectLectureSummary {
    @JsonProperty("id")
    private Integer id = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("image")
    private String image = null;

    @JsonProperty("time")
    private String time = null;

    @JsonProperty("info")
    private String info = null;

    @JsonProperty("address")
    private String address = null;

    @JsonProperty("publishPeople")
    private String publishPeople = null;

    @JsonProperty("publishTime")
    private String publishTime = null;

    @JsonProperty("viewCount")
    private Integer viewCount = null;

    @JsonProperty("topImage")
    private  String topImage=null;

    @JsonProperty("top")
    private  Integer top=null;

    /**
     * 项目讲座是否置顶
     * @return department
     **/
    @ApiModelProperty(example = "1", value = "项目讲座是否置顶（1：置顶，0：非置顶）")
    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    /**
     * 项目讲座置顶图片
     * @return department
     **/
    @ApiModelProperty(example = "sy/10-17/7b2832e6-382b-4046-8c68-f7a9b8641e7e.jpg", value = "项目讲座置顶图片")
    public String getTopImage() {
        return topImage;
    }

    public void setTopImage(String topImage) {
        this.topImage = topImage;
    }

    /**
     * 项目讲座发布人
     * @return department
     **/
    @ApiModelProperty(example = "小明", value = "项目讲座发布人")
    public String getPublishPeople() {
        return publishPeople;
    }

    public void setPublishPeople(String publishPeople) {
        this.publishPeople = publishPeople;
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
    /**
     * 项目讲座浏览量
     * @return department
     **/
    @ApiModelProperty(example = "212", value = "项目讲座浏览量")
    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    /**
     * 项目讲座id
     * @return department
     **/
    @ApiModelProperty(example = "1", value = "项目讲座id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    /**
     * 项目讲座图片
     * @return department
     **/
    @ApiModelProperty(example = "sy/10-17/7b2832e6-382b-4046-8c68-f7a9b8641e7e.jpg",value = "项目讲座图片")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

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
     * 项目讲座介绍
     * @return department
     **/
    @ApiModelProperty(example = "这是一个项目讲座", value = "项目讲座介绍")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * 项目讲座地址
     * @return department
     **/
    @ApiModelProperty(example = "中国四川成都优易数据", value = "项目讲座地址")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
