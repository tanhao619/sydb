package com.cdyoue.oddJobs.api.requirement;


import com.cdyoue.oddJobs.annotion.authentication.Login;
import com.cdyoue.oddJobs.annotion.authentication.Role;
import com.cdyoue.oddJobs.constant.TrackCategories;
import com.cdyoue.oddJobs.dto.AcceptPeopleSumary;
import com.cdyoue.oddJobs.dto.Contact;
import com.cdyoue.oddJobs.dto.Reason;
import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.common.message.CommonMessage;
import com.cdyoue.oddJobs.dto.common.message.RequirementMessage;
import com.cdyoue.oddJobs.dto.requirement.*;
import com.cdyoue.oddJobs.en.LoginTypeEnum;
import com.cdyoue.oddJobs.event.EventPiwikTracking;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.service.CategoryService;
import com.cdyoue.oddJobs.service.LgPortalRequirementService;
import com.cdyoue.oddJobs.service.UserService;
import com.cdyoue.oddJobs.service.UserpersonalService;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.common.SortUtils;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-20T01:12:00.448Z")

@Controller
public class RequirementsApiController implements RequirementsApi {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private LgPortalRequirementService lgPortalRequirementService;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${default.recommend.remoteUrl}")
    private String recommendRemoteUrl;
    @Autowired
    private UserpersonalService userpersonalService;

    @Autowired
    private UserService userService;


    @Autowired
    private EventPiwikTracking eventPiwikTracking;

    public ResponseEntity<HttpMessage> acceptReqiurement(@ApiParam(value = "", required = true) @PathVariable("id") Integer id,
                                                         @ApiParam(value = "") @RequestBody Contact contactInfo) {
        // do some magic!
        lgPortalRequirementService.acceptReqiurement(id, contactInfo);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    @Role(value = 2)
    public ResponseEntity<HttpMessage> approveReqiurement(@ApiParam(value = "", required = true) @PathVariable("id") Integer id,
                                                          @ApiParam(value = "理由", required = true)@RequestBody Reason reason) {
        // do some magic!
        Integer idAf = lgPortalRequirementService.approve(id,reason);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    public ResponseEntity<HttpMessage> cancelReqiurement(@ApiParam(value = "", required = true) @PathVariable("id") Integer id) {
        // do some magic!
        lgPortalRequirementService.cancelReqiurement(id);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }
    @Login
    public ResponseEntity<HttpMessage> closeReqiurement(@ApiParam(value = "", required = true) @PathVariable("id") Integer id) {
        // do some magic!
        lgPortalRequirementService.close(id);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> openReqiurement(@ApiParam(value = "", required = true) @PathVariable("id") Integer id
                                                       ) {
        lgPortalRequirementService.openReqiurement(id);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    public ResponseEntity<HttpMessage> inviteReqiurement(@ApiParam(value = "需求id", required = true) @PathVariable("id") String id,
                                                         @ApiParam(value = "用户id", required = true) @PathVariable("userid") Integer userid) {
        // do some magic!
        lgPortalRequirementService.inviteReqiurement(id, userid);
        userpersonalService.updateInvitedNum(userid);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    @Role(value = 2)
    public ResponseEntity<HttpMessage> rejectReqiurement(@ApiParam(value = "", required = true) @PathVariable("id") Integer id,
                                                         @ApiParam(value = "理由", required = true) @RequestBody Reason reason
                                                         ) {
        // do some magic!
        Integer idAf = lgPortalRequirementService.reject(id,reason);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    @Login
    public ResponseEntity<HttpMessage> createRequirements(@ApiParam(value = "需要发布的需求实体信息", required = true) @Valid @RequestBody RequireRequest reqiurement) {
        Integer id = lgPortalRequirementService.save(reqiurement);
        UserMine um = SecurityUtils.getCurrentUserLogin();
        if(um.getLoginTypeEnum().equals(LoginTypeEnum.APP)){
            eventPiwikTracking.doTracking(TrackCategories.PUBLISH_REQ,um.getName());
        }
        // do some magic!
        return id == null ? new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST) : new ResponseEntity<HttpMessage>(HttpStatus.CREATED);
    }
    @Login
    public ResponseEntity<HttpMessage> deleteRequirements(@ApiParam(value = "", required = true) @PathVariable("id") Integer id) {
        lgPortalRequirementService.deleteByPrimary(id);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }
    public ResponseEntity<List<RecommendTalentBase>> getHotReqiureTalents(@ApiParam(value = "“注意！！”传入需要被推荐外包项目的【分类id(TypeId)】", required = true) @PathVariable("id") Integer id) {
        // do some magic!

        Map parm = new HashMap();
        parm.put("id", id);
        RecommendeTalents tr = null;
        try {
            tr = (RecommendeTalents) restTemplate.getForEntity(recommendRemoteUrl.concat("/recommendDngineer?id={id}"), RecommendeTalents.class, parm).getBody();
        } catch (RestClientException e) {
            return new ResponseEntity<>(HttpStatus.REQUEST_TIMEOUT);
        }
        if (tr.getResult() == 0) {
            throw new NotFoundEntityException("没有找到合适的推荐数据");
        }
        /*if (tr.getResult() == -1 || tr.getData().size() == 0) {
            return new ResponseEntity(RequirementMessage.BADREQUESTERECOMMENDNOTFOUNDRROR, HttpStatus.NOT_FOUND);
        }*/
        List<RecommendTalentBase> tbs = userService.findReqiureTalents(tr.getData(), id);
        return new ResponseEntity<List<RecommendTalentBase>>(tbs, HttpStatus.OK);
    }


    public ResponseEntity<List<RecommandResponse>> getHotReqiures() {
        // do some magic!

        Map parm = new HashMap();
        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();

//        if (currentUserLogin == null) {
//            return new ResponseEntity(RequirementMessage.BADREQUESTERECOMMENDRROR, HttpStatus.BAD_REQUEST);
//        }
        int userId=SecurityUtils.getCurrentUserLogin() == null ? -1: SecurityUtils.getCurrentUserLogin().getId();
        
        parm.put("id", userId);
        RecommendeRequirement rr = null;
        try {
            rr = (RecommendeRequirement) restTemplate.getForEntity(recommendRemoteUrl.concat("/recommendOutsourcingProject?id={id}"), RecommendeRequirement.class, parm).getBody();
        } catch (Exception e) {
            return new ResponseEntity(RequirementMessage.BADREQUESTERECOMMENDNOTFOUNDRROR, HttpStatus.NOT_FOUND);
        }
        if (rr.getResult() == -1 || rr.getData().size() == 0) {
            return new ResponseEntity(RequirementMessage.BADREQUESTERECOMMENDNOTFOUNDRROR, HttpStatus.NOT_FOUND);
        }
        List<RecommandResponse> rrs = lgPortalRequirementService.findRecommand(rr.getData());
        return new ResponseEntity<List<RecommandResponse>>(rrs, HttpStatus.OK);
    }

    public ResponseEntity<PageInfo<RequireMine>> getMyReqiures(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                               @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                               @ApiParam(value = "需求类型：pub:我发布的，acc:承接，rec:我收到", allowableValues = "PUB, ACC, REC") @RequestParam(value = "type", required = false) String type,
                                                               @ApiParam(value = "查询条件") @RequestParam(value = "q", required = false) String q,
                                                               @ApiParam(value = "排序字段和方式 例如：/policies?sort=-create_time|-update_time", allowableValues = "BUDGET, PUBLISHDATE") @RequestParam(value = "sort", required = false, defaultValue = "-createTime") String sort) {
        // do some magic!
        if (StringUtils.isBlank(type)) {
            return new ResponseEntity<PageInfo<RequireMine>>(HttpStatus.BAD_REQUEST);
        }
        Sort orders = SortUtils.assembleSort(sort);
        PageRequest pr = new PageRequest(pageNumber, pageSize, orders);
        PageInfo<RequireMine> pageInfo = lgPortalRequirementService.getMyReqiures(type, q, pr);

        return new ResponseEntity<PageInfo<RequireMine>>(pageInfo, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageInfo<RequireMine>> getReqiure(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                            @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                            @ApiParam(value = "用户Id") @PathVariable(value = "userId", required = false) Integer userId,
                                                            @ApiParam(value = "排序字段和方式 例如：/policies?sort=-create_time|-update_time", allowableValues = "BUDGET, PUBLISHDATE") @RequestParam(value = "sort", required = false, defaultValue = "-createTime") String sort) {
        // do some magic!
        if (userId == null) {
            return new ResponseEntity<PageInfo<RequireMine>>(HttpStatus.BAD_REQUEST);
        }
        Sort orders = SortUtils.assembleSort(sort);
        PageRequest pr = new PageRequest(pageNumber, pageSize, orders);
        PageInfo<RequireMine> pageInfo = lgPortalRequirementService.getReqiure(userId, pr);

        return new ResponseEntity<PageInfo<RequireMine>>(pageInfo, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getSomeOneReqiure(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                                   @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                                   @ApiParam(value = "用户Id") @PathVariable(value = "userId", required = true) Integer userId,
                                                                   @ApiParam(value = "查询条件") @RequestParam(value = "q", required = false) String q,
                                                                   @ApiParam(value = "排序字段和方式 例如：/policies?sort=-create_time|-update_time", allowableValues = "BUDGET, PUBLISHDATE") @RequestParam(value = "sort", required = false,defaultValue = "-createTime") String sort) {
        //do some magic!
        Sort orders = SortUtils.assembleSort(sort);
        PageRequest pr = new PageRequest(pageNumber, pageSize, orders);
        PageInfo<RequireMini> pageInfo = lgPortalRequirementService.getRequirementByUserId(userId, pr);
        if (pageInfo.getList() == null | pageInfo.getList().size() < 1) {
            return new ResponseEntity<HttpMessage>(CommonMessage.GETSOMEONEREQUIRENOTFOUND, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<PageInfo<RequireMini>>(pageInfo, HttpStatus.OK);
    }

    public ResponseEntity<RequireComp> getRequirementsById(@ApiParam(value = "", required = true) @PathVariable("id") Integer id) {
        // do some magic!
        RequireComp rqc = lgPortalRequirementService.getRequirementById(id);

        return new ResponseEntity<RequireComp>(rqc, HttpStatus.OK);
    }

    public ResponseEntity<PageInfo> getRequirements(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                     @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                     @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                                     @ApiParam(value = "分类id") @RequestParam(value = "categoryId", required = false) Integer categoryId,
                                                     @ApiParam(value = "需求状态 0待审核，1审核失败 2审核成功',", allowableValues = "0,1,2") @RequestParam(value = "reviewStatus", required = false) Integer reviewStatus,
                                                     @ApiParam(value = "排序字段和方式 例如：/policies?sort=-create_time|-update_time", allowableValues = "createTime, -createTime,proBudget,-proBudget") @RequestParam(value = "sort", required = false, defaultValue = "-createTime") String sort) {
        // do some magic!
        Sort orders = SortUtils.assembleSort(sort);
        Pageable requestPage = new PageRequest(pageNumber, pageSize, orders);
        PageInfo<RequireSummary> pageInfo = lgPortalRequirementService.findByKeyWord(q, categoryId, reviewStatus, requestPage);
        return new ResponseEntity<com.cdyoue.spring.page.PageInfo>(pageInfo, HttpStatus.OK);
    }

    public ResponseEntity<HttpMessage> updateRequirements(@ApiParam(value = "", required = true) @PathVariable("id") Integer id,
                                                          @ApiParam(value = "需求对象", required = true) @RequestBody @Valid RequireRequest reqiurement) {

        // do some magic!
        Integer idAf = lgPortalRequirementService.updateRequirements(id, reqiurement);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageInfo<AcceptPeopleSumary>> getAcceptPeoples(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                                         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                                         @ApiParam(value = "需求id") @PathVariable(value = "id", required = true) Integer id,
                                                                         @ApiParam(value = "排序字段和方式 例如：/policies?sort=-create_time|-update_time", allowableValues = "BUDGET, PUBLISHDATE") @RequestParam(value = "sort", required = false, defaultValue = "-createTime") String sort) {
        if (id == null) {
            return new ResponseEntity<PageInfo<AcceptPeopleSumary>>(HttpStatus.BAD_REQUEST);
        }
        Sort orders = SortUtils.assembleSort(sort);
        Pageable requestPage = new PageRequest(pageNumber, pageSize, orders);
        PageInfo<AcceptPeopleSumary> pageInfo = lgPortalRequirementService.findAcceptPeoples(id, requestPage);
        return new ResponseEntity<PageInfo<AcceptPeopleSumary>>(pageInfo, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getMyInviteRequirment(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize, @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber) {
        Sort orders = SortUtils.assembleSort("publishTime");
        UserMine u = SecurityUtils.getCurrentUserLogin();
        if (u == null) return new ResponseEntity<HttpMessage>(CommonMessage.GETMYINVITEREQUIRUNAUTH, HttpStatus.UNAUTHORIZED);
        Pageable requestPage = new PageRequest(pageNumber, pageSize, orders);
        PageInfo<InviteMeRequireSummary> pageInfo = lgPortalRequirementService.findInviteMeRequirement(requestPage);
        if (pageInfo.getList() == null || pageInfo.getList().size() < 1) new ResponseEntity<HttpMessage>(CommonMessage.GETINVITEMEREQUIRNOTFOUND, HttpStatus.NOT_FOUND);
        return new ResponseEntity<PageInfo<InviteMeRequireSummary>>(pageInfo, HttpStatus.OK);
    }
}
