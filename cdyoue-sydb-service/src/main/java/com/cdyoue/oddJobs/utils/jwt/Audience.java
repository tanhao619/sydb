package com.cdyoue.oddJobs.utils.jwt;

import com.cdyoue.oddJobs.dto.oauth.TokenSumary;
import com.cdyoue.oddJobs.en.LoginTypeEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Hashtable;
import java.util.stream.StreamSupport;

/**
 * Created by liaoyoule on 2017/5/4.
 */
@Component
public class Audience {
    @Value("${audience.webId}")
    private String webId;
    @Value("${audience.appId}")
    private String appId;
    @Value("${audience.base64Secret}")
    private String base64Secret;
    @Value("${audience.name}")
    private String name;
    @Value("${audience.expiresSecond}")
    private int expiresSecond;
    @Value("${spring.profiles.active}")
    private String active;


    public String getWebId() {
        return webId;
    }

    public void setWebId(String webId) {
        this.webId = webId;
    }

    public String getBase64Secret() {
        return base64Secret;
    }
    public void setBase64Secret(String base64Secret) {
        this.base64Secret = base64Secret;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getExpiresSecond() {
        return expiresSecond;
    }
    public void setExpiresSecond(int expiresSecond) {
        this.expiresSecond = expiresSecond;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
