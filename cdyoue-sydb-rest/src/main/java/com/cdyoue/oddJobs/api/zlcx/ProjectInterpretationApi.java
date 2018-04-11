package com.cdyoue.oddJobs.api.zlcx;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.zlcx.ProjectInterpretationBase;
import com.cdyoue.oddJobs.dto.zlcx.ProjectInterpretationDetail;
import com.cdyoue.oddJobs.dto.zlcx.ProjectInterpretationSummary;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sky on 2017/9/19.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-18T06:53:23.085Z")

@Api(value = "projectInterpretation", description = "the projectInterpretation API")
public interface ProjectInterpretationApi {
    @ApiOperation(value = "获取项目解读详情(完成)", notes = "获取项目解读详情", response = ProjectInterpretationDetail.class, tags={ "projectInterpretation", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = ProjectInterpretationDetail.class) })
    @RequestMapping(value = "/projectInterpretation/{id}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<ProjectInterpretationDetail> getProjectInterpretationById(@ApiParam(value = "项目解读id", required = true) @PathVariable("id") Integer id);


    @ApiOperation(value = "获取项目解读列表(完成)", notes = "获取项目解读列表", response = ProjectInterpretationSummary.class, responseContainer = "List", tags={ "projectInterpretation", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = ProjectInterpretationSummary.class) })
    @RequestMapping(value = "/projectInterpretation",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<PageInfo> getProjectInterpretation(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                               @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                      @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q);

    @ApiOperation(value = "删除项目解读(完成)", notes = "删除项目解读", response = HttpMessage.class, tags={ "projectInterpretation", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class) })
    @RequestMapping(value = "/projectInterpretation",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteProjectInterpretation(@ApiParam(value = "项目解读ids",required=true ) @RequestParam Integer[] ids);

    @ApiOperation(value = "发布项目解读(完成)", notes = "发布项目解读", response = ProjectInterpretationBase.class, tags={ "projectInterpretation", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "创建成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误.", response = HttpMessage.class) })
    @RequestMapping(value = "/create/projectInterpretation",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> createProjectInterpretation(@ApiParam(value = "发布项目解读实体" ,required=true ) @RequestBody ProjectInterpretationBase projectInterpretationBase);

    @ApiOperation(value = "编辑项目解读(完成)", notes = "编辑项目解读", response = HttpMessage.class, tags={ "projectInterpretation", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "项目没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/projectInterpretation/{id}",
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updateProjectInterpretation(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
                                              @ApiParam(value = "项目解读对象信息" ,required=true ) @RequestBody ProjectInterpretationBase projectInterpretationBase);
}
