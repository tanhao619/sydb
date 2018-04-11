package com.cdyoue.oddJobs.dto.zlcy;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by sky on 2017/9/23.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-18T06:24:34.814Z")
public class TrainingSummary {
    @JsonProperty("id")
    private Integer id=null;

    @JsonProperty("name")
    private String name=null;

    @JsonProperty("address")
    private String address=null;

    @JsonProperty("info")
    private String info=null;

    @JsonProperty("logo")
    private String logo=null;

    @JsonProperty("topImage")
    private String topImage=null;

    @JsonProperty("publishPeople")
    private String publishPeople=null;

    @JsonProperty("viewCount")
    private Integer viewCount=null;

    @JsonProperty("createTime")
    private String createTime=null;

    @JsonProperty("top")
    private Integer top=null;

    /**
     * 培训机构是否置顶
     * @return id
     **/
    @ApiModelProperty(example = "1",value = "培训机构是否置顶（1：置顶，0；非置顶）")
    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    /**
     * 培训机构置顶图片
     * @return id
     **/
    @ApiModelProperty(example = "sy/10-11/b8a33f15-2d6c-4773-b299-5610a5889a8d.jpg",value = "培训机构置顶图片")
    public String getTopImage() {
        return topImage;
    }

    public void setTopImage(String topImage) {
        this.topImage = topImage;
    }

    /**
     * 培训机构发布人
     * @return id
     **/
    @ApiModelProperty(example = "卡拉卡",value = "培训机构发布人")
    public String getPublishPeople() {
        return publishPeople;
    }

    public void setPublishPeople(String publishPeople) {
        this.publishPeople = publishPeople;
    }
    /**
     * 培训机构浏览量
     * @return id
     **/
    @ApiModelProperty(example = "123",value = "培训机构浏览量")
    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }
    /**
     * 培训机构发布时间
     * @return id
     **/
    @ApiModelProperty(example = "2017-09-09",value = "培训机构发布时间")
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 培训机构id 自动生成
     * @return id
     **/
    @ApiModelProperty(value = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 培训机构名称
     * @return id
     **/
    @ApiModelProperty(example = "卡拉卡",value = "培训机构名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 培训机构地点
     * @return id
     **/
    @ApiModelProperty(example = "北京",value = "培训机构地点")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 培训机构简介
     * @return id
     **/
    @ApiModelProperty(example = "这是一个培训机构",value = "培训机构简介")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * 培训机构logo
     * @return id
     **/
    @ApiModelProperty(example = "xxxx.jpg",value = "培训机构logo")
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
