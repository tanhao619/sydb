package com.cdyoue.oddJobs.dto.scfw;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * EnterServiceSummary
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-15T01:26:16.185Z")

public class EnterServiceSummary {
    @JsonProperty("id")
    private Integer id = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("serviceInfo")
    private String serviceInfo = null;

    @JsonProperty("link")
    private String link = null;

    @JsonProperty("info")
    private String info = null;

    @JsonProperty("viewCount")
    private Integer viewCount = null;

    @JsonProperty("introduction")
    private String introduction = null;

    @JsonProperty("coverImg")
    private String coverImg = null;

    @JsonProperty("createTime")
    private String createTime = null;

    @JsonProperty("createBy")
    private String createBy = null;

    @JsonProperty("h5link")
    private String h5link = null;

    public EnterServiceSummary id(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * id
     *
     * @return id
     **/
    @ApiModelProperty(value = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @ApiModelProperty(example = "International Business Service", value = "服务内容")
    public String getServiceInfo() {
        return serviceInfo;
    }

    public void setServiceInfo(String serviceInfo) {
        this.serviceInfo = serviceInfo;
    }

    public EnterServiceSummary serviceInfo(String serviceInfo) {
        this.serviceInfo = serviceInfo;
        return this;
    }

    @ApiModelProperty(example = "IBM", value = "服务商名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EnterServiceSummary name(String name) {
        this.name = name;
        return this;
    }


    @ApiModelProperty(example = "www.cambrige.ac.uk", value = "链接")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public EnterServiceSummary link(String link) {
        this.link = link;
        return this;
    }

    @ApiModelProperty(example = "666", value = "浏览量")
    public Integer getViewCount() {
        return viewCount == null ? 0 : viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public EnterServiceSummary viewCount(Integer viewCount) {
        this.viewCount = viewCount;
        return this;
    }


    @ApiModelProperty(example = "IBM Service Information", value = "简介")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public EnterServiceSummary info(String info) {
        this.info = info;
        return this;
    }


    @ApiModelProperty(example = "Here is introduction", value = "详细内容")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }


    @ApiModelProperty(example = "www.abc.com/q/1.img", value = "封面图片")
    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    @ApiModelProperty(example = "2017-02-21 10:00:00", value = "创建时间")
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @ApiModelProperty(example = "2017-02-21 10:00:00", value = "创建人")
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @ApiModelProperty(example = "/H5/patentapplicationDetails.html?id=1234", value = "H5链接")
    public String getH5link() {
        return h5link;
    }

    public void setH5link(String h5link) {
        this.h5link = h5link;
    }
}

