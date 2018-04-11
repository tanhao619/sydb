package com.cdyoue.oddJobs.api.ggfw;

import java.util.List;

import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.ggfw.CommunitySummary;
import org.springframework.web.bind.annotation.RequestParam;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-09T00:59:32.305Z")

@Api(value = "communities", description = "the communities API")
public interface CommunitiesApi {

    @ApiOperation(value = "获取社区动态列表(完成)", notes = "获取社区动态列表", response = CommunitySummary.class, responseContainer = "List", tags={ "communities", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = CommunitySummary.class),
        @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "页面没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/communities",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PageInfo> getCommunities( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                             @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber);

}
