package com.cdyoue.oddJobs.api.lgfc;



import java.util.Date;
import java.util.List;

import com.cdyoue.oddJobs.dto.common.message.TopicMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.common.message.AuthenticationMessage;
import com.cdyoue.oddJobs.dto.lgfc.QuestionRequest;
import com.cdyoue.oddJobs.dto.lgfc.Topic;
import com.cdyoue.oddJobs.dto.lgfc.TopicBase;
import com.cdyoue.oddJobs.dto.lgfc.TopicMini;
import com.cdyoue.oddJobs.dto.lgfc.TopicRequest;
import com.cdyoue.oddJobs.en.MessageEventTypeEnum;
import com.cdyoue.oddJobs.en.MessageMsgTypeEnum;
import com.cdyoue.oddJobs.exception.UnAuthenticationMessageException;
import com.cdyoue.oddJobs.service.LgTopicService;
import com.cdyoue.oddJobs.service.QuestionService;
import com.cdyoue.oddJobs.utils.MessageUtils;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.common.SortUtils;
import com.cdyoue.spring.page.PageInfo;

import io.swagger.annotations.ApiParam;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-30T14:30:56.295Z")

@Controller
public class TopicsApiController implements TopicsApi {
    @Autowired
    private LgTopicService lgTopicService;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${default.recommend.remoteUrl}")
    private String recommendRemoteUrl;

@Autowired
private QuestionService questionService;

    public ResponseEntity<HttpMessage> createQuestion4Topic(@ApiParam(value = "问题id",required=true ) @PathVariable("topicid") Integer topicid,
        @ApiParam(value = "问题实体信息" ,required=true ) @RequestBody QuestionRequest question) {
        // do some magic!
        Integer id=questionService.createQuestion4Topic(topicid,question);
        return id == null ? new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST) : new ResponseEntity<HttpMessage>(HttpStatus.CREATED);
        //return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    public ResponseEntity<HttpMessage> createTopic(@ApiParam(value = "话题实体信息" ,required=true ) @RequestBody TopicRequest topicRequest) {
        // do some magic!
        Integer id = lgTopicService.save(topicRequest);
        // do some magic!
        return id == null ? new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST) : new ResponseEntity<HttpMessage>(HttpStatus.CREATED);
    }

    public ResponseEntity<HttpMessage> deleteTopicById(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id) {
        // do some magic!
        lgTopicService.deleteTopicById(id);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    public ResponseEntity<HttpMessage> followTopicById(@ApiParam(value = "话题id",required=true ) @PathVariable("id") Integer id) {
        // do some magic!
        //获取当前用户ID,msgid是指 话题 类型，eid是指 关注 类型。
        Integer uid = SecurityUtils.getCurrentUserLogin().getId();
        if(id!=null){
            if(!MessageUtils.isMessageExist(id,MessageEventTypeEnum.FOCUS, MessageMsgTypeEnum.FocusTopic)){
                lgTopicService.followTopicById(id);
                MessageUtils.createMessage(id,MessageEventTypeEnum.FOCUS, MessageMsgTypeEnum.FocusTopic);
                return new ResponseEntity<HttpMessage>(HttpStatus.OK);
            }else {
                return new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<TopicMini>> getHotTopics() {
        // do some magic!
        List<TopicMini> pageInfo =   lgTopicService.getHotTopics();
        if(pageInfo.size()!=0){
            return new ResponseEntity<List<TopicMini>>(pageInfo,HttpStatus.OK);
        }else {
            return new ResponseEntity(TopicMessage.NO_TOPIC,HttpStatus.OK);
        }
    }

    public ResponseEntity<PageInfo<Topic>> getMyTopics( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
         @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
         @ApiParam(value = "排序字段和方式 例如：/topics?sort=createTime",defaultValue = "-createTime",allowableValues = "follow,-createTime") @RequestParam(value = "sort", required = false) String sort) {
        // do some magic!
    	if (SecurityUtils.getCurrentUserLogin() == null) {
			throw new UnAuthenticationMessageException(AuthenticationMessage.USERUNLOGIN);
		}
        Sort orders = SortUtils.assembleSort(sort);
        //获取当前用户ID,msgid是指 话题 类型，eid是指 关注 类型。
        Integer uid = SecurityUtils.getCurrentUserLogin().getId();
        int msgid = 3;
        int eid = 3;

        PageRequest pr = new PageRequest(pageNumber,pageSize,orders);
        PageInfo<Topic> pageInfo =   lgTopicService.getMyTopics(q,pr,uid,msgid,eid);
        return new ResponseEntity<PageInfo<Topic>>(pageInfo,HttpStatus.OK);
    }

    public ResponseEntity<TopicMini> getTopicById(@ApiParam(value = "话题id",required=true ) @PathVariable("id") Integer id) {
        // do some magic!
        TopicMini detail = lgTopicService.getTopicDetailById(id);
        return new ResponseEntity<TopicMini>(detail,HttpStatus.OK);
    }

    public ResponseEntity<PageInfo<Topic>> getTopics( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
         @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
         @ApiParam(value = "排序字段和方式 例如：/topics?sort=createTime，默认按照创建时间排序。follow = 关注度最高的话题", allowableValues = "follow, createTime") @RequestParam(value = "sort", required = false) String sort) {
        // do some magic!
        PageRequest pr;
        if (sort != null && !sort.trim().equals("")) {
            Sort orders = SortUtils.assembleSort(sort);
            pr = new PageRequest(pageNumber,pageSize,orders);
        } else {
            pr = new PageRequest(pageNumber, pageSize);
        }
        PageInfo<Topic> pageInfo =   lgTopicService.getMyReqiures(q,pr);
        return new ResponseEntity<PageInfo<Topic>>(pageInfo,HttpStatus.OK);
    }

    public ResponseEntity<HttpMessage> unFollowTopicById(@ApiParam(value = "话题id",required=true ) @PathVariable("id") Integer id) {
        // do some magic!
        //获取当前用户ID,msgid是指 话题 类型，eid是指 关注 类型。
        Integer uid = SecurityUtils.getCurrentUserLogin().getId();
        int msgtype = 3;
        int etype = 3;
        Date creatTime = new Date();
        if(id!=null){
            if(MessageUtils.isMessageExist(id,MessageEventTypeEnum.FOCUS, MessageMsgTypeEnum.FocusTopic)) {
                lgTopicService.unFollowTopicById(id);
//            lgTopicService.delMark(uid,msgtype,etype,id);
                MessageUtils.cancelEventAction(id, MessageEventTypeEnum.FOCUS, MessageMsgTypeEnum.FocusTopic);
                return new ResponseEntity<HttpMessage>(HttpStatus.OK);
            }
            return new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<HttpMessage> updateTopic(@ApiParam(value = "",required=true ) @PathVariable("id") String id,
        @ApiParam(value = "话题对象" ,required=true ) @RequestBody TopicRequest topicRequest) {
        // do some magic!

        Integer tid = Integer.valueOf(id);
        lgTopicService.updateTopic(tid,topicRequest);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

}
