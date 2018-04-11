package com.cdyoue.oddJobs.entity.syData;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by dengshaojun on 2017/09/18.
 */
@Entity
@Table(name = "sy_expert_career", schema = "sydb", catalog = "")
public class SyExpertCareerEntity {
    private Integer id;
    private String brief;
    private String detail;
    private Integer expertId;

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
    @Column(name = "brief")
    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    @Basic
    @Column(name = "detail")
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Basic
    @Column(name = "expert_id")
    public Integer getExpertId() {
        return expertId;
    }

    public void setExpertId(Integer expertId) {
        this.expertId = expertId;
    }

    /*@ManyToOne
    @JoinColumn(name = "expert_id")
    public SyExpertEntity getSyExpertEntity() {
        return syExpertEntity;
    }

    public void setSyExpertEntity(SyExpertEntity syExpertEntity) {
        this.syExpertEntity = syExpertEntity;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SyExpertCareerEntity that = (SyExpertCareerEntity) o;

        //if (id != that.id) return false;
        if (brief != null ? !brief.equals(that.brief) : that.brief != null) return false;
        if (detail != null ? !detail.equals(that.detail) : that.detail != null) return false;
        //if (syExpertEntity != null ? !syExpertEntity.equals(that.syExpertEntity) : that.syExpertEntity != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (brief != null ? brief.hashCode() : 0);
        result = 31 * result + (detail != null ? detail.hashCode() : 0);
        //result = 31 * result + (syExpertEntity != null ? syExpertEntity.hashCode() : 0);
        return result;
    }
}
