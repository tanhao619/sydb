package com.cdyoue.oddJobs.api.zlcx;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.zlcx.ProfessionalInterpretationDetail;
import com.cdyoue.oddJobs.dto.zlcx.ProfessionalInterpretationRequest;
import com.cdyoue.oddJobs.dto.zlcx.ProfessionalInterpretationSummary;
import com.cdyoue.oddJobs.dto.zlcx.ProfessionalInterpretationTop;
import com.cdyoue.oddJobs.service.sydb.ProfessionalInterpretationService;
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
 * Created by dengshaojun on 2017/09/18.
 */
@Controller
public class ProfessionalInterpretationApiController implements ProfessionalInterpretationApi {

    @Autowired
    private ProfessionalInterpretationService professionalInterpretationService;

    @Override
    public ResponseEntity<ProfessionalInterpretationDetail> getProfessionalInterpretation(@ApiParam(value = "专业解读id", required = true) @PathVariable("id") Integer id) {
        ProfessionalInterpretationDetail detail = professionalInterpretationService.findProfessionalInterpretation(id);
        return new ResponseEntity<ProfessionalInterpretationDetail>(detail, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProfessionalInterpretationSummary>> getExpertProfessionalInterpretation(@ApiParam(value = "专家id", required = true) @PathVariable("expertId") Integer expertId) {
        List<ProfessionalInterpretationSummary> list = professionalInterpretationService.findExpertProfessionalInterpretation(expertId);
        return new ResponseEntity<List<ProfessionalInterpretationSummary>>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageInfo<ProfessionalInterpretationSummary>> getProfessionalInterpretations(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", defaultValue="10") Integer pageSize,
                                                                                                      @ApiParam(value = "页码。默认第一页", defaultValue = "1") @RequestParam(value = "pageNumber", defaultValue="1") Integer pageNumber,
                                                                                                      @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q) {
        Sort order = SortUtils.assembleSort("-top|-updateTime");
        Pageable pageRequest = new PageRequest(pageNumber-1, pageSize, order);
        PageInfo<ProfessionalInterpretationSummary> pageInfo = professionalInterpretationService.findExpertProfessionalInterpretationsPage(q, pageRequest);
        return new ResponseEntity<PageInfo<ProfessionalInterpretationSummary>>(pageInfo, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProfessionalInterpretationTop>> getProfessionalInterpretationTop() {
        List<ProfessionalInterpretationTop> list = professionalInterpretationService.findProfessionalInterpretationTop3();
        return new ResponseEntity<List<ProfessionalInterpretationTop>>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> addProfessionalInterpretation(@ApiParam(value = "专业解读表单", required = true) @RequestBody ProfessionalInterpretationRequest professionalInterpretationRequest) {
        professionalInterpretationService.saveProfessionalInterpretation(professionalInterpretationRequest);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> editProfessionalInterpretation(@ApiParam(value = "专业解读id", required=true ) @PathVariable("id") Integer id,
                                                                      @ApiParam(value = "专业解读表单", required = true) @RequestBody ProfessionalInterpretationRequest professionalInterpretationRequest) {
        professionalInterpretationService.updateProfessionalInterpretation(id, professionalInterpretationRequest);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> deleteProfessionalInterpretation(@ApiParam(value = "专业解读ids", required = true) @RequestParam Integer[] ids) {
        professionalInterpretationService.deleteProfessionalInterpretation(ids);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> topProfessionalInterpretation(@ApiParam(value = "专业解读id", required=true ) @PathVariable("id") Integer id,
                                                                     @ApiParam(value = "图片url") @RequestParam(value = "topImgUrl") String topImgUrl) {
        professionalInterpretationService.topProfessionalInterpretation(id, topImgUrl);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> collectProfessionalInterpretation(@ApiParam(value = "专业解读id", required=true ) @PathVariable("id") Integer id) {
        Integer type=0;
        Integer userId = SecurityUtils.getCurrentUserLogin().getId();
        professionalInterpretationService.collectProfessionalInterpretation(userId, id);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageInfo<ProfessionalInterpretationSummary>> getMyCollectProfessionalInterpretation(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", defaultValue="10") Integer pageSize,
                                                                                                              @ApiParam(value = "页码。默认第一页", defaultValue = "1") @RequestParam(value = "pageNumber", defaultValue="1") Integer pageNumber) {
        Integer type=0;
        Integer userId = SecurityUtils.getCurrentUserLogin().getId();
        Sort order = SortUtils.assembleSort("-updateTime");
        Pageable pageRequest = new PageRequest(pageNumber-1, pageSize, order);
        PageInfo<ProfessionalInterpretationSummary> pageInfo =  professionalInterpretationService.findProfessionalInterpretationByUC(userId, pageRequest);
        return new ResponseEntity<PageInfo<ProfessionalInterpretationSummary>>(pageInfo, HttpStatus.OK);
    }
}
