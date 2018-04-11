package com.cdyoue.oddJobs.dto.lgfc;

/**
 * Created by admin on 2017/5/11.
 */
public class Portrait {
    private Integer result;
    private UserOperation data;

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public UserOperation getData() {
        return data;
    }

    public void setData(UserOperation data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Portrait portrait = (Portrait) o;

        if (result != null ? !result.equals(portrait.result) : portrait.result != null) return false;
        return data != null ? data.equals(portrait.data) : portrait.data == null;

    }

    @Override
    public int hashCode() {
        int result1 = result != null ? result.hashCode() : 0;
        result1 = 31 * result1 + (data != null ? data.hashCode() : 0);
        return result1;
    }
}
