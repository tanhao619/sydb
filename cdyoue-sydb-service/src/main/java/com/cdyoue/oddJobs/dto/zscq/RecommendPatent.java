package com.cdyoue.oddJobs.dto.zscq;

import java.util.List;

/**
 * Created by dengshaojun on 2017/5/24.
 */
public class RecommendPatent {

    private Integer result;
    private List<RecommendPatentData> data;

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public List<RecommendPatentData> getData() {
        return data;
    }

    public void setData(List<RecommendPatentData> data) {
        this.data = data;
    }

}
