package com.cdyoue.oddJobs.entity.lgsq;

import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by liaoyoule on 2017/5/10.
 */
@Entity
@Table(name = "lg_portal_independent_text", schema = "lgsq", catalog = "")
public class PortalIndependentText implements Serializable{
    private Integer id;
    private String title;
    private String coverImg;
    private String brief;
    private String intro;
    private Timestamp createTime;
    private UserEntity creator;
    private UserEntity updator;
    private Timestamp updateTime;
    private Integer type;
    private Integer viewNum;

    @Id
    @Column(name = "id")
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
    @Column(name = "brief")
    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    @Basic
    @Column(name = "intro")
    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
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
    @Column(name = "viewNum")
    public Integer getViewNum() {
        return viewNum;
    }

    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PortalIndependentText that = (PortalIndependentText) o;

        if (id != that.id) return false;
        if (coverImg != null ? !coverImg.equals(that.coverImg) : that.coverImg != null) return false;
        if (brief != null ? !brief.equals(that.brief) : that.brief != null) return false;
        if (intro != null ? !intro.equals(that.intro) : that.intro != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (coverImg != null ? coverImg.hashCode() : 0);
        result = 31 * result + (brief != null ? brief.hashCode() : 0);
        result = 31 * result + (intro != null ? intro.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "createBy")
    public UserEntity getCreator() {
        return creator;
    }

    public void setCreator(UserEntity creator) {
        this.creator = creator;
    }
    @OneToOne
    @JoinColumn(name = "updateBy")
    public UserEntity getUpdator() {
        return updator;
    }

    public void setUpdator(UserEntity updator) {
        this.updator = updator;
    }
}
