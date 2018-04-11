package com.cdyoue.oddJobs.entity.lgsq;

import javax.persistence.*;

/**
 * Created by dengshaojun on 2017/5/23.
 */
@Entity
@Table(name = "lg_portal_prices_info", schema = "lgsq", catalog = "")
public class PortalPricesInfoEntity {
    private int id;
    private String name;
    private String applyCode;
    private String sourceWebSite;
    private String businessType;
    private String businessPrice;
    private String priceRange;
    private String updateTime;
    private String link;
    private String category;
    private String patentType;

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
    @Column(name = "applyCode")
    public String getApplyCode() {
        return applyCode;
    }

    public void setApplyCode(String applyCode) {
        this.applyCode = applyCode;
    }

    @Basic
    @Column(name = "sourceWebSite")
    public String getSourceWebSite() {
        return sourceWebSite;
    }

    public void setSourceWebSite(String sourceWebSite) {
        this.sourceWebSite = sourceWebSite;
    }

    @Basic
    @Column(name = "businessType")
    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    @Basic
    @Column(name = "businessPrice")
    public String getBusinessPrice() {
        return businessPrice;
    }

    public void setBusinessPrice(String businessPrice) {
        this.businessPrice = businessPrice;
    }

    @Basic
    @Column(name = "priceRange")
    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    @Basic
    @Column(name = "updateTime")
    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
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
    @Column(name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Basic
    @Column(name = "patentType")
    public String getPatentType() {
        return patentType;
    }

    public void setPatentType(String patentType) {
        this.patentType = patentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PortalPricesInfoEntity that = (PortalPricesInfoEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (applyCode != null ? !applyCode.equals(that.applyCode) : that.applyCode != null) return false;
        if (sourceWebSite != null ? !sourceWebSite.equals(that.sourceWebSite) : that.sourceWebSite != null)
            return false;
        if (businessType != null ? !businessType.equals(that.businessType) : that.businessType != null) return false;
        if (businessPrice != null ? !businessPrice.equals(that.businessPrice) : that.businessPrice != null)
            return false;
        if (priceRange != null ? !priceRange.equals(that.priceRange) : that.priceRange != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (link != null ? !link.equals(that.link) : that.link != null) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        if (patentType != null ? !patentType.equals(that.patentType) : that.patentType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (applyCode != null ? applyCode.hashCode() : 0);
        result = 31 * result + (sourceWebSite != null ? sourceWebSite.hashCode() : 0);
        result = 31 * result + (businessType != null ? businessType.hashCode() : 0);
        result = 31 * result + (businessPrice != null ? businessPrice.hashCode() : 0);
        result = 31 * result + (priceRange != null ? priceRange.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (patentType != null ? patentType.hashCode() : 0);
        return result;
    }
}
