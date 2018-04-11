package com.cdyoue.oddJobs.api.zlcx;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.zlcx.ProfessionalInterpretationDetail;
import com.cdyoue.oddJobs.dto.zlcx.ProfessionalInterpretationRequest;
import com.cdyoue.oddJobs.dto.zlcx.ProfessionalInterpretationSummary;
import com.cdyoue.oddJobs.dto.zlcx.ProfessionalInterpretationTop;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "sy-professionalInterpretation", description = "专业解读")
public interface ProfessionalInterpretationApi {

    @ApiOperation(value = "获取专业解读详细信息", notes = "获取专业解读详细信息", response = ProfessionalInterpretationDetail.class, tags={ "sy-professionalInterpretation", })
    @RequestMapping(value = "/professionalInterpretation/{id}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<ProfessionalInterpretationDetail> getProfessionalInterpretation(@ApiParam(value = "专业解读id", required = true) @PathVariable("id") Integer id);

    @ApiOperation(value = "获取专家的专业解读列表", notes = "获取专家的专业解读列表", response = ProfessionalInterpretationSummary.class, responseContainer = "List", tags={ "sy-expert", })
    @RequestMapping(value = "/expert/{expertId}/professionalInterpretation",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<ProfessionalInterpretationSummary>> getExpertProfessionalInterpretation(@ApiParam(value = "专家id", required = true) @PathVariable("expertId") Integer expertId);

    @ApiOperation(value = "获取专业解读列表", notes = "获取专业解读列表", response = ProfessionalInterpretationSummary.class, responseContainer = "List", tags={ "sy-professionalInterpretation", })
    @RequestMapping(value = "/professionalInterpretations",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<PageInfo<ProfessionalInterpretationSummary>> getProfessionalInterpretations(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", defaultValue="10") Integer pageSize,
                                                                                               @ApiParam(value = "页码。默认第一页", defaultValue = "1") @RequestParam(value = "pageNumber", defaultValue="1") Integer pageNumber,
                                                                                               @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q);

    @ApiOperation(value = "获取专业解读首页top3", notes = "获取专业解读首页top3", response = ProfessionalInterpretationTop.class, responseContainer = "List", tags={ "sy-professionalInterpretation", })
    @RequestMapping(value = "/professionalInterpretation/top",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<ProfessionalInterpretationTop>> getProfessionalInterpretationTop();

    @ApiOperation(value = "新增专业解读", notes = "新增专业解读", response = HttpMessage.class, tags={ "sy-professionalInterpretation", })
    @RequestMapping(value = "/professionalInterpretation",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> addProfessionalInterpretation(@ApiParam(value = "专业解读表单", required = true) @RequestBody ProfessionalInterpretationRequest professionalInterpretationRequest);

    @ApiOperation(value = "编辑专业解读", notes = "编辑专业解读", response = HttpMessage.class, tags={ "sy-professionalInterpretation", })
    @RequestMapping(value = "/professionalInterpretation/{id}",
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> editProfessionalInterpretation(@ApiParam(value = "专业解读id", required=true ) @PathVariable("id") Integer id,
                                                      @ApiParam(value = "专业解读表单", required = true) @RequestBody ProfessionalInterpretationRequest professionalInterpretationRequest);

    @ApiOperation(value = "（批量）删除专业解读", notes = "（批量）删除专业解读", response = HttpMessage.class, tags={ "sy-professionalInterpretation", })
    @RequestMapping(value = "/professionalInterpretation",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteProfessionalInterpretation(@ApiParam(value = "专业解读ids", required = true) @RequestParam Integer[] ids);

    @ApiOperation(value = "（取消）置顶专业解读", notes = "（取消）置顶专业解读", response = HttpMessage.class, tags={ "sy-professionalInterpretation", })
    @RequestMapping(value = "/professionalInterpretation/{id}/top",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> topProfessionalInterpretation(@ApiParam(value = "专业解读id", required=true ) @PathVariable("id") Integer id,
                                                              @ApiParam(value = "图片url") @RequestParam(value = "topImgUrl") String topImgUrl);

    @ApiOperation(value = "（取消）收藏专业解读", notes = "（取消）收藏专业解读", response = HttpMessage.class, tags={ "sy-professionalInterpretation", })
    @RequestMapping(value = "/professionalInterpretation/{id}/collect",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> collectProfessionalInterpretation(@ApiParam(value = "专业解读id", required=true ) @PathVariable("id") Integer id);

    @ApiOperation(value = "获取我收藏的专业解读", notes = "获取我收藏的专业解读", response = ProfessionalInterpretationSummary.class, responseContainer = "List", tags={ "sy-professionalInterpretation", })
    @RequestMapping(value = "/professionalInterpretation/my/collect",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<PageInfo<ProfessionalInterpretationSummary>> getMyCollectProfessionalInterpretation(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", defaultValue="10") Integer pageSize,
                                                                                     @ApiParam(value = "页码。默认第一页", defaultValue = "1") @RequestParam(value = "pageNumber", defaultValue="1") Integer pageNumber);
}
