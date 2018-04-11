package com.cdyoue.oddJobs.entity.lgsq;

import com.cdyoue.oddJobs.entity.lgsq.common.OutsourcingProjectTypeEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by liaoyoule on 2017/4/20.
 */
@Entity
@Table(name = "outsourcingproject", schema = "lgsq", catalog = "")
public class OutsourcingProjectEntity {
    private Integer id;
    private String name;
    private String introduction;
    private String contact;
    private String coverImg;
    private String tel;
    private BigDecimal proBudget;
    private OutsourcingProjectTypeEntity outsourcingProjectTypeEntity;
    private Byte acceptType;
    private Byte reviewStatus;
    private Timestamp reviewTime;
    private Integer reviewUserId;
    private Byte status;
    private Timestamp publishTime;
    private Timestamp createTime;
    private UserEntity createBy;
    private Timestamp updateTime;
    private Integer updateBy;
    private Integer viewNum;
    private Integer deadLine;
    private String attachUrl;
    private String reviewReason;


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @Column(name = "introduction")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
    @Basic
    @Column(name = "contact")
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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
    @Column(name = "tel")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "proBudget")
    public BigDecimal getProBudget() {
        return proBudget;
    }

    public void setProBudget(BigDecimal proBudget) {
        this.proBudget = proBudget;
    }




    @Basic
    @Column(name = "acceptType")
    public Byte getAcceptType() {
        return acceptType;
    }

    public void setAcceptType(Byte acceptType) {
        this.acceptType = acceptType;
    }

    @Basic
    @Column(name = "reviewStatus")
    public Byte getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(Byte reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    @Basic
    @Column(name = "reviewTime")
    public Timestamp getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Timestamp reviewTime) {
        this.reviewTime = reviewTime;
    }

    @Basic
    @Column(name = "reviewUserId")
    public Integer getReviewUserId() {
        return reviewUserId;
    }

    public void setReviewUserId(Integer reviewUserId) {
        this.reviewUserId = reviewUserId;
    }

    @Basic
    @Column(name = "status")
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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
    @Column(name = "deadLine")
    public Integer getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Integer deadLine) {
        this.deadLine = deadLine;
    }

    @Basic
    @Column(name = "viewCount")
    public Integer getViewNum() {
        return viewNum;
    }

    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
    }
    @Basic
    @Column(name = "attachUrl")
    public String getAttachUrl() {
        return attachUrl;
    }

    public void setAttachUrl(String attachUrl) {
        this.attachUrl = attachUrl;
    }

    @Basic
    @Column(name = "reviewReason")
    public String getReviewReason() {
        return reviewReason;
    }

    public void setReviewReason(String reviewReason) {
        this.reviewReason = reviewReason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OutsourcingProjectEntity that = (OutsourcingProjectEntity) o;

        if (id != that.id) return false;
        if (acceptType != that.acceptType) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (introduction != null ? !introduction.equals(that.introduction) : that.introduction != null) return false;
        if (coverImg != null ? !coverImg.equals(that.coverImg) : that.coverImg != null) return false;
        if (tel != null ? !tel.equals(that.tel) : that.tel != null) return false;
        if (proBudget != null ? !proBudget.equals(that.proBudget) : that.proBudget != null) return false;
        if (reviewStatus != null ? !reviewStatus.equals(that.reviewStatus) : that.reviewStatus != null) return false;
        if (reviewTime != null ? !reviewTime.equals(that.reviewTime) : that.reviewTime != null) return false;
        if (reviewUserId != null ? !reviewUserId.equals(that.reviewUserId) : that.reviewUserId != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (publishTime != null ? !publishTime.equals(that.publishTime) : that.publishTime != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (createBy != null ? !createBy.equals(that.createBy) : that.createBy != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (updateBy != null ? !updateBy.equals(that.updateBy) : that.updateBy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (introduction != null ? introduction.hashCode() : 0);
        result = 31 * result + (coverImg != null ? coverImg.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (proBudget != null ? proBudget.hashCode() : 0);
        result = 31 * result + (int) acceptType;
        result = 31 * result + (reviewStatus != null ? reviewStatus.hashCode() : 0);
        result = 31 * result + (reviewTime != null ? reviewTime.hashCode() : 0);
        result = 31 * result + (reviewUserId != null ? reviewUserId.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (publishTime != null ? publishTime.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (updateBy != null ? updateBy.hashCode() : 0);
        return result;
    }



    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TypeId")
    public OutsourcingProjectTypeEntity getOutsourcingProjectTypeEntity() {
        return outsourcingProjectTypeEntity;
    }

    public void setOutsourcingProjectTypeEntity(OutsourcingProjectTypeEntity outsourcingProjectTypeEntity) {
        this.outsourcingProjectTypeEntity = outsourcingProjectTypeEntity;
    }



    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "createBy")
    public UserEntity getCreateBy() {
        return createBy;
    }

    public void setCreateBy(UserEntity createBy) {
        this.createBy = createBy;
    }

}
