package com.cdyoue.oddJobs.dto.requirement;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * RequireBase
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-20T01:12:00.448Z")

public class RequireBase   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("budget")
  private BigDecimal budget = null;

  @JsonProperty("publishDate")
  private String publishDate = null;

  @JsonProperty("accStatus")
  private Integer accStatus = null;

  @JsonProperty("status")
  private Integer status = null;

  @JsonProperty("accRequireCount")
  private Integer accRequireCount = 0;

  private Byte reviewStatus;

  private Integer readNum;

  public RequireBase id(Integer id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "需求主键ID")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public RequireBase name(String name) {
    this.name = name;
    return this;
  }

   /**
   * 需求名称（AW）
   * @return name
  **/
  @ApiModelProperty(example = "XXXX类型的LOGO制作", value = "需求名称（AW）")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public RequireBase budget(BigDecimal budget) {
    this.budget = budget;
    return this;
  }

   /**
   * 项目预算（AW）
   * @return budget
  **/
  @ApiModelProperty(example = "20万", value = "项目预算（AW）")
  public BigDecimal getBudget() {
    return budget;
  }

  public void setBudget(BigDecimal budget) {
    this.budget = budget;
  }

  public RequireBase publishDate(String publishDate) {
    this.publishDate = publishDate;
    return this;
  }

   /**
   * 发布日期（AW）
   * @return publishDate
  **/
  @ApiModelProperty(example = "2017/4/19", value = "发布日期（AW）")
  public String getPublishDate() {
    return publishDate;
  }

  public void setPublishDate(String publishDate) {
    this.publishDate = publishDate;
  }

  public RequireBase accStatus(Integer accStatus) {
    this.accStatus = accStatus;
    return this;
  }

   /**
   * 需求承接状态：1:已承接 0:未承接
   * @return accStatus
  **/
  @ApiModelProperty(example = "1", value = "需求承接状态：1:已承接 0:未承接")
  public Integer getAccStatus() {
    return accStatus;
  }

  public void setAccStatus(Integer accStatus) {
    this.accStatus = accStatus;
  }

  public RequireBase status(Integer status) {
    this.status = status;
    return this;
  }

   /**
   * 需求状态：0:关闭，1:开放
   * minimum: 0
   * maximum: 1
   * @return status
  **/
  @ApiModelProperty(example = "1", value = "需求状态：0:关闭，1:开放")
  @Min(0)
  @Max(1)
  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

        /**
         * 承接需求人数
         *
         **/

        @ApiModelProperty(example = "5", value = "承接需求人数")
        public Integer getAccRequireCount() {
            return accRequireCount;
        }

        public void setAccRequireCount(Integer accRequireCount) {
            this.accRequireCount = accRequireCount;
        }

        public RequireBase accRequireCount(Integer accRequireCount) {
            this.accRequireCount = accRequireCount;
            return this;
        }

    @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RequireBase requireBase = (RequireBase) o;
    return Objects.equals(this.id, requireBase.id) &&
        Objects.equals(this.name, requireBase.name) &&
        Objects.equals(this.budget, requireBase.budget) &&
        Objects.equals(this.publishDate, requireBase.publishDate) &&
        Objects.equals(this.accStatus, requireBase.accStatus) &&
        Objects.equals(this.status, requireBase.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, budget, publishDate, accStatus, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RequireBase {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    budget: ").append(toIndentedString(budget)).append("\n");
    sb.append("    publishDate: ").append(toIndentedString(publishDate)).append("\n");
    sb.append("    accStatus: ").append(toIndentedString(accStatus)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("}");
    return sb.toString();
  }
  @ApiModelProperty("审核状态：0未审核，1审核失败 2审核成功")
  public Byte getReviewStatus() {
    return reviewStatus;
  }

  public void setReviewStatus(Byte reviewStatus) {
    this.reviewStatus = reviewStatus;
  }
  @ApiModelProperty("浏览次数")
  public Integer getReadNum() {
    return readNum;
  }

  public void setReadNum(Integer readNum) {
    this.readNum = readNum;
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

