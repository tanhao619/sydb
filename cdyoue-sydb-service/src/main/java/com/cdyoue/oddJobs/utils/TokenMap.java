package com.cdyoue.oddJobs.utils;

import com.alibaba.fastjson.JSONObject;
import com.cdyoue.oddJobs.dto.User;
import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.common.message.AuthenticationMessage;
import com.cdyoue.oddJobs.dto.oauth.TokenSumary;
import com.cdyoue.oddJobs.en.LoginTypeEnum;
import com.cdyoue.oddJobs.utils.jwt.Audience;
import com.cdyoue.oddJobs.utils.jwt.JwtHelper;
import com.cdyoue.oddJobs.utils.jwt.TokenCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/10/10.
 */
public  class TokenMap {
    @Autowired
    private static JwtHelper jwtHelper;
    @Autowired
    private static Audience audience;

    public static Map<String,TokenSumary> tokens = new ConcurrentHashMap<String, TokenSumary>();

    public static void setToken(TokenSumary tokenSumary){
        tokens.put(tokenSumary.getAccess_token(),tokenSumary);
    }

    public static TokenSumary getToken(String tokenString){
        return tokens.get(tokenString);
    }

    public static  void remove(String tokenString) {
        tokens.remove(tokenString);
    }

    /**
     * 判断Toekn状态
     * @param jwtHelper
     * @param obj
     * @param audience
     * @return
     */
    public static Integer checkTokenUtil (JwtHelper jwtHelper, Object obj, Audience audience) {
        TokenSumary tokenSumary = null;
        Integer result = -1;
        //判断传入对象是string还是token
        boolean b = obj instanceof String;
        String tokenString = "";
        if(b){
            tokenString = obj.toString();
            tokenSumary = TokenMap.getToken(tokenString);
        }else {
            try {
                JSONObject js = new JSONObject();
                if (obj instanceof JSONObject) {
                    js = (JSONObject) obj;
                    tokenSumary.setExpires_in(Long.parseLong(js.get("expires_in").toString()));
                    tokenSumary.setAccess_token(js.get("access_token").toString());
                    tokenSumary.setUserMine((UserMine) js.get("userMine"));
                    tokenSumary.setApplyInfo(Boolean.parseBoolean(js.get("applyInfo").toString()));
                    tokenSumary.setToken_type(js.get("token_type").toString());
                    tokenSumary.setRefresh_token(js.get("refresh_token").toString());
                }
//                tokenSumary.setExpires_in(js.)
//                tokenSumary = (TokenSumary) obj;

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        //判断token是否存在
        if(tokenSumary == null){
            return result = -1;
        }

        Claims claims = jwtHelper.parseJWT(tokenString, audience.getBase64Secret());
        //判断是否过期
        long expiration = claims.getExpiration().getTime();
        //当前时间
        long current = System.currentTimeMillis();
        //时间戳
        long second = TimeUnit.MILLISECONDS.toSeconds(expiration - current);
        //如果时间戳小于等于0，证明已经失效
        if (second <= 0) {
            return result = 0;
        }
        tokenSumary.setExpires_in(System.currentTimeMillis());
        TokenMap.setToken(tokenSumary);
        return result = 1;
    }

    /**
     * 通过access_token获取用户ID
     */
    public Integer getUidByToken(String access_token){
        Claims claims = jwtHelper.parseJWT(access_token, audience.getBase64Secret());
        TokenSumary tokenM = TokenCache.getStoreToken(Integer.parseInt(claims.getSubject()), LoginTypeEnum.WEB);
        return tokenM.getUserMine().getId();
    }


}
