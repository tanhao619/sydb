package com.cdyoue.oddJobs.dto.zscq;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.*;

/**
 * IndustryReportSummary
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-20T03:33:06.490Z")

public class IndustryReportSummary {
    @JsonProperty("id")
    private Integer id = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("coverImgUrl")
    private String coverImgUrl = null;

    @JsonProperty("publishTime")
    private String publishTime = null;

    @JsonProperty("viewsCount")
    private Integer viewsCount = null;

    @JsonProperty("publishPepole")
    private String publishPepole = null;

    @JsonProperty("link")
    private String link = null;

    public IndustryReportSummary id(Integer id) {
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

    public IndustryReportSummary name(String name) {
        this.name = name;
        return this;
    }

    /**
     * 融资项目名称
     *
     * @return name
     **/
    @ApiModelProperty(example = "卡拉卡", value = "融资项目名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IndustryReportSummary coverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
        return this;
    }

    /**
     * 封面
     *
     * @return coverImgUrl
     **/
    @ApiModelProperty(example = "http://172.16.0.102/image.jpg", value = "封面")
    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public IndustryReportSummary publishTime(String publishTime) {
        this.publishTime = publishTime;
        return this;
    }

    /**
     * 发布时间（admin）
     *
     * @return publishTime
     **/
    @ApiModelProperty(example = "2017/03/03 12:00:00", value = "发布时间（admin）")
    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public IndustryReportSummary viewsCount(Integer viewsCount) {
        this.viewsCount = viewsCount;
        return this;
    }

    /**
     * 浏览量（admin）
     *
     * @return viewsCount
     **/
    @ApiModelProperty(example = "10000", value = "浏览量（admin）")
    public Integer getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(Integer viewsCount) {
        this.viewsCount = viewsCount;
    }

    public IndustryReportSummary publishPepole(String publishPepole) {
        this.publishPepole = publishPepole;
        return this;
    }

    /**
     * 发布人（admin）
     *
     * @return publishPepole
     **/
    @ApiModelProperty(example = "admin", value = "发布人（admin）")
    public String getPublishPepole() {
        return publishPepole;
    }

    public void setPublishPepole(String publishPepole) {
        this.publishPepole = publishPepole;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IndustryReportSummary industryReportSummary = (IndustryReportSummary) o;
        return Objects.equals(this.id, industryReportSummary.id) &&
                Objects.equals(this.name, industryReportSummary.name) &&
                Objects.equals(this.coverImgUrl, industryReportSummary.coverImgUrl) &&
                Objects.equals(this.publishTime, industryReportSummary.publishTime) &&
                Objects.equals(this.viewsCount, industryReportSummary.viewsCount) &&
                Objects.equals(this.publishPepole, industryReportSummary.publishPepole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coverImgUrl, publishTime, viewsCount, publishPepole);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class IndustryReportSummary {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    coverImgUrl: ").append(toIndentedString(coverImgUrl)).append("\n");
        sb.append("    publishTime: ").append(toIndentedString(publishTime)).append("\n");
        sb.append("    viewsCount: ").append(toIndentedString(viewsCount)).append("\n");
        sb.append("    publishPepole: ").append(toIndentedString(publishPepole)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    @ApiModelProperty(example = "/H5/patentreportDetails.html?id=1234", value = "H5链接")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

