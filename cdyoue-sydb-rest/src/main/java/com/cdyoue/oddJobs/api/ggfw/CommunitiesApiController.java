package com.cdyoue.oddJobs.api.ggfw;

import com.cdyoue.oddJobs.service.CommunityService;
import com.cdyoue.spring.common.SortUtils;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.cdyoue.oddJobs.dto.ggfw.CommunitySummary;
import org.springframework.web.bind.annotation.RequestParam;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-09T00:59:32.305Z")

@Controller
public class CommunitiesApiController implements CommunitiesApi {

    @Autowired
    CommunityService communityService;


    @Override
    public ResponseEntity<PageInfo> getCommunities(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize, @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber) {
        //Hardcode sort
        Sort order = SortUtils.assembleSort("publishTime");
        PageRequest pr = new PageRequest(pageNumber,pageSize,order);
        PageInfo<CommunitySummary> pageInfo = communityService.findCommunityList(pr);
        return new ResponseEntity<com.cdyoue.spring.page.PageInfo>(pageInfo,HttpStatus.OK);
    }
}
