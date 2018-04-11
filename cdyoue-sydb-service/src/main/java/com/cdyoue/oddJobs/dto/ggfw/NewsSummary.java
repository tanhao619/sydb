package com.cdyoue.oddJobs.dto.ggfw;

import java.util.Objects;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
/**
 * NewsSummary
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-09T11:27:32.880Z")

public class NewsSummary   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("coverImg")
  private String coverImg;

  @JsonProperty("introduction")
  private String introduction = null;

  @JsonProperty("publishTime")
  private String publishTime = null;

  @JsonProperty("publishCompany")
  private String publishCompany = null;

  @JsonProperty("approveStatus")
  private Integer approveStatus = null;

  public NewsSummary id(Integer id) {
    this.id = id;
    return this;
  }

   /**
   * id
   * @return id
  **/
  @ApiModelProperty(value = "id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public NewsSummary title(String title) {
    this.title = title;
    return this;
  }

   /**
   * 标题
   * @return title
  **/
  @ApiModelProperty(value = "标题")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public NewsSummary introduction(String introduction) {
    this.introduction = introduction;
    return this;
  }

   /**
   * 个人简介
   * @return introduction
  **/
  @ApiModelProperty(example = "2013年5月10日，辞任阿里巴巴集团CEO，继续担任阿里集团董事局主席。", value = "个人简介")
  public String getIntroduction() {
    return introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }

  public NewsSummary publishTime(String publishTime) {
    this.publishTime = publishTime;
    return this;
  }

   /**
   * 发布时间
   * @return publishTime
  **/
  @ApiModelProperty(example = "2017/03/03", value = "发布时间")
  public String getPublishTime() {
    return publishTime;
  }

  public void setPublishTime(String publishTime) {
    this.publishTime = publishTime;
  }

  public NewsSummary publishCompany(String publishCompany) {
    this.publishCompany = publishCompany;
    return this;
  }

   /**
   * 发布单位
   * @return publishCompany
  **/
  @ApiModelProperty(example = "丰台区政府", value = "发布单位")
  public String getPublishCompany() {
    return publishCompany;
  }

  public void setPublishCompany(String publishCompany) {
    this.publishCompany = publishCompany;
  }

  public NewsSummary approveStatus(Integer approveStatus) {
    this.approveStatus = approveStatus;
    return this;
  }

   /**
   * 审核状态：0:未审核，1:通过，2:拒绝
   * minimum: 0
   * maximum: 2
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
    NewsSummary newsSummary = (NewsSummary) o;
    return Objects.equals(this.id, newsSummary.id) &&
        Objects.equals(this.title, newsSummary.title) &&
        Objects.equals(this.introduction, newsSummary.introduction) &&
        Objects.equals(this.publishTime, newsSummary.publishTime) &&
        Objects.equals(this.publishCompany, newsSummary.publishCompany) &&
        Objects.equals(this.approveStatus, newsSummary.approveStatus);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, introduction, publishTime, publishCompany, approveStatus);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewsSummary {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    introduction: ").append(toIndentedString(introduction)).append("\n");
    sb.append("    publishTime: ").append(toIndentedString(publishTime)).append("\n");
    sb.append("    publishCompany: ").append(toIndentedString(publishCompany)).append("\n");
    sb.append("    approveStatus: ").append(toIndentedString(approveStatus)).append("\n");
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
}

