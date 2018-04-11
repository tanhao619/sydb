package com.cdyoue.oddJobs.entity.syData;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by sky on 2017/9/19.
 */
@Entity
@Table(name = "sy_project_lecture", schema = "sydb", catalog = "")
public class ProjectLectureEntity {
    private int id;
    private String image;
    private String name;
    private String info;
    private String content;
    private Timestamp time;
    private Timestamp publishTime;
    private Timestamp updateTime;
    private String address;
    private Integer viewCount;
    private Integer publishPeople;
    private Integer top;
    private String topImage;


    @Basic
    @Column(name = "topImage")
    public String getTopImage() {
        return topImage;
    }

    public void setTopImage(String topImage) {
        this.topImage = topImage;
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
    @Column(name = "image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
    @Column(name = "info")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Basic
    @Column(name = "time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
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
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
    @Column(name = "publishPeople")
    public Integer getPublishPeople() {
        return publishPeople;
    }

    public void setPublishPeople(Integer publishPeople) {
        this.publishPeople = publishPeople;
    }

    @Basic
    @Column(name = "top")
    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectLectureEntity that = (ProjectLectureEntity) o;

        if (id != that.id) return false;
        if (image != null ? !image.equals(that.image) : that.image != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (info != null ? !info.equals(that.info) : that.info != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (publishTime != null ? !publishTime.equals(that.publishTime) : that.publishTime != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (viewCount != null ? !viewCount.equals(that.viewCount) : that.viewCount != null) return false;
        if (top != null ? !top.equals(that.top) : that.top != null) return false;
        if (publishPeople != null ? !publishPeople.equals(that.publishPeople) : that.publishPeople != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (publishTime != null ? publishTime.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (viewCount != null ? viewCount.hashCode() : 0);
        result = 31 * result + (top != null ? top.hashCode() : 0);
        result = 31 * result + (publishPeople != null ? publishPeople.hashCode() : 0);
        return result;
    }
}
