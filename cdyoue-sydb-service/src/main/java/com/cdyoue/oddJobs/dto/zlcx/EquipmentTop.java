package com.cdyoue.oddJobs.dto.zlcx;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

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

}
