package com.cdyoue.oddJobs.dto.lgfc;

/**
 * Created by tr on 2017/6/1.
 */
public class LoginInfoData {
    private String firstLogin;
    private String lastLogin;
    private Integer totalLogin;

    public String getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(String firstLogin) {
        this.firstLogin = firstLogin;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Integer getTotalLogin() {
        return totalLogin;
    }

    public void setTotalLogin(Integer totalLogin) {
        this.totalLogin = totalLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoginInfoData that = (LoginInfoData) o;

        if (firstLogin != null ? !firstLogin.equals(that.firstLogin) : that.firstLogin != null) return false;
        if (lastLogin != null ? !lastLogin.equals(that.lastLogin) : that.lastLogin != null) return false;
        return totalLogin != null ? totalLogin.equals(that.totalLogin) : that.totalLogin == null;

    }

    @Override
    public int hashCode() {
        int result = firstLogin != null ? firstLogin.hashCode() : 0;
        result = 31 * result + (lastLogin != null ? lastLogin.hashCode() : 0);
        result = 31 * result + (totalLogin != null ? totalLogin.hashCode() : 0);
        return result;
    }
}
