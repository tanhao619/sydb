package com.cdyoue.oddJobs.entity.lgsq;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by liaoyoule on 2017/5/2.
 */
@Entity
@Table(name = "questionreply", schema = "lgsq", catalog = "")
public class QuestionreplyEntity {
    private int id;
    private Integer questionTypeId;
    private String context;
    private Integer userId;
    private Timestamp createTime;
    private Integer likeCount;
    private Integer favorCount;
//    private Integer questionId;
    private QuestionEntity questionEntity;


    @Id
    @Column(name = "Id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    /*@Basic
    @Column(name = "QuestionId")
    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }*/

    @Basic
    @Column(name = "QuestionTypeId")
    public Integer getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(Integer questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    @Basic
    @Column(name = "Context")
    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
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
    @Column(name = "CreateTime")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "LikeCount")
    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    @Basic
    @Column(name = "FavorCount")
    public Integer getFavorCount() {
        return favorCount;
    }

    public void setFavorCount(Integer favorCount) {
        this.favorCount = favorCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionreplyEntity that = (QuestionreplyEntity) o;

        if (id != that.id) return false;
        if (questionTypeId != null ? !questionTypeId.equals(that.questionTypeId) : that.questionTypeId != null)
            return false;
        if (context != null ? !context.equals(that.context) : that.context != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (likeCount != null ? !likeCount.equals(that.likeCount) : that.likeCount != null) return false;
        if (favorCount != null ? !favorCount.equals(that.favorCount) : that.favorCount != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (questionTypeId != null ? questionTypeId.hashCode() : 0);
        result = 31 * result + (context != null ? context.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (likeCount != null ? likeCount.hashCode() : 0);
        result = 31 * result + (favorCount != null ? favorCount.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "QuestionId")
    public QuestionEntity getQuestionEntity() {

        return questionEntity;
    }

    public void setQuestionEntity(QuestionEntity questionEntity) {
        this.questionEntity = questionEntity;
    }
}
