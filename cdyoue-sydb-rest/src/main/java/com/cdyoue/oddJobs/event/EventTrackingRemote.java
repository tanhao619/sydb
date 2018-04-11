package com.cdyoue.oddJobs.event;

import com.cdyoue.oddJobs.config.DynamicSetting;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by liaoyoule on 2017/5/3.
 */
@Component
public class EventTrackingRemote {
    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DynamicSetting dynamicSetting;
    @Async
    public void transferEvent(EventRequest event){
        String url = new StringBuffer(dynamicSetting.getEventRemoteUrl())
                                     .append("/event/api/msgRequest").toString();
        try {
            restTemplate.postForLocation(url,event);
        } catch (Exception e) {
            logger.error("eventRemote full path:"+url +"  eventRemote error:"+e.getMessage());
        }
    }

}
