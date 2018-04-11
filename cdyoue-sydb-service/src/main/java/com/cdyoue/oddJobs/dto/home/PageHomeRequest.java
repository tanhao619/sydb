package com.cdyoue.oddJobs.dto.home;

import com.cdyoue.oddJobs.dto.common.KeyValue;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * PageHome
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-07T01:21:59.849Z")

public class PageHomeRequest {



  @JsonProperty("title")
  private String title = null;

  @JsonProperty("intro")
  private String intro = null;

  @JsonProperty("cover_img")
  private String coverImg = null;

  @JsonProperty("link")
  private String link = null;


  @JsonProperty("addProperties")
  private List<KeyValue> addProperties = new ArrayList<KeyValue>();

  @ApiModelProperty(value = "标题")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
  @ApiModelProperty(value = "简介")
  public String getIntro() {
    return intro;
  }

  public void setIntro(String intro) {
    this.intro = intro;
  }
  @ApiModelProperty(value = "图示")
  public String getCoverImg() {
    return coverImg;
  }

  public void setCoverImg(String coverImg) {
    this.coverImg = coverImg;
  }
  @ApiModelProperty(value = "连接")
  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public List<KeyValue> getAddProperties() {
    return addProperties;
  }

  public void setAddProperties(List<KeyValue> addProperties) {
    this.addProperties = addProperties;
  }
}

