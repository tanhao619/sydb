package com.cdyoue.oddJobs.dto.scfw;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * Created by Administrator on 2017/5/12.
 */
public class IncubatorInfo {
    @JsonProperty("id")//孵化器ID
    private int id;
    @JsonProperty("name")//孵化器名称
    private String name;
    @JsonProperty("levelId")//孵化器级别
    private Integer levelId;
    @JsonProperty("address")//孵化器地址
    private String address;
    @JsonProperty("coverImg")//封面图片
    private String coverImg;
    @JsonProperty("logo")//封面图片
    private String logo;
    @JsonProperty("info")//简介
    private String info;
    @JsonProperty("introduction")//详细内容
    private String introduction;

    /**
     * 返回id
     * @return id
     **/
    @ApiModelProperty(example = "1", value = "孵化器id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * 返回name
     * @return name
     **/
    @ApiModelProperty(example = "孵化器1号", value = "孵化器名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 返回孵化器等级编号
     * @return levelId
     **/
    @ApiModelProperty(example = "1", value = "孵化器等级:1:国家级孵化器；2：省级孵化器；3：市级孵化器；区级孵化器")
    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    /**
     * 返回孵化器地址
     * @return address
     **/
    @ApiModelProperty(example = "静园东路138号", value = "返回孵化器地址")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 封面图片
     * @return coverImg
     **/
    @ApiModelProperty(example = "xxx/xxx/xxx.jpg", value = "返回孵化器封面图片(孵化器级别对应不同的图标)")
    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }


    /**
     * 封面图片
     * @return coverImg
     **/
    @ApiModelProperty(example = "xxx/xxx/xxx.jpg", value = "返回孵化器封面图片(孵化器级别对应不同的图标)")
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     * 简介
     * @return info
     **/
    @ApiModelProperty(example = "我们很强！我们最屌！", value = "返回孵化器简介")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * 简介
     * @return introduction
     **/
    @ApiModelProperty(example = "我们就是很强！我们就是最屌！", value = "返回孵化器详情内容")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IncubatorInfo article = (IncubatorInfo) o;
        return  Objects.equals(this.id, article.id) &&
                Objects.equals(this.name, article.name) &&
                Objects.equals(this.levelId, article.levelId) &&
                Objects.equals(this.address, article.address) &&
                Objects.equals(this.logo, article.logo)&&
                Objects.equals(this.coverImg, article.coverImg)&&
                Objects.equals(this.info, article.info)&&
                Objects.equals(this.introduction, article.introduction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,name, levelId, address , logo,coverImg,info,introduction);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Article {\n");
        sb.append("    title: ").append(toIndentedString(id)).append("\n");
        sb.append("    title: ").append(toIndentedString(name)).append("\n");
        sb.append("    author: ").append(toIndentedString(levelId)).append("\n");
        sb.append("    content: ").append(toIndentedString(address)).append("\n");
        sb.append("    publishTime: ").append(toIndentedString(logo)).append("\n");
        sb.append("    publishTime: ").append(toIndentedString(coverImg)).append("\n");
        sb.append("    viewsAccount: ").append(toIndentedString(info)).append("\n");
        sb.append("    favorAccount: ").append(toIndentedString(introduction)).append("\n");
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
