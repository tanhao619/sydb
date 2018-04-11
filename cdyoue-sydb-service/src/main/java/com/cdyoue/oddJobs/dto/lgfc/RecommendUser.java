package com.cdyoue.oddJobs.dto.lgfc;

import java.util.List;

/**
 * Created by sky on 2017/5/9.
 */
public class RecommendUser {
    private Integer result;
    private List<RecommendUserData> data;

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public List<RecommendUserData> getData() {
        return data;
    }

    public void setData(List<RecommendUserData> data) {
        this.data = data;
    }
}
