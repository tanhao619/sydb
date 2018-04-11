package com.cdyoue.oddJobs.dto.lgfc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.ApiModelProperty;

/**
 * ReplyDetails
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-30T14:30:56.295Z")

public class ReplyDetails {

    @JsonProperty("id")
    private Integer id = null;

    @JsonProperty("replierName")
    private String replierName = null;

    @JsonProperty("replierId")
    private Integer replierId = null;

    @JsonProperty("replierTime")
    private String replierTime = null;

    @JsonProperty("replierHeadimg")
    private String replierHeadimg = null;

    @JsonProperty("certs")
    private List<Integer> certs = new ArrayList<Integer>();

    @JsonProperty("content")
    private String content = null;

    @JsonProperty("collectNum")
    private Integer collectNum = null;

    @JsonProperty("likeNum")
    private Integer likeNum = null;

    @JsonProperty("collect")
    private Boolean collect = null;

    @JsonProperty("like")
    private Boolean like = null;

    @JsonProperty("focus")
    private Boolean focus = null;

    @JsonProperty("focusReplyer")//当前用户是否关注回答问题的人
    private Boolean focusReplyer=null;

    public ReplyDetails id(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * 是否关注了回答问题的人
     * @return
     */
    public Boolean getFocusReplyer() {
        return focusReplyer;
    }

    public void setFocusReplyer(Boolean focusReplyer) {
        this.focusReplyer = focusReplyer;
    }

    /**
     * 回复id
     *
     * @return id
     */
    @ApiModelProperty(example = "1", value = "回复id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ReplyDetails replierName(String replierName) {
        this.replierName = replierName;
        return this;
    }

    /**
     * 回复者
     *
     * @return replierName
     **/
    @ApiModelProperty(example = "都灵", value = "回复者")
    public String getReplierName() {
        return replierName;
    }

    public void setReplierName(String replierName) {
        this.replierName = replierName;
    }

    public ReplyDetails replierId(Integer replierId) {
        this.replierId = replierId;
        return this;
    }

    /**
     * 回复者ID
     *
     * @return replierId
     **/
    @ApiModelProperty(example = "10", value = "回复者ID")
    public Integer getReplierId() {
        return replierId;
    }

    public void setReplierId(Integer replierId) {
        this.replierId = replierId;
    }


    public ReplyDetails replierTime(String replierTime) {
        this.replierTime = replierTime;
        return this;
    }

    /**
     * 回复时间
     *
     * @return replierTime
     **/
    @ApiModelProperty(example = "2017-05-05", value = "回复时间")
    public String getReplierTime() {
        return replierTime;
    }

    public void setReplierTime(String replierTime) {
        this.replierTime = replierTime;
    }


    public ReplyDetails replierHeadimg(String replierHeadimg) {
        this.replierHeadimg = replierHeadimg;
        return this;
    }

    /**
     * 回复者头像
     *
     * @return replierHeadimg
     **/
    @ApiModelProperty(example = "http://skks.com/sdfui.jsp", value = "回复者头像")
    public String getReplierHeadimg() {
        return replierHeadimg;
    }

    public void setReplierHeadimg(String replierHeadimg) {
        this.replierHeadimg = replierHeadimg;
    }


    public ReplyDetails certs(List<Integer> certs) {
        this.certs = certs;
        return this;
    }

    public ReplyDetails addCertsItem(Integer certsItem) {
        this.certs.add(certsItem);
        return this;
    }

    /**
     * Get certs认证类型
     *
     * @return certs
     **/
    @ApiModelProperty(example = "1实名，2大咖，3导师", value = "认证类型：1实名，2大咖，3导师")
    public List<Integer> getCerts() {
        return certs;
    }

    public void setCerts(List<Integer> certs) {
        this.certs = certs;
    }

    public ReplyDetails content(String content) {
        this.content = content;
        return this;
    }

    /**
     * 回复内容
     *
     * @return content
     **/
    @ApiModelProperty(example = "除了欧文，还有谁向科比发出过这样的挑战", value = "回复内容")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ReplyDetails collectNum(Integer collectNum) {
        this.collectNum = collectNum;
        return this;
    }

    /**
     * 收藏数
     *
     * @return collectNum
     **/
    @ApiModelProperty(example = "123", value = "收藏数")
    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }

    public ReplyDetails likeNum(Integer likeNum) {
        this.likeNum = likeNum;
        return this;
    }

    /**
     * 点赞数
     *
     * @return likeNum
     **/
    @ApiModelProperty(example = "100", value = "点赞数")
    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }


    public ReplyDetails collect(Boolean collect) {
        this.collect = collect;
        return this;
    }

    /**
     * 是否收藏
     *
     * @return collect
     */
    @ApiModelProperty(example = "false", value = "是否收藏")
    public Boolean getCollect() {
        return collect;
    }

    public void setCollect(Boolean collect) {
        this.collect = collect;
    }


    public ReplyDetails like(Boolean like) {
        this.like = like;
        return this;
    }

    /**
     * 是否点赞
     *
     * @return like
     */
    @ApiModelProperty(example = "false", value = "是否点赞")
    public Boolean getLike() {
        return like;
    }

    public void setLike(Boolean like) {
        this.like = like;
    }


    public ReplyDetails focus(Boolean focus) {
        this.focus = focus;
        return this;
    }

    /**
     * 是否关注所属的问题
     *
     * @return focus
     */
    @ApiModelProperty(example = "false", value = "是否关注所属的问题")
    public Boolean getFocus() {
        return focus;
    }

    public void setFocus(Boolean focus) {
        this.focus = focus;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReplyDetails replyDetails = (ReplyDetails) o;
        return Objects.equals(this.replierName, replyDetails.replierName) &&
                Objects.equals(this.id, replyDetails.id) &&
                Objects.equals(this.replierId, replyDetails.replierId) &&
                Objects.equals(this.replierTime, replyDetails.replierTime) &&
                Objects.equals(this.certs, replyDetails.certs) &&
                Objects.equals(this.content, replyDetails.content) &&
                Objects.equals(this.collectNum, replyDetails.collectNum) &&
                Objects.equals(this.likeNum, replyDetails.collectNum) &&
                Objects.equals(this.collect, replyDetails.collect) &&
                Objects.equals(this.focusReplyer, replyDetails.focusReplyer) &&
                Objects.equals(this.like, replyDetails.like);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, replierName, replierId, replierTime, certs, content, collectNum, likeNum, collect, like,focusReplyer);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ReplyDetails {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    replierName: ").append(toIndentedString(replierName)).append("\n");
        sb.append("    replierId: ").append(toIndentedString(replierId)).append("\n");
        sb.append("    replierTime: ").append(toIndentedString(replierTime)).append("\n");
        sb.append("    certs: ").append(toIndentedString(certs)).append("\n");
        sb.append("    content: ").append(toIndentedString(content)).append("\n");
        sb.append("    collectNum: ").append(toIndentedString(collectNum)).append("\n");
        sb.append("    likeNum: ").append(toIndentedString(likeNum)).append("\n");
        sb.append("    collect: ").append(toIndentedString(collect)).append("\n");
        sb.append("    like: ").append(toIndentedString(like)).append("\n");
        sb.append("    focusReplyer: ").append(toIndentedString(focusReplyer)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

