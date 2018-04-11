package com.cdyoue.oddJobs.api.ggfw;


import com.cdyoue.oddJobs.dto.ActivityBanner;
import com.cdyoue.oddJobs.dto.ggfw.ActivityRequest;
import com.cdyoue.oddJobs.dto.ggfw.ActivitySummary;
import com.cdyoue.oddJobs.dto.Reason;
import com.cdyoue.oddJobs.dto.zscq.IntellectualTop;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.ggfw.ActivityDetail;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-10T12:31:39.195Z")

@Api(value = "activities", description = "the activities API")
public interface ActivitiesApi {

    @ApiOperation(value = "活动置顶", notes = "活动置顶", response = HttpMessage.class, tags={ "activities", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "著作权信息没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/activities/activityTop",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> activityTop(@ApiParam(value = "",required=true ) @RequestBody IntellectualTop top);

    @ApiOperation(value = "取消活动置顶", notes = "取消活动置顶", response = HttpMessage.class, tags={ "activities", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "著作权信息没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/activities/removeActivityTop/{id}",
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> removeActivityTop(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id);

    @ApiOperation(value = "获取Banner活动列表", notes = "获取Banner活动列表", response = ActivityBanner.class, responseContainer = "List", tags={ "activities", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = ActivityBanner.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class)})
    @RequestMapping(value = "/spaces/activityBanners",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<ActivityBanner>> getActivityBanners();

    @ApiOperation(value = "通过活动发布(完成)", notes = "通过活动发布", response = HttpMessage.class, tags={ "activities", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "需求没找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/activities/{id}/approve",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> approveActivity(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,@ApiParam(value = "原因" ,required=false ) @RequestBody Reason reason);


    @ApiOperation(value = "发布活动(完成)", notes = "发布活动，只有企业才能发布新闻", response = HttpMessage.class, tags={ "activities", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "创建成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误.", response = HttpMessage.class) })
    @RequestMapping(value = "/activities",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> createActivity(@ApiParam(value = "活动实体信息" ,required=true ) @RequestBody ActivityRequest activity);


    @ApiOperation(value = "关注活动(完成)", notes = "关注活动", response = HttpMessage.class, tags={ "activities", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "对象没找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/activities/{id}/follow",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> followActivityById(@ApiParam(value = "活动id",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "获取活动列表(完成)", notes = "获取活动列表", response = ActivitySummary.class, responseContainer = "List", tags={ "activities", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = ActivityDetail.class),
        @ApiResponse(code = 400, message = "请求错误.", response = ActivityDetail.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = ActivityDetail.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = ActivityDetail.class),
        @ApiResponse(code = 404, message = "页面没有找到", response = ActivityDetail.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = ActivityDetail.class) })
    @RequestMapping(value = "/activities",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PageInfo>  getActivities( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                             @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
                                             @ApiParam(value = "排序字段和方式 例如：/sales?sort=publishTime，默认按照发布时间排序。publishTime = 发布时间最新",defaultValue = "-publishTime") @RequestParam(value = "sort", required = false) String sort,
                                             @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                             @ApiParam(value = "审核状态") @RequestParam(value = "s", required = false) String s);


    @ApiOperation(value = "获取活动详情（完成）", notes = "获取活动详情", response = ActivityDetail.class, tags={ "activities", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = ActivityDetail.class),
        @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "页面没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/activityDetail/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<ActivityDetail> getActivityById(@ApiParam(value = "活动id",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "获取我发布的活动(完成)", notes = "获取我发布的活动", response = ActivitySummary.class, responseContainer = "List", tags={ "activities", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = ActivitySummary.class),
        @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "页面没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/activities/my",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PageInfo> getMyActivities(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                             @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
                                             @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q);


    @ApiOperation(value = "获取我关注的活动(完成)", notes = "获取我关注的活动", response = ActivitySummary.class, responseContainer = "List", tags={ "activities", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = ActivitySummary.class),
        @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "页面没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/activities/my/follows",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity getMyFollowActivities( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
         @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q);


    @ApiOperation(value = "拒绝活动发布(完成)", notes = "拒绝活动发布", response = HttpMessage.class, tags={ "activities", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "需求没找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/activities/{id}/reject",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> rejectActivity(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,@ApiParam(value = "原因" ,required=false ) @RequestBody Reason reason);


    @ApiOperation(value = "取消关注活动(完成)", notes = "根据id取消活动问题", response = HttpMessage.class, tags={ "activities", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "对象没找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/activities/{id}/unfollow",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> unFollowActivityById(@ApiParam(value = "活动id",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "编辑活动(完成)", notes = "编辑活动", response = HttpMessage.class, tags={ "activities", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "需求没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/activities/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updateActivity(@ApiParam(value = "",required=true ) @PathVariable("id") String id,
        @ApiParam(value = "活动对象信息" ,required=true ) @RequestBody ActivityRequest activity);

    @ApiOperation(value = "删除活动(完成)", notes = "删除活动", response = HttpMessage.class, tags={ "activities", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "需求没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/activities/{id}",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteActivity(@ApiParam(value = "",required=true ) @PathVariable("id") String id);

    @ApiOperation(value = "批量删除活动", notes = "批量删除活动", response = HttpMessage.class, tags={ "activities", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误.", response = HttpMessage.class)})
    @RequestMapping(value = "/activities/delAll",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteAllActivities(@ApiParam(value = "",required=true ) @RequestParam("ids") Integer[] ids);

}
