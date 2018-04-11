package com.cdyoue.oddJobs.dto.publish;

import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;

/**
 * Created by liaoyoule on 2017/5/10.
 */
public class PublishSumary {
    private Integer id;
    private String coverImg;
    private String brief;
    private String intro;
    private String creator;
    private Timestamp createTime;
    private String updator;
    private Timestamp updateTime;

    @ApiModelProperty(value = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @ApiModelProperty(value = "封面")
    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }
    @ApiModelProperty(value = "简介")

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }
    @ApiModelProperty(value = "详细内容")

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
    @ApiModelProperty(value = "发布人")

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
    @ApiModelProperty(value = "发布时间")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
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
