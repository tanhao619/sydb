package com.cdyoue.oddJobs.api.author;

import com.cdyoue.oddJobs.dto.Reason;
import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.oauth.ApplyInfoDto;
import com.cdyoue.oddJobs.dto.oauth.RealNameApplyInfo;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by dengshaojun on 2017/5/9.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-20T01:12:00.448Z")

@Api(value = "auth", description = "the auth API")
public interface AuthApi {

    @ApiOperation(value = "发起实名、大咖、导师认证（完成）", notes = "根据登录的用户添加认证认消息", response = HttpMessage.class, tags={ "auth", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "对象没找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/auth",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> auth(@ApiParam(value = "认证类型：realname实名，daka大咖，mentor导师（参数跟在url后面：type=realname daka mentor，三选一） ", required = true, allowableValues = "realname, daka, mentor") @RequestParam(value = "type", required = true) String type,
                                     @ApiParam(value = "认证信息") @RequestBody RealNameApplyInfo realNameApplyInfo);


    @ApiOperation(value = "通过实名、大咖、导师认证（完成）", notes = "更改认证状态", response = HttpMessage.class, tags={ "auth", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "对象没找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/auth/{id}/approve",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> authApprove(@ApiParam(value = "认证类型：realname实名，daka大咖，mentor导师（参数跟在url后面：type=realname daka mentor，三选一） ", required = true, allowableValues = "realname, daka, mentor") @RequestParam(value = "type", required = true) String type,
                                            @ApiParam(value = "认证信息的id",required=true ) @PathVariable("id") Integer id,
                                            @ApiParam(value = "审核原因" ,required=true ) @RequestBody Reason reason);


    @ApiOperation(value = "拒绝实名、大咖、导师认证（完成）", notes = "更改认证状态", response = HttpMessage.class, tags={ "auth", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "对象没找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/auth/{id}/reject",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> authReject(@ApiParam(value = "认证类型：realname实名，daka大咖，mentor导师（参数跟在url后面：type=realname daka mentor，三选一） ", required = true, allowableValues = "realname, daka, mentor") @RequestParam(value = "type", required = true) String type,
                                           @ApiParam(value = "认证信息的id",required=true ) @PathVariable("id") Integer id,
                                           @ApiParam(value = "审核原因" ,required=true ) @RequestBody Reason reason);

    @ApiOperation(value = "编辑实名认证（新增，完成）", notes = "更新实名认证信息", response = HttpMessage.class, tags={ "auth", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "对象没找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/auth/{id}",
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> editApplyInfo(@ApiParam(value = "认证信息的id",required=true ) @PathVariable("id") Integer id,
                                              @ApiParam(value = "认证信息",required=true ) @RequestBody RealNameApplyInfo realNameApplyInfo);


    @ApiOperation(value = "获取当前登陆人认证信息", notes = "获取当前登陆人认证信息", response = HttpMessage.class, tags={ "auth", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = ApplyInfoDto.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "对象没找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/auth/applyInfo/{type}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<ApplyInfoDto> getApplyInfo(@ApiParam(value = "认证类型:realname实名，daka大咖，mentor导师（", allowableValues = "realname, daka, mentor") @PathVariable(value = "type", required = true) String type);



    @ApiOperation(value = "获取认证信息", notes = "获取认证信息", response = HttpMessage.class, tags={ "auth", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = ApplyInfoDto.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "对象没找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/auth/identification/{id}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<ApplyInfoDto>> getIdentificationInfo(@ApiParam(value = "用户id") @PathVariable(value = "id", required = true) Integer id);

    @ApiOperation(value = "查看审核通过的信息弹窗，改变状态", notes = "更新查看状态为已查看", response = HttpMessage.class, tags={ "auth", })
    @RequestMapping(value = "/auth/{id}/look",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> updApplyInfoLook(@ApiParam(value = "认证信息的id", required=true) @PathVariable("id") Integer id);


}
