package com.cdyoue.oddJobs.api.home;


import java.util.List;

import com.cdyoue.oddJobs.dto.home.PageHomeRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.home.PageHome;
import com.cdyoue.oddJobs.dto.home.PageHomeSummary;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import com.cdyoue.spring.page.PageInfo;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-20T01:12:00.448Z")

@Api(value = "homes", description = "the home API")
public interface HomePageApi {
    


    @ApiOperation(value = "获取各个页面首页信息(完成)", notes = "获取各个页面首页信息", response = PageHomeSummary.class, responseContainer = "List", tags={ "homes", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = PageHomeSummary.class),
        @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "xxx 没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/homes",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<PageHomeSummary>> getPageHomes(@ApiParam(value = "各个板块名称，具体名称对应关系见'首页接口对应表'。", allowableValues = "HOME, XQDT, LGFC, ZSCQ, SCFW, GGFW") @RequestParam(value = "page", required = false) String page);


    @ApiOperation(value = "获取各个页面首页信息（完成）", notes = "获取各个页面首页信息", response = PageHome.class, responseContainer = "List", tags={ "homes", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = PageHome.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "xxx 没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/pageHomes",
            produces = { "application/json" },
            method = RequestMethod.GET)
     ResponseEntity<PageInfo<PageHomeSummary>>getPageHomes(@ApiParam(value = "各个板块名称，具体名称对应关系见'首页接口对应表'。", allowableValues = "HOME,HOME_0, XQDT, LGFC, ZSCQ, SCFW, GGFW") @RequestParam(value = "page", required = false) String page,
                                                           @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                           @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                           @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                                           @ApiParam(value = "排序字段和方式 例如：/policies?sort=-create_time|-update_time", allowableValues = "sort, -sort") @RequestParam(value = "sort", required = false, defaultValue = "-sort") String sort

     );



    @ApiOperation(value = "编辑栏目名称（完成）", notes = "编辑栏目名称", response = HttpMessage.class, tags={ "homes", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "需求没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/homes/{code}/changeName",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updateHomePageName(@ApiParam(value = "",required=true ) @PathVariable("code") String code,
        @ApiParam(value = "栏目名称" ,required=true ) @RequestParam String name);


    @ApiOperation(value = "编辑栏目（完成）", notes = "编辑栏目", response = HttpMessage.class, tags={ "homes", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "需求没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/homes/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updateHomePages(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
        @ApiParam(value = "需求对象" ,required=true ) @RequestBody PageHomeRequest page);


    @ApiOperation(value = "首页导航栏目上移动（完成）", notes = "首页导航栏目上移动", response = HttpMessage.class, tags={ "homes", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "需求没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/homes/{id}/moveUp",
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> HomePagesMoveUp(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id);

    @ApiOperation(value = "首页导航栏目下移动（完成）", notes = "首页导航栏目下移动", response = HttpMessage.class, tags={ "homes", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "需求没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/homes/{id}/moveDown",
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> HomePagesMoveDown(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id);


}
