package com.cdyoue.oddJobs.entity.syData;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by sky on 2017/9/23.
 */
@Entity
@Table(name = "sy_enterprise", schema = "sydb", catalog = "")
public class SyEnterpriseEntity {
    private int id;
    private String name;
    private String logo;
    private String address;
    private String info;
    private String topImage;
    private Integer top;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String contactPeople;
    private String contactNumber;
    private String enterpriseUrl;
    private Integer publishPeople;
    private Integer viewCount;
    private String industry;
    private String content;

    @Basic
    @Column(name = "industry")
    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }
    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
    @Column(name = "publishPeople")
    public Integer getPublishPeople() {
        return publishPeople;
    }

    public void setPublishPeople(Integer publishPeople) {
        this.publishPeople = publishPeople;
    }

    @Basic
    @Column(name = "contactPeople")
    public String getContactPeople() {
        return contactPeople;
    }

    public void setContactPeople(String contactPeople) {
        this.contactPeople = contactPeople;
    }
    @Basic
    @Column(name = "contactNumber")
    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    @Basic
    @Column(name = "enterpriseUrl")
    public String getEnterpriseUrl() {
        return enterpriseUrl;
    }

    public void setEnterpriseUrl(String enterpriseUrl) {
        this.enterpriseUrl = enterpriseUrl;
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
    @Column(name = "logo")
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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
    @Column(name = "info")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }



    @Basic
    @Column(name = "topImage")
    public String getTopImage() {
        return topImage;
    }

    public void setTopImage(String topImage) {
        this.topImage = topImage;
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
    @Column(name = "createTime")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "updateTime")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SyEnterpriseEntity that = (SyEnterpriseEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (logo != null ? !logo.equals(that.logo) : that.logo != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (info != null ? !info.equals(that.info) : that.info != null) return false;
        if (topImage != null ? !topImage.equals(that.topImage) : that.topImage != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (logo != null ? logo.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        result = 31 * result + (topImage != null ? topImage.hashCode() : 0);
        return result;
    }
}
