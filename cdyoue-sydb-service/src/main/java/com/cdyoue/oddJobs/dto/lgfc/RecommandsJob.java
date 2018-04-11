package com.cdyoue.oddJobs.dto.lgfc;

import java.math.BigDecimal;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
/**
 * RecommandsJob
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-06T22:58:08.241Z")

public class RecommandsJob   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("recommendeddegree")
  private BigDecimal recommendeddegree = null;

  public RecommandsJob id(Integer id) {
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

  public RecommandsJob name(String name) {
    this.name = name;
    return this;
  }

   /**
   * 职位名称
   * @return name
  **/
  @ApiModelProperty(example = "UI设计师", value = "职位名称")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public RecommandsJob recommendeddegree(BigDecimal recommendeddegree) {
    this.recommendeddegree = recommendeddegree;
    return this;
  }

   /**
   * 推荐度
   * @return recommendeddegree
  **/
  @ApiModelProperty(example = "0.95", value = "推荐度")
  public BigDecimal getRecommendeddegree() {
    return recommendeddegree;
  }

  public void setRecommendeddegree(BigDecimal recommendeddegree) {
    this.recommendeddegree = recommendeddegree;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RecommandsJob recommandsJob = (RecommandsJob) o;
    return Objects.equals(this.id, recommandsJob.id) &&
        Objects.equals(this.name, recommandsJob.name) &&
        Objects.equals(this.recommendeddegree, recommandsJob.recommendeddegree);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, recommendeddegree);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RecommandsJob {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    recommendeddegree: ").append(toIndentedString(recommendeddegree)).append("\n");
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

