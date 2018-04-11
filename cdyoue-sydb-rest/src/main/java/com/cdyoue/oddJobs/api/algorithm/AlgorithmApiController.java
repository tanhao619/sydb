package com.cdyoue.oddJobs.api.algorithm;

import com.cdyoue.oddJobs.annotion.authentication.Role;
import com.cdyoue.oddJobs.dto.algorithm.*;
import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.common.message.CommonMessage;
import com.cdyoue.oddJobs.service.AlgorithmOperatorService;
import com.cdyoue.oddJobs.service.AlgorithmTypeService;
import com.cdyoue.spring.common.SortUtils;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by liaoyoule on 2017/6/14.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-30T14:30:56.295Z")
@Controller
public class AlgorithmApiController implements AlgorithmApi {


    @Autowired
    private AlgorithmOperatorService algorithmOperatorService;
    @Autowired
    private AlgorithmTypeService algorithmTypeService;

    @Override
    @Role(2)
    public ResponseEntity<PageInfo<AlgorithmBase>> getAlgorithms(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                                 @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                                 @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                                                 @ApiParam(value = "排序字段和方式 例如：/policies?sort=createTime|-createTime", allowableValues = "createTime, -createTime") @RequestParam(value = "sort", required = false, defaultValue = "-createTime") String sort) {

        PageRequest pr = new PageRequest(pageNumber, pageSize, SortUtils.assembleSort(sort));
        return new ResponseEntity<PageInfo<AlgorithmBase>>(algorithmOperatorService.getAlgorithms(q,pr), HttpStatus.OK);
    }

    @Override
    @Role(2)
    public ResponseEntity<HttpMessage> deleteAlgorithmOperator(@ApiParam(value = "", required = true) @PathVariable("id") Integer id) {
        algorithmOperatorService.delete(id);
        return new ResponseEntity<HttpMessage>(CommonMessage.DELETESUCCESS, HttpStatus.OK);
    }

    @Override
    @Role(2)
    public ResponseEntity<AlgorithmSumary> getAlgorithm(@ApiParam(value = "", required = true) @PathVariable("id") Integer id) {
        AlgorithmSumary as = algorithmOperatorService.getAlgorithm(id);
        return new ResponseEntity<AlgorithmSumary>(as, HttpStatus.OK);
    }

    @Override
    @Role(2)
    public ResponseEntity<HttpMessage> createAlgorithm(@ApiParam(value = "", required = true) @RequestBody() AlgorithmRequest algorithmRequest) {
        algorithmOperatorService.createAlgorithm(algorithmRequest);
        return new ResponseEntity<HttpMessage>(CommonMessage.CREATESUCCESS, HttpStatus.OK);
    }

    @Override
    @Role(2)
    public ResponseEntity<HttpMessage> updateAlgorithm(  @ApiParam(value = "", required = true) @PathVariable("id") Integer id,
                                                         @ApiParam(value = "", required = true) @RequestBody() AlgorithmRequest algorithmRequest) {
        algorithmOperatorService.updateAlgorithm(id,algorithmRequest);
        return new ResponseEntity<HttpMessage>(CommonMessage.UPDATESUCCESS, HttpStatus.OK);
    }

    @Override
    @Role(2)
    public ResponseEntity<PageInfo<AlgorithmTypeSumary>> getAlgorithmTypes(
            @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
            @ApiParam(value = "排序字段和方式 例如：/policies?sort=createTime|-createTime", allowableValues = "createTime, -createTime") @RequestParam(value = "sort", required = false, defaultValue = "-createTime") String sort) {

        PageRequest pr = new PageRequest(pageNumber, pageSize, SortUtils.assembleSort(sort));


        return new ResponseEntity<PageInfo<AlgorithmTypeSumary>>( algorithmTypeService.getAlgorithmTypes(q, pr),HttpStatus.OK);
    }

    @Override
    @Role(2)
    public ResponseEntity<HttpMessage> deleteAlgorithmTypes(@ApiParam(value = "", required = true) @PathVariable("id") Integer id) {
        algorithmTypeService.deleteAlgorithmTypes(id);
        return new ResponseEntity<HttpMessage>(CommonMessage.DELETESUCCESS,HttpStatus.OK);
    }

    @Override
    @Role(2)
    public ResponseEntity<AlgorithmTypeSumary> getAlgorithmType(@ApiParam(value = "", required = true) @PathVariable("id") Integer id) {
        return new ResponseEntity<AlgorithmTypeSumary>(algorithmTypeService.getAlgorithmType(id),HttpStatus.OK);
    }

    @Override
    @Role(2)
    public ResponseEntity<HttpMessage> createAlgorithmType(@ApiParam(value = "", required = true) @RequestBody() AlgorithmTypeRequest algorithmTypeRequest) {
        algorithmTypeService.createAlgorithmType(algorithmTypeRequest);
        return new ResponseEntity<HttpMessage>(CommonMessage.CREATESUCCESS,HttpStatus.CREATED);
    }

    @Override
    @Role(2)
    public ResponseEntity<HttpMessage> updateAlgorithmType(@ApiParam(value = "", required = true) @PathVariable("id") Integer id,
                                                           @ApiParam(value = "", required = true) @RequestBody() AlgorithmTypeRequest algorithmTypeRequest) {


        algorithmTypeService.updateAlgorithmType(id,algorithmTypeRequest);
        return new ResponseEntity<HttpMessage>(CommonMessage.UPDATESUCCESS,HttpStatus.OK);
    }


}
