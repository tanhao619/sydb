package com.cdyoue.oddJobs.dto.ggfw;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
/**
 * NewsRequest
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-09T11:27:32.880Z")

public class NewsRequest   {
  @JsonProperty("title")
  private String title = null;

  @JsonProperty("content")
  private String content = null;

  @JsonProperty("abstract")
  private String _abstract = null;

  @JsonProperty("coverImgUrl")
  private String coverImgUrl = null;

  public NewsRequest title(String title) {
    this.title = title;
    return this;
  }

   /**
   * 新闻标题,最多50个字符
   * @return title
  **/
  @ApiModelProperty(example = "今天天气很好", value = "新闻标题,最多50个字符")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public NewsRequest content(String content) {
    this.content = content;
    return this;
  }

   /**
   * 新闻内容
   * @return content
  **/
  @ApiModelProperty(example = "今天天气很好,今天天气很好,今天天气很好,今天天气很好", value = "新闻内容")
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public NewsRequest _abstract(String _abstract) {
    this._abstract = _abstract;
    return this;
  }

   /**
   * 新闻摘要
   * @return _abstract
  **/
  @ApiModelProperty(example = "新闻摘要新闻摘要新闻摘要", value = "新闻摘要")
  public String getAbstract() {
    return _abstract;
  }

  public void setAbstract(String _abstract) {
    this._abstract = _abstract;
  }

  public NewsRequest coverImgUrl(String coverImgUrl) {
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
    NewsRequest newsRequest = (NewsRequest) o;
    return Objects.equals(this.title, newsRequest.title) &&
        Objects.equals(this.content, newsRequest.content) &&
        Objects.equals(this._abstract, newsRequest._abstract) &&
        Objects.equals(this.coverImgUrl, newsRequest.coverImgUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, content, _abstract, coverImgUrl);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewsRequest {\n");
    
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    _abstract: ").append(toIndentedString(_abstract)).append("\n");
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

