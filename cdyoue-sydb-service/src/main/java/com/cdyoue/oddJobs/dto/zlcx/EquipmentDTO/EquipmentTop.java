package com.cdyoue.oddJobs.dto.zlcx.EquipmentDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by Administrator on 2017/9/18.
 */
public class EquipmentTop {
    @JsonProperty("id")//设备id
    private Integer id = null;

    @JsonProperty("name")//设备名称
    private String name  = null;

    @JsonProperty("price")//设备价格
    private Integer price  = null;

    @JsonProperty("imgUrl")//设备价格
    private String imgUrl  = null;

    @JsonProperty("createBy")//创建人
    private String createBy = null;

    @JsonProperty("createTime")//创建时间
    private Date createTime;

    @JsonProperty("viewCount")//设备浏览次数
    private Integer viewCount;

    @JsonProperty("applyTime")//设备申请次数
    private Integer applyTime;

    @JsonProperty("top")
    private Integer top;//是否被置顶

    @JsonProperty("topImg")//设备置顶图片
    private String topImg  = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Integer applyTime) {
        this.applyTime = applyTime;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
