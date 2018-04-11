package com.cdyoue.oddJobs.dto.zlcx.FinancingDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Administrator on 2017/9/20.
 */
public class FinancingDetail {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private Integer type;

    @JsonProperty("patentNum")
    private String patentNum;

    @JsonProperty("fund")
    private String fund;

    @JsonProperty("imgUrl")
    private String imgUrl;

    @JsonProperty("summary")
    private String summary;

    @JsonProperty("detail")
    private String detail;

    @JsonProperty("file")
    private String file;

    @JsonProperty("contacts")
    private String contacts;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("email")
    private String email;

    @JsonProperty("viewCount")
    private Integer viewCount;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPatentNum() {
        return patentNum;
    }

    public void setPatentNum(String patentNum) {
        this.patentNum = patentNum;
    }

    public String getFund() {
        return fund;
    }

    public void setFund(String fund) {
        this.fund = fund;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }
}
