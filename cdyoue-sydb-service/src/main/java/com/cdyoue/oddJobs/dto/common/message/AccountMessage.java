package com.cdyoue.oddJobs.dto.common.message;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2017/5/11.
 */

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AccountMessage implements HttpMessage {
    CHECKSUCCESSFUL(20,"验证成功",""),
    CHECKFAIlBYADDRESS(11,"邮箱错误","验证邮箱地址有误"),
    CHECKFAIlBYYZM(12,"验证失败","验证码错误"),
    UPDATESUCCESSFUL(20,"修改成功","信息已更新"),
    UPDATEFAIL(11,"修改失败","系统内部错误"),
    NOTFOUND(10,"查询失败","未找到用户信息"),
    ACCOUNTNOTFOUND(10,"账户不存在","账户不存在"),
    FOUNDFORBIDDEN(60, "查询失败","没有权限，只有管理员才能修改" ),
    TIMEOUT(10,"验证失败","验证码失效"),

    TelTelCaptchERROR(30, "无效的验证码", "验证码错误"),
    TELTELCAPTCHTIMEOUT(31, "验证码超时", "请在5分钟内使用验证码"),




    UPDPWDSUCCESS(20, "修改密码成功", "修改密码成功，请使用新密码登录"),
    UPDPWDFAILD400(10, "修改密码失败", "输入的旧密码与原来不匹配，请重新输入"),
    UPDPWDFAILD4001(11, "修改密码失败", "输入新密码（8位及8位以上，数字字母特殊字符组合），请重新输入"),


    /**
     * 账号设置
     */

    ACCOUNT_DUPLICATE(10,"失败","邮箱或电话已被绑定"),
    ACCOUNT_ISBIND(10,"失败","请先解除绑定"),


    ACCOUNT_TYPE_INVALID(10,"解除绑定失败","无效的type"),
    ACCOUNT_INFO_IMPERFECT(10,"解除绑定失败","邮箱或电话未绑定"),
    ACCOUNT_PERSON_UPDATE_FAIL(10,"个人信息跟新失败",""),
    ACCOUNT_UNBIND_SUCCESS(20,"解除绑定成功","");


    private Integer code;
    private String msg;
    private String description;
    private List<AccountMessage.Error> errors = new ArrayList<>();

    AccountMessage(Integer code, String msg, String description){
        this.code = code;
        this.msg = msg;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getDescription() {
        return description;
    }

    public List<AccountMessage.Error> getErrors() {
        return errors;
    }

    public AccountMessage errors(List<ObjectError> errorList){
        this.errors = errorList.stream()
                .map(objectError -> new AccountMessage.Error(objectError.getObjectName(), objectError.getDefaultMessage()))
                .collect(Collectors.toList());
        return this;
    }


    public static class Error {
        private String filed;
        private String msg;

        public Error() {
        }

        public Error(String filed, String msg) {
            this.filed = filed;
            this.msg = msg;
        }

        public String getFiled() {
            return filed;
        }

        public void setFiled(String filed) {
            this.filed = filed;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
