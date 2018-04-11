package com.cdyoue.oddJobs.dto.captch;

import com.cdyoue.oddJobs.dto.oauth.CacheBase;

/**
 * Created by liaoyoule on 2017/6/16.
 */
public class CaptchMemory implements CacheBase {
    private long expire;
    private String account;



    public Boolean isExpire(){
        return System.currentTimeMillis()-this.getExpire() >= 0 ? true:false;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Boolean isExpires(){
        return this.expire - System.currentTimeMillis() < 0 ? true : false;
    }
}
