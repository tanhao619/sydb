package com.cdyoue.oddJobs.entity.syData;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by th on 2017/09/26.
 */
@Entity
@Table(name = "sy_cooperative_partner", schema = "sydb", catalog = "")
public class SyCooperativePartnerEntity {
    private int id;
    private String name;
    private String url;
    private String logoImg;
    private Integer viewCount;
    private Integer creatBy;
    private Date publishTime;
    private Date updateTime;

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
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "logoImg")
    public String getLogoImg() {
        return logoImg;
    }

    public void setLogoImg(String logoImg) {
        this.logoImg = logoImg;
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
    @Column(name = "creatBy")
    public Integer getCreatBy() {
        return creatBy;
    }

    public void setCreatBy(Integer creatBy) {
        this.creatBy = creatBy;
    }

    @Basic
    @Column(name = "publishTime")
    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    @Basic
    @Column(name = "updateTime")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SyCooperativePartnerEntity that = (SyCooperativePartnerEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (logoImg != null ? !logoImg.equals(that.logoImg) : that.logoImg != null) return false;
        if (viewCount != null ? !viewCount.equals(that.viewCount) : that.viewCount != null) return false;
        if (creatBy != null ? !creatBy.equals(that.creatBy) : that.creatBy != null) return false;
        if (publishTime != null ? !publishTime.equals(that.publishTime) : that.publishTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (logoImg != null ? logoImg.hashCode() : 0);
        result = 31 * result + (viewCount != null ? viewCount.hashCode() : 0);
        result = 31 * result + (creatBy != null ? creatBy.hashCode() : 0);
        result = 31 * result + (publishTime != null ? publishTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }
}
