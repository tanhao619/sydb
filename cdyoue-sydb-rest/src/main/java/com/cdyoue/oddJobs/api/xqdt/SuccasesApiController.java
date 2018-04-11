package com.cdyoue.oddJobs.api.xqdt;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.common.message.CommonMessage;
import com.cdyoue.oddJobs.dto.xqdt.SuccaseDetail;
import com.cdyoue.oddJobs.dto.xqdt.SuccaseSummary;
import com.cdyoue.oddJobs.service.SuccaseService;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.common.SortUtils;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.ApiParam;
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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-18T06:53:23.085Z")

@Controller
public class SuccasesApiController implements SuccasesApi {
    @Autowired
    SuccaseService succaseService;

    @Override
    public ResponseEntity<HttpMessage> createSuccase(@ApiParam(value = "成功案例实体信息", required = true) @RequestBody SuccaseDetail succase) {
        Integer role =  SecurityUtils.getCurrentUserLogin().getRole();

        if (2 != role) {
            return new ResponseEntity<HttpMessage>(HttpStatus.FORBIDDEN);
        }

        SuccaseDetail dto = new SuccaseDetail();
        BeanUtils.copyProperties(succase,dto);
        String id = succaseService.createSuccase(dto);
        return id == null ? new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST) : new ResponseEntity<HttpMessage>(HttpStatus.CREATED);
    }

    public ResponseEntity<HttpMessage> deleteSuccase(@ApiParam(value = "",required=true ) @PathVariable("id") Integer paramID) {
        Integer id = Integer.valueOf(paramID);
        if (succaseService.getSuccaseCheck(id) == false) {
            return new ResponseEntity<HttpMessage>(HttpStatus.NOT_FOUND);
        }

        Integer role =  SecurityUtils.getCurrentUserLogin().getRole();

        if (2!=role) {
            return new ResponseEntity<HttpMessage>(HttpStatus.FORBIDDEN);
        }

        succaseService.deleteSuccase(id);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    public ResponseEntity<SuccaseSummary> getSuccaseById(@ApiParam(value = "成功案例id",required=true ) @PathVariable("id") Integer id) {
        if(succaseService.getSuccaseCheck(id)){
            SuccaseSummary dto = new SuccaseSummary();
            dto = succaseService.getSuccaseById(id);
            return new ResponseEntity<SuccaseSummary>(dto,HttpStatus.OK);
        }else {
            return new ResponseEntity<SuccaseSummary>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity getSuccases( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
         @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q) {
        //Hardcode sort
        Sort order = SortUtils.assembleSort("-publishTime");
        PageRequest pr = new PageRequest(pageNumber,pageSize,order);
        PageInfo<SuccaseSummary> pageInfo = succaseService.findList(pr,q);
        if (pageInfo.getList().size() < 1) return new ResponseEntity(CommonMessage.GETSUCCCASENOTFOUND, HttpStatus.NOT_FOUND);
        return new ResponseEntity<com.cdyoue.spring.page.PageInfo>(pageInfo,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> updateSuccase(@ApiParam(value = "", required = true) @PathVariable("id") Integer paramID, @ApiParam(value = "成功案例对象", required = true) @RequestBody SuccaseDetail succase) {
        Integer id = Integer.valueOf(paramID);
        if (succaseService.getSuccaseCheck(id) == false) {
            return new ResponseEntity<HttpMessage>(HttpStatus.NOT_FOUND);
        }

        Integer role =  SecurityUtils.getCurrentUserLogin().getRole();

        if (2!=role) {
            return new ResponseEntity<HttpMessage>(HttpStatus.FORBIDDEN);
        }

        succaseService.updateSuccase(id,succase);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }


}
