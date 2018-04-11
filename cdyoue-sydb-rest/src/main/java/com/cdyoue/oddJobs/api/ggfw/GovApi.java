package com.cdyoue.oddJobs.api.ggfw;


import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.ggfw.Govproject;
import com.cdyoue.oddJobs.dto.ggfw.GovprojectSummary;
import com.cdyoue.oddJobs.dto.ggfw.Govservice;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-15T01:26:16.185Z")

@Api(value = "gov", description = "the gov API")
public interface GovApi {

    @ApiOperation(value = "发布政府项目(完成)", notes = "发布政府项目，只有企业才能发布政府项目", response = HttpMessage.class, tags={ "govproject", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "创建成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误.", response = HttpMessage.class) })
    @RequestMapping(value = "/gov/project",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> createGovproject(@ApiParam(value = "政府项目实体信息" ,required=true ) @RequestBody Govproject govproject);


    @ApiOperation(value = "发布政府服务(完成)", notes = "发布政府服务，只有企业才能发布政府服务", response = HttpMessage.class, tags={ "govservice", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "创建成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误.", response = HttpMessage.class) })
    @RequestMapping(value = "/gov/service",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> createGovservice(@ApiParam(value = "政府服务实体信息" ,required=true ) @RequestBody Govservice news);


    @ApiOperation(value = "删除政府服务(完成)", notes = "删除政府服务", response = HttpMessage.class, tags={ "govservice", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "政府服务没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/gov/service/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteGovService(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "获取政府项目列表(完成)", notes = "获取政府项目列表", response = GovprojectSummary.class, responseContainer = "List", tags={ "govproject", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = GovprojectSummary.class),
        @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "页面没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/gov/project",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PageInfo> getGovproject( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
                                            @NotNull @ApiParam(value = "发文时间，最近一周:1，最近一月:2", required = false, allowableValues = "1, 2") @RequestParam(value = "timeFilter", required = false) Integer timeFilter,
                                            @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q);


    @ApiOperation(value = "获取政府项目详情(完成)", notes = "获取政府项目详情", response = Govproject.class, tags={ "govproject", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = Govproject.class),
        @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "页面没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/gov/project/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Govproject> getGovprojectById(@ApiParam(value = "政府项目id",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "获取政府服务列表(完成)", notes = "获取政府服务列表", response = Govservice.class, responseContainer = "List", tags={ "govservice", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = Govservice.class),
        @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "页面没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/gov/service",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PageInfo> getGovservice(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                          @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
                                           @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q);


    @ApiOperation(value = "获取政府服务详情(完成)", notes = "获取政府服务详情", response = Govservice.class, tags={ "govservice", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = Govservice.class),
        @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "页面没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/gov/service/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Govservice> getGovserviceById(@ApiParam(value = "政府服务id",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "编辑政府项目(完成)", notes = "编辑政府项目", response = HttpMessage.class, tags={ "govproject", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "政府项目没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/gov/project/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updateGovproject(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
        @ApiParam(value = "政府项目对象" ,required=true ) @RequestBody Govproject govproject);


    @ApiOperation(value = "编辑政府服务(完成)", notes = "编辑政府服务", response = HttpMessage.class, tags={ "govservice", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "政府服务没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/gov/service/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updateGovservice(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
        @ApiParam(value = "政府服务对象" ,required=true ) @RequestBody Govservice govservice);

    @ApiOperation(value = "删除政府项目(完成)", notes = "删除政府服务", response = HttpMessage.class, tags={ "govproject", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "政府服务没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/gov/project/{id}",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteGovProject(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id);

}
