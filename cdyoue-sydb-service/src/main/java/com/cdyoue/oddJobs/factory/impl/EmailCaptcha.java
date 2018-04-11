package com.cdyoue.oddJobs.factory.impl;

import com.cdyoue.oddJobs.factory.abs.CaptchaAbstract;
import com.cdyoue.oddJobs.utils.MailManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by liaoyoule on 2017/6/16.
 */
@Component
public class EmailCaptcha extends CaptchaAbstract {
    @Autowired
    private MailManager mailManager;
    @Override
    public void sendCaptcha(String to) {
        mailManager.sendResetCaptcha(to);
    }
}
