package com.cdyoue.oddJobs.api.scfw;


import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.scfw.IncubatorDetail;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-16T14:01:12.120Z")

@Api(value = "incubators", description = "the incubators API")
public interface IncubatorsApi {

    @ApiOperation(value = "发布孵化器(完成)", notes = "发布孵化器，只有企业才能发布孵化器", response = HttpMessage.class, tags={ "incubators", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "创建成功", response = HttpMessage.class) })
    @RequestMapping(value = "/incubators",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> createIncubator(@ApiParam(value = "孵化器实体信息" ,required=true ) @RequestBody IncubatorDetail enterService);


    @ApiOperation(value = "删除孵化器(完成)", notes = "删除孵化器", response = HttpMessage.class, tags={ "incubators", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class) })
    @RequestMapping(value = "/incubators/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteIncubator(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "获取孵化器列表(完成)", notes = "获取孵化器列表", response = IncubatorDetail.class, responseContainer = "List", tags={ "incubators", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = IncubatorDetail.class) })
    @RequestMapping(value = "/incubators",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PageInfo> getIncubator(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                          @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
                                          @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q);


    @ApiOperation(value = "获取孵化器详情(完成)", notes = "获取孵化器详情", response = IncubatorDetail.class, tags={ "incubators", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = IncubatorDetail.class) })
    @RequestMapping(value = "/incubators/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<IncubatorDetail> getIncubatorById(@ApiParam(value = "孵化器id",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "编辑孵化器(完成)", notes = "编辑孵化器", response = HttpMessage.class, tags={ "incubators", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class) })
    @RequestMapping(value = "/incubators/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updateIncubator(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
        @ApiParam(value = "孵化器对象" ,required=true ) @RequestBody IncubatorDetail enterService);

}
