package com.cdyoue.oddJobs.scheduler;

import com.cdyoue.oddJobs.dto.oauth.CacheBase;
import com.cdyoue.oddJobs.service.UserService;
import com.cdyoue.oddJobs.utils.jwt.TokenCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;


@Component
public class Scheduler {
    @Autowired
    private UserService userService;
    @Scheduled(fixedRate = 1800000)
    public void clearAccountScheduler() {
        //删除
        userService.accountDeliquesce();
    }



    @Scheduled(cron = "0 0 4 * * ?")
    public void cacheGcScheduler() {
        this.cacheGc(TokenCache.getMemoryAppTokens());
        this.cacheGc(TokenCache.getMemoryClientTokens());
        this.cacheGc(TokenCache.getMemoryEmailCaptch());
        this.cacheGc(TokenCache.getMemoryResetCaptch());
        this.cacheGc(TokenCache.getMemoryTelCaptch());
    }

    public void cacheGc(Hashtable cache){
        Iterator<Map.Entry<Object, CacheBase>> iterator = cache.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Object, CacheBase> entry = iterator.next();
            CacheBase v = entry.getValue();
            if (v.isExpires()) {
                iterator.remove();        //OK
            }
        }
    }


}
