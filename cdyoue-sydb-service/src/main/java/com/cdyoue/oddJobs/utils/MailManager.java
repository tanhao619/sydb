package com.cdyoue.oddJobs.utils;

import com.cdyoue.oddJobs.dto.captch.AccountCaptch;
import com.cdyoue.oddJobs.dto.captch.CaptchMemory;
import com.cdyoue.oddJobs.utils.jwt.TokenCache;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Syntax;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by liaoyoule on 2017/5/7.
 */
@Component
public class MailManager {
    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender; //读取配置文件中的参数
    @Value("${server.context-path}")
    private String context;
    @Value("${server.network-address}")
    private String address;

    @Async
    public void sendActiveMail(String to, String token) {

        StringBuffer url = new StringBuffer(address)
                .append(context)
                .append("/active")
                .append("?token=")
                .append(token);


        StringBuffer str = new StringBuffer("亲爱的")
                .append(to)
                .append(": ")
                .append("<br/>感谢您在我站注册了新帐号。<br/>请点击链接激活您的帐号。<br/>")
                .append("<a href='")
                .append(url)
                .append("'>")
                .append(url)
                .append("</a><br/>")
                .append("如果以上链接无法点击，请将它复制到你的浏览器地址栏中进入访问，该链接24小时内有效。");

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(sender);
            helper.setTo(to);
            helper.setSubject("零工社区 激活邮件 dev");
            helper.setText(str.toString(), true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }









    @Async//发送验证码到指定邮箱
    public void sendAccountCaptchaMail(String to) {
        int randNum = CaptchaUtils.getRandNum(1, 999999);
        StringBuffer str = new StringBuffer("<p style=\"margin:0;padding:0;font-size:14px;line-height:30px;color:#333;font-family:arial,sans-serif;font-weight:bold\">亲爱的")
                .append(to)
                .append(": </p>")
                .append("<br/><p style=\"margin:0;padding:0;line-height:30px;font-size:14px;color:#333;font-family:'宋体',arial,sans-serif\">您好！感谢您使用零工社区服务，您正在进行邮箱验证，本次请求的验证码为：</p><br/>")
                .append("<b style=\"font-size:18px;color:#f90\">"+randNum+"</b>")
                .append("<span style=\"margin:0;padding:0;margin-left:10px;line-height:30px;font-size:14px;color:#979797;font-family:'宋体',arial,sans-serif\">(为了保障您帐号的安全性，请在5分钟内完成验证。)</span>");

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(sender);
            helper.setTo(to);
            helper.setSubject("零工社区 邮箱验证 dev");
            helper.setText(str.toString(), true);
            javaMailSender.send(mimeMessage);
            AccountCaptch ac = new AccountCaptch();
            ac.setExpire(System.currentTimeMillis() + 5 * 60 * 1000);
            ac.setCode(randNum);
            TokenCache.emailCaptchStore(to,ac);
        } catch (MessagingException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }







    @Async//发送验证码到指定邮箱
    public void sendVerificationMail(String to, String token) {
        StringBuffer str = new StringBuffer("<p style=\"margin:0;padding:0;font-size:14px;line-height:30px;color:#333;font-family:arial,sans-serif;font-weight:bold\">亲爱的")
                .append(to)
                .append(": </p>")
                .append("<br/><p style=\"margin:0;padding:0;line-height:30px;font-size:14px;color:#333;font-family:'宋体',arial,sans-serif\">您好！感谢您使用零工社区服务，您正在进行邮箱验证，本次请求的验证码为：</p><br/>")
                .append("<b style=\"font-size:18px;color:#f90\">"+token+"</b>")
                .append("<span style=\"margin:0;padding:0;margin-left:10px;line-height:30px;font-size:14px;color:#979797;font-family:'宋体',arial,sans-serif\">(为了保障您帐号的安全性，请在5分钟内完成验证。)</span>");

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(sender);
            helper.setTo(to);
            helper.setSubject("零工社区 邮箱验证 dev");
            helper.setText(str.toString(), true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    @Async
    public void sendResetCaptcha(String to) {

        int randNum = CaptchaUtils.getResetRandNum(1, 999999);


        StringBuffer str = new StringBuffer("<p style=\"margin:0;padding:0;font-size:14px;line-height:30px;color:#333;font-family:arial,sans-serif;font-weight:bold\">亲爱的")
                .append(to)
                .append(": </p>")
                .append("<br/><p style=\"margin:0;padding:0;line-height:30px;font-size:14px;color:#333;font-family:'宋体',arial,sans-serif\">您好！感谢您使用零工社区服务，您正在进行邮箱验证，本次请求的验证码为：</p><br/>")
                .append("<b style=\"font-size:18px;color:#f90\">"+randNum+"</b>")
                .append("<span style=\"margin:0;padding:0;margin-left:10px;line-height:30px;font-size:14px;color:#979797;font-family:'宋体',arial,sans-serif\">(为了保障您帐号的安全性，请在5分钟内完成验证。)</span>");

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(sender);
            helper.setTo(to);
            helper.setSubject("零工社区 邮箱验证 dev");
            helper.setText(str.toString(), true);
            javaMailSender.send(mimeMessage);
            CaptchMemory memory = new CaptchMemory();
            memory.setExpire(System.currentTimeMillis() + 5 * 60 * 1000);
            memory.setAccount(to);
            TokenCache.resetCaptchStore(randNum,memory);
        } catch (MessagingException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
}
