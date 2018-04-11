package com.cdyoue.oddJobs.api.requirement;


import com.cdyoue.oddJobs.dto.AcceptPeopleSumary;
import com.cdyoue.oddJobs.dto.Contact;
import com.cdyoue.oddJobs.dto.Reason;
import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.requirement.*;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-20T01:12:00.448Z")

@Api(value = "requirements", description = "the requirements API")
public interface RequirementsApi {

    @ApiOperation(value = "承接需求(完成)", notes = "根据id承接需求", response = HttpMessage.class, tags={ "reqiurement", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "需求没找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/requirements/{id}/accept",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> acceptReqiurement(@ApiParam(value = "", required = true) @PathVariable("id") Integer id,
                                                  @ApiParam(value = "") @RequestBody Contact contactInfo);


    @ApiOperation(value = "审核通过需求(完成)", notes = "根据id审核通过需求", response = HttpMessage.class, tags={ "reqiurement", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "需求没找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/requirements/{id}/approve",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> approveReqiurement(@ApiParam(value = "", required = true) @PathVariable("id") Integer id,
                                                   @ApiParam(value = "理由", required = true) @RequestBody Reason reason);


    @ApiOperation(value = "取消承接需求(完成)", notes = "根据id取消承接需求", response = HttpMessage.class, tags={ "reqiurement", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "需求没找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/requirements/{id}/cancel",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> cancelReqiurement(@ApiParam(value = "", required = true) @PathVariable("id") Integer id);


    @ApiOperation(value = "关闭需求(完成)", notes = "根据id关闭需求", response = HttpMessage.class, tags={ "reqiurement", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "需求没找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/requirements/{id}/close",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> closeReqiurement(@ApiParam(value = "", required = true) @PathVariable("id") Integer id);



    @ApiOperation(value = "开启需求(完成)", notes = "根据id开启需求", response = HttpMessage.class, tags={ "reqiurement", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "需求没找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/requirements/{id}/open",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> openReqiurement(@ApiParam(value = "", required = true) @PathVariable("id") Integer id);





    @ApiOperation(value = "邀请用户参与需求(完成)", notes = "邀请用户参与需求", response = HttpMessage.class, tags={ "reqiurement", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "需求没找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/requirements/{id}/invite/{userid}",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> inviteReqiurement(@ApiParam(value = "需求id", required = true) @PathVariable("id") String id,
                                                  @ApiParam(value = "用户id", required = true) @PathVariable("userid") Integer userid);


    @ApiOperation(value = "根据id审核拒绝需求(完成)", notes = "根据id审核拒绝需求", response = HttpMessage.class, tags={ "reqiurement", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "需求没找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/requirements/{id}/reject",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> rejectReqiurement(@ApiParam(value = "", required = true) @PathVariable("id") Integer id,
                                                  @ApiParam(value = "理由", required = true) @RequestBody Reason reason
                                                  );



    @ApiOperation(value = "发布需求(完成)", notes = "PE才可以发布需求", response = HttpMessage.class, tags={ "reqiurement", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "创建成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误.", response = HttpMessage.class) })
    @RequestMapping(value = "/requirements",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> createRequirements(@ApiParam(value = "需要发布的需求实体信息", required = true) @RequestBody RequireRequest reqiurement);


    @ApiOperation(value = "删除需求(完成)", notes = "根据id删除需求", response = HttpMessage.class, tags={ "reqiurement", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "需求没找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/requirements/{id}",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteRequirements(@ApiParam(value = "", required = true) @PathVariable("id") Integer id);


    @ApiOperation(value = "获取热门人才(完成)", notes = "通过推荐系统获取热门人才", response = RecommendTalentBase.class, responseContainer = "List", tags={ "reqiurement", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = RecommendTalentBase.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "xxx 没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/requirements/{id}/recommands/talents",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<RecommendTalentBase>> getHotReqiureTalents(@ApiParam(value = "“注意！！”传入需要被推荐外包项目的【分类id(TypeId)】", required = true) @PathVariable("id") Integer id);


    @ApiOperation(value = "获取热门需求列表(完成)", notes = "通过推荐系统获取热门需求", response = RecommandResponse.class, responseContainer = "List", tags={ "reqiurement", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = RecommandResponse.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "xxx 没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/requirements/recommands",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<RecommandResponse>> getHotReqiures();


    @ApiOperation(value = "获取我的需求列表(完成)", notes = "可以获取我发起的需求，我承接的需求，我收到的需求邀请，根据type来判断获取那种需求", response = RequireMine.class, responseContainer = "List", tags={ "reqiurement", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = RequireMine.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "xxx 没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/requirements/my",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<PageInfo<RequireMine>> getMyReqiures(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                                               @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                                               @ApiParam(value = "需求类型：pub:我发布的，acc:我收到的，rec:我收到", allowableValues = "PUB, ACC, REC") @RequestParam(value = "type", required = false) String type,
                                                                               @ApiParam(value = "查询条件") @RequestParam(value = "q", required = false) String q,
                                                                               @ApiParam(value = "排序字段和方式 例如：/policies?sort=-create_time|-update_time", allowableValues = "BUDGET, PUBLISHDATE") @RequestParam(value = "sort", required = false,defaultValue = "-createTime") String sort);









    @ApiOperation(value = "获取用户发布的需求(完成)", notes = "获取用户发布的需求", response = RequireMine.class, tags={ "reqiurement", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = RequireMine.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "xxx 没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/requirements/{userId}/user",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<PageInfo<RequireMine>> getReqiure(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                        @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                        @ApiParam(value = "用户Id") @PathVariable(value = "userId", required = false) Integer userId,
                                                        @ApiParam(value = "排序字段和方式 例如：/policies?sort=-create_time|-update_time", allowableValues = "BUDGET, PUBLISHDATE") @RequestParam(value = "sort", required = false,defaultValue = "-createTime") String sort);

    @ApiOperation(value = "获取用户承接的需求(完成)", notes = "获取用户承接的需求", response = RequireMini.class, tags={ "reqiurement", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = RequireMini.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "xxx 没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/requirements/{userId}/userAccept",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity getSomeOneReqiure(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                     @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                     @ApiParam(value = "用户Id") @PathVariable(value = "userId", required = false) Integer userId,
                                                     @ApiParam(value = "查询条件") @RequestParam(value = "q", required = false) String q,
                                                     @ApiParam(value = "排序字段和方式 例如：/policies?sort=-create_time|-update_time", allowableValues = "BUDGET, PUBLISHDATE") @RequestParam(value = "sort", required = false,defaultValue = "-createTime") String sort);





    @ApiOperation(value = "获取需求详情(完成)", notes = "根据需求id获取需求详情", response = RequireComp.class, tags={ "reqiurement", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = RequireComp.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "需求没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/requirements/{id}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<RequireComp> getRequirementsById(@ApiParam(value = "", required = true) @PathVariable("id") Integer id);


    @ApiOperation(value = "获取需求列表(完成)", notes = "获取所有需求列表", response = RequireSummary.class, responseContainer = "List", tags={ "reqiurement", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = RequireSummary.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "xxx 没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/requirements",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<PageInfo> getRequirements(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                                     @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                                     @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                                                     @ApiParam(value = "分类id") @RequestParam(value = "categoryId", required = false) Integer categoryId,
                                                                     @ApiParam(value = "需求状态 0待审核，1审核失败 2审核成功',",allowableValues = "0,1,2") @RequestParam(value = "reviewStatus", required = false) Integer reviewStatus,
                                                                     @ApiParam(value = "排序字段和方式 例如：/policies?sort=-create_time|-update_time", allowableValues = "createTime, -createTime,proBudget,-proBudget") @RequestParam(value = "sort", required = false,defaultValue = "-createTime") String sort);


    @ApiOperation(value = "编辑需求(完成)", notes = "编辑需求", response = HttpMessage.class, tags={ "reqiurement", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "需求没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/requirements/{id}",
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updateRequirements(@ApiParam(value = "", required = true) @PathVariable("id") Integer id,
                                                   @ApiParam(value = "需求对象", required = true) @RequestBody RequireRequest reqiurement);


    @ApiOperation(value = "获取承接需求人员列表", notes = "可以获取承接需求人员列表", response = AcceptPeopleSumary.class, responseContainer = "List", tags={ "reqiurement", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = AcceptPeopleSumary.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "xxx 没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/requirements/{id}/acceptPeoples",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<PageInfo<AcceptPeopleSumary>> getAcceptPeoples(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                                  @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                                  @ApiParam(value = "需求id") @PathVariable(value = "id", required = true) Integer id,
                                                                  @ApiParam(value = "排序字段和方式 例如：/policies?sort=-create_time|-update_time", allowableValues = "BUDGET, PUBLISHDATE") @RequestParam(value = "sort", required = false,defaultValue = "-createTime") String sort);

    @ApiOperation(value = "获取我收到的的需求邀请(完成)", notes = "获取我收到的的需求邀请", response = InviteMeRequireSummary.class,responseContainer = "List", tags={ "reqiurement", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = InviteMeRequireSummary.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "全职没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/requirements/invite",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity getMyInviteRequirment(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                                                               @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber);





}
