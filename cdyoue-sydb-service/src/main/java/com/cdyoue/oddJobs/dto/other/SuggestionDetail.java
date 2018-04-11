package com.cdyoue.oddJobs.dto.other;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * SuggestionDetail
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-31T12:57:35.459Z")

public class SuggestionDetail   {
  @JsonProperty("content")
  private String content = null;

  @JsonProperty("picUrls")
  private List<String> picUrls = new ArrayList<String>();

  @JsonProperty("contact")
  private String contact = null;

  @JsonProperty("createBy")
  private String createBy;
    public SuggestionDetail picUrls(List<String> picUrls) {
        this.picUrls = picUrls;
        return this;
    }

    public SuggestionDetail addPicUrlsItem(String picUrlsItem) {
        this.picUrls.add(picUrlsItem);
        return this;
    }

    /**
     * Get picUrls
     * @return picUrls
     **/
    @ApiModelProperty(value = "图片")
    public List<String> getPicUrls() {
        return picUrls;
    }

    public void setPicUrls(List<String> picUrls) {
        this.picUrls = picUrls;
    }

    @ApiModelProperty(example = "浏览速度慢", value = "反馈内容")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @ApiModelProperty(example = "13981194466", value = "反馈联系电话")
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @ApiModelProperty(example = "唐瑞", value = "反馈人")
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SuggestionDetail that = (SuggestionDetail) o;

        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (picUrls != null ? !picUrls.equals(that.picUrls) : that.picUrls != null) return false;
        if (contact != null ? !contact.equals(that.contact) : that.contact != null) return false;
        if (createBy != null ? !createBy.equals(that.createBy) : that.createBy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = content != null ? content.hashCode() : 0;
        result = 31 * result + (picUrls != null ? picUrls.hashCode() : 0);
        result = 31 * result + (contact != null ? contact.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        return result;
    }
}

