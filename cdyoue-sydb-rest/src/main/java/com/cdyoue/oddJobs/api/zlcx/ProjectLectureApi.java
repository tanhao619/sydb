package com.cdyoue.oddJobs.api.zlcx;
import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.zlcx.*;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**
 * Created by sky on 2017/9/19.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-18T06:53:23.085Z")

@Api(value = "projectLecture", description = "the projectLecture API")
public interface ProjectLectureApi {

        @ApiOperation(value = "获取项目讲座详情(完成)", notes = "获取项目讲座详情", response = ProjectLectureDetail.class, tags={ "projectLecture", })
        @ApiResponses(value = {
                @ApiResponse(code = 200, message = "操作成功", response = ProjectLectureDetail.class) })
        @RequestMapping(value = "/projectLecture/{id}",
                produces = { "application/json" },
                method = RequestMethod.GET)
        ResponseEntity<ProjectLectureDetail> getProjectLectureById(@ApiParam(value = "项目讲座id", required = true) @PathVariable("id") Integer id);


        @ApiOperation(value = "获取项目讲座列表(完成)", notes = "获取项目讲座列表", response = ProjectLectureSummary.class, responseContainer = "List", tags={ "projectLecture", })
        @ApiResponses(value = {
                @ApiResponse(code = 200, message = "操作成功", response = ProjectLectureSummary.class) })
        @RequestMapping(value = "/projectLecture",
                produces = { "application/json" },
                method = RequestMethod.GET)
        ResponseEntity<PageInfo> getProjectLecture(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                             @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                             @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q);

        @ApiOperation(value = "获取置顶项目讲座(完成)", notes = "获取置顶项目讲座", response = ProjectLectureSummary.class, tags={ "projectLecture", })
        @ApiResponses(value = {
                @ApiResponse(code = 200, message = "操作成功", response = ProjectLectureSummary.class) })
        @RequestMapping(value = "/projectLecture/top",
                produces = { "application/json" },
                method = RequestMethod.GET)
        ResponseEntity<ProjectLectureSummary> getTopProjectLecture();

        @ApiOperation(value = "置顶(取消置顶)项目讲座(完成)", notes = "置顶(取消置顶)项目讲座", response = HttpMessage.class, tags={ "projectLecture", })
        @ApiResponses(value = {
                @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class) })
        @RequestMapping(value = "/projectLecture/top/{id}",
                produces = { "application/json" },
                method = RequestMethod.PUT)
        ResponseEntity<HttpMessage> topProjectLecture(@ApiParam(value = "项目讲座id", required = true) @PathVariable("id") Integer id,
                                                      @ApiParam(value = "置顶图片") @RequestParam(value="topImage", required = false) String topImage);

        @ApiOperation(value = "删除项目讲座(完成)", notes = "删除项目讲座", response = HttpMessage.class, tags={ "projectLecture", })
        @ApiResponses(value = {
                @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class) })
        @RequestMapping(value = "/projectLecture",
                produces = { "application/json" },
                method = RequestMethod.DELETE)
        ResponseEntity<HttpMessage> deleteProjectLecture(@ApiParam(value = "项目讲座ids",required=true ) @RequestParam Integer[] ids);

        @ApiOperation(value = "发布项目讲座(完成)", notes = "发布项目讲座", response = ProjectLectureCreate.class, tags={ "projectLecture", })
        @ApiResponses(value = {
                @ApiResponse(code = 200, message = "创建成功", response = HttpMessage.class),
                @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
                @ApiResponse(code = 500, message = "系统内部错误.", response = HttpMessage.class) })
        @RequestMapping(value = "/create/projectLecture",
                produces = { "application/json" },
                method = RequestMethod.POST)
        ResponseEntity<HttpMessage> createProjectLecture(@ApiParam(value = "发布项目讲座实体" ,required=true ) @RequestBody ProjectLectureCreate projectLectureCreate);

        @ApiOperation(value = "编辑项目讲座(完成)", notes = "编辑项目讲座", response = HttpMessage.class, tags={ "projectLecture", })
        @ApiResponses(value = {
                @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
                @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
                @ApiResponse(code = 404, message = "项目没有找到", response = HttpMessage.class),
                @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
        @RequestMapping(value = "/projectLecture/{id}",
                produces = { "application/json" },
                method = RequestMethod.PUT)
        ResponseEntity<HttpMessage> updateProjectLecture(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
                                                                @ApiParam(value = "项目讲座对象信息" ,required=true ) @RequestBody ProjectLectureCreate projectLectureCreate);
}
