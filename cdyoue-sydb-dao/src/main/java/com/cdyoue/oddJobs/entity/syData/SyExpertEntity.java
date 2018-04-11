package com.cdyoue.oddJobs.entity.syData;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by dengshaojun on 2017/09/18.
 */
@Entity
@Table(name = "sy_expert", schema = "sydb", catalog = "")
public class SyExpertEntity {
    private Integer id;
    private String topImg;
    private String headImg;
    private String name;
    private String job;
    private String briefIntro;
    private Byte gender;
    private Integer age;
    private String homePlace;
    private String livePlace;
    private String workYear;
    private String detailIntro;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Byte top;
    private Integer viewCount;
    private Integer contactCount;
    private Integer userId;
    private SyIndustryTypeEntity syIndustryTypeEntity;
    private Set<SyExpertAchievementEntity> syExpertAchievementEntitySet;
    private Set<SyProfessionalInterpretationEntity> syProfessionalInterpretationEntitySet;
    private Set<SyExpertCareerEntity> syExpertCareerEntitySet;
    private Set<SyExpertContactEntity> syExpertContactEntitySet;

    @Id
    @GeneratedValue
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "top_img")
    public String getTopImg() {
        return topImg;
    }

    public void setTopImg(String topImg) {
        this.topImg = topImg;
    }

    @Basic
    @Column(name = "head_img")
    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
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
    @Column(name = "job")
    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Basic
    @Column(name = "brief_intro")
    public String getBriefIntro() {
        return briefIntro;
    }

    public void setBriefIntro(String briefIntro) {
        this.briefIntro = briefIntro;
    }

    @Basic
    @Column(name = "gender")
    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "home_place")
    public String getHomePlace() {
        return homePlace;
    }

    public void setHomePlace(String homePlace) {
        this.homePlace = homePlace;
    }

    @Basic
    @Column(name = "live_place")
    public String getLivePlace() {
        return livePlace;
    }

    public void setLivePlace(String livePlace) {
        this.livePlace = livePlace;
    }

    @Basic
    @Column(name = "work_year")
    public String getWorkYear() {
        return workYear;
    }

    public void setWorkYear(String workYear) {
        this.workYear = workYear;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "top")
    public Byte getTop() {
        return top;
    }

    public void setTop(Byte top) {
        this.top = top;
    }

    @Basic
    @Column(name = "view_count")
    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    @Basic
    @Column(name = "contact_count")
    public Integer getContactCount() {
        return contactCount;
    }

    public void setContactCount(Integer contactCount) {
        this.contactCount = contactCount;
    }

    @Basic
    @Column(name = "detail_intro")
    public String getDetailIntro() {
        return detailIntro;
    }

    public void setDetailIntro(String detailIntro) {
        this.detailIntro = detailIntro;
    }

    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @ManyToOne
    @JoinColumn(name = "industry_id")
    public SyIndustryTypeEntity getSyIndustryTypeEntity() {
        return syIndustryTypeEntity;
    }

    public void setSyIndustryTypeEntity(SyIndustryTypeEntity syIndustryTypeEntity) {
        this.syIndustryTypeEntity = syIndustryTypeEntity;
    }

    @OneToMany(mappedBy = "syExpertEntity", fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    public Set<SyExpertAchievementEntity> getSyExpertAchievementEntitySet() {
        return syExpertAchievementEntitySet;
    }

    public void setSyExpertAchievementEntitySet(Set<SyExpertAchievementEntity> syExpertAchievementEntitySet) {
        this.syExpertAchievementEntitySet = syExpertAchievementEntitySet;
    }

    @OneToMany(mappedBy = "syExpertEntity", fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    public Set<SyProfessionalInterpretationEntity> getSyProfessionalInterpretationEntitySet() {
        return syProfessionalInterpretationEntitySet;
    }

    public void setSyProfessionalInterpretationEntitySet(Set<SyProfessionalInterpretationEntity> syProfessionalInterpretationEntitySet) {
        this.syProfessionalInterpretationEntitySet = syProfessionalInterpretationEntitySet;
    }

    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "expert_id")
    public Set<SyExpertCareerEntity> getSyExpertCareerEntitySet() {
        return syExpertCareerEntitySet;
    }

    public void setSyExpertCareerEntitySet(Set<SyExpertCareerEntity> syExpertCareerEntitySet) {
        this.syExpertCareerEntitySet = syExpertCareerEntitySet;
    }

    @OneToMany(mappedBy = "syExpertEntity", cascade = {CascadeType.REMOVE})
    public Set<SyExpertContactEntity> getSyExpertContactEntitySet() {
        return syExpertContactEntitySet;
    }

    public void setSyExpertContactEntitySet(Set<SyExpertContactEntity> syExpertContactEntitySet) {
        this.syExpertContactEntitySet = syExpertContactEntitySet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SyExpertEntity that = (SyExpertEntity) o;

        if (id != that.id) return false;
        if (topImg != null ? !topImg.equals(that.topImg) : that.topImg != null) return false;
        if (headImg != null ? !headImg.equals(that.headImg) : that.headImg != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (job != null ? !job.equals(that.job) : that.job != null) return false;
        if (briefIntro != null ? !briefIntro.equals(that.briefIntro) : that.briefIntro != null) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;
        if (age != null ? !age.equals(that.age) : that.age != null) return false;
        if (homePlace != null ? !homePlace.equals(that.homePlace) : that.homePlace != null) return false;
        if (livePlace != null ? !livePlace.equals(that.livePlace) : that.livePlace != null) return false;
        if (workYear != null ? !workYear.equals(that.workYear) : that.workYear != null) return false;
        if (syIndustryTypeEntity != null ? !syIndustryTypeEntity.equals(that.syIndustryTypeEntity) : that.syIndustryTypeEntity != null) return false;
        if (detailIntro != null ? !detailIntro.equals(that.detailIntro) : that.detailIntro != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (top != null ? !top.equals(that.top) : that.top != null) return false;
        if (viewCount != null ? !viewCount.equals(that.viewCount) : that.viewCount != null) return false;
        if (contactCount != null ? !contactCount.equals(that.contactCount) : that.contactCount != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (topImg != null ? topImg.hashCode() : 0);
        result = 31 * result + (headImg != null ? headImg.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (job != null ? job.hashCode() : 0);
        result = 31 * result + (briefIntro != null ? briefIntro.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (homePlace != null ? homePlace.hashCode() : 0);
        result = 31 * result + (livePlace != null ? livePlace.hashCode() : 0);
        result = 31 * result + (workYear != null ? workYear.hashCode() : 0);
        result = 31 * result + (syIndustryTypeEntity != null ? syIndustryTypeEntity.hashCode() : 0);
        result = 31 * result + (detailIntro != null ? detailIntro.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (top != null ? top.hashCode() : 0);
        result = 31 * result + (viewCount != null ? viewCount.hashCode() : 0);
        result = 31 * result + (contactCount != null ? contactCount.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
