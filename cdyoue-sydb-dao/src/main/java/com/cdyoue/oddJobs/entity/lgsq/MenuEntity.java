package com.cdyoue.oddJobs.entity.lgsq;

import javax.persistence.*;

/**
 * Created by liaoyoule on 2017/6/9.
 */
@Entity
@Table(name = "menu", schema = "lgsq", catalog = "")
public class MenuEntity {
    private Integer id;
    private String name;
    private String uiSref;
    private String icon;
    private MenuEntity topMenu;


    @Id
    @Column(name = "id")
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
    @Column(name = "ui_sref")
    public String getUiSref() {
        return uiSref;
    }

    public void setUiSref(String uiSref) {
        this.uiSref = uiSref;
    }
    @Basic
    @Column(name = "icon")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuEntity that = (MenuEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (uiSref != null ? !uiSref.equals(that.uiSref) : that.uiSref != null) return false;
        return topMenu != null ? topMenu.equals(that.topMenu) : that.topMenu == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (uiSref != null ? uiSref.hashCode() : 0);
        result = 31 * result + (topMenu != null ? topMenu.hashCode() : 0);
        return result;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pid")
    @OrderBy("topMenu")
    public MenuEntity getTopMenu() {
        return topMenu;
    }

    public void setTopMenu(MenuEntity topMenu) {
        this.topMenu = topMenu;
    }
}
