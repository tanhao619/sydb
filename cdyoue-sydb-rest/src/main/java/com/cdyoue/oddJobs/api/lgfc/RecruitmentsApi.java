package com.cdyoue.oddJobs.api.lgfc;

import javax.validation.constraints.NotNull;

import com.cdyoue.oddJobs.dto.lgfc.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.spring.page.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-06T22:58:08.241Z")

@Api(value = "recruitments", description = "the recruitments API")
public interface RecruitmentsApi {

    @ApiOperation(value = "删除招聘信息（新增，完成）", notes = "根据兼职/全职和id删除", response = HttpMessage.class, tags={ "recruitments", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "用户没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/recruitments/{id}",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteRecruitment(@ApiParam(value = "职位id",required=true ) @PathVariable("id") Integer id,
                                                 @NotNull @ApiParam(value = "类型：fulljob全职，partjob兼职 如/recruitments/{id}?type=fulljob", required = true, allowableValues = "FULLJOB, PARTJOB") @RequestParam(value = "type", required = true) String type);

    @ApiOperation(value = "应聘兼职/全职(完成)", notes = "应聘兼职/全职", response = HttpMessage.class, tags={ "recruitments", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "用户没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/recruitments/{id}/apply",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> applyRecruitment(@ApiParam(value = "职位id",required=true ) @PathVariable("id") Integer id,
         @NotNull @ApiParam(value = "类型：fulljob全职，partjob兼职 如/recruitments/{id}/apply?type=fulljob", required = true, allowableValues = "FULLJOB, PARTJOB") @RequestParam(value = "type", required = true) String type,
                                                 @ApiParam(value = "给雇主留言",required=false ) @RequestBody Word4Work word4Work);


    @ApiOperation(value = "获取企业发布的全职(完成)", notes = "获取企业发布的全职", response = RecruitmentsFullJobs.class,responseContainer = "List", tags={ "recruitments", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = RecruitmentsFullJobs.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "全职没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/recruitments/enterp/fulljob",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PageInfo<RecruitmentsFullJobs>> getEnterpFullRecruitments(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                                                             @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                                                                @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber);

    @ApiOperation(value = "获取我收到的的全职邀请(完成)", notes = "获取我收到的的全职邀请", response = RecruitmentsFullJobs.class,responseContainer = "List", tags={ "recruitments", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = RecruitmentsFullJobs.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "全职没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/recruitments/invite/fulljob",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<PageInfo<RecruitmentsFullJobs>> getMyInviteFullRecruitments(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                                                             @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                                                             @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber);

    @ApiOperation(value = "获取我收到的的兼职邀请(完成)", notes = "获取我收到的的兼职邀请", response = RecruitmentsPartJobs.class,responseContainer = "List", tags={ "recruitments", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = RecruitmentsPartJobs.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "全职没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/recruitments/invite/partjob",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<PageInfo<RecruitmentsPartJobs>> getMyInvitePartRecruitments(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                                                               @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                                                               @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber);

    @ApiOperation(value = "获取企业发布的兼职(完成)", notes = "获取企业发布的兼职", response = RecruitmentsPartJobs.class, responseContainer = "List", tags={ "recruitments", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = RecruitmentsPartJobs.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "兼职没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/recruitments/enterp/partjob",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PageInfo<RecruitmentsPartJobs>> getEnterpPartRecruitments( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                                                              @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber);


    @ApiOperation(value = "获取全职招聘信息列表(完成)", notes = "获取我的全职招聘信息列表", response = RecruitmentsFullMini.class,responseContainer = "List", tags={ "recruitments", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = RecruitmentsFullMini.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "用户没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/recruitments/fulljob",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PageInfo<RecruitmentsFullMini>> getFullRecruitments( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                                                        @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                                                        @ApiParam(value = "排序字段和方式 例如：/topics?sort=createTime",defaultValue = "createTime") @RequestParam(value = "sort", required = false) String sort,
                                                                        @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber);


    @ApiOperation(value = "获取兼职招聘信息列表(完成)", notes = "获取兼职招聘信息列表", response = RecruitmentsPartMini.class,responseContainer = "List", tags={ "recruitments", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = RecruitmentsPartMini.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "用户没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/recruitments/partjob",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PageInfo<RecruitmentsPartMini>> getPartRecruitments( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                                                        @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                                                        @ApiParam(value = "排序字段和方式 例如：/topics?sort=createTime",defaultValue = "createTime") @RequestParam(value = "sort", required = false) String sort,
                                                                         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber);


    @ApiOperation(value = "获取个人投递的全职 (完成)", notes = "获取个人投递的全职", response = RecruitmentsFullJobs.class,responseContainer = "List", tags={ "recruitments", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = RecruitmentsFullJobs.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "全职没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/recruitments/personal/fulljob",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PageInfo<RecruitmentsFullJobs>> getPersonalFullRecruitments( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber);


    @ApiOperation(value = "获取个人投递的兼职（完成）", notes = "获取个人投递的兼职", response = RecruitmentsPartJobs.class,responseContainer = "List", tags={ "recruitments", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = RecruitmentsPartJobs.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "兼职没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/recruitments/personal/partjob",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PageInfo<RecruitmentsPartJobs>> getPersonalPartRecruitments( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber);

//完成
    @ApiOperation(value = "获取智能推荐职位（全职）(完成)", notes = "获取智能推荐职位（全职）", response = RecommandsJob.class,responseContainer = "List", tags={ "recruitments", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = RecommandsJob.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "职位没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/recruitments/recommands/fulljob",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PageInfo<RecommandsJob>> getRecommandsFullJob( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber);

//完成
    @ApiOperation(value = "获取智能推荐职位（兼职）（完成）", notes = "获取智能推荐职位（兼职）", response = RecommandsJob.class, responseContainer = "List",tags={ "recruitments", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = RecommandsJob.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "职位没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/recruitments/recommands/partjob",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PageInfo<RecommandsJob>> getRecommandsPartJob( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber);


    @ApiOperation(value = "获取职位详细信息(完成)", notes = "获取职位详细信息", response = RecruitmentsDetail.class, tags={ "recruitments", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = RecruitmentsDetail.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "用户没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/recruitments/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<RecruitmentsDetail> getRecruitmentsDetail(@ApiParam(value = "职位id",required=true ) @PathVariable("id") Integer id,
         @NotNull @ApiParam(value = "类型：fulljob全职，partjob兼职 如/recruitments/{id}?type=fulljob", required = true, allowableValues = "FULLJOB, PARTJOB") @RequestParam(value = "type", required = true) String type
         );


    @ApiOperation(value = "邀请某用户兼职/全职（完成）", notes = "邀请某用户兼职/全职", response = HttpMessage.class, tags={ "recruitments", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "用户没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/recruitments/{id}/invite/{userID}",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> inviteUserRecruitment(@ApiParam(value = "职位id",required=true ) @PathVariable("id") Integer id,
        @ApiParam(value = "用户id",required=true ) @PathVariable("userID") Integer userID,
         @NotNull @ApiParam(value = "类型：fulljob全职，partjob兼职 如/recruitments/{id}/invite/{userID}?type=fulljob", required = true, allowableValues = "FULLJOB, PARTJOB") @RequestParam(value = "type", required = true) String type);


    @ApiOperation(value = "发布全职招聘信息（完成）", notes = "发布全职招聘信息", response = HttpMessage.class, tags={ "recruitments", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "用户没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/recruitments/fulljob",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> createFullRecruitment(@ApiParam(value = "教育经历" ,required=true ) @RequestBody RecruitmentsFullInfo recruitments);


    @ApiOperation(value = "发布兼职招聘信息（完成）", notes = "发布兼职招聘信息", response = HttpMessage.class, tags={ "recruitments", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "用户没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/recruitments/partjob",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> createPartRecruitment(@ApiParam(value = "教育经历" ,required=true ) @RequestBody RecruitmentsPartInfo recruitments);

}
