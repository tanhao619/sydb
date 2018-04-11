package com.cdyoue.oddJobs.api.scfw;


import com.cdyoue.oddJobs.dto.AreaBanner;
import com.cdyoue.oddJobs.dto.Reason;
import com.cdyoue.oddJobs.dto.zscq.IntellectualTop;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.scfw.SpaceDetail;
import com.cdyoue.oddJobs.dto.scfw.SpaceSummary;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-18T06:53:23.085Z")

@Api(value = "spaces", description = "the spaces API")
public interface SpacesApi {

    @ApiOperation(value = "场地置顶", notes = "场地置顶", response = HttpMessage.class, tags={ "spaces", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "著作权信息没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/spaces/siteTop",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> siteTop(@ApiParam(value = "",required=true ) @RequestBody IntellectualTop top);

    @ApiOperation(value = "取消场地置顶", notes = "取消场地置顶", response = HttpMessage.class, tags={ "spaces", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "著作权信息没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/spaces/removeSiteTop/{id}",
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> removeSiteTop(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id);

    @ApiOperation(value = "获取Banner空间列表", notes = "获取Banner空间列表", response = AreaBanner.class, responseContainer = "List", tags={ "spaces", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = AreaBanner.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class)})
    @RequestMapping(value = "/spaces/areaBanners",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<AreaBanner>> getAreaBanners();

    @ApiOperation(value = "发布空间（完成）", notes = "发布空间，只有企业才能发布空间", response = HttpMessage.class, tags={ "spaces", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "创建成功", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误.", response = HttpMessage.class)})
    @RequestMapping(value = "/insertSpace",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> createSpace(@ApiParam(value = "空间实体信息" ,required=true ) @RequestBody SpaceDetail space);


    @ApiOperation(value = "删除空间(完成)", notes = "删除空间", response = HttpMessage.class, tags={ "spaces", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误.", response = HttpMessage.class)})
    @RequestMapping(value = "/spaces/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteSpace(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id);

    @ApiOperation(value = "批量删除空间", notes = "批量删除空间", response = HttpMessage.class, tags={ "spaces", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误.", response = HttpMessage.class)})
    @RequestMapping(value = "/spaces/delAll",
        produces = { "application/json" },
        method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteAllSpaces(@ApiParam(value = "",required=true ) @RequestParam("ids") Integer[] ids);


    @ApiOperation(value = "获取空间详情（完成）", notes = "获取空间详情", response = SpaceDetail.class, tags={ "spaces", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = SpaceDetail.class) })
    @RequestMapping(value = "/spaceDetail/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<SpaceDetail> getSpaceById(@ApiParam(value = "空间id",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "获取空间列表（完成）", notes = "获取空间列表", response = SpaceSummary.class, responseContainer = "List", tags={ "spaces", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = SpaceSummary.class) })
    @RequestMapping(value = "/spaces",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PageInfo> getSpaces(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                       @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
                                       @ApiParam(value = "审核状态：0待审 1审核通过  2审核失败", allowableValues = "0,1,2") @RequestParam(value = "reviewStatus",required = false) Integer reviewStatus,
                                       @ApiParam(value = "空间类型：1:场地，2:工位", allowableValues = "1,2") @RequestParam(value = "type",required = false) Integer type,
                                       @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                       @ApiParam(value = "一级地理位置【省，传对应位置code值】") @RequestParam(value = "areaIdLvPre", required = false) Integer areaIdLvPre,
                                       @ApiParam(value = "二级地理位置【市，传对应位置code值】") @RequestParam(value = "areaIdLvNext", required = false) Integer areaIdLvNext);

    @ApiOperation(value = "获取我发布的空间列表（新增）（完成）", notes = "获取我发布的空间列表", response = SpaceSummary.class, responseContainer = "List", tags={ "spaces", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = SpaceSummary.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class)})
    @RequestMapping(value = "/spaces/my",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<PageInfo> getMySpaces(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                       @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
                                       @ApiParam(value = "空间类型：1:场地，2:工位", allowableValues = "1,2") @RequestParam(value = "type",required = false) Integer type,
                                       @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                       @ApiParam(value = "一级地理位置【省，传对应位置code值】") @RequestParam(value = "areaIdLvPre", required = false) Integer areaIdLvPre,
                                       @ApiParam(value = "二级地理位置【市，传对应位置code值】") @RequestParam(value = "areaIdLvNext", required = false) Integer areaIdLvNext);

    @ApiOperation(value = "编辑空间(完成)", notes = "编辑空间", response = HttpMessage.class, tags={ "spaces", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "空间没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/spaces/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updateSpace(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
        @ApiParam(value = "空间对象" ,required=true ) @RequestBody SpaceDetail space);


    @ApiOperation(value = "通过空间发布（新增）", notes = "通过空间发布", response = HttpMessage.class, tags={ "spaces", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "需求没找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/spaces/{id}/approve",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> approveNews(@ApiParam(value = "空间id",required=true ) @PathVariable("id") Integer id,
                                            @ApiParam(value = "审核原因" ,required=true ) @RequestBody Reason reason);


    @ApiOperation(value = "拒绝空间发布（新增）", notes = "拒绝空间发布", response = HttpMessage.class, tags={ "spaces", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "需求没找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/spaces/{id}/reject",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> rejectNews(@ApiParam(value = "空间id",required=true ) @PathVariable("id") Integer id,
                                           @ApiParam(value = "审核原因" ,required=true ) @RequestBody Reason reason);

}
