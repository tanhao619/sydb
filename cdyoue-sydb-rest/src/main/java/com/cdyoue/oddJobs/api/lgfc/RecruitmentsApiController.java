package com.cdyoue.oddJobs.api.lgfc;

import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.common.message.RecruitmentsMessage;
import com.cdyoue.oddJobs.dto.lgfc.*;
import com.cdyoue.oddJobs.en.MessageEventTypeEnum;
import com.cdyoue.oddJobs.en.MessageMsgTypeEnum;
import com.cdyoue.oddJobs.entity.lgsq.user.UserenterpriseEntity;
import com.cdyoue.oddJobs.service.*;
import com.cdyoue.oddJobs.utils.MessageUtils;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.common.SortUtils;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-06T22:58:08.241Z")

@Controller
public class RecruitmentsApiController implements RecruitmentsApi {


    @Autowired
    private PortalWordService portalWordService;
    @Autowired
    private RecruitmentsService recruitmentsService;
    @Autowired
    private RecruitsPartService recruitsPartService;
    @Autowired
    private UserEnterpriseService userEnterpriseService;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${default.recommend.remoteUrl}")
    private String recommendRemoteUrl;
    @Autowired
    private UserpersonalService userpersonalService;

    public ResponseEntity<HttpMessage> deleteRecruitment(@ApiParam(value = "职位id",required=true ) @PathVariable("id") Integer id,
                                                         @NotNull @ApiParam(value = "类型：fulljob全职，partjob兼职 如/recruitments/{id}?type=fulljob", required = true, allowableValues = "FULLJOB, PARTJOB") @RequestParam(value = "type", required = true) String type) {
        // 全职
        if(type!=null&&type.equalsIgnoreCase("fulljob")){
            RecruitmentsDetail recruitmentsDetail = recruitmentsService.getFullRecruitmentById(id);
            if (recruitmentsDetail == null) {
                return new ResponseEntity<HttpMessage>(RecruitmentsMessage.RECRUITMENTREDELFAILD, HttpStatus.NOT_FOUND);
            }
            recruitmentsService.deleteById(id);
            return new ResponseEntity<HttpMessage>(RecruitmentsMessage.RECRUITMENTREDELSUCCESS, HttpStatus.CREATED);
        }
        // 兼职
        if(type!=null&&type.equalsIgnoreCase("partjob")){
            RecruitmentsDetail recruitmentsDetail = recruitsPartService.getPartRecruitmentById(id);
            if (recruitmentsDetail == null) {
                return new ResponseEntity<HttpMessage>(RecruitmentsMessage.RECRUITMENTREDELFAILD, HttpStatus.NOT_FOUND);
            }
            recruitsPartService.deleteById(id);
            return new ResponseEntity<HttpMessage>(RecruitmentsMessage.RECRUITMENTREDELSUCCESS, HttpStatus.CREATED);
        }
        return new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST);
    }

    /**
     * 应聘兼职/全职，给雇主加留言（介绍自己、联系电话）
     * @param id
     * @param type
     * @return
     */
    public ResponseEntity<HttpMessage> applyRecruitment(@ApiParam(value = "职位id",required=true ) @PathVariable("id") Integer id,
                                                        @NotNull @ApiParam(value = "类型：fulljob全职，partjob兼职 如/recruitments/{id}/apply?type=fulljob", required = true, allowableValues = "FULLJOB, PARTJOB") @RequestParam(value = "type", required = true) String type,
                                                        @ApiParam(value = "给雇主留言",required=false ) @RequestBody Word4Work word4Work) {
        // do some magic!
        //只有个人用户才能应聘职位
        if(SecurityUtils.getCurrentUserLogin().getUserType()!=null&&SecurityUtils.getCurrentUserLogin().getUserType()==0){
            if(type!=null&&type.equalsIgnoreCase("fulljob")&&!MessageUtils.isMessageExist(id, MessageEventTypeEnum.APPLYFOR, MessageMsgTypeEnum.ApplyForFullTimeJob)){
                Integer entId= recruitmentsService.getFullRecruitmentEntId(id);
                UserenterpriseEntity uent = userEnterpriseService.findOne(entId);
                if(null != uent){
                    MessageUtils.createMessage(uent.getUserId(),id, MessageEventTypeEnum.APPLYFOR, MessageMsgTypeEnum.ApplyForFullTimeJob);
                }
                portalWordService.save4Work(id, word4Work,1);
                return new ResponseEntity<HttpMessage>(HttpStatus.OK);
            }
            if(type!=null&&type.equalsIgnoreCase("partjob")&&!MessageUtils.isMessageExist(id, MessageEventTypeEnum.APPLYFOR, MessageMsgTypeEnum.ApplyForPartTimeJob)){
                Integer entId= recruitsPartService.getFullRecruitmentEntId(id);
                UserenterpriseEntity uent = userEnterpriseService.findOne(entId);
                if(null != uent){
                    MessageUtils.createMessage(uent.getUserId(),id, MessageEventTypeEnum.APPLYFOR, MessageMsgTypeEnum.ApplyForPartTimeJob);
                }
                portalWordService.save4Work(id, word4Work,2);
                return new ResponseEntity<HttpMessage>(HttpStatus.OK);
            }
            return new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<HttpMessage>(RecruitmentsMessage.RECRUITMENTACCEPTFAILED,HttpStatus.OK);
        }

    }

    /**
     * 获得企业发布的全职
     * @param pageSize
     * @param pageNumber
     * @return
     */
    public ResponseEntity<PageInfo<RecruitmentsFullJobs>> getEnterpFullRecruitments( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                                                                     @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                                                                     @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber) {
        // do some magic!
        UserMine userMine=SecurityUtils.getCurrentUserLogin();
       
        if(userMine!=null){
            //根据userId查询对应的企业ID
        	int userId=userMine.getId();
        	int userType=userMine.getUserType();
        	if(userType==1){
                int enterpriseId=userEnterpriseService.findEnterpriseIdByUserId(userId);
                Pageable requestPage=new PageRequest(pageNumber,pageSize, Sort.Direction.DESC,"refreshTime");
                PageInfo<RecruitmentsFullJobs> enterpDetailPageInfo=recruitmentsService.getUserEnterpriseFullJobs(enterpriseId,q,requestPage);
                if(enterpDetailPageInfo.getList()!=null&&enterpDetailPageInfo.getList().size()!=0){
                    return new ResponseEntity<PageInfo<RecruitmentsFullJobs>>(enterpDetailPageInfo,HttpStatus.OK);
                }else {
                    return new ResponseEntity(RecruitmentsMessage.RECRUITMENTENTNOT_FOUND,HttpStatus.NOT_FOUND);//没有
                }
            }else {
                return new ResponseEntity(RecruitmentsMessage.RECRUITMENTENT_FORBIDDENNOT_FOUND,HttpStatus.NOT_FOUND);
            }
        }else{
             return new ResponseEntity(RecruitmentsMessage.RECRUITMENTENTUNAUTHORIZED,HttpStatus.UNAUTHORIZED);//没有登录
        }
    }

    /**
     * 获取我收到的全职邀请
     * @param pageSize
     * @param q
     * @param pageNumber
     * @return
     */
    @Override
    public ResponseEntity<PageInfo<RecruitmentsFullJobs>> getMyInviteFullRecruitments(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                                                                      @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                                                                      @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber) {
        UserMine userMine=SecurityUtils.getCurrentUserLogin();
        if(userMine==null){
            return new ResponseEntity(RecruitmentsMessage.RECRUITMENTENTUNAUTHORIZED,HttpStatus.UNAUTHORIZED);//没有登录
        }else {
            Integer userId=userMine.getId();
            Sort order = SortUtils.assembleSort("-refreshTime");
            Pageable requestPage=new PageRequest(pageNumber,pageSize,order);
            PageInfo<RecruitmentsFullJobs> fullJobsPageInfo=recruitmentsService.getMyInviteFullJobs(userId,requestPage);
            if(fullJobsPageInfo==null){
                return new ResponseEntity(RecruitmentsMessage.NO_INVITE_RECRUITMENTENT,HttpStatus.OK);
            }
            return new ResponseEntity<PageInfo<RecruitmentsFullJobs>>(fullJobsPageInfo,HttpStatus.OK);
        }
    }

    /**
     * 获取我收到的兼职邀请
     * @param pageSize
     * @param q
     * @param pageNumber
     * @return
     */
    @Override
    public ResponseEntity<PageInfo<RecruitmentsPartJobs>> getMyInvitePartRecruitments(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                                                                      @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                                                                      @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber) {
        UserMine userMine=SecurityUtils.getCurrentUserLogin();
        if(userMine==null){
            return new ResponseEntity(RecruitmentsMessage.RECRUITMENTENTUNAUTHORIZED,HttpStatus.UNAUTHORIZED);//没有登录
        }else {
            Integer userId=userMine.getId();
            Sort order = SortUtils.assembleSort("-refreshTime");
            Pageable requestPage=new PageRequest(pageNumber,pageSize,order);
            PageInfo<RecruitmentsPartJobs> partJobsPageInfo=recruitsPartService.getMyInvitePartJobs(userId,requestPage);
            if(partJobsPageInfo==null){
                return new ResponseEntity(RecruitmentsMessage.NO_INVITE_RECRUITMENTENT,HttpStatus.OK);
            }
            return new ResponseEntity<PageInfo<RecruitmentsPartJobs>>(partJobsPageInfo,HttpStatus.OK);
        }
    }

    /**
     * 获得企业发布的兼职
     * @param pageSize
     * @param pageNumber
     * @return
     */
    public ResponseEntity<PageInfo<RecruitmentsPartJobs>> getEnterpPartRecruitments( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                                                                     @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber) {
        // do some magic!
        UserMine userMine=SecurityUtils.getCurrentUserLogin();
        if(userMine!=null){
        	int userId=userMine.getId();
            int userType=userMine.getUserType();
            //根据userId查询对应的企业ID
            if(userType==1){
                int enterpriseId=userEnterpriseService.findEnterpriseIdByUserId(userId);
                Pageable requestPage=new PageRequest(pageNumber,pageSize,Sort.Direction.DESC, "refreshTime");
                PageInfo<RecruitmentsPartJobs> enterpDetailPageInfo=recruitsPartService.getUserEnterprisePartJobs(enterpriseId,q,requestPage);
                if(enterpDetailPageInfo.getList()!=null&&enterpDetailPageInfo.getList().size()!=0){
                    return new ResponseEntity<PageInfo<RecruitmentsPartJobs>>(enterpDetailPageInfo,HttpStatus.OK);
                }else {
                    return new ResponseEntity(RecruitmentsMessage.RECRUITMENTENTNOT_FOUND,HttpStatus.NOT_FOUND);//没有
                }
            }else {
                return new ResponseEntity(RecruitmentsMessage.RECRUITMENTENT_FORBIDDENNOT_FOUND,HttpStatus.NOT_FOUND);
            }
        }else{
            return new ResponseEntity(RecruitmentsMessage.RECRUITMENTENTNOT_FOUND,HttpStatus.UNAUTHORIZED);//没有登录
        }
    }

    public ResponseEntity<PageInfo<RecruitmentsFullMini>> getFullRecruitments( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                                                               @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                                                               @ApiParam(value = "排序字段和方式 例如：/topics?sort=createTime",defaultValue = "createTime") @RequestParam(value = "sort", required = false) String sort,
            @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber) {
        // do some magic!
        Sort orders = null;
        if (StringUtils.isEmpty(sort)) {
            orders = SortUtils.assembleSort("-createTime");
        }else{
            orders = SortUtils.assembleSort(sort);
        }
        PageRequest pr = new PageRequest(pageNumber,pageSize, orders);
        PageInfo<RecruitmentsFullMini> pageInfo =   recruitmentsService.getFullRecruitments(q,pr);
        return new ResponseEntity<PageInfo<RecruitmentsFullMini>>(pageInfo,HttpStatus.OK);
    }

    public ResponseEntity<PageInfo<RecruitmentsPartMini>> getPartRecruitments( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                                                               @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                                                               @ApiParam(value = "排序字段和方式 例如：/topics?sort=createTime",defaultValue = "createTime") @RequestParam(value = "sort", required = false) String sort,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber) {
        Sort orders = null;
        if (StringUtils.isEmpty(sort)) {
            orders = SortUtils.assembleSort("-createTime");
        }else{
            orders = SortUtils.assembleSort(sort);
        }
        PageRequest pr = new PageRequest(pageNumber,pageSize,orders);
        PageInfo<RecruitmentsPartMini> pageInfo =   recruitsPartService.getPartRecruitments(q,pr);
        return new ResponseEntity<PageInfo<RecruitmentsPartMini>>(pageInfo,HttpStatus.OK);
    }

    /**
     * 获得个人投递的全职
     * @param pageSize
     * @param pageNumber
     * @return
     */
    public ResponseEntity<PageInfo<RecruitmentsFullJobs>> getPersonalFullRecruitments( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber) {
        // do some magic!
        UserMine userMine=SecurityUtils.getCurrentUserLogin();
        if(userMine!=null){
            int userId=userMine.getId();
            Pageable requestPage=new PageRequest(pageNumber,pageSize, Sort.Direction.DESC, "refreshTime");
            PageInfo<RecruitmentsFullJobs> recruitmentsFullMiniPageInfo=recruitmentsService.getMyFullJobs(userId,requestPage);
            if(recruitmentsFullMiniPageInfo!=null){
                return new ResponseEntity<PageInfo<RecruitmentsFullJobs>>(recruitmentsFullMiniPageInfo,HttpStatus.OK);
            }else {
                return new ResponseEntity(RecruitmentsMessage.RECRUITMENTUSERNOT_FOUND,HttpStatus.NOT_FOUND);//没有投递全职
            }
        }else {
            return new ResponseEntity(RecruitmentsMessage.RECRUITMENTUSERUNAUTHORIZED,HttpStatus.UNAUTHORIZED);//没有登录
        }
    }

    /**
     * 获得个人投递的兼职
     * @param pageSize
     * @param pageNumber
     * @return
     */
    public ResponseEntity<PageInfo<RecruitmentsPartJobs>> getPersonalPartRecruitments( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber) {
        // do some magic!
        UserMine userMine=SecurityUtils.getCurrentUserLogin();
        if(userMine!=null){
        	int userId=userMine.getId();
            Pageable requestPage=new PageRequest(pageNumber,pageSize, Sort.Direction.DESC, "refreshTime");
            PageInfo<RecruitmentsPartJobs> recruitmentsPartMiniPageInfo=recruitsPartService.getMyPartJobs(userId,requestPage);
            if(recruitmentsPartMiniPageInfo!=null){
                return new ResponseEntity<PageInfo<RecruitmentsPartJobs>>(recruitmentsPartMiniPageInfo,HttpStatus.OK);
            }else {
                return new ResponseEntity(RecruitmentsMessage.RECRUITMENTUSERNOT_FOUND,HttpStatus.NOT_FOUND);//没有投递兼职
            }
        }else {
            return new ResponseEntity(RecruitmentsMessage.RECRUITMENTUSERUNAUTHORIZED,HttpStatus.UNAUTHORIZED);//没有登录
        }
    }

    /**
     * 获得推荐全职
     * @param pageSize
     * @param pageNumber
     * @return
     */
    public ResponseEntity<PageInfo<RecommandsJob>> getRecommandsFullJob( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber) {
        // do some magic!
    	int userId=SecurityUtils.getCurrentUserLogin() == null ? -1: SecurityUtils.getCurrentUserLogin().getId();
    	/*int userType=SecurityUtils.getCurrentUserLogin().getUserType();
        if(userType!= UserTypeEnum.ORDINARYUSER.getValue()){//判断是否是普通用户
            return new ResponseEntity(RecruitmentsMessage.RECRUITMENTPERMISSIONSFAILS,HttpStatus.NOT_FOUND);
        }*/
        Map parm = new HashMap();
        parm.put("id",userId);
        RecommendBackResult rr =null;
        try {
            rr = (RecommendBackResult) restTemplate.getForEntity(recommendRemoteUrl.concat("/recommendWork?id={id}"), RecommendBackResult.class, parm).getBody();
        } catch (NullPointerException e) {
            return new ResponseEntity(RecruitmentsMessage.RECRUITMENTRECOMMENTGEDFAILS,HttpStatus.REQUEST_TIMEOUT);
        }
        if(rr.getResult() == 0 || rr.getData().size()==0){
            return new ResponseEntity(RecruitmentsMessage.RECRUITMENTRECOMMENTGEDNOT_FOUND,HttpStatus.NOT_FOUND);
        }
        //判断用户是否登录
//        if(SecurityUtils.getCurrentUserLogin()!=null){
            Pageable requestPage=new PageRequest(pageNumber,pageSize);
            PageInfo<RecommandsJob> pageInfo = recruitmentsService.getRecommendJobs(rr.getData(),requestPage);
            return new ResponseEntity<PageInfo<RecommandsJob>>(pageInfo,HttpStatus.OK);
//        }else{
//            return new ResponseEntity(RecruitmentsMessage.RECRUITMENTRECOMMENTGEDUNAUTHORIZED,HttpStatus.UNAUTHORIZED);//用户没有登录
//        }
    }

    /**
     * 获得推荐兼职
     * @param pageSize
     * @param pageNumber
     * @return
     */
    public ResponseEntity<PageInfo<RecommandsJob>> getRecommandsPartJob( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber) {
        // do some magic!
    	int userId=SecurityUtils.getCurrentUserLogin() == null ? -1: SecurityUtils.getCurrentUserLogin().getId();
        
        /*int userType=SecurityUtils.getCurrentUserLogin().getUserType();
        if(userType!= UserTypeEnum.ORDINARYUSER.getValue()){//判断是否是普通用户
            return new ResponseEntity(RecruitmentsMessage.RECRUITMENTPERMISSIONSFAILS,HttpStatus.NOT_FOUND);
        }*/
        Map parm = new HashMap();
        parm.put("id",userId);
        RecommendBackResult rr = null;
        try {
            rr = (RecommendBackResult) restTemplate.getForEntity(recommendRemoteUrl.concat("/recommendPartTimeCompany?id={id}"), RecommendBackResult.class, parm).getBody();
        } catch (NullPointerException e) {
            return new ResponseEntity(RecruitmentsMessage.RECRUITMENTRECOMMENTGEDFAILS,HttpStatus.REQUEST_TIMEOUT);
        }
        if(rr.getResult() == 0 || rr.getData().size()==0){
            return new ResponseEntity(RecruitmentsMessage.RECRUITMENTRECOMMENTGEDNOT_FOUND,HttpStatus.NOT_FOUND);
        }
        //TODO 判断用户是否登录  --duke 用户可以不登录也有推荐职位
       // if(SecurityUtils.getCurrentUserLogin()!=null){
            Pageable requestPage=new PageRequest(pageNumber,pageSize);
            PageInfo<RecommandsJob> pageInfo = recruitsPartService.getRecommendJobs(rr.getData(),requestPage);
            return new ResponseEntity<PageInfo<RecommandsJob>>(pageInfo,HttpStatus.OK);
       // }else{
         //   return new ResponseEntity(RecruitmentsMessage.RECRUITMENTRECOMMENTGEDUNAUTHORIZED,HttpStatus.UNAUTHORIZED);//用户没有登录
        //}
    }


    /**
     * 获取职位详细信息
     * @param id
     * @param type
     * @return
     */
    public ResponseEntity<RecruitmentsDetail> getRecruitmentsDetail(@ApiParam(value = "职位id",required=true ) @PathVariable("id") Integer id,
         @NotNull @ApiParam(value = "类型：fulljob全职，partjob兼职 如/recruitments/{id}?type=fulljob", required = true, allowableValues = "FULLJOB, PARTJOB") @RequestParam(value = "type", required = true) String type) {
        // do some magic!
        if(type!=null&&type.equalsIgnoreCase("fulljob")){
            RecruitmentsDetail rd = recruitmentsService.getFullRecruitmentById(id);
            if(rd!=null){
                return new ResponseEntity<RecruitmentsDetail>(rd,HttpStatus.OK);
            }else {
                return new ResponseEntity<RecruitmentsDetail>(HttpStatus.NOT_FOUND);
            }
        }else if(type!=null&&type.equalsIgnoreCase("partjob")){
            RecruitmentsDetail rd = recruitsPartService.getPartRecruitmentById(id);
            if(rd!=null){
                return new ResponseEntity<RecruitmentsDetail>(rd,HttpStatus.OK);
            }else {
                return new ResponseEntity<RecruitmentsDetail>(HttpStatus.NOT_FOUND);
            }
        }else {
            return new ResponseEntity<RecruitmentsDetail>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<HttpMessage> inviteUserRecruitment(@ApiParam(value = "职位id",required=true ) @PathVariable("id") Integer id,
        @ApiParam(value = "用户id",required=true ) @PathVariable("userID") Integer userID,
         @NotNull @ApiParam(value = "类型：fulljob全职，partjob兼职 如/recruitments/{id}/invite/{userID}?type=fulljob", required = true, allowableValues = "FULLJOB, PARTJOB") @RequestParam(value = "type", required = true) String type) {
        // do some magic!
        if(type.equalsIgnoreCase("fulljob")&&!MessageUtils.isMessageExist(userID,id,MessageEventTypeEnum.INVITATION,MessageMsgTypeEnum.InvitationFullTimeJob)){
            MessageUtils.createMessage(userID,id,MessageEventTypeEnum.INVITATION,MessageMsgTypeEnum.InvitationFullTimeJob);
            userpersonalService.updateInvitedNum(userID);
            return new ResponseEntity<HttpMessage>(HttpStatus.OK);
        }else if (type.equalsIgnoreCase("partjob")&&!MessageUtils.isMessageExist(userID,id,MessageEventTypeEnum.INVITATION,MessageMsgTypeEnum.InvitationPartTimeJob)){
            MessageUtils.createMessage(userID,id,MessageEventTypeEnum.INVITATION,MessageMsgTypeEnum.InvitationPartTimeJob);
            userpersonalService.updateInvitedNum(userID);
            return new ResponseEntity<HttpMessage>(HttpStatus.OK);
        }else {
            return new ResponseEntity<HttpMessage>(RecruitmentsMessage.RECRUITMENINVATEFAILS, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<HttpMessage> createFullRecruitment(@ApiParam(value = "教育经历" ,required=true ) @RequestBody RecruitmentsFullInfo recruitments) {
        // do some magic!
        Integer userType = SecurityUtils.getCurrentUserLogin().getUserType();
        if(userType==1){
            recruitmentsService.createFullRecruitment(recruitments);
            return new ResponseEntity<HttpMessage>(HttpStatus.OK);
        }else{
            return new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<HttpMessage> createPartRecruitment(@ApiParam(value = "教育经历" ,required=true ) @RequestBody RecruitmentsPartInfo recruitments) {
        // do some magic!
        Integer userType = SecurityUtils.getCurrentUserLogin().getUserType();
        if(userType==1) {
            recruitsPartService.createPartRecruitment(recruitments);
            return new ResponseEntity<HttpMessage>(HttpStatus.OK);
        }else{
            return new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST);
        }
    }

}
