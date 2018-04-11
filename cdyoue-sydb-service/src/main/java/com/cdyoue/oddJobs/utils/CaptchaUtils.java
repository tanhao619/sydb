package com.cdyoue.oddJobs.utils;

import com.cdyoue.oddJobs.utils.jwt.TokenCache;

/**
 * Created by liaoyoule on 2017/6/16.
 */
public class CaptchaUtils {
    public static int getRandNum(int min, int max) {
        int randNum = min + (int)(Math.random() * ((max - min) + 1));
        return randNum;
    }

    public static int getResetRandNum(int min, int max) {
        int randNum = getRandNum(min,max);
        if(  TokenCache.getResetCaptch(randNum)!=null){
            randNum = getRandNum(min, max);
        }
        return randNum;
    }
}
