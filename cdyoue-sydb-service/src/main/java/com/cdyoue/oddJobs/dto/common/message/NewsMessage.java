package com.cdyoue.oddJobs.dto.common.message;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum NewsMessage implements HttpMessage {

    /**
     * news
     */

    CREATENEWSSUCCESS(20, "创建新闻成功", "恭喜您，新闻发布成功，并提交平台审核,我们将在3个工作日内完成审核，请您及时关注审核动态"),
    CREATENEWSFAILD403(40, "创建新闻失败", "该用户没有权限，只有企业才能发布新闻"),

    GETNEWSFAILD404(40, "获取新闻详情失败", "要获取的新闻不存在"),

    GETMYPUBLISHNEWSFAILD404(40, "获取我发布的新闻详情失败", "超出页码范围或者该用户从未发布过新闻"),

    UPDNEWSFAILD404(40, "编辑新闻失败,新闻不存在", "要编辑的新闻不存在"),
    UPDNEWSFAILD403(40, "编辑新闻失败,没有权限", "不是该新闻的创建者，没有权限编辑新闻，请刷新你的列表或联系客服"),
    UPDNEWSSUCCESS(20, "编辑新闻成功", "编辑新闻成功"),

    DELNEWSFAILD404(40, "删除新闻失败,新闻不存在", "要删除的新闻不存在"),
    DELNEWSFAILD403(40, "删除新闻失败,没有权限", "不是该新闻的创建者，没有权限删除新闻，请刷新你的列表或联系客服"),
    DELNEWSSUCCESS(20, "删除新闻成功", "删除新闻成功"),

    REVIEWNEWSFAILD404(80, "审核新闻失败", "要审核的新闻不存在，请刷新列表或联系客服"),
    REVIEWNEWSFAILD400(80, "审核新闻失败", "该新闻已经被审核，请刷新列表或联系客服"),
    REVIEWNEWSSUCCESS(20, "审核新闻成功", "审核新闻成功");



    private Integer code;
    private String msg;
    private String description;
    private List<Error> errors = new ArrayList<>();

    NewsMessage(Integer code, String msg, String description){
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

    public NewsMessage errors(List<ObjectError> errorList){
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
