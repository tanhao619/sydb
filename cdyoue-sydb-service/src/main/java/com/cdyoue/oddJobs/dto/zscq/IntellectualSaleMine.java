package com.cdyoue.oddJobs.dto.zscq;

import java.util.Objects;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * IntellectualSaleMine
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-11T00:49:03.408Z")

public class IntellectualSaleMine {
    @JsonProperty("id")
    private Integer id = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("publishTime")
    private String publishTime = null;

    @JsonProperty("approveStatus")
    private Integer approveStatus = null;

    @JsonProperty("category")
    private Integer category = null;

    @JsonProperty("price")
    private Double price = null;

    @JsonProperty("link")
    private String link = null;

    public IntellectualSaleMine id(Integer id) {
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

    public IntellectualSaleMine name(String name) {
        this.name = name;
        return this;
    }

    /**
     * 知产名称
     *
     * @return name
     **/
    @ApiModelProperty(example = "XXXX类型的LOGO制作", value = "知产名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IntellectualSaleMine publishTime(String publishTime) {
        this.publishTime = publishTime;
        return this;
    }

    /**
     * 发布时间
     *
     * @return publishTime
     **/
    @ApiModelProperty(example = "2017/03/03", value = "发布时间")
    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public IntellectualSaleMine approveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus;
        return this;
    }

    /**
     * 审核状态：0:未审核，1:通过，2:拒绝
     * minimum: 0
     * maximum: 2
     *
     * @return approveStatus
     **/
    @ApiModelProperty(example = "1", value = "审核状态：0:未审核，1:通过，2:拒绝")
    @Min(0)
    @Max(2)
    public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IntellectualSaleMine intellectualSaleMine = (IntellectualSaleMine) o;
        return Objects.equals(this.id, intellectualSaleMine.id) &&
                Objects.equals(this.name, intellectualSaleMine.name) &&
                Objects.equals(this.publishTime, intellectualSaleMine.publishTime) &&
                Objects.equals(this.approveStatus, intellectualSaleMine.approveStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, publishTime, approveStatus);
    }

    @Override
    public String toString() {
        return "IntellectualSaleMine{" +
                "approveStatus=" + approveStatus +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", publishTime='" + publishTime + '\'' +
                ", category=" + category +
                ", price=" + price +
                '}';
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

    @ApiModelProperty(example = "1", value = "发布的知识产权类别：1商标，2专利，3著作权")
    public Integer getCategory() {
        return category;
    }

    @ApiModelProperty(example = "120.4", value = "价格")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    @ApiModelProperty(example = "/H5/patentsaleDetails.html?id=1234&type=1", value = "H5链接")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

