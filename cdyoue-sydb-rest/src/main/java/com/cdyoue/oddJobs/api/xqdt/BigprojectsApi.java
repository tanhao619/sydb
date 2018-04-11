package com.cdyoue.oddJobs.api.xqdt;


import java.util.List;

import com.cdyoue.spring.page.PageInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.xqdt.BigProjectDetail;
import com.cdyoue.oddJobs.dto.xqdt.BigProjectSummary;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-18T06:53:23.085Z")

@Api(value = "bigprojects", description = "the bigprojects API")
public interface BigprojectsApi {

    @ApiOperation(value = "发布大项目(完成)", notes = "发布大项目，只有企业才能发布大项目", response = HttpMessage.class, tags={ "bigprojects", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "创建成功", response = HttpMessage.class) })
    @RequestMapping(value = "/bigprojects",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> createBigProject(@ApiParam(value = "大项目实体信息" ,required=true ) @RequestBody BigProjectDetail bigproject);


    @ApiOperation(value = "删除大项目(完成)", notes = "删除大项目", response = HttpMessage.class, tags={ "bigprojects", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class) })
    @RequestMapping(value = "/bigprojects/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteBigProject(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "获取大项目详情(完成)", notes = "获取大项目详情", response = BigProjectDetail.class, tags={ "bigprojects", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = BigProjectDetail.class) })
    @RequestMapping(value = "/bigprojects/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<BigProjectDetail> getBigProjectById(@ApiParam(value = "大项目id",required=true ) @PathVariable("id") Integer id);

    @ApiOperation(value = "获取置顶大项目(完成)", notes = "获取置顶大项目", response = BigProjectDetail.class, tags={ "bigprojects", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = BigProjectDetail.class) })
    @RequestMapping(value = "/bigprojects/top",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<BigProjectDetail> getTopBigProject();


    @ApiOperation(value = "获取大项目列表(完成)", notes = "获取大项目列表", response = BigProjectSummary.class, responseContainer = "List", tags={ "bigprojects", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = BigProjectSummary.class) })
    @RequestMapping(value = "/bigprojects",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PageInfo> getBigProjects(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                            @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
                                            @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q);


    @ApiOperation(value = "置顶大项目(完成)", notes = "置顶大项目,只能置顶一个大项目，如果有其他的已经置顶，先取消置顶", response = HttpMessage.class, tags={ "bigprojects", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class) })
    @RequestMapping(value = "/bigprojects/{id}/stick",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> stickBigProject(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
                                                @RequestParam String topImg);


    @ApiOperation(value = "取消置顶大项目(完成)", notes = "取消置顶大项目,只能置顶一个大项目，如果有其他的已经置顶，先取消置顶", response = HttpMessage.class, tags={ "bigprojects", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class) })
    @RequestMapping(value = "/bigprojects/{id}/unstick",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> unStickBigProject(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "编辑大项目(完成)", notes = "编辑大项目", response = HttpMessage.class, tags={ "bigprojects", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class) })
    @RequestMapping(value = "/bigprojects/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updateBigProject(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
        @ApiParam(value = "大项目对象" ,required=true ) @RequestBody BigProjectDetail bigproject);

}
