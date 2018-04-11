package com.cdyoue.oddJobs.api.other;

import com.cdyoue.oddJobs.dto.common.AppMsgResponse;
import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.common.MessageBody;
import com.cdyoue.oddJobs.dto.common.ResponseDetail;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by tr on 2017/6/8.
 */
public interface AppMessageApi {
    @ApiOperation(value = "APP端专用 - 每种消息未读数量(完成)", notes = "APP端专用 - 每种消息未读数量", response = AppMsgResponse.class,responseContainer = "List", tags={ "appMessage", })
    @ApiResponses(value = {
    @ApiResponse(code = 200, message = "操作成功", response = AppMsgResponse.class),
            @ApiResponse(code = 400, message = "Returned if the request is invalid.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "Returned if the user is not authenticated.", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "Returned if the caller user does not have permission", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "demands not found", response = String.class),
            @ApiResponse(code = 500, message = "Returned if the user was not created because of other error.", response = HttpMessage.class)})
    @RequestMapping(value = "/unreadAppMessage",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity getUnreadAppMessage();

    @ApiOperation(value = "APP端专用 - 获取未读消息列表页面(完成)", notes = "APP端专用 - 获取未读消息列表页面", response = ResponseDetail.class, responseContainer = "List", tags={ "appMessage", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response  = ResponseDetail.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "页面没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/appMessage/{msgType}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity  getUnreadMsgSummary(
                                            @ApiParam(value = "消息类别：1审核，2收到邀请，3关注通知，4需求通知，5官方通知", required = true) @PathVariable("msgType") Integer msgType,
                                            @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                            @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber);

    @ApiOperation(value = "APP端专用 - 查看消息改变已读状态(完成)", notes = "APP端专用 - 消息已读状态", response = HttpMessage.class, tags={ "appMessage", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "需求没找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/appMessage/{id}/read",
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> msgStatusRead(
            @ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
            @ApiParam(value = "事件Id 即eventId", required = true) @RequestParam(value = "eventId", required = false) Integer eventId,
            @ApiParam(value = "设置问题replyId,用于改变查看状态,用户关注的问题有回答，即type=3的时候更新lookStatus状态", required = true) @RequestParam(value = "quesReplyId", required = false) Integer quesReplyId,
            @ApiParam(value = "-1 其他 1 关注的能人的发布的文章 2 关注的能人发布的问题  3 关注的问题有新的回答", required = true) @RequestParam(value = "type", required = false) Integer type);

    @ApiOperation(value = "APP端专用 - 未读消息全部置为已读状态(完成)", notes = "APP端专用 - 未读消息全部置为已读状态", response = HttpMessage.class, tags={ "appMessage", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "需求没找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/appMessage/{msgType}/readAll",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> allMsgStatusReadByType(@ApiParam(value = "消息类别：1审核，2收到邀请，3关注通知，4需求通知，5官方通知",required=true ) @PathVariable("msgType") Integer msgType);
}
