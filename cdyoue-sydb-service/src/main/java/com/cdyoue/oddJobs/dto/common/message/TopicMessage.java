package com.cdyoue.oddJobs.dto.common.message;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2017/5/11.
 */

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TopicMessage implements HttpMessage {
    RECOMMENDRESPONSENOTFOUND(40,"推荐系统返回数据为空",""),
    NO_TOPIC(40,"话题没找到","话题id不存在");

    private Integer code;
    private String msg;
    private String description;
    private List<TopicMessage.Error> errors = new ArrayList<>();

    TopicMessage(Integer code, String msg, String description){
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

    public List<TopicMessage.Error> getErrors() {
        return errors;
    }

    public TopicMessage errors(List<ObjectError> errorList){
        this.errors = errorList.stream()
                .map(objectError -> new TopicMessage.Error(objectError.getObjectName(), objectError.getDefaultMessage()))
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
