//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cdyoue.common;

import org.springframework.boot.SpringApplication;
import org.springframework.core.env.Environment;

import java.util.HashMap;

public final class DefaultProfileUtil {
    private static final String SPRING_PROFILE_DEFAULT = "spring.profiles.default";

    public DefaultProfileUtil() {
    }

    public static void addDefaultProfile(SpringApplication app) {
        HashMap defProperties = new HashMap();
        defProperties.put("spring.profiles.default", "dev");
        app.setDefaultProperties(defProperties);
    }

    public static String[] getActiveProfiles(Environment env) {
        String[] profiles = env.getActiveProfiles();
        return profiles.length == 0?env.getDefaultProfiles():profiles;
    }
}
