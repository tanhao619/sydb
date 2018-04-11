package com.cdyoue.oddJobs.dto.zscq;

import java.util.Objects;

import com.cdyoue.oddJobs.dto.Contact;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * IntellectualSaleSummary
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-11T00:49:03.408Z")

public class IntellectualSaleSummary {
    @JsonProperty("id")
    private Integer id = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("price")
    private String price = null;

    @JsonProperty("category")
    private Integer category = null;

    @JsonProperty("publisher")
    private String publisher = null;

    @JsonProperty("createTime")
    private String createTime = null;

    @JsonProperty("viewCount")
    private Integer viewCount = null;

    @JsonProperty("reviewStatus")
    private Integer reviewStatus = null;

    @JsonProperty("contact")
    private Contact contact = null;

    @JsonProperty("businessType")
    private Integer businessType = null;

    @JsonProperty("introduction")
    private String introduction = null;

    @JsonProperty("link")
    private String link = null;

    @JsonProperty("type")
    private Integer type;

    @JsonProperty("top")
    private Integer top;

    @JsonProperty("topImg")
    private String topImg;

    public IntellectualSaleSummary id(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * id
     *
     * @return id
     **/
    @ApiModelProperty(example = "12333", value = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public IntellectualSaleSummary name(String name) {
        this.name = name;
        return this;
    }

    /**
     * 知产出售名称
     *
     * @return name
     **/
    @ApiModelProperty(example = "XXXX类型的LOGO制作", value = "知产出售名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IntellectualSaleSummary price(String price) {
        this.price = price;
        return this;
    }

    /**
     * 出售价格
     *
     * @return price
     **/
    @ApiModelProperty(example = "5000", value = "出售价格")
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public IntellectualSaleSummary category(Integer category) {
        this.category = category;
        return this;
    }

    /**
     * 发布的知识产权类别：1商标，2专利，3著作权
     *
     * @return category
     **/
    @ApiModelProperty(example = "1", value = "发布的知识产权类别：1商标，2专利，3著作权")
    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public IntellectualSaleSummary contact(Contact contact) {
        this.contact = contact;
        return this;
    }

    /**
     * Get contact
     *
     * @return contact
     **/
    @ApiModelProperty(value = "")
    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @ApiModelProperty(example = "AAA", value = "发布人")
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @ApiModelProperty(example = "2012-12-12 12:12", value = "创建时间")
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @ApiModelProperty(example = "100", value = "浏览次数")
    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    @ApiModelProperty(example = "1", value = "状态：0待审核，1审核失败，2审核通过")
    public Integer getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(Integer reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    @ApiModelProperty(example = "1", value = "交易类型：1转让，2许可")
    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    @ApiModelProperty(example = "这是知产内容介绍", value = "详细信息")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

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
        IntellectualSaleSummary intellectualSaleSummary = (IntellectualSaleSummary) o;
        return Objects.equals(this.id, intellectualSaleSummary.id) &&
                Objects.equals(this.name, intellectualSaleSummary.name) &&
                Objects.equals(this.price, intellectualSaleSummary.price) &&
                Objects.equals(this.category, intellectualSaleSummary.category) &&
                Objects.equals(this.contact, intellectualSaleSummary.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, category, contact);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class IntellectualSaleSummary {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    price: ").append(toIndentedString(price)).append("\n");
        sb.append("    category: ").append(toIndentedString(category)).append("\n");
        sb.append("    contact: ").append(toIndentedString(contact)).append("\n");
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

    @ApiModelProperty(example = "/H5/patentsaleDetails.html?id=1234&type=1", value = "H5链接")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    @ApiModelProperty(value = "type : 1:商标 2:专利 3: 著作权 ")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}

