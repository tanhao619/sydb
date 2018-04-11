package com.cdyoue.oddJobs.error;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liaoyoule on 2017/5/15.
 */
@Component
public class ResponseHolder {
    public String getBehavior(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest hsr = attributes.getRequest();
        switch (hsr.getMethod().toLowerCase()){
            case "get":
                return "获取数据失败";
            case "post":
                return "创建数据失败";
            case "put":
                return "修改数据失败";
            case "delete":
                return "删除失败";

            default:
                return "未添加的操作类型[error]";
        }
    }

}
