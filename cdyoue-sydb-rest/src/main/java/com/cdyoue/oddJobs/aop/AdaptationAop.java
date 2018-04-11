package com.cdyoue.oddJobs.aop;

import com.cdyoue.oddJobs.config.ImageAdaptation;
import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.common.ResponseResult;
import com.cdyoue.oddJobs.en.StatusEnum;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Created by liaoyoule on 2017/5/5.
 */
@Aspect
@Component
public class AdaptationAop {
    @Autowired
    private ImageAdaptation imageAdaptation;

    @Pointcut("execution(* com.cdyoue.oddJobs.api..*(..))")
    public void pointCut() {
        return;
    }





    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object proceed = point.proceed();
        if (proceed instanceof ResponseEntity) {
            ResponseEntity re = (ResponseEntity)proceed;
            Object body = imageAdaptation.convertImage(re);
//            Object body = re.getBody();
            HttpStatus responsCode = re.getStatusCode();
            ResponseResult responseResult = new ResponseResult();
            if (responsCode.equals(HttpStatus.OK) || responsCode.equals(HttpStatus.CREATED)) {
                responseResult.setCode(20);
                responseResult.setStatus(StatusEnum.SUCCESS);
                String message = body instanceof HttpMessage ? ((HttpMessage) body).getMsg() : "操作成功";
                String des = body instanceof HttpMessage ? ((HttpMessage) body).getDescription() : "操作成功";
                responseResult.setMessage(message);
                responseResult.setDescription(des);
                responseResult.setResponse(body);
                return new ResponseEntity(responseResult, HttpStatus.OK);
            }

            if (body instanceof HttpMessage) {
                HttpMessage hm = (HttpMessage) body;
                responseResult.setCode(hm.getCode());
                responseResult.setMessage(hm.getMsg());
                responseResult.setDescription(hm.getDescription());
                responseResult.setStatus(StatusEnum.FAIL);
                return new ResponseEntity(responseResult, HttpStatus.OK);
            }
        }
        return proceed;
    }


}
