package com.cdyoue.oddJobs.api.advertisement;

import com.cdyoue.oddJobs.dto.advertisement.Advertisement;
import com.cdyoue.oddJobs.dto.advertisement.advertisementInfo;
import com.cdyoue.oddJobs.dto.common.HttpMessage;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-28T13:17:18.617Z")

@Api(value = "advertisement", description = "the advertisement API")
public interface AdvertisementApi {

    @ApiOperation(value = "获取某个页面的banner图列表", notes = "获取某个页面的banner图列表", response = Advertisement.class, tags={ "advertisement", })
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "操作成功", response = Advertisement.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "文章没找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/advertisement/{view}",
        produces = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<List<Advertisement>> getAdvertisements(@ApiParam(value = "页面名称编号，如：首页：sy；产业洞察：cydc；数据服务：sjfw；助力创新：zlcx；助力创业：zlcy；助力创新—设备库：zlcxsbk", required = true) @PathVariable("view") String view);

    @ApiOperation(value = "编辑banner图", notes = "编辑banner图", response = advertisementInfo.class, tags={ "advertisement", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "文章没找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/advertisement/{view}/{id}",
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updateAdvertisement(@ApiParam(value = "页面名称，如：首页：sy；产业洞察：cydc；数据服务：sjfw；助力创新：zlcx；助力创业：zlcy；助力创新—设备库：zlcxsbk",required=true ) @PathVariable("view") String view,
                                               @ApiParam(value = "广告排序ID：左中右：1,2,3",required=true ) @PathVariable("id") Integer id,
                                               @ApiParam(value = "广告对象" ,required=true ) @RequestBody advertisementInfo advertisement);

    @ApiOperation(value = "查看banner图", notes = "根据一个", response = Advertisement.class, tags={ "advertisement", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = Advertisement.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "文章没找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/advertisement/{view}/{id}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<Advertisement> getAdvertisement(@ApiParam(value = "页面名称，如：首页：sy；产业洞察：cydc；数据服务：sjfw；助力创新：zlcx；助力创业：zlcy；助力创新—设备库：zlcxsbk", required = true) @PathVariable("view") String view,
                                            @ApiParam(value = "广告排序ID：左中右：1,2,3", required = true) @PathVariable("id") Integer id);

}
