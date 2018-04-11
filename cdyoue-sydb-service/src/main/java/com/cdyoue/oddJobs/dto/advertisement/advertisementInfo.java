package com.cdyoue.oddJobs.dto.advertisement;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * Created by Administrator on 2017/5/10.
 */
public class advertisementInfo {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("title")//广告标题
    private String title;

    @JsonProperty("coverImg")//广告图片
    private String coverImg;

    @JsonProperty("link")//链接
    private String link;

    /**
     * 名称或者标题
     * @return title
     **/
    @ApiModelProperty(example = "这个广告位被我承包了", value = "名称或者标题")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * 图片路径
     * @return coverImg
     **/
    @ApiModelProperty(example = "xxx/xxx/xx.jpg", value = "图片路径")
    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    /**
     * 页面链接
     * @return link
     **/
    @ApiModelProperty(example = "www.baidu.com", value = "页面链接")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        advertisementInfo article = (advertisementInfo) o;
        return  Objects.equals(this.id, article.id) &&
                Objects.equals(this.title, article.title) &&
                Objects.equals(this.coverImg, article.coverImg) &&

                Objects.equals(this.link, article.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(  title, coverImg,link);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Article {\n");
        sb.append("    title: ").append(toIndentedString(title)).append("\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    coverImg: ").append(toIndentedString(coverImg)).append("\n");
        sb.append("    link: ").append(toIndentedString(link)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
