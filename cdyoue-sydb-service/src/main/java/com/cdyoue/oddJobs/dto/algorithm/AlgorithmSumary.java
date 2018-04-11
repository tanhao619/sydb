package com.cdyoue.oddJobs.dto.algorithm;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by liaoyoule on 2017/6/14.
 */
public class AlgorithmSumary {
    private AlgorithmBase algorithmBase;
    private String dataUrl;
    private String operatorUrl;

    public AlgorithmBase getAlgorithmBase() {
        return algorithmBase;
    }

    public void setAlgorithmBase(AlgorithmBase algorithmBase) {
        this.algorithmBase = algorithmBase;
    }
    @ApiModelProperty(value = "样例数据包地址")
    public String getDataUrl() {
        return dataUrl;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }
    @ApiModelProperty(value = "算子包地址")
    public String getOperatorUrl() {
        return operatorUrl;
    }

    public void setOperatorUrl(String operatorUrl) {
        this.operatorUrl = operatorUrl;
    }
}
