package com.cdyoue.oddJobs.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Legend
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-20T01:12:00.448Z")

public class Legend   {
  @JsonProperty("title")
  private String title = null;

  @JsonProperty("types")
  private List<String> types = new ArrayList<String>();

  @JsonProperty("datas")
  private List<LegendData> datas = new ArrayList<LegendData>();

  public Legend title(String title) {
    this.title = title;
    return this;
  }

   /**
   * 标题
   * @return title
  **/
  @ApiModelProperty(example = "关注领域", value = "标题")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Legend types(List<String> types) {
    this.types = types;
    return this;
  }

  public Legend addTypesItem(String typesItem) {
    this.types.add(typesItem);
    return this;
  }

   /**
   * 图例分类
   * @return types
  **/
  @ApiModelProperty(example = "[&quot;大数据&quot;,&quot;电商&quot;,&quot;互联网&quot;,&quot;视频广告&quot;,&quot;搜索引擎&quot;]", value = "图例分类")
  public List<String> getTypes() {
    return types;
  }

  public void setTypes(List<String> types) {
    this.types = types;
  }

  public Legend datas(List<LegendData> datas) {
    this.datas = datas;
    return this;
  }

  public Legend addDatasItem(LegendData datasItem) {
    this.datas.add(datasItem);
    return this;
  }

   /**
   * 图例分类及对应数据
   * @return datas
  **/
  @ApiModelProperty(example = "[{&quot;value:335&quot;:null,&quot;name:&#39;大数据&#39;&quot;:null},{&quot;value:310&quot;:null,&quot;name:&#39;电商&#39;&quot;:null},{&quot;value:234&quot;:null,&quot;name:&#39;互联网&#39;&quot;:null},{&quot;value:135&quot;:null,&quot;name:&#39;视频广告&#39;&quot;:null},{&quot;value:1548&quot;:null,&quot;name:&#39;搜索引擎&#39;&quot;:null}]", value = "图例分类及对应数据")
  public List<LegendData> getDatas() {
    return datas;
  }

  public void setDatas(List<LegendData> datas) {
    this.datas = datas;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Legend legend = (Legend) o;
    return Objects.equals(this.title, legend.title) &&
        Objects.equals(this.types, legend.types) &&
        Objects.equals(this.datas, legend.datas);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, types, datas);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Legend {\n");
    
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    types: ").append(toIndentedString(types)).append("\n");
    sb.append("    datas: ").append(toIndentedString(datas)).append("\n");
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

