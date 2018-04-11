package com.cdyoue.oddJobs.dto.common.message;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by liaoyoule on 2017/5/10.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum  ArticlesMessage implements HttpMessage{
    ARTICLESCOLLECT(20, "收藏成功", "收藏成功"),
    ARTICLESCOLLECTNOTFOUND(40, "操作失败", "该文章id不存在"),
    ARTICLESCOLLECTREP(10, "操作失败", "该文章已被收藏"),

    ARTICLEUNORHTHRE(60, "删除失败", "不能删除他人文章"),
    ARTICLEUNORHTH(61, "删除失败", "已发布文章不能删除"),
    ARTICLEUNORHTH2(62, "删除失败", "已发布文章不能删除"),

    ARTICLEUPDFAIL403(62, "更新失败", "不能修改别人的文章"),
    ARTICLEUPDFAIL400(62, "更新失败", "已审核通过的文章不能修改"),

    ARTICLETOPNOTPERMITTED(11,"设置失败","未审核通过不能设为精选"),
    ARTICLETOP(12,"设置失败","该文章已被设置为精选"),
    ARTICLEUNTOP(40,"操作失败","该文章不是精选的"),
    PERSONACCOUNTUPDATE(20, "更新成功", "更新成功");

    private Integer code;
    private String msg;
    private String description;
    private List<AuthenticationMessage.Error> errors = new ArrayList<>();

    ArticlesMessage(Integer code, String msg, String description){
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

    public List<AuthenticationMessage.Error> getErrors() {
        return errors;
    }

    public ArticlesMessage errors(List<ObjectError> errorList){
        this.errors = errorList.stream()
                .map(objectError -> new AuthenticationMessage.Error(objectError.getObjectName(), objectError.getDefaultMessage()))
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
