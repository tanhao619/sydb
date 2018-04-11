package com.cdyoue.oddJobs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.LinkedHashSet;

/**
 * Created by liaoyoule on 2017/5/4.
 */
public class AcceptPeopleSumary {
    private int id;
    private String name;
    private String headImage;
    private String tel;
    private String intro;
    private LinkedHashSet<Integer> applyTypes;
    private String categoryName;
    private String major;
    @JsonProperty("h5link")
    private String h5link;

    @ApiModelProperty(value = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ApiModelProperty(value = "姓名")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @ApiModelProperty(value = "头像")
    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }
    @ApiModelProperty(value = "联系电话")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    @ApiModelProperty(value = "简介")
    public String getIntro() {
        return intro;
    }

    @ApiModelProperty(value = "认证类型：1实名，2大咖，3导师'")
    public LinkedHashSet<Integer> getApplyTypes() {
        return applyTypes;
    }

    public void setApplyTypes(LinkedHashSet<Integer> applyTypes) {
        this.applyTypes = applyTypes;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
    @ApiModelProperty(value = "行业")
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    @ApiModelProperty(value = "职业")
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @ApiModelProperty(example = "/H5/findexpert.html?id=1234", name = "H5链接")
    public String getH5link() {
        return "/H5/findexpert.html?id=" + this.getId();
    }

    public void setH5link(String h5link) {
        this.h5link = h5link;
    }
}
