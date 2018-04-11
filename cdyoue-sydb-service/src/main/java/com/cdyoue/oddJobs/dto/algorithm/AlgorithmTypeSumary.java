package com.cdyoue.oddJobs.dto.algorithm;

import java.util.Date;

/**
 * Created by liaoyoule on 2017/6/20.
 */
public class AlgorithmTypeSumary {

    private AlgorithmTypeBase typeBase;

    private String intro;
    private Date createTime;
    private String creator;
    private Date updateTime;


    public AlgorithmTypeBase getTypeBase() {
        return typeBase;
    }

    public void setTypeBase(AlgorithmTypeBase typeBase) {
        this.typeBase = typeBase;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
