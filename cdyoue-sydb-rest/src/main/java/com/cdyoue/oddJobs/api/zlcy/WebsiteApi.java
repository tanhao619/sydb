package com.cdyoue.oddJobs.api.zlcy;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.zlcy.WebsiteRequest;
import com.cdyoue.oddJobs.dto.zlcy.WebsiteSummary;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "sy-website", description = "网址站")
public interface WebsiteApi {

    @ApiOperation(value = "获取网址站列表", notes = "获取网址站列表", response = WebsiteSummary.class, responseContainer = "List", tags={ "sy-website", })
    @RequestMapping(value = "/websites",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<PageInfo<WebsiteSummary>> getWebsitePage(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", defaultValue="10") Integer pageSize,
                                                         @ApiParam(value = "页码。默认第一页", defaultValue = "1") @RequestParam(value = "pageNumber", defaultValue="1") Integer pageNumber,
                                                         @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q);

    @ApiOperation(value = "获取网址站首页列表", notes = "获取网址站首页列表", response = WebsiteSummary.class, responseContainer = "List", tags={ "sy-website", })
    @RequestMapping(value = "/website/list",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<WebsiteSummary>> getWebsites();

    @ApiOperation(value = "新增网址站", notes = "新增网址站", response = HttpMessage.class, tags={ "sy-website", })
    @RequestMapping(value = "/website",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> addWebsite(@ApiParam(value = "网址站表单", required = true) @RequestBody WebsiteRequest websiteRequest);

    @ApiOperation(value = "编辑网址站", notes = "编辑网址站", response = HttpMessage.class, tags={ "sy-website", })
    @RequestMapping(value = "/website/{id}",
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> editWebsite(@ApiParam(value = "专家成果id", required = true) @PathVariable("id") Integer id,
                                            @ApiParam(value = "网址站表单", required = true) @RequestBody WebsiteRequest websiteRequest);

    @ApiOperation(value = "（批量）删除网址站", notes = "（批量）删除网址站", response = HttpMessage.class, tags={ "sy-website", })
    @RequestMapping(value = "/website",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteWebsite(@ApiParam(value = "网址站ids", required = true) @RequestParam Integer[] ids);

}
