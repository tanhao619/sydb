package com.cdyoue.oddJobs.dto.common;

/**
 * 十位:  0失败 2 成功  4没有找到  6 没权限  8请求不合法
 * 个位:自定义标识
 */
public interface HttpMessage {
     Integer getCode();

     String getMsg();

     String getDescription();
}
