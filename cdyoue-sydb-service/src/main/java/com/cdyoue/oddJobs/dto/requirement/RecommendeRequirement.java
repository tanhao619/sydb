package com.cdyoue.oddJobs.dto.requirement;

import com.cdyoue.oddJobs.dto.requirement.RequireData;

import java.util.List;

/**
 * Created by liaoyoule on 2017/4/27.
 */
public class RecommendeRequirement {
    private Integer result;
    private List<RequireData> data;

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public List<RequireData> getData() {
        return data;
    }

    public void setData(List<RequireData> data) {
        this.data = data;
    }
}
