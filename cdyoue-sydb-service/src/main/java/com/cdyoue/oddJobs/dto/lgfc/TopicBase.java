package com.cdyoue.oddJobs.dto.lgfc;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Topic
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-30T14:30:56.295Z")

public class TopicBase {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("createTime")
  private String createTime = null;

  @JsonProperty("info")
  private String info=null;

  @JsonProperty("coverImg")
  private String coverImg = null;


  public TopicBase id(Integer id) {
    this.id = id;
    return this;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public String getCoverImg() {
    return coverImg;
  }

  public void setCoverImg(String coverImg) {
    this.coverImg = coverImg;
  }
}

