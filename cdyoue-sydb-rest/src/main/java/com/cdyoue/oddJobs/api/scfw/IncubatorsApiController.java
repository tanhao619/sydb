package com.cdyoue.oddJobs.api.scfw;


import java.util.List;

import javax.validation.constraints.NotNull;

import com.cdyoue.oddJobs.service.IncubatorsService;
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
import com.cdyoue.oddJobs.dto.scfw.IncubatorDetail;
import com.cdyoue.oddJobs.dto.scfw.IncubatorSummary;

import io.swagger.annotations.ApiParam;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-16T14:01:12.120Z")

@Controller
public class IncubatorsApiController implements IncubatorsApi {
    @Autowired
    IncubatorsService incubatorsService;

    public ResponseEntity<HttpMessage> createIncubator(@ApiParam(value = "孵化器实体信息" ,required=true ) @RequestBody IncubatorDetail enterService) {
        if(SecurityUtils.getCurrentUserLogin().getRole() == 0){
            return new ResponseEntity<HttpMessage>(HttpStatus.FORBIDDEN);
        }
        String id = incubatorsService.createIncubators(enterService);
        return id == null ? new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST) : new ResponseEntity<HttpMessage>(HttpStatus.CREATED);
    }

    public ResponseEntity<HttpMessage> deleteIncubator(@ApiParam(value = "",required=true ) @PathVariable("id") Integer pid) {
        Integer id = Integer.valueOf(pid);
        if (incubatorsService.getIncubatorsCheck(id) == false) {
            return new ResponseEntity<HttpMessage>(HttpStatus.NOT_FOUND);
        }

        Integer uId = SecurityUtils.getCurrentUserLogin().getId();
        Integer role =  SecurityUtils.getCurrentUserLogin().getRole();

        if (uId != incubatorsService.getCreatorIdBy(id) && 2!=role) {
            return new ResponseEntity<HttpMessage>(HttpStatus.FORBIDDEN);
        }

        incubatorsService.deleteIncubators(id);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    public ResponseEntity<PageInfo> getIncubator(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
         @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q) {
        //Hardcode sort
        Sort order = SortUtils.assembleSort("-createTime");
        PageRequest pr = new PageRequest(pageNumber,pageSize,order);
        PageInfo<IncubatorDetail> pageInfo = incubatorsService.findList(pr,q);
        return new ResponseEntity<com.cdyoue.spring.page.PageInfo>(pageInfo,HttpStatus.OK);
    }

    public ResponseEntity<IncubatorDetail> getIncubatorById(@ApiParam(value = "孵化器id",required=true ) @PathVariable("id") Integer id) {
        if(incubatorsService.getIncubatorsCheck(id)){
            IncubatorDetail dtoObj = incubatorsService.getIncubatorsById(id);
            return new ResponseEntity<IncubatorDetail>(dtoObj,HttpStatus.OK);
        }else{
            return new ResponseEntity<IncubatorDetail>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpMessage> updateIncubator(@ApiParam(value = "",required=true ) @PathVariable("id") Integer pid,
        @ApiParam(value = "孵化器对象" ,required=true ) @RequestBody IncubatorDetail enterService) {

        Integer id = Integer.valueOf(pid);
        if (incubatorsService.getIncubatorsCheck(id) == false) {
            return new ResponseEntity<HttpMessage>(HttpStatus.NOT_FOUND);
        }

        Integer uId = SecurityUtils.getCurrentUserLogin().getId();
        Integer role =  SecurityUtils.getCurrentUserLogin().getRole();

        if (uId != incubatorsService.getCreatorIdBy(id) && 2!=role) {
            return new ResponseEntity<HttpMessage>(HttpStatus.FORBIDDEN);
        }

        incubatorsService.updateIncubators(id, enterService);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

}
