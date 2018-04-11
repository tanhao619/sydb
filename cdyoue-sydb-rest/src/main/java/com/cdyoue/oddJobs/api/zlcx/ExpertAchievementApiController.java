package com.cdyoue.oddJobs.api.zlcx;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.zlcx.ExpertAchievementDetail;
import com.cdyoue.oddJobs.dto.zlcx.ExpertAchievementRequest;
import com.cdyoue.oddJobs.dto.zlcx.ExpertAchievementSummary;
import com.cdyoue.oddJobs.dto.zlcx.ExpertAchievementTop;
import com.cdyoue.oddJobs.service.sydb.ExpertAchievementService;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.common.SortUtils;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * Created by dengshaojun on 2017/09/15.
 */
@Controller
public class ExpertAchievementApiController implements ExpertAchievementApi {

    @Autowired
    private ExpertAchievementService expertAchievementService;

    @Override
    public ResponseEntity<ExpertAchievementDetail> getExpertAchievement(@ApiParam(value = "专家成果id", required = true) @PathVariable("id") Integer id) {
        ExpertAchievementDetail expertAchievementDetail = expertAchievementService.findExpertAchievement(id);
        return new ResponseEntity<ExpertAchievementDetail>(expertAchievementDetail, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ExpertAchievementSummary>> getExpertAchievementExpert(@ApiParam(value = "专家id", required = true) @PathVariable("expertId")Integer expertId) {
        List<ExpertAchievementSummary> list = expertAchievementService.findExpertAchievementByEid(expertId);
        return new ResponseEntity<List<ExpertAchievementSummary>>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageInfo<ExpertAchievementSummary>> getExpertAchievements(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", defaultValue="10") Integer pageSize,
                                                                                    @ApiParam(value = "页码。默认第一页", defaultValue = "1") @RequestParam(value = "pageNumber", defaultValue="1") Integer pageNumber,
                                                                                    @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q) {
        Sort order = SortUtils.assembleSort("-top|-updateTime");
        Pageable pageRequest = new PageRequest(pageNumber-1, pageSize, order);
        PageInfo<ExpertAchievementSummary> pageInfo = expertAchievementService.findExpertAchievementsPage(q, pageRequest);
        return new ResponseEntity<PageInfo<ExpertAchievementSummary>>(pageInfo, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ExpertAchievementTop>> getExpertAchievementTop() {
        List<ExpertAchievementTop> list = expertAchievementService.findExpertAchievementTop2();
        return new ResponseEntity<List<ExpertAchievementTop>>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> addExpertAchievement(@ApiParam(value = "专家成果表单", required = true) @RequestBody ExpertAchievementRequest expertAchievementRequest) {
        expertAchievementService.saveExpertAchievement(expertAchievementRequest);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> editExpertAchievement(@ApiParam(value = "专家成果id", required=true ) @PathVariable("id") Integer id,
                                                             @ApiParam(value = "专家成果表单", required = true) @RequestBody ExpertAchievementRequest expertAchievementRequest) {
        expertAchievementService.updateExpertAchievement(id, expertAchievementRequest);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> deleteExpertAchievement(@ApiParam(value = "专家成果ids", required = true) @RequestParam Integer[] ids) {
        expertAchievementService.deleteExpertAchievement(ids);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> topExpertAchievement(@ApiParam(value = "专家成果id", required=true ) @PathVariable("id") Integer id,
                                                            @ApiParam(value = "图片url") @RequestParam(value = "topImgUrl") String topImgUrl) {
        expertAchievementService.updateTopExpertAchievement(id, topImgUrl);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> collectExpertAchievement(@ApiParam(value = "专家成果id", required=true ) @PathVariable("id") Integer id) {
        Integer type=0;
        Integer userId = SecurityUtils.getCurrentUserLogin().getId();
        expertAchievementService.collectExpertAchievement(userId, id);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageInfo<ExpertAchievementSummary>> getMyCollectExpertAchievement(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", defaultValue="10") Integer pageSize,
                                                                                            @ApiParam(value = "页码。默认第一页", defaultValue = "1") @RequestParam(value = "pageNumber", defaultValue="1") Integer pageNumber) {
        Integer type=0;
        Integer userId = SecurityUtils.getCurrentUserLogin().getId();
        Sort order = SortUtils.assembleSort("-updateTime");
        Pageable pageRequest = new PageRequest(pageNumber-1, pageSize, order);
        PageInfo<ExpertAchievementSummary> pageInfo =  expertAchievementService.findExpertAchievementByUC(userId, pageRequest);
        return new ResponseEntity<PageInfo<ExpertAchievementSummary>>(pageInfo, HttpStatus.OK);
    }
}
