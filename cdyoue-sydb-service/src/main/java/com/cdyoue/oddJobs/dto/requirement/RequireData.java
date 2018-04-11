package com.cdyoue.oddJobs.dto.requirement;

import java.math.BigDecimal;

/**
 * Created by liaoyoule on 2017/4/27.
 */
public class RequireData {
    private Integer outsourcingprojectid;
    private BigDecimal recommendeddegree;

    public Integer getOutsourcingprojectid() {
        return outsourcingprojectid;
    }

    public void setOutsourcingprojectid(Integer outsourcingprojectid) {
        this.outsourcingprojectid = outsourcingprojectid;
    }

    public BigDecimal getRecommendeddegree() {
        return recommendeddegree;
    }

    public void setRecommendeddegree(BigDecimal recommendeddegree) {
        this.recommendeddegree = recommendeddegree;
    }
}
