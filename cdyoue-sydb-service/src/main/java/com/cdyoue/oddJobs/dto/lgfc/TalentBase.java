package com.cdyoue.oddJobs.dto.lgfc;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * TalentBase
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-04T01:20:49.378Z")

public class TalentBase {
    @JsonProperty("id")
    private Integer id = null;
    /**
     * 名称
     */
    @JsonProperty("name")
    private String name = null;

    /**
     *
     */
    @JsonProperty("recommendeddegree")
    private String recommendeddegree = null;

    /**
     * 标签
     */
    @JsonProperty("certs")
    private List<Integer> certs = new ArrayList<Integer>();

    /**
     * 封面图片
     */
    @JsonProperty("coverImgUrl")
    private String coverImgUrl = null;

    /**
     * 简介
     */
    @JsonProperty("info")
    private String info;

    @JsonProperty("position")
    private String position = null;

    @JsonProperty("isFollow")
    private Boolean isFollow = false;

    @JsonProperty("invitedNum")
    private Integer invitedNum = null;

    @JsonProperty("dataComp")
    private Integer dataComp = null;

    @JsonProperty("industry")
    private String industry = null;

    @JsonProperty("isInvited")
    private Boolean isInvited = false;

    @JsonProperty("link")
    private String link = null;

    public TalentBase id(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     *
     * @return id
     **/
    @ApiModelProperty(value = "")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TalentBase name(String name) {
        this.name = name;
        return this;
    }

    /**
     * 姓名
     *
     * @return name
     **/
    @ApiModelProperty(example = "王小波", value = "姓名")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TalentBase recommendeddegree(String recommendeddegree) {
        this.recommendeddegree = recommendeddegree;
        return this;
    }

    @ApiModelProperty(value = "简介")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * 推荐度
     *
     * @return recommendeddegree
     **/
    @ApiModelProperty(example = "0.95", value = "推荐度")
    public String getRecommendeddegree() {
        return recommendeddegree;
    }

    public void setRecommendeddegree(String recommendeddegree) {
        this.recommendeddegree = recommendeddegree;
    }

    public TalentBase certs(List<Integer> certs) {
        this.certs = certs;
        return this;
    }

    public TalentBase addCertsItem(Integer certsItem) {
        this.certs.add(certsItem);
        return this;
    }

    /**
     * Get certs
     *
     * @return certs
     **/
    @ApiModelProperty(value = "")
    public List<Integer> getCerts() {
        return certs;
    }

    public void setCerts(List<Integer> certs) {
        this.certs = certs;
    }

    public TalentBase coverImgUrl(String coverImgUrl) {
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

    public TalentBase position(String position) {
        this.position = position;
        return this;
    }

    /**
     * 职位
     *
     * @return position
     **/
    @ApiModelProperty(example = "程序员", value = "职位")
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public TalentBase isFollow(Boolean isFollow) {
        this.isFollow = isFollow;
        return this;
    }

    /**
     * 是否关注
     *
     * @return isFollow
     **/
    @ApiModelProperty(example = "", value = "是否关注")
    public Boolean getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(Boolean isFollow) {
        this.isFollow = isFollow;
    }

    public TalentBase invitedNum(Integer invitedNum) {
        this.invitedNum = invitedNum;
        return this;
    }

    /**
     * Get invitedNum
     *
     * @return invitedNum
     **/
    @ApiModelProperty(value = "被邀请数")
    public Integer getInvitedNum() {
        return invitedNum;
    }

    public void setInvitedNum(Integer invitedNum) {
        this.invitedNum = invitedNum;
    }

    public TalentBase dataComp(Integer dataComp) {
        this.dataComp = dataComp;
        return this;
    }

    @ApiModelProperty(example = "98", value = "资料完整度")

    public Integer getDataComp() {
        return dataComp;
    }

    public void setDataComp(Integer dataComp) {
        this.dataComp = dataComp;
    }

    public TalentBase industry(String industry) {
        this.industry = industry;
        return this;
    }

    /**
     * 所在行业
     *
     * @return industry
     **/
    @ApiModelProperty(example = "IT", value = "所在行业")
    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public TalentBase isInvited(Boolean isInvited) {
        this.isInvited = isInvited;
        return this;
    }

    /**
     * 是否邀请
     *
     * @return isInvited
     **/
    @ApiModelProperty(example = "", value = "是否邀请")
    public Boolean getIsInvited() {
        return isInvited;
    }

    public void setIsInvited(Boolean isInvited) {
        this.isInvited = isInvited;
    }

    /**
     * 资料完整度
     * @return
     */
    @ApiModelProperty(example = "100", value = "资料完整度")


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TalentBase talentBase = (TalentBase) o;
        return Objects.equals(this.id, talentBase.id) &&
                Objects.equals(this.name, talentBase.name) &&
                Objects.equals(this.recommendeddegree, talentBase.recommendeddegree) &&
                Objects.equals(this.certs, talentBase.certs) &&
                Objects.equals(this.coverImgUrl, talentBase.coverImgUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, recommendeddegree, certs, coverImgUrl);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TalentBase {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    recommendeddegree: ").append(toIndentedString(recommendeddegree)).append("\n");
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

    @ApiModelProperty(example = "/H5/findexpert.html?id=1234", value = "H5链接")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

