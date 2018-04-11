package com.cdyoue.oddJobs.dto.zscq;

import java.math.BigDecimal;

/**
 * Created by dengshaojun on 2017/5/24.
 */
public class RecommendPatentData {

    private Integer patentid;
    private BigDecimal recommendeddegree;

    public Integer getPatentid() {
        return patentid;
    }

    public void setPatentid(Integer patentid) {
        this.patentid = patentid;
    }

    public BigDecimal getRecommendeddegree() {
        return recommendeddegree;
    }

    public void setRecommendeddegree(BigDecimal recommendeddegree) {
        this.recommendeddegree = recommendeddegree;
    }
}
