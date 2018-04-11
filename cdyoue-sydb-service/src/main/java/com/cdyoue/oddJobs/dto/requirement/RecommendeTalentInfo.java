package com.cdyoue.oddJobs.dto.requirement;

import java.math.BigDecimal;

/**
 * Created by liaoyoule on 2017/4/27.
 */
public class RecommendeTalentInfo {
    private Integer userid;
    private BigDecimal activeness;
    private BigDecimal recommendeddegree;

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

    public BigDecimal getRecommendeddegree() {
        return recommendeddegree;
    }

    public void setRecommendeddegree(BigDecimal recommendeddegree) {
        this.recommendeddegree = recommendeddegree;
    }
}
