package com.cdyoue.oddJobs.entity.lgsq;

import javax.persistence.*;

/**
 * Created by liaoyoule on 2017/5/2.
 */
@Entity
@Table(name = "lg_portal_incubator_level", schema = "lgsq", catalog = "")
public class PortalIncubatorLevelEntity {
    private int id;
    private String name;
    private String coverImg;
    private String info;

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
    @Column(name = "coverImg")
    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    @Basic
    @Column(name = "info")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PortalIncubatorLevelEntity that = (PortalIncubatorLevelEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (coverImg != null ? !coverImg.equals(that.coverImg) : that.coverImg != null) return false;
        if (info != null ? !info.equals(that.info) : that.info != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (coverImg != null ? coverImg.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }
}
