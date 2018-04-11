package com.cdyoue.oddJobs.api.lgfc;



import java.util.List;

import com.cdyoue.oddJobs.dto.lgfc.*;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cdyoue.oddJobs.dto.common.HttpMessage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-30T14:30:56.295Z")

@Api(value = "topics", description = "the topics API")
public interface TopicsApi {

    @ApiOperation(value = "给指定的话题创建问题(完成)", notes = "给指定的话题创建问题", response = HttpMessage.class, tags={ "questions", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "创建成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误.", response = HttpMessage.class) })
    @RequestMapping(value = "/topics/{topicid}/questions",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> createQuestion4Topic(@ApiParam(value = "问题id",required=true ) @PathVariable("topicid") Integer topicid,
        @ApiParam(value = "问题实体信息" ,required=true ) @RequestBody QuestionRequest question);


    @ApiOperation(value = "创建话题(完成)", notes = "只有管理员才可以创建话题", response = HttpMessage.class, tags={ "topics", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "创建成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误.", response = HttpMessage.class) })
    @RequestMapping(value = "/topics",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> createTopic(@ApiParam(value = "话题实体信息" ,required=true ) @RequestBody TopicRequest topicRequest);


    @ApiOperation(value = "删除话题(完成)", notes = "根据id删除话题", response = HttpMessage.class, tags={ "topics", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "话题没找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/topics/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteTopicById(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "关注话题(完成)", notes = "根据id关注话题", response = HttpMessage.class, tags={ "topics", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "对象没找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/topics/{id}/follow",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> followTopicById(@ApiParam(value = "话题id",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "获取热门的话题(完成)", notes = "获取热门的话题", response = TopicMini.class, responseContainer = "List", tags={ "topics", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = TopicMini.class),
        @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "话题没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/topics/hots",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<TopicMini>> getHotTopics();


    @ApiOperation(value = "获取我关注的所有的话题(完成)", notes = "获取我关注的所有的话题", response = Topic.class, responseContainer = "List", tags={ "topics", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = Topic.class),
        @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "话题没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/topics/my/follows",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PageInfo<Topic>> getMyTopics( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
         @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
         @ApiParam(value = "排序字段和方式 例如：/topics?sort=createTime",defaultValue = "-createTime", allowableValues = "follow, -createTime") @RequestParam(value = "sort", required = false) String sort);


    @ApiOperation(value = "获取话题详情(完成)", notes = "根据id获取话题详情", response = TopicMini.class, responseContainer = "List", tags={ "topics", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = TopicMini.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "对象没找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/topics/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<TopicMini> getTopicById(@ApiParam(value = "话题id",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "获取所有的话题(完成)", notes = "获取所有的话题", response = Topic.class, responseContainer = "List", tags={ "topics", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = Topic.class),
        @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "话题没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/topics",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PageInfo<Topic>>  getTopics(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                               @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
                                               @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                               @ApiParam(value = "排序字段和方式 例如：/topics?sort=createTime，默认按照创建时间排序。follow = 关注度最高的话题", allowableValues = "follow, createTime") @RequestParam(value = "sort", required = false) String sort);


    @ApiOperation(value = "取消关注话题(完成)", notes = "根据id取消关注话题", response = HttpMessage.class, tags={ "topics", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "对象没找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/topics/{id}/unfollow",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> unFollowTopicById(@ApiParam(value = "话题id",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "编辑话题（完成）", notes = "编辑话题", response = HttpMessage.class, tags={ "topics", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "话题没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/topics/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updateTopic(@ApiParam(value = "",required=true ) @PathVariable("id") String id,
        @ApiParam(value = "话题对象,时间格式请按照'2017-02-04 12:12:12'这种格式填写" ,required=true ) @RequestBody TopicRequest topicRequest);

}
