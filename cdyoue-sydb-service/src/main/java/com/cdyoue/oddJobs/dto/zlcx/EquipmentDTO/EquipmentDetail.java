package com.cdyoue.oddJobs.dto.zlcx.EquipmentDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by Administrator on 2017/9/18.
 */
public class EquipmentDetail {
    @JsonProperty("id")//设备id
    private Integer id;

    @JsonProperty("name")//设备名称
    private String name;

    @JsonProperty("price")//设备价格
    private Integer price;

    @JsonProperty("imgUrl")//图片路径
    private String imgUrl;

    @JsonProperty("owner")//所有者
    private String owner;

    @JsonProperty("viewCount")//浏览量
    private Integer viewCount;

    @JsonProperty("favorCount")//收藏量
    private Integer favorCount;

    @JsonProperty("type")//设备所属行业
    private String type;

    @JsonProperty("model")//设备型号
    private String model;

    @JsonProperty("value")//设备价值
    private Integer value;

    @JsonProperty("manufacturer")//生产厂商
    private String manufacturer;

    @JsonProperty("status")//设备状态
    private String status;

    @JsonProperty("detail")//设备详情
    private String detail;

    @JsonProperty("createTime")//创建时间
    private String createTime;

    @JsonProperty("createBy")//创建人
    private String createBy;

    @JsonProperty("category")//仪器类别
    private  String category;

    @JsonProperty("topImg")//仪器类别
    private  String topImg;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getFavorCount() {
        return favorCount;
    }

    public void setFavorCount(Integer favorCount) {
        this.favorCount = favorCount;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getTopImg() {
        return topImg;
    }

    public void setTopImg(String topImg) {
        this.topImg = topImg;
    }

    @Override
    public String toString() {
        return "EquipmentDetail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", imgUrl='" + imgUrl + '\'' +
                ", owner='" + owner + '\'' +
                ", viewCount=" + viewCount +
                ", favorCount=" + favorCount +
                ", model='" + model + '\'' +
                ", value=" + value +
                ", manufacturer='" + manufacturer + '\'' +
                ", status='" + status + '\'' +
                ", detail='" + detail + '\'' +
                ", createTime='" + createTime + '\'' +
                ", createBy='" + createBy + '\'' +
                '}';
    }
}
