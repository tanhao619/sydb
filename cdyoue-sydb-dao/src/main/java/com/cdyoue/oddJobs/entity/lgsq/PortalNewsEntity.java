package com.cdyoue.oddJobs.entity.lgsq;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by liaoyoule on 2017/5/2.
 */
@Entity
@Table(name = "lg_portal_news", schema = "lgsq", catalog = "")
public class PortalNewsEntity {
    private int id;
    private String title;
    private Integer viewsCount;
    private String coverImg;
    private String summary;
    private String introduction;
    private Timestamp publishTime;
    private Timestamp createTime;
    private Integer createBy;
    private Timestamp updateTime;
    private Integer updateBy;
    private Byte reviewStatus;
    private Timestamp reviewTime;
    private Integer reviewUserId;
    private String reviewReason;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "viewCount")
    public Integer getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(Integer viewsCount) {
        this.viewsCount = viewsCount;
    }

    @Basic
    @Column(name = "coverImg")
    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    @Basic
    @Column(name = "summary")
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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
    @Column(name = "publishTime")
    public Timestamp getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Timestamp publishTime) {
        this.publishTime = publishTime;
    }

    @Basic
    @Column(name = "createTime")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "createBy")
    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "updateTime")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "updateBy")
    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    @Basic
    @Column(name = "reviewStatus")
    public Byte getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(Byte reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    @Basic
    @Column(name = "reviewTime")
    public Timestamp getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Timestamp reviewTime) {
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

    @Basic
    @Column(name = "reviewReason")
    public String getReviewReason() {
        return reviewReason;
    }

    public void setReviewReason(String reviewReason) {
        this.reviewReason = reviewReason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PortalNewsEntity that = (PortalNewsEntity) o;

        if (id != that.id) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (viewsCount != null ? !viewsCount.equals(that.viewsCount) : that.viewsCount != null) return false;
        if (coverImg != null ? !coverImg.equals(that.coverImg) : that.coverImg != null) return false;
        if (summary != null ? !summary.equals(that.summary) : that.summary != null) return false;
        if (introduction != null ? !introduction.equals(that.introduction) : that.introduction != null) return false;
        if (publishTime != null ? !publishTime.equals(that.publishTime) : that.publishTime != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (createBy != null ? !createBy.equals(that.createBy) : that.createBy != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (updateBy != null ? !updateBy.equals(that.updateBy) : that.updateBy != null) return false;
        if (reviewStatus != null ? !reviewStatus.equals(that.reviewStatus) : that.reviewStatus != null) return false;
        if (reviewTime != null ? !reviewTime.equals(that.reviewTime) : that.reviewTime != null) return false;
        if (reviewUserId != null ? !reviewUserId.equals(that.reviewUserId) : that.reviewUserId != null) return false;
        if (reviewReason != null ? !reviewReason.equals(that.reviewReason) : that.reviewReason != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (viewsCount != null ? viewsCount.hashCode() : 0);
        result = 31 * result + (coverImg != null ? coverImg.hashCode() : 0);
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        result = 31 * result + (introduction != null ? introduction.hashCode() : 0);
        result = 31 * result + (publishTime != null ? publishTime.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (updateBy != null ? updateBy.hashCode() : 0);
        result = 31 * result + (reviewStatus != null ? reviewStatus.hashCode() : 0);
        result = 31 * result + (reviewTime != null ? reviewTime.hashCode() : 0);
        result = 31 * result + (reviewUserId != null ? reviewUserId.hashCode() : 0);
        result = 31 * result + (reviewReason != null ? reviewReason.hashCode() : 0);
        return result;
    }
}
