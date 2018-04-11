package com.cdyoue.oddJobs.api.scfw;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.scfw.EnterServiceDetail;
import com.cdyoue.oddJobs.dto.scfw.EnterServiceSummary;
import com.cdyoue.oddJobs.service.EnterserviceService;
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
public class EnterApiController implements EnterApi {

    @Autowired
    private EnterserviceService enterserviceService;

    public ResponseEntity<HttpMessage> createEnterService(@ApiParam(value = "服务机构实体信息" ,required=true ) @RequestBody EnterServiceDetail enterServiceDetail) {
        // do some magic!
        Integer id= enterserviceService.createEnterservice(enterServiceDetail);
        return id == null ? new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST) : new ResponseEntity<HttpMessage>(HttpStatus.CREATED);
    }

    public ResponseEntity<HttpMessage> deleteEnterService(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id) {
        // do some magic!
        //判断id对应的数据是否存在
        if(enterserviceService.getEnterserviceById(id) == null) return new ResponseEntity<HttpMessage>(HttpStatus.NOT_FOUND);
        //判断该用户是否可编辑或删除该条目
        //if(!enterserviceService.updateOrDeleteAuthority(id)) return new ResponseEntity<HttpMessage>(HttpStatus.UNAUTHORIZED);
        enterserviceService.deleteEnterService(id);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    public ResponseEntity<PageInfo> getEnterService(@NotNull @ApiParam(value = "服务机构类别，zscq：知识产权，scfw：双创服务", required = true, allowableValues = "ZSCQ, SCFW") @RequestParam(value = "type", required = true) String type,
                                                    @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                                    @ApiParam(value = "页码。默认第0页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
                                                    @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q) {
        // do some magic!
        Pageable requestPage = new PageRequest(pageNumber, pageSize, Sort.Direction.DESC,"createTime");
        PageInfo<EnterServiceSummary> pageInfo = enterserviceService.findByKeyWord(requestPage, q, type);
        return new ResponseEntity<PageInfo>(pageInfo, HttpStatus.OK);
    }

    public ResponseEntity<EnterServiceDetail> getEnterServiceById(@ApiParam(value = "服务机构id",required=true ) @PathVariable("id") Integer id) {
        // do some magic!
        EnterServiceDetail enterServiceDetail = enterserviceService.getEnterserviceById(id);
        return new ResponseEntity<EnterServiceDetail>(enterServiceDetail, HttpStatus.OK);
    }

    public ResponseEntity<HttpMessage> updateEnterService(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
        @ApiParam(value = "服务机构对象" ,required=true ) @RequestBody EnterServiceDetail enterServiceDetail) {
        // do some magic!
        //判断id对应的数据是否存在
        if(enterserviceService.getEnterserviceById(id) == null) return new ResponseEntity<HttpMessage>(HttpStatus.NOT_FOUND);
        enterserviceService.updateEnterservice(id, enterServiceDetail);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

}
