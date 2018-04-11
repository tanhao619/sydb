package com.cdyoue.oddJobs.api.ggfw;


import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.ggfw.Govproject;
import com.cdyoue.oddJobs.dto.ggfw.GovprojectSummary;
import com.cdyoue.oddJobs.dto.ggfw.Govservice;
import com.cdyoue.oddJobs.service.GovprojectService;
import com.cdyoue.oddJobs.service.GovserviceService;
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

import javax.validation.constraints.NotNull;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-15T01:26:16.185Z")

@Controller
public class GovApiController implements GovApi {

    @Autowired
    private GovserviceService govserviceService;

    @Autowired
    private GovprojectService govprojectService;

    public ResponseEntity<HttpMessage> createGovproject(@ApiParam(value = "政府项目实体信息" ,required=true ) @RequestBody Govproject govproject) {
        // do some magic!
        Integer id= govprojectService.createGovproject(govproject);
        return id == null ? new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST) : new ResponseEntity<HttpMessage>(HttpStatus.CREATED);
    }

    public ResponseEntity<HttpMessage> createGovservice(@ApiParam(value = "政府服务实体信息" ,required=true ) @RequestBody Govservice news) {
        // do some magic!
        Integer id= govserviceService.createGovservice(news);
        return id == null ? new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST) : new ResponseEntity<HttpMessage>(HttpStatus.CREATED);
    }

    public ResponseEntity<HttpMessage> deleteGovService(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id) {
        govserviceService.deleteGovService(id);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    public ResponseEntity<PageInfo> getGovproject( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
                                                   @NotNull @ApiParam(value = "发文时间，最近一周:1，最近一月:2", required = false, allowableValues = "1, 2") @RequestParam(value = "timeFilter", required = false) Integer timeFilter,
         @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q) {
        // do some magic!
        Pageable requestPage = new PageRequest(pageNumber, pageSize, Sort.Direction.DESC,"publishTime");
        PageInfo<GovprojectSummary> pageInfo = govprojectService.findGovprojectByKeyWord(requestPage, q, timeFilter);
        return new ResponseEntity<PageInfo>(pageInfo, HttpStatus.OK);
    }

    public ResponseEntity<Govproject> getGovprojectById(@ApiParam(value = "政府项目id",required=true ) @PathVariable("id") Integer id) {
        Govproject govproject = govprojectService.getGovprojectById(id);
        return new ResponseEntity<Govproject>(govproject, HttpStatus.OK);
    }

    public ResponseEntity<PageInfo> getGovservice( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
         @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q) {
        // do some magic!
        Pageable requestPage = new PageRequest(pageNumber, pageSize, Sort.Direction.DESC,"CreateTime");
        PageInfo<Govservice> pageInfo = govserviceService.findByKeyWord(requestPage, q);
        return new ResponseEntity<PageInfo>(pageInfo, HttpStatus.OK);
    }

    public ResponseEntity<Govservice> getGovserviceById(@ApiParam(value = "政府服务id",required=true ) @PathVariable("id") Integer id) {
        // do some magic!
        Govservice govservice = govserviceService.getGovserviceById(id);
        return new ResponseEntity<Govservice>(govservice, HttpStatus.OK);
    }

    public ResponseEntity<HttpMessage> deleteGovProject(@ApiParam(value = "", required = true) @PathVariable("id") Integer id) {
        govprojectService.deleteGovProject(id);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    public ResponseEntity<HttpMessage> updateGovproject(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
                                                        @ApiParam(value = "政府项目对象" ,required=true ) @RequestBody Govproject govproject) {
        govprojectService.updateGovproject(id, govproject);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    public ResponseEntity<HttpMessage> updateGovservice(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
        @ApiParam(value = "政府服务对象" ,required=true ) @RequestBody Govservice govservice) {
        //判断该政府服务是否存在
        if(govserviceService.getGovserviceById(id) == null) return new ResponseEntity<HttpMessage>(HttpStatus.NOT_FOUND);
        //更新政府服务
        govserviceService.updateGovservice(id, govservice);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }
}
