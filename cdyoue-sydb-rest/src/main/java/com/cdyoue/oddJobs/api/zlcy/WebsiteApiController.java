package com.cdyoue.oddJobs.api.zlcy;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.zlcy.WebsiteRequest;
import com.cdyoue.oddJobs.dto.zlcy.WebsiteSummary;
import com.cdyoue.oddJobs.service.sydb.WebsiteService;
import com.cdyoue.spring.common.SortUtils;
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

import java.util.List;


/**
 * Created by dengshaojun on 2017/09/15.
 */
@Controller
public class WebsiteApiController implements WebsiteApi {

    @Autowired
    private WebsiteService websiteService;

    @Override
    public ResponseEntity<PageInfo<WebsiteSummary>> getWebsitePage(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", defaultValue="10") Integer pageSize,
                                                            @ApiParam(value = "页码。默认第一页", defaultValue = "1") @RequestParam(value = "pageNumber", defaultValue="1") Integer pageNumber,
                                                            @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q) {
        Sort order = SortUtils.assembleSort("id");
        Pageable pageRequest = new PageRequest(pageNumber - 1, pageSize, order);
        PageInfo<WebsiteSummary> pageInfo = websiteService.findWebsites(q, pageRequest);
        return new ResponseEntity(pageInfo, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<WebsiteSummary>> getWebsites() {
        Sort sort = SortUtils.assembleSort("id");
        List<WebsiteSummary> list = websiteService.findWebsite(sort);
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> addWebsite(@ApiParam(value = "网址站表单", required = true) @RequestBody WebsiteRequest websiteRequest) {
        websiteService.saveWebsite(websiteRequest);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> editWebsite(@ApiParam(value = "专家成果id", required = true) @PathVariable("id") Integer id,
                                                   @ApiParam(value = "网址站表单", required = true) @RequestBody WebsiteRequest websiteRequest) {
        websiteService.updateWebsite(id, websiteRequest);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> deleteWebsite(@ApiParam(value = "网址站ids", required = true) @RequestParam Integer[] ids) {
        websiteService.deleteWebsite(ids);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

}
