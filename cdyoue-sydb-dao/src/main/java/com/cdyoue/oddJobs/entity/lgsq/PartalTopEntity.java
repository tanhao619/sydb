package com.cdyoue.oddJobs.entity.lgsq;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2017/5/10.
 */
@Entity
@Table(name = "lg_partal_top", schema = "lgsq", catalog = "")
public class    PartalTopEntity {
    private int id;
    private String title;
    private int createBy;
    private Timestamp refreshTime;
    private String coverImg;
    private int orderBy;
    private int pageNum;
    private String link;

    @Id
    @Column(name = "id")
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
    @Column(name = "createBy")
    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "refreshTime")
    public Timestamp getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(Timestamp refreshTime) {
        this.refreshTime = refreshTime;
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
    @Column(name = "orderBy")
    public int getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(int orderBy) {
        this.orderBy = orderBy;
    }

    @Basic
    @Column(name = "pageNum")
    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    @Basic
    @Column(name = "link")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PartalTopEntity entity = (PartalTopEntity) o;

        if (id != entity.id) return false;
        if (createBy != entity.createBy) return false;
        if (orderBy != entity.orderBy) return false;
        if (pageNum != entity.pageNum) return false;
        if (title != null ? !title.equals(entity.title) : entity.title != null) return false;
        if (refreshTime != null ? !refreshTime.equals(entity.refreshTime) : entity.refreshTime != null) return false;
        if (coverImg != null ? !coverImg.equals(entity.coverImg) : entity.coverImg != null) return false;
        return link != null ? link.equals(entity.link) : entity.link == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + createBy;
        result = 31 * result + (refreshTime != null ? refreshTime.hashCode() : 0);
        result = 31 * result + (coverImg != null ? coverImg.hashCode() : 0);
        result = 31 * result + orderBy;
        result = 31 * result + pageNum;
        result = 31 * result + (link != null ? link.hashCode() : 0);
        return result;
    }
}
