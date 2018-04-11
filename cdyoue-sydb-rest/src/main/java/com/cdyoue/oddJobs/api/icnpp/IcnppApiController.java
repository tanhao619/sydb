package com.cdyoue.oddJobs.api.icnpp;

import com.cdyoue.oddJobs.dto.icnpp.policy.PolicyDetail;
import com.cdyoue.oddJobs.dto.icnpp.policy.PolicySumary;
import com.cdyoue.oddJobs.service.IcnppService;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by liaoyoule on 2017/6/21.
 */
@Controller
public class IcnppApiController implements IcnppApi{


    @Autowired

    private IcnppService icnppService;

    @Override
    public ResponseEntity<PageInfo<PolicySumary>> getPolices(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                             @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                             @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q) {

        PageRequest pr = new PageRequest(pageNumber, pageSize);

        return new ResponseEntity(icnppService.getPolices(q,pr), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PolicyDetail> getPolice(@ApiParam(value = "政策id") @PathVariable(value = "id") String id) {


        return new ResponseEntity<PolicyDetail>(icnppService.getPolice(id),HttpStatus.OK);
    }
}
