package com.cdyoue.oddJobs.dto.lgfc;

import java.util.List;

/**
 * Created by tr on 2017/5/11.
 */
public class FocusField {
    private Integer result;
    private List<FocusFieldData> data;

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public List<FocusFieldData> getData() {
        return data;
    }

    public void setData(List<FocusFieldData> data) {
        this.data = data;
    }
}
