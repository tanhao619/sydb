package com.cdyoue.oddJobs.dto.common.message;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by sky on 2017/6/29.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum BigProjectMessage implements HttpMessage{


    NO_BigProject(40,"没有找到大项目","没有找到大项目");

    private Integer code;
    private String msg;
    private String description;
    private List<BigProjectMessage.Error> errors = new ArrayList<>();

    BigProjectMessage(Integer code, String msg, String description){
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

    public List<BigProjectMessage.Error> getErrors() {
        return errors;
    }

    public BigProjectMessage errors(List<ObjectError> errorList){
        this.errors = errorList.stream()
                .map(objectError -> new BigProjectMessage.Error(objectError.getObjectName(), objectError.getDefaultMessage()))
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
