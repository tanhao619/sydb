package com.cdyoue.oddJobs.utils.jwt;

import com.cdyoue.oddJobs.constant.CustomConstant;
import com.cdyoue.oddJobs.dto.common.message.AuthenticationMessage;
import com.cdyoue.oddJobs.dto.oauth.JwtClaim;
import com.cdyoue.oddJobs.dto.oauth.TokenSumary;
import com.cdyoue.oddJobs.en.LoginTypeEnum;
import com.cdyoue.oddJobs.exception.InvalidException;
import com.cdyoue.oddJobs.exception.UnAuthenticationMessageException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * Created by m2m on 2017/3/27.
 *
 */
@Component
public class JwtHelper {
    @Autowired
    private Audience audience;
    public  Claims parseJWT(String jsonWebToken,String base64Security){
        try {

            Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        }catch (Exception e){
            throw new UnAuthenticationMessageException(AuthenticationMessage.INVALIDTOKEN);
        }
    }




    public  TokenSumary createJWT(String subjec , JwtClaim jc, String base64Security){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillt = System.currentTimeMillis();
        //生成签名密匙
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes,signatureAlgorithm.getJcaName());
        //添加构成JWT的参数
        JwtBuilder tokenbuilder = Jwts.builder()
                .setSubject(subjec)
                .setHeaderParam("typ","JWT")
                .claim("info",jc)
                .setIssuer(CustomConstant.TOKEN_ISSUER)
                .setAudience(subjec)
                .signWith(signatureAlgorithm,signingKey);

        JwtBuilder fTokenbuilder = Jwts.builder()
                .setSubject(subjec)
                .setHeaderParam("typ","JWT")
                .claim("info",jc)
                .setIssuer(CustomConstant.REFRESH_TOKEN_ISSUER)
                .setAudience(subjec)
                .signWith(signatureAlgorithm,signingKey);
        //添加token过期时间
        long expMills = 0;

        int TTLMills = audience.getExpiresSecond() * 1000;
        Date now = new Date(nowMillt);
        if (TTLMills >=0 ){
            expMills = nowMillt + TTLMills;
            Date exp = new Date(expMills);
            tokenbuilder.setExpiration(exp).setNotBefore(now);
        }

        long fExpMills = 0;
        if (TTLMills >=0 ){
            fExpMills = nowMillt + TTLMills+60*1000;
            Date fExp = new Date(fExpMills);
            fTokenbuilder.setExpiration(fExp).setNotBefore(now);
        }


        //生成JWT
        TokenSumary ts = new TokenSumary();
        ts.setAccess_token(tokenbuilder.compact());
        ts.setRefresh_token(fTokenbuilder.compact());
        ts.setExpires_in(expMills);
        ts.setToken_type(jc.getTokenType().getLoginType());
        //存储在内存中
        TokenCache.tokenStore(Integer.parseInt(subjec),ts,jc.getTokenType());
        return ts;
    }
}
