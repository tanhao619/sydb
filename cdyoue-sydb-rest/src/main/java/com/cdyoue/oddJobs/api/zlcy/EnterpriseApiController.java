package com.cdyoue.oddJobs.api.zlcy;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.common.message.EnterpriseMessage;
import com.cdyoue.oddJobs.dto.zlcy.*;
import com.cdyoue.oddJobs.service.sydb.ApplicationCheckInService;
import com.cdyoue.oddJobs.service.sydb.ApplicationDirectionService;
import com.cdyoue.oddJobs.service.sydb.EnterpriseService;
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

import java.util.List;

/**
 * Created by sky on 2017/9/23.
 */
@Controller
public class EnterpriseApiController implements EnterpriseApi{

    @Autowired
    EnterpriseService enterpriseService;

    @Autowired
    private ApplicationCheckInService applicationCheckInService;

    @Autowired
    private ApplicationDirectionService applicationDirectionService;

    @Override
    public ResponseEntity<EnterpriseDetail> getEnterpriseById(@ApiParam(value = "培训机构id", required = true) @PathVariable("id") Integer id) {
       if(enterpriseService.getEnterpriseCheck(id)){
           EnterpriseDetail enterpriseDetail=enterpriseService.getEnterpriseById(id);
           enterpriseService.updateEnterpriseViewCount(id);
           return new ResponseEntity<EnterpriseDetail>(enterpriseDetail, HttpStatus.OK);
       }else {
           return new ResponseEntity(EnterpriseMessage.NO_Enterprise, HttpStatus.NOT_FOUND);
       }

    }

    @Override
    public ResponseEntity<PageInfo> getEnterprises(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                   @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                   @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q) {
        Sort order = SortUtils.assembleSort("-top|-updateTime");
        PageRequest pageRequest=new PageRequest(pageNumber,pageSize,order);
        PageInfo<EnterpriseSummary> pageInfo=enterpriseService.findList(pageRequest,q);
        return new ResponseEntity<com.cdyoue.spring.page.PageInfo>(pageInfo, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<EnterpriseSummary>> getTopEnterprises() {
        List<EnterpriseSummary> topList=enterpriseService.findTopList();
        return new ResponseEntity<List<EnterpriseSummary>>(topList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> topEnterprise(@ApiParam(value = "企业id", required = true) @PathVariable("id") Integer id,
                                                     @ApiParam(value = "置顶图片") @RequestParam(value = "topImage",required=false ) String topImage) {
        if(enterpriseService.getEnterpriseCheck(id)) {
            enterpriseService.topEnterprise(id,topImage);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity(EnterpriseMessage.NO_Enterprise,HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<HttpMessage> ApplicationCheckIn(@ApiParam(value = "申请企业信息", required = true) @RequestBody ApplicationEnterpriseSummary applicationEnterpriseSummary) {
        Integer id =enterpriseService.saveApplicationMessage(applicationEnterpriseSummary);
        return id == null ? new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST) : new ResponseEntity<HttpMessage>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PageInfo<ApplicationCheckInMini>> getApplicationCheckIns(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                                                   @ApiParam(value = "页码。默认第一页", defaultValue = "1") @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
                                                                                   @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q) {
        Sort order = SortUtils.assembleSort("status|-createTime");
        PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, order);
        PageInfo<ApplicationCheckInMini> pageInfo = applicationCheckInService.findApplicationCheckIns(q, pageRequest);
        return new ResponseEntity<PageInfo<ApplicationCheckInMini>>(pageInfo, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApplicationCheckInDetail> getApplicationCheckIn(@ApiParam(value = "申请入住基地的企业信息id", required = true) @PathVariable("id") Integer id) {
        ApplicationCheckInDetail expertAchievementDetail = applicationCheckInService.findApplicationCheckIn(id);
        return new ResponseEntity<ApplicationCheckInDetail>(expertAchievementDetail, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> deleteApplicationCheckIn(@ApiParam(value = "申请入住基地的企业信息ids", required = true) @RequestParam Integer[] ids) {
        applicationCheckInService.deleteApplicationCheckIn(ids);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> ApplicationDirection(@ApiParam(value = "申请企业信息", required = true) @RequestBody ApplicationDirectionSummary applicationDirectionSummary) {
        Integer id =enterpriseService.saveDirectionMessage(applicationDirectionSummary);
        return id == null ? new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST) : new ResponseEntity<HttpMessage>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PageInfo<ApplicationDirectionMini>> getApplicationDirections(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                                                       @ApiParam(value = "页码。默认第一页", defaultValue = "1") @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
                                                                                       @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q) {
        Sort order = SortUtils.assembleSort("status|-createTime");
        PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, order);
        PageInfo<ApplicationDirectionMini> pageInfo = applicationDirectionService.findApplicationDirections(q, pageRequest);
        return new ResponseEntity<PageInfo<ApplicationDirectionMini>>(pageInfo, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApplicationDirectionDetail> getApplicationDirection(@ApiParam(value = "申请创业指导的企业信息id", required = true) @PathVariable("id") Integer id) {
        ApplicationDirectionDetail applicationDirectionDetail = applicationDirectionService.findApplicationDirection(id);
        return new ResponseEntity<ApplicationDirectionDetail>(applicationDirectionDetail, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> deleteApplicationDirection(@ApiParam(value = "申请创业指导的企业信息ids", required = true) @RequestParam Integer[] ids) {
        applicationDirectionService.deleteApplicationDirection(ids);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> deleteEnterprise(@ApiParam(value = "项目申报ids",required=true ) @RequestParam Integer[] ids) {
        enterpriseService.deleteByIds(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> createEnterprise(@ApiParam(value = "发布企业实体" ,required=true ) @RequestBody EnterpriseCreate enterpriseCreate) {
        Integer enterpriseId=enterpriseService.createEnterprise(enterpriseCreate);
        return enterpriseId!=null?new ResponseEntity<>(HttpStatus.CREATED):new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<HttpMessage> updateEnterprise(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
                                                        @ApiParam(value = "企业对象信息" ,required=true ) @RequestBody EnterpriseCreate enterpriseCreate) {
        if(enterpriseService.getEnterpriseCheck(id)){
            enterpriseService.updateEnterprise(id,enterpriseCreate);
            return new ResponseEntity<HttpMessage>(HttpStatus.CREATED);
        }else{
            return new ResponseEntity<HttpMessage>(EnterpriseMessage.NO_Enterprise, HttpStatus.OK);
        }

    }
}
