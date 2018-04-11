package com.cdyoue.oddJobs.api.xqdt;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.xqdt.SuccaseDetail;
import com.cdyoue.oddJobs.dto.xqdt.SuccaseSummary;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-18T06:53:23.085Z")

@Api(value = "succases", description = "the succases API")
public interface SuccasesApi {

    @ApiOperation(value = "发布成功案例(完成)", notes = "发布成功案例，只有企业才能发布成功案例", response = HttpMessage.class, tags={ "succases", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "创建成功", response = HttpMessage.class) })
    @RequestMapping(value = "/succases",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> createSuccase(@ApiParam(value = "成功案例实体信息" ,required=true ) @RequestBody SuccaseDetail succase);


    @ApiOperation(value = "删除成功案例(完成)", notes = "删除成功案例", response = HttpMessage.class, tags={ "succases", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class) })
    @RequestMapping(value = "/succases/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteSuccase(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "获取成功案例详情(完成)", notes = "获取成功案例详情", response = SuccaseDetail.class, tags={ "succases", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = SuccaseDetail.class) })
    @RequestMapping(value = "/succases/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<SuccaseSummary> getSuccaseById(@ApiParam(value = "成功案例id",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "获取成功案例列表(完成)", notes = "获取成功案例列表", response = SuccaseSummary.class, responseContainer = "List", tags={ "succases", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = SuccaseSummary.class) })
    @RequestMapping(value = "/succases",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity getSuccases(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
                                         @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q);


    @ApiOperation(value = "编辑成功案例(完成)", notes = "编辑成功案例", response = HttpMessage.class, tags={ "succases", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class) })
    @RequestMapping(value = "/succases/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updateSuccase(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
        @ApiParam(value = "成功案例对象" ,required=true ) @RequestBody SuccaseDetail succase);

}
