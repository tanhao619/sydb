package com.cdyoue.oddJobs.utils;

import com.cdyoue.oddJobs.constant.SmsConstant;
import com.cdyoue.oddJobs.dto.captch.CaptchMemory;
import com.cdyoue.oddJobs.dto.captch.SmsResponse;
import com.cdyoue.oddJobs.dto.captch.AccountCaptch;
import com.cdyoue.oddJobs.utils.jwt.TokenCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Created by liaoyoule on 2017/5/17.
 */
@Component
public class SmsManager {

    @Value("${default.sms.apikey}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;


    @Async
    public void sendTelCaptcha(long tel) {

        LinkedMultiValueMap params = new LinkedMultiValueMap();

        int code = CaptchaUtils.getRandNum(1,999999);
//        String text = new StringBuffer("【沈阳工业】验证码:")
//                .append(code)
//                .append("，请您在5分钟内填写。如非本人操作，请忽略本短信。")
//                .toString();
        String text = new StringBuffer("【装备制造业大数据】验证码：")
                .append(code)
                .append("，请您在5分钟内填写，如非本人操作，请忽略本短信")
                .toString();
        params.add("apikey", apiKey);
        params.add("text", text);
        params.add("mobile", String.valueOf(tel));

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/x-www-form-urlencoded; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        HttpEntity<String> paramEntity = new HttpEntity(params, headers);

        SmsResponse smsResponse = restTemplate.postForObject(SmsConstant.URI_SEND_SMS, paramEntity, SmsResponse.class);
        int rpCode = smsResponse.getCode();
        switch (rpCode){
            case  0:
                AccountCaptch ct = new AccountCaptch();
                ct.setCode(code);
                ct.setExpire(System.currentTimeMillis()+5*60*1000);
                TokenCache.telCaptchStore(tel,ct);
                break;
            default:
                System.err.println(smsResponse);

        }


    }

    @Async
    public void sendResetCaptcha(String to) {
        LinkedMultiValueMap params = new LinkedMultiValueMap();

        int code = CaptchaUtils.getResetRandNum(1,999999);
//        String text = new StringBuffer("【沈阳工业】验证码:")
//                .append(code)
//                .append("，请您在5分钟内填写。如非本人操作，请忽略本短信。")
//                .toString();
        String text = new StringBuffer("【装备制造业大数据】验证码：")
                .append(code)
                .append("，请您在5分钟内填写，如非本人操作，请忽略本短信")
                .toString();
        params.add("apikey", apiKey);
        params.add("text", text);
        params.add("mobile", to);

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/x-www-form-urlencoded; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        HttpEntity<String> paramEntity = new HttpEntity(params, headers);

        SmsResponse smsResponse = restTemplate.postForObject(SmsConstant.URI_SEND_SMS, paramEntity, SmsResponse.class);
        int rpCode = smsResponse.getCode();
        switch (rpCode){
            case  0:
                CaptchMemory memory = new CaptchMemory();
                memory.setAccount(to);
                memory.setExpire(System.currentTimeMillis()+5*60*1000);
                TokenCache.resetCaptchStore(code,memory);
                break;
            default:
                System.err.println(smsResponse);

        }
    }
}
