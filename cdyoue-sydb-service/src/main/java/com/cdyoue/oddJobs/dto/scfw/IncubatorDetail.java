package com.cdyoue.oddJobs.dto.scfw;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * IncubatorDetail
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-16T14:01:12.120Z")

public class IncubatorDetail {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("address")
    private String address = null;

    @JsonProperty("level")
    private Integer level = null;
    @JsonProperty("levelName")
    private String levelName = null;

    @JsonProperty("content")
    private String content = null;

    @JsonProperty("introduction")
    private String introduction = null;

    @JsonProperty("logoUrl")
    private String logoUrl = null;

    @JsonProperty("createBy")
    private String createBy = null;

    @JsonProperty("link")
    private String link = null;

    @JsonProperty("viewCount")
    private Integer viewCount = 0;

    @JsonProperty("createTime")
    private String createTime = null;

    public IncubatorDetail name(String name) {
        this.name = name;
        return this;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName( String levelName) {
        this.levelName = levelName;
    }

    /**
     * 孵化园 id 自动生成
     *
     * @return id
     **/
    @ApiModelProperty(example = "11", value = "Incubator id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public IncubatorDetail id(String id) {
        this.id = id;
        return this;
    }

    /**
     * 创建者 自动生成
     *
     * @return createBy id
     **/
    @ApiModelProperty(example = "88", value = "creator id")
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public IncubatorDetail createBy(String createBy) {
        this.createBy = createBy;
        return this;
    }

    /**
     * 名称
     *
     * @return name
     **/
    @ApiModelProperty(example = "贝壳菁汇孵化器", value = "名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IncubatorDetail address(String address) {
        this.address = address;
        return this;
    }

    /**
     * 地址
     *
     * @return address
     **/
    @ApiModelProperty(example = "四川省成都市菁蓉镇", value = "地址")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public IncubatorDetail level(Integer level) {
        this.level = level;
        return this;
    }

    /**
     * 孵化器级别（1:国家 2：省 3：市  4：区）
     *
     * @return level
     **/
    @ApiModelProperty(example = "1", value = "孵化器级别（1:国家 2：省 3：市  4：区）")
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public IncubatorDetail content(String content) {
        this.content = content;
        return this;
    }

    /**
     * 详细内容
     *
     * @return content
     **/
    @ApiModelProperty(example = "1996年10月，王强在北京新东方学校开创了称", value = "详细内容")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public IncubatorDetail introduction(String introduction) {
        this.introduction = introduction;
        return this;
    }

    /**
     * 简介
     *
     * @return introduction
     **/
    @ApiModelProperty(example = "金三优服,隶属于北京金三科技股份有限公司,专注提供电信增值业务许可证,icp经营许可证,edi许可证,", value = "简介")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public IncubatorDetail logoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
        return this;
    }

    /**
     * logo封面
     *
     * @return logoUrl
     **/
    @ApiModelProperty(example = "http://172.16.0.102/image.jpg", value = "logo封面")
    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    @ApiModelProperty(example = "500", value = "浏览量")
    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    @ApiModelProperty(example = "2009/09/12 10:00:12", value = "创建时间")
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IncubatorDetail incubatorDetail = (IncubatorDetail) o;
        return Objects.equals(this.name, incubatorDetail.name) &&
                Objects.equals(this.address, incubatorDetail.address) &&
                Objects.equals(this.level, incubatorDetail.level) &&
                Objects.equals(this.levelName, incubatorDetail.levelName) &&
                Objects.equals(this.content, incubatorDetail.content) &&
                Objects.equals(this.introduction, incubatorDetail.introduction) &&
                Objects.equals(this.logoUrl, incubatorDetail.logoUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, level, content, introduction, logoUrl);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class IncubatorDetail {\n");

        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    address: ").append(toIndentedString(address)).append("\n");
        sb.append("    level: ").append(toIndentedString(level)).append("\n");
        sb.append("    levelName: ").append(toIndentedString(levelName)).append("\n");
        sb.append("    content: ").append(toIndentedString(content)).append("\n");
        sb.append("    introduction: ").append(toIndentedString(introduction)).append("\n");
        sb.append("    logoUrl: ").append(toIndentedString(logoUrl)).append("\n");
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

    @ApiModelProperty(example = "/H5/incubatorDetails.html?id=1234", value = "H5链接")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

