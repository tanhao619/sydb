package com.cdyoue.oddJobs.dto.requirement;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * TalentBase
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-20T01:12:00.448Z")

public class RecommendTalentBase {
    @JsonProperty("id")
    private Integer id = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("certs")
    private List<Integer> certs = new ArrayList<Integer>();

    @JsonProperty("coverImgUrl")
    private String coverImgUrl = null;

    /*@JsonProperty("activeness")
    private BigDecimal activeness;*/

    @JsonProperty("category")
    private String categoryName;

    @JsonProperty("major")
    private String major;

    @JsonProperty("inviteStatus")
    private Integer inviteStatus;

    @JsonProperty("h5link")
    private String h5link;

    @JsonProperty("recommendeddegree")
    private BigDecimal recommendeddegree;

    public RecommendTalentBase id(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     *
     * @return id
     **/
    @ApiModelProperty(value = "承接人主键ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RecommendTalentBase name(String name) {
        this.name = name;
        return this;
    }

    @ApiModelProperty(value = "邀请状态: 1 未被邀请 2 已被邀请")
    public Integer getInviteStatus() {
        return inviteStatus;
    }

    public void setInviteStatus(Integer inviteStatus) {
        this.inviteStatus = inviteStatus;
    }

    /**
     * 姓名
     *
     * @return name
     **/
    @ApiModelProperty(example = "王小波", value = "承接人姓名")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RecommendTalentBase certs(List<Integer> certs) {
        this.certs = certs;
        return this;
    }

    public RecommendTalentBase addCertsItem(Integer certsItem) {
        this.certs.add(certsItem);
        return this;
    }

    /**
     * Get certs
     *
     * @return certs
     **/
    @ApiModelProperty(example = "1实名认证 2大咖认证 3导师认证", value = "认证类型")
    public List<Integer> getCerts() {
        return certs;
    }

    public void setCerts(List<Integer> certs) {
        this.certs = certs;
    }

    public RecommendTalentBase coverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
        return this;
    }

    /**
     * 封面
     *
     * @return coverImgUrl
     **/
    @ApiModelProperty(example = "http://172.16.0.102/image.jpg", value = "封面")
    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    @ApiModelProperty(value = "行业")
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    @ApiModelProperty(value = "专业")
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RecommendTalentBase talentBase = (RecommendTalentBase) o;
        return Objects.equals(this.id, talentBase.id) &&
                Objects.equals(this.name, talentBase.name) &&
                Objects.equals(this.certs, talentBase.certs) &&
                Objects.equals(this.coverImgUrl, talentBase.coverImgUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, certs, coverImgUrl);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TalentBase {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    certs: ").append(toIndentedString(certs)).append("\n");
        sb.append("    coverImgUrl: ").append(toIndentedString(coverImgUrl)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    /*public BigDecimal getActiveness() {
        return activeness;
    }

    public void setActiveness(BigDecimal activeness) {
        this.activeness = activeness;
    }*/

    @ApiModelProperty(example = "/H5/findexpert.html?id=1234", value = "H5链接")
    public String getH5link() {
        return "/H5/findexpert.html?id=" + this.getId();
    }

    public void setH5link(String h5link) {
        this.h5link = h5link;
    }

    public BigDecimal getRecommendeddegree() {
        return recommendeddegree;
    }

    @ApiModelProperty(example = "0.95（按需自行格式化百分制）", value = "推荐度")
    public void setRecommendeddegree(BigDecimal recommendeddegree) {
        this.recommendeddegree = recommendeddegree;
    }
}

