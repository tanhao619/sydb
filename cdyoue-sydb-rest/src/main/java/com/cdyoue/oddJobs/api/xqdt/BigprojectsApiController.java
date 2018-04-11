package com.cdyoue.oddJobs.api.xqdt;


import java.util.List;

import com.cdyoue.oddJobs.dto.common.message.BigProjectMessage;
import com.cdyoue.oddJobs.entity.lgsq.PortalBigProjectEntity;
import com.cdyoue.oddJobs.service.BigPorjectService;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.common.SortUtils;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.xqdt.BigProjectDetail;
import com.cdyoue.oddJobs.dto.xqdt.BigProjectSummary;

import io.swagger.annotations.ApiParam;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-18T06:53:23.085Z")

@Controller
public class BigprojectsApiController implements BigprojectsApi {

    @Autowired
    BigPorjectService bigPorjectService;

    public ResponseEntity<HttpMessage> createBigProject(@ApiParam(value = "大项目实体信息" ,required=true ) @RequestBody BigProjectDetail bigproject) {
        Integer role =  SecurityUtils.getCurrentUserLogin().getRole();
        if (2 != role) {
            return new ResponseEntity<HttpMessage>(HttpStatus.FORBIDDEN);
        }

        BigProjectDetail dto = new BigProjectDetail();
        BeanUtils.copyProperties(bigproject,dto);
        String id = bigPorjectService.createBigProject(dto);
        return id == null ? new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST) : new ResponseEntity<HttpMessage>(HttpStatus.CREATED);
    }

    public ResponseEntity<HttpMessage> deleteBigProject(@ApiParam(value = "",required=true ) @PathVariable("id") Integer paramID) {
        Integer id = Integer.valueOf(paramID);
        if (bigPorjectService.getBigProjectCheck(id) == false) {
            return new ResponseEntity<HttpMessage>(BigProjectMessage.NO_BigProject,HttpStatus.NOT_FOUND);
        }

        Integer role =  SecurityUtils.getCurrentUserLogin().getRole();

        if (2!=role) {
            return new ResponseEntity<HttpMessage>(HttpStatus.FORBIDDEN);
        }

        bigPorjectService.deleteBigProject(id);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    public ResponseEntity<BigProjectDetail> getBigProjectById(@ApiParam(value = "大项目id",required=true ) @PathVariable("id") Integer id) {
        if(bigPorjectService.getBigProjectCheck(id)){
            BigProjectDetail dto = new BigProjectDetail();
            dto = bigPorjectService.getBigProjectById(id);
            return new ResponseEntity<BigProjectDetail>(dto,HttpStatus.OK);
        }else {
            return new ResponseEntity(BigProjectMessage.NO_BigProject,HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<BigProjectDetail> getTopBigProject() {
        List<PortalBigProjectEntity> portalBigProjectEntityList=bigPorjectService.findAll();
        if(portalBigProjectEntityList==null||portalBigProjectEntityList.size()<1){
            return new ResponseEntity(BigProjectMessage.NO_BigProject,HttpStatus.NOT_FOUND);
        }
        BigProjectDetail dto = bigPorjectService.getTopBigProject();
        if(dto!=null){
            return new ResponseEntity<BigProjectDetail>(dto,HttpStatus.OK);
        }else {
            return new ResponseEntity(BigProjectMessage.NO_BigProject,HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<PageInfo> getBigProjects( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
         @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q) {
        //Hardcode sort
        Sort order = SortUtils.assembleSort("-createTime");
        PageRequest pr = new PageRequest(pageNumber,pageSize,order);
        PageInfo<BigProjectDetail> pageInfo = bigPorjectService.findList(pr,q);
        return new ResponseEntity<com.cdyoue.spring.page.PageInfo>(pageInfo,HttpStatus.OK);
    }

    public ResponseEntity<HttpMessage> stickBigProject(@ApiParam(value = "",required=true ) @PathVariable("id") Integer paramID,
                                                       @RequestParam String topImg) {
        Integer id = Integer.valueOf(paramID);
        if (bigPorjectService.getBigProjectCheck(id) == false) {
            return new ResponseEntity<HttpMessage>(BigProjectMessage.NO_BigProject,HttpStatus.NOT_FOUND);
        }

        Integer role =  SecurityUtils.getCurrentUserLogin().getRole();
        if ( 2!=role) {
            return new ResponseEntity<HttpMessage>(HttpStatus.FORBIDDEN);
        }

        bigPorjectService.cancelTopBigProjects();
        bigPorjectService.topBigProject(id,topImg);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    public ResponseEntity<HttpMessage> unStickBigProject(@ApiParam(value = "",required=true ) @PathVariable("id") Integer paramID) {
        Integer id = Integer.valueOf(paramID);
        if (bigPorjectService.getBigProjectCheck(id) == false) {
            return new ResponseEntity<HttpMessage>(BigProjectMessage.NO_BigProject,HttpStatus.NOT_FOUND);
        }

        Integer role =  SecurityUtils.getCurrentUserLogin().getRole();
        if ( 2!=role) {
            return new ResponseEntity<HttpMessage>(HttpStatus.FORBIDDEN);
        }

        bigPorjectService.cancelTopBigProject(id);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    public ResponseEntity<HttpMessage> updateBigProject(@ApiParam(value = "",required=true ) @PathVariable("id") Integer paramID,
        @ApiParam(value = "大项目对象" ,required=true ) @RequestBody BigProjectDetail bigproject) {
        Integer id = Integer.valueOf(paramID);
        if (bigPorjectService.getBigProjectCheck(id) == false) {
            return new ResponseEntity<HttpMessage>(BigProjectMessage.NO_BigProject,HttpStatus.NOT_FOUND);
        }

        Integer role =  SecurityUtils.getCurrentUserLogin().getRole();

        if (2!=role) {
            return new ResponseEntity<HttpMessage>(HttpStatus.FORBIDDEN);
        }

        bigPorjectService.updateBigProject(id,bigproject);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

}
