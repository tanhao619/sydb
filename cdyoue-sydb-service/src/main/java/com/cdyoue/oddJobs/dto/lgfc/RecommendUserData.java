package com.cdyoue.oddJobs.dto.lgfc;

import java.math.BigDecimal;

/**
 * Created by sky on 2017/5/9.
 */
public class RecommendUserData {
    private Integer userid;
    private BigDecimal recommendeddegree;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public BigDecimal getRecommendeddegree() {
        return recommendeddegree;
    }

    public void setRecommendeddegree(BigDecimal recommendeddegree) {
        this.recommendeddegree = recommendeddegree;
    }
}
