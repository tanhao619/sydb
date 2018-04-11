package com.cdyoue.oddJobs.entity.lgsq;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by liaoyoule on 2017/5/2.
 */
@Entity
@Table(name = "lg_portal_big_project", schema = "lgsq", catalog = "")
public class PortalBigProjectEntity {
    private int id;
    private String name;
    private String proBudget;
    private Byte proStatus;
    private String coverImg;
    private String info;
    private String introduction;
    private Timestamp createTime;
    private Integer createUserId;
    private Integer viewsCount;
    private Byte reviewStatus;

    private Timestamp reviewTime;
    private Integer reviewUserId;
    private String reviewReason;
    private Integer isTop;
    private String topImg;

    @Basic
    @Column(name = "topImg")
    public String getTopImg() {
        return topImg;
    }

    public void setTopImg(String topImg) {
        this.topImg = topImg;
    }

    @Basic
    @Column(name = "isTop")
    public Integer getIsTop() {
        return isTop;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
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
    @Column(name = "proBudget")
    public String getProBudget() {
        return proBudget;
    }

    public void setProBudget(String proBudget) {
        this.proBudget = proBudget;
    }

    @Basic
    @Column(name = "proStatus")
    public Byte getProStatus() {
        return proStatus;
    }

    public void setProStatus(Byte proStatus) {
        this.proStatus = proStatus;
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
    @Column(name = "info")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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
    @Column(name = "createTime")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "createUserId")
    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
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

        PortalBigProjectEntity that = (PortalBigProjectEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (proBudget != null ? !proBudget.equals(that.proBudget) : that.proBudget != null) return false;
        if (proStatus != null ? !proStatus.equals(that.proStatus) : that.proStatus != null) return false;
        if (coverImg != null ? !coverImg.equals(that.coverImg) : that.coverImg != null) return false;
        if (info != null ? !info.equals(that.info) : that.info != null) return false;
        if (introduction != null ? !introduction.equals(that.introduction) : that.introduction != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (createUserId != null ? !createUserId.equals(that.createUserId) : that.createUserId != null) return false;
        if (viewsCount != null ? !viewsCount.equals(that.viewsCount) : that.viewsCount != null) return false;
        if (reviewStatus != null ? !reviewStatus.equals(that.reviewStatus) : that.reviewStatus != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (proBudget != null ? proBudget.hashCode() : 0);
        result = 31 * result + (proStatus != null ? proStatus.hashCode() : 0);
        result = 31 * result + (coverImg != null ? coverImg.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        result = 31 * result + (introduction != null ? introduction.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (createUserId != null ? createUserId.hashCode() : 0);
        result = 31 * result + (viewsCount != null ? viewsCount.hashCode() : 0);
        result = 31 * result + (reviewStatus != null ? reviewStatus.hashCode() : 0);
        return result;
    }
}
