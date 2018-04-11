package com.cdyoue.oddJobs.entity.lgsq.user;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by liaoyoule on 2017/4/24.
 */
@Entity
@Table(name = "userenterprise", schema = "lgsq", catalog = "")
public class UserenterpriseEntity implements Serializable {
    private Integer id;
    private String name;
    private String headImg;
    private String title;
    private Timestamp establishmentTime;
    private String location;
    private Integer registeredCapital;
    private String legalPerson;
    private String organizationCode;
    private String info;
    private String business;
    private String enterpriseDetail;
    private String enterType;
    private Integer userId;

    private String businessLicenseUrl;

    public String getBusinessLicenseUrl() {
        return businessLicenseUrl;
    }

    public void setBusinessLicenseUrl(final String businessLicenseUrl) {
        this.businessLicenseUrl = businessLicenseUrl;
    }

    @Id
    @Column(name = "Id")
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
    @Column(name = "headImg")
    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
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
    @Column(name = "establishmentTime")
    public Timestamp getEstablishmentTime() {
        return establishmentTime;
    }

    public void setEstablishmentTime(Timestamp establishmentTime) {
        this.establishmentTime = establishmentTime;
    }

    @Basic
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "registeredCapital")
    public Integer getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(Integer registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    @Basic
    @Column(name = "legalPerson")
    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    @Basic
    @Column(name = "organizationCode")
    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
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
    @Column(name = "business")
    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    @Basic
    @Column(name = "enterpriseDetail")
    public String getEnterpriseDetail() {
        return enterpriseDetail;
    }

    public void setEnterpriseDetail(String enterpriseDetail) {
        this.enterpriseDetail = enterpriseDetail;
    }

    @Basic
    @Column(name = "UserId")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "enterType")
    public String getEnterType() {
        return enterType;
    }

    public void setEnterType(String enterType) {
        this.enterType = enterType;
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final UserenterpriseEntity that = (UserenterpriseEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (headImg != null ? !headImg.equals(that.headImg) : that.headImg != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (establishmentTime != null ? !establishmentTime.equals(that.establishmentTime) : that.establishmentTime != null)
            return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        if (registeredCapital != null ? !registeredCapital.equals(that.registeredCapital) : that.registeredCapital != null)
            return false;
        if (legalPerson != null ? !legalPerson.equals(that.legalPerson) : that.legalPerson != null) return false;
        if (organizationCode != null ? !organizationCode.equals(that.organizationCode) : that.organizationCode != null)
            return false;
        if (info != null ? !info.equals(that.info) : that.info != null) return false;
        if (business != null ? !business.equals(that.business) : that.business != null) return false;
        if (enterpriseDetail != null ? !enterpriseDetail.equals(that.enterpriseDetail) : that.enterpriseDetail != null)
            return false;
        if (enterType != null ? !enterType.equals(that.enterType) : that.enterType != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        return businessLicenseUrl != null ? businessLicenseUrl.equals(that.businessLicenseUrl) : that.businessLicenseUrl == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (headImg != null ? headImg.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (establishmentTime != null ? establishmentTime.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (registeredCapital != null ? registeredCapital.hashCode() : 0);
        result = 31 * result + (legalPerson != null ? legalPerson.hashCode() : 0);
        result = 31 * result + (organizationCode != null ? organizationCode.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        result = 31 * result + (business != null ? business.hashCode() : 0);
        result = 31 * result + (enterpriseDetail != null ? enterpriseDetail.hashCode() : 0);
        result = 31 * result + (enterType != null ? enterType.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (businessLicenseUrl != null ? businessLicenseUrl.hashCode() : 0);
        return result;
    }
}
