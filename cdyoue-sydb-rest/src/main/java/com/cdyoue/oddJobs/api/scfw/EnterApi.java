package com.cdyoue.oddJobs.api.scfw;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.scfw.EnterServiceDetail;
import com.cdyoue.oddJobs.dto.scfw.EnterServiceSummary;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-15T01:26:16.185Z")

@Api(value = "enter", description = "the enter API")
public interface EnterApi {

    @ApiOperation(value = "发布服务机构(完成)", notes = "发布服务机构，只有企业才能发布服务机构", response = HttpMessage.class, tags={ "enterservice", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "创建成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误.", response = HttpMessage.class) })
    @RequestMapping(value = "/enter/service",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> createEnterService(@ApiParam(value = "服务机构实体信息" ,required=true ) @RequestBody EnterServiceDetail enterServiceDetail);


    @ApiOperation(value = "删除服务机构(完成)", notes = "删除服务机构", response = HttpMessage.class, tags={ "enterservice", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "服务机构没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/enter/service/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteEnterService(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "获取服务机构列表(完成)", notes = "获取服务机构列表", response = EnterServiceSummary.class, responseContainer = "List", tags={ "enterservice", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = EnterServiceSummary.class),
        @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "页面没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/enter/service",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PageInfo> getEnterService(@NotNull @ApiParam(value = "服务机构类别，zscq：知识产权，scfw：双创服务", required = true, allowableValues = "ZSCQ, SCFW") @RequestParam(value = "type", required = true) String type,
                                             @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                             @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
                                             @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q);


    @ApiOperation(value = "获取服务机构详情(完成)", notes = "获取服务机构详情", response = EnterServiceDetail.class, tags={ "enterservice", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = EnterServiceDetail.class),
        @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "页面没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/enter/service/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<EnterServiceDetail> getEnterServiceById(@ApiParam(value = "服务机构id",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "编辑服务机构(完成)", notes = "编辑服务机构", response = HttpMessage.class, tags={ "enterservice", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "服务机构没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/enter/service/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updateEnterService(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
        @ApiParam(value = "服务机构对象" ,required=true ) @RequestBody EnterServiceDetail enterServiceDetail);

}
