package com.cdyoue.oddJobs.error;

import com.cdyoue.oddJobs.dto.oauth.ResultMsg;
import com.cdyoue.oddJobs.dto.oauth.ResultStatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by liaoyoule on 2017/5/4.
 */
@RestController
@ApiIgnore
public class ErrorStatusController {
    @PostMapping("invalid/invalidAuthentication")
    public ResponseEntity<ResultMsg> invalidAuthenticationPOST(){
        ResultMsg resultMsg = new ResultMsg(ResultStatusCode.INVALID_AUTHENTICATION.getCode(), ResultStatusCode.INVALID_AUTHENTICATION.getError(), ResultStatusCode.INVALID_AUTHENTICATION.getError_description());
        return new ResponseEntity<ResultMsg>(resultMsg, HttpStatus.UNAUTHORIZED);
    }


    @GetMapping("invalid/invalidAuthentication")
    public ResponseEntity<ResultMsg> invalidAuthenticationGET(){
        ResultMsg resultMsg = new ResultMsg(ResultStatusCode.INVALID_AUTHENTICATION.getCode(), ResultStatusCode.INVALID_AUTHENTICATION.getError(), ResultStatusCode.INVALID_AUTHENTICATION.getError_description());
        return new ResponseEntity<ResultMsg>(resultMsg, HttpStatus.UNAUTHORIZED);
    }
    @PutMapping("invalid/invalidAuthentication")
    public ResponseEntity<ResultMsg> invalidAuthenticationPUT(){
        ResultMsg resultMsg = new ResultMsg(ResultStatusCode.INVALID_AUTHENTICATION.getCode(), ResultStatusCode.INVALID_AUTHENTICATION.getError(), ResultStatusCode.INVALID_AUTHENTICATION.getError_description());
        return new ResponseEntity<ResultMsg>(resultMsg, HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("invalid/invalidAuthentication")
    public ResponseEntity<ResultMsg> invalidAuthenticationDELETE(){
        ResultMsg resultMsg = new ResultMsg(ResultStatusCode.INVALID_AUTHENTICATION.getCode(), ResultStatusCode.INVALID_AUTHENTICATION.getError(), ResultStatusCode.INVALID_AUTHENTICATION.getError_description());
        return new ResponseEntity<ResultMsg>(resultMsg, HttpStatus.UNAUTHORIZED);
    }

}
