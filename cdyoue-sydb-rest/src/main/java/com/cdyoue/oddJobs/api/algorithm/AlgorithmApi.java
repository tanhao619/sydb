package com.cdyoue.oddJobs.api.algorithm;

import com.cdyoue.oddJobs.dto.algorithm.*;
import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.home.PageHome;
import com.cdyoue.oddJobs.dto.home.PageHomeSummary;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by liaoyoule on 2017/6/14.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-20T01:12:00.448Z")

@Api(value = "algorithms", description = "the home API")
public interface AlgorithmApi {
    @ApiOperation(value = "获取分页算法信息（完成）", notes = "获取分页算法信息", response = AlgorithmBase.class, responseContainer = "List", tags={ "algorithms", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = PageHome.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "xxx 没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/algorithms",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<PageInfo<AlgorithmBase>> getAlgorithms(
                                                           @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                           @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                           @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                                           @ApiParam(value = "排序字段和方式 例如：/policies?sort=createTime|-createTime", allowableValues = "createTime, -createTime") @RequestParam(value = "sort", required = false, defaultValue = "-createTime") String sort

    ) ;




    @ApiOperation(value = "删除算法(完成)", notes = "根据id删除算法", response = HttpMessage.class, tags={ "algorithms", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "需求没找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/algorithms/{id}",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteAlgorithmOperator(@ApiParam(value = "", required = true) @PathVariable("id") Integer id);


    @ApiOperation(value = "查看算法详情(完成)", notes = "根据id查看算法详情", response = AlgorithmSumary.class, tags={ "algorithms", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = AlgorithmSumary.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "需求没找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/algorithms/{id}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<AlgorithmSumary> getAlgorithm(@ApiParam(value = "", required = true) @PathVariable("id") Integer id);







    @ApiOperation(value = "添加算法(完成)", notes = "添加算法", response = HttpMessage.class, tags={ "algorithms", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "需求没找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/algorithms",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> createAlgorithm(@ApiParam(value = "", required = true) @RequestBody()@Valid AlgorithmRequest algorithmRequest);



    @ApiOperation(value = "修改算法(完成)", notes = "修改算法", response = HttpMessage.class, tags={ "algorithms", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "需求没找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/algorithms/{id}",
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updateAlgorithm(
            @ApiParam(value = "", required = true) @PathVariable("id") Integer id,
            @ApiParam(value = "", required = true) @RequestBody()@Valid AlgorithmRequest algorithmRequest);


    /**
     * =============
     *algorithm type
     * =============
     */

    @ApiOperation(value = "获取分页算法类型信息（完成）", notes = "获取分页算法类型信息", response = AlgorithmBase.class, responseContainer = "List", tags={ "algorithms", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = PageHome.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "xxx 没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/algorithmTypes",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<PageInfo<AlgorithmTypeSumary>> getAlgorithmTypes(
            @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
            @ApiParam(value = "排序字段和方式 例如：/policies?sort=createTime|-createTime", allowableValues = "createTime, -createTime") @RequestParam(value = "sort", required = false, defaultValue = "-createTime") String sort

    ) ;


    @ApiOperation(value = "删除算法类型(完成)", notes = "根据id删除算法类型", response = HttpMessage.class, tags={ "algorithms", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "需求没找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/algorithmTypes/{id}",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteAlgorithmTypes(@ApiParam(value = "", required = true) @PathVariable("id") Integer id);




    @ApiOperation(value = "查看算法详情(完成)", notes = "根据id查看算法详情", response = AlgorithmSumary.class, tags={ "algorithms", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = AlgorithmSumary.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "需求没找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/algorithmTypes/{id}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<AlgorithmTypeSumary> getAlgorithmType(@ApiParam(value = "", required = true) @PathVariable("id") Integer id);



    @ApiOperation(value = "添加算法类型(完成)", notes = "添加算法类型", response = HttpMessage.class, tags={ "algorithms", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "需求没找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/algorithmTypes",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> createAlgorithmType(@ApiParam(value = "", required = true) @RequestBody()@Valid AlgorithmTypeRequest algorithmTypeRequest);



    @ApiOperation(value = "修改算法类型((完成)", notes = "修改算法类型(", response = HttpMessage.class, tags={ "algorithms", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "需求没找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/algorithmTypes/{id}",
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updateAlgorithmType(
            @ApiParam(value = "", required = true) @PathVariable("id") Integer id,
            @ApiParam(value = "", required = true) @RequestBody()@Valid AlgorithmTypeRequest algorithmTypeRequest);



}
