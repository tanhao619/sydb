package com.cdyoue.oddJobs.entity.lgsq;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by liaoyoule on 2017/5/2.
 */
@Entity
@Table(name = "question", schema = "lgsq", catalog = "")
public class QuestionEntity {
    private Integer id;
    private Timestamp publicTime;
    private Timestamp createTime;
    private Integer typeId;
    private Integer userId;
    private String title;
    private String introduction;
    private Integer topicId;
    private Integer followCount;
    private Integer domainId;
    private Integer viewCount;//新加字段，浏览量（点击量）

    private Set<QuestionreplyEntity> questionreplys;


    private PortalTopicEntity portalTopicEntity;

    @GeneratedValue
    @Id
    @Column(name = "Id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "PublicTime")
    public Timestamp getPublicTime() {
        return publicTime;
    }

    public void setPublicTime(Timestamp publicTime) {
        this.publicTime = publicTime;
    }

    @Basic
    @Column(name = "CreateTime")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "TypeId")
    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
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
    @Column(name = "Title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "Introduction")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Basic
    @Column(name = "TopicId")
    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    @Basic
    @Column(name = "FollowCount")
    public Integer getFollowCount() {
        return followCount;
    }

    public void setFollowCount(Integer followCount) {
        this.followCount = followCount;
    }

    @Basic
    @Column(name = "domainId")
    public Integer getDomainId() {
        return domainId;
    }

    public void setDomainId(Integer domainId) {
        this.domainId = domainId;
    }

    @Basic
    @Column(name="viewCount")
    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionEntity that = (QuestionEntity) o;

        if (id != that.id) return false;
        if (publicTime != null ? !publicTime.equals(that.publicTime) : that.publicTime != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (typeId != null ? !typeId.equals(that.typeId) : that.typeId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (introduction != null ? !introduction.equals(that.introduction) : that.introduction != null) return false;
        if (topicId != null ? !topicId.equals(that.topicId) : that.topicId != null) return false;
        if (followCount != null ? !followCount.equals(that.followCount) : that.followCount != null) return false;
        if (domainId != null ? !domainId.equals(that.domainId) : that.domainId != null) return false;
        if (viewCount != null ? !viewCount.equals(that.viewCount) : that.viewCount != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (publicTime != null ? publicTime.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (typeId != null ? typeId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (introduction != null ? introduction.hashCode() : 0);
        result = 31 * result + (topicId != null ? topicId.hashCode() : 0);
        result = 31 * result + (followCount != null ? followCount.hashCode() : 0);
        result = 31 * result + (domainId != null ? domainId.hashCode() : 0);
        result = 31 * result + (viewCount != null ? viewCount.hashCode() : 0);
        return result;
    }

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "QuestionId")
    public Set<QuestionreplyEntity> getQuestionreplys() {
        return questionreplys;
    }

    public void setQuestionreplys(Set<QuestionreplyEntity> questionreplys) {
        this.questionreplys = questionreplys;
    }


    @ManyToOne
    @JoinColumn(name = "TopicId",insertable = false,updatable = false)
    @JsonIgnore
    public PortalTopicEntity getPortalTopicEntity() {
        return portalTopicEntity;
    }

    public void setPortalTopicEntity(PortalTopicEntity portalTopicEntity) {
        this.portalTopicEntity = portalTopicEntity;
    }
}
