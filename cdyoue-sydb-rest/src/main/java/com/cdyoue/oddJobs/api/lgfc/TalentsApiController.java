package com.cdyoue.oddJobs.api.lgfc;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.common.message.CommonMessage;
import com.cdyoue.oddJobs.dto.common.message.TalentMessage;
import com.cdyoue.oddJobs.dto.lgfc.*;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.service.TalentService;
import com.cdyoue.oddJobs.service.UserpersonalService;
import com.cdyoue.oddJobs.utils.SecurityUtils;
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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-04T01:20:49.378Z")

@Controller
public class TalentsApiController implements TalentsApi {
    @Autowired
    private UserpersonalService userpersonalService;

    @Autowired
    private TalentService talentService;

    public ResponseEntity<HttpMessage> addMyCareer(@ApiParam(value = "职业经历" ,required=true ) @RequestBody TalentCareer talentCareer) {
        Integer id=talentService.addMyCareer(talentCareer);
        return id == null ? new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST) : new ResponseEntity<HttpMessage>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<HttpMessage> addMyEducation(@ApiParam(value = "教育经历", required = true) @RequestBody TalentEducation talentEducation) {
        Integer id=talentService.addMyEducation(talentEducation);
        return id == null ? new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST) : new ResponseEntity<HttpMessage>(HttpStatus.CREATED);
    }

    public ResponseEntity<HttpMessage> followTalent(@ApiParam(value = "用户id",required=true ) @PathVariable("id") Integer id) {
        // do some magic!
//        int ret = Tencent.startWPAConversation(WPAActivity.this,uin, "");
        userpersonalService.followTalent(id);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    public ResponseEntity<PageInfo> getMyCareer( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber) {
        //用户是否登录
        if (SecurityUtils.getCurrentUserLogin() == null) return new ResponseEntity<PageInfo>(HttpStatus.UNAUTHORIZED);
        Pageable requestPage = new PageRequest(pageNumber, pageSize, Sort.Direction.DESC,"endTime");
        PageInfo<TalentCareer> pageInfo = talentService.findAllCareers(requestPage);
        return new ResponseEntity<PageInfo>(pageInfo, HttpStatus.OK);
    }

    public ResponseEntity<PageInfo> getMyEducation( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber) {
        //用户是否登录
        if (SecurityUtils.getCurrentUserLogin() == null) return new ResponseEntity<PageInfo>(HttpStatus.UNAUTHORIZED);
        Pageable requestPage = new PageRequest(pageNumber, pageSize);
        PageInfo<TalentEducation> pageInfo = talentService.findAllEducations(requestPage);
        return new ResponseEntity<PageInfo>(pageInfo, HttpStatus.OK);
    }

    public ResponseEntity getMyTalents( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
         @ApiParam(value = "按照资料完整度和被邀请数排序 例如：/talents?sort=-dataCom", allowableValues = "DATACOM, INVITEDNUM") @RequestParam(value = "sort", required = false) String sort,
         @ApiParam(value = "所属行业id, 过滤条件") @RequestParam(value = "industry", required = false) Integer industry) {
        // do some magic!
        if(null == sort) sort = "id";
        Sort orders = SortUtils.assembleSort(sort);
        Pageable requestPage = new PageRequest(pageNumber, pageSize, orders);
        PageInfo<TalentAbility> pageInfo = userpersonalService.getMyTalents(requestPage);
        if (pageInfo.getList() == null || pageInfo.getList().size() < 1) return new ResponseEntity<HttpMessage>(CommonMessage.GETMYFOLLOWTALENTNOTFOUND, HttpStatus.NOT_FOUND);
        return new ResponseEntity<com.cdyoue.spring.page.PageInfo>(pageInfo, HttpStatus.OK);
    }

    public ResponseEntity<TalentImage> getTalentAbility(@ApiParam(value = "用户id",required=true ) @PathVariable("id") Integer id) {
            TalentImage talentImage = talentService.findTalentImageById(id);
            return new ResponseEntity<TalentImage>(talentImage, HttpStatus.OK);
    }

    public ResponseEntity getTalentBaseInfo(@ApiParam(value = "用户id",required=true ) @PathVariable("id") Integer id) {
        TalentInfo talentInfo = userpersonalService.getTalentBaseInfo(id);
        if (talentInfo == null) throw new NotFoundEntityException("找不到该能人");
        return new ResponseEntity(talentInfo,HttpStatus.OK);
    }

    public ResponseEntity<List<TalentSummary>> getTalentExperience(@ApiParam(value = "用户id",required=true ) @RequestParam("id") Integer id,
                                                             @ApiParam(value = "技能类型：0全部，1项目经验，2教育经历，3职业经历", required = true) @RequestParam("type") Integer type) {
        // do some magic!
        return new ResponseEntity<List<TalentSummary>>(userpersonalService.getTalentExperience(id,type),HttpStatus.OK);
    }

    public ResponseEntity<PageInfo> getTalents(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                                       @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
                                                       @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                                       @ApiParam(value = "按照资料完整度和被邀请数排序 例如：/talents?sort=-dataComp", allowableValues = "dataComp,-dataComp, invitedNum,-invitedNum") @RequestParam(value = "sort", required = false) String sort,
                                                       @ApiParam(value = "所属行业id, 过滤条件") @RequestParam(value = "industry", required = false) Integer industry) {
        // do some magic!
        if(null == sort) sort = "-id";//其实是按照认证时间排序
        Sort orders = SortUtils.assembleSort(sort);
        Pageable requestPage = new PageRequest(pageNumber, pageSize,orders);
        PageInfo<TalentBase> pageInfo = userpersonalService.findByKeyWord(q, industry,1, requestPage);
        return new ResponseEntity<com.cdyoue.spring.page.PageInfo>(pageInfo, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageInfo> getTalentsExperts(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                                      @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
                                                      @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                                      @ApiParam(value = "认证类型：1实名，2大咖，3导师',",allowableValues = "1,2,3") @RequestParam(value = "expertType", required = false) Integer expertType,
                                                      @ApiParam(value = "按照资料完整度和被邀请数排序 例如：/talents?sort=-dataCom") @RequestParam(value = "sort", required = false) String sort,
                                                      @ApiParam(value = "所属行业id, 过滤条件") @RequestParam(value = "industry", required = false) Integer industry) {
        if(null == sort) sort = "-id";//其实是按照认证通过时间倒序排序
        Sort orders = SortUtils.assembleSort(sort);
        Pageable requestPage = new PageRequest(pageNumber, pageSize, orders);
        PageInfo<TalentBase> pageInfo = userpersonalService.findByKeyWord(q, industry,expertType, requestPage);
        return new ResponseEntity<com.cdyoue.spring.page.PageInfo>(pageInfo, HttpStatus.OK);
    }

    public ResponseEntity<List<TalentBase>> getrRecommandsFullJob(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                                                  @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
                                                                  @ApiParam(value = "按照资料完整度和被邀请数排序 例如：/talents?sort=-dataCom", allowableValues = "DATACOM, INVITEDNUM") @RequestParam(value = "sort", required = false) String sort,
                                                                  @ApiParam(value = "需要被推荐的企业招聘的职位id（此参数为推荐系统参数）", required = true) @RequestParam("id") Integer id) {
        // do some magic!
        if(userpersonalService.getRecommandUsers("full",id)==null){
            return new ResponseEntity(TalentMessage.NO_Talent,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<TalentBase>>(userpersonalService.getRecommandUsers("full",id),HttpStatus.OK);
    }

    public ResponseEntity<List<TalentBase>> getrRecommandsPartJob( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
         @ApiParam(value = "按照资料完整度和被邀请数排序 例如：/talents?sort=-dataCom", allowableValues = "DATACOM, INVITEDNUM") @RequestParam(value = "sort", required = false) String sort,
                                                                   @ApiParam(value = "需要被推荐的兼职工作的职位id（此参数为推荐系统参数）", required = true) @RequestParam("id") Integer id) {
        // do some magic!
        if(userpersonalService.getRecommandUsers("part",id)==null){
            return new ResponseEntity(TalentMessage.NO_Talent,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<TalentBase>>(userpersonalService.getRecommandUsers("part",id),HttpStatus.OK);
    }

    public ResponseEntity<HttpMessage> unfollowTalent(@ApiParam(value = "用户id",required=true ) @PathVariable("id") Integer id) {
        // do some magic!
        userpersonalService.unFollowTalent(id);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    public ResponseEntity<HttpMessage> updateMyCareer(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,@ApiParam(value = "职业经历" ,required=true ) @RequestBody TalentCareer talentCareer) {
        talentService.updateTalentCareer(id, talentCareer);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
        }

    public ResponseEntity<HttpMessage> updateMyEducation(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id, @ApiParam(value = "教育经历", required = true) @RequestBody TalentEducation talentEducation) {
        talentService.updateTalentEducation(id, talentEducation);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);

    }

}
