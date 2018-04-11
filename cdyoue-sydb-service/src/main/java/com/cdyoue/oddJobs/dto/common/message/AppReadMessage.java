package com.cdyoue.oddJobs.dto.common.message;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tr on 2017/6/9.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AppReadMessage implements HttpMessage{
    SYSTEMERROR(0, "设置已读失败","系统错误"),
    MSGNOTFOUND(0, "未找到消息","找不到该id对应的消息"),
    MSGREADSUCCESS(1, "设置成功","设置已读状态成功"),
    MSGTYPENOTFOUND(0, "请求参数错误", "没有该消息type，请求参数错误"),
    APPUNREADMSGNOTFOUND(0, "没有未读消息", "没有未读消息");


    private Integer code;
    private String msg;
    private String description;
    private List<AppReadMessage.Error> errors = new ArrayList<>();

    AppReadMessage(Integer code, String msg, String description){
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

    public List<AppReadMessage.Error> getErrors() {
        return errors;
    }

    public AppReadMessage errors(List<ObjectError> errorList){
        this.errors = errorList.stream()
                .map(objectError -> new AppReadMessage.Error(objectError.getObjectName(), objectError.getDefaultMessage()))
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
