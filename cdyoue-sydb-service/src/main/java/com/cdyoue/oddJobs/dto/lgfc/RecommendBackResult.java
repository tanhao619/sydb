package com.cdyoue.oddJobs.dto.lgfc;

import java.util.List;

/**
 * Created by sky on 2017/5/9.
 */
public class RecommendBackResult {
    private Integer result;
    private List<RecommendData> data;

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public List<RecommendData> getData() {
        return data;
    }

    public void setData(List<RecommendData> data) {
        this.data = data;
    }
}
