package com.cdyoue.oddJobs.config;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.en.StatusEnum;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liaoyoule on 2017/6/19.
 */
@Component
public class ImageAdaptation {
    private Logger logger = Logger.getLogger(getClass());


    public Object convertImage(ResponseEntity re) {
        Object body = re.getBody();

        if (body instanceof HttpMessage) {
            return body;
        }
        String reponseStr = JSONObject.toJSONString(body,
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.WriteNullNumberAsZero);
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest hsr  =attributes.getRequest();


//        reponseStr = hsr.getHeader("IP") != null ? reponseStr : reponseStr.replaceAll("(?i)\\.jpg", "_mobile.jpg")
//                .replaceAll("(?i)\\.png", "_mobile.png");

         return JSONObject.parseObject(reponseStr, Object.class);
    }
}