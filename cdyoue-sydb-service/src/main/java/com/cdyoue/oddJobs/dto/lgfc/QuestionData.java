package com.cdyoue.oddJobs.dto.lgfc;

import java.math.BigDecimal;

/**
 * Created by sky on 2017/5/5.
 */
public class QuestionData {
    private Integer Issueid;
    private BigDecimal recommendeddegree;

    public Integer getIssueid() {
        return Issueid;
    }

    public void setIssueid(Integer issueid) {
        Issueid = issueid;
    }

    public BigDecimal getRecommendeddegree() {
        return recommendeddegree;
    }

    public void setRecommendeddegree(BigDecimal recommendeddegree) {
        this.recommendeddegree = recommendeddegree;
    }
}
