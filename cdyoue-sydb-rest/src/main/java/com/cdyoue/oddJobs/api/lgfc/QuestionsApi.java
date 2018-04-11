package com.cdyoue.oddJobs.api.lgfc;


import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.lgfc.*;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-30T14:30:56.295Z")

@Api(value = "questions", description = "the questions API")
public interface QuestionsApi {
//5-3
    @ApiOperation(value = "创建问题(完成)", notes = "创建问题", response = HttpMessage.class, tags={ "questions", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "创建成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误.", response = HttpMessage.class) })
    @RequestMapping(value = "/questions",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> createQuestion(@ApiParam(value = "问题实体信息" ,required=true ) @RequestBody QuestionRequest question);


    @ApiOperation(value = "为指定的问题创建回复（完成）", notes = "为指定的问题创建回复", response = HttpMessage.class, tags={ "replies", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "创建成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "问题没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误.", response = HttpMessage.class) })
    @RequestMapping(value = "/questions/{questionid}/replies",
        produces = {"application/json"},
//        produces = {MediaType.TEXT_PLAIN_VALUE},
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> createReply4Question(@ApiParam(value = "问题id",required=true ) @PathVariable("questionid") Integer questionid,
                                                     @ApiParam(value = "回复内容信息" ,required=true ) @RequestBody Reply4Question reply4Question);

//5-5
    @ApiOperation(value = "获取我关注的问题列表(完成)", notes = "获取我关注的问题列表，默认按照时间倒序", response = QuestionSummary.class, responseContainer = "List", tags={ "questions", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = QuestionSummary.class),
        @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "问题没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/questions/my/follows",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PageInfo> getMyFolQuestions( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
         @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q);

//5-5
    @ApiOperation(value = "获取回答的问题列表(完成)", notes = "获取回答的问题列表，默认按照时间倒序", response = QuestionSummary.class, responseContainer = "List", tags={ "questions", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = QuestionSummary.class),
        @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "问题没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/questions/my/replies",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PageInfo> getMyQuestionReplies(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                              @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
                                              @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q);

    @ApiOperation(value = "获取我收到的问答邀请列表(完成)", notes = "获取我收到的问答邀请列表，默认按照时间倒序", response = QuestionSummary.class, responseContainer = "List", tags={ "questions", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = QuestionSummary.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "问题没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/questions/my/invite",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<PageInfo> getMyQuestionInvite(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                                  @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
                                                  @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q);
//5-5
    @ApiOperation(value = "获取我发布的问题列表(完成)", notes = "获取我发布的问题列表，默认按照时间倒序", response = QuestionSummary.class, responseContainer = "List", tags={ "questions", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = QuestionSummary.class),
        @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "问题没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/questions/my",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PageInfo> getMyQuestions( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
                                             @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q);

//5-2
    @ApiOperation(value = "获取问题列表(完成)", notes = "获取所有的问题，默认按照时间倒序", response = QuestionSummary.class, responseContainer = "List", tags={ "questions", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = QuestionSummary.class),
        @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "问题没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/questions",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PageInfo> getQuestions(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                          @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
                                          @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q);

//5-4
    @ApiOperation(value = "获取某个用户发布的问题(完成)", notes = "获取某个用户发布的问题，默认按照时间倒序", response = QuestionSummary.class, responseContainer = "List", tags={ "questions", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = QuestionSummary.class),
        @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "问题没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/questions/user/{userid}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PageInfo> getQuestionsByUserId(@ApiParam(value = "用户id",required=true ) @PathVariable("userid") Integer userid,
         @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
         @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q);

//5-5
    @ApiOperation(value = "邀请某用户回答问题(完成)", notes = "id", response = HttpMessage.class, tags={ "questions", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "问题没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/questions/{id}/invite/{userid}",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> inviteAnsQuestion(@ApiParam(value = "用户id",required=true ) @PathVariable("userid") Integer userid,
        @ApiParam(value = "问题id",required=true ) @PathVariable("id") Integer id);
    //5-5
    @ApiOperation(value = "删除问题(完成)", notes = "根据id删除问题", response = HttpMessage.class, tags={ "questions", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "问题没找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/questions/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteQuestionById(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id);

   //5-3
    @ApiOperation(value = "关注问题(完成)", notes = "根据id关注问题", response = HttpMessage.class, tags={ "questions", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "对象没找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/questions/{id}/follow",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> followQuesionById(@ApiParam(value = "问题id",required=true ) @PathVariable("id") Integer id);

//5-5
    @ApiOperation(value = "获取问题详情(完成)", notes = "根据id获取问题详情", response = QuestionDetails.class, tags={ "questions", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = QuestionDetails.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "对象没找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/questions/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<QuestionDetails> getQuestionById(@ApiParam(value = "问题id",required=true ) @PathVariable("id") Integer id);

//5-5
    @ApiOperation(value = "获取推荐问题列表(完成)", notes = "从推荐系统获取推荐的问题，1.5 推荐热门问题", response = QuestionSummary.class, responseContainer = "List", tags={ "questions", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = QuestionSummary.class),
        @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "问题没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/questions/recommands",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PageInfo> getRecQuestions( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
         @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q);

//5-3
    @ApiOperation(value = "获取top问题列表(完成)", notes = "获取top问题列表，1.热门问题，2.最赞回答问题，3.参与的最高问题", response = QuestionMini.class, responseContainer = "List", tags={ "questions", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = QuestionMini.class),
        @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "问题没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/questions/tops",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<QuestionMini>> getTopQuestions(@ApiParam(value = "排序字段和方式 例如：/topics?type=follows，          热门问题top5 - follows;  最赞回答问题top5 - likes; 参与度最高问题top5 - replies", allowableValues = "FOLLOWS, LIKES, REPLIES") @RequestParam(value = "type", required = false) String type);

//5-3
    @ApiOperation(value = "取消关注问题(完成)", notes = "根据id取消关注问题", response = HttpMessage.class, tags={ "questions", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "对象没找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/questions/{id}/unfollow",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> unFollowQuesionById(@ApiParam(value = "问题id",required=true ) @PathVariable("id") Integer id);

//5-4
    @ApiOperation(value = "编辑问题(完成)", notes = "编辑问题", response = HttpMessage.class, tags={ "questions", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "问题没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/questions/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updateQuestion(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
        @ApiParam(value = "问题对象信息" ,required=true ) @RequestBody QuestionRequest question	);

    @ApiOperation(value = "根据话题ID获取问题分页列表(完成)", notes = "获取问题列表，默认按照时间倒序", response = QuestionSummary.class, responseContainer = "List", tags={ "questions", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = QuestionSummary.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "问题没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/topics/{topicid}/questions",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<PageInfo> getMyTopicQuestions(@ApiParam(value = "话题id",required=true ) @PathVariable("topicid") Integer topicid,
                                                @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                                @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber);
}
