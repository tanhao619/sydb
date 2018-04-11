package com.cdyoue.oddJobs.entity.syData;


import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/9/18.
 */
@Entity
@Table(name = "sy_equipment", schema = "syData", catalog = "")
public class EquipmentEntity {
    private Integer id;
    private String name;
    private Integer price;
    private Integer type;
    private String imgUrl;
    private String owner;
    private Integer viewCount;
    private Integer favorCount;
    private String model;
    private Integer value;
    private String manufacturer;
    private Integer status;
    private String detail;
    private Date createTime;
    private Integer createBy;
    private Integer top;
    private String category;
    private Integer updateBy;
    private Date updateTime;
    private String topImg;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "price")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Basic
    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "imgUrl")
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Basic
    @Column(name = "owner")
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Basic
    @Column(name = "viewCount")
    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    @Basic
    @Column(name = "favorCount")
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

    @Basic
    @Column(name = "value")
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Basic
    @Column(name = "manufacturer")
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "detail")
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Basic
    @Column(name = "createTime")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "createBy")
    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "top")
    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    @Basic
    @Column(name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Basic
    @Column(name = "updateBy")
    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    @Basic
    @Column(name = "updateTime")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "topImg")
    public String getTopImg() {
        return topImg;
    }

    public void setTopImg(String topImg) {
        this.topImg = topImg;
    }

    @Override
    public String toString() {
        return "EquipmentEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", imgUrl='" + imgUrl + '\'' +
                ", owner='" + owner + '\'' +
                ", viewCount=" + viewCount +
                ", favorCount=" + favorCount +
                ", model=" + model +
                ", value=" + value +
                ", manufacturer='" + manufacturer + '\'' +
                ", status=" + status +
                ", detail='" + detail + '\'' +
                ", createTime=" + createTime +
                ", createBy=" + createBy +
                ", top=" + top +
                '}';
    }
}
