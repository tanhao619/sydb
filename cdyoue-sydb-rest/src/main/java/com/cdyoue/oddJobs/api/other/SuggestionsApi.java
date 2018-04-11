package com.cdyoue.oddJobs.api.other;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.other.SuggestionDetail;
import com.cdyoue.oddJobs.dto.other.SuggestionRequest;
import com.cdyoue.oddJobs.dto.other.SuggestionSummary;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-31T12:57:35.459Z")

@Api(value = "suggestions", description = "the suggestions API")
public interface SuggestionsApi {

    @ApiOperation(value = "发布意见反馈(完成)", notes = "发布意见反馈，只有企业才能发布意见反馈", response = HttpMessage.class, tags={ "suggestions", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "创建成功", response = HttpMessage.class) })
    @RequestMapping(value = "/suggestions",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> createSuggestion(@ApiParam(value = "意见反馈实体信息" ,required=true ) @RequestBody SuggestionRequest enterService);


    @ApiOperation(value = "删除意见反馈(完成)", notes = "删除意见反馈", response = HttpMessage.class, tags={ "suggestions", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class) })
    @RequestMapping(value = "/suggestions/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteSuggestion(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "获取意见反馈详情(完成)", notes = "获取意见反馈详情", response = SuggestionDetail.class, tags={ "suggestions", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = SuggestionDetail.class),
        @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "xxx 没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/suggestions/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity getSuggestionById(@ApiParam(value = "意见反馈id",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "获取意见反馈列表(完成)", notes = "获取意见反馈列表", response = SuggestionSummary.class, responseContainer = "List", tags={ "suggestions", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = SuggestionSummary.class),
        @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "xxx 没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/suggestions",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity getSuggestions( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
         @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q);

}
