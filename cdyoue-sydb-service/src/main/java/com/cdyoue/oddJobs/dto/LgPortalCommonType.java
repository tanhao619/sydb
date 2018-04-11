package com.cdyoue.oddJobs.dto;

/**
 * Created by liaoyoule on 2017/4/20.
 */
public class LgPortalCommonType {
    private Integer id;
    private String name;
    private Byte commonType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getCommonType() {
        return commonType;
    }

    public void setCommonType(Byte commonType) {
        this.commonType = commonType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LgPortalCommonType that = (LgPortalCommonType) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return commonType != null ? commonType.equals(that.commonType) : that.commonType == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (commonType != null ? commonType.hashCode() : 0);
        return result;
    }
}
