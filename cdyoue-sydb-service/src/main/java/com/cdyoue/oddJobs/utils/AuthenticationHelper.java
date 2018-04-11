package com.cdyoue.oddJobs.utils;

import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.common.message.CommonMessage;
import com.cdyoue.oddJobs.exception.BadRequestMessageException;

/**
 * Created by liaoyoule on 2017/5/18.
 */
public class AuthenticationHelper {
    public static void isAdminORCreator(Integer createBy){
        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();
        if(!createBy.equals(currentUserLogin.getId()) && currentUserLogin.getRole() !=2 ){
            throw new BadRequestMessageException(CommonMessage.FORBIDDENEDITORiSNOTADMINORCREATOR);
        }
    }
}
