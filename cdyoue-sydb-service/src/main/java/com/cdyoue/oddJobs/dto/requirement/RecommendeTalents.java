package com.cdyoue.oddJobs.dto.requirement;

import java.util.List;

/**
 * Created by liaoyoule on 2017/4/27.
 */
public class RecommendeTalents {
    private Integer result;
    private List<RecommendeTalentInfo> data;

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public List<RecommendeTalentInfo> getData() {
        return data;
    }

    public void setData(List<RecommendeTalentInfo> data) {
        this.data = data;
    }
}
