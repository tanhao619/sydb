package com.cdyoue.oddJobs.factory.impl;

import com.cdyoue.oddJobs.factory.abs.CaptchaAbstract;
import com.cdyoue.oddJobs.utils.SmsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by liaoyoule on 2017/6/16.
 */
@Component
public class TelCaptcha extends CaptchaAbstract {
    @Autowired
    private SmsManager smsManager;
    @Override
    public void sendCaptcha(String to) {
        smsManager.sendResetCaptcha(to);
    }
}
