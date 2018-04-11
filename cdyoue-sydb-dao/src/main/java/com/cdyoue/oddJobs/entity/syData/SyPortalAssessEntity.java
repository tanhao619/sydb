package com.cdyoue.oddJobs.entity.syData;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sky on 2017/9/22.
 */
@Entity
@Table(name = "sy_portal_assess", schema = "sydb", catalog = "")
public class SyPortalAssessEntity {
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
    private Date publishTime;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "brandID")
    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    @Basic
    @Column(name = "brandName")
    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Basic
    @Column(name = "brandType")
    public String getBrandType() {
        return brandType;
    }

    public void setBrandType(String brandType) {
        this.brandType = brandType;
    }

    @Basic
    @Column(name = "patentNum")
    public String getPatentNum() {
        return patentNum;
    }

    public void setPatentNum(String patentNum) {
        this.patentNum = patentNum;
    }

    @Basic
    @Column(name = "patentInfo")
    public String getPatentInfo() {
        return patentInfo;
    }

    public void setPatentInfo(String patentInfo) {
        this.patentInfo = patentInfo;
    }

    @Basic
    @Column(name = "assessProject")
    public String getAssessProject() {
        return assessProject;
    }

    public void setAssessProject(String assessProject) {
        this.assessProject = assessProject;
    }

    @Basic
    @Column(name = "assessInfo")
    public String getAssessInfo() {
        return assessInfo;
    }

    public void setAssessInfo(String assessInfo) {
        this.assessInfo = assessInfo;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "applyPerson")
    public String getApplyPerson() {
        return applyPerson;
    }

    public void setApplyPerson(String applyPerson) {
        this.applyPerson = applyPerson;
    }

    @Basic
    @Column(name = "tel")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "updateTime")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "publishTime")
    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SyPortalAssessEntity that = (SyPortalAssessEntity) o;

        if (id != that.id) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (brandId != null ? !brandId.equals(that.brandId) : that.brandId != null) return false;
        if (brandName != null ? !brandName.equals(that.brandName) : that.brandName != null) return false;
        if (brandType != null ? !brandType.equals(that.brandType) : that.brandType != null) return false;
        if (patentNum != null ? !patentNum.equals(that.patentNum) : that.patentNum != null) return false;
        if (patentInfo != null ? !patentInfo.equals(that.patentInfo) : that.patentInfo != null) return false;
        if (assessProject != null ? !assessProject.equals(that.assessProject) : that.assessProject != null)
            return false;
        if (assessInfo != null ? !assessInfo.equals(that.assessInfo) : that.assessInfo != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (applyPerson != null ? !applyPerson.equals(that.applyPerson) : that.applyPerson != null) return false;
        if (tel != null ? !tel.equals(that.tel) : that.tel != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (brandId != null ? brandId.hashCode() : 0);
        result = 31 * result + (brandName != null ? brandName.hashCode() : 0);
        result = 31 * result + (brandType != null ? brandType.hashCode() : 0);
        result = 31 * result + (patentNum != null ? patentNum.hashCode() : 0);
        result = 31 * result + (patentInfo != null ? patentInfo.hashCode() : 0);
        result = 31 * result + (assessProject != null ? assessProject.hashCode() : 0);
        result = 31 * result + (assessInfo != null ? assessInfo.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (applyPerson != null ? applyPerson.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        return result;
    }
}
