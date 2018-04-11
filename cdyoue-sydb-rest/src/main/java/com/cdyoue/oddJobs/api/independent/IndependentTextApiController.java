package com.cdyoue.oddJobs.api.independent;


import com.cdyoue.oddJobs.annotion.authentication.Role;
import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.common.message.CommonMessage;
import com.cdyoue.oddJobs.dto.common.message.IndependentTextMessage;
import com.cdyoue.oddJobs.dto.independent.IndependentMine;
import com.cdyoue.oddJobs.dto.independent.IndependentSumary;
import com.cdyoue.oddJobs.dto.independent.RequestIndependent;
import com.cdyoue.oddJobs.service.PortalIndependentTextService;
import com.cdyoue.spring.common.SortUtils;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-20T01:12:00.448Z")
@Controller
public class IndependentTextApiController implements IndependentTextApi {

    @Autowired
    private PortalIndependentTextService portalIndependentTextService;

    @Override
    @Role(value = 2)
    public ResponseEntity<HttpMessage> createIndependent(
            @ApiParam(value = "1: 独立页面发布2:数据分析", required = true, allowableValues = "1,2") @RequestParam("type") Integer type,
            @ApiParam(value = "需要发布的独立发布实体信息", required = true) @Valid @RequestBody RequestIndependent publish) {
        Integer result = portalIndependentTextService.save(type, publish);
        return result == null ? new ResponseEntity<HttpMessage>(IndependentTextMessage.INDEPENDENTTEXTPUBLISHCREATEFAIL, HttpStatus.BAD_REQUEST) : new ResponseEntity<HttpMessage>(IndependentTextMessage.INDEPENDENTTEXTPUBLISHCREATESUCCESS, HttpStatus.OK);
    }

    @Override
    @Role(value = 2)
    public ResponseEntity<HttpMessage> deleteIndependent(@ApiParam(value = "", required = true) @PathVariable("id") Integer id) {
        portalIndependentTextService.delete(id);
        return new ResponseEntity<HttpMessage>(IndependentTextMessage.INDEPENDENTTEXTPUBLISHDELETESUCCESS, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<IndependentSumary> getIndependentById(@ApiParam(value = "", required = true) @PathVariable("id") Integer id) {
        IndependentSumary ps = portalIndependentTextService.findById(id);
        return new ResponseEntity<IndependentSumary>(ps, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageInfo<IndependentMine>> getIndependents(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                                     @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                                     @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                                                     @ApiParam(value = "1: 独立页面发布2:数据分析", required = true, allowableValues = "1,2") @RequestParam("type") Integer type,
                                                                     @ApiParam(value = "排序字段和方式 例如：/policies?sort=-create_time|-update_time", allowableValues = "createTime, -createTime") @RequestParam(value = "sort", required = false, defaultValue = "-createTime") String sort) {


        Sort orders = SortUtils.assembleSort(sort);
        PageRequest pr = new PageRequest(pageNumber, pageSize, orders);
        PageInfo<IndependentMine> page = portalIndependentTextService.getIndependents(type, q, pr);
        return new ResponseEntity<PageInfo<IndependentMine>>(page, HttpStatus.OK);
    }

    @Override
    @Role(value = 2)
    public ResponseEntity<HttpMessage> updateIndependent(@ApiParam(value = "", required = true) @PathVariable("id") Integer id,
                                                         @ApiParam(value = "独立发布对象", required = true) @Valid @RequestBody RequestIndependent publish) {
        Integer result = portalIndependentTextService.updateIndependent(id, publish);
        return result == null ? new ResponseEntity<HttpMessage>(CommonMessage.UPDATEFAIL,HttpStatus.BAD_REQUEST)
                :
                new ResponseEntity<HttpMessage>(CommonMessage.UPDATESUCCESS,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> addViewNum(@ApiParam(value = "", required = true) @PathVariable("id") Integer id) {
        Integer result = portalIndependentTextService.addViewNum(id);
        return result == null ? new ResponseEntity<HttpMessage>(CommonMessage.CREATEFAIL,HttpStatus.BAD_REQUEST)
                :
                new ResponseEntity<HttpMessage>(CommonMessage.CREATESUCCESS,HttpStatus.OK);
    }



}
