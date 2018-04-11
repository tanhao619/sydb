package com.cdyoue.oddJobs.dto.lgfc;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by sky on 2017/5/26.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-28T13:17:18.617Z")
public class ArticleTop {


    @JsonProperty("name")//标题
    private String name = null;

    @JsonProperty("coverImg")//图片
    private String coverImg = null;

    @JsonProperty("info")//简介
    private String info = null;



    @ApiModelProperty(example = "好文章", value = "精选文章标题")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ApiModelProperty(example = "xxxx.png", value = "精选文章图片")
    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    @ApiModelProperty(example = "真的是一篇好文章！！！！！", value = "精选文章简介")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticleTop that = (ArticleTop) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (coverImg != null ? !coverImg.equals(that.coverImg) : that.coverImg != null) return false;
        if (info != null ? !info.equals(that.info) : that.info != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (coverImg != null ? coverImg.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ArticleTop {\n");

        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    coverImg: ").append(toIndentedString(coverImg)).append("\n");
        sb.append("    info: ").append(toIndentedString(info)).append("\n");
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
