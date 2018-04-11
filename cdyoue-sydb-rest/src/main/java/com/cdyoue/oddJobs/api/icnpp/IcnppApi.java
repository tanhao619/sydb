package com.cdyoue.oddJobs.api.icnpp;

import com.cdyoue.oddJobs.dto.algorithm.AlgorithmBase;
import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.home.PageHome;
import com.cdyoue.oddJobs.dto.icnpp.policy.PolicyBase;
import com.cdyoue.oddJobs.dto.icnpp.policy.PolicyDetail;
import com.cdyoue.oddJobs.dto.icnpp.policy.PolicySumary;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by liaoyoule on 2017/6/21.
 */
@Api(value = "icnpps", description = "the icnpp API")
public interface IcnppApi {
    @ApiOperation(value = "获取政策列表（完成）", notes = "获取政策列表", response = PolicySumary.class, responseContainer = "List", tags={ "icnpps", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = PolicySumary.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "xxx 没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/policys",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<PageInfo<PolicySumary>> getPolices(
            @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q
    ) ;

    @ApiOperation(value = "获取政策详情（完成）", notes = "获取政策详情", response = PolicyDetail.class, responseContainer = "List", tags={ "icnpps", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = PolicyDetail.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "xxx 没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/policys/{id}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<PolicyDetail> getPolice(
            @ApiParam(value = "政策id") @PathVariable(value = "id") String id) ;



}
