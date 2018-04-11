package com.cdyoue.oddJobs.dto.common.message;

/**
 * Created by dengshaojun on 2017/5/10.
 */

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ReplyMessage implements HttpMessage {
    /**
     * reply
     */

    REPLYCREATESUCCESS(20,"创建回复成功","创建回复成功"),
    REPLYCREATEFAILD404(40, "创建回复失败", "要回复的问题不存在"),

    REPLYCOLLECTSUCCESS(20, "收藏回复成功", "收藏回复成功"),
    REPLYCOLLECTFAILD400(80, "收藏回复失败", "你已经收藏过这个回复"),
    REPLYCOLLECTFAILD404(40, "收藏回复失败", "你收藏的回复不存在"),
    REPLYCOLLECTFAILD401(60, "收藏回复失败", "当前未登录不能收藏"),

    REPLYUNCOLLECTSUCCESS(20, "取消收藏回复成功", "取消收藏回复成功"),
    REPLYUNCOLLECTFAILD400(80, "取消收藏回复失败", "你已经取消收藏过这个回复"),
    REPLYUNCOLLECTFAILD404(40, "取消收藏回复失败", "你取消收藏的回复不存在"),
    REPLYUNCOLLECTFAILD500(00, "取消收藏回复失败", "错误的操作"),

    REPLYLIKESUCCESS(20, "点赞回复成功", "点赞回复成功"),
    REPLYLIKEFAILD400(80, "点赞回复失败", "你已经点赞过这个回复"),
    REPLYLIKEFAILD404(40, "点赞回复失败", "你点赞的回复不存在"),
    REPLYLIKEFAILD401(40, "点赞回复失败", "当前未登录不能点赞"),

    REPLYUNLIKESUCCESS(20, "取消点赞回复成功", "取消点赞回复成功"),
    REPLYUNLIKEFAILD400(80, "取消点赞回复失败", "你已经取消点赞过这个回复"),
    REPLYUNLIKEFAILD404(40, "取消点赞回复失败", "你取消点赞的回复不存在"),
    REPLYUNLIKEFAILD500(10, "取消点赞回复失败", "错误的操作"),


    REPLYUSERNOTFOUND(40, "没有内容", "该用户没有回复任何问题"),
    REPLYCOLLECTNOTFOUND(40, "没有内容", "该用户没有收藏任何回复"),

    REPLYQUESTION400(80, "没有内容", "该问题不存在"),
    REPLYQUESTIONNOTFOUND(40, "没有内容", "该问题没有任何回复"),

    REPLYDELETEFAILD404(40, "删除回复失败", "该回复不存在"),
    REPLYDELETESUCCESS(20, "删除回复成功", "删除回复成功");



    private Integer code;
    private String msg;
    private String description;
    private List<Error> errors = new ArrayList<>();

    ReplyMessage(Integer code, String msg, String description) {
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

    public ReplyMessage errors(List<ObjectError> errorList){
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
