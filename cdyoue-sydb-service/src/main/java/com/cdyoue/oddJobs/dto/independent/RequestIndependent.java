package com.cdyoue.oddJobs.dto.independent;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by liaoyoule on 2017/5/11.
 */
public class RequestIndependent {
    @NotNull
    @Size(min = 1,message = "标题必填")
    private String title;
    private String coverImg;

    private String brief;

    private String intro;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
