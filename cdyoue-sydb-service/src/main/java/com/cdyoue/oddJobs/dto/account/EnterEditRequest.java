package com.cdyoue.oddJobs.dto.account;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 个人中心-编辑我的企业信息表单
 * @author dengshaojun
 * @date 2017/10/24
 */
public class EnterEditRequest {

    private String name;
    private String tel;
    private String email;
    private String enterpriseDetail;

    @NotBlank(message = "名称不能为空")
    @Size(max = 50, message = "名称不能超过50字")
    @ApiModelProperty(example = "优易数据", value = "企业名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotBlank(message = "电话不能为空")
    @Pattern(regexp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$", message = "不是标准的手机号码")
    @ApiModelProperty(example = "13990012345！", value = "电话")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "不是标准的邮箱格式")
    @ApiModelProperty(example = "mmp@baidu.com", value = "邮箱")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotBlank(message = "详细不能为空")
    @ApiModelProperty(example = "企业详细信息！", value = "企业详悉信息")
    public String getEnterpriseDetail() {
        return enterpriseDetail;
    }

    public void setEnterpriseDetail(String enterpriseDetail) {
        this.enterpriseDetail = enterpriseDetail;
    }

}
