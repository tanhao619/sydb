package com.cdyoue.oddJobs.api.lgfc;


import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.lgfc.QuestionDetail;
import com.cdyoue.oddJobs.dto.lgfc.ReplyDetails;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-30T14:30:56.295Z")

@Api(value = "replies", description = "the replies API")
public interface RepliesApi {

    @ApiOperation(value = "收藏回复（完成）", notes = "根据id关注问题", response = HttpMessage.class, tags={ "replies", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "对象没找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/replies/{id}/collect",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> collectReplyById(@ApiParam(value = "回复id",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "获取我收藏的回复（完成）", notes = "获取我收藏的回复，默认按照时间倒序", response = QuestionDetail.class, responseContainer = "List", tags={ "replies", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = QuestionDetail.class),
        @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "话题没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/replies/my/collections",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PageInfo<QuestionDetail>> getMyColReplies(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                                             @ApiParam(value = "页码。默认第一页（从0开始）", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="1") Integer pageNumber);


    @ApiOperation(value = "获取用户回答的问题（完成）", notes = "获取用户回答的问题，默认按照时间倒序", response = QuestionDetail.class, responseContainer = "List", tags={ "replies", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = QuestionDetail.class),
        @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "话题没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/replies/user/{userid}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PageInfo<QuestionDetail>> getUserReplies(@ApiParam(value = "用户id",required=true ) @PathVariable("userid") Integer userid,
                                                               @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                                               @ApiParam(value = "页码。默认第一页（0开始）", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="1") Integer pageNumber);


    @ApiOperation(value = "点赞回复（完成）", notes = "根据id点赞问题", response = HttpMessage.class, tags={ "replies", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "对象没找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/replies/{id}/like",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> likeReplyById(@ApiParam(value = "回复id",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "取消收藏回复（完成）", notes = "根据id取消收藏回复", response = HttpMessage.class, tags={ "replies", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "对象没找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/replies/{id}/uncollect",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> unCollectReplyById(@ApiParam(value = "回复id",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "取消点赞回复（完成）", notes = "根据id取消点赞回复", response = HttpMessage.class, tags={ "replies", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "对象没找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/replies/{id}/unlike",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> unLikeReplyById(@ApiParam(value = "回复id",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "获取指定问题回复（新增，完成）", notes = "根据问题id获取回复列表（新增）", response = ReplyDetails.class, responseContainer = "List", tags={ "replies", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = ReplyDetails.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "话题没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/questions/{questionid}/replies",
        produces = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<PageInfo<ReplyDetails>> getReplyByQuestionid(@ApiParam(value = "问题id",required=true ) @PathVariable("questionid") Integer questionid,
                                                                @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                                                @ApiParam(value = "页码。默认第一页（0开始）", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="1") Integer pageNumber);


    @ApiOperation(value = "删除回复（新增，完成）", notes = "根据回复id删除回复", response = HttpMessage.class, tags={ "replies", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "对象没找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/replies/{id}",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteReplyById(@ApiParam(value = "回复id",required=true ) @PathVariable("id") Integer id);
}
