package com.cdyoue.oddJobs.api.zlcy;



import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.zlcy.TrainingCreate;
import com.cdyoue.oddJobs.dto.zlcy.TrainingDetail;
import com.cdyoue.oddJobs.dto.zlcy.TrainingSummary;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-18T06:53:23.085Z")

@Api(value = "training", description = "the training API")
public interface TrainingApi {




    @ApiOperation(value = "获取培训机构详情(完成)", notes = "获取培训机构详情", response = TrainingDetail.class, tags={ "training", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "操作成功", response = TrainingDetail.class) })
    @RequestMapping(value = "/training/{id}",
        produces = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<TrainingDetail> getTrainingById(@ApiParam(value = "培训机构id", required = true) @PathVariable("id") Integer id);


    @ApiOperation(value = "获取培训机构列表(完成)", notes = "获取培训机构列表", response = TrainingSummary.class, responseContainer = "List", tags={ "training", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "操作成功", response = TrainingSummary.class) })
    @RequestMapping(value = "/training",
        produces = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<PageInfo> getTrainings(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                         @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q);

    @ApiOperation(value = "获取推荐培训机构表(完成)", notes = "获取推荐培训机构表", response = TrainingSummary.class, responseContainer = "List", tags={ "training", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = TrainingSummary.class) })
    @RequestMapping(value = "/training/top",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<TrainingSummary>> getTopTrainings();


    @ApiOperation(value = "置顶(取消置顶)培训机构(完成)", notes = "置顶(取消置顶)培训机构", response = HttpMessage.class, tags={ "training", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class) })
    @RequestMapping(value = "/training/top/{id}",
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> topTraining(@ApiParam(value = "培训机构id", required = true) @PathVariable("id") Integer id,
                                              @ApiParam(value = "置顶图片") @RequestParam(value = "topImage",required = false) String topImage);

    @ApiOperation(value = "删除培训机构(完成)", notes = "删除培训机构", response = HttpMessage.class, tags={ "training", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class) })
    @RequestMapping(value = "/training",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteTraining(@ApiParam(value = "培训机构ids",required=true ) @RequestParam Integer[] ids);

    @ApiOperation(value = "发布培训机构(完成)", notes = "发布培训机构", response = TrainingCreate.class, tags={ "training", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "创建成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误.", response = HttpMessage.class) })
    @RequestMapping(value = "/create/training",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> createTraining(@ApiParam(value = "发布培训机构实体" ,required=true ) @RequestBody TrainingCreate trainingCreate);


    @ApiOperation(value = "编辑培训机构(完成)", notes = "编辑培训机构", response = HttpMessage.class, tags={ "training", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "项目没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/training/{id}",
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updateTraining(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
                                                 @ApiParam(value = "培训机构对象信息" ,required=true ) @RequestBody TrainingCreate trainingCreate);
}
