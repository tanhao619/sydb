package com.cdyoue.oddJobs.aop;

import com.cdyoue.oddJobs.annotion.authentication.Login;
import com.cdyoue.oddJobs.annotion.authentication.Role;
import com.cdyoue.oddJobs.constant.CustomConstant;
import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.common.message.AuthenticationMessage;
import com.cdyoue.oddJobs.dto.oauth.LoginPara;
import com.cdyoue.oddJobs.dto.oauth.TokenSumary;
import com.cdyoue.oddJobs.en.LoginTypeEnum;
import com.cdyoue.oddJobs.event.EventRequest;
import com.cdyoue.oddJobs.event.EventTrackingRemote;
import com.cdyoue.oddJobs.exception.UnAuthenticationMessageException;
import com.cdyoue.oddJobs.service.UserLogHistoryService;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.oddJobs.utils.SysContent;
import com.cdyoue.oddJobs.utils.jwt.Audience;
import com.cdyoue.oddJobs.utils.jwt.JwtHelper;
import com.cdyoue.oddJobs.utils.jwt.TokenCache;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Created by liaoyoule on 2017/4/26.
 */
@Aspect
@Component
public class HTTPAuthorizeAttributeAop {
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    private EventTrackingRemote eventTrackingRemote;

    @Autowired
    private Audience audience;
    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserLogHistoryService userLogHistoryService;
    @Autowired
    LoggerAop loggerAop;
    @Pointcut("execution(public * com.cdyoue.oddJobs.api..*.*(..))")
    private void apiPointcut() {
    }


    @Before("apiPointcut()")
    private void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest hsr = attributes.getRequest();
        SysContent.setRequest(hsr);
        String servletPath = hsr.getServletPath();
        loggerAop.doBefore(joinPoint);
        if (!StringUtils.isEmpty(hsr.getQueryString()) && hsr.getQueryString().contains("q=")) {
            servletPath = new StringBuffer(servletPath).append("?q=").toString();
        }
        doIdentification(hsr);
        doAuthentication(joinPoint);
        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();

        //埋点服务
        EventRequest er = null;
        if("/login".equals(servletPath)){
            for (int i = 0; i< joinPoint.getArgs().length; i++) {
                Object o = joinPoint.getArgs()[i];
                if(joinPoint.getArgs()[i] instanceof LoginPara){
                    LoginPara loginPara = (LoginPara) joinPoint.getArgs()[i];
                    er = new EventRequest(servletPath, hsr.getMethod(), currentUserLogin == null ? null :
                            currentUserLogin.getId(),hsr.getQueryString(), loginPara.getUserName());
                }
            }
        }else {
            er = new EventRequest(servletPath, hsr.getMethod(), currentUserLogin == null ? null :
                    currentUserLogin.getId(), hsr.getQueryString());
        }
        eventTrackingRemote.transferEvent(er);

    }


    /**
     * 权限控制
     *
     * @param joinPoint
     */
    public void doAuthentication(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        Optional<Login> loginOptional = Optional.ofNullable(targetMethod.getAnnotation(Login.class));


        if (loginOptional.isPresent()) {
            UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();
            if (currentUserLogin == null) {

                throw new UnAuthenticationMessageException(AuthenticationMessage.USERUNLOGIN);
            }
        }


        Optional<Role> roleOptional = Optional.ofNullable(targetMethod.getAnnotation(Role.class));

        if (roleOptional.isPresent()) {
            int value = roleOptional.get().value();
            UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();
            if (currentUserLogin == null || currentUserLogin.getRole() != value) {
                throw new UnAuthenticationMessageException(AuthenticationMessage.AUTHENTICATIONFAILBYROLE);
            }

        }

    }


    /**
     * 登录认证
     *
     * @param hsr
     */
    private void doIdentification(HttpServletRequest hsr) {
        String requestURI = hsr.getRequestURI();
        String urlSufix = requestURI.substring(requestURI.lastIndexOf("/")+1);
        SecurityUtils.removeCatch();
        //排除ueditor上传
        String[] filterRegex = new String[]{"resetting","uploadimage", "login", "loginback", "register", "active", "refreshtoken", "telregister"};

        List<String> filterRegexs = Arrays.asList(filterRegex);

        if ((filterRegexs.contains(urlSufix.toLowerCase()))) {
            return;
        }

        String header = hsr.getHeader("Authorization") == null ? hsr.getHeader("api_key") : hsr.getHeader("Authorization");

        //如果没有header信息
        UserMine um = null;
        try {
            if (StringUtils.isBlank(header) || header.length() < 3) {
                throw new UnAuthenticationMessageException(AuthenticationMessage.USERUNLOGIN);
            }
            String HeadStr = header.substring(0, 3).toLowerCase();
            String token = header.substring(3, header.length()).trim();
            LoginTypeEnum loginType = HeadStr.equalsIgnoreCase("web") ? LoginTypeEnum.WEB : HeadStr.equalsIgnoreCase("app") ? LoginTypeEnum.APP : null;
            Claims claims = jwtHelper.parseJWT(token, audience.getBase64Secret());
            if (loginType == null || !claims.getIssuer().equals(CustomConstant.TOKEN_ISSUER)) {
                throw new UnAuthenticationMessageException(AuthenticationMessage.INVALIDISSUREY);
            }
            TokenSumary tokenM = TokenCache.getStoreToken(Integer.parseInt(claims.getSubject()), loginType);

            if (tokenM == null) {
                tokenM = userLogHistoryService.getStoreToken(Integer.parseInt(claims.getSubject()), loginType);
            }

            if (tokenM == null || !tokenM.getAccess_token().equals(token)) {
                throw new UnAuthenticationMessageException(AuthenticationMessage.INVALIDTOKEN);
            }

            //判断是否过期
            long expiration = claims.getExpiration().getTime();
            //当前时间
            long current = System.currentTimeMillis();
            //时间戳
            long second = TimeUnit.MILLISECONDS.toSeconds(expiration - current);
            //如果时间戳小于等于0，证明已经失效
            if (second <= 0 && "web".equalsIgnoreCase(loginType.getLoginType())) {
                throw new UnAuthenticationMessageException(AuthenticationMessage.TIMEOUT);
            }
            UserMine userMine = tokenM.getUserMine();
            SecurityUtils.setCurrentUserLogin(userMine);
        } catch (UnAuthenticationMessageException e) {
            if ("get".equalsIgnoreCase(hsr.getMethod())) {
                return;
            } else {
                loggerAop.doRecoveryActions(e);
                throw e;
            }
        }
    }
}
