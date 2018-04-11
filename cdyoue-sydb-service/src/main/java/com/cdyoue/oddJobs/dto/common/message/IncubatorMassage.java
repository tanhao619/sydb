package com.cdyoue.oddJobs.dto.common.message;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2017/5/12.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum IncubatorMassage implements HttpMessage {
    NOTFOUND(10,"查询失败","未找到孵化器信息"),
    UPDATEFORBIDDEN(60, "修改失败","没有权限，只有管理员才能修改" ),
    UPDATEFAIL(10,"修改失败","系统内部错误或参数错误"),
    UPDATESUCCESSFUL(20,"修改成功","信息已更新");

    private Integer code;
    private String msg;
    private String description;
    private List<IncubatorMassage.Error> errors = new ArrayList<>();

    IncubatorMassage(Integer code, String msg, String description){
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

    public List<IncubatorMassage.Error> getErrors() {
        return errors;
    }

    public IncubatorMassage errors(List<ObjectError> errorList){
        this.errors = errorList.stream()
                .map(objectError -> new IncubatorMassage.Error(objectError.getObjectName(), objectError.getDefaultMessage()))
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
