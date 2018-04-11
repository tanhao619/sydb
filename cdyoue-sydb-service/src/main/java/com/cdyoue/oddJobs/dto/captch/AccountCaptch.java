package com.cdyoue.oddJobs.dto.captch;


import com.cdyoue.oddJobs.dto.oauth.CacheBase;

/**
 * Created by liaoyoule on 2017/5/17.
 */
public class AccountCaptch implements CacheBase {
    private int code;
    private long expire;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public Boolean isExpires(){
        return this.expire - System.currentTimeMillis() < 0 ? true : false;
    }
}
