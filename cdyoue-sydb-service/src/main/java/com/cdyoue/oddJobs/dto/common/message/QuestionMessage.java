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
public enum QuestionMessage implements HttpMessage {
    RECOMMENDRESPONSENOTFOUND(40,"操作失败","推荐系统返回数据为空"),
    NO_QUESTION(40,"操作失败","问题没找到"),
    NO_User(40,"操作失败","用户没找到"),
    REINVITATION(80,"邀请失败","不能重复邀请");




    private Integer code;
    private String msg;
    private String description;
    private List<QuestionMessage.Error> errors = new ArrayList<>();

    QuestionMessage(Integer code, String msg, String description){
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

    public List<QuestionMessage.Error> getErrors() {
        return errors;
    }

    public QuestionMessage errors(List<ObjectError> errorList){
        this.errors = errorList.stream()
                .map(objectError -> new QuestionMessage.Error(objectError.getObjectName(), objectError.getDefaultMessage()))
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
