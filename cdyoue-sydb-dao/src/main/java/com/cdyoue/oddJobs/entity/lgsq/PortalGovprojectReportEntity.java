package com.cdyoue.oddJobs.entity.lgsq;

import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by liaoyoule on 2017/5/2.
 */
@Entity
@Table(name = "lg_portal_project_report", schema = "lgsq", catalog = "")
public class PortalGovprojectReportEntity {
    private int id;
    private String title;
    private Timestamp publishTime;
    private String duration;
    private String sourceFrom;
    private String code;
    private String introduction;
    private UserEntity createBy;
    private Integer viewCount;

    @Id
    @Column(name = "id")
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "startToEndTime")
    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Basic
    @Column(name = "sourceFrom")
    public String getSourceFrom() {
        return sourceFrom;
    }

    public void setSourceFrom(String sourceFrom) {
        this.sourceFrom = sourceFrom;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
    @Column(name = "viewCount")
    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    @OneToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "createBy")
    public UserEntity getCreateBy() {
        return createBy;
    }

    public void setCreateBy(UserEntity createBy) {
        this.createBy = createBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PortalGovprojectReportEntity that = (PortalGovprojectReportEntity) o;

        if (id != that.id) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (publishTime != null ? !publishTime.equals(that.publishTime) : that.publishTime != null) return false;
        if (duration != null ? !duration.equals(that.duration) : that.duration != null) return false;
        if (sourceFrom != null ? !sourceFrom.equals(that.sourceFrom) : that.sourceFrom != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (introduction != null ? !introduction.equals(that.introduction) : that.introduction != null) return false;
        if (createBy != null ? !createBy.equals(that.createBy) : that.createBy != null) return false;
        return viewCount != null ? viewCount.equals(that.viewCount) : that.viewCount == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (publishTime != null ? publishTime.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (sourceFrom != null ? sourceFrom.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (introduction != null ? introduction.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (viewCount != null ? viewCount.hashCode() : 0);
        return result;
    }
}
