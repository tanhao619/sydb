package com.cdyoue.oddJobs.entity.lgsq;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by liaoyoule on 2017/5/2.
 */
@Entity
@Table(name = "lg_portal_real_name_info", schema = "lgsq", catalog = "")
public class PortalRealNameInfoEntity implements Serializable {
    private Integer id;
    private Integer userId;
    private String reviewReason;
    private String cardNo;
    private String frontImg;
    private String backImg;
    private Byte reviewStatus;
    private Timestamp reviewTime;
    private Integer reviewUserId;
    private Integer applyType;
    private Integer look;

    @Id
    @Column(name = "id")
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "userId")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "reviewReason")
    public String getReviewReason() {
        return reviewReason;
    }

    public void setReviewReason(String reviewReason) {
        this.reviewReason = reviewReason;
    }

    @Basic
    @Column(name = "cardNo")
    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    @Basic
    @Column(name = "frontImg")
    public String getFrontImg() {
        return frontImg;
    }

    public void setFrontImg(String frontImg) {
        this.frontImg = frontImg;
    }

    @Basic
    @Column(name = "backImg")
    public String getBackImg() {
        return backImg;
    }

    public void setBackImg(String backImg) {
        this.backImg = backImg;
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
    @Column(name = "applyType")
    public Integer getApplyType() {
        return applyType;
    }

    public void setApplyType(Integer applyType) {
        this.applyType = applyType;
    }

    @Basic
    @Column(name = "look")
    public Integer getLook() {
        return look;
    }

    public void setLook(Integer look) {
        this.look = look;
    }

    @Override
    public boolean equals( Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final PortalRealNameInfoEntity that = (PortalRealNameInfoEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (reviewReason != null ? !reviewReason.equals(that.reviewReason) : that.reviewReason != null) return false;
        if (cardNo != null ? !cardNo.equals(that.cardNo) : that.cardNo != null) return false;
        if (frontImg != null ? !frontImg.equals(that.frontImg) : that.frontImg != null) return false;
        if (backImg != null ? !backImg.equals(that.backImg) : that.backImg != null) return false;
        if (reviewStatus != null ? !reviewStatus.equals(that.reviewStatus) : that.reviewStatus != null) return false;
        if (reviewTime != null ? !reviewTime.equals(that.reviewTime) : that.reviewTime != null) return false;
        if (reviewUserId != null ? !reviewUserId.equals(that.reviewUserId) : that.reviewUserId != null) return false;
        if (look != null ? !look.equals(that.look) : that.look != null) return false;
        return applyType != null ? applyType.equals(that.applyType) : that.applyType == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (reviewReason != null ? reviewReason.hashCode() : 0);
        result = 31 * result + (cardNo != null ? cardNo.hashCode() : 0);
        result = 31 * result + (frontImg != null ? frontImg.hashCode() : 0);
        result = 31 * result + (backImg != null ? backImg.hashCode() : 0);
        result = 31 * result + (reviewStatus != null ? reviewStatus.hashCode() : 0);
        result = 31 * result + (reviewTime != null ? reviewTime.hashCode() : 0);
        result = 31 * result + (reviewUserId != null ? reviewUserId.hashCode() : 0);
        result = 31 * result + (look != null ? look.hashCode() : 0);
        result = 31 * result + (applyType != null ? applyType.hashCode() : 0);
        return result;
    }
}
