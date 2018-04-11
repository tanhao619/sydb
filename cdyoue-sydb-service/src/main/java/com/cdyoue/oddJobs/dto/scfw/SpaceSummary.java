package com.cdyoue.oddJobs.dto.scfw;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.*;

/**
 * SpaceSummary
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-18T06:24:34.814Z")

public class SpaceSummary {
    @JsonProperty("id")
    private Integer id = null;

    @JsonProperty("title")
    private String title = null;

    @JsonProperty("coverImgUrl")
    private String coverImgUrl = null;

    @JsonProperty("type")
    private Integer type = null;

    @JsonProperty("rent")
    private String rent = null;

    @JsonProperty("number")
    private Integer number = null;

//  @JsonProperty("publishTime")
//  private String publishTime = null;

    @JsonProperty("createTime")
    private String createTime = null;

    @JsonProperty("viewCount")
    private Integer viewCount = null;

    @JsonProperty("reviewStatus")
    private Integer reviewStatus = null;

    @JsonProperty("publishPepole")
    private String publishPepole = null;

    @JsonProperty("address")
    private String address = null;

    @JsonProperty("introduction")
    private String introduction = null;

    @JsonProperty("link")
    private String link = null;

    @JsonProperty("top")
    private Integer top;

    @JsonProperty("topImg")
    private String topImg;

//  @JsonProperty("isTop")
//  private Integer isTop = null;

    public SpaceSummary id(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * 创建时间（admin）
     *
     * @return
     */
    @ApiModelProperty(example = "2017/03/03 12:00:00", value = "创建时间（后台管理员查看发布的空间有用）")
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * id
     *
     * @return id
     **/
    @ApiModelProperty(value = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SpaceSummary title(String title) {
        this.title = title;
        return this;
    }

    /**
     * 详细地址
     *
     * @return
     */
    @ApiModelProperty(example = "", value = "地址")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 介绍
     *
     * @return
     */
    @ApiModelProperty(example = "这是介绍", value = "介绍")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    /**
     * 名称
     *
     * @return title
     **/
    @ApiModelProperty(example = "卡拉卡", value = "名称")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SpaceSummary coverImgUrl(String coverImgUrl) {
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

    public SpaceSummary type(Integer type) {
        this.type = type;
        return this;
    }

    /**
     * 出租方式,1 场地出租 2 工位出租
     *
     * @return type
     **/
    @ApiModelProperty(example = "1", value = "出租方式,1 场地出租 2 工位出租")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public SpaceSummary rent(String rent) {
        this.rent = rent;
        return this;
    }

    /**
     * 租金
     *
     * @return rent
     **/
    @ApiModelProperty(example = "123", value = "租金")
    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public SpaceSummary number(Integer number) {
        this.number = number;
        return this;
    }

    /**
     * 数量
     *
     * @return number
     **/
    @ApiModelProperty(example = "123", value = "数量")
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

//  public SpaceSummary publishTime(String publishTime) {
//    this.publishTime = publishTime;
//    return this;
//  }

    /**
     * 发布时间（admin）
     *
     * @return publishTime
     **/
//  @ApiModelProperty(example = "2017/03/03 12:00:00", value = "发布时间（admin）")
//  public String getCreateTime() {
//    return publishTime;
//  }
//
//  public void setCreateTime(String publishTime) {
//    this.publishTime = publishTime;
//  }
    public SpaceSummary viewsCount(Integer viewCount) {
        this.viewCount = viewCount;
        return this;
    }

    /**
     * 阅读量（admin）
     *
     * @return viewsCount
     **/
    @ApiModelProperty(example = "10000", value = "阅读量（admin）")
    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public SpaceSummary publishPepole(String publishPepole) {
        this.publishPepole = publishPepole;
        return this;
    }

    /**
     * 发布人（admin）
     *
     * @return publishPepole
     **/
    @ApiModelProperty(example = "admin", value = "发布人（admin）")
    public String getPublishPepole() {
        return publishPepole;
    }

    public void setPublishPepole(String publishPepole) {
        this.publishPepole = publishPepole;
    }

    /**
     * 审核状态
     *
     * @return
     */
    @ApiModelProperty(example = "1", value = "审核状态，0未审核，1已审核")
    public Integer getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(Integer reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    /**
     * 是否置顶（admin） 1 是， 0 否
     *
     * @return top
     **/
    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public String getTopImg() {
        return topImg;
    }

    public void setTopImg(String topImg) {
        this.topImg = topImg;
    }



    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SpaceSummary spaceSummary = (SpaceSummary) o;
        return Objects.equals(this.id, spaceSummary.id) &&
                Objects.equals(this.title, spaceSummary.title) &&
                Objects.equals(this.coverImgUrl, spaceSummary.coverImgUrl) &&
                Objects.equals(this.type, spaceSummary.type) &&
                Objects.equals(this.rent, spaceSummary.rent) &&
                Objects.equals(this.number, spaceSummary.number) &&
//        Objects.equals(this.publishTime, spaceSummary.publishTime) &&
                Objects.equals(this.viewCount, spaceSummary.viewCount) &&
                Objects.equals(this.address, spaceSummary.address) &&
                Objects.equals(this.introduction, spaceSummary.introduction) &&
                Objects.equals(this.reviewStatus, spaceSummary.reviewStatus) &&
                Objects.equals(this.createTime, spaceSummary.createTime) &&
                Objects.equals(this.publishPepole, spaceSummary.publishPepole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, coverImgUrl, type, rent, number, viewCount, publishPepole, address, reviewStatus, createTime);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SpaceSummary {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    title: ").append(toIndentedString(title)).append("\n");
        sb.append("    coverImgUrl: ").append(toIndentedString(coverImgUrl)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    rent: ").append(toIndentedString(rent)).append("\n");
        sb.append("    number: ").append(toIndentedString(number)).append("\n");
//    sb.append("    publishTime: ").append(toIndentedString(publishTime)).append("\n");
        sb.append("    viewsCount: ").append(toIndentedString(viewCount)).append("\n");
        sb.append("    publishPepole: ").append(toIndentedString(publishPepole)).append("\n");
        sb.append("    address: ").append(toIndentedString(address)).append("\n");
        sb.append("    introduction: ").append(toIndentedString(introduction)).append("\n");
        sb.append("    reviewStatus: ").append(toIndentedString(reviewStatus)).append("\n");
        sb.append("    createTime: ").append(toIndentedString(createTime)).append("\n");
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

    @ApiModelProperty(example = "/H5/spacerentDetails.html?id=1234&type=1", value = "H5链接")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

