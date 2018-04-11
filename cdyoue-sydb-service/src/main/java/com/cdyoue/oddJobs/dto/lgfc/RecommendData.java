package com.cdyoue.oddJobs.dto.lgfc;

import java.math.BigDecimal;

/**
 * Created by sky on 2017/5/9.
 */
public class RecommendData {
    private Integer jobid;
    private BigDecimal recommendeddegree;

    public Integer getJobid() {
        return jobid;
    }

    public void setJobid(Integer jobid) {
        this.jobid = jobid;
    }

    public BigDecimal getRecommendeddegree() {
        return recommendeddegree;
    }

    public void setRecommendeddegree(BigDecimal recommendeddegree) {
        this.recommendeddegree = recommendeddegree;
    }
}
