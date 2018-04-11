package com.cdyoue.oddJobs.dto.requirement;

import com.cdyoue.oddJobs.dto.common.KeyValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * PageHomeInfo
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-20T01:12:00.448Z")

public class PageHomeInfo   {
  @JsonProperty("name")
  private String name = null;

  /**
   * 获取各个页面列表信息，页面包括：case:成功案例，bpro:大项目，zscqfw:知识产权服务，scfwjg:双创服务机构，govServ：政府服务
   */
  public enum PositionEnum {
    HOME_1("home_1"),
    
    HOME_2("home_2"),
    
    ZSCQFW("zscqfw"),
    
    SCFWJG("scfwjg"),
    
    GOVSERV("govServ");

    private String value;

    PositionEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static PositionEnum fromValue(String text) {
      for (PositionEnum b : PositionEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("position")
  private PositionEnum position = null;

  @JsonProperty("Objects")
  private List<PageBase> objects = new ArrayList<PageBase>();

  @JsonProperty("addProperties")
  private List<KeyValue> addProperties = new ArrayList<KeyValue>();

  public PageHomeInfo name(String name) {
    this.name = name;
    return this;
  }

   /**
   * 页面区域名称
   * @return name
  **/
  @ApiModelProperty(example = "创新成果", value = "页面区域名称")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public PageHomeInfo position(PositionEnum position) {
    this.position = position;
    return this;
  }

   /**
   * 获取各个页面列表信息，页面包括：case:成功案例，bpro:大项目，zscqfw:知识产权服务，scfwjg:双创服务机构，govServ：政府服务
   * @return position
  **/
  @ApiModelProperty(example = "home_1", value = "获取各个页面列表信息，页面包括：case:成功案例，bpro:大项目，zscqfw:知识产权服务，scfwjg:双创服务机构，govServ：政府服务")
  public PositionEnum getPosition() {
    return position;
  }

  public void setPosition(PositionEnum position) {
    this.position = position;
  }

  public PageHomeInfo objects(List<PageBase> objects) {
    this.objects = objects;
    return this;
  }

  public PageHomeInfo addObjectsItem(PageBase objectsItem) {
    this.objects.add(objectsItem);
    return this;
  }

   /**
   * 页面基本信息
   * @return objects
  **/
  @ApiModelProperty(value = "页面基本信息")
  public List<PageBase> getObjects() {
    return objects;
  }

  public void setObjects(List<PageBase> objects) {
    this.objects = objects;
  }

  public PageHomeInfo addProperties(List<KeyValue> addProperties) {
    this.addProperties = addProperties;
    return this;
  }

  public PageHomeInfo addAddPropertiesItem(KeyValue addPropertiesItem) {
    this.addProperties.add(addPropertiesItem);
    return this;
  }

   /**
   * 页面其他属性信息
   * @return addProperties
  **/
  @ApiModelProperty(value = "页面其他属性信息")
  public List<KeyValue> getAddProperties() {
    return addProperties;
  }

  public void setAddProperties(List<KeyValue> addProperties) {
    this.addProperties = addProperties;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PageHomeInfo pageHomeInfo = (PageHomeInfo) o;
    return Objects.equals(this.name, pageHomeInfo.name) &&
        Objects.equals(this.position, pageHomeInfo.position) &&
        Objects.equals(this.objects, pageHomeInfo.objects) &&
        Objects.equals(this.addProperties, pageHomeInfo.addProperties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, position, objects, addProperties);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PageHomeInfo {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    position: ").append(toIndentedString(position)).append("\n");
    sb.append("    objects: ").append(toIndentedString(objects)).append("\n");
    sb.append("    addProperties: ").append(toIndentedString(addProperties)).append("\n");
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

