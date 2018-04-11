package com.cdyoue.oddJobs.entity.lgsq;

import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by tr on 2017/5/16.
 */

@Entity
@Table(name = "lg_portal_intellectual_innovate", schema = "lgsq", catalog = "")
public class PortalIntellectualInnovateEntity {
    private Integer id;
    private String name;
    private String tel;
    private String link;
    private String info;
    private String introduction;
    private String coverImg;
    private Integer type;
    private String serviceInfo;
    private Timestamp createTime;
    private UserEntity createBy;
    private Integer viewCount;

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
    @Column(name = "link")
    public String getLink() {
        return link;
    }


    public void setLink(String link) {
        this.link = link;
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
    @Column(name = "coverImg")
    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    @Basic
    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "viewCount")
    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "createBy")
    public UserEntity getCreateBy() {
        return createBy;
    }

    public void setCreateBy(UserEntity createBy) {
        this.createBy = createBy;
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
    @Column(name = "serviceInfo")
    public String getServiceInfo() {
        return serviceInfo;
    }

    public void setServiceInfo(String serviceInfo) {
        this.serviceInfo = serviceInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PortalIntellectualInnovateEntity that = (PortalIntellectualInnovateEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (tel != null ? !tel.equals(that.tel) : that.tel != null) return false;
        if (link != null ? !link.equals(that.link) : that.link != null) return false;
        if (info != null ? !info.equals(that.info) : that.info != null) return false;
        if (introduction != null ? !introduction.equals(that.introduction) : that.introduction != null) return false;
        if (coverImg != null ? !coverImg.equals(that.coverImg) : that.coverImg != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (serviceInfo != null ? !serviceInfo.equals(that.serviceInfo) : that.serviceInfo != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (createBy != null ? !createBy.equals(that.createBy) : that.createBy != null) return false;
        return viewCount != null ? viewCount.equals(that.viewCount) : that.viewCount == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        result = 31 * result + (introduction != null ? introduction.hashCode() : 0);
        result = 31 * result + (coverImg != null ? coverImg.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (serviceInfo != null ? serviceInfo.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (viewCount != null ? viewCount.hashCode() : 0);
        return result;
    }
}
