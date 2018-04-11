package com.cdyoue.oddJobs.dto.zscq;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;
/**
 * PatentBigdataSummary
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-19T00:40:47.264Z")

public class PatentBigdataSummary   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("index")
  private String index = null;

  @JsonProperty("priorityNO")
  private String priorityNO = null;

  @JsonProperty("referNO")
  private String referNO = null;

  @JsonProperty("typeNO")
  private String typeNO = null;

  @JsonProperty("age")
  private String age = null;

  @JsonProperty("price")
  private String price = null;

  @JsonProperty("publishTime")
  private String publishTime = null;

  public PatentBigdataSummary id(Integer id) {
    this.id = id;
    return this;
  }

   /**
   * id
   * @return id
  **/
  @ApiModelProperty(value = "id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public PatentBigdataSummary name(String name) {
    this.name = name;
    return this;
  }

   /**
   * 专利名称
   * @return name
  **/
  @ApiModelProperty(example = "一种聚义", value = "专利名称")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public PatentBigdataSummary index(String index) {
    this.index = index;
    return this;
  }

   /**
   * 产业化指数
   * @return index
  **/
  @ApiModelProperty(example = "222", value = "产业化指数")
  public String getIndex() {
    return index;
  }

  public void setIndex(String index) {
    this.index = index;
  }

  public PatentBigdataSummary priorityNO(String priorityNO) {
    this.priorityNO = priorityNO;
    return this;
  }

   /**
   * 优先权个数
   * @return priorityNO
  **/
  @ApiModelProperty(example = "111", value = "优先权个数")
  public String getPriorityNO() {
    return priorityNO;
  }

  public void setPriorityNO(String priorityNO) {
    this.priorityNO = priorityNO;
  }

  public PatentBigdataSummary referNO(String referNO) {
    this.referNO = referNO;
    return this;
  }

   /**
   * 引用次数
   * @return referNO
  **/
  @ApiModelProperty(example = "CN1234567890", value = "引用次数")
  public String getReferNO() {
    return referNO;
  }

  public void setReferNO(String referNO) {
    this.referNO = referNO;
  }

  public PatentBigdataSummary typeNO(String typeNO) {
    this.typeNO = typeNO;
    return this;
  }

   /**
   * 分类号个数
   * @return typeNO
  **/
  @ApiModelProperty(example = "143", value = "分类号个数")
  public String getTypeNO() {
    return typeNO;
  }

  public void setTypeNO(String typeNO) {
    this.typeNO = typeNO;
  }

  public PatentBigdataSummary age(String age) {
    this.age = age;
    return this;
  }

   /**
   * 专利年龄
   * @return age
  **/
  @ApiModelProperty(example = "23", value = "专利年龄")
  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public PatentBigdataSummary price(String price) {
    this.price = price;
    return this;
  }

   /**
   * 评估价值
   * @return price
  **/
  @ApiModelProperty(example = "2", value = "评估价值")
  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

    @ApiModelProperty(example = "2008-05-12 15:00:00", value = "发布时间")
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
    PatentBigdataSummary patentBigdataSummary = (PatentBigdataSummary) o;
    return Objects.equals(this.id, patentBigdataSummary.id) &&
        Objects.equals(this.name, patentBigdataSummary.name) &&
        Objects.equals(this.index, patentBigdataSummary.index) &&
        Objects.equals(this.priorityNO, patentBigdataSummary.priorityNO) &&
        Objects.equals(this.referNO, patentBigdataSummary.referNO) &&
        Objects.equals(this.typeNO, patentBigdataSummary.typeNO) &&
        Objects.equals(this.age, patentBigdataSummary.age) &&
        Objects.equals(this.price, patentBigdataSummary.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, index, priorityNO, referNO, typeNO, age, price);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PatentBigdataSummary {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    index: ").append(toIndentedString(index)).append("\n");
    sb.append("    priorityNO: ").append(toIndentedString(priorityNO)).append("\n");
    sb.append("    referNO: ").append(toIndentedString(referNO)).append("\n");
    sb.append("    typeNO: ").append(toIndentedString(typeNO)).append("\n");
    sb.append("    age: ").append(toIndentedString(age)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
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

