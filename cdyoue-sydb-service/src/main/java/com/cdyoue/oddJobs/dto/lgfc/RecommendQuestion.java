package com.cdyoue.oddJobs.dto.lgfc;

import java.util.List;

/**
 * Created by sky on 2017/5/5.
 */
public class RecommendQuestion {
    private Integer result;
    private List<QuestionData> data;

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public List<QuestionData> getData() {
        return data;
    }

    public void setData(List<QuestionData> data) {
        this.data = data;
    }
}
