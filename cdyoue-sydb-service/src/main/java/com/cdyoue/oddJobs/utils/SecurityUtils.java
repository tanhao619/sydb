package com.cdyoue.oddJobs.utils;


import com.cdyoue.oddJobs.dao.lqsq.UserResponsitory;
import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.oauth.TokenSumary;
import com.cdyoue.oddJobs.en.LoginTypeEnum;
import com.cdyoue.oddJobs.utils.jwt.Audience;
import com.cdyoue.oddJobs.utils.jwt.JwtHelper;
import com.cdyoue.oddJobs.utils.jwt.TokenCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * Utility class for Spring Security.
 */
public final class SecurityUtils {
    @Autowired
    private static JwtHelper jwtHelper;
    @Autowired
    private static Audience audience;

    private  static final ThreadLocal threadLocal = new ThreadLocal();
    private SecurityUtils() {
    }
    /**
     * Get the login of the current user.
     *
     * @return the login of the current user
     */
    public static UserMine getCurrentUserLogin() {
//        UserMine userMine = (UserMine) threadLocal.get();
//        return (UserMine)threadLocal.get();
        try {
            UserMine userMine = (UserMine) threadLocal.get();
            if (userMine != null) {
                return userMine;
            } else {
                UserMine user = new UserMine();
                HttpServletRequest request = SysContent.getRequest();
                String header = request.getHeader("Authorization");
                String token = request.getHeader("Authorization").substring(3, header.length()).trim();
                Claims claims = jwtHelper.parseJWT(token, audience.getBase64Secret());
                TokenSumary tokenM = TokenCache.getStoreToken(Integer.parseInt(claims.getSubject()), LoginTypeEnum.WEB);
                return tokenM.getUserMine();
            }
        }catch (Exception e){
            return null;
        }

    }

//    public static UserMine getUserBackLogin() {
//        UserMine userMine = new UserMine();
//        userMine.setId(6);
//        userMine.setName("17600000000");
//        userMine.setRole(2);
//        userMine.setUserType(0);
//        return userMine;
//    }
//
//    public static UserMine getUserFrontLogin() {
//        UserMine userMine = new UserMine();
//        userMine.setId(5);
//        userMine.setName("15199090067");
//        userMine.setRole(1);
//        userMine.setUserType(1);
//        return userMine;
//    }

    public static void setCurrentUserLogin(UserMine user) {
        threadLocal.set(user);
    }

    public static void removeCatch() {
        threadLocal.remove();
    }
}
