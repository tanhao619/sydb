package com.cdyoue.oddJobs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * LegendData
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-20T01:12:00.448Z")

public class LegendData   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("value")
  private Integer value = null;

  public LegendData name(String name) {
    this.name = name;
    return this;
  }

   /**
   * 图例名称
   * @return name
  **/
  @ApiModelProperty(example = "大数据", value = "图例名称")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LegendData value(Integer value) {
    this.value = value;
    return this;
  }

   /**
   * 图例值
   * @return value
  **/
  @ApiModelProperty(example = "123", value = "图例值")
  public Integer getValue() {
    return value;
  }

  public void setValue(Integer value) {
    this.value = value;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LegendData legendData = (LegendData) o;
    return Objects.equals(this.name, legendData.name) &&
        Objects.equals(this.value, legendData.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LegendData {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
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

