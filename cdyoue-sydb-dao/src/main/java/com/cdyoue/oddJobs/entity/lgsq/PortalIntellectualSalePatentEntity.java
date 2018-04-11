package com.cdyoue.oddJobs.entity.lgsq;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by liaoyoule on 2017/5/15.
 */
@Entity
@Table(name = "lg_portal_intellectual_sale_patent", schema = "lgsq", catalog = "")
public class PortalIntellectualSalePatentEntity {
    private int id;
    private String name;
    private String applyCode;
    private Integer businessType;
    private Integer patentType;
    private Double price;
    private String introduction;
    private String contact;
    private String tel;
    private Integer viewCount;
    private Timestamp publishTime;
    private Integer reviewStatus;
    private Integer createBy;
    private Date reviewTime;
    private Integer reviewUserId;
    private String reviewReason;
    private Integer top;
    private String topImg;
    private Date updateTime;
    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "businessType")
    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    @Basic
    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "introduction")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Basic
    @Column(name = "contact")
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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
    @Column(name = "viewCount")
    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    @Basic
    @Column(name = "publishTime")
    public Timestamp getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Timestamp publishTime) {
        this.publishTime = publishTime;
    }

    @Basic
    @Column(name = "applyCode")
    public String getApplyCode() {
        return applyCode;
    }

    public void setApplyCode(String applyCode) {
        this.applyCode = applyCode;
    }

    @Basic
    @Column(name = "patentType")
    public Integer getPatentType() {
        return patentType;
    }

    public void setPatentType(Integer patentType) {
        this.patentType = patentType;
    }

    @Basic
    @Column(name = "top")
    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    @Basic
    @Column(name = "updateTime")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getTopImg() {
        return topImg;
    }

    public void setTopImg(String topImg) {
        this.topImg = topImg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PortalIntellectualSalePatentEntity that = (PortalIntellectualSalePatentEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (businessType != null ? !businessType.equals(that.businessType) : that.businessType != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (introduction != null ? !introduction.equals(that.introduction) : that.introduction != null) return false;
        if (contact != null ? !contact.equals(that.contact) : that.contact != null) return false;
        if (tel != null ? !tel.equals(that.tel) : that.tel != null) return false;
        if (viewCount != null ? !viewCount.equals(that.viewCount) : that.viewCount != null) return false;
        if (publishTime != null ? !publishTime.equals(that.publishTime) : that.publishTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (businessType != null ? businessType.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (introduction != null ? introduction.hashCode() : 0);
        result = 31 * result + (contact != null ? contact.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (viewCount != null ? viewCount.hashCode() : 0);
        result = 31 * result + (publishTime != null ? publishTime.hashCode() : 0);
        return result;
    }

    public Integer getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(Integer reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public Integer getReviewUserId() {
        return reviewUserId;
    }

    public void setReviewUserId(Integer reviewUserId) {
        this.reviewUserId = reviewUserId;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public String getReviewReason() {
        return reviewReason;
    }

    public void setReviewReason(String reviewReason) {
        this.reviewReason = reviewReason;
    }
}
