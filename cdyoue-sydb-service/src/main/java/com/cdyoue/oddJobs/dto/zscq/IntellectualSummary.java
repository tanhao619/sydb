package com.cdyoue.oddJobs.dto.zscq;

import com.cdyoue.oddJobs.dto.Contact;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * IntellectualSummary
 * 获取知产求购列表
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-09T00:59:32.305Z")

public class IntellectualSummary {

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("id")
    private long id;

    @JsonProperty("contact")
    private Contact contact = null;

    @JsonProperty("viewCount")
    private Integer viewCount;

    @JsonProperty("createBy")
    private String createBy;

    @JsonProperty("reviewStatus")
    private Integer reviewStatus;

    @JsonProperty("publishTime")
    private String publishTime;

    @JsonProperty("introduction")
    private String introduction;

    @JsonProperty("businessType")
    private Integer businessType;

    @JsonProperty("intellType")
    private Integer intellType;

    @JsonProperty("link")
    private String link;

    @JsonProperty("top")
    private Integer top;

    @JsonProperty("topImg")
    private String topImg;

    public IntellectualSummary name(String name) {
        this.name = name;
        return this;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * 需求介绍
     *
     * @return name
     **/
    @ApiModelProperty(example = "XXXX类型的LOGO制作", value = "需求介绍")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IntellectualSummary contact(Contact contact) {
        this.contact = contact;
        return this;
    }

    /**
     * Get contact
     *
     * @return contact
     **/
    @ApiModelProperty(value = "")
    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @ApiModelProperty(example = "888", value = "浏览量")
    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    @ApiModelProperty(example = "唐瑞", value = "发布人")
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @ApiModelProperty(example = "1", value = "审核状态")
    public Integer getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(Integer reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    @ApiModelProperty(example = "2014-02-12 16:00:00", value = "发布时间")
    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    @ApiModelProperty(example = "数据库没有“资产简介字”段，用最相似的“详细要求”代替", value = "详细要求（数据库没有资产简介字段）")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @ApiModelProperty(example = "1", value = "交易类型：1转让，2许可")
    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    @ApiModelProperty(example = "2", value = "产权类别：1专利，2商标 3 著作权")
    public Integer getIntellType() {
        return intellType;
    }

    public void setIntellType(Integer intellType) {
        this.intellType = intellType;
    }

    @ApiModelProperty(example = "0", value = "是否置顶:0，不置顶；1，置顶")
    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    @ApiModelProperty(example = "", value = "置顶图片")
    public String getTopImg() {
        return topImg;
    }

    public void setTopImg(String topImg) {
        this.topImg = topImg;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IntellectualSummary intellectualSummary = (IntellectualSummary) o;
        return Objects.equals(this.name, intellectualSummary.name) &&
                Objects.equals(this.contact, intellectualSummary.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, contact);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class IntellectualSummary {\n");

        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    contact: ").append(toIndentedString(contact)).append("\n");
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

    @ApiModelProperty(example = "/H5/patentaskbuyDetails.html?id=1234", value = "H5链接")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

