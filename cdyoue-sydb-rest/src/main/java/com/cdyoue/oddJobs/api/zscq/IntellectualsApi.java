package com.cdyoue.oddJobs.api.zscq;


import com.cdyoue.oddJobs.dto.Reason;
import com.cdyoue.oddJobs.dto.SyCooperativePartnerDTO;
import com.cdyoue.oddJobs.dto.SyCooperativePartnerMiniDTO;
import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.zscq.*;
import com.cdyoue.oddJobs.entity.syData.SyPortalAssessEntity;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-11T00:49:03.408Z")

@Api(value = "intellectuals", description = "the intellectuals API")
public interface IntellectualsApi {

    @ApiOperation(value = "知产求购置顶", notes = "知产求购置顶", response = HttpMessage.class, tags={ "intellectuals", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "著作权信息没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/intellectuals/buyTop",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> buyTop(@ApiParam(value = "",required=true ) @RequestBody IntellectualTop top);

    @ApiOperation(value = "知产出售置顶", notes = "知产出售置顶", response = HttpMessage.class, tags={ "intellectuals", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "著作权信息没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/intellectuals/saleTop",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> saleTop(@ApiParam(value = "",required=true ) @RequestBody IntellectualTop top);

    @ApiOperation(value = "取消知产求购置顶", notes = "取消知产求购置顶", response = HttpMessage.class, tags={ "intellectuals", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "著作权信息没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/intellectuals/removeBuyTop/{id}",
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> removeBuyTop(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id);

    @ApiOperation(value = "取消知产出售置顶", notes = "取消知产出售置顶", response = HttpMessage.class, tags={ "intellectuals", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "著作权信息没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/intellectuals/removeSaleTop/{type}/{id}",
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> removeSaleTop(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
                                              @ApiParam(value = "",required=true ) @PathVariable("type") Integer type);

    @ApiOperation(value = "获取知产评估列表", notes = "知产评估列表", response = AssessDetailDTO.class, responseContainer = "List", tags={ "intellectuals", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = AssessDetailDTO.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "页面没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/intellectuals/assess",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<PageInfo> getAssessList(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                                @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
                                                @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                                @ApiParam(value = "排序字段和方式 例如：/sales?sort=publishTime，默认按照发布时间排序。publishTime = 发布时间最新",defaultValue = "-publishTime") @RequestParam(value = "sort", required = false) String sort);

    @ApiOperation(value = "提交知产评估)", notes = "提交知产评估", response = HttpMessage.class, tags={ "intellectuals", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "需求没找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/intellectuals/Assess",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> insertAssess(@ApiParam(value = "",required=true ) @RequestBody AssessDetailSummer detailSummer);

    @ApiOperation(value = "获取知产评估详情", notes = "获取知产评估详情", response = SyPortalAssessEntity.class, tags={ "intellectuals", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "页面没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/intellectuals/assessDetail/{id}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<AssessDetailDTO> getAssessById(@ApiParam(value = "知产评估id",required=true ) @PathVariable("id") Integer id);

    @ApiOperation(value = "删除知产评估信息", notes = "删除知产评估信息", response = HttpMessage.class, tags={ "intellectuals", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "著作权信息没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/intellectuals/delAssess/{id}",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteAssess(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id);

    @ApiOperation(value = "批量删除知产评估信息", notes = "批量删除知产评估信息", response = HttpMessage.class, tags={ "intellectuals", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "著作权信息没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/intellectuals/delAllAssess",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteAllAssess(@ApiParam(value = "",required=true ) @RequestParam("ids") Integer[] ids);

    @ApiOperation(value = "获取合作伙伴列表", notes = "获取合作伙伴列表", response = SyCooperativePartnerDTO.class, responseContainer = "List", tags={ "intellectuals", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = SyCooperativePartnerDTO.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "页面没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/intellectuals/getPartner",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<SyCooperativePartnerDTO>> getPartnerList();

    @ApiOperation(value = "分页获取合作伙伴列表", notes = "分页获取合作伙伴列表", response = SyCooperativePartnerDTO.class, responseContainer = "List", tags={ "intellectuals", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = SyCooperativePartnerDTO.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "页面没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/intellectuals/getPagePartners",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<PageInfo> getPartnerPageList(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                              @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
                                              @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                              @ApiParam(value = "排序字段和方式 例如：/sales?sort=publishTime，默认按照发布时间排序。publishTime = 发布时间最新",defaultValue = "-publishTime") @RequestParam(value = "sort", required = false) String sort);

    @ApiOperation(value = "获取合作伙伴详情", notes = "获取合作伙伴详情", response = SyCooperativePartnerDTO.class, tags={ "intellectuals", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = SyCooperativePartnerDTO.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "页面没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/intellectuals/partnerDetail/{id}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<SyCooperativePartnerDTO> getPartnerDetail(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id);

    @ApiOperation(value = "发布合作伙伴信息", notes = "发布合作伙伴信息", response = HttpMessage.class, tags={ "intellectuals", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "创建成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误.", response = HttpMessage.class) })
    @RequestMapping(value = "/intellectuals/partner",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> insertPartner(@ApiParam(value = "合作伙伴实体信息" ,required=true ) @RequestBody SyCooperativePartnerMiniDTO miniDTO);

    @ApiOperation(value = "删除合作伙伴信息", notes = "删除合作伙伴信息", response = HttpMessage.class, tags={ "intellectuals", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "著作权信息没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/intellectuals/delPartner/{id}",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deletePartner(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id);

    @ApiOperation(value = "批量删除合作伙伴信息", notes = "批量删除合作伙伴信息", response = HttpMessage.class, tags={ "intellectuals", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "著作权信息没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/intellectuals/delAllPartner",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteAllPartner(@ApiParam(value = "",required=true ) @RequestParam("ids") Integer[] ids);

    @ApiOperation(value = "编辑合作伙伴信息", notes = "编辑合作伙伴信息", response = HttpMessage.class, tags={ "intellectuals", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "需求没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/intellectuals/partner/{id}",
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updatePartner(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
                                                     @ApiParam(value = "合作伙伴信息对象" ,required=true ) @RequestBody SyCooperativePartnerMiniDTO miniDTO);

    @ApiOperation(value = "获取知产出售Banner列表", notes = "获取知产出售Banner列表", response = IntellectualSaleBanner.class, responseContainer = "List", tags={ "intellectuals", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = IntellectualSaleSummary.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "页面没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/intellectuals/salesBanner",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<IntellectualSaleBanner>> getIntellectualSalesBanner();

    @ApiOperation(value = "获取知产求购Banner列表", notes = "获取知产求购Banner列表", response = IntellectualBuyBanner.class, responseContainer = "List", tags={ "intellectuals", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = IntellectualSaleSummary.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "页面没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/intellectuals/buysBanner",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<IntellectualBuyBanner>> getIntellectualBuysBanner();

    @ApiOperation(value = "通过知产求购发布(完成)", notes = "通过知产求购发布", response = HttpMessage.class, tags={ "intellectuals", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "需求没找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/intellectuals/seeks/{id}/approve",
        produces = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> approveIntellectual(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
                                                    @ApiParam(value = "理由", required = true) @RequestBody Reason reason);


    @ApiOperation(value = "通过知产出售发布(完成)", notes = "通过知产出售发布", response = HttpMessage.class, tags={ "intellectuals", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "需求没找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/intellectuals/sales/{id}/approve/{type}",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> approveIntellectualSale(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
                                                        @ApiParam(value = "类型：1商标，2专利，3著作权",required=true ) @PathVariable("type") Integer type,
                                                        @ApiParam(value = "审核原因" ,required=true ) @RequestBody IntellectualMIni reviewReason);


    @ApiOperation(value = "发布知产求购信息(完成)", notes = "发布知产求购信息", response = HttpMessage.class, tags={ "intellectuals", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "创建成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误.", response = HttpMessage.class) })
    @RequestMapping(value = "/intellectuals/seeks",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> createIntellectual(@ApiParam(value = "知产求购实体信息" ,required=true ) @RequestBody Intellectual intellectual);


    @ApiOperation(value = "发布知产出售商标信息(完成)", notes = "发布知产出售商标信息", response = HttpMessage.class, tags={ "intellectuals", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "创建成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误.", response = HttpMessage.class) })
    @RequestMapping(value = "/intellectuals/salesBrand",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> createIntellectualSaleBrand(@ApiParam(value = "知产出售实体信息" ,required=true ) @RequestBody Trademark trademark);

    @ApiOperation(value = "发布知产出售专利信息(完成)", notes = "发布知产出售专利信息", response = HttpMessage.class, tags={ "intellectuals", })
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "创建成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误.", response = HttpMessage.class) })
    @RequestMapping(value = "/intellectuals/salesPatent",
        produces = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> createIntellectualSalePatent(@ApiParam(value = "知产出售实体信息" ,required=true ) @RequestBody Patent patent);

    @ApiOperation(value = "发布知产出售著作权信息(完成)", notes = "发布知产出售著作权信息", response = HttpMessage.class, tags={ "intellectuals", })
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "创建成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误.", response = HttpMessage.class) })
    @RequestMapping(value = "/intellectuals/salesWork",
        produces = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> createIntellectualSaleWork(@ApiParam(value = "知产出售实体信息" ,required=true ) @RequestBody Copyright copyright);


    @ApiOperation(value = "删除著作权(完成)", notes = "删除著作权", response = HttpMessage.class, tags={ "intellectuals", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "著作权信息没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/intellectuals/sales/copyright/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteIntelCopyright(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "删除知产专利(完成)", notes = "删除知产专利", response = HttpMessage.class, tags={ "intellectuals", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "知产专利信息没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/intellectuals/sales/patent/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteIntelPatent(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "删除商标(完成)", notes = "删除商标", response = HttpMessage.class, tags={ "intellectuals", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "商标信息没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/intellectuals/sales/trademark/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteIntelTrademark(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "删除知产求购（完成）", notes = "删除知产求购", response = HttpMessage.class, tags={ "intellectuals", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "知产求购信息没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/intellectuals/seeks/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteIntellectual(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "获取著作权详情(完成)", notes = "获取著作权详情", response = CopyrightDetail.class, tags={ "intellectuals", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = CopyrightDetail.class),
        @ApiResponse(code = 400, message = "请求错误.", response = CopyrightDetail.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = CopyrightDetail.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = CopyrightDetail.class),
        @ApiResponse(code = 404, message = "页面没有找到", response = CopyrightDetail.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = CopyrightDetail.class) })
    @RequestMapping(value = "/intellectuals/sales/copyright/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<CopyrightDetail> getIntelCopyrightById(@ApiParam(value = "著作权id",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "获取知产专利详情(完成)", notes = "获取知产专利详情", response = PatentDetail.class, tags={ "intellectuals", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = PatentDetail.class),
        @ApiResponse(code = 400, message = "请求错误.", response = PatentDetail.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = PatentDetail.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = PatentDetail.class),
        @ApiResponse(code = 404, message = "页面没有找到", response = PatentDetail.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = PatentDetail.class) })
    @RequestMapping(value = "/intellectuals/sales/patent/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PatentDetail> getIntelPatentById(@ApiParam(value = "知产专利id",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "获取商标详情(完成)", notes = "获取商标详情", response = TrademarkDetail.class, tags={ "intellectuals", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = TrademarkDetail.class),
        @ApiResponse(code = 400, message = "请求错误.", response = TrademarkDetail.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = TrademarkDetail.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = TrademarkDetail.class),
        @ApiResponse(code = 404, message = "页面没有找到", response = TrademarkDetail.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = TrademarkDetail.class) })
    @RequestMapping(value = "/intellectuals/sales/trademark/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<TrademarkDetail> getIntelTrademarkById(@ApiParam(value = "商标id",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "获取知产求购详情(完成)", notes = "获取知产求购详情", response = IntellectualDetail.class, tags={ "intellectuals", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = IntellectualDetail.class),
        @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "页面没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/intellectuals/seeks/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<IntellectualDetail> getIntellectualById(@ApiParam(value = "知产求购id",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "获取知产出售列表(完成)", notes = "获取知产出售列表", response = IntellectualSaleSummary.class, responseContainer = "List", tags={ "intellectuals", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = IntellectualSaleSummary.class),
        @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "页面没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/intellectuals/sales",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PageInfo<IntellectualSaleSummary>> getIntellectualSales( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
         @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
         @ApiParam(value = "排序字段和方式 例如：/sales?sort=publishTime，默认按照发布时间排序。publishTime = 发布时间最新",defaultValue = "publishTime") @RequestParam(value = "sort", required = false) String sort,
         @ApiParam(value = "知识产权类别：1商标，2专利，3著作权") @RequestParam(value = "type", required = false) Integer type,
         @ApiParam(value = "交易类型：1:转让，2:许可") @RequestParam(value = "transactiinonType", required = false) Integer transactionType);


    @ApiOperation(value = "获取知产求购列表(完成)", notes = "获取知产求购列表", response = IntellectualSummary.class, responseContainer = "List", tags={ "intellectuals", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = IntellectualSummary.class),
        @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "页面没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/intellectuals/seeks",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PageInfo> getIntellectuals(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                              @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
                                              @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                              @ApiParam(value = "排序字段和方式 例如：/sales?sort=publishTime，默认按照发布时间排序。publishTime = 发布时间最新",defaultValue = "-publishTime") @RequestParam(value = "sort", required = false) String sort,
                                              @ApiParam(value = "知识产权类别：1商标，2专利，3著作权") @RequestParam(value = "type", required = false) Integer type,
                                              @ApiParam(value = "交易类型：1:转让，2:许可") @RequestParam(value = "transactionType", required = false) Integer transactionType);


    @ApiOperation(value = "获取我的知产出售列表（完成）", notes = "获取我的知产出售列表", response = IntellectualSaleMine.class, responseContainer = "List", tags={ "intellectuals", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = IntellectualSaleMine.class),
        @ApiResponse(code = 400, message = "请求错误.", response = IntellectualSaleMine.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = IntellectualSaleMine.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = IntellectualSaleMine.class),
        @ApiResponse(code = 404, message = "页面没有找到", response = IntellectualSaleMine.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = IntellectualSaleMine.class) })
    @RequestMapping(value = "/intellectuals/sales/my",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity getMyIntellectualSales( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber);


    @ApiOperation(value = "获取出售最新发布列表(完成)", notes = "获取出售最新发布列表", response = IntellectualSaleMine.class, responseContainer = "List", tags={ "intellectuals", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "操作成功", response = IntellectualSaleMine.class),
        @ApiResponse(code = 400, message = "请求错误.", response = IntellectualSaleMine.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = IntellectualSaleMine.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = IntellectualSaleMine.class),
        @ApiResponse(code = 404, message = "页面没有找到", response = IntellectualSaleMine.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = IntellectualSaleMine.class) })
    @RequestMapping(value = "/intellectuals/sales/hots",
        produces = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<List<IntellectualSaleSummary>> getIntellectualSalesHots();


    @ApiOperation(value = "获取我的知产求购列表(完成)", notes = "获取我的知产求购列表", response = IntellectualMine.class, responseContainer = "List", tags={ "intellectuals", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = IntellectualMine.class),
        @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "页面没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/intellectuals/seeks/my",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity getNyIntellectuals( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
         @ApiParam(value = "知识产权类别：1商标，2专利，3著作权") @RequestParam(value = "type", required = false) Integer type,
         @ApiParam(value = "交易类型：1:转让，2:许可") @RequestParam(value = "transactionType", required = false) Integer transactionType);

    @ApiOperation(value = "拒绝知产求购(完成)", notes = "拒绝知产求购", response = HttpMessage.class, tags={ "intellectuals", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "需求没找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/intellectuals/seeks/{id}/reject",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> rejectIntellectual(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
                                                   @ApiParam(value = "理由", required = true) @RequestBody Reason reason);


    @ApiOperation(value = "拒绝知产出售(完成)", notes = "拒绝知产出售", response = HttpMessage.class, tags={ "intellectuals", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "需求没找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/intellectuals/sales/{id}/reject/{type}",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> rejectIntellectualSale(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
                                                        @ApiParam(value = "类型：1商标，2专利，3著作权",required=true ) @PathVariable("type") Integer type,
                                                       @ApiParam(value = "审核原因" ,required=true ) @RequestBody IntellectualMIni reviewReason);


    @ApiOperation(value = "编辑著作权(完成)", notes = "编辑著作权", response = HttpMessage.class, tags={ "intellectuals", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "需求没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/intellectuals/sales/copyright/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updateIntelCopyright(@ApiParam(value = "",required=true ) @PathVariable("id") String id,
                                                     @ApiParam(value = "著作权对象" ,required=true ) @RequestBody Copyright intellectual);


    @ApiOperation(value = "编辑知产专利(完成)", notes = "编辑知产专利", response = HttpMessage.class, tags={ "intellectuals", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "需求没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/intellectuals/sales/patent/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updateIntelPatent(@ApiParam(value = "",required=true ) @PathVariable("id") String id,
        @ApiParam(value = "知产专利对象" ,required=true ) @RequestBody Patent intellectual);


    @ApiOperation(value = "编辑商标(完成)", notes = "编辑商标", response = HttpMessage.class, tags={ "intellectuals", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "需求没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/intellectuals/sales/trademark/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updateIntelTrademark(@ApiParam(value = "",required=true ) @PathVariable("id") String id,
        @ApiParam(value = "商标对象" ,required=true ) @RequestBody Trademark intellectual);


    @ApiOperation(value = "编辑知产求购（完成）", notes = "编辑知产求购", response = HttpMessage.class, tags={ "intellectuals", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "需求没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/intellectuals/seeks/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updateIntellectual(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
        @ApiParam(value = "知产求购对象" ,required=true ) @RequestBody Intellectual intellectual);

}
