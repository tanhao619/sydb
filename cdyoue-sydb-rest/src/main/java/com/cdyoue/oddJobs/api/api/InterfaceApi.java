package com.cdyoue.oddJobs.api.api;

import com.cdyoue.oddJobs.dto.algorithm.AlgorithmSumary;
import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.other.InterfaceApiRequest;
import com.cdyoue.oddJobs.dto.other.PortalApiSumary;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by liaoyoule on 2017/6/14.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-20T01:12:00.448Z")

@Api(value = "interface ", description = "the home API")
public interface InterfaceApi {
    @ApiOperation(value = "获取api接口信息（完成）", notes = "获取api接口信息", response = PortalApiSumary.class, responseContainer = "List", tags={ "interface ", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = PortalApiSumary.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "xxx 没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/interface",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<PageInfo<PortalApiSumary>> getApis (
            @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
            @ApiParam(value = "分类 1过滤器api接口 2 计算器api接口 3 格式转换api接口 4相似度api接口 5文件操作api接口'", allowableValues = "1,2,3,4,5") @RequestParam(value = "type", required = false) Integer type,
            @ApiParam(value = "排序字段和方式 例如：/policies?sort=createTime|-createTime", allowableValues = "createTime, -createTime") @RequestParam(value = "sort", required = false, defaultValue = "-createTime") String sort

    ) ;




    @ApiOperation(value = "删除api接口(完成)", notes = "根据id删除api接口", response = HttpMessage.class, tags={ "interface ", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "需求没找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/interface/{id}",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteApi (@ApiParam(value = "", required = true) @PathVariable("id") Integer id);


    @ApiOperation(value = "查看api接口详情(完成)", notes = "根据id查看api接口详情", response = AlgorithmSumary.class, tags={ "interface ", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = AlgorithmSumary.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "需求没找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/interface/{id}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<PortalApiSumary> getApi(@ApiParam(value = "", required = true) @PathVariable("id") Integer id);







    @ApiOperation(value = "添加api接口(完成)", notes = "添加api接口", response = HttpMessage.class, tags={ "interface ", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "需求没找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/interface",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> createApi(
            @ApiParam(value = "", required = true) @RequestBody() InterfaceApiRequest interfaceApiRequest);






    @ApiOperation(value = "修改api接口(完成)", notes = "修改api接口", response = HttpMessage.class, tags={ "interface ", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "需求没找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/interface/{id}",
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updateApi(
            @ApiParam(value = "", required = true) @PathVariable("id") Integer id,
            @ApiParam(value = "", required = true) @RequestBody() InterfaceApiRequest interfaceApiRequest);





}
