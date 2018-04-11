package com.cdyoue.oddJobs.api.zlcx.equipment;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.zlcx.EquipmentDTO.EquipmentApplyDTO;
import com.cdyoue.oddJobs.dto.zlcx.EquipmentDTO.EquipmentDetail;
import com.cdyoue.oddJobs.dto.zlcx.EquipmentDTO.EquipmentInfo;
import com.cdyoue.oddJobs.dto.zlcx.EquipmentDTO.EquipmentTop;
import com.cdyoue.oddJobs.entity.syData.EquipmentApplyEntity;
import com.cdyoue.oddJobs.entity.syData.EquipmentEntity;
import com.cdyoue.oddJobs.entity.syData.SyEquipmentApplyView;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2017/9/18.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-28T13:17:18.617Z")

@Api(value = "equipment", description = "the equipment API")
public interface EquipmentApi {

    @ApiOperation(value = "获取TOP8列表（完成）", notes = "获取助力创新页面设备top8,按创建时间降序排列", response = EquipmentTop.class, responseContainer = "List", tags={ "equipment", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = EquipmentTop.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "文章没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/equipment/top",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<EquipmentTop>> getEquipmentTop();

    @ApiOperation(value = "获取设备分页列表（完成）", notes = "", response = EquipmentTop.class, responseContainer = "List", tags={ "equipment", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = EquipmentTop.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "文章没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/equipment",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<PageInfo> getEquipmentPageList(
                                         @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
                                         @ApiParam(value = "排序字段和方式", defaultValue = "createTime",allowableValues = "createTime,viewCount") @RequestParam(value = "sort", required = false) String sort,
                                         @ApiParam(value = "行业分类") @RequestParam(value = "type", required = false) Integer type,
                                         @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q
    );

    @ApiOperation(value = "获取设备详情（完成）", notes = "通过id产需详情", response = EquipmentDetail.class, tags={ "equipment", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = EquipmentDetail.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "文章没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/equipment/{id}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<EquipmentDetail> getEquipmentById(@ApiParam(value = "1",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "创建设备(完成)", notes = "创建的设备实体", response = EquipmentEntity.class, tags={ "equipment", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "创建成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误.", response = HttpMessage.class) })
    @RequestMapping(value = "/equipment",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> createEquipment(@ApiParam(value = "创建的设备实体" ,required=true ) @RequestBody EquipmentEntity equipmentEntity);


    @ApiOperation(value = "删除设备（完成）", notes = "", response = HttpMessage.class, tags={ "equipment", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "文章没找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/equipment/{id}",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteEquipment(@ApiParam(value = "",required=true ) @PathVariable("id") List<Integer> id);


    @ApiOperation(value = "编辑设备(完成)", notes = "编辑设备", response = HttpMessage.class, tags={ "equipment", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "文章没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/equipment",
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updateEquipment(@ApiParam(value = "设备对象" ,required=true ) @RequestBody EquipmentInfo equipmentInfo);

    @ApiOperation(value = "设备顶置", notes = "", response = HttpMessage.class, tags={ "equipment", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "取消收藏成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误.", response = HttpMessage.class) })
    @RequestMapping(value = "/equipment/top",
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> madeTop(@ApiParam(value = "",required=true ) @RequestBody EquipmentEntity equipmentEntity);

    @ApiOperation(value = "取消设备顶置", notes = "", response = HttpMessage.class, tags={ "equipment", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "取消收藏成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误.", response = HttpMessage.class) })
    @RequestMapping(value = "/equipment/{id}/upTop",
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> cancelTop(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id);

    @ApiOperation(value = "收藏设备", notes = "", response = HttpMessage.class, tags={ "equipment", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "取消收藏成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误.", response = HttpMessage.class) })
    @RequestMapping(value = "/equipment/{id}/collection",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> collectionEquipment(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id);

    @ApiOperation(value = "取消收藏设备", notes = "", response = HttpMessage.class, tags={ "equipment", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "取消收藏成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误.", response = HttpMessage.class) })
    @RequestMapping(value = "/equipment/{id}/cancelCollection",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> cancelCollectionEquipment(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id);

    //=================================================申请设备===================================================//
    @ApiOperation(value = "申请设备(完成)", notes = "创建的申请信息", response = EquipmentApplyEntity.class, tags={ "equipment", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "创建成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误.", response = HttpMessage.class) })
    @RequestMapping(value = "/equipment/apply",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> applyEquipment(@ApiParam(value = "提交申请信息" ,required=true ) @RequestBody EquipmentApplyEntity equipmentApplyEntity);


    @ApiOperation(value = "获取申请详情(完成)", notes = "通过id查找申请详情", response = EquipmentApplyEntity.class, tags={ "equipment", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = EquipmentApplyEntity.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "文章没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/equipment/apply/{id}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<EquipmentApplyDTO> getEquipmentApplyById(@ApiParam(value = "1",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "获取设备申请分页列表(完成)", notes = "", response = SyEquipmentApplyView.class, responseContainer = "List", tags={ "equipment", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = SyEquipmentApplyView.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "文章没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/equipment/apply",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<PageInfo> getEquipmentApplyPageList(
            @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
            @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
            @ApiParam(value = "排序字段和方式", defaultValue = "-createTime") @RequestParam(value = "sort", required = false) String sort,
            @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q
    );

    @ApiOperation(value = "删除申请(完成)", notes = "通过id删除申请", response = HttpMessage.class, tags={ "equipment", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "文章没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/equipment/apply/{id}",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteEquipmentApplyById(@ApiParam(value = "1",required=true ) @PathVariable("id") List<Integer> id);

}
