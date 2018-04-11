package com.cdyoue.oddJobs.dto.scfw;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Objects;

/**
 * FinProjectSummary
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-18T06:24:34.814Z")

public class FinProjectSummary {
    @JsonProperty("id")
    private Integer id = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("coverImgUrl")
    private String coverImgUrl = null;

    @JsonProperty("introduction")
    private String introduction = null;

    @JsonProperty("finance")
    private String finance = null;

    @JsonProperty("approveStatus")
    private Byte approveStatus = null;

    @JsonProperty("createTime")
    private String createTime = null;

    @JsonProperty("publishTime")
    private String publishTime = null;

    @JsonProperty("viewsCount")
    private Integer viewsCount = null;

    @JsonProperty("publishPepole")
    private String publishPepole = null;

    @JsonProperty("link")
    private String link = null;

    public FinProjectSummary id(Integer id) {
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

    public FinProjectSummary name(String name) {
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

    public FinProjectSummary coverImgUrl(String coverImgUrl) {
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

    public FinProjectSummary introduction(String introduction) {
        this.introduction = introduction;
        return this;
    }

    /**
     * 简介
     *
     * @return introduction
     **/
    @ApiModelProperty(example = "个人简介个人简介个人简介个人简介", value = "简介")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public FinProjectSummary finance(String finance) {
        this.finance = finance;
        return this;
    }

    /**
     * 融资需求,单位万
     *
     * @return finance
     **/
    @ApiModelProperty(example = "5000", value = "融资需求,单位万")
    public String getFinance() {
        return finance;
    }

    public void setFinance(String finance) {
        this.finance = finance;
    }

    public FinProjectSummary approveStatus(Byte approveStatus) {
        this.approveStatus = approveStatus;
        return this;
    }

    /**
     * 审核状态：0:未审核，1:通过，2:拒绝
     * minimum: 0
     * maximum: 2
     *
     * @return approveStatus
     **/
    @ApiModelProperty(example = "1", value = "审核状态：0:未审核，1:通过，2:拒绝")
    @Min(0)
    @Max(2)
    public Byte getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Byte approveStatus) {
        this.approveStatus = approveStatus;
    }

    public FinProjectSummary publishTime(String publishTime) {
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

    public FinProjectSummary viewsCount(Integer viewsCount) {
        this.viewsCount = viewsCount;
        return this;
    }

    @ApiModelProperty(value = "创建时间")
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 浏览量（admin）
     *
     * @return viewsCount
     **/
    @ApiModelProperty(example = "10000", value = "浏览量（admin）")
    public Integer getViewsCount() {
        return viewsCount == null ? 0 : viewsCount;
    }

    public void setViewsCount(Integer viewsCount) {
        this.viewsCount = viewsCount;
    }

    public FinProjectSummary publishPepole(String publishPepole) {
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
        FinProjectSummary finProjectSummary = (FinProjectSummary) o;
        return Objects.equals(this.id, finProjectSummary.id) &&
                Objects.equals(this.name, finProjectSummary.name) &&
                Objects.equals(this.coverImgUrl, finProjectSummary.coverImgUrl) &&
                Objects.equals(this.introduction, finProjectSummary.introduction) &&
                Objects.equals(this.finance, finProjectSummary.finance) &&
                Objects.equals(this.approveStatus, finProjectSummary.approveStatus) &&
                Objects.equals(this.publishTime, finProjectSummary.publishTime) &&
                Objects.equals(this.viewsCount, finProjectSummary.viewsCount) &&
                Objects.equals(this.publishPepole, finProjectSummary.publishPepole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coverImgUrl, introduction, finance, approveStatus, publishTime, viewsCount, publishPepole);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class FinProjectSummary {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    coverImgUrl: ").append(toIndentedString(coverImgUrl)).append("\n");
        sb.append("    introduction: ").append(toIndentedString(introduction)).append("\n");
        sb.append("    finance: ").append(toIndentedString(finance)).append("\n");
        sb.append("    approveStatus: ").append(toIndentedString(approveStatus)).append("\n");
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

    @ApiModelProperty(example = "/H5/financingprojectDetails.html?id=1234", value = "H5链接")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

