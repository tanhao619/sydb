package com.cdyoue.oddJobs.dto.algorithm;

import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;

/**
 * Created by liaoyoule on 2017/6/14.
 */
public class AlgorithmBase {
    private Integer id;
    private String name;
    private String intro;
    private String dataUrl;
    private String operatorUrl;
    private Timestamp createTime;
    private String creator;
    private AlgorithmTypeBase type;

    @ApiModelProperty(value = "主键id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @ApiModelProperty(value = "名称")

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @ApiModelProperty(value = "介绍")

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
    @ApiModelProperty(value = "创建时间")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }
    @ApiModelProperty(value = "创建人")
    public void setCreator(String creator) {
        this.creator = creator;
    }
    @ApiModelProperty(value = "类型")
    public AlgorithmTypeBase getType() {
        return type;
    }

    public void setType(AlgorithmTypeBase type) {
        this.type = type;
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
