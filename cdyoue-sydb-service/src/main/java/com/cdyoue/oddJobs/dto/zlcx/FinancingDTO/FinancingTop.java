package com.cdyoue.oddJobs.dto.zlcx.FinancingDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by Administrator on 2017/9/20.
 */
public class FinancingTop {
    @JsonProperty("id")//项目id
    private Integer id;

    @JsonProperty("name")//项目名称
    private String name;

    @JsonProperty("fund")//融资需求资金
    private String fund;

    @JsonProperty("imgUrl")//图片路径
    private String imgUrl;

    @JsonProperty("summary")//项目简介
    private String summary;

    @JsonProperty("status")//项目状态
    private Integer status;

    @JsonProperty("createBy")//创建人
    private String createBy;

    @JsonProperty("createTime")//创建时间
    private Date createTime;

    @JsonProperty("viewCount")//浏览次数
    private Integer viewCount;

    @JsonProperty("top")//是否置顶
    private Integer top;

    @JsonProperty("remarks")//审核备注
    private String remarks;

    @JsonProperty("topImg")//审核备注
    private String topImg;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTopImg() {
        return topImg;
    }

    public void setTopImg(String topImg) {
        this.topImg = topImg;
    }
}
