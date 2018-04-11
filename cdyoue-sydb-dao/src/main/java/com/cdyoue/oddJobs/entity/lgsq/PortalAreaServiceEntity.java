package com.cdyoue.oddJobs.entity.lgsq;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by liaoyoule on 2017/5/2.
 */
@Entity
@Table(name = "lg_portal_area_service", schema = "lgsq", catalog = "")
public class PortalAreaServiceEntity {
    private int id;
    private String name;
    private String tel;
    private String email;
    private String coverImg;
    private Integer areaIdLvPre;
    private Integer areaIdLvNext;
    private Integer areaType;
    private Double price;
    private Integer size;
    private String address;
    private String introduction;
    private Timestamp publishTime;
    private Timestamp createTime;
    private Integer createBy;
    private Timestamp updateTime;
    private Integer updateBy;
    private Integer reviewStatus;
    private Timestamp reviewTime;
    private Integer reviewUserId;
    private Integer viewCount;
    private String contactor;
    private String reviewReason;
    private Integer top;
    private String topImg;

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
    @Column(name = "tel")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    @Column(name = "areaIdLvPre")
    public Integer getAreaIdLvPre() {
        return areaIdLvPre;
    }

    public void setAreaIdLvPre(final Integer areaIdLvPre) {
        this.areaIdLvPre = areaIdLvPre;
    }
    @Basic
    @Column(name = "areaIdLvNext")
    public Integer getAreaIdLvNext() {
        return areaIdLvNext;
    }

    public void setAreaIdLvNext(final Integer areaIdLvNext) {
        this.areaIdLvNext = areaIdLvNext;
    }



    @Basic
    @Column(name = "areaType")
    public Integer getAreaType() {
        return areaType;
    }

    public void setAreaType(Integer areaType) {
        this.areaType = areaType;
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
    @Column(name = "size")
    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
    public Integer getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(Integer reviewStatus) {
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
    @Column(name="viewCount")
    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    @Basic
    @Column(name="contactor")
    public String getContactor() {
        return contactor;
    }

    public void setContactor(String contactor) {
        this.contactor = contactor;
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
    @Column(name = "top")
    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
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
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final PortalAreaServiceEntity that = (PortalAreaServiceEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (tel != null ? !tel.equals(that.tel) : that.tel != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (coverImg != null ? !coverImg.equals(that.coverImg) : that.coverImg != null) return false;
        if (areaIdLvPre != null ? !areaIdLvPre.equals(that.areaIdLvPre) : that.areaIdLvPre != null) return false;
        if (areaIdLvNext != null ? !areaIdLvNext.equals(that.areaIdLvNext) : that.areaIdLvNext != null) return false;
        if (areaType != null ? !areaType.equals(that.areaType) : that.areaType != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (size != null ? !size.equals(that.size) : that.size != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (introduction != null ? !introduction.equals(that.introduction) : that.introduction != null) return false;
        if (publishTime != null ? !publishTime.equals(that.publishTime) : that.publishTime != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (createBy != null ? !createBy.equals(that.createBy) : that.createBy != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (updateBy != null ? !updateBy.equals(that.updateBy) : that.updateBy != null) return false;
        if (reviewStatus != null ? !reviewStatus.equals(that.reviewStatus) : that.reviewStatus != null) return false;
        if (reviewTime != null ? !reviewTime.equals(that.reviewTime) : that.reviewTime != null) return false;
        if (reviewUserId != null ? !reviewUserId.equals(that.reviewUserId) : that.reviewUserId != null) return false;
        if (viewCount != null ? !viewCount.equals(that.viewCount) : that.viewCount != null) return false;
        if (reviewReason != null ? !reviewReason.equals(that.reviewReason) : that.reviewReason != null) return false;
        return contactor != null ? contactor.equals(that.contactor) : that.contactor == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (coverImg != null ? coverImg.hashCode() : 0);
        result = 31 * result + (areaIdLvPre != null ? areaIdLvPre.hashCode() : 0);
        result = 31 * result + (areaIdLvNext != null ? areaIdLvNext.hashCode() : 0);
        result = 31 * result + (areaType != null ? areaType.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (introduction != null ? introduction.hashCode() : 0);
        result = 31 * result + (publishTime != null ? publishTime.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (updateBy != null ? updateBy.hashCode() : 0);
        result = 31 * result + (reviewStatus != null ? reviewStatus.hashCode() : 0);
        result = 31 * result + (reviewTime != null ? reviewTime.hashCode() : 0);
        result = 31 * result + (reviewUserId != null ? reviewUserId.hashCode() : 0);
        result = 31 * result + (viewCount != null ? viewCount.hashCode() : 0);
        result = 31 * result + (contactor != null ? contactor.hashCode() : 0);
        result = 31 * result + (reviewReason != null ? reviewReason.hashCode() : 0);
        return result;
    }
}
