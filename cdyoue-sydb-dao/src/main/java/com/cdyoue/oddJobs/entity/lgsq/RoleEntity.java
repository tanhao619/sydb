package com.cdyoue.oddJobs.entity.lgsq;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by liaoyoule on 2017/6/9.
 */
@Entity
@Table(name = "role", schema = "lgsq", catalog = "")
public class RoleEntity {
    private Integer id;
    private String name;
    private String intro;
    private Timestamp createTime;
    private Integer createBy;
    private Timestamp updateTime;
    private Integer updateBy;
    private Set<MenuEntity> leftMenus;

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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Basic
    @Column(name = "intro")
    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    @Basic
    @Column(name = "createTime")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "createBy")
    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }
    @Basic
    @Column(name = "updateTime")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
    @Basic
    @Column(name = "updateBy")
    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleEntity that = (RoleEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (createBy != null ? !createBy.equals(that.createBy) : that.createBy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        return result;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "role_menu",
            joinColumns =
            @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false),
            inverseJoinColumns =
            @JoinColumn(name = "menu_id", referencedColumnName = "id", nullable = false)
    )
    public Set<MenuEntity> getLeftMenus() {
        return leftMenus;
    }

    public void setLeftMenus(Set<MenuEntity> leftMenus) {
        this.leftMenus = leftMenus;
    }
}
