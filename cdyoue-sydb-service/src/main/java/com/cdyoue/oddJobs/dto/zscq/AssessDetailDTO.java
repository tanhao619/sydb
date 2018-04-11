package com.cdyoue.oddJobs.dto.zscq;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sky on 2017/9/22.
 */
public class AssessDetailDTO {
    private int id;
    private Integer type;
    private String brandId;
    private String brandName;
    private String brandType;
    private String patentNum;
    private String patentInfo;
    private String assessProject;
    private String assessInfo;
    private String remark;
    private String applyPerson;
    private String tel;
    private Date updateTime;
    private String publishTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
    public String getBrandType() {
        return brandType;
    }

    public void setBrandType(String brandType) {
        this.brandType = brandType;
    }

    public String getPatentNum() {
        return patentNum;
    }

    public void setPatentNum(String patentNum) {
        this.patentNum = patentNum;
    }

    public String getPatentInfo() {
        return patentInfo;
    }

    public void setPatentInfo(String patentInfo) {
        this.patentInfo = patentInfo;
    }

    public String getAssessProject() {
        return assessProject;
    }

    public void setAssessProject(String assessProject) {
        this.assessProject = assessProject;
    }

    public String getAssessInfo() {
        return assessInfo;
    }

    public void setAssessInfo(String assessInfo) {
        this.assessInfo = assessInfo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getApplyPerson() {
        return applyPerson;
    }

    public void setApplyPerson(String applyPerson) {
        this.applyPerson = applyPerson;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }
}
