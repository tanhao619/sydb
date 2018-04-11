package com.cdyoue.oddJobs.api.zlcx;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.zlcx.*;
import com.cdyoue.oddJobs.service.sydb.ExpertService;
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
public class ExpertApiController implements ExpertApi {

    @Autowired
    private ExpertService expertService;

    @Override
    public ResponseEntity<ExpertBaseinfo> getExpert(@ApiParam(value = "专家id", required = true) @PathVariable("id") Integer id) {
        ExpertBaseinfo expertBaseinfo = expertService.findExpert(id);
        return new ResponseEntity<ExpertBaseinfo>(expertBaseinfo, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageInfo<ExpertSummary>> getExperts(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", defaultValue="10") Integer pageSize,
                                                              @ApiParam(value = "页码。默认第一页", defaultValue = "1") @RequestParam(value = "pageNumber", defaultValue="1") Integer pageNumber,
                                                              @ApiParam(value = "行业分类") @RequestParam(value = "industryType", required = false) Integer industryType,
                                                              @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q) {
        Sort order = SortUtils.assembleSort("-top|-updateTime");
        Pageable pageRequest = new PageRequest(pageNumber-1, pageSize, order);
        PageInfo<ExpertSummary> pageInfo = expertService.findExpertsPage(industryType, q, pageRequest);
        return new ResponseEntity<PageInfo<ExpertSummary>>(pageInfo, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ExpertTop>> getExpertTop() {
        List<ExpertTop> list = expertService.findExpertTop3();
        return new ResponseEntity<List<ExpertTop>>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> contactExpert(@ApiParam(value = "专家id", required=true ) @PathVariable("id") Integer id,
                                                     @ApiParam(value = "联系专家表单", required = true) @RequestBody ExpertContactRequest expertContactRequest) {
        expertService.saveExpertContact(id, expertContactRequest);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> addExpert(@ApiParam(value = "专家表单", required = true) @RequestBody ExpertRequest expertRequest) {
        expertService.saveExpert(expertRequest);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> editExpert(@ApiParam(value = "专家id", required=true ) @PathVariable("id") Integer id,
                                                  @ApiParam(value = "专家表单", required = true) @RequestBody ExpertRequest expertRequest) {
        expertService.updateExpert(id, expertRequest);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> deleteExpert(@ApiParam(value = "专家ids", required = true) @RequestParam Integer[] ids) {
        expertService.deleteExpert(ids);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> topExpert(@ApiParam(value = "专家id", required=true ) @PathVariable("id") Integer id,
                                                 @ApiParam(value = "图片url") @RequestParam(value = "topImgUrl") String topImgUrl) {
        expertService.updateTopExpert(id ,topImgUrl);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ExpertMini>> getExpertSelect(@ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q) {
        List<ExpertMini> list = expertService.findAllExpert(q);
        return new ResponseEntity<List<ExpertMini>>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageInfo<ExpertContactSummary>> getExpertContacts(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", defaultValue="10") Integer pageSize,
                                                                            @ApiParam(value = "页码。默认第一页", defaultValue = "1") @RequestParam(value = "pageNumber", defaultValue="1") Integer pageNumber,
                                                                            @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q) {
        Sort order = SortUtils.assembleSort("-createTime");
        Pageable pageRequest = new PageRequest(pageNumber-1, pageSize, order);
        PageInfo<ExpertContactSummary> pageInfo = expertService.findExpertContactsPage(q, pageRequest);
        return new ResponseEntity<PageInfo<ExpertContactSummary>>(pageInfo, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> deleteExpertContact(@ApiParam(value = "专家联系ids", required = true) @RequestParam Integer[] ids) {
        expertService.deleteExpertContact(ids);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

}
