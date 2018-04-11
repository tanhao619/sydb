package com.cdyoue.oddJobs.entity.lgsq;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Administrator on 2017/5/18.
 */
@Entity
public class PortalIntellectualSaleMineEntity {
    @Id
    private Integer id ;

    private String name ;

    private String publishTime ;

    private Integer reviewStatus ;

    private Integer category ;

    private Double price ;

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

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(Integer reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
