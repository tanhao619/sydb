package com.cdyoue.oddJobs.api.zlcx;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.zlcx.ExpertAchievementDetail;
import com.cdyoue.oddJobs.dto.zlcx.ExpertAchievementRequest;
import com.cdyoue.oddJobs.dto.zlcx.ExpertAchievementSummary;
import com.cdyoue.oddJobs.dto.zlcx.ExpertAchievementTop;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "sy-expertAchievement", description = "专家成果")
public interface ExpertAchievementApi {

    @ApiOperation(value = "获取专家成果详细信息", notes = "获取专家成果详细信息", response = ExpertAchievementDetail.class, tags={ "sy-expertAchievement", })
    @RequestMapping(value = "/expertAchievement/{id}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<ExpertAchievementDetail> getExpertAchievement(@ApiParam(value = "专家成果id",required=true ) @PathVariable("id") Integer id);

    @ApiOperation(value = "获取专家的专家成果列表", notes = "获取专家的专家成果列表", response = ExpertAchievementSummary.class, responseContainer = "List", tags={ "sy-expert", })
    @RequestMapping(value = "/expert/{expertId}/expertAchievement",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<ExpertAchievementSummary>> getExpertAchievementExpert(@ApiParam(value = "专家id", required = true) @PathVariable("expertId") Integer expertId);

    @ApiOperation(value = "获取专家成果列表", notes = "获取专家成果列表", response = ExpertAchievementSummary.class, responseContainer = "List", tags={ "sy-expertAchievement", })
    @RequestMapping(value = "/expertAchievements",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<PageInfo<ExpertAchievementSummary>> getExpertAchievements(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", defaultValue="10") Integer pageSize,
                                                                             @ApiParam(value = "页码。默认第一页", defaultValue = "1") @RequestParam(value = "pageNumber", defaultValue="1") Integer pageNumber,
                                                                             @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q);

    @ApiOperation(value = "获取专家成果首页top2", notes = "获取专家成果首页top2", response = ExpertAchievementTop.class, responseContainer = "List", tags={ "sy-expertAchievement", })
    @RequestMapping(value = "/expertAchievement/top",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<ExpertAchievementTop>> getExpertAchievementTop();

    @ApiOperation(value = "新增专家成果", notes = "新增专家成果", response = HttpMessage.class, tags={ "sy-expertAchievement", })
    @RequestMapping(value = "/expertAchievement",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> addExpertAchievement(@ApiParam(value = "专家成果表单", required = true) @RequestBody ExpertAchievementRequest expertAchievementRequest);

    @ApiOperation(value = "编辑专家成果", notes = "编辑专家成果", response = HttpMessage.class, tags={ "sy-expertAchievement", })
    @RequestMapping(value = "/expertAchievement/{id}",
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> editExpertAchievement(@ApiParam(value = "专家成果id", required=true ) @PathVariable("id") Integer id,
                                                      @ApiParam(value = "专家成果表单", required = true) @RequestBody ExpertAchievementRequest expertAchievementRequest);

    @ApiOperation(value = "（批量）删除专家成果", notes = "（批量）删除专家成果", response = HttpMessage.class, tags={ "sy-expertAchievement", })
    @RequestMapping(value = "/expertAchievement",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteExpertAchievement(@ApiParam(value = "专家成果ids", required = true) @RequestParam Integer[] ids);

    @ApiOperation(value = "（取消）置顶专家成果", notes = "（取消）置顶专家成果", response = HttpMessage.class, tags={ "sy-expertAchievement", })
    @RequestMapping(value = "/expertAchievement/{id}/top",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> topExpertAchievement(@ApiParam(value = "专家成果id", required = true ) @PathVariable("id") Integer id,
                                                     @ApiParam(value = "图片url") @RequestParam(value = "topImgUrl") String topImgUrl);

    @ApiOperation(value = "（取消）收藏专家成果", notes = "（取消）收藏专家成果", response = HttpMessage.class, tags={ "sy-expertAchievement", })
    @RequestMapping(value = "/expertAchievement/{id}/collect",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> collectExpertAchievement(@ApiParam(value = "专家成果id", required=true ) @PathVariable("id") Integer id);

    @ApiOperation(value = "获取我收藏的专家成果", notes = "获取我收藏的专家成果", response = ExpertAchievementSummary.class, responseContainer = "List", tags={ "sy-expertAchievement", })
    @RequestMapping(value = "/expertAchievement/my/collect",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<PageInfo<ExpertAchievementSummary>> getMyCollectExpertAchievement(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", defaultValue="10") Integer pageSize,
                                                                                     @ApiParam(value = "页码。默认第一页", defaultValue = "1") @RequestParam(value = "pageNumber", defaultValue="1") Integer pageNumber);
}
