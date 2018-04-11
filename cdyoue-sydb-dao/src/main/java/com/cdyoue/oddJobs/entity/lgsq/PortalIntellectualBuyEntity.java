package com.cdyoue.oddJobs.entity.lgsq;

import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by liaoyoule on 2017/5/11.
 */
@Entity
@Table(name = "lg_portal_intellectual_buy", schema = "lgsq", catalog = "")
public class PortalIntellectualBuyEntity {
    private int id;
    private String name;
    private Integer businessType;
    private Integer patentType;
    private String introduction;
    private String plan;
    private String contact;
    private String tel;
    private Integer intellType;
    private Integer viewCount;
    private Timestamp publishTime;
    private Integer reviewStatus;
    private Date reviewTime;
    private Date updateTime;
    private Integer reviewUserId;
    private UserEntity createBy;
    private String reviewReason;
    private String topImg;
    private Integer top;

    @Basic
    @Column(name = "reviewReason")
    public String getReviewReason() {
        return reviewReason;
    }

    public void setReviewReason(String reviewReason) {
        this.reviewReason = reviewReason;
    }

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
    @Column(name = "patentType")
    public Integer getPatentType() {
        return patentType;
    }

    public void setPatentType(Integer patentType) {
        this.patentType = patentType;
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
    @Column(name = "plan")
    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
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
    @Column(name = "intellType")
    public Integer getIntellType() {
        return intellType;
    }

    public void setIntellType(Integer intellType) {
        this.intellType = intellType;
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
    @Column(name = "reviewStatus")
    public Integer getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(Integer reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    @Basic
    @Column(name = "reviewTime")
    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    @Basic
    @Column(name = "reviewUserId")
    public Integer getReviewUserId() {
        return reviewUserId;
    }

    public void setReviewUserId(Integer reviewUserId) {
        this.reviewUserId = reviewUserId;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "createBy")
    public UserEntity getCreateBy() {
        return createBy;
    }

    public void setCreateBy(UserEntity createBy) {
        this.createBy = createBy;
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

    @Basic
    @Column(name = "topImg")
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

        PortalIntellectualBuyEntity that = (PortalIntellectualBuyEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (businessType != null ? !businessType.equals(that.businessType) : that.businessType != null) return false;
        if (patentType != null ? !patentType.equals(that.patentType) : that.patentType != null) return false;
        if (introduction != null ? !introduction.equals(that.introduction) : that.introduction != null) return false;
        if (plan != null ? !plan.equals(that.plan) : that.plan != null) return false;
        if (contact != null ? !contact.equals(that.contact) : that.contact != null) return false;
        if (tel != null ? !tel.equals(that.tel) : that.tel != null) return false;
        if (intellType != null ? !intellType.equals(that.intellType) : that.intellType != null) return false;
        if (viewCount != null ? !viewCount.equals(that.viewCount) : that.viewCount != null) return false;
        if (publishTime != null ? !publishTime.equals(that.publishTime) : that.publishTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (businessType != null ? businessType.hashCode() : 0);
        result = 31 * result + (patentType != null ? patentType.hashCode() : 0);
        result = 31 * result + (introduction != null ? introduction.hashCode() : 0);
        result = 31 * result + (plan != null ? plan.hashCode() : 0);
        result = 31 * result + (contact != null ? contact.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (intellType != null ? intellType.hashCode() : 0);
        result = 31 * result + (viewCount != null ? viewCount.hashCode() : 0);
        result = 31 * result + (publishTime != null ? publishTime.hashCode() : 0);
        return result;
    }
}
