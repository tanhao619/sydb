package com.cdyoue.oddJobs.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by liaoyoule on 2017/6/9.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-08T12:12:19.725Z")
public class AccountBase {
    @JsonProperty("email")
    private String email;
    @JsonProperty("tel")

    private String tel;
    @JsonProperty("role")

    private Integer role;
    @JsonProperty("name")

    private String name;
    @JsonProperty("createTime")
    private Date createTime;

    @ApiModelProperty(value = "邮箱", example = "408648487@qq.com")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ApiModelProperty(value = "联系电话", example = "13980628745")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @ApiModelProperty(value = "用户角色 0 个人 1 企业 2 后台管理员")
    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @ApiModelProperty(value = "用户昵称", example = "王二蛋")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
