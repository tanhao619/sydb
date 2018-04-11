package com.cdyoue.oddJobs.dto.other;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
/**
 * SuggestionSummary
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-31T12:57:35.459Z")

public class SuggestionSummary   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("content")
  private String content = null;

  @JsonProperty("publishPeople")
  private String publishPeople = null;

  @JsonProperty("publishTime")
  private String publishTime = null;

  public SuggestionSummary id(Integer id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public SuggestionSummary content(String content) {
    this.content = content;
    return this;
  }

   /**
   * 反馈内容
   * @return content
  **/
  @ApiModelProperty(example = "反馈内容", value = "反馈内容")
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public SuggestionSummary publishPeople(String publishPeople) {
    this.publishPeople = publishPeople;
    return this;
  }

   /**
   * 反馈人
   * @return publishPeople
  **/
  @ApiModelProperty(example = "李晓霞", value = "反馈人")
  public String getPublishPeople() {
    return publishPeople;
  }

  public void setPublishPeople(String publishPeople) {
    this.publishPeople = publishPeople;
  }

  public SuggestionSummary publishTime(String publishTime) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SuggestionSummary suggestionSummary = (SuggestionSummary) o;
    return Objects.equals(this.id, suggestionSummary.id) &&
        Objects.equals(this.content, suggestionSummary.content) &&
        Objects.equals(this.publishPeople, suggestionSummary.publishPeople) &&
        Objects.equals(this.publishTime, suggestionSummary.publishTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, content, publishPeople, publishTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SuggestionSummary {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    publishPeople: ").append(toIndentedString(publishPeople)).append("\n");
    sb.append("    publishTime: ").append(toIndentedString(publishTime)).append("\n");
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

