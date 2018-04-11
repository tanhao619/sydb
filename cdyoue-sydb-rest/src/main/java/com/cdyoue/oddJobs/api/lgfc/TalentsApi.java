package com.cdyoue.oddJobs.api.lgfc;


import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.lgfc.*;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-04T01:20:49.378Z")

@Api(value = "talents", description = "the talents API")
public interface TalentsApi {

    @ApiOperation(value = "新增我的职业经历(完成)", notes = "新增我的职业经历", response = HttpMessage.class, tags={ "talent", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "用户没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/talents/my/career",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> addMyCareer(@ApiParam(value = "职业经历", required = true) @RequestBody TalentCareer talentCareer);


    @ApiOperation(value = "新增我的教育经历(完成)", notes = "新增我的教育经历", response = HttpMessage.class, tags={ "talent", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "用户没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/talents/my/educations",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> addMyEducation(@ApiParam(value = "教育经历", required = true) @RequestBody TalentEducation talentEducation);


    @ApiOperation(value = "关注能人（完成）", notes = "关注能人", response = HttpMessage.class, tags={ "talent", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "用户没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/talents/{id}/follow",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> followTalent(@ApiParam(value = "用户id", required = true) @PathVariable("id") Integer id);


    @ApiOperation(value = "获取我的职业经历(完成)", notes = "获取我的职业经历", response = TalentCareer.class, responseContainer = "List", tags={ "talent", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = TalentCareer.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "用户没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/talents/my/career",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PageInfo> getMyCareer(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                           @ApiParam(value = "页码。默认第0页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber);


    @ApiOperation(value = "获取我的教育经历(完成)", notes = "获取我的教育经历", response = TalentEducation.class, responseContainer = "List", tags={ "talent", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = TalentEducation.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "用户没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/talents/my/educations",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PageInfo> getMyEducation(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                              @ApiParam(value = "页码。默认第0页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber);


    @ApiOperation(value = "获取我关注的能人（完成）", notes = "获取我关注的能人", response = TalentAbility.class, responseContainer = "List", tags={ "talent", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = TalentAbility.class),
        @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "xxx 没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/talents/my/follows",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity getMyTalents(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                     @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                     @ApiParam(value = "按照资料完整度和被邀请数排序 例如：/talents?sort=-dataCom", allowableValues = "DATACOM, INVITEDNUM") @RequestParam(value = "sort", required = false) String sort,
                                                     @ApiParam(value = "所属行业id, 过滤条件") @RequestParam(value = "industry", required = false) Integer industry);


    @ApiOperation(value = "获取能人画像(完成)", notes = "获取能人画像", response = TalentImage.class, tags={ "talent", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = TalentImage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "用户没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/talents/{id}/ability",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<TalentImage> getTalentAbility(@ApiParam(value = "用户id", required = true) @PathVariable("id") Integer id);


    @ApiOperation(value = "获取能人基本信息（完成）", notes = "获取能人基本信息", response = TalentInfo.class, tags={ "talent", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = TalentInfo.class),
        @ApiResponse(code = 400, message = "请求错误", response = TalentInfo.class),
        @ApiResponse(code = 404, message = "用户没有找到", response = TalentInfo.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = TalentInfo.class) })
    @RequestMapping(value = "/talents/{id}/base",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity getTalentBaseInfo(@ApiParam(value = "用户id", required = true) @PathVariable("id") Integer id);


    @ApiOperation(value = "获取能人经历信息（完成）", notes = "获取能人经历信息", response = HttpMessage.class, tags={ "talent", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "用户没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/talents/{id}/experience",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<TalentSummary>> getTalentExperience(@ApiParam(value = "用户id", required = true) @RequestParam("id") Integer id,
                                                      @ApiParam(value = "技能类型：0全部, 1项目经验，2教育经历，3职业经历", required = true) @RequestParam("type") Integer type);


    @ApiOperation(value = "获取能人列表（完成）", notes = "获取能人列表，找能人，根据行业过滤能人", response = TalentBase.class, responseContainer = "List", tags={ "talent", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = TalentBase.class),
        @ApiResponse(code = 400, message = "请求错误.", response = TalentBase.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = TalentBase.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = TalentBase.class),
        @ApiResponse(code = 404, message = "xxx 没有找到", response = TalentBase.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = TalentBase.class) })
    @RequestMapping(value = "/talents",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PageInfo> getTalents(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                        @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                        @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                        @ApiParam(value = "按照资料完整度和被邀请数排序 例如：/talents?sort=-dataComp") @RequestParam(value = "sort", required = false) String sort,
                                        @ApiParam(value = "所属行业id, 过滤条件") @RequestParam(value = "industry", required = false) Integer industry);


    @ApiOperation(value = "获取专家列表（完成）", notes = "获取专家列表（导师）", response = TalentBase.class, responseContainer = "List", tags={ "talent", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = TalentBase.class),
            @ApiResponse(code = 400, message = "请求错误.", response = TalentBase.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = TalentBase.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = TalentBase.class),
            @ApiResponse(code = 404, message = "xxx 没有找到", response = TalentBase.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = TalentBase.class) })
    @RequestMapping(value = "/talents/experts",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<PageInfo> getTalentsExperts(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                        @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                        @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                        @ApiParam(value = "认证类型：1实名，2大咖，3导师',",allowableValues = "1,2,3") @RequestParam(value = "expertType", required = false) Integer expertType,
                                        @ApiParam(value = "按照资料完整度和被邀请数排序 例如：/talents?sort=-dataCom") @RequestParam(value = "sort", required = false) String sort,
                                        @ApiParam(value = "所属行业id, 过滤条件") @RequestParam(value = "industry", required = false) Integer industry);


    @ApiOperation(value = "获取推荐应聘者信息列表(全职)（已完成，需要推荐系统提供有效数据，测试）", notes = "获取推荐应聘者信息列表(全职)", response = TalentBase.class, responseContainer = "List", tags={ "talent", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = TalentBase.class),
        @ApiResponse(code = 400, message = "请求错误.", response = TalentBase.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = TalentBase.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = TalentBase.class),
        @ApiResponse(code = 404, message = "没有找到", response = TalentBase.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = TalentBase.class) })
    @RequestMapping(value = "/talents/recommands/fjob",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<TalentBase>> getrRecommandsFullJob(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                           @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                           @ApiParam(value = "按照资料完整度和被邀请数排序 例如：/talents?sort=-dataCom", allowableValues = "DATACOM, INVITEDNUM") @RequestParam(value = "sort", required = false) String sort,
                                                           @ApiParam(value = "需要被推荐的企业招聘的职位id（此参数为推荐系统参数）", required = true) @RequestParam("id") Integer id);


    @ApiOperation(value = "获取推荐应聘者信息列表(兼职)（已完成，需要推荐系统提供有效数据，测试）", notes = "获取推荐应聘者信息列表(兼职)", response = TalentBase.class, responseContainer = "List", tags={ "talent", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = TalentBase.class),
        @ApiResponse(code = 400, message = "请求错误.", response = TalentBase.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = TalentBase.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = TalentBase.class),
        @ApiResponse(code = 404, message = "没有找到", response = TalentBase.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = TalentBase.class) })
    @RequestMapping(value = "/talents/recommands/pjob",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<TalentBase>> getrRecommandsPartJob(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                           @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                           @ApiParam(value = "按照资料完整度和被邀请数排序 例如：/talents?sort=-dataCom", allowableValues = "DATACOM, INVITEDNUM") @RequestParam(value = "sort", required = false) String sort,
                                                           @ApiParam(value = "需要被推荐的兼职工作的职位id（此参数为推荐系统参数）", required = true) @RequestParam("id") Integer id);


    @ApiOperation(value = "取消关注能人（完成）", notes = "取消关注能人", response = HttpMessage.class, tags={ "talent", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "用户没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/talents/{id}/unfollow",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> unfollowTalent(@ApiParam(value = "用户id", required = true) @PathVariable("id") Integer id);


    @ApiOperation(value = "修改我的职业经历(完成)", notes = "修改我的职业经历", response = HttpMessage.class, tags={ "talent", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "用户没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/talents/my/{id}/career",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updateMyCareer(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id, @ApiParam(value = "职业经历", required = true) @RequestBody TalentCareer talentCareer);


    @ApiOperation(value = "修改我的教育经历(完成)", notes = "修改我的教育经历", response = HttpMessage.class, tags={ "talent", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "用户没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/talents/my/{id}/educations",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updateMyEducation(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id, @ApiParam(value = "教育经历", required = true) @RequestBody TalentEducation talentEducation);

}
