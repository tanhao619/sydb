package com.cdyoue.oddJobs.entity.lgsq.homePage;

import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by liaoyoule on 2017/5/6.
 */
@Entity
@Table(name = "lg_portal_home_page_info", schema = "lgsq", catalog = "")
public class PortalHomePageInfoEntity {
    private int id;
    private String name;
    private String title;
    private Timestamp publishTime;
    private Timestamp createTime;
    private UserEntity creator;
    private Timestamp updateTime;
    private Integer updateBy;
    private String info;
    private String coverImg;
    private String link;
    private PortalHomePageEntity portalHomePage;
    private Double sort;


    private Set<PortalHomePagePropertyEntity> propertys;

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
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
    @Column(name = "info")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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
    @Column(name = "link")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


    @Basic
    @Column(name = "sort")
    public Double getSort() {
        return sort;
    }

    public void setSort(Double sort) {
        this.sort = sort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PortalHomePageInfoEntity that = (PortalHomePageInfoEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (publishTime != null ? !publishTime.equals(that.publishTime) : that.publishTime != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (updateBy != null ? !updateBy.equals(that.updateBy) : that.updateBy != null) return false;
        if (info != null ? !info.equals(that.info) : that.info != null) return false;
        if (coverImg != null ? !coverImg.equals(that.coverImg) : that.coverImg != null) return false;
        if (link != null ? !link.equals(that.link) : that.link != null) return false;
        if (sort != null ? !sort.equals(that.sort) : that.sort != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (publishTime != null ? publishTime.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (updateBy != null ? updateBy.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        result = 31 * result + (coverImg != null ? coverImg.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (sort != null ? sort.hashCode() : 0);
        return result;
    }

    @OneToMany
    @JoinColumn(name = "pageInfoId")
    public Set<PortalHomePagePropertyEntity> getPropertys() {
        return propertys;
    }

    public void setPropertys(Set<PortalHomePagePropertyEntity> propertys) {
        this.propertys = propertys;
    }

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "pid")
    public PortalHomePageEntity getPortalHomePage() {
        return portalHomePage;
    }

    public void setPortalHomePage(PortalHomePageEntity portalHomePage) {
        this.portalHomePage = portalHomePage;
    }
    @OneToOne
    @JoinColumn(name = "createBy")
    public UserEntity getCreator() {
        return creator;
    }

    public void setCreator(UserEntity creator) {
        this.creator = creator;
    }
}
