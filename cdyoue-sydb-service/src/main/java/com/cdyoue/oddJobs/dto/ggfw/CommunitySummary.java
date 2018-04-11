package com.cdyoue.oddJobs.dto.ggfw;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.cdyoue.oddJobs.dto.common.KeyValue;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * CommunitySummary
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-09T11:27:32.880Z")

public class CommunitySummary {
    @JsonProperty("id")
    private Integer id = null;

    @JsonProperty("title")
    private String title = null;

    @JsonProperty("summary")
    private String summary = null;

    @JsonProperty("coverImg")
    private String coverImg;

    @JsonProperty("publishTime")
    private String publishTime = null;

    @JsonProperty("createBy")
    private String createBy;

    @JsonProperty("type")
    private Integer type = null;

    @JsonProperty("viewCount")
    private Integer viewCount = null;

    @JsonProperty("approveStatus")
    private Integer approveStatus = null;

    @JsonProperty("addProperties")
    private List<KeyValue> addProperties = new ArrayList<KeyValue>();

    @JsonProperty("link")
    private String link = null;

    public CommunitySummary viewCount(Integer viewCount) {
        this.viewCount = viewCount;
        return this;
    }

    /**
     * viewCount
     *
     * @return viewCount
     **/
    @ApiModelProperty(value = "阅读量")
    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public CommunitySummary id(Integer id) {
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

    public CommunitySummary title(String title) {
        this.title = title;
        return this;
    }

    /**
     * 标题
     *
     * @return title
     **/
    @ApiModelProperty(value = "标题")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CommunitySummary summary(String summary) {
        this.summary = summary;
        return this;
    }

    /**
     * 个人简介
     *
     * @return introduction
     **/
    @ApiModelProperty(example = "2013年5月10日，辞任阿里巴巴集团CEO，继续担任阿里集团董事局主席。", value = "个人简介")
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public CommunitySummary publishTime(String publishTime) {
        this.publishTime = publishTime;
        return this;
    }

    /**
     * 发布时间
     *
     * @return publishTime
     **/
    @ApiModelProperty(example = "2017/03/03", value = "发布时间")
    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public CommunitySummary type(Integer type) {
        this.type = type;
        return this;
    }

    /**
     * 社区动态类型：1:新闻，2:活动
     * minimum: 1
     * maximum: 2
     *
     * @return type
     **/
    @ApiModelProperty(example = "1", value = "社区动态类型：1:新闻，2:活动")
    @Min(1)
    @Max(2)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public CommunitySummary addProperties(List<KeyValue> addProperties) {
        this.addProperties = addProperties;
        return this;
    }

    public CommunitySummary addAddPropertiesItem(KeyValue addPropertiesItem) {
        this.addProperties.add(addPropertiesItem);
        return this;
    }

    /**
     * 页面其他属性信息 如果是新闻, key = unit, 如果是活动，key=location和key=scale
     *
     * @return addProperties
     **/
    @ApiModelProperty(value = "页面其他属性信息 如果是新闻, key = unit, 如果是活动，key=lication和key=scale")
    public List<KeyValue> getAddProperties() {
        return addProperties;
    }

    public void setAddProperties(List<KeyValue> addProperties) {
        this.addProperties = addProperties;
    }

    public CommunitySummary approveStatus(Integer approveStatus) {
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
    public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CommunitySummary communitySummary = (CommunitySummary) o;
        return Objects.equals(this.id, communitySummary.id) &&
                Objects.equals(this.title, communitySummary.title) &&
                Objects.equals(this.summary, communitySummary.summary) &&
                Objects.equals(this.publishTime, communitySummary.publishTime) &&
                Objects.equals(this.type, communitySummary.type) &&
                Objects.equals(this.addProperties, communitySummary.addProperties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, summary, publishTime, type, addProperties);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CommunitySummary {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    title: ").append(toIndentedString(title)).append("\n");
        sb.append("    introduction: ").append(toIndentedString(summary)).append("\n");
        sb.append("    publishTime: ").append(toIndentedString(publishTime)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    addProperties: ").append(toIndentedString(addProperties)).append("\n");
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

    @ApiModelProperty(value = "封面图片")
    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    @ApiModelProperty(example = "/H5/newsDetails.html?id=1234", value = "H5链接")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @ApiModelProperty(example = "", value = "创建人")

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}

