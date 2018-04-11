package com.cdyoue.oddJobs.dto.scfw;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * EnterServiceDetail
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-15T01:26:16.185Z")

public class EnterServiceDetail   {
    @JsonProperty("name")
    private String name = null;

    @JsonProperty("serviceInfo")
    private String serviceInfo = null;

    @JsonProperty("tel")
    private String tel = null;

    @JsonProperty("link")
    private String link = null;

    @JsonProperty("coverImg")
    private String coverImg = null;

    @JsonProperty("introduction")
    private String introduction = null;

    @JsonProperty("info")
    private String info = null;

    @JsonProperty("type")
    private String type = null;

    @JsonProperty("viewCount")
    private Integer viewCount = null;

    @JsonProperty("h5link")
    private String h5link;

    @ApiModelProperty(example = "IBM", value = "服务机构名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EnterServiceDetail name(String name) {
        this.name = name;
        return this;
    }

    @ApiModelProperty(example = "", value = "服务内容")
    public String getServiceInfo() {
        return serviceInfo;
    }

    public void setServiceInfo(String serviceInfo) {
        this.serviceInfo = serviceInfo;
    }

    public EnterServiceDetail serviceInfo(String serviceInfo) {
        this.serviceInfo = serviceInfo;
        return this;
    }

    @ApiModelProperty(example = "02086882888", value = "联系电话")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public EnterServiceDetail tel(String tel) {
        this.tel = tel;
        return this;
    }

    @ApiModelProperty(example = "www.ibm.com.cn", value = "官网链接")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public EnterServiceDetail link(String link) {
        this.link = link;
        return this;
    }

    @ApiModelProperty(example = "www.yoedata.com/img/1/1.jpg", value = "图片链接")
    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public EnterServiceDetail coverImg(String coverImg) {
        this.coverImg = coverImg;
        return this;
    }

    @ApiModelProperty(example = "This is the introduction of IBM", value = "简介")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public EnterServiceDetail introduction(String introduction) {
        this.introduction = introduction;
        return this;
    }

    @ApiModelProperty(example = "This is the specific information for IBM", value = "详细内容")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public EnterServiceDetail info(String info) {
        this.info = info;
        return this;
    }

    @ApiModelProperty(example = "ZSCQ", value = "；类型：知识产权'zscq', 双创服务'scfw")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @ApiModelProperty(example = "8888", value = "浏览次数")
    public Integer getViewCount() {
        return viewCount == null ? 0 : viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    @ApiModelProperty(example = "patentapplicationDetails.html?id=123&type=ZSCQ", value = "H5链接")
    public String getH5link() {
        return h5link;
    }

    public void setH5link(String h5link) {
        this.h5link = h5link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EnterServiceDetail that = (EnterServiceDetail) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (serviceInfo != null ? !serviceInfo.equals(that.serviceInfo) : that.serviceInfo != null) return false;
        if (tel != null ? !tel.equals(that.tel) : that.tel != null) return false;
        if (link != null ? !link.equals(that.link) : that.link != null) return false;
        if (coverImg != null ? !coverImg.equals(that.coverImg) : that.coverImg != null) return false;
        if (introduction != null ? !introduction.equals(that.introduction) : that.introduction != null) return false;
        if (info != null ? !info.equals(that.info) : that.info != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (serviceInfo != null ? serviceInfo.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (coverImg != null ? coverImg.hashCode() : 0);
        result = 31 * result + (introduction != null ? introduction.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }
}

