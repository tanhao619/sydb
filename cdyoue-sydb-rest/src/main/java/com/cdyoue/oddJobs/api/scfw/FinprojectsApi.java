package com.cdyoue.oddJobs.api.scfw;


import com.cdyoue.oddJobs.dto.Reason;
import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.scfw.FinProjectDetail;
import com.cdyoue.oddJobs.dto.scfw.FinProjectSummary;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-23T00:52:52.589Z")

@Api(value = "finprojects", description = "the finprojects API")
public interface FinprojectsApi {

    @ApiOperation(value = "审核通过融资项目(完成)", notes = "根据id审核通过融资项目", response = HttpMessage.class, tags={ "finprojects", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class) })
    @RequestMapping(value = "/finprojects/{id}/approve",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> approveFinproject(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,@ApiParam(value = "Reason" ,required=false ) @RequestBody Reason Reason);


    @ApiOperation(value = "发布项目(完成)", notes = "发布项目，只有企业才能发布项目", response = HttpMessage.class, tags={ "finprojects", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "创建成功", response = HttpMessage.class) })
    @RequestMapping(value = "/finprojects",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> createFinProject(@ApiParam(value = "项目实体信息" ,required=true ) @RequestBody FinProjectDetail finproject);


    @ApiOperation(value = "删除项目(完成)", notes = "删除项目", response = HttpMessage.class, tags={ "finprojects", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class) })
    @RequestMapping(value = "/finprojects/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteFinProject(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "下载项目附件(完成)", notes = "下载项目附件", response = FinProjectDetail.class, tags={ "finprojects", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = FinProjectDetail.class) })
    @RequestMapping(value = "/finprojects/{id}/download", produces = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<FinProjectDetail> downloadAttachById(@ApiParam(value = "项目id",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "获取项目详情(完成)", notes = "获取项目详情", response = FinProjectDetail.class, tags={ "finprojects", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = FinProjectDetail.class) })
    @RequestMapping(value = "/finprojects/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<FinProjectDetail> getFinProjectById(@ApiParam(value = "项目id",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "获取融资项目列表(完成)", notes = "获取融资项目列表", response = FinProjectSummary.class, responseContainer = "List", tags={ "finprojects", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = FinProjectSummary.class) })
    @RequestMapping(value = "/finprojects",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity getFinProjects(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                            @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
                                            @ApiParam(value = "审核状态，待审核:0，审核通过:1，审核不通过:2", required = false, allowableValues = "0, 1, 2") @RequestParam(value = "statusFilter", required = false) Byte statusFilter,
                                            @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q);


    @ApiOperation(value = "获取我发布的融资项目列表(完成)", notes = "获取我发布的融资项目列表", response = FinProjectSummary.class, responseContainer = "List", tags={ "finprojects", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = FinProjectSummary.class) })
    @RequestMapping(value = "/finprojects/my",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity getMyFinProjects( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第0页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
         @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q);


    @ApiOperation(value = "拒绝通过融资项目(完成)", notes = "拒绝通过融资项目", response = HttpMessage.class, tags={ "finprojects", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class) })
    @RequestMapping(value = "/finprojects/{id}/reject",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> rejectFinproject(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,@ApiParam(value = "Reason" ,required=false ) @RequestBody Reason Reason);


    @ApiOperation(value = "编辑融资项目(完成)", notes = "编辑融资项目", response = HttpMessage.class, tags={ "finprojects", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class) })
    @RequestMapping(value = "/finprojects/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updateFinProject(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
        @ApiParam(value = "项目对象" ,required=true ) @RequestBody FinProjectDetail finproject);

}
