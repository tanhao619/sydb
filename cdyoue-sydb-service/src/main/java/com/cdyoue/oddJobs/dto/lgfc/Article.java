package com.cdyoue.oddJobs.dto.lgfc;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;
/**
 * Article
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-28T13:17:18.617Z")

public class Article   {
  @JsonProperty("id")//id
  private Integer id = null;

  @JsonProperty("title")//标题
  private String title = null;

  @JsonProperty("author")//作者
  private String author = null;

  @JsonProperty("content")//内容
  private String content = null;

  @JsonProperty("top")//是否精选
  private Integer top=null;

  @JsonProperty("publishTime")//发布时间
  private String publishTime = null;

  @JsonProperty("createTime")//发布时间
  private String createTime = null;

  @JsonProperty("updateTime")//修改时间
  private String updateTime = null;

  @JsonProperty("viewsAccount")//浏览量
  private Integer viewsAccount = null;

  @JsonProperty("favorAccount")//收藏数
  private Integer favorAccount = null;

  @JsonProperty("status")//文章状态
  private Integer status = null;

  @JsonProperty("favorStatus")//收藏状态
  private boolean favorStatus = false;

  @JsonProperty("coverImg")//图片地址
  private String coverImg = null;

  @JsonProperty("link")//文章链接
  private String link = null;

  @JsonProperty("topName")//精选文章标题
  private String topName = null;


  @JsonProperty("topImg")//精选文章图片
  private String topImg = null;

  @JsonProperty("topInfo")//精选文章信息
  private String topInfo = null;


  public Article id(Integer id) {
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

  public Article content(String content) {
    this.content = content;
    return this;
  }

   /**
   * 内容
   * @return content
  **/
  @ApiModelProperty(example = "冯成成请假啦", value = "内容")
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Article publishTime(String publishTime) {
    this.publishTime = publishTime;
    return this;
  }

  /**
   * 是否精选
   * @return
   */
  @ApiModelProperty(example = "0", value = "是否精选，0否，1是")
  public Integer getTop() {
    return top;
  }

  public void setTop(Integer top) {
    this.top = top;
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
   * 图片地址
   * @return publishTime
   **/
  @ApiModelProperty(example = "xxx/xxx/xx.jpg", value = "图片路径")
  public String getCoverImg() {
    return coverImg;
  }

  public void setCoverImg(String coverImg) {
    this.coverImg = coverImg;
  }

  /**
   * 文章链接
   * @return link
   **/
  @ApiModelProperty(example = "/H5/articleDetails.html?id=当前文章id", value = "文章链接")
  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  /**
   * 浏览量
   * @return viewsAccount
  **/
  @ApiModelProperty(example = "10000", value = "浏览量")
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

  @ApiModelProperty(example = "2017/03/03:12:00:00", value = "创建时间")
  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  @ApiModelProperty(example = "2017/03/03:12:00:00", value = "修改时间")
  public String getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(String updateTime) {
    this.updateTime = updateTime;
  }

  @ApiModelProperty(example = "0", value = "文章状态")
  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  @ApiModelProperty(example = "false", value = "收藏状态：true已收藏，false未收藏")
  public boolean isFavorStatus() {
    return favorStatus;
  }

  public void setFavorStatus(boolean favorStatus) {
    this.favorStatus = favorStatus;
  }

    public String getTopName() {
        return topName;
    }

    public void setTopName(String topName) {
        this.topName = topName;
    }

    public String getTopImg() {
        return topImg;
    }

    public void setTopImg(String topImg) {
        this.topImg = topImg;
    }

    public String getTopInfo() {
        return topInfo;
    }

    public void setTopInfo(String topInfo) {
        this.topInfo = topInfo;
    }

    @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Article article = (Article) o;
    return Objects.equals(this.id, article.id) &&
        Objects.equals(this.title, article.title) &&
        Objects.equals(this.author, article.author) &&
        Objects.equals(this.content, article.content) &&
        Objects.equals(this.publishTime, article.publishTime) &&
        Objects.equals(this.viewsAccount, article.viewsAccount) &&
        Objects.equals(this.top, article.top) &&
        Objects.equals(this.favorAccount, article.favorAccount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, author, content, publishTime, viewsAccount, favorAccount,top);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Article {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    author: ").append(toIndentedString(author)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    publishTime: ").append(toIndentedString(publishTime)).append("\n");
    sb.append("    viewsAccount: ").append(toIndentedString(viewsAccount)).append("\n");
    sb.append("    favorAccount: ").append(toIndentedString(favorAccount)).append("\n");
    sb.append("    top: ").append(toIndentedString(top)).append("\n");
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

