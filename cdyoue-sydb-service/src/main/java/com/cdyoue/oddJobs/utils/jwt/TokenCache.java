package com.cdyoue.oddJobs.utils.jwt;

import com.cdyoue.oddJobs.dto.captch.AccountCaptch;
import com.cdyoue.oddJobs.dto.captch.CaptchMemory;
import com.cdyoue.oddJobs.dto.oauth.TokenSumary;
import com.cdyoue.oddJobs.en.AccountTypeEnum;
import com.cdyoue.oddJobs.en.LoginTypeEnum;

import java.util.Hashtable;

/**
 * Created by liaoyoule on 2017/5/8.
 */
public class TokenCache {
    //内存存放tokens，不需要每次都从数据库读取，每次生成token自动更新内存token的值
    private static final Hashtable<Integer, TokenSumary> MEMORY_APP_TOKENS = new Hashtable<>();
    private static final Hashtable<Integer, TokenSumary> MEMORY_CLIENT_TOKENS = new Hashtable<>();
    private static final Hashtable<Long, AccountCaptch> MEMORY_TEL_CAPTCH = new Hashtable<>();
    private static final Hashtable<String, AccountCaptch> MEMORY_EMAIL_CAPTCH = new Hashtable<>();
    private static final Hashtable<Integer, CaptchMemory> MEMORY_RESET_CAPTCH = new Hashtable<>();


    public static Hashtable<Integer, TokenSumary> getMemoryAppTokens() {
        return MEMORY_APP_TOKENS;
    }

    public static Hashtable<Integer, TokenSumary> getMemoryClientTokens() {
        return MEMORY_CLIENT_TOKENS;
    }

    public static Hashtable<Long, AccountCaptch> getMemoryTelCaptch() {
        return MEMORY_TEL_CAPTCH;
    }

    public static Hashtable<String, AccountCaptch> getMemoryEmailCaptch() {
        return MEMORY_EMAIL_CAPTCH;
    }

    public static Hashtable<Integer, CaptchMemory> getMemoryResetCaptch() {
        return MEMORY_RESET_CAPTCH;
    }





    public static void unbindCaptchStore(String key, AccountCaptch captch, AccountTypeEnum type) {
        switch (type) {
            case TEL:
                MEMORY_TEL_CAPTCH.put(Long.parseLong(key), captch);
                break;
            case EMAIL:
                MEMORY_EMAIL_CAPTCH.put(key, captch);
                break;
        }
    }

    public static void telCaptchStore(long tel, AccountCaptch accountCaptch) {
        MEMORY_TEL_CAPTCH.put(tel, accountCaptch);
    }

    public static void emailCaptchStore(String email, AccountCaptch accountCaptch) {
        MEMORY_EMAIL_CAPTCH.put(email, accountCaptch);
    }



    public static void resetCaptchStore(int key,CaptchMemory memory) {
        MEMORY_RESET_CAPTCH.put(key, memory);
    }



    public static CaptchMemory getResetCaptch(int key) {
       return MEMORY_RESET_CAPTCH.get(key);
    }


    public static void resetCaptchRemove(int key) {
         MEMORY_RESET_CAPTCH.remove(key);
    }



    /**
     * 账号设置验证码 获取
     *
     * @param key
     * @param type
     * @return
     */
    public static AccountCaptch getUnbindCaptch(String key, AccountTypeEnum type) {
        switch (type) {
            case TEL:
                return MEMORY_TEL_CAPTCH.get(Long.parseLong(key));
            case EMAIL:
                return MEMORY_EMAIL_CAPTCH.get(key);
            default:
                return null;
        }
    }


    /**
     * 账号设置验证码 移除
     *
     * @param key
     * @param type
     * @return
     */
    public static void removeUnbindCaptch(String key, AccountTypeEnum type) {
        switch (type) {
            case TEL:
                MEMORY_TEL_CAPTCH.remove(key);
                break;

            case EMAIL:
                MEMORY_EMAIL_CAPTCH.remove(key);
                break;

        }
    }


    public static void tokenStore(Integer key, TokenSumary ts, LoginTypeEnum type) {
        switch (type) {
            case APP:
                MEMORY_APP_TOKENS.put(key, ts);
                break;
            case WEB:
                MEMORY_CLIENT_TOKENS.put(key, ts);
                break;
        }
    }


    public static AccountCaptch getTelCaptch(long tel) {
        return MEMORY_TEL_CAPTCH.get(tel);
    }

    public static AccountCaptch removeTelCaptch(long tel) {
        return MEMORY_TEL_CAPTCH.remove(tel);
    }

    public static void removeStoreToken(Integer key, LoginTypeEnum type) {
        switch (type) {
            case APP:
                MEMORY_APP_TOKENS.remove(key);
                break;

            case WEB:
                MEMORY_CLIENT_TOKENS.remove(key);
                break;

        }
    }

    public static TokenSumary getStoreToken(Integer key, LoginTypeEnum type) {
        switch (type) {
            case APP:
                return MEMORY_APP_TOKENS.get(key);
            case WEB:
                return MEMORY_CLIENT_TOKENS.get(key);
            default:
                return null;
        }
    }

}
