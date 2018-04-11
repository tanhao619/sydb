package com.cdyoue.oddJobs.api.home;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cdyoue.oddJobs.annotion.authentication.Role;
import com.cdyoue.oddJobs.dto.common.message.HomePageMessage;
import com.cdyoue.oddJobs.dto.home.PageHomeRequest;
import com.cdyoue.spring.common.SortUtils;
import com.cdyoue.spring.page.PageInfo;
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
import com.cdyoue.oddJobs.dto.home.HomePageSumary;
import com.cdyoue.oddJobs.dto.home.PageHome;
import com.cdyoue.oddJobs.dto.home.PageHomeSummary;
import com.cdyoue.oddJobs.service.HomePageService;

import io.swagger.annotations.ApiParam;

import javax.annotation.security.RolesAllowed;

/**
 * Created by liaoyoule on 2017/5/6.
 */
@Controller
public class HomePageApiController implements  HomePageApi {
    @Autowired
    private HomePageService homePageService;
    public ResponseEntity<List<PageHomeSummary>>getPageHomes(@ApiParam(value = "各个板块名称，具体名称对应关系见'首页接口对应表'。", allowableValues = "HOME, XQDT, LGFC, ZSCQ, SCFW, GGFW") @RequestParam(value = "page", required = false) String page) {
        // do some magic!
        List<PageHomeSummary> pageHomeSummaries =   homePageService.listHomePage(page);
        return new ResponseEntity(pageHomeSummaries,HttpStatus.OK);
    }







    public ResponseEntity<PageInfo<PageHomeSummary>>getPageHomes(@ApiParam(value = "各个板块名称，具体名称对应关系见'首页接口对应表'。", allowableValues = "HOME,HOME_0, XQDT, LGFC, ZSCQ, SCFW, GGFW") @RequestParam(value = "page", required = false) String page,
                                                                 @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                                 @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                                 @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                                                 @ApiParam(value = "排序字段和方式 例如：/home?sort=name|-name", allowableValues = "sort, -sort") @RequestParam(value = "sort", required = false, defaultValue = "-sort") String sort) {
        // do some magic!

        Sort sortRequest = new Sort(Sort.Direction.ASC,"portalHomePage.id");
        Sort orders = SortUtils.assembleSort(sort);
        PageRequest pr = new PageRequest(pageNumber,pageSize,sortRequest.and(orders));
        PageInfo<PageHomeSummary> phs =   homePageService.listHomePage(page,q,pr);
        return new ResponseEntity(phs,HttpStatus.OK);
    }



    @Role(value = 2)
    public ResponseEntity<HttpMessage> updateHomePageName(@ApiParam(value = "",required=true ) @PathVariable("code") String code,
        @ApiParam(value = "栏目名称" ,required=true ) @RequestParam String name) {
        // do some magic! xqdt
        String regEx = "^[x][q][d][t][_][1-4]";
        Pattern pattern = Pattern.compile(regEx);

        Matcher matcher = pattern.matcher(code);

        if(!matcher.matches()){
           return new ResponseEntity<HttpMessage>(HomePageMessage.HOMEPAGEUPDATENAMEFAIL, HttpStatus.BAD_REQUEST);
        }

        homePageService.updateHomePageName(code,name);
        return new ResponseEntity<HttpMessage>(HomePageMessage.HOMEPAGEUPDATENAMESUCCESS,HttpStatus.OK);
    }
    @Role(value = 2)
    public ResponseEntity<HttpMessage> updateHomePages(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
        @ApiParam(value = "需求对象" ,required=true ) @RequestBody PageHomeRequest page) {
        // do some magic!
        homePageService.updateHomePage(id,page);
        return new ResponseEntity<HttpMessage>(HomePageMessage.HOMEPAGEUPDATENAMESUCCESS,HttpStatus.OK);
    }

    @Override
    @Role(value = 2)
    public ResponseEntity<HttpMessage> HomePagesMoveUp(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id) {
        homePageService.moveUp(id);
        return new ResponseEntity<HttpMessage>(HomePageMessage.HOMEPAGEMOVEUPSUCCESS,HttpStatus.OK);
    }

    @Override
    @Role(value = 2)
    public ResponseEntity<HttpMessage> HomePagesMoveDown(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id) {
        homePageService.moveDown(id);
        return new ResponseEntity<HttpMessage>(HomePageMessage.HOMEPAGEMOVEDOWNSUCCESS,HttpStatus.OK);
    }
}
