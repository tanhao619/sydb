package com.cdyoue.oddJobs.entity.lgsq.common;

import javax.persistence.*;

/**
 * Created by liaoyoule on 2017/4/27.
 */
@Entity
@Table(name = "outsourcingprojecttype", schema = "lgsq", catalog = "")
public class OutsourcingProjectTypeEntity {
    private Integer id;
    private String name;
    private Integer domainId;

    @Id
    @Column(name = "Id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "domainId")
    public Integer getDomainId() {
        return domainId;
    }

    public void setDomainId(Integer domainId) {
        this.domainId = domainId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OutsourcingProjectTypeEntity that = (OutsourcingProjectTypeEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (domainId != null ? !domainId.equals(that.domainId) : that.domainId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (domainId != null ? domainId.hashCode() : 0);
        return result;
    }
}
