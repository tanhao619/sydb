package com.cdyoue.oddJobs.factory;

import com.cdyoue.oddJobs.en.AccountTypeEnum;
import com.cdyoue.oddJobs.factory.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by liaoyoule on 2017/4/25.
 */
@Component
public class CaptchaFactory {
    @Autowired
    private EmailCaptcha emailCaptcha;
    @Autowired
    private TelCaptcha telCaptcha;

    public void sendCaptcha(String to, AccountTypeEnum accountTypeEnum){
        switch (accountTypeEnum){
            case EMAIL:
                emailCaptcha.sendCaptcha(to);
                break;
            case TEL:
                telCaptcha.sendCaptcha(to);
                break;
        }
    }

}
