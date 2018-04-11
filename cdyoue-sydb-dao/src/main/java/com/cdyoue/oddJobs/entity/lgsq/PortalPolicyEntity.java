package com.cdyoue.oddJobs.entity.lgsq;

import javax.persistence.*;

/**
 * Created by liaoyoule on 2017/5/2.
 */
@Entity
@Table(name = "lg_portal_policy", schema = "lgsq", catalog = "")
public class PortalPolicyEntity {
    private int id;
    private String name;
    private String code;
    private String enterpriseName;
    private String content;
    private Integer areaPolicy;
    private Integer gardenPolicy;
    private Integer people;

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
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "enterpriseName")
    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
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
    @Column(name = "areaPolicy")
    public Integer getAreaPolicy() {
        return areaPolicy;
    }

    public void setAreaPolicy(Integer areaPolicy) {
        this.areaPolicy = areaPolicy;
    }

    @Basic
    @Column(name = "gardenPolicy")
    public Integer getGardenPolicy() {
        return gardenPolicy;
    }

    public void setGardenPolicy(Integer gardenPolicy) {
        this.gardenPolicy = gardenPolicy;
    }

    @Basic
    @Column(name = "people")
    public Integer getPeople() {
        return people;
    }

    public void setPeople(Integer people) {
        this.people = people;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PortalPolicyEntity that = (PortalPolicyEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (enterpriseName != null ? !enterpriseName.equals(that.enterpriseName) : that.enterpriseName != null)
            return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (areaPolicy != null ? !areaPolicy.equals(that.areaPolicy) : that.areaPolicy != null) return false;
        if (gardenPolicy != null ? !gardenPolicy.equals(that.gardenPolicy) : that.gardenPolicy != null) return false;
        if (people != null ? !people.equals(that.people) : that.people != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (enterpriseName != null ? enterpriseName.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (areaPolicy != null ? areaPolicy.hashCode() : 0);
        result = 31 * result + (gardenPolicy != null ? gardenPolicy.hashCode() : 0);
        result = 31 * result + (people != null ? people.hashCode() : 0);
        return result;
    }
}
