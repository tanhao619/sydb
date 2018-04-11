package com.cdyoue.oddJobs.entity.lgsq;

import javax.persistence.*;

/**
 * Created by dengshaojun on 2017/5/23.
 */
@Entity
@Table(name = "lg_portal_patent_data", schema = "lgsq", catalog = "")
public class PortalPatentDataEntity {
    private int id;
    private String name;
    private String applyCode;
    private String industryCode;
    private Integer priorityCount;
    private Integer refTimes;
    private Integer typeCodeCount;
    private String patentAge;
    private String priceApply;
    private String priceRange;
//    private Timestamp publishTime;

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
    @Column(name = "industryCode")
    public String getIndustryCode() {
        return industryCode;
    }

    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode;
    }

    @Basic
    @Column(name = "priorityCount")
    public Integer getPriorityCount() {
        return priorityCount;
    }

    public void setPriorityCount(Integer priorityCount) {
        this.priorityCount = priorityCount;
    }

    @Basic
    @Column(name = "refTimes")
    public Integer getRefTimes() {
        return refTimes;
    }

    public void setRefTimes(Integer refTimes) {
        this.refTimes = refTimes;
    }

    @Basic
    @Column(name = "typeCodeCount")
    public Integer getTypeCodeCount() {
        return typeCodeCount;
    }

    public void setTypeCodeCount(Integer typeCodeCount) {
        this.typeCodeCount = typeCodeCount;
    }

    @Basic
    @Column(name = "patentAge")
    public String getPatentAge() {
        return patentAge;
    }

    public void setPatentAge(String patentAge) {
        this.patentAge = patentAge;
    }

    @Basic
    @Column(name = "priceApply")
    public String getPriceApply() {
        return priceApply;
    }

    public void setPriceApply(String priceApply) {
        this.priceApply = priceApply;
    }

    @Basic
    @Column(name = "priceRange")
    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    /*@Basic
    @Column(name = "publishTime")
    public Timestamp getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Timestamp publishTime) {
        this.publishTime = publishTime;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PortalPatentDataEntity that = (PortalPatentDataEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (applyCode != null ? !applyCode.equals(that.applyCode) : that.applyCode != null) return false;
        if (industryCode != null ? !industryCode.equals(that.industryCode) : that.industryCode != null) return false;
        if (priorityCount != null ? !priorityCount.equals(that.priorityCount) : that.priorityCount != null)
            return false;
        if (refTimes != null ? !refTimes.equals(that.refTimes) : that.refTimes != null) return false;
        if (typeCodeCount != null ? !typeCodeCount.equals(that.typeCodeCount) : that.typeCodeCount != null)
            return false;
        if (patentAge != null ? !patentAge.equals(that.patentAge) : that.patentAge != null) return false;
        if (priceApply != null ? !priceApply.equals(that.priceApply) : that.priceApply != null) return false;
        if (priceRange != null ? !priceRange.equals(that.priceRange) : that.priceRange != null) return false;
//        if (publishTime != null ? !publishTime.equals(that.publishTime) : that.publishTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (applyCode != null ? applyCode.hashCode() : 0);
        result = 31 * result + (industryCode != null ? industryCode.hashCode() : 0);
        result = 31 * result + (priorityCount != null ? priorityCount.hashCode() : 0);
        result = 31 * result + (refTimes != null ? refTimes.hashCode() : 0);
        result = 31 * result + (typeCodeCount != null ? typeCodeCount.hashCode() : 0);
        result = 31 * result + (patentAge != null ? patentAge.hashCode() : 0);
        result = 31 * result + (priceApply != null ? priceApply.hashCode() : 0);
        result = 31 * result + (priceRange != null ? priceRange.hashCode() : 0);
//        result = 31 * result + (publishTime != null ? publishTime.hashCode() : 0);
        return result;
    }
}
