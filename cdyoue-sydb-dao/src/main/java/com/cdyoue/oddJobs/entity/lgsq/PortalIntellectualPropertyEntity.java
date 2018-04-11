package com.cdyoue.oddJobs.entity.lgsq;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by liaoyoule on 2017/5/2.
 */
@Entity
@Table(name = "lg_portal_intellectual_property", schema = "lgsq", catalog = "")
public class PortalIntellectualPropertyEntity {
    private int id;
    private String name;
    private String contactPerson;
    private String contactTel;
    private String contactEmail;
    private String link;
    private Byte propertyType;
    private Byte businessType;
    private Byte professionalType;
    private String title;
    private String introduction;
    private String usage;
    private Integer price;
    private Integer viewsCount;
    private String coverImg;
    private Byte intellType;
    private Timestamp publishTime;
    private Timestamp createTime;
    private Integer createBy;
    private Timestamp updateTime;
    private Integer updateBy;
    private Byte reviewStatus;
    private Timestamp reviewTime;
    private Integer reviewUserId;

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
    @Column(name = "contactPerson")
    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    @Basic
    @Column(name = "contactTel")
    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    @Basic
    @Column(name = "contactEmail")
    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    @Basic
    @Column(name = "link")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Basic
    @Column(name = "propertyType")
    public Byte getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(Byte propertyType) {
        this.propertyType = propertyType;
    }

    @Basic
    @Column(name = "businessType")
    public Byte getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Byte businessType) {
        this.businessType = businessType;
    }

    @Basic
    @Column(name = "professionalType")
    public Byte getProfessionalType() {
        return professionalType;
    }

    public void setProfessionalType(Byte professionalType) {
        this.professionalType = professionalType;
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
    @Column(name = "introduction")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Basic
    @Column(name = "usage")
    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    @Basic
    @Column(name = "price")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Basic
    @Column(name = "viewsCount")
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
    @Column(name = "intellType")
    public Byte getIntellType() {
        return intellType;
    }

    public void setIntellType(Byte intellType) {
        this.intellType = intellType;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PortalIntellectualPropertyEntity that = (PortalIntellectualPropertyEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (contactPerson != null ? !contactPerson.equals(that.contactPerson) : that.contactPerson != null)
            return false;
        if (contactTel != null ? !contactTel.equals(that.contactTel) : that.contactTel != null) return false;
        if (contactEmail != null ? !contactEmail.equals(that.contactEmail) : that.contactEmail != null) return false;
        if (link != null ? !link.equals(that.link) : that.link != null) return false;
        if (propertyType != null ? !propertyType.equals(that.propertyType) : that.propertyType != null) return false;
        if (businessType != null ? !businessType.equals(that.businessType) : that.businessType != null) return false;
        if (professionalType != null ? !professionalType.equals(that.professionalType) : that.professionalType != null)
            return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (introduction != null ? !introduction.equals(that.introduction) : that.introduction != null) return false;
        if (usage != null ? !usage.equals(that.usage) : that.usage != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (viewsCount != null ? !viewsCount.equals(that.viewsCount) : that.viewsCount != null) return false;
        if (coverImg != null ? !coverImg.equals(that.coverImg) : that.coverImg != null) return false;
        if (intellType != null ? !intellType.equals(that.intellType) : that.intellType != null) return false;
        if (publishTime != null ? !publishTime.equals(that.publishTime) : that.publishTime != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (createBy != null ? !createBy.equals(that.createBy) : that.createBy != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (updateBy != null ? !updateBy.equals(that.updateBy) : that.updateBy != null) return false;
        if (reviewStatus != null ? !reviewStatus.equals(that.reviewStatus) : that.reviewStatus != null) return false;
        if (reviewTime != null ? !reviewTime.equals(that.reviewTime) : that.reviewTime != null) return false;
        if (reviewUserId != null ? !reviewUserId.equals(that.reviewUserId) : that.reviewUserId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (contactPerson != null ? contactPerson.hashCode() : 0);
        result = 31 * result + (contactTel != null ? contactTel.hashCode() : 0);
        result = 31 * result + (contactEmail != null ? contactEmail.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (propertyType != null ? propertyType.hashCode() : 0);
        result = 31 * result + (businessType != null ? businessType.hashCode() : 0);
        result = 31 * result + (professionalType != null ? professionalType.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (introduction != null ? introduction.hashCode() : 0);
        result = 31 * result + (usage != null ? usage.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (viewsCount != null ? viewsCount.hashCode() : 0);
        result = 31 * result + (coverImg != null ? coverImg.hashCode() : 0);
        result = 31 * result + (intellType != null ? intellType.hashCode() : 0);
        result = 31 * result + (publishTime != null ? publishTime.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (updateBy != null ? updateBy.hashCode() : 0);
        result = 31 * result + (reviewStatus != null ? reviewStatus.hashCode() : 0);
        result = 31 * result + (reviewTime != null ? reviewTime.hashCode() : 0);
        result = 31 * result + (reviewUserId != null ? reviewUserId.hashCode() : 0);
        return result;
    }
}
