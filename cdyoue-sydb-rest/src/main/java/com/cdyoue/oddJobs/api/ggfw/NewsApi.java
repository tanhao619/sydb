package com.cdyoue.oddJobs.api.ggfw;


import java.util.List;

import com.cdyoue.oddJobs.dto.Reason;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.ggfw.CommunitySummary;
import com.cdyoue.oddJobs.dto.ggfw.NewsDetail;
import com.cdyoue.oddJobs.dto.ggfw.NewsRequest;
import com.cdyoue.oddJobs.dto.ggfw.NewsSummary;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-09T00:59:32.305Z")

@Api(value = "news", description = "the news API")
public interface NewsApi {

    @ApiOperation(value = "通过新闻发布（完成）", notes = "通过新闻发布", response = HttpMessage.class, tags={ "news", })
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "需求没找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/news/{id}/approve",
        produces = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> approveNews(@ApiParam(value = "新闻id",required=true ) @PathVariable("id") Integer id,
                                            @ApiParam(value = "审核原因" ,required=true ) @RequestBody Reason reason);


    @ApiOperation(value = "发布新闻（完成）", notes = "发布新闻，只有企业才能发布新闻", response = HttpMessage.class, tags={ "news", })
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "创建成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误.", response = HttpMessage.class) })
    @RequestMapping(value = "/news",
        produces = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> createNews(@ApiParam(value = "新闻实体信息" ,required=true ) @RequestBody NewsRequest news);


    @ApiOperation(value = "获取我发布的新闻详情（完成）", notes = "获取我发布的新闻详情，只有企业才能获取。", response = NewsSummary.class, responseContainer = "List", tags={ "news", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "操作成功", response = NewsSummary.class),
        @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "页面没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/news/my",
        produces = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<PageInfo<NewsSummary>> getMyNews(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                                    @ApiParam(value = "页码。默认第一页（从0开始）", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber);


    @ApiOperation(value = "获取新闻列表（完成）", notes = "获取新闻列表", response = CommunitySummary.class, responseContainer = "List", tags={ "news", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = CommunitySummary.class),
        @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "页面没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/news",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PageInfo<CommunitySummary>> getNews(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                                       @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
                                                       @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                                       @ApiParam(value = "审核状态：0待审 1审核通过 2审核失败")  @RequestParam(value = "reviewStatus", required = false) Integer reviewStatus);


    @ApiOperation(value = "获取新闻详情（完成）", notes = "获取新闻详情", response = NewsDetail.class, tags={ "news", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "操作成功", response = NewsDetail.class),
        @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "页面没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/news/{id}",
        produces = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<NewsDetail> getNewsById(@ApiParam(value = "新闻id",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "拒绝新闻发布（完成）", notes = "拒绝新闻发布", response = HttpMessage.class, tags={ "news", })
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "需求没找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/news/{id}/reject",
        produces = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> rejectNews(@ApiParam(value = "新闻id",required=true ) @PathVariable("id") Integer id,
                                           @ApiParam(value = "审核原因" ,required=true ) @RequestBody Reason reason);


    @ApiOperation(value = "编辑新闻（完成）", notes = "编辑需求", response = HttpMessage.class, tags={ "news", })
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "需求没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/news/{id}",
        produces = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updateNews(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
        @ApiParam(value = "需求对象" ,required=true ) @RequestBody NewsRequest newsRequest);


    @ApiOperation(value = "删除新闻（完成）", notes = "删除新闻", response = HttpMessage.class, tags={ "news", })
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "需求没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/news/{id}",
        produces = { "application/json" },
        method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteNews(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id);

}
