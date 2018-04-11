package com.cdyoue.oddJobs.api.zscq;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.zscq.*;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-19T00:40:47.264Z")

@Api(value = "patents", description = "the patents API")
public interface PatentsApi {

    @ApiOperation(value = "获取专利大数据比价信息（完成）", notes = "获取专利大数据比价信息", response = PatentBigdataPriceComp.class, responseContainer = "List", tags={ "patents", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = PatentBigdataPriceComp.class) })
    @RequestMapping(value = "/patents/bigdata/{name}/pricecomp",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<PatentBigdataPriceComp>> getPatentBdPriceById(@ApiParam(value = "专利大数据名称",required=true ) @PathVariable("name") String name);


    @ApiOperation(value = "删除专利大数据比价（新增，完成）", notes = "根据专利大数据比价id删除", response = HttpMessage.class, tags={ "patents", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class) })
    @RequestMapping(value = "/patents/bigdata/{id}/pricecomp",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deletePatentBdpById(@ApiParam(value = "专利大数据比价id",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "获取专利大数据列表（完成）", notes = "获取专利大数据列表", response = PatentBigdataSummary.class, responseContainer = "List", tags={ "patents", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = PatentBigdataSummary.class) })
    @RequestMapping(value = "/patents/bigdata",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PageInfo<PatentBigdataSummary>> getPatentBds( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
         @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
         @ApiParam(value = "排序字段和方式 例如：/patents/bigdata?sort=index", allowableValues = "INDEX, PRIORITYNO, REFERNO, TYPENO, AGE") @RequestParam(value = "sort", required = false) String sort);


    @ApiOperation(value = "删除专利大数据（新增，完成）", notes = "根据专利大数据id删除", response = HttpMessage.class, tags={ "patents", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class) })
    @RequestMapping(value = "/patents/bigdata/{id}",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deletePatentBdById(@ApiParam(value = "专利大数据id",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "获取国家专利详情（完成）", notes = "获取国家专利详情", response = PatentNationDetail.class, tags={ "patents", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = PatentNationDetail.class) })
    @RequestMapping(value = "/patents/nation/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PatentNationDetail> getPatentById(@ApiParam(value = "国家专利id",required=true ) @PathVariable("id") Integer id);

    @ApiOperation(value = "删除国家专利（新增，完成）", notes = "根据国家专利id删除", response = HttpMessage.class, tags={ "patents", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class) })
    @RequestMapping(value = "/patents/nation/{id}",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deletePatentById(@ApiParam(value = "国家专利id",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "获取国家专利列表（完成）", notes = "获取国家专利列表", response = PatentNationSummary.class, responseContainer = "List", tags={ "patents", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = PatentNationSummary.class) })
    @RequestMapping(value = "/patents/nation",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PageInfo<PatentNationSummary>> getPatents(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                                             @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
                                                             @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                                             @ApiParam(value = "专利类型：1 发明专利，2 实用新型专利，3 外观设计专利") @RequestParam(value = "type", required = false) String type);
    
    @ApiOperation(value = "获取国家专利列表推荐（完成）", notes = "获取国家专利列表 推荐", response = PatentNationSummary.class, responseContainer = "List", tags={ "patents", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = PatentNationSummary.class) })
    @RequestMapping(value = "/patents/nation/recommands",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<PatentNationSummary>> getRecommandPatents();
    
    @ApiOperation(value = "获取产业链报告详情(完成)", notes = "获取产业链报告详情", response = IndustryReportDetail.class, tags={ "reports", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = IndustryReportDetail.class) })
    @RequestMapping(value = "/patents/industryreports/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<IndustryReportDetail> getReportById(@ApiParam(value = "产业链报告id",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "获取产业链报告列表(完成)", notes = "获取产业链报告列表", response = IndustryReportSummary.class, responseContainer = "List", tags={ "reports", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = IndustryReportSummary.class) })
    @RequestMapping(value = "/patents/industryreports",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PageInfo> getReports( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
         @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q);


    @ApiOperation(value = "编辑产业链报告(完成)", notes = "编辑产业链报告", response = HttpMessage.class, tags={ "reports", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class) })
    @RequestMapping(value = "/patents/industryreports/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updateReport(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
        @ApiParam(value = "产业链报告对象" ,required=true ) @RequestBody IndustryReportDetail industryReportDetail);

    @ApiOperation(value = "发布产业链报告(完成)", notes = "发布产业链报告，只有企业才能发布产业链报告", response = HttpMessage.class, tags={ "reports", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "创建成功", response = HttpMessage.class) })
    @RequestMapping(value = "/patents/industryreports",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> createReport(@ApiParam(value = "产业链报告实体信息" ,required=true ) @RequestBody IndustryReportDetail industryReportDetail);


    @ApiOperation(value = "删除产业链报告(完成)", notes = "删除产业链报告", response = HttpMessage.class, tags={ "reports", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class) })
    @RequestMapping(value = "/patents/industryreports/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteReport(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id);

    @ApiOperation(value = "收藏专利（完成）", notes = "根据id收藏专利", response = HttpMessage.class, tags={ "patents", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "z专利没找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/patents/{id}/collect",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> collectPatent(@ApiParam(value = "国家专利id",required=true ) @PathVariable("id") Integer id);


}
