package com.cdyoue.oddJobs.dto.zscq;

import com.cdyoue.oddJobs.dto.Contact;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Objects;
/**
 * IntellectualMine
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-11T00:49:03.408Z")

public class IntellectualMine   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("contact")
  private Contact contact= null;

  @JsonProperty("publishTime")
  private String publishTime = null;

  @JsonProperty("approveStatus")
  private Integer approveStatus = null;


  public IntellectualMine id(Integer id) {
    this.id = id;
    return this;
  }

   /**
   * id
   * @return id
  **/
  @ApiModelProperty(example = "12333", value = "id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public IntellectualMine name(String name) {
    this.name = name;
    return this;
  }

   /**
   * 知产名称
   * @return name
  **/
  @ApiModelProperty(example = "XXXX类型的LOGO制作", value = "知产名称")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public IntellectualMine publishTime(String publishTime) {
    this.publishTime = publishTime;
    return this;
  }

   /**
   * 发布时间
   * @return publishTime
  **/
  @ApiModelProperty(example = "2017/03/03", value = "发布时间")
  public String getPublishTime() {
    return publishTime;
  }

  public void setPublishTime(String publishTime) {
    this.publishTime = publishTime;
  }

  public IntellectualMine approveStatus(Integer approveStatus) {
    this.approveStatus = approveStatus;
    return this;
  }

  /**
   * 联系电话
   * @return tel
  **/
  @ApiModelProperty(value = "联系方式")
   public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public IntellectualMine contact(Contact contact) {
        this.contact = contact;
        return this;
    }
    /**
   * 审核状态：0:未审核，1:通过，2:拒绝
   * minimum: 0
   * maximum: 2
   * @return approveStatus
  **/
  @ApiModelProperty(example = "1", value = "审核状态：0:未审核，1:通过，2:拒绝")
  @Min(0)
  @Max(2)
  public Integer getApproveStatus() {
    return approveStatus;
  }

  public void setApproveStatus(Integer approveStatus) {
    this.approveStatus = approveStatus;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IntellectualMine intellectualMine = (IntellectualMine) o;
    return Objects.equals(this.id, intellectualMine.id) &&
        Objects.equals(this.name, intellectualMine.name) &&
        Objects.equals(this.publishTime, intellectualMine.publishTime) &&
        Objects.equals(this.approveStatus, intellectualMine.approveStatus);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, publishTime, approveStatus);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IntellectualMine {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    publishTime: ").append(toIndentedString(publishTime)).append("\n");
    sb.append("    approveStatus: ").append(toIndentedString(approveStatus)).append("\n");
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

