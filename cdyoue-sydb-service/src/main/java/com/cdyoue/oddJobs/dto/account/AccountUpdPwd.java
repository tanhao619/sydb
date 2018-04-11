package com.cdyoue.oddJobs.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * AccountUpdPwd
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-08T17:12:19.725Z")
public class AccountUpdPwd {

    @JsonProperty("oldPwd")
    private String oldPwd = null;

    @JsonProperty("newPwd")
    private String newPwd = null;

    public AccountUpdPwd oldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
        return this;
    }
    /**
     * 旧密码
     * @return oldPwd
     **/
    @ApiModelProperty(example = "******", value = "旧密码")
    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }


    public AccountUpdPwd newPwd(String newPwd) {
        this.newPwd = newPwd;
        return this;
    }
    /**
     * 新密码（8位及8位以上，数字字母特殊字符组合）
     * @return newPwd
     **/
    //@Pattern(regexp = "/^(?=.*[a-z])(?=.*\d)(?=.*[#@!~%^&*])[a-z\d#@!~%^&*]{8,}$/", message = "8位及8位以上，数字字母特殊字符组合")
    @ApiModelProperty(example = "******", value = "新密码（8位及8位以上，数字字母特殊字符组合）")
    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AccountUpdPwd accountUpdPwd = (AccountUpdPwd) o;
        return Objects.equals(this.oldPwd, accountUpdPwd.oldPwd) &&
                Objects.equals(this.newPwd, accountUpdPwd.newPwd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oldPwd, newPwd);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AccountUpdPwd {\n");

        sb.append("    oldPwd: ").append(toIndentedString(oldPwd)).append("\n");
        sb.append("    newPwd: ").append(toIndentedString(newPwd)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
