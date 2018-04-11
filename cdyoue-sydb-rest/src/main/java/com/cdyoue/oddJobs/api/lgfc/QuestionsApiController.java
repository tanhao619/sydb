package com.cdyoue.oddJobs.api.lgfc;


import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.common.message.AuthenticationMessage;
import com.cdyoue.oddJobs.dto.common.message.QuestionMessage;
import com.cdyoue.oddJobs.dto.lgfc.*;
import com.cdyoue.oddJobs.en.MessageEventTypeEnum;
import com.cdyoue.oddJobs.en.MessageMsgTypeEnum;
import com.cdyoue.oddJobs.entity.lgsq.PortalTopicEntity;
import com.cdyoue.oddJobs.entity.lgsq.QuestionEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.exception.UnAuthenticationMessageException;
import com.cdyoue.oddJobs.mapper.QuestionMapper;
import com.cdyoue.oddJobs.service.*;
import com.cdyoue.oddJobs.utils.MessageUtils;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//import com.cdyoue.oddJobs.service.ReplyService;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-30T14:30:56.295Z")

@Controller
public class QuestionsApiController implements QuestionsApi {

    @Autowired
    private ReplyService replyService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserService userService;
    @Autowired
    private LgTopicService lgTopicService;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${default.recommend.remoteUrl}")
    private String recommendRemoteUrl;
    @Autowired
    private UserpersonalService userpersonalService;

    /**
     * 创建问题
     *
     * @param question
     * @return
     */
    public ResponseEntity<HttpMessage> createQuestion(@ApiParam(value = "问题实体信息", required = true) @RequestBody QuestionRequest question) {
        // do some magic!
        Integer id = questionService.createQuestion(question);
        return id == null ? new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST) : new ResponseEntity<HttpMessage>(HttpStatus.CREATED);
    }

    public ResponseEntity<HttpMessage> createReply4Question(@ApiParam(value = "问题id", required = true) @PathVariable("questionid") Integer questionid,
                                                            @ApiParam(value = "回复内容信息", required = true) @RequestBody Reply4Question reply4Question) {
        // do some magic!
        // 先判断问题是否存在（404）
        /*if (!questionService.isExist(questionid)) {
            return new ResponseEntity<HttpMessage>(ReplyMessage.REPLYCREATEFAILD404, HttpStatus.NOT_FOUND);
        }*/
        // 如果存在，添加回复（201）
        replyService.createReply4Question(questionid, reply4Question.getReply());
        return new ResponseEntity<HttpMessage>(HttpStatus.CREATED);
    }

    /**
     * 获取我关注的问题
     *
     * @param pageSize
     * @param pageNumber
     * @param q
     * @return
     */
    public ResponseEntity<PageInfo> getMyFolQuestions(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                      @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                      @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q) {
        //判断用户是否登录
        UserMine userEntity = SecurityUtils.getCurrentUserLogin();
        if (userEntity != null) {
            Pageable pr = new PageRequest(pageNumber, pageSize, Sort.Direction.DESC, "createTime");
            if (q == null || q.isEmpty()) {
                //不带关键字查询
                PageInfo<QuestionBase> pageInfo = questionService.findMyCollectionsList(pr);
                if (pageInfo.getList() != null && pageInfo.getList().size() != 0) {
                    return new ResponseEntity<com.cdyoue.spring.page.PageInfo>(pageInfo, HttpStatus.OK);
                } else {
                    return new ResponseEntity(QuestionMessage.NO_QUESTION, HttpStatus.OK);
                }
            } else {
                //带关键字查询
                // 被关注问题的ID
                List<Integer> focusQuestionIds = MessageUtils.getMessageEventIds(MessageEventTypeEnum.FOCUS, MessageMsgTypeEnum.FocusQuestions);
                PageInfo<QuestionBase> pageInfo = questionService.findMyCollectionsListByStrKey(focusQuestionIds, q, pr);
                if (pageInfo.getList() != null && pageInfo.getList().size() != 0) {
                    return new ResponseEntity<com.cdyoue.spring.page.PageInfo>(pageInfo, HttpStatus.OK);
                } else {
                    return new ResponseEntity(QuestionMessage.NO_QUESTION, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     * 获取我回答的问题  完成
     *
     * @param pageSize
     * @param pageNumber
     * @param q
     * @return
     */
    public ResponseEntity<PageInfo> getMyQuestionReplies(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                         @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q) {
        // do some magic!
        UserMine user = SecurityUtils.getCurrentUserLogin();
        //判断用户是否登录
        if (user != null) {
            int userId = user.getId();
            Pageable requestPage = new PageRequest(pageNumber, pageSize);
            if (q == null || q.isEmpty()) {
                Page<QuestionEntity> page = questionService.findReplyQuestionByUserId(userId, requestPage);
                List<QuestionSummary> questionSummary = page.getContent().stream().map(pre -> questionMapper.questionEntityToQuestionSummary(pre)).collect(Collectors.toList());
                PageInfo pageInfo = new PageInfo<QuestionSummary>(new PageImpl(questionSummary, requestPage, page.getTotalElements()));
                return new ResponseEntity<PageInfo>(pageInfo, HttpStatus.OK);
            } else {
                Page<QuestionEntity> page = questionService.findReplyQuestionByUserIdStrKey(userId, q, requestPage);
                List<QuestionSummary> questionSummary = page.getContent().stream().map(pre -> questionMapper.questionEntityToQuestionSummary(pre)).collect(Collectors.toList());
                PageInfo pageInfo = new PageInfo<QuestionSummary>(new PageImpl(questionSummary, requestPage, page.getTotalElements()));
                return new ResponseEntity<PageInfo>(pageInfo, HttpStatus.OK);
            }
        } else {
            throw new UnAuthenticationMessageException(AuthenticationMessage.USERUNLOGIN);//用户没有登录
        }
    }

    /**
     * 获取我收到的问答邀请列表
     *
     * @param pageSize
     * @param pageNumber
     * @param q
     * @return
     */
    @Override
    public ResponseEntity<PageInfo> getMyQuestionInvite(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                        @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                        @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q) {
        UserMine userMine = SecurityUtils.getCurrentUserLogin();
        if (userMine == null) {
            throw new UnAuthenticationMessageException(AuthenticationMessage.USERUNLOGIN);//用户没有登录
        } else {
            Integer userId = userMine.getId();
            Pageable requestPage = new PageRequest(pageNumber, pageSize, Sort.Direction.DESC, "createTime");
            PageInfo<QuestionSummary> pageInfo = questionService.findMyInviteQuestion(userId, q, requestPage);
            if (pageInfo == null) {
                return new ResponseEntity(QuestionMessage.NO_QUESTION, HttpStatus.OK);
            }
            return new ResponseEntity<PageInfo>(pageInfo, HttpStatus.OK);
        }
    }

    /**
     * 获取我发布的问题 完成
     *
     * @param pageSize
     * @param pageNumber
     * @return
     */
    public ResponseEntity<PageInfo> getMyQuestions(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                   @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                   @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q) {
        // do some magic!
        //先判断我自己是否登录
        if (SecurityUtils.getCurrentUserLogin() != null) {
            Pageable requestPage = new PageRequest(pageNumber, pageSize, Sort.Direction.DESC, "CreateTime");
            PageInfo<QuestionSummary> pageInfo = questionService.findCreateQuestionByUserId(SecurityUtils.getCurrentUserLogin().getId(), q, requestPage);
            if (pageInfo != null) {
                return new ResponseEntity<PageInfo>(pageInfo, HttpStatus.OK);
            } else {
                return new ResponseEntity(QuestionMessage.NO_QUESTION, HttpStatus.OK);
            }
        } else {
            throw new UnAuthenticationMessageException(AuthenticationMessage.USERUNLOGIN);//用户没有登录
        }
    }

    /**
     * 获取问题分页列表 完成
     *
     * @param pageSize
     * @param pageNumber
     * @param q
     * @return 问题分页列表
     */
    public ResponseEntity<PageInfo> getQuestions(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                 @ApiParam(value = "页码。默认第0页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                 @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q) {
        // do some magic!
        Pageable requestPage = new PageRequest(pageNumber, pageSize, Sort.Direction.DESC, "CreateTime");
        PageInfo<QuestionSummary> pageInfo = questionService.findByKeyWord(q, requestPage);
        if (pageInfo == null || pageInfo.getList() == null || pageInfo.getList().size() == 0) {
            return new ResponseEntity(QuestionMessage.NO_QUESTION, HttpStatus.OK);
        } else {
            return new ResponseEntity<com.cdyoue.spring.page.PageInfo>(pageInfo, HttpStatus.OK);
        }
    }

    /**
     * 获取某个用户发布的问题 完成
     *
     * @param userid
     * @param pageSize
     * @param pageNumber
     * @param q
     * @return
     */
    public ResponseEntity<PageInfo> getQuestionsByUserId(@ApiParam(value = "用户id", required = true) @PathVariable("userid") Integer userid,
                                                         @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                         @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q) {
        // do some magic!
        //先查询该用户是否存在
        UserEntity user = userService.findOne(userid);
        if (user != null) {
            Pageable requestPage = new PageRequest(pageNumber, pageSize, Sort.Direction.DESC, "CreateTime");
            PageInfo<QuestionSummary> pageInfo = questionService.findByKeyWordUserId(userid, q, requestPage);
            return new ResponseEntity<PageInfo>(pageInfo, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * 邀请某用户回答问题
     *
     * @param userid
     * @param id
     * @return
     */
    public ResponseEntity<HttpMessage> inviteAnsQuestion(@ApiParam(value = "用户id", required = true) @PathVariable("userid") Integer userid,
                                                         @ApiParam(value = "问题id", required = true) @PathVariable("id") Integer id) {
        // do some magic!
        // 判断自己登录否（401）
        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();
        if (currentUserLogin == null) {
            return new ResponseEntity<HttpMessage>(HttpStatus.UNAUTHORIZED);
        }
        // 判断是否邀请自己（400）!!!可以邀请自己回答问题
//        if (currentUserLogin.getId() == userid) {
//            return new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST);
//        }
        // 判断邀请的用户存在否，判断问题存在否（404）
        UserEntity userEntity = userService.findOne(userid);
        QuestionEntity questionEntity = questionService.findOneQuestion(id);
        if (questionEntity == null) {
            return new ResponseEntity<HttpMessage>(QuestionMessage.NO_QUESTION, HttpStatus.NOT_FOUND);
        }
        if (userEntity == null) {
            return new ResponseEntity<HttpMessage>(QuestionMessage.NO_User, HttpStatus.NOT_FOUND);
        }
        //判断是否已经邀请该用户回答该问题
        Boolean is = MessageUtils.isMessageExist(userid, id, MessageEventTypeEnum.INVITATION, MessageMsgTypeEnum.InvitationAnswerQuestions);
        if (is == true) {
            //如果已经邀请则是错误的请求
            return new ResponseEntity<HttpMessage>(QuestionMessage.REINVITATION, HttpStatus.BAD_REQUEST);
        }
        // 发出邀请
        MessageUtils.createMessage(userid, id, MessageEventTypeEnum.INVITATION, MessageMsgTypeEnum.InvitationAnswerQuestions);
        userpersonalService.updateInvitedNum(userid);
        return new ResponseEntity<HttpMessage>(HttpStatus.CREATED);
    }

    /**
     * 删除问题 完成
     *
     * @param id
     * @return
     */
    public ResponseEntity<HttpMessage> deleteQuestionById(@ApiParam(value = "", required = true) @PathVariable("id") Integer id) {
        // do some magic!
        //先判断问题是否存在

        questionService.deleteQuestion(id);
        return new ResponseEntity<HttpMessage>(HttpStatus.CREATED);


    }

    /**
     * 关注问题 完成
     *
     * @param id
     * @return
     */
    public ResponseEntity<HttpMessage> followQuesionById(@ApiParam(value = "问题id", required = true) @PathVariable("id") Integer id) {
        // do some magic!
        Boolean isFollow = MessageUtils.isMessageExist(id, MessageEventTypeEnum.FOCUS, MessageMsgTypeEnum.FocusQuestions);
        if (isFollow) {
            return new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST);
        }
        questionService.followQuestion(id);
        return new ResponseEntity<HttpMessage>(HttpStatus.CREATED);
    }

    /***
     * 获取问题的详情
     * @param id
     * @return
     */
    public ResponseEntity<QuestionDetails> getQuestionById(@ApiParam(value = "问题id", required = true) @PathVariable("id") Integer id) {
        // do some magic!
        //先查询是否有该问题
        QuestionDetails questionDetails = questionService.selectQuestionDetails(id);
        if (questionDetails == null) {
            return new ResponseEntity(QuestionMessage.NO_QUESTION, HttpStatus.OK);
        }
        return new ResponseEntity<QuestionDetails>(questionDetails, HttpStatus.OK);
    }

    /**
     * 获取推荐问题列表 完成
     *
     * @param pageSize
     * @param pageNumber
     * @param q
     * @return
     */
    public ResponseEntity<PageInfo> getRecQuestions(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                    @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                    @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q) {
        // do some magic!
        int userId = SecurityUtils.getCurrentUserLogin() == null ? -1 : SecurityUtils.getCurrentUserLogin().getId();
        Map parm = new HashMap();
        parm.put("id", userId);
        RecommendQuestion rr = null;
        try {
            rr = (RecommendQuestion) restTemplate.getForEntity(recommendRemoteUrl.concat("/recommendHotProblem?id={id}"), RecommendQuestion.class, parm).getBody();
        } catch (RestClientException e) {
            return new ResponseEntity<PageInfo>(HttpStatus.REQUEST_TIMEOUT);
        }
        if (rr.getResult() == 0 || rr.getData().size() == 0) {
            return new ResponseEntity(QuestionMessage.RECOMMENDRESPONSENOTFOUND, HttpStatus.NOT_FOUND);
        }
        //判断用户是否登录
//        if(SecurityUtils.getCurrentUserLogin()!=null){
        Pageable requestPage = new PageRequest(pageNumber, pageSize);
        PageInfo<QuestionSummary> pageInfo = questionService.findRecommendeQuestion(rr.getData(), q, requestPage);
        return new ResponseEntity<PageInfo>(pageInfo, HttpStatus.OK);
//        }else{
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);//用户没有登录
//        }

    }

    /**
     * 获取热门，赞数多，参与度高的问题(取前面5个) 完成
     *
     * @param pageSize
     * @param pageNumber
     * @param type
     * @return
     */
    public ResponseEntity<List<QuestionMini>> getTopQuestions(@ApiParam(value = "排序字段和方式 例如：/topics?type=follows,热门问题top5 - follows;  最赞回答问题top5 - likes; 参与度最高问题top5 - replies", allowableValues = "FOLLOWS, LIKES, REPLIES") @RequestParam(value = "type", required = false) String type) {
        // do some magic!
        if (type == null || type.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<QuestionMini> questionMiniList = questionService.findTopQuestion(type);
        if (questionMiniList == null) {
            return new ResponseEntity(QuestionMessage.NO_QUESTION, HttpStatus.OK);
        }
        return new ResponseEntity<List<QuestionMini>>(questionMiniList, HttpStatus.OK);
    }

    /**
     * 取消关注问题 完成
     *
     * @param id
     * @return
     */
    public ResponseEntity<HttpMessage> unFollowQuesionById(@ApiParam(value = "问题id", required = true) @PathVariable("id") Integer id) {
        // do some magic!
        Boolean isFollow = MessageUtils.isMessageExist(id, MessageEventTypeEnum.FOCUS, MessageMsgTypeEnum.FocusQuestions);
        if (!isFollow) {
            return new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST);
        }
        questionService.unFollowQuestion(id);
        return new ResponseEntity<HttpMessage>(HttpStatus.CREATED);
    }

    /**
     * 编辑问题  完成
     *
     * @param id
     * @param question
     * @return
     */
    public ResponseEntity<HttpMessage> updateQuestion(@ApiParam(value = "", required = true) @PathVariable("id") Integer id,
                                                      @ApiParam(value = "问题对象信息", required = true) @RequestBody QuestionRequest question) {
        // do some magic!
        QuestionEntity questionEntity = questionService.findOneQuestion(id);
        if (questionEntity != null) {
            questionService.updateQuestion(id, question);
            return new ResponseEntity<HttpMessage>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<HttpMessage>(QuestionMessage.NO_QUESTION, HttpStatus.OK);
        }

    }

    /**
     * 根据话题ID获取问题列表
     *
     * @param pageSize
     * @param pageNumber
     * @return
     */
    @Override
    public ResponseEntity<PageInfo> getMyTopicQuestions(@ApiParam(value = "话题id", required = true) @PathVariable("topicid") Integer topicid,
                                                        @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                        @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber) {
        Pageable requestPage = new PageRequest(pageNumber, pageSize, Sort.Direction.DESC, "createTime");
        //判断该话题是否存在
        PortalTopicEntity topicEntity = lgTopicService.findOne(topicid);
        if (topicEntity != null) {
            PageInfo<QuestionSummary> summaryPageInfo = questionService.findQuestionByTopicId(topicid, requestPage);
            if (summaryPageInfo.getList() == null || summaryPageInfo.getList().size() == 0) {
                throw new NotFoundEntityException("该话题没有对应的问题");
            }
            return new ResponseEntity<PageInfo>(summaryPageInfo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);//错误请求，没有该话题
        }

    }

}
