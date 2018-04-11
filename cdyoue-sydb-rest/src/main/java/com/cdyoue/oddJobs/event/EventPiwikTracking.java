package com.cdyoue.oddJobs.event;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class EventPiwikTracking {
    @Value("${default.eventTracking.piwikUrl}")
    private String piwikAddress;
    @Autowired
    private RestTemplate restTemplate;

    @Async
    public void doTracking(String categories, String oper) {
        DateTime now = DateTime.now();
        Map parm = new HashMap();
        parm.put("ec", categories);
        parm.put("ea", oper);
        parm.put("idsite", "1");
        parm.put("rec", "1");
        parm.put("r", "330973");
        parm.put("h", now.getHourOfDay());
        parm.put("m", now.getMinuteOfHour());
        parm.put("s", now.getSecondOfMinute());
        StringBuilder url = new StringBuilder(piwikAddress)
                .append("?e_c={ec}&e_a={ea}&idsite={idsite}&rec={rec}&r={r}&h={h}&m={m}&s={s}");
        restTemplate.getForEntity(url.toString(), null, parm);
    }

}
