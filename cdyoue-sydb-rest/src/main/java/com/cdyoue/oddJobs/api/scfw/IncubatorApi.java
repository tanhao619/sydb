package com.cdyoue.oddJobs.api.scfw;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.lgfc.Article;
import com.cdyoue.oddJobs.dto.scfw.Incubator;
import com.cdyoue.oddJobs.dto.scfw.IncubatorInfo;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2017/5/11.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-28T13:17:18.617Z")
@Api(value = "Incubator", description = "the IncubatorApi API")
public interface IncubatorApi {

    @ApiOperation(value = "获取孵化器详情", notes = "根据孵化器id获取孵化器详情，寻找不到报错（404）", response = IncubatorInfo.class, tags={ "Incubator", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = IncubatorInfo.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "文章没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/Incubator/{id}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<IncubatorInfo> getIncubatorById(@ApiParam(value = "孵化器ID",required=true ) @PathVariable("id") Integer id);

    @ApiOperation(value = "编辑孵化器信息(完成)", notes = "根据ID，更改对应孵化器信息", response = Incubator.class, tags={ "Incubator", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "文章没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/Incubator/{id}",
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updateArticle(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
                                              @ApiParam(value = "文章对象" ,required=true ) @RequestBody Incubator incubator);



}

