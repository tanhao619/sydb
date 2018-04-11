package com.cdyoue.oddJobs.api.zlcx;


import com.cdyoue.oddJobs.dto.common.AttachmentInfoSumary;
import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.zlcx.*;
import com.cdyoue.oddJobs.entity.lgsq.PortalAttachmentEntity;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-18T06:53:23.085Z")

@Api(value = "projects", description = "the projects API")
public interface projectsApi {

    @ApiOperation(value = "获取申报项目列表", notes = "申报项目列表", response = DeclarationProjectMessageDTO.class, responseContainer = "List", tags={ "projects", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = DeclarationProjectMessageDTO.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "页面没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/projects/projects",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<PageInfo> getProjectsList(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                           @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
                                           @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                           @ApiParam(value = "排序字段和方式 例如：/sales?sort=createTime，默认按照发布时间排序。createTime = 发布时间最新",defaultValue = "-createTime") @RequestParam(value = "sort", required = false) String sort);

    @ApiOperation(value = "删除申报项目信息", notes = "删除申报项目信息", response = HttpMessage.class, tags={ "projects", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "著作权信息没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/projects/delProject/{id}",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteProject(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id);

    @ApiOperation(value = "批量删除申报项目信息", notes = "批量删除申报项目信息", response = HttpMessage.class, tags={ "projects", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "著作权信息没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/projects/delAllProject",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteAllProjects(@ApiParam(value = "",required=true ) @RequestParam("ids") Integer[] ids);

    @ApiOperation(value = "获取申报项目详细详情(完成)", notes = "获取申报项目详细详情", response = DeclarationProjectMessageDTO.class, tags={ "projects", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = DeclarationProjectMessageDTO.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "页面没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/projects/seeks/{id}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<DeclarationProjectMessageDTO> getProjectDetail(@ApiParam(value = "申报项目id",required=true ) @PathVariable("id") Integer id);

    @ApiOperation(value = "申报项目(完成)", notes = "申报项目", response = HttpMessage.class, tags={ "projects", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class) })
    @RequestMapping(value = "/projects",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> declarationProject(@ApiParam(value = "申报人信息", required = true) @RequestBody DeclarationPeopleSummary declarationPeopleSummary);


    @ApiOperation(value = "保存申报材料附件(完成)", notes = "保存申报材料附件", response = PortalAttachmentEntity.class, tags={ "projects", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class) })
    @RequestMapping(value = "/projects/attachment",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<PortalAttachmentEntity> saveProjectAttachment(@ApiParam(value = "申报附件信息", required = true) @RequestBody AttachmentInfoSumary attachmentInfoSumary);

    @ApiOperation(value = "获取项目申报详情(完成)", notes = "获取项目申报详情", response = ProjectDetail.class, tags={ "projects", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "操作成功", response = ProjectDetail.class) })
    @RequestMapping(value = "/projects/{id}",
        produces = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<ProjectDetail> getProjectById(@ApiParam(value = "项目申报id", required = true) @PathVariable("id") Integer id);


    @ApiOperation(value = "获取项目申报库列表(完成)", notes = "获取项目申报库列表", response = ProjectSummary.class, responseContainer = "List", tags={ "projects", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "操作成功", response = ProjectSummary.class) })
    @RequestMapping(value = "/projects",
        produces = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<PageInfo> getProjects(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                            @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                         @ApiParam(value = "发文时间：latestWeek:最近一周，latestMonth:最近一个月", allowableValues = "latestWeek,latestMonth") @RequestParam(value = "time",required = false) String time,
                                         @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q);

    @ApiOperation(value = "删除项目申报库(完成)", notes = "删除项目申报库", response = HttpMessage.class, tags={ "projects", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class) })
    @RequestMapping(value = "/projects",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteProject(@ApiParam(value = "项目申报ids",required=true ) @RequestParam Integer[] ids);

    @ApiOperation(value = "发布项目申报(完成)", notes = "发布项目申报", response = ProjectCreate.class, tags={ "projects", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "创建成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误.", response = HttpMessage.class) })
    @RequestMapping(value = "/create/projects",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> createProject(@ApiParam(value = "发布项目申报实体" ,required=true ) @RequestBody ProjectCreate projectCreate);


    @ApiOperation(value = "编辑项目(完成)", notes = "编辑项目", response = HttpMessage.class, tags={ "projects", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "项目没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/projects/{id}",
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updateProject(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
                                               @ApiParam(value = "项目对象信息" ,required=true ) @RequestBody ProjectCreate projectCreate);


    @ApiOperation(value = "我的项目申报(完成)", notes = "我的项目申报", response = DeclarationProjectMessageBase.class, tags={ "projects", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = DeclarationProjectMessageBase.class) })
    @RequestMapping(value = "/my/projects",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<PageInfo> getMyProjects(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                              @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber);
}
