package com.cdyoue.oddJobs.dto;

import java.sql.Timestamp;

/**
 * Created by th on 2017/09/26.
 */
public class SyCooperativePartnerMiniDTO {
    private String name;
    private String url;
    private String logoImg;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLogoImg() {
        return logoImg;
    }

    public void setLogoImg(String logoImg) {
        this.logoImg = logoImg;
    }
}
