package com.cdyoue.oddJobs.entity.lgsq.homePage;

import javax.persistence.*;

/**
 * Created by liaoyoule on 2017/5/6.
 */
@Entity
@Table(name = "lg_portal_home_page_property", schema = "lgsq", catalog = "")
public class PortalHomePagePropertyEntity {
    private Integer id;
    private Integer pageInfoId;
    private String name;
    private String value;
    private String valueType;

    @Id
    @Column(name = "id")
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "pageInfoId")
    public Integer getPageInfoId() {
        return pageInfoId;
    }

    public void setPageInfoId(Integer pageInfoId) {
        this.pageInfoId = pageInfoId;
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
    @Column(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Basic
    @Column(name = "valueType")
    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PortalHomePagePropertyEntity that = (PortalHomePagePropertyEntity) o;

        if (id != that.id) return false;
        if (pageInfoId != null ? !pageInfoId.equals(that.pageInfoId) : that.pageInfoId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        if (valueType != null ? !valueType.equals(that.valueType) : that.valueType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (pageInfoId != null ? pageInfoId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (valueType != null ? valueType.hashCode() : 0);
        return result;
    }
}
