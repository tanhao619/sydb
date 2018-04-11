package com.cdyoue.oddJobs.dto.requirement;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * 需求复合信息，包括需求企业信息，需求详细信息和需求承接人信息
 */
@ApiModel(description = "需求复合信息，包括需求企业信息，需求详细信息和需求承接人信息")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-20T01:12:00.448Z")

public class RecommandResponse   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("name")
  private String name = null;
  @JsonProperty("recommendeddegree")
  private BigDecimal recommendeddegree;
  @JsonProperty("coverImg")
  private String coverImg;
  //新加字段，需求介绍
  @JsonProperty("introduction")
  private String introduction=null;
  @JsonProperty("proBudget")//项目预算
  private BigDecimal proBudget;
  @JsonProperty("viewNum")//浏览次数
  private Integer viewNum;
  public RecommandResponse id(Integer id) {
    this.id = id;
    return this;
  }

   /**
   * 推荐实体id
   * @return id
  **/
  @ApiModelProperty(value = "推荐实体id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public RecommandResponse name(String name) {
    this.name = name;
    return this;
  }

   /**
   * 推荐名称
   * @return name
  **/
  @ApiModelProperty(value = "推荐名称")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /**
   *  项目介绍
   * @return
   */
  @ApiModelProperty(value = "项目介绍")
  public String getIntroduction() {
    return introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }

  /**
   *  预算
   * @return
   */
  @ApiModelProperty(value = "预算")
  public BigDecimal getProBudget() {
    return proBudget;
  }

  public void setProBudget(BigDecimal proBudget) {
    this.proBudget = proBudget;
  }

  /**
   * 浏览次数
   * @return
   */
  @ApiModelProperty(value = "浏览次数")
  public Integer getViewNum() {
    return viewNum;
  }

  public void setViewNum(Integer viewNum) {
    this.viewNum = viewNum;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RecommandResponse recommandResponse = (RecommandResponse) o;
    return Objects.equals(this.id, recommandResponse.id) &&
        Objects.equals(this.name, recommandResponse.name)&&
            Objects.equals(this.introduction, recommandResponse.introduction)&&
    Objects.equals(this.proBudget, recommandResponse.proBudget)&&
    Objects.equals(this.viewNum, recommandResponse.viewNum);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RecommandResponse {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    introduction: ").append(toIndentedString(introduction)).append("\n");
    sb.append("    proBudget: ").append(toIndentedString(proBudget)).append("\n");
    sb.append("    viewNum: ").append(toIndentedString(viewNum)).append("\n");
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

  @ApiModelProperty(value = "推荐度")
  public BigDecimal getRecommendeddegree() {
    return recommendeddegree;
  }

  public void setRecommendeddegree(BigDecimal recommendeddegree) {
    this.recommendeddegree = recommendeddegree;
  }
  @ApiModelProperty(value = "封面图片")
  public String getCoverImg() {
    return coverImg;
  }

  public void setCoverImg(String coverImg) {
    this.coverImg = coverImg;
  }
}

