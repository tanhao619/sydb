package com.cdyoue.oddJobs.dto.common;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
/**
 * Location
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-10T12:31:39.195Z")

public class Location   {
  @JsonProperty("pid")
  private Integer pid = null;

  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("pName")
  private String pName = null;

  @JsonProperty("name")
  private String name = null;

  public Location pid(Integer pid) {
    this.pid = pid;
    return this;
  }

   /**
   * 第一级地点id
   * @return pid
  **/
  @ApiModelProperty(example = "11", value = "第一级地点id")
  public Integer getPid() {
    return pid;
  }

  public void setPid(Integer pid) {
    this.pid = pid;
  }

  public Location id(Integer id) {
    this.id = id;
    return this;
  }

   /**
   * 第二级地点id
   * @return id
  **/
  @ApiModelProperty(example = "21", value = "第二级地点id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Location pName(String pName) {
    this.pName = pName;
    return this;
  }

   /**
   * 第一级地点名称
   * @return pName
  **/
  @ApiModelProperty(example = "四川", value = "第一级地点名称")
  public String getPName() {
    return pName;
  }

  public void setPName(String pName) {
    this.pName = pName;
  }

  public Location name(String name) {
    this.name = name;
    return this;
  }

   /**
   * 第二级地点名称
   * @return name
  **/
  @ApiModelProperty(example = "成都", value = "第二级地点名称")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Location location = (Location) o;
    return Objects.equals(this.pid, location.pid) &&
        Objects.equals(this.id, location.id) &&
        Objects.equals(this.pName, location.pName) &&
        Objects.equals(this.name, location.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pid, id, pName, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Location {\n");
    
    sb.append("    pid: ").append(toIndentedString(pid)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    pName: ").append(toIndentedString(pName)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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

