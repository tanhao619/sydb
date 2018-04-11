package com.cdyoue.oddJobs.dto.common.message;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tr on 2017/6/8.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ActivityMessage implements HttpMessage {
    MYFOLACTNOTFOUND(40, "没有我关注的活动", "没有我关注的活动"),
    DELETEACTFAIL(40, "没有该id对应的活动", "没有该id对应的活动");

    private Integer code;
    private String msg;
    private String description;
    private List<ActivityMessage.Error> errors = new ArrayList<>();

    ActivityMessage(Integer code, String msg, String description){
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

    public List<ActivityMessage.Error> getErrors() {
        return errors;
    }


    public ActivityMessage errors(List<ObjectError> errorList){
        this.errors = errorList.stream()
                .map(objectError -> new Error(objectError.getObjectName(), objectError.getDefaultMessage()))
                .collect(Collectors.toList());
        return this;
    }



    public static class Error{
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
