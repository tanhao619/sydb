package com.cdyoue.oddJobs.api.zlcx;


import com.cdyoue.oddJobs.dto.zlcx.IndustryType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Api(value = "sy-industry", description = "行业分类")
public interface IndustryTypeApi {

    @ApiOperation(value = "获取行业分类", notes = "获取行业分类", response = IndustryType.class, responseContainer = "List", tags={ "sy-industry", })
    @RequestMapping(value = "/industrys",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<IndustryType>> getIndustrys(@ApiParam(value = "行业类型所属分类（0：专家分类，1：设备分类。。自行扩展）", required = true) @RequestParam(value = "type", required = true) Integer type);
}
