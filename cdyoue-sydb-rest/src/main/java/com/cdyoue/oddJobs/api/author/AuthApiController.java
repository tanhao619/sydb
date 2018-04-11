package com.cdyoue.oddJobs.api.author;

import com.cdyoue.oddJobs.dto.Reason;
import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.common.message.AuthMessage;
import com.cdyoue.oddJobs.dto.oauth.ApplyInfoDto;
import com.cdyoue.oddJobs.dto.oauth.RealNameApplyInfo;
import com.cdyoue.oddJobs.en.ApplyTypeEnum;
import com.cdyoue.oddJobs.en.MessageEventTypeEnum;
import com.cdyoue.oddJobs.en.MessageMsgTypeEnum;
import com.cdyoue.oddJobs.entity.lgsq.PortalRealNameInfoEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import com.cdyoue.oddJobs.service.AuthService;
import com.cdyoue.oddJobs.service.RealNameInfoService;
import com.cdyoue.oddJobs.service.UserService;
import com.cdyoue.oddJobs.utils.MessageUtils;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-30T14:30:56.295Z")

@Controller
public class AuthApiController implements AuthApi {

    @Autowired
    private AuthService authService;

    @Autowired
    private RealNameInfoService realNameInfoService;

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<HttpMessage> auth(@ApiParam(value = "认证类型：realname实名，daka大咖，mentor导师（参数跟在url后面：type=realname daka mentor，三选一） ", required = true, allowableValues = "realname, daka, mentor") @RequestParam(value = "type", required = true) String type,
                                            @ApiParam(value = "认证信息") @RequestBody RealNameApplyInfo realNameApplyInfo) {

        
    	UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();
        Integer userid = currentUserLogin.getId();
        Integer usertype = currentUserLogin.getUserType();
        if (type.equals("realname")) {
            if (usertype == 0) { //0个人，1企业
                //MessageUtils.isMessageExist(userid, );
                Boolean isApply = realNameInfoService.existByUseridAndApplytype(userid, ApplyTypeEnum.REALNAME);
                if (isApply) {
                    return new ResponseEntity<HttpMessage>(AuthMessage.CREATEAPPLTINFOFAILD400, HttpStatus.BAD_REQUEST);
                }
                authService.saveRealNameInfo(userid, realNameApplyInfo, ApplyTypeEnum.REALNAME);
                return new ResponseEntity<HttpMessage>(AuthMessage.CREATEAPPLTINFOSUCCESS, HttpStatus.CREATED);
            } else if (usertype == 1) {
                Boolean isApply = realNameInfoService.existByUseridAndApplytype(userid, ApplyTypeEnum.REALNAME);
                if (isApply) {
                    return new ResponseEntity<HttpMessage>(AuthMessage.CREATEAPPLTINFOFAILD400, HttpStatus.BAD_REQUEST);
                }
                authService.saveRealNameInfo(userid, realNameApplyInfo, ApplyTypeEnum.REALNAME);
                return new ResponseEntity<HttpMessage>(AuthMessage.CREATEAPPLTINFOSUCCESS, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<HttpMessage>(AuthMessage.CREATEAPPLTINFOFAILD403, HttpStatus.FORBIDDEN);
            }
        } else if (type.equals("daka")) {
            realNameApplyInfo = null;
            if (usertype == 0 ) { //0个人，1企业
                Boolean isApply = realNameInfoService.existByUseridAndApplytype(userid, ApplyTypeEnum.DAKA);
                if (isApply) {
                    return new ResponseEntity<HttpMessage>(AuthMessage.CREATEAPPLTINFOFAILD400, HttpStatus.BAD_REQUEST);
                }
                authService.saveRealNameInfo(userid, realNameApplyInfo, ApplyTypeEnum.DAKA);
                return new ResponseEntity<HttpMessage>(AuthMessage.CREATEAPPLTINFOSUCCESS, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<HttpMessage>(AuthMessage.CREATEAPPLTINFOFAILD403, HttpStatus.FORBIDDEN);
            }
        } else if (type.equals("mentor")) {
            realNameApplyInfo = null;
            if (usertype == 0) { //0个人，1企业
                Boolean isApply = realNameInfoService.existByUseridAndApplytype(userid, ApplyTypeEnum.MENTOR);
                if (isApply) {
                    return new ResponseEntity<HttpMessage>(AuthMessage.CREATEAPPLTINFOFAILD400, HttpStatus.BAD_REQUEST);
                }
                authService.saveRealNameInfo(userid, realNameApplyInfo, ApplyTypeEnum.MENTOR);
                return new ResponseEntity<HttpMessage>(AuthMessage.CREATEAPPLTINFOSUCCESS, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<HttpMessage>(AuthMessage.CREATEAPPLTINFOFAILD403, HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity<HttpMessage>(AuthMessage.APPLTINFOFAILD400, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<HttpMessage> authApprove(@ApiParam(value = "认证类型：realname实名，daka大咖，mentor导师（参数跟在url后面：type=realname daka mentor，三选一） ", required = true, allowableValues = "realname, daka, mentor") @RequestParam(value = "type", required = true) String type,
                                                   @ApiParam(value = "认证信息的id",required=true ) @PathVariable("id") Integer id,
                                                   @ApiParam(value = "审核原因" ,required=true ) @RequestBody Reason reason) {

        PortalRealNameInfoEntity portalRealNameInfoEntity = realNameInfoService.findById(id);
        UserEntity userEntity = userService.findOne(portalRealNameInfoEntity.getUserId());
        Integer usertype = userEntity.getUserType();
        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();
        Integer userrole = currentUserLogin.getRole();
        //角色：0个人用户 1 企业用户2超级管理员
        if (userrole != 2) {
            return new ResponseEntity<HttpMessage>(AuthMessage.APPROVEAPPLTINFOFAILD403, HttpStatus.FORBIDDEN);
        }
        if (type.equals("realname")) {
            if (usertype == 0) { //0个人，1企业
                authService.authApprove(id, reason);
                // 判断有消息否，有就不加
//                Boolean isExist = MessageUtils.isMessageExist(portalRealNameInfoEntity.getUserId(), id, MessageEventTypeEnum.AUDIT, MessageMsgTypeEnum.AuditCertificationPersonRealname);
//                if (!isExist) {
                    MessageUtils.createAuditMessage(portalRealNameInfoEntity.getUserId(), id, MessageEventTypeEnum.AUDIT, MessageMsgTypeEnum.AuditCertificationPersonRealname,"1");
//                }
                return new ResponseEntity<HttpMessage>(AuthMessage.REVIEWAPPLTINFOSUCCESS, HttpStatus.CREATED);
            } else if (usertype == 1) {
                authService.authApprove(id, reason);
                // 判断有消息否，有就不加
//                Boolean isExist = MessageUtils.isMessageExist(portalRealNameInfoEntity.getUserId(), id, MessageEventTypeEnum.AUDIT, MessageMsgTypeEnum.AuditCertificationEnterRealname);
//                if (!isExist) {
                    MessageUtils.createAuditMessage(portalRealNameInfoEntity.getUserId(), id, MessageEventTypeEnum.AUDIT, MessageMsgTypeEnum.AuditCertificationEnterRealname,"1");
//                }
                return new ResponseEntity<HttpMessage>(AuthMessage.REVIEWAPPLTINFOSUCCESS, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<HttpMessage>(AuthMessage.REVIEWATEAPPLTINFOFAILD403, HttpStatus.FORBIDDEN);
            }
        } else if (type.equals("daka")) {
            if (usertype == 0 ) { //0个人，1企业
                authService.authApprove(id, reason);
                // 判断有消息否，有就不加
                Boolean isExist = MessageUtils.isMessageExist(portalRealNameInfoEntity.getUserId(), id, MessageEventTypeEnum.AUDIT, MessageMsgTypeEnum.AuditCertificationPersonDaka);
                if (!isExist) {
                    MessageUtils.createAuditMessage(portalRealNameInfoEntity.getUserId(), id, MessageEventTypeEnum.AUDIT, MessageMsgTypeEnum.AuditCertificationPersonDaka,"1");
                }
                return new ResponseEntity<HttpMessage>(AuthMessage.REVIEWAPPLTINFOSUCCESS, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<HttpMessage>(AuthMessage.REVIEWATEAPPLTINFOFAILD403, HttpStatus.FORBIDDEN);
            }
        } else if (type.equals("mentor")) {
            if (usertype == 0) { //0个人，1企业
                authService.authApprove(id, reason);
                // 判断有消息否，有就不加
//                Boolean isExist = MessageUtils.isMessageExist(portalRealNameInfoEntity.getUserId(), id, MessageEventTypeEnum.AUDIT, MessageMsgTypeEnum.AuditCertificationPersonMentor);
//                if (!isExist) {
                    MessageUtils.createAuditMessage(portalRealNameInfoEntity.getUserId(), id, MessageEventTypeEnum.AUDIT, MessageMsgTypeEnum.AuditCertificationPersonMentor,"1");
//                }
                return new ResponseEntity<HttpMessage>(AuthMessage.REVIEWAPPLTINFOSUCCESS, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<HttpMessage>(AuthMessage.REVIEWATEAPPLTINFOFAILD403, HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity<HttpMessage>(AuthMessage.APPLTINFOFAILD400, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<HttpMessage> authReject(@ApiParam(value = "认证类型：realname实名，daka大咖，mentor导师（参数跟在url后面：type=realname daka mentor，三选一） ", required = true, allowableValues = "realname, daka, mentor") @RequestParam(value = "type", required = true) String type,
                                                  @ApiParam(value = "认证信息的id",required=true ) @PathVariable("id") Integer id,
                                                  @ApiParam(value = "审核原因" ,required=true ) @RequestBody Reason reason) {

        PortalRealNameInfoEntity portalRealNameInfoEntity = realNameInfoService.findById(id);
        UserEntity userEntity = userService.findOne(portalRealNameInfoEntity.getUserId());
        Integer usertype = userEntity.getUserType();
        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();
        Integer userrole = currentUserLogin.getRole();
        //角色：0个人用户 1 企业用户2超级管理员
        if (userrole != 2) {
            return new ResponseEntity<HttpMessage>(AuthMessage.APPROVEAPPLTINFOFAILD403, HttpStatus.FORBIDDEN);
        }
        if (type.equals("realname")) {
            if (usertype == 0) { //0个人，1企业
                authService.authReject(id, reason);
                // 判断有消息否，有就不加
//                Boolean isExist = MessageUtils.isMessageExist(portalRealNameInfoEntity.getUserId(), id, MessageEventTypeEnum.AUDIT, MessageMsgTypeEnum.AuditCertificationPersonRealname);
//                if (!isExist) {
                    MessageUtils.createAuditMessage(portalRealNameInfoEntity.getUserId(), id, MessageEventTypeEnum.AUDIT, MessageMsgTypeEnum.AuditCertificationPersonRealname,"2");
//                }
                return new ResponseEntity<HttpMessage>(AuthMessage.REVIEWAPPLTINFOSUCCESS, HttpStatus.CREATED);
            } else if (usertype == 1) {
                authService.authReject(id, reason);
                // 判断有消息否，有就不加
//                Boolean isExist = MessageUtils.isMessageExist(portalRealNameInfoEntity.getUserId(), id, MessageEventTypeEnum.AUDIT, MessageMsgTypeEnum.AuditCertificationEnterRealname);
//                if (!isExist) {
                    MessageUtils.createAuditMessage(portalRealNameInfoEntity.getUserId(), id, MessageEventTypeEnum.AUDIT, MessageMsgTypeEnum.AuditCertificationEnterRealname,"2");
//                }
                return new ResponseEntity<HttpMessage>(AuthMessage.REVIEWAPPLTINFOSUCCESS, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<HttpMessage>(AuthMessage.REVIEWATEAPPLTINFOFAILD403, HttpStatus.FORBIDDEN);
            }
        } else if (type.equals("daka")) {
            if (usertype == 0 ) { //0个人，1企业
                authService.authReject(id, reason);
                // 判断有消息否，有就不加
//                Boolean isExist = MessageUtils.isMessageExist(portalRealNameInfoEntity.getUserId(), id, MessageEventTypeEnum.AUDIT, MessageMsgTypeEnum.AuditCertificationPersonDaka);
//                if (!isExist) {
                    MessageUtils.createAuditMessage(portalRealNameInfoEntity.getUserId(), id, MessageEventTypeEnum.AUDIT, MessageMsgTypeEnum.AuditCertificationPersonDaka,"2");
//                }
                return new ResponseEntity<HttpMessage>(AuthMessage.REVIEWAPPLTINFOSUCCESS, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<HttpMessage>(AuthMessage.REVIEWATEAPPLTINFOFAILD403, HttpStatus.FORBIDDEN);
            }
        } else if (type.equals("mentor")) {
            if (usertype == 0) { //0个人，1企业
                authService.authReject(id, reason);
                // 判断有消息否，有就不加
//                Boolean isExist = MessageUtils.isMessageExist(portalRealNameInfoEntity.getUserId(), id, MessageEventTypeEnum.AUDIT, MessageMsgTypeEnum.AuditCertificationPersonMentor);
//                if (!isExist) {
                    MessageUtils.createAuditMessage(portalRealNameInfoEntity.getUserId(), id, MessageEventTypeEnum.AUDIT, MessageMsgTypeEnum.AuditCertificationPersonMentor,"2");
//                }
                return new ResponseEntity<HttpMessage>(AuthMessage.REVIEWAPPLTINFOSUCCESS, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<HttpMessage>(AuthMessage.REVIEWATEAPPLTINFOFAILD403, HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity<HttpMessage>(AuthMessage.APPLTINFOFAILD400, HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<HttpMessage> editApplyInfo(@ApiParam(value = "认证信息的id",required=true ) @PathVariable("id") Integer id,
                                                     @ApiParam(value = "认证信息",required=true ) @RequestBody RealNameApplyInfo realNameApplyInfo) {
        authService.updateRealNameInfo(id, realNameApplyInfo);
        return new ResponseEntity<HttpMessage>(AuthMessage.EDITAPPLYINFOSUCCESS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApplyInfoDto> getApplyInfo(@ApiParam(value = "认证类型:realname实名，daka大咖，mentor导师（", allowableValues = "realname, daka, mentor") @PathVariable(value = "type", required = true) String type) {
        ApplyInfoDto appleInfoDto= authService.getApplyInfo(type);
        return new ResponseEntity<ApplyInfoDto>(appleInfoDto,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ApplyInfoDto>> getIdentificationInfo(@ApiParam(value = "用户id") @PathVariable(value = "id", required = true) Integer id) {
        return new ResponseEntity<List<ApplyInfoDto>>(authService.getIdentificationInfo(id),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> updApplyInfoLook(@ApiParam(value = "认证信息的id", required=true) @PathVariable(value = "id", required = true) Integer id) {
        authService.updLook(id);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }
}
