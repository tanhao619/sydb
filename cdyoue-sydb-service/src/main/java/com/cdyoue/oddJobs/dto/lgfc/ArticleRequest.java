package com.cdyoue.oddJobs.dto.lgfc;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
/**
 * ArticleRequest
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-28T13:17:18.617Z")

public class ArticleRequest   {
  @JsonProperty("title")
  private String title = null;

  @JsonProperty("content")
  private String content = null;

  public ArticleRequest title(String title) {
    this.title = title;
    return this;
  }

   /**
   * 名称或者标题
   * @return title
  **/
  @ApiModelProperty(example = "大数据创新创业", value = "名称或者标题")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public ArticleRequest content(String content) {
    this.content = content;
    return this;
  }

   /**
   * 内容
   * @return content
  **/
  @ApiModelProperty(example = "冯成成穿越啦", value = "内容")
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
    ArticleRequest articleRequest = (ArticleRequest) o;
    return Objects.equals(this.title, articleRequest.title) &&
        Objects.equals(this.content, articleRequest.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, content);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ArticleRequest {\n");
    
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

