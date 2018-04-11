package com.cdyoue.oddJobs.dto.lgfc;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * 应聘兼职/全职的留言
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-30T14:30:56.295Z")
public class Word4Work {

    @JsonProperty("tel")
    private String tel;

    @JsonProperty("info")
    private String info;

    @ApiModelProperty(example = "13990025648", value = "联系电话")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @ApiModelProperty(example = "行行好吧，混口饭吃", value = "留言内容")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
