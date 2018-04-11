package com.cdyoue.oddJobs.dto.scfw;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * Investor
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-16T01:11:39.286Z")

public class Investor   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("titles")
  private List<String> titles = new ArrayList<String>();

  @JsonProperty("headImg")
  private String headImg = null;

  public Investor name(String name) {
    this.name = name;
    return this;
  }
    /**
     * 投资人头像
     * @return headImg
     **/
    @ApiModelProperty(example = "www.youedata.com/a//investor.1.jpg", value = "投资人头像")
    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public Investor headImg(String headImg) {
        this.headImg = headImg;
        return this;
    }

    /**
   * 投资人姓名
   * @return name
  **/
  @ApiModelProperty(example = "刘杰", value = "投资人姓名")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Investor titles(List<String> titles) {
    this.titles = titles;
    return this;
  }

  public Investor addTitlesItem(String titlesItem) {
    this.titles.add(titlesItem);
    return this;
  }

   /**
   * Get titles
   * @return titles
  **/
  @ApiModelProperty(value = "")
  public List<String> getTitles() {
    return titles;
  }

  public void setTitles(List<String> titles) {
    this.titles = titles;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Investor investor = (Investor) o;
    return Objects.equals(this.name, investor.name) &&
        Objects.equals(this.titles, investor.titles);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, titles);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Investor {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    titles: ").append(toIndentedString(titles)).append("\n");
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

