package com.cdyoue.oddJobs.api.ggfw;

import com.cdyoue.oddJobs.constant.TrackCategories;
import com.cdyoue.oddJobs.dto.ActivityBanner;
import com.cdyoue.oddJobs.dto.Reason;
import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.common.message.ActivityMessage;
import com.cdyoue.oddJobs.dto.common.message.AuthenticationMessage;
import com.cdyoue.oddJobs.dto.ggfw.ActivityDetail;
import com.cdyoue.oddJobs.dto.ggfw.ActivityRequest;
import com.cdyoue.oddJobs.dto.ggfw.ActivitySummary;
import com.cdyoue.oddJobs.dto.zscq.IntellectualTop;
import com.cdyoue.oddJobs.en.LoginTypeEnum;
import com.cdyoue.oddJobs.en.MessageEventTypeEnum;
import com.cdyoue.oddJobs.en.MessageMsgTypeEnum;
import com.cdyoue.oddJobs.event.EventPiwikTracking;
import com.cdyoue.oddJobs.exception.UnAuthenticationMessageException;
import com.cdyoue.oddJobs.service.ActivityService;
import com.cdyoue.oddJobs.utils.MessageUtils;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.common.SortUtils;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-10T12:31:39.195Z")

@Controller
public class ActivitiesApiController implements ActivitiesApi {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private EventPiwikTracking eventPiwikTracking;

    @Override
    public ResponseEntity<HttpMessage> activityTop(@ApiParam(value = "", required = true) @RequestBody IntellectualTop top) {
        activityService.activityTop(top);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> removeActivityTop(@ApiParam(value = "", required = true) @PathVariable("id") Integer id) {
        activityService.removeActivityTop(id);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ActivityBanner>> getActivityBanners() {
        List<ActivityBanner> banners = activityService.getActivityBanners();
        return new ResponseEntity<List<ActivityBanner>>(banners,HttpStatus.OK);
    }

    public ResponseEntity<HttpMessage> approveActivity(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id, @ApiParam(value = "原因" ,required=false ) @RequestBody Reason reason) {

        Integer role =  SecurityUtils.getCurrentUserLogin().getRole();

        if(activityService.getActivityCheck(id)==false){
            return new ResponseEntity<HttpMessage>(HttpStatus.NOT_FOUND);
        }else if(activityService.getStatus(id).intValue()!=0){
            return new ResponseEntity<HttpMessage>(HttpStatus.FORBIDDEN);
        }else if(role!=2){
            return new ResponseEntity<HttpMessage>(HttpStatus.FORBIDDEN);
        }else{
            activityService.approveActivity(id,reason);
            return new ResponseEntity<HttpMessage>(HttpStatus.OK);
        }
    }

    //生成一条新的活动
    public ResponseEntity<HttpMessage> createActivity(@ApiParam(value = "活动实体信息" ,required=true ) @RequestBody ActivityRequest activity) {
        ActivityRequest activityDTO = new ActivityRequest();
        BeanUtils.copyProperties(activity,activityDTO);
        String id = activityService.createActivity(activityDTO);
        UserMine um = SecurityUtils.getCurrentUserLogin();
        if(SecurityUtils.getCurrentUserLogin().getRole() == 2){
            eventPiwikTracking.doTracking(TrackCategories.PUBLISH_ACT,um.getName());
        }
        return id == null ? new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST) : new ResponseEntity<HttpMessage>(HttpStatus.CREATED);
    }

    //关注一条活动 By ID
    public ResponseEntity<HttpMessage> followActivityById(@ApiParam(value = "活动id",required=true ) @PathVariable("id") Integer id) {
        //如果该用户已关注该活动，返回一个错误请求(403),如果活动不存在，返回未查找到(404)。
        if (MessageUtils.isMessageExist(id, MessageEventTypeEnum.FOCUS, MessageMsgTypeEnum.FoucsActivity)){
            return new ResponseEntity<HttpMessage>(HttpStatus.FORBIDDEN);
        }else if(activityService.getActivityCheck(id)!=true){
            return new ResponseEntity<HttpMessage>(HttpStatus.NOT_FOUND);
        }else {
            activityService.focusActivity(id);
            return new ResponseEntity<HttpMessage>(HttpStatus.OK);
        }
    }

    //获取所有的活动
    public ResponseEntity<PageInfo>  getActivities( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                                    @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
                                                    @ApiParam(value = "排序字段和方式 例如：/sales?sort=publishTime，默认按照发布时间排序。publishTime = 发布时间最新",defaultValue = "-publishTime") @RequestParam(value = "sort", required = false) String sort,
                                                    @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                                    @ApiParam(value = "审核状态") @RequestParam(value = "s", required = false) String s) {
        //Hardcode sort
        Sort order = SortUtils.assembleSort(sort);
        PageRequest pr = new PageRequest(pageNumber,pageSize,order);
        PageInfo<ActivitySummary> pageInfo = activityService.findList(pr,q,s);
        return new ResponseEntity<com.cdyoue.spring.page.PageInfo>(pageInfo,HttpStatus.OK);
    }

    //Fetch one record of activity according to activity id
    public ResponseEntity<ActivityDetail> getActivityById(@ApiParam(value = "活动id",required=true ) @PathVariable("id") Integer id) {
        if(activityService.getActivityCheck(id)){
            ActivityDetail activityDTO = new ActivityDetail();
            activityDTO = activityService.getActivityById(id);
            return new ResponseEntity<ActivityDetail>(activityDTO,HttpStatus.OK);
        }else {
            return new ResponseEntity<ActivityDetail>(HttpStatus.NOT_FOUND);
        }
    }

    //Get all activities published by current user
    public ResponseEntity<PageInfo> getMyActivities( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
         @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q) {
        //Hardcode sort
        if (SecurityUtils.getCurrentUserLogin() == null) {
            throw new UnAuthenticationMessageException(AuthenticationMessage.USERUNLOGIN);
        }
        Sort order = SortUtils.assembleSort("-createTime");
        PageRequest pr = new PageRequest(pageNumber,pageSize,order);
        PageInfo<ActivitySummary> pageInfo = activityService.findMyList(pr,q);
        return new ResponseEntity<com.cdyoue.spring.page.PageInfo>(pageInfo,HttpStatus.OK);
    }

    //Get all activities focused by current user
    public ResponseEntity getMyFollowActivities( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
         @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q) {
        if (SecurityUtils.getCurrentUserLogin() == null) {
            throw new UnAuthenticationMessageException(AuthenticationMessage.USERUNLOGIN);
        }
        Sort order = SortUtils.assembleSort("-createTime");
        PageRequest pr = new PageRequest(pageNumber,pageSize,order);
        List<Integer> focusActivityIds = MessageUtils.getMessageEventIds(MessageEventTypeEnum.FOCUS, MessageMsgTypeEnum.FoucsActivity);
        if(focusActivityIds==null||focusActivityIds.size()==0)
            return new ResponseEntity<HttpMessage>(ActivityMessage.MYFOLACTNOTFOUND, HttpStatus.NOT_FOUND);
        if(q==null) q="";
        PageInfo<ActivitySummary> pageInfo = activityService.findMyCollectionsListByStrKey(focusActivityIds,q,pr);
        if(pageInfo.getList()!=null&&pageInfo.getList().size()!=0){
            return new ResponseEntity<com.cdyoue.spring.page.PageInfo>(pageInfo,HttpStatus.OK);
        }else{
            return new ResponseEntity<HttpMessage>(ActivityMessage.MYFOLACTNOTFOUND,HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpMessage> rejectActivity(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,@ApiParam(value = "原因" ,required=false ) @RequestBody Reason reason) {

        Integer role =  SecurityUtils.getCurrentUserLogin().getRole();

        if(activityService.getActivityCheck(id)==false){
            return new ResponseEntity<HttpMessage>(HttpStatus.NOT_FOUND);
        }else if(activityService.getStatus(id).intValue()!=0){
            return new ResponseEntity<HttpMessage>(HttpStatus.FORBIDDEN);
        }else if(role!=2){
            return new ResponseEntity<HttpMessage>(HttpStatus.FORBIDDEN);
        }else{
            activityService.rejectActivity(id,reason);
            return new ResponseEntity<HttpMessage>(HttpStatus.OK);
        }
    }

    //Unfocus activity by ID
    public ResponseEntity<HttpMessage> unFollowActivityById(@ApiParam(value = "活动id",required=true ) @PathVariable("id") Integer id) {
        if(MessageUtils.isMessageExist(id, MessageEventTypeEnum.FOCUS, MessageMsgTypeEnum.FoucsActivity)){
            activityService.cancelfocusActivity(id);
            return new ResponseEntity<HttpMessage>(HttpStatus.OK);
        }else {
            return new ResponseEntity<HttpMessage>(HttpStatus.NOT_FOUND);
        }
    }

    //Update activity
    public ResponseEntity<HttpMessage> updateActivity(@ApiParam(value = "",required=true ) @PathVariable("id") String paramID,
        @ApiParam(value = "活动对象信息" ,required=true ) @RequestBody ActivityRequest activity) {

        Integer id = Integer.valueOf(paramID);
        if (activityService.getActivityCheck(id) == false) {
            return new ResponseEntity<HttpMessage>(HttpStatus.NOT_FOUND);
        }

        Integer uId = SecurityUtils.getCurrentUserLogin().getId();
        Integer role =  SecurityUtils.getCurrentUserLogin().getRole();
        Integer userType = SecurityUtils.getCurrentUserLogin().getUserType();
        Integer status = activityService.getStatus(id);

        /*if (uId != activityService.getCreatorIdBy(id) && 2!=role) {
            return new ResponseEntity<HttpMessage>(HttpStatus.FORBIDDEN);
        } else if (userType !=1) {
            return new ResponseEntity<HttpMessage>(HttpStatus.FORBIDDEN);
        }

        activityService.updateActivity(id, activity);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);*/
        if (role == 2) {
            activityService.updateActivity(id, activity);
            return new ResponseEntity<HttpMessage>(HttpStatus.OK);
        } else {
            return new ResponseEntity<HttpMessage>(HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public ResponseEntity<HttpMessage> deleteActivity(@ApiParam(value = "", required = true) @PathVariable("id") String paramID) {

        Integer id = Integer.valueOf(paramID);
        if (activityService.getActivityCheck(id) == false) {
            return new ResponseEntity<HttpMessage>(ActivityMessage.DELETEACTFAIL, HttpStatus.BAD_REQUEST);
        }

        Integer uId = SecurityUtils.getCurrentUserLogin().getId();
        Integer role =  SecurityUtils.getCurrentUserLogin().getRole();
        Integer status = activityService.getStatus(id);

        /*if (uId != activityService.getCreatorIdBy(id) && 2!=role) {
            return new ResponseEntity<HttpMessage>(HttpStatus.FORBIDDEN);
        } else if (status !=2 && 2!=role) {
            return new ResponseEntity<HttpMessage>(HttpStatus.FORBIDDEN);
            return new ResponseEntity<HttpMessage>(HttpStatus.FORBIDDEN);
        }*/
        if (uId.equals(activityService.getCreatorIdBy(id)) || role == 2) {
            activityService.deleteActivity(id);
            return new ResponseEntity<HttpMessage>(HttpStatus.OK);
        } else {
            return new ResponseEntity<HttpMessage>(HttpStatus.FORBIDDEN);
        }


    }

    @Override
    public ResponseEntity<HttpMessage> deleteAllActivities(@ApiParam(value = "", required = true) @RequestParam("ids") Integer[] ids) {
        activityService.deleteAllActivities(ids);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

}
