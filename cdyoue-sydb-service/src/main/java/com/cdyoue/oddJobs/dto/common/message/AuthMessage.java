package com.cdyoue.oddJobs.dto.common.message;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AuthMessage implements HttpMessage {


    /**
     * auth
     */

    APPLTINFOFAILD400(80, "请求参数错误", "认证类型有误，无法请求，请联系客服"),

    CREATEAPPLTINFOSUCCESS(20, "提交认证成功", "提交认证信息成功，请等待审核"),
    CREATEAPPLTINFOFAILD403(60, "提交认证失败", "用户类型有误，没有权限提交认证，请联系客服"),
    CREATEAPPLTINFOFAILD400(80, "提交认证失败", "用户已经提交此类认证，请等待审核，不要重复提交"),

    REVIEWAPPLTINFOSUCCESS(20, "完成审核用户认证", "完成审核用户认证"),
    REVIEWATEAPPLTINFOFAILD403(60, "未完成审核用户认证", "用户类型有误，不能审核认证，请联系客服"),
    APPROVEAPPLTINFOFAILD403(60, "未完成审核用户认证", "审核者不是超级管理员，不能审核认证，请联系客服"),

    EDITAPPLYINFOSUCCESS(20, "编辑认证成功", "编辑认证成功");


    private Integer code;
    private String msg;
    private String description;
    private List<Error> errors = new ArrayList<>();

    AuthMessage(Integer code, String msg, String description) {
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

    public List<Error> getErrors() {
        return errors;
    }

    public AuthMessage errors(List<ObjectError> errorList) {
        this.errors = errorList.stream()
                .map(objectError -> new Error(objectError.getObjectName(), objectError.getDefaultMessage()))
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
