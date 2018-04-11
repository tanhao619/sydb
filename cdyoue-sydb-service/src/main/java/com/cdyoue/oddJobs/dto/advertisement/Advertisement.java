package com.cdyoue.oddJobs.dto.advertisement;

import com.cdyoue.oddJobs.dto.lgfc.Article;
import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * Created by Administrator on 2017/5/10.
 */
public class Advertisement {
    @JsonProperty("id")//id
    private int id;

    @JsonProperty("title")//广告标题
    private String title;

    @JsonProperty("createBy")//发布人
    private String  createBy;

    @JsonProperty("refreshTime")//更新时间
    private Long refreshTime;

    @JsonProperty("coverImg")//广告图片
    private String coverImg;

    @JsonProperty("orderBy")//排序id(左1，中2，右3)
    private int orderBy;

    @JsonProperty("link")//链接
    private String link;

    /**
     * Get id
     * @return id
     **/
    @ApiModelProperty(value = "1")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    /**
     * 发布人
     * @return title
     **/
    @ApiModelProperty(example = "赵日天", value = "发布人")
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * 更改时间
     * @return refreshTime
     **/
    @ApiModelProperty(example = "2017-5-10 19:42:18", value = "更改时间")
    public Long getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(Long refreshTime) {
        this.refreshTime = refreshTime;
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
     * 排序序号
     * @return orderBy
     **/
    @ApiModelProperty(example = "1", value = "排序序号，左1，中2，右3")
    public int getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(int orderBy) {
        this.orderBy = orderBy;
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
        Advertisement article = (Advertisement) o;
        return Objects.equals(this.id, article.id) &&
                Objects.equals(this.title, article.title) &&
                Objects.equals(this.refreshTime, article.refreshTime) &&
                Objects.equals(this.coverImg, article.coverImg) &&
                Objects.equals(this.orderBy, article.orderBy) &&
                Objects.equals(this.link, article.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, refreshTime, coverImg, orderBy, link);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Article {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    title: ").append(toIndentedString(title)).append("\n");
        sb.append("    refreshTime: ").append(toIndentedString(refreshTime)).append("\n");
        sb.append("    coverImg: ").append(toIndentedString(coverImg)).append("\n");
        sb.append("    orderBy: ").append(toIndentedString(orderBy)).append("\n");
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
