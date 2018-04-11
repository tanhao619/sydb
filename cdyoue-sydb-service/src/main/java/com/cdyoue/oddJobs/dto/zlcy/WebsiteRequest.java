package com.cdyoue.oddJobs.dto.zlcy;

import io.swagger.annotations.ApiModelProperty;

/**
 * 网址站表单
 */
public class WebsiteRequest {

    private String name;
    private String content;

    /*@NotBlank(message = "网址站类别不能为空")*/
    @ApiModelProperty(example = "电竞平台", value = "网址站类别")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*@NotBlank(message = "网址站内容不能为空")*/
    @ApiModelProperty(example = "<a href=\"www.baidu.com\">百度</a><a href=\"www.taobao.com\">淘宝</a>", value = "网址站内容")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
