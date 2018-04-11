package com.cdyoue.oddJobs.api.independent;


import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.independent.IndependentMine;
import com.cdyoue.oddJobs.dto.independent.IndependentSumary;
import com.cdyoue.oddJobs.dto.independent.RequestIndependent;
import com.cdyoue.oddJobs.dto.requirement.RequireSummary;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-20T01:12:00.448Z")

@Api(value = "independent", description = "the independent API")
public interface IndependentTextApi {




    @ApiOperation(value = "发布独立发布(完成)", notes = "发布独立发布", response = HttpMessage.class, tags={ "independent", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "创建成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误.", response = HttpMessage.class) })
    @RequestMapping(value = "/independent",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> createIndependent(
            @ApiParam(value = "1: 独立页面发布2:数据分析", required = true,allowableValues = "1,2") @RequestParam("type") Integer type,
            @ApiParam(value = "需要发布的独立发布实体信息", required = true) @RequestBody RequestIndependent independent);


    @ApiOperation(value = "删除独立发布(完成)", notes = "根据id删除独立发布", response = HttpMessage.class, tags={ "independent", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "独立发布没找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/independent/{id}",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteIndependent(@ApiParam(value = "", required = true) @PathVariable("id") Integer id);




    @ApiOperation(value = "获取独立发布详情(完成)", notes = "根据独立发布id获取独立发布详情", response = IndependentSumary.class, tags={ "independent", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = IndependentSumary.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "独立发布没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/independent/{id}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<IndependentSumary> getIndependentById(@ApiParam(value = "", required = true) @PathVariable("id") Integer id);


    @ApiOperation(value = "获取独立发布列表(完成)", notes = "获取所有独立发布列表", response = IndependentMine.class, responseContainer = "List", tags={ "independent", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = IndependentMine.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "xxx 没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/independent",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<PageInfo<IndependentMine>> getIndependents(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                          @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                          @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                                              @ApiParam(value = "1: 独立页面发布2:数据分析", required = true,allowableValues = "1,2") @RequestParam("type") Integer type,
                                                          @ApiParam(value = "排序字段和方式 例如：/policies?sort=-create_time|-update_time", allowableValues = "createTime, -createTime") @RequestParam(value = "sort", required = false, defaultValue = "-createTime") String sort);


    @ApiOperation(value = "编辑独立(发布或数据分析)((完成)", notes = "编辑独立发布", response = HttpMessage.class, tags={ "independent", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "独立发布没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/independent/{id}",
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updateIndependent(@ApiParam(value = "", required = true) @PathVariable("id") Integer id,
                                                   @ApiParam(value = "独立发布对象", required = true) @RequestBody RequestIndependent independent);







    @ApiOperation(value = "增加独立阅读量(发布或数据分析)((完成)", notes = "增加阅读量", response = HttpMessage.class, tags={ "independent", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "独立发布没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/independent/{id}/viewNum",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<HttpMessage> addViewNum(@ApiParam(value = "", required = true) @PathVariable("id") Integer id);





}
