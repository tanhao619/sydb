package com.cdyoue.oddJobs.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * Created by Administrator on 2017/5/8.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-08T12:12:19.725Z")
public class EnterAccountForRecruitment {
    @JsonProperty("userId")
    private Integer userId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("headImg")
    private String headImg;

    @JsonProperty("info")
    private String info;

    /**
     * 用户ID
     * @return id
     **/
    @ApiModelProperty(example = "1", value = "用户ID")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    /**
     * 企业名称
     * @return name
     **/
    @ApiModelProperty(example = "优易数据", value = "企业名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 企业头像
     * @return headImg
     **/
    @ApiModelProperty(example = "/xxx/xxx/xxx.jpg", value = "企业头像")
    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    /**
     * 企业简介
     * @return info
     **/
    @ApiModelProperty(example = "企业简介！", value = "企业简介")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
