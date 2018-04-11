package com.cdyoue.oddJobs.entity.syData;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by sky on 2017/9/18.
 */
@Entity
@Table(name = "sy_project", schema = "sydb", catalog = "")
public class ProjectEntity {
    private int id;
    private String name;
    private Integer publishPeople;
    private String content;
    private Timestamp publishTime;
    private Timestamp updateTime;
    private String department;
    private Timestamp startTime;
    private Timestamp endTime;
    private Integer viewCount;
    private String projectNumber;
    private String source;
    private String attachmentUrl;
    private Set<SyDeclarationProjectMessageEntity> syDeclarationProjectMessageEntitySet;

    @OneToMany(mappedBy = "projectEntity",cascade = {CascadeType.REMOVE})
    public Set<SyDeclarationProjectMessageEntity> getSyDeclarationProjectMessageEntitySet() {
        return syDeclarationProjectMessageEntitySet;
    }

    public void setSyDeclarationProjectMessageEntitySet(Set<SyDeclarationProjectMessageEntity> syDeclarationProjectMessageEntitySet) {
        this.syDeclarationProjectMessageEntitySet = syDeclarationProjectMessageEntitySet;
    }

    @Basic
    @Column(name = "updateTime")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

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
    @Column(name = "publishPeople")
    public Integer getPublishPeople() {
        return publishPeople;
    }

    public void setPublishPeople(Integer publishPeople) {
        this.publishPeople = publishPeople;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
    @Column(name = "department")
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Basic
    @Column(name = "startTime")
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "endTime")
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
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
    @Column(name = "projectNumber")
    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    @Basic
    @Column(name = "source")
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Basic
    @Column(name = "attachmentUrl")
    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectEntity that = (ProjectEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (publishTime != null ? !publishTime.equals(that.publishTime) : that.publishTime != null) return false;
        if (department != null ? !department.equals(that.department) : that.department != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (viewCount != null ? !viewCount.equals(that.viewCount) : that.viewCount != null) return false;
        if (publishPeople != null ? !publishPeople.equals(that.publishPeople) : that.publishPeople != null) return false;
        if (projectNumber != null ? !projectNumber.equals(that.projectNumber) : that.projectNumber != null)
            return false;
        if (source != null ? !source.equals(that.source) : that.source != null) return false;
        if (attachmentUrl != null ? !attachmentUrl.equals(that.attachmentUrl) : that.attachmentUrl != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (publishTime != null ? publishTime.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (viewCount != null ? viewCount.hashCode() : 0);
        result = 31 * result + (publishPeople != null ? publishPeople.hashCode() : 0);
        result = 31 * result + (projectNumber != null ? projectNumber.hashCode() : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        result = 31 * result + (attachmentUrl != null ? attachmentUrl.hashCode() : 0);
        return result;
    }
}
