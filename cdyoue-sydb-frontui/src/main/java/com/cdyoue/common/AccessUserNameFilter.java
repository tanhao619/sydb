package com.cdyoue.common;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liaoyoule on 2017/4/24.
 */
@Component
public class AccessUserNameFilter extends ZuulFilter {
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        ctx.addZuulRequestHeader("Authorization",request.getHeader("Authorization"));
        ctx.addZuulRequestHeader("IP", request.getRemoteAddr());
        ctx.setSendZuulResponse(true);// 对该请求进行路由
        ctx.setResponseStatusCode(200);
        ctx.set("isSuccess", true);// 设值，让下一个Filter看到上一个Filter的状态
        return null;
    }

    @Override
    public boolean shouldFilter() {
        return true;// 是否执行该过滤器，此处为true，说明需要过滤
    }

    @Override
    public int filterOrder() {
        return 0;// 优先级为0，数字越大，优先级越低
    }

    @Override
    public String filterType() {
        return "pre";// 前置过滤器
    }
}
