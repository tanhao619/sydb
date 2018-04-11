package com.cdyoue.oddJobs.dto.other;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;
/**
 * SuggestionRequest
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-31T12:57:35.459Z")

public class SuggestionRequest   {
  @JsonProperty("content")
  private String content = null;

  @JsonProperty("picUrls")
  private List<String> picUrls = new ArrayList<String>();

  @JsonProperty("contact")
  private String contact = null;

  public SuggestionRequest content(String content) {
    this.content = content;
    return this;
  }

   /**
   * 意见描述
   * @return content
  **/
  @ApiModelProperty(example = "请描述你的意见", value = "意见描述")
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public SuggestionRequest picUrls(List<String> picUrls) {
    this.picUrls = picUrls;
    return this;
  }

  public SuggestionRequest addPicUrlsItem(String picUrlsItem) {
    this.picUrls.add(picUrlsItem);
    return this;
  }

   /**
   * Get picUrls
   * @return picUrls
  **/
  @ApiModelProperty(value = "")
  public List<String> getPicUrls() {
    return picUrls;
  }

  public void setPicUrls(List<String> picUrls) {
    this.picUrls = picUrls;
  }

  public SuggestionRequest contact(String contact) {
    this.contact = contact;
    return this;
  }

   /**
   * 联系电话
   * @return contact
  **/
  @ApiModelProperty(example = "1380000000", value = "联系电话")
  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SuggestionRequest suggestionRequest = (SuggestionRequest) o;
    return Objects.equals(this.content, suggestionRequest.content) &&
        Objects.equals(this.picUrls, suggestionRequest.picUrls) &&
        Objects.equals(this.contact, suggestionRequest.contact);
  }

  @Override
  public int hashCode() {
    return Objects.hash(content, picUrls, contact);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SuggestionRequest {\n");
    
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    picUrls: ").append(toIndentedString(picUrls)).append("\n");
    sb.append("    contact: ").append(toIndentedString(contact)).append("\n");
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

