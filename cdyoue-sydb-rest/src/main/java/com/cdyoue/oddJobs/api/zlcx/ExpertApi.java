package com.cdyoue.oddJobs.api.zlcx;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.zlcx.*;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "sy-expert", description = "专家")
public interface ExpertApi {

    @ApiOperation(value = "获取专家基本信息", notes = "获取专家基本信息", response = ExpertBaseinfo.class, tags={ "sy-expert", })
    @RequestMapping(value = "/expert/{id}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<ExpertBaseinfo> getExpert(@ApiParam(value = "专家id",required=true ) @PathVariable("id") Integer id);

    @ApiOperation(value = "获取专家列表", notes = "获取专家列表", response = ExpertSummary.class, responseContainer = "List", tags={ "sy-expert", })
    @RequestMapping(value = "/experts",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<PageInfo<ExpertSummary>> getExperts(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", defaultValue="10") Integer pageSize,
                                                       @ApiParam(value = "页码。默认第一页", defaultValue = "1") @RequestParam(value = "pageNumber", defaultValue="1") Integer pageNumber,
                                                       @ApiParam(value = "行业分类") @RequestParam(value = "industryType", required = false) Integer industryType,
                                                       @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q);

    @ApiOperation(value = "获取专家首页top3", notes = "获取专家首页top3", response = ExpertTop.class, responseContainer = "List", tags={ "sy-expert", })
    @RequestMapping(value = "/expert/top",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<ExpertTop>> getExpertTop();

    @ApiOperation(value = "联系专家", notes = "联系专家", response = HttpMessage.class, tags={ "sy-expert", })
    @RequestMapping(value = "/expert/{id}/contact",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> contactExpert(@ApiParam(value = "专家id", required=true ) @PathVariable("id") Integer id,
                                              @ApiParam(value = "联系专家表单", required = true) @RequestBody ExpertContactRequest expertContactRequest);

    @ApiOperation(value = "新增专家", notes = "新增专家", response = HttpMessage.class, tags={ "sy-expert", })
    @RequestMapping(value = "/expert",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> addExpert(@ApiParam(value = "专家表单", required = true) @RequestBody ExpertRequest expertRequest);

    @ApiOperation(value = "编辑专家", notes = "编辑专家", response = HttpMessage.class, tags={ "sy-expert", })
    @RequestMapping(value = "/expert/{id}",
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> editExpert(@ApiParam(value = "专家id", required=true ) @PathVariable("id") Integer id,
                                           @ApiParam(value = "专家表单", required = true) @RequestBody ExpertRequest expertRequest);

    @ApiOperation(value = "（批量）删除专家", notes = "（批量）删除专家", response = HttpMessage.class, tags={ "sy-expert", })
    @RequestMapping(value = "/expert",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteExpert(@ApiParam(value = "专家ids", required = true) @RequestParam Integer[] ids);

    @ApiOperation(value = "（取消）置顶专家", notes = "（取消）置顶专家", response = HttpMessage.class, tags={ "sy-expert", })
    @RequestMapping(value = "/expert/{id}/top",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> topExpert(@ApiParam(value = "专家id", required=true ) @PathVariable("id") Integer id,
                                          @ApiParam(value = "图片url") @RequestParam(value = "topImgUrl") String topImgUrl);

    @ApiOperation(value = "获取专家下拉列表", notes = "获取专家下拉列表", response = ExpertMini.class, responseContainer = "List", tags={ "sy-expert", })
    @RequestMapping(value = "/expert/select",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<ExpertMini>> getExpertSelect(@ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q);

    @ApiOperation(value = "获取专家联系列表", notes = "获取专家联系列表", response = ExpertContactSummary.class, responseContainer = "PageInfo<List>", tags={ "sy-expert", })
    @RequestMapping(value = "/expert/contacts",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<PageInfo<ExpertContactSummary>> getExpertContacts(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", defaultValue="10") Integer pageSize,
                                                       @ApiParam(value = "页码。默认第一页", defaultValue = "1") @RequestParam(value = "pageNumber", defaultValue="1") Integer pageNumber,
                                                       @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q);

    @ApiOperation(value = "（批量）删除专家联系", notes = "（批量）删除专家联系", response = HttpMessage.class, tags={ "sy-expert", })
    @RequestMapping(value = "/expert/contact",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteExpertContact(@ApiParam(value = "专家联系ids", required = true) @RequestParam Integer[] ids);

}
