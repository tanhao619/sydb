package com.cdyoue.oddJobs.api.scfw;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.scfw.InvestorDetail;
import com.cdyoue.oddJobs.dto.scfw.InvestorSummary;
import com.cdyoue.oddJobs.service.InvestorsApiService;
import com.cdyoue.oddJobs.utils.SecurityUtils;
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

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-09T00:59:32.305Z")

@Controller
public class InvestorsApiController implements InvestorsApi {

    @Autowired
   private InvestorsApiService investorsApiService;

    public ResponseEntity<PageInfo> getInvestors(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                                 @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
                                                 @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q)
    {
        // do some magic!
        Pageable requestPage = new PageRequest(pageNumber, pageSize, Sort.Direction.DESC,"createTime");
        PageInfo<InvestorSummary> pageInfo = investorsApiService.getInvestors(q, requestPage);
        return new ResponseEntity<PageInfo>(pageInfo,HttpStatus.OK);
    }
    
    public ResponseEntity<HttpMessage> createInvestor(@ApiParam(value = "投资人实体信息" ,required=true ) @RequestBody InvestorDetail investorDetail) {
        // do some magic!
        if (SecurityUtils.getCurrentUserLogin().getId() == null) return new ResponseEntity<HttpMessage>(HttpStatus.UNAUTHORIZED);
        return investorsApiService.createInvestor(investorDetail) == null ?  new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST) : new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    public ResponseEntity<HttpMessage> deleteInvestor(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id) {
        // do some magic!
        investorsApiService.deleteInvestor(id);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    public ResponseEntity<InvestorDetail> getInvestorById(@ApiParam(value = "投资人id",required=true ) @PathVariable("id") Integer id) {
        InvestorDetail investorDetail = investorsApiService.getInvestorById(id);
        return new ResponseEntity<InvestorDetail>(investorDetail, HttpStatus.OK);
    }

    public ResponseEntity<HttpMessage> updateInvestor(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
        @ApiParam(value = "投资人对象" ,required=true ) @RequestBody InvestorDetail investorDetail) {
        // do some magic!
        investorsApiService.updateInvestor(id, investorDetail);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

}
