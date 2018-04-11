package com.cdyoue.oddJobs.utils;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.common.message.AccountMessage;
import com.cdyoue.oddJobs.dto.common.message.CommonMessage;
import com.cdyoue.oddJobs.en.AccountTypeEnum;
import com.cdyoue.oddJobs.exception.BadRequestMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liaoyoule on 2017/5/31.
 */
public class ValidateUtils {
    public static void AccountValidate(String account, AccountTypeEnum accountType){
        if (accountType == null) {
            throw  new BadRequestMessageException(AccountMessage.ACCOUNT_TYPE_INVALID);

        }
        String regexp = null;
        switch (accountType){
            case EMAIL:
                regexp = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
                break;
            case TEL:
                regexp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
                break;
        }


        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regexp);

        Matcher matcher = pattern.matcher(account);

        if(!matcher.matches()){
            throw  new BadRequestMessageException(CommonMessage.ILLEGALACCOUNT);
        }
    }


    public static void telNumber(String tel){
        String regexp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";

        Pattern pattern = Pattern.compile(regexp);

        Matcher matcher = pattern.matcher(tel);

        if(!matcher.matches()){
            throw  new BadRequestMessageException(CommonMessage.ILLEGALTEL);
        }
    }
}
