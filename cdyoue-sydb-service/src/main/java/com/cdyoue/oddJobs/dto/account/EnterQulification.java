package com.cdyoue.oddJobs.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by tr on 2017/6/2.
 */
public class EnterQulification {

    @JsonProperty("enterName")
    private String enterName = null;

    @JsonProperty("codeNo")
    private String codeNo = null;

    @JsonProperty("certUrl")
    private String certUrl = null;

    @ApiModelProperty(example = "特斯拉汽车有限公司", value = "企业名称")
    public String getEnterName() {
        return enterName;
    }

    public void setEnterName(String enterName) {
        this.enterName = enterName;
    }

    @ApiModelProperty(example = "612199204246525", value = "营业执照注册号")
    public String getCodeNo() {
        return codeNo;
    }

    public void setCodeNo(String codeNo) {
        this.codeNo = codeNo;
    }

    @ApiModelProperty(example = "", value = "营业执照图片")
    public String getCertUrl() {
        return certUrl;
    }

    public void setCertUrl(String certUrl) {
        this.certUrl = certUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EnterQulification that = (EnterQulification) o;

        if (enterName != null ? !enterName.equals(that.enterName) : that.enterName != null) return false;
        if (codeNo != null ? !codeNo.equals(that.codeNo) : that.codeNo != null) return false;
        return certUrl != null ? certUrl.equals(that.certUrl) : that.certUrl == null;

    }

    @Override
    public int hashCode() {
        int result = enterName != null ? enterName.hashCode() : 0;
        result = 31 * result + (codeNo != null ? codeNo.hashCode() : 0);
        result = 31 * result + (certUrl != null ? certUrl.hashCode() : 0);
        return result;
    }
}
