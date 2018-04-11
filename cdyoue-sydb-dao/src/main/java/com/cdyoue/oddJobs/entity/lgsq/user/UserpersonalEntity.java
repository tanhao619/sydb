package com.cdyoue.oddJobs.entity.lgsq.user;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by liaoyoule on 2017/4/24.
 */
@Entity
@Table(name = "userpersonal", schema = "lgsq", catalog = "")
public class UserpersonalEntity implements Serializable {
    private int id;
    private String name;
    private String nickName;
    private String headImg;
    private String signature;
    private String job;
    private Timestamp birthday;
    private String homeTown;
    private String location;
    private Integer activity;
    private String info;
    private String introduction;
    private Integer systemRole;
    private Integer userId;
    private Integer recruitmentCategoryId;
    private Integer parttimeCategoryId;
    private Integer outsourcingprojectType;
    private Integer invitedNum;
    private Integer dataComp;

    private Integer viewCount;



    @Id
    @Column(name = "Id")
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Basic
    @Column(name = "viewCount")
    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(final Integer viewCount) {
        this.viewCount = viewCount;
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
    @Column(name = "signature")
    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
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
    @Column(name = "birthday")
    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "homeTown")
    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
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
    @Column(name = "activity")
    public Integer getActivity() {
        return activity;
    }

    public void setActivity(Integer activity) {
        this.activity = activity;
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
    @Column(name = "systemRole")
    public Integer getSystemRole() {
        return systemRole;
    }

    public void setSystemRole(Integer systemRole) {
        this.systemRole = systemRole;
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
    @Column(name = "recruitmentCategoryId")
    public Integer getRecruitmentCategoryId() {
        return recruitmentCategoryId;
    }

    public void setRecruitmentCategoryId(Integer recruitmentCategoryId) {
        this.recruitmentCategoryId = recruitmentCategoryId;
    }

    @Basic
    @Column(name = "parttimeCategoryId")
    public Integer getParttimeCategoryId() {
        return parttimeCategoryId;
    }

    public void setParttimeCategoryId(Integer parttimeCategoryId) {
        this.parttimeCategoryId = parttimeCategoryId;
    }

    @Basic
    @Column(name = "outsourcingprojectType")
    public Integer getOutsourcingprojectType() {
        return outsourcingprojectType;
    }

    public void setOutsourcingprojectType(Integer outsourcingprojectType) {
        this.outsourcingprojectType = outsourcingprojectType;
    }

    @Basic
    @Column(name = "invitedNum")
    public Integer getInvitedNum() {
        return invitedNum;
    }

    public void setInvitedNum(Integer invitedNum) {
        this.invitedNum = invitedNum;
    }

    @Basic
    @Column(name = "dataComp")
    public Integer getDataComp() {
        return dataComp;
    }

    public void setDataComp(Integer dataComp) {
        this.dataComp = dataComp;
    }
    //昵称
    @Basic
    @Column(name = "nickName")
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserpersonalEntity that = (UserpersonalEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (nickName != null ? !nickName.equals(that.nickName) : that.nickName != null) return false;
        if (headImg != null ? !headImg.equals(that.headImg) : that.headImg != null) return false;
        if (signature != null ? !signature.equals(that.signature) : that.signature != null) return false;
        if (job != null ? !job.equals(that.job) : that.job != null) return false;
        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;
        if (homeTown != null ? !homeTown.equals(that.homeTown) : that.homeTown != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        if (activity != null ? !activity.equals(that.activity) : that.activity != null) return false;
        if (info != null ? !info.equals(that.info) : that.info != null) return false;
        if (introduction != null ? !introduction.equals(that.introduction) : that.introduction != null) return false;
        if (systemRole != null ? !systemRole.equals(that.systemRole) : that.systemRole != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (recruitmentCategoryId != null ? !recruitmentCategoryId.equals(that.recruitmentCategoryId) : that.recruitmentCategoryId != null)
            return false;
        if (parttimeCategoryId != null ? !parttimeCategoryId.equals(that.parttimeCategoryId) : that.parttimeCategoryId != null)
            return false;
        if (outsourcingprojectType != null ? !outsourcingprojectType.equals(that.outsourcingprojectType) : that.outsourcingprojectType != null)
            return false;
        if (invitedNum != null ? !invitedNum.equals(that.invitedNum) : that.invitedNum != null) return false;
        if (viewCount != null ? !viewCount.equals(that.viewCount) : that.viewCount != null) return false;
        return dataComp != null ? dataComp.equals(that.dataComp) : that.dataComp == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (nickName != null ? nickName.hashCode() : 0);
        result = 31 * result + (headImg != null ? headImg.hashCode() : 0);
        result = 31 * result + (signature != null ? signature.hashCode() : 0);
        result = 31 * result + (job != null ? job.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (homeTown != null ? homeTown.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (activity != null ? activity.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        result = 31 * result + (introduction != null ? introduction.hashCode() : 0);
        result = 31 * result + (systemRole != null ? systemRole.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (recruitmentCategoryId != null ? recruitmentCategoryId.hashCode() : 0);
        result = 31 * result + (parttimeCategoryId != null ? parttimeCategoryId.hashCode() : 0);
        result = 31 * result + (outsourcingprojectType != null ? outsourcingprojectType.hashCode() : 0);
        result = 31 * result + (invitedNum != null ? invitedNum.hashCode() : 0);
        result = 31 * result + (dataComp != null ? dataComp.hashCode() : 0);
        result = 31 * result + (viewCount != null ? viewCount.hashCode() : 0);
        return result;
    }
}
