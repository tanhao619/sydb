package com.cdyoue.oddJobs.api.zlcx.financing;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.zlcx.FinancingDTO.FinancingTop;
import com.cdyoue.oddJobs.entity.syData.FinancingEntity;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2017/9/20.
 */
@Api(value = "financing", description = "the financing API")
public interface FinancingApi {

    @ApiOperation(value = "获取TOP", notes = "获取融资top", response = FinancingTop.class, tags={ "financing", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = FinancingTop.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "文章没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/financing/top",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<FinancingTop> getFinancingTop();

    @ApiOperation(value = "获取融资分页列表（前台）", notes = "", response = FinancingTop.class, responseContainer = "List", tags={ "financing", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = FinancingTop.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "文章没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/financing",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<PageInfo> getFinancingPageList(
            @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
            @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
            @ApiParam(value = "排序字段和方式", defaultValue = "-createTime") @RequestParam(value = "sort", required = false) String sort,
            @ApiParam(value = "状态分类",defaultValue = "2") @RequestParam(value = "0",defaultValue = "2", required = false) Integer type,
            @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q
    );

    @ApiOperation(value = "获取融资分页列表（后台）", notes = "", response = FinancingTop.class, responseContainer = "List", tags={ "financing", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = FinancingTop.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "文章没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/financing/back",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<PageInfo> getFinancingBackPageList(
            @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
            @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
            @ApiParam(value = "排序字段和方式", defaultValue = "-createTime") @RequestParam(value = "sort", required = false) String sort,
            @ApiParam(value = "审核状态筛选", defaultValue = "") @RequestParam(value = "status", required = false) String status,
            @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q
    );

    @ApiOperation(value = "获取融资详情（完成）", notes = "通过id产需详情", response = FinancingEntity.class, tags={ "financing", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = FinancingEntity.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "文章没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/financing/{id}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<FinancingEntity> getFinancingById(@ApiParam(value = "1",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "审核融资项目", notes = "", response = HttpMessage.class, tags={ "financing", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "取消收藏成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误.", response = HttpMessage.class) })
    @RequestMapping(value = "/financing/{id}/{status}/uncollect",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> uncollectFinancing(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
                                                   @ApiParam(value = "",required=true ) @PathVariable("status") Integer status);

    @ApiOperation(value = "融资顶置", notes = "", response = HttpMessage.class, tags={ "financing", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "取消收藏成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误.", response = HttpMessage.class) })
    @RequestMapping(value = "/financing/top",
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> madeTop(@ApiParam(value = "",required=true )  @RequestBody FinancingEntity financingEntity);


    @ApiOperation(value = "取消融资顶置", notes = "", response = HttpMessage.class, tags={ "financing", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "取消收藏成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误.", response = HttpMessage.class) })
    @RequestMapping(value = "/financing/{id}/upTop",
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> cancelTop(@ApiParam(value = "",required=true )  @PathVariable("id") Integer id);

    @ApiOperation(value = "融资审核", notes = "", response = HttpMessage.class, tags={ "financing", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "取消收藏成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误.", response = HttpMessage.class) })
    @RequestMapping(value = "/financing/examine",
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> toExamine(@ApiParam(value = "",required=true )  @RequestBody FinancingEntity financingEntity);

    @ApiOperation(value = "删除融资（完成）", notes = "", response = HttpMessage.class, tags={ "financing", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "文章没找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/financing/{id}",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteFinancing(@ApiParam(value = "",required=true ) @PathVariable("id") List<Integer> id);

    @ApiOperation(value = "新建融资需求(完成)", notes = "创建的设备实体", response = FinancingEntity.class, tags={ "financing", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "创建成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误.", response = HttpMessage.class) })
    @RequestMapping(value = "/financing",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> createFinancing(@ApiParam(value = "创建的设备实体" ,required=true ) @RequestBody FinancingEntity financingEntity);

    @ApiOperation(value = "获取我的融资分页列表", notes = "", response = FinancingTop.class, responseContainer = "List", tags={ "financing", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = FinancingTop.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "文章没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/financing/my",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<PageInfo> getMyFinancingPageList(
            @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
            @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
            @ApiParam(value = "排序字段和方式", defaultValue = "createTime") @RequestParam(value = "sort", required = false) String sort
    );


}
