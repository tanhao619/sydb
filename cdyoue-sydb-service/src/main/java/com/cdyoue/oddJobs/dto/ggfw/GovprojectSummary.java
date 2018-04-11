package com.cdyoue.oddJobs.dto.ggfw;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * GovprojectSummary
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-15T01:26:16.185Z")

public class GovprojectSummary {
    @JsonProperty("id")
    private Integer id = null;

    @JsonProperty("title")
    private String title = null;

    @JsonProperty("publishTime")
    private String publishTime = null;

    @JsonProperty("duration")
    private String duration = null;

    @JsonProperty("attachUrl")
    private String attachUrl = null;

    @JsonProperty("viewscount")
    private Integer viewscount = null;

    @JsonProperty("createBy")
    private String createBy = null;

    @JsonProperty("link")
    private String link = null;

    public GovprojectSummary id(Integer id) {
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

    public GovprojectSummary title(String title) {
        this.title = title;
        return this;
    }

    /**
     * 申报标题
     *
     * @return title
     **/
    @ApiModelProperty(example = "关于申请2016年度海淀园企业人才公租房租金补贴的通知", value = "申报标题")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public GovprojectSummary publishTime(String publishTime) {
        this.publishTime = publishTime;
        return this;
    }

    /**
     * 发文时间
     *
     * @return publishTime
     **/
    @ApiModelProperty(example = "2017/03/03", value = "发文时间")
    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public GovprojectSummary duration(String duration) {
        this.duration = duration;
        return this;
    }

    /**
     * 申报起止时间
     *
     * @return duration
     **/
    @ApiModelProperty(example = "2016-8-19至2016-7-3", value = "申报起止时间")
    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public GovprojectSummary attachUrl(String attachUrl) {
        this.attachUrl = attachUrl;
        return this;
    }

    /**
     * 附件地址
     *
     * @return attachUrl
     **/
    @ApiModelProperty(example = "http://172.16.0.102/image.jpg", value = "附件地址")
    public String getAttachUrl() {
        return attachUrl;
    }

    public void setAttachUrl(String attachUrl) {
        this.attachUrl = attachUrl;
    }

    public GovprojectSummary viewscount(Integer viewscount) {
        this.viewscount = viewscount;
        return this;
    }

    /**
     * 阅读量
     *
     * @return viewscount
     **/
    @ApiModelProperty(example = "10000", value = "阅读量")
    public Integer getViewscount() {
        return viewscount == null ? 0 : viewscount;
    }

    public void setViewscount(Integer viewscount) {
        this.viewscount = viewscount;
    }

    public GovprojectSummary createBy(String createBy) {
        this.createBy = createBy;
        return this;
    }

    @ApiModelProperty(example = "tangrui", value = "发布人")
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GovprojectSummary that = (GovprojectSummary) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (publishTime != null ? !publishTime.equals(that.publishTime) : that.publishTime != null) return false;
        if (duration != null ? !duration.equals(that.duration) : that.duration != null) return false;
        if (attachUrl != null ? !attachUrl.equals(that.attachUrl) : that.attachUrl != null) return false;
        if (viewscount != null ? !viewscount.equals(that.viewscount) : that.viewscount != null) return false;
        if (createBy != null ? !createBy.equals(that.createBy) : that.createBy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (publishTime != null ? publishTime.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (attachUrl != null ? attachUrl.hashCode() : 0);
        result = 31 * result + (viewscount != null ? viewscount.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GovprojectSummary{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publishTime='" + publishTime + '\'' +
                ", duration='" + duration + '\'' +
                ", attachUrl='" + attachUrl + '\'' +
                ", viewscount=" + viewscount +
                ", createBy='" + createBy + '\'' +
                '}';
    }

    @ApiModelProperty(example = "/H5/projectdeclarationDetails.html?id=1234", value = "H5链接")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

