package com.cdyoue.oddJobs.entity.lgsq.common;

import javax.persistence.*;

/**
 * Created by liaoyoule on 2017/5/6.
 */
@Entity
@Table(name = "lg_portal_domain", schema = "lgsq", catalog = "")
public class PortalDomainEntity {
    private int id;
    private String name;
    private Integer parentId;
    private String treeCode;

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
    @Column(name = "parentId")
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "treeCode")
    public String getTreeCode() {
        return treeCode;
    }

    public void setTreeCode(String treeCode) {
        this.treeCode = treeCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PortalDomainEntity that = (PortalDomainEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (parentId != null ? !parentId.equals(that.parentId) : that.parentId != null) return false;
        if (treeCode != null ? !treeCode.equals(that.treeCode) : that.treeCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (treeCode != null ? treeCode.hashCode() : 0);
        return result;
    }
}
