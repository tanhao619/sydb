package com.cdyoue.oddJobs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * Article
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-20T01:12:00.448Z")

public class Article   {
  @JsonProperty("title")
  private String title = null;

  @JsonProperty("author")
  private String author = null;

  @JsonProperty("publishTime")
  private String publishTime = null;

  @JsonProperty("viewsAccount")
  private Integer viewsAccount = null;

  @JsonProperty("favorAccount")
  private Integer favorAccount = null;

  public Article title(String title) {
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

  public Article author(String author) {
    this.author = author;
    return this;
  }

   /**
   * 作者
   * @return author
  **/
  @ApiModelProperty(example = "王俊凯", value = "作者")
  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Article publishTime(String publishTime) {
    this.publishTime = publishTime;
    return this;
  }

   /**
   * 发布时间
   * @return publishTime
  **/
  @ApiModelProperty(example = "2017/03/03:12:00:00", value = "发布时间")
  public String getPublishTime() {
    return publishTime;
  }

  public void setPublishTime(String publishTime) {
    this.publishTime = publishTime;
  }

  public Article viewsAccount(Integer viewsAccount) {
    this.viewsAccount = viewsAccount;
    return this;
  }

   /**
   * 阅读量
   * @return viewsAccount
  **/
  @ApiModelProperty(example = "10000", value = "阅读量")
  public Integer getViewsAccount() {
    return viewsAccount;
  }

  public void setViewsAccount(Integer viewsAccount) {
    this.viewsAccount = viewsAccount;
  }

  public Article favorAccount(Integer favorAccount) {
    this.favorAccount = favorAccount;
    return this;
  }

   /**
   * 收藏量
   * @return favorAccount
  **/
  @ApiModelProperty(example = "10000", value = "收藏量")
  public Integer getFavorAccount() {
    return favorAccount;
  }

  public void setFavorAccount(Integer favorAccount) {
    this.favorAccount = favorAccount;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Article article = (Article) o;
    return Objects.equals(this.title, article.title) &&
        Objects.equals(this.author, article.author) &&
        Objects.equals(this.publishTime, article.publishTime) &&
        Objects.equals(this.viewsAccount, article.viewsAccount) &&
        Objects.equals(this.favorAccount, article.favorAccount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, author, publishTime, viewsAccount, favorAccount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Article {\n");
    
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    author: ").append(toIndentedString(author)).append("\n");
    sb.append("    publishTime: ").append(toIndentedString(publishTime)).append("\n");
    sb.append("    viewsAccount: ").append(toIndentedString(viewsAccount)).append("\n");
    sb.append("    favorAccount: ").append(toIndentedString(favorAccount)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

