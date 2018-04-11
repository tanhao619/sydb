package com.cdyoue.oddJobs.dto.algorithm;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by liaoyoule on 2017/6/14.
 */
public class AlgorithmRequest {
    private String name;
    private String intro;
    private Integer type;
    private String dataUrl;
    private String operatorUrl;

    @ApiModelProperty(value = "名称")
    @NotNull
    @Size(min = 1,message = "名称不能为空")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @ApiModelProperty(value = "简介")
    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
    @ApiModelProperty(value = "算子分类:1过滤器算法 2 计算器算法 3 格式转换算法 4相似度算法 5文件操作算法")
    @NotNull
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    @ApiModelProperty(value = "样例数据包地址")
    @NotNull
    @Size(min = 1,message = "样例数据包地址 不能为空")
    public String getDataUrl() {
        return dataUrl;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }
    @ApiModelProperty(value = "算子包地址")
    @NotNull
    @Size(min = 1,message = "算子包地址 不能为空")
    public String getOperatorUrl() {
        return operatorUrl;
    }

    public void setOperatorUrl(String operatorUrl) {
        this.operatorUrl = operatorUrl;
    }
}
