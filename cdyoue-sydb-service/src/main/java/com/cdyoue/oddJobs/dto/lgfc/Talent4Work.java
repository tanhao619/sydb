package com.cdyoue.oddJobs.dto.lgfc;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.print.attribute.standard.PrinterURI;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

/**
 * TalentBase，获取职位详细信息的报名人列表
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-04T01:20:49.378Z")
public class Talent4Work {

    @JsonProperty("id")
    private Integer id = null;

    @JsonProperty("headimgurl")
    private String headimgurl = null;

    @JsonProperty("name")
    private String name = null;
    @JsonProperty("major")
    private String major;

    @JsonProperty("certs")
    private List<Integer> certs = new ArrayList<>();

    @JsonProperty("job")
    private String job = null;

    @JsonProperty("functionCategoryName")
    private String functionCategoryName = null;

    @JsonProperty("signature")
    private String signature = null;

    @JsonProperty("tel")
    private String tel = null;

    public Talent4Work id(Integer id) {
        this.id = id;
        return this;
    }
    @ApiModelProperty(example = "42", value = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Talent4Work headimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
        return this;
    }
    @ApiModelProperty(example = "http://fcsuyh.fvauy.com/df.jpg", value = "头像url")
    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public Talent4Work name(String name) {
        this.name = name;
        return this;
    }
    @ApiModelProperty(example = "王小波", value = "姓名")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Talent4Work certs(List<Integer> certs) {
        this.certs = certs;
        return this;
    }
    public Talent4Work addCertsItem(Integer certsItem) {
        this.certs.add(certsItem);
        return this;
    }
    @ApiModelProperty(example = "1实名认证，2大咖认证，3导师认证", value = "认证信息")
    public List<Integer> getCerts() {
        return certs;
    }

    public void setCerts(List<Integer> certs) {
        this.certs = certs;
    }

    public Talent4Work functionCategoryName(String functionCategoryName) {
        this.functionCategoryName = functionCategoryName;
        return this;
    }
    @ApiModelProperty(example = "电子竞技", value = "行业")
    public String getFunctionCategoryName() {
        return functionCategoryName;
    }

    public void setFunctionCategoryName(String functionCategoryName) {
        this.functionCategoryName = functionCategoryName;
    }

    public Talent4Work job(String job) {
        this.job = job;
        return this;
    }
    @ApiModelProperty(example = "攻城狮", value = "职位")
    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Talent4Work signature(String signature) {
        this.signature = signature;
        return this;
    }
    @ApiModelProperty(example = "有一句不知当讲不当讲", value = "一句话留言")
    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Talent4Work tel(String tel) {
        this.tel = tel;
        return this;
    }
    @ApiModelProperty(example = "400-820-8890", value = "联系方式")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    @ApiModelProperty(example = "java工程师", value = "最近职位")
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
