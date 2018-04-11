package com.cdyoue.oddJobs.dispatch;

import com.cdyoue.oddJobs.dto.common.message.AuthenticationMessage;
import com.cdyoue.oddJobs.exception.BadRequestMessageException;
import com.cdyoue.oddJobs.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by liaoyoule on 2017/6/19.
 */
@Controller()
public class DispatchController {
    @Value("${default.front.remoteUrl}")
    private String frontUrl;
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/active",
            method = RequestMethod.GET)
    public String active(String token) {
        if (!StringUtils.isNotBlank(token)) {
            throw new BadRequestMessageException(AuthenticationMessage.ACCOUNTEACTIVEFAILD);
        }
        userService.active(token);
        StringBuffer url = new StringBuffer("redirect:")
                .append(frontUrl).append("/#/app/catAddUser/signUpSuccess");
        return url.toString();
    }
}
