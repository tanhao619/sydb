package com.cdyoue.oddJobs.dto.xqdt;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
/**
 * SuccaseDetail
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-18T06:24:34.814Z")

public class SuccaseDetail   {
  @JsonProperty("title")
  private String title = null;

  @JsonProperty("content")
  private String content = null;

  @JsonProperty("introduction")
  private String introduction = null;

  @JsonProperty("coverImgUrl")
  private String coverImgUrl = null;

  public SuccaseDetail title(String title) {
    this.title = title;
    return this;
  }

   /**
   * 标题
   * @return title
  **/
  @ApiModelProperty(example = "贝壳菁汇孵化器", value = "标题")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public SuccaseDetail content(String content) {
    this.content = content;
    return this;
  }

   /**
   * 详细内容
   * @return content
  **/
  @ApiModelProperty(example = "1996年10月，王强在北京新东方学校开创了称", value = "详细内容")
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public SuccaseDetail introduction(String introduction) {
    this.introduction = introduction;
    return this;
  }

   /**
   * 简介
   * @return introduction
  **/
  @ApiModelProperty(example = "金三优服,隶属于北京金三科技股份有限公司,专注提供电信增值业务许可证,icp经营许可证,edi许可证,", value = "简介")
  public String getIntroduction() {
    return introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }

  public SuccaseDetail coverImgUrl(String coverImgUrl) {
    this.coverImgUrl = coverImgUrl;
    return this;
  }

   /**
   * 封面
   * @return coverImgUrl
  **/
  @ApiModelProperty(example = "http://172.16.0.102/image.jpg", value = "封面")
  public String getCoverImgUrl() {
    return coverImgUrl;
  }

  public void setCoverImgUrl(String coverImgUrl) {
    this.coverImgUrl = coverImgUrl;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SuccaseDetail succaseDetail = (SuccaseDetail) o;
    return Objects.equals(this.title, succaseDetail.title) &&
        Objects.equals(this.content, succaseDetail.content) &&
        Objects.equals(this.introduction, succaseDetail.introduction) &&
        Objects.equals(this.coverImgUrl, succaseDetail.coverImgUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, content, introduction, coverImgUrl);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SuccaseDetail {\n");
    
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    introduction: ").append(toIndentedString(introduction)).append("\n");
    sb.append("    coverImgUrl: ").append(toIndentedString(coverImgUrl)).append("\n");
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
}

