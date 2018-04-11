package com.cdyoue.oddJobs.dto.home;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * PageHomeSummary
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-07T01:21:59.849Z")

public class PageHomeSummary   {



  @JsonProperty("categoryId")
  private Integer categoryId;

  @JsonProperty("name")
  private String name = null;

  /**
   * 获取各个首页面列表信息
   */


  @JsonProperty("code")
  private String code = null;

  @JsonProperty("elements")
  private List<PageHome> elements = new ArrayList<PageHome>();

  public PageHomeSummary name(String name) {
    this.name = name;
    return this;
  }

   /**
   * 页面区域名称，可以为空
   * @return name
  **/
  @ApiModelProperty(example = "创新成果", value = "页面区域名称，可以为空")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public PageHomeSummary code(String code) {
    this.code = code;
    return this;
  }

   /**
   * 获取各个首页面列表信息
   * @return code
  **/
  @ApiModelProperty(example = "home_1", value = "获取各个首页面列表信息")
  public String getCode() {
    return code;
  }



  public void setCode(String code) {
    this.code = code;
  }

  @ApiModelProperty(value = "当为 xqdt 时  显示 name分类id")
  public Integer getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Integer categoryId) {
    this.categoryId = categoryId;
  }







  public PageHomeSummary elements(List<PageHome> elements) {
    this.elements = elements;
    return this;
  }

  public PageHomeSummary addElementsItem(PageHome elementsItem) {
    this.elements.add(elementsItem);
    return this;
  }

   /**
   * 页面具体信息列表
   * @return elements
  **/
  @ApiModelProperty(value = "页面具体信息列表")
  public List<PageHome> getElements() {
    return elements;
  }

  public void setElements(List<PageHome> elements) {
    this.elements = elements;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PageHomeSummary pageHomeSummary = (PageHomeSummary) o;
    return Objects.equals(this.name, pageHomeSummary.name) &&
        Objects.equals(this.code, pageHomeSummary.code) &&
        Objects.equals(this.elements, pageHomeSummary.elements);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, code, elements);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PageHomeSummary {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    elements: ").append(toIndentedString(elements)).append("\n");
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

