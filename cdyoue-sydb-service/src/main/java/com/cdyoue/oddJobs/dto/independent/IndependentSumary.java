package com.cdyoue.oddJobs.dto.independent;

import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;

/**
 * Created by liaoyoule on 2017/5/10.
 */
public class IndependentSumary {
    private IndependentMine independentMine;
    private String updator;
    private Timestamp updateTime;


    public IndependentMine getIndependentMine() {
        return independentMine;
    }

    public void setIndependentMine(IndependentMine independentMine) {
        this.independentMine = independentMine;
    }

    @ApiModelProperty(value = "修改人")
    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }
    @ApiModelProperty(value = "修改时间")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
