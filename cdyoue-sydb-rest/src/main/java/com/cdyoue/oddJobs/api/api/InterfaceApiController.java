package com.cdyoue.oddJobs.api.api;

import com.cdyoue.oddJobs.annotion.authentication.Role;
import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.common.message.CommonMessage;
import com.cdyoue.oddJobs.dto.other.InterfaceApiRequest;
import com.cdyoue.oddJobs.dto.other.PortalApiSumary;
import com.cdyoue.oddJobs.service.ApiService;
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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-20T01:12:00.448Z")
@Controller
public class InterfaceApiController implements InterfaceApi {

    @Autowired
    private ApiService apiService;
    @Override
    public ResponseEntity<PageInfo<PortalApiSumary>> getApis(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                             @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                             @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                                             @ApiParam(value = "接口分类 1:专利相关 2: 无关", allowableValues = "1,2") @RequestParam(value = "type", required = false) Integer type,
                                                             @ApiParam(value = "排序字段和方式 例如：/policies?sort=createTime|-createTime", allowableValues = "createTime, -createTime") @RequestParam(value = "sort", required = false, defaultValue = "-createTime") String sort) {





        PageRequest pr = new PageRequest(pageNumber, pageSize, SortUtils.assembleSort(sort));
        return new ResponseEntity<PageInfo<PortalApiSumary>>(apiService.getApis(q, pr), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> deleteApi(@ApiParam(value = "", required = true) @PathVariable("id") Integer id) {
        apiService.delete(id);
        return new ResponseEntity<HttpMessage>(CommonMessage.DELETESUCCESS,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PortalApiSumary> getApi(@ApiParam(value = "", required = true) @PathVariable("id") Integer id) {
        return new ResponseEntity<PortalApiSumary>( apiService.getApis(id),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> createApi(@ApiParam(value = "", required = true) @RequestBody() InterfaceApiRequest interfaceApiRequest) {
        apiService.createApi(interfaceApiRequest);
        return new ResponseEntity<HttpMessage>(CommonMessage.CREATESUCCESS,HttpStatus.OK);
    }


    @Override
    public ResponseEntity<HttpMessage> updateApi( @ApiParam(value = "", required = true) @PathVariable("id") Integer id,
                                                  @ApiParam(value = "", required = true) @RequestBody() InterfaceApiRequest interfaceApiRequest) {


        apiService.updateApi(id,interfaceApiRequest);
        return new ResponseEntity<HttpMessage>(CommonMessage.UPDATESUCCESS,HttpStatus.OK);
    }
}
