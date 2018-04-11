package com.cdyoue.oddJobs.dto.lgfc;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Created by Administrator on 2017/5/6.
 */
public class TopicMini {
    @JsonProperty("id")
    private Integer id = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("follow")
    private Integer follow = null;

    @JsonProperty("info")
    private String info=null;

    @JsonProperty("viewCount")
    private Integer viewCount;

    private Boolean isfollow;

    @JsonProperty("coverImg")
    private String coverImg = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 名字
     * @return name
     **/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 关注数
     * @return follow
     */
    public Integer getFollow() {
        return follow;
    }

    public void setFollow(Integer follow) {
        this.follow = follow;
    }

    /**
     * 简介
     * @return info
     */
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * 是否关注
     * @return boolean
     */
    public Boolean getIsfollow() {
        return isfollow;
    }

    public void setIsfollow(Boolean isfollow) {
        this.isfollow = isfollow;
    }

    /**
     * 封面图
     * @return coverImg
     */
    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    /**
     * 浏览量
     * @return viewCount
     */
    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }
}
