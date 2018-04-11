package com.cdyoue.oddJobs.api.scfw;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.scfw.InvestorDetail;
import com.cdyoue.oddJobs.dto.scfw.InvestorSummary;
import com.cdyoue.spring.page.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-09T00:59:32.305Z")

@Api(value = "investors", description = "the investors API")
public interface InvestorsApi {

    @ApiOperation(value = "获取投资人列表(完成)", notes = "获取投资人列表", response = InvestorSummary.class, responseContainer = "List", tags={ "investors", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = InvestorSummary.class),
        @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "页面没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/investors",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PageInfo> getInvestors(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                          @ApiParam(value = "页码。默认第0页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
                                          @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q);

    @ApiOperation(value = "发布投资人(完成)", notes = "发布投资人，只有企业才能发布投资人", response = HttpMessage.class, tags={ "investors", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "创建成功", response = HttpMessage.class) })
    @RequestMapping(value = "/investors",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> createInvestor(@ApiParam(value = "投资人实体信息" ,required=true ) @RequestBody InvestorDetail investorDetail);


    @ApiOperation(value = "删除投资人(完成)", notes = "删除投资人", response = HttpMessage.class, tags={ "investors", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class) })
    @RequestMapping(value = "/investors/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteInvestor(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "获取投资人详情(完成)", notes = "获取投资人详情", response = InvestorDetail.class, tags={ "investors", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = InvestorDetail.class) })
    @RequestMapping(value = "/investors/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<InvestorDetail> getInvestorById(@ApiParam(value = "投资人id",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "编辑投资人(完成)", notes = "编辑投资人", response = HttpMessage.class, tags={ "investors", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class) })
    @RequestMapping(value = "/investors/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updateInvestor(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
        @ApiParam(value = "投资人对象" ,required=true ) @RequestBody InvestorDetail investorDetail);

}
