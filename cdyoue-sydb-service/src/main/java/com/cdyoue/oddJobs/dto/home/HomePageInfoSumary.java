package com.cdyoue.oddJobs.dto.home;

import java.util.List;

/**
 * Created by liaoyoule on 2017/5/6.
 */
public class HomePageInfoSumary {
    private HomePageInfoBase homePageInfoBase;
    private List<HomePageProperty> homePageProperties;

    public List<HomePageProperty> getHomePageProperties() {
        return homePageProperties;
    }

    public void setHomePageProperties(List<HomePageProperty> homePageProperties) {
        this.homePageProperties = homePageProperties;
    }
}
