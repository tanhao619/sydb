package com.cdyoue.oddJobs.dto.lgfc;

import java.math.BigDecimal;

/**
 * Created by tr on 2017/5/27.
 */
public class UserActivenessData {
    private Integer id;
    private Integer userid;
    private BigDecimal activeness;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public BigDecimal getActiveness() {
        return activeness;
    }

    public void setActiveness(BigDecimal activeness) {
        this.activeness = activeness;
    }
}
