package com.cdyoue.oddJobs.dto.home;

import java.util.List;

/**
 * Created by liaoyoule on 2017/5/6.
 */
public class HomePageSumary {
    private String name;
    private List<HomePageInfoSumary> homePageInfoSumaries;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<HomePageInfoSumary> getHomePageInfoSumaries() {
        return homePageInfoSumaries;
    }

    public void setHomePageInfoSumaries(List<HomePageInfoSumary> homePageInfoSumaries) {
        this.homePageInfoSumaries = homePageInfoSumaries;
    }

}
