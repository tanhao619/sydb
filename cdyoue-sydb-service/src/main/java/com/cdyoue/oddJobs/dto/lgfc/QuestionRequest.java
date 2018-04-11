package com.cdyoue.oddJobs.dto.lgfc;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
/**
 * QuesionRequest
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-30T14:30:56.295Z")

public class QuestionRequest   {
  @JsonProperty("domainId")
  private Integer domainId = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("content")
  private String content = null;

  public QuestionRequest domainId(Integer domainId) {
    this.domainId = domainId;
    return this;
  }

   /**
   * 所属领域id
   * @return domainId
  **/
  @ApiModelProperty(example = "123", value = "所属领域id")
  public Integer getDomainId() {
    return domainId;
  }

  public void setDomainId(Integer domainId) {
    this.domainId = domainId;
  }

  public QuestionRequest title(String title) {
    this.title = title;
    return this;
  }

   /**
   * 标题
   * @return title
  **/
  @ApiModelProperty(example = "都灵", value = "标题")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public QuestionRequest content(String content) {
    this.content = content;
    return this;
  }

   /**
   * 内容
   * @return content
  **/
  @ApiModelProperty(example = "除了欧文，还有谁向科比发出过这样的挑战", value = "内容")
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QuestionRequest quesionRequest = (QuestionRequest) o;
    return Objects.equals(this.domainId, quesionRequest.domainId) &&
        Objects.equals(this.title, quesionRequest.title) &&
        Objects.equals(this.content, quesionRequest.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(domainId, title, content);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QuesionRequest {\n");
    
    sb.append("    domainId: ").append(toIndentedString(domainId)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
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

