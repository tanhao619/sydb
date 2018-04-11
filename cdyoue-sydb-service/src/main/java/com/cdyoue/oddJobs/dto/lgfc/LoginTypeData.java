package com.cdyoue.oddJobs.dto.lgfc;

/**
 * Created by tr on 2017/5/27.
 */
public class LoginTypeData {
    private Integer userid;
    private Integer mtlogintimes;
    private Integer pclogintimes;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getMtlogintimes() {
        return mtlogintimes;
    }

    public void setMtlogintimes(Integer mtlogintimes) {
        this.mtlogintimes = mtlogintimes;
    }

    public Integer getPclogintimes() {
        return pclogintimes;
    }

    public void setPclogintimes(Integer pclogintimes) {
        this.pclogintimes = pclogintimes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoginTypeData that = (LoginTypeData) o;

        if (userid != null ? !userid.equals(that.userid) : that.userid != null) return false;
        if (mtlogintimes != null ? !mtlogintimes.equals(that.mtlogintimes) : that.mtlogintimes != null) return false;
        return pclogintimes != null ? pclogintimes.equals(that.pclogintimes) : that.pclogintimes == null;

    }

    @Override
    public int hashCode() {
        int result = userid != null ? userid.hashCode() : 0;
        result = 31 * result + (mtlogintimes != null ? mtlogintimes.hashCode() : 0);
        result = 31 * result + (pclogintimes != null ? pclogintimes.hashCode() : 0);
        return result;
    }
}
