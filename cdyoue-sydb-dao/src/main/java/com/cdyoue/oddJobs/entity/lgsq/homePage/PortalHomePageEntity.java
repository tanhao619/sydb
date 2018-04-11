package com.cdyoue.oddJobs.entity.lgsq.homePage;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by liaoyoule on 2017/5/6.
 */
@Entity
@Table(name = "lg_portal_home_page", schema = "lgsq", catalog = "")
public class PortalHomePageEntity {
    private int id;
    private String name;
    private String code;
    private Set<PortalHomePageInfoEntity> pageinfos;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PortalHomePageEntity that = (PortalHomePageEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        return pageinfos != null ? pageinfos.equals(that.pageinfos) : that.pageinfos == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (pageinfos != null ? pageinfos.hashCode() : 0);
        return result;
    }

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "portalHomePage")
    @OrderBy("sort ASC")
    public Set<PortalHomePageInfoEntity> getPageinfos() {
        return pageinfos;
    }

    public void setPageinfos(Set<PortalHomePageInfoEntity> pageinfos) {
        this.pageinfos = pageinfos;
    }
}
