package com.cdyoue.oddJobs.api.zlcy;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.zlcy.*;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sky on 2017/9/23.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-18T06:53:23.085Z")

@Api(value = "enterprise", description = "the enterprise API")
public interface EnterpriseApi {
    @ApiOperation(value = "获取企业详情(完成)", notes = "获取企业详情", response = EnterpriseDetail.class, tags={ "enterprise", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = EnterpriseDetail.class) })
    @RequestMapping(value = "/enterprise/{id}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<EnterpriseDetail> getEnterpriseById(@ApiParam(value = "培训机构id", required = true) @PathVariable("id") Integer id);


    @ApiOperation(value = "获取企业表(完成)", notes = "获取企业表", response = EnterpriseSummary.class, responseContainer = "List", tags={ "enterprise", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = EnterpriseSummary.class) })
    @RequestMapping(value = "/enterprise",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<PageInfo> getEnterprises(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                          @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                          @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q);

    @ApiOperation(value = "获取推荐企业表(完成)", notes = "获取推荐企业表", response = EnterpriseSummary.class, responseContainer = "List", tags={ "enterprise", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = EnterpriseSummary.class) })
    @RequestMapping(value = "/enterprise/top",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<EnterpriseSummary>> getTopEnterprises();

    @ApiOperation(value = "置顶(取消置顶)企业(完成)", notes = "置顶(取消置顶)企业", response = HttpMessage.class, tags={ "enterprise", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class) })
    @RequestMapping(value = "/enterprise/top/{id}",
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> topEnterprise(@ApiParam(value = "企业id", required = true) @PathVariable("id") Integer id,
                                              @ApiParam(value = "置顶图片") @RequestParam(value = "topImage",required=false ) String topImage);

    @ApiOperation(value = "申请入住基地(完成)", notes = "申请入住基地", response = HttpMessage.class, tags={ "enterprise", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class) })
    @RequestMapping(value = "/enterprise/checkIn",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> ApplicationCheckIn(@ApiParam(value = "申请企业信息", required = true) @RequestBody ApplicationEnterpriseSummary applicationEnterpriseSummary);

    @ApiOperation(value = "获取申请入住基地的企业列表", notes = "获取申请入住基地的企业列表", responseContainer = "List", response = ApplicationCheckInMini.class, tags={ "enterprise", })
    @RequestMapping(value = "/enterprise/checkIns",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<PageInfo<ApplicationCheckInMini>> getApplicationCheckIns(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                                            @ApiParam(value = "页码。默认第一页", defaultValue = "1") @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
                                                                            @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q);

    @ApiOperation(value = "获取申请入住基地的企业详情", notes = "获取申请入住基地的企业详情", response = ApplicationCheckInDetail.class, tags={ "enterprise", })
    @RequestMapping(value = "/enterprise/checkIn/{id}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<ApplicationCheckInDetail> getApplicationCheckIn(@ApiParam(value = "申请入住基地的企业信息id", required = true) @PathVariable("id") Integer id);

    @ApiOperation(value = "（批量）删除申请入住基地的企业信息", notes = "（批量）删除申请入住基地的企业信息", response = HttpMessage.class, tags={ "enterprise", })
    @RequestMapping(value = "/enterprise/checkIn",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteApplicationCheckIn(@ApiParam(value = "申请入住基地的企业信息ids", required = true) @RequestParam Integer[] ids);

    @ApiOperation(value = "申请创业指导(完成)", notes = "申请创业指导", response = HttpMessage.class, tags={ "enterprise", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class) })
    @RequestMapping(value = "/enterprise/direction",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> ApplicationDirection(@ApiParam(value = "申请企业信息", required = true) @RequestBody ApplicationDirectionSummary applicationDirectionSummary);

    @ApiOperation(value = "获取申请创业指导的企业列表", notes = "获取申请创业指导的企业列表", responseContainer = "List", response = ApplicationDirectionMini.class, tags={ "enterprise", })
    @RequestMapping(value = "/enterprise/directions",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<PageInfo<ApplicationDirectionMini>> getApplicationDirections(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                                                @ApiParam(value = "页码。默认第一页", defaultValue = "1") @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
                                                                                @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q);

    @ApiOperation(value = "获取申请创业指导的企业详情", notes = "获取申请创业指导的企业详情", response = ApplicationDirectionDetail.class, tags={ "enterprise", })
    @RequestMapping(value = "/enterprise/direction/{id}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<ApplicationDirectionDetail> getApplicationDirection(@ApiParam(value = "申请创业指导的企业信息id", required = true) @PathVariable("id") Integer id);

    @ApiOperation(value = "（批量）删除申请创业指导的企业信息", notes = "（批量）删除申请创业指导的企业信息", response = HttpMessage.class, tags={ "enterprise", })
    @RequestMapping(value = "/enterprise/direction",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteApplicationDirection(@ApiParam(value = "申请创业指导的企业信息ids", required = true) @RequestParam Integer[] ids);

    @ApiOperation(value = "删除入驻企业库(完成)", notes = "删除入驻企业库", response = HttpMessage.class, tags={ "enterprise", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class) })
    @RequestMapping(value = "/enterprise",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteEnterprise(@ApiParam(value = "项目申报ids",required=true ) @RequestParam Integer[] ids);

    @ApiOperation(value = "发布企业(完成)", notes = "发布企业", response = EnterpriseCreate.class, tags={ "enterprise", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "创建成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误.", response = HttpMessage.class) })
    @RequestMapping(value = "/create/enterprise",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> createEnterprise(@ApiParam(value = "发布企业实体" ,required=true ) @RequestBody EnterpriseCreate enterpriseCreate);


    @ApiOperation(value = "编辑企业(完成)", notes = "编辑企业", response = HttpMessage.class, tags={ "enterprise", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "项目没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/enterprise/{id}",
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updateEnterprise(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
                                              @ApiParam(value = "企业对象信息" ,required=true ) @RequestBody EnterpriseCreate enterpriseCreate);
}
