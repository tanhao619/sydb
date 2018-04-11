package com.cdyoue.oddJobs.api.lgfc;

import java.util.List;

import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.common.message.AuthenticationMessage;
import com.cdyoue.oddJobs.dto.common.message.ReplyMessage;
import com.cdyoue.oddJobs.dto.lgfc.QuestionDetail;
import com.cdyoue.oddJobs.dto.lgfc.ReplyDetails;
import com.cdyoue.oddJobs.en.MessageEventTypeEnum;
import com.cdyoue.oddJobs.en.MessageMsgTypeEnum;
import com.cdyoue.oddJobs.exception.UnAuthenticationMessageException;
import com.cdyoue.oddJobs.service.QuestionService;
import com.cdyoue.oddJobs.service.ReplyService;
import com.cdyoue.oddJobs.utils.MessageUtils;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.common.SortUtils;
import com.cdyoue.spring.page.PageInfo;

import io.swagger.annotations.ApiParam;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-30T14:30:56.295Z")

@Controller
public class RepliesApiController implements RepliesApi {

    @Autowired
    private ReplyService replyService;

    @Autowired
    private QuestionService questionService;

    public ResponseEntity<HttpMessage> collectReplyById(@ApiParam(value = "回复id",required=true ) @PathVariable("id") Integer id) {
        // do some magic!

        // 判断用户是否登录（401）
        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();
        if (currentUserLogin == null){
            return new ResponseEntity<HttpMessage>(ReplyMessage.REPLYCOLLECTFAILD401, HttpStatus.UNAUTHORIZED);
        }
        // 判断要收藏的回复是否存在（404）
        ReplyDetails replyDetails = replyService.findOne(id);
        if (replyDetails == null) {
            return new ResponseEntity<HttpMessage>(ReplyMessage.REPLYCOLLECTFAILD404, HttpStatus.NOT_FOUND);
        }
        // 判断用户是否收藏（400）
        Boolean isCollect = MessageUtils.isMessageExist(id, MessageEventTypeEnum.COLLECTION, MessageMsgTypeEnum.CollectionAnswer);
        if (isCollect) {
            return new ResponseEntity<HttpMessage>(ReplyMessage.REPLYCOLLECTFAILD400, HttpStatus.BAD_REQUEST);
        }
        int userid = currentUserLogin.getId();
        // 如果存在，执行收藏操作，收藏数量加1（201）
        replyService.updateCollectById(id, replyDetails.getCollectNum() + 1);
        // 收藏后关联到用户，防止重复收藏
        MessageUtils.createMessage(id, MessageEventTypeEnum.COLLECTION, MessageMsgTypeEnum.CollectionAnswer);
        return new ResponseEntity<HttpMessage>(HttpStatus.CREATED);
    }

    public ResponseEntity<PageInfo<QuestionDetail>> getMyColReplies(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                                                    @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber) {
        // do some magic!
        // 获取当前用户的id
    	if (SecurityUtils.getCurrentUserLogin() == null) {
			throw new UnAuthenticationMessageException(AuthenticationMessage.USERUNLOGIN);
		}
        Integer userid = SecurityUtils.getCurrentUserLogin().getId();
        // 根据userid从message得到eventid（当前eventtype=6<收藏>，msgtype=2<回答>），即replyid
        List<Integer> replyids = MessageUtils.getMessageEventIds(MessageEventTypeEnum.COLLECTION, MessageMsgTypeEnum.CollectionAnswer);

        if (replyids == null || replyids.size() < 1) {
            //return new ResponseEntity(ReplyMessage.REPLYCOLLECTNOTFOUND, HttpStatus.NOT_FOUND);
            throw new NotFoundEntityException("没有数据");
        }
        String sort = "-createTime";
        Sort orders = SortUtils.assembleSort(sort);
        PageRequest pageRequest = new PageRequest(pageNumber,pageSize,orders);
        PageInfo<QuestionDetail> pageInfo = replyService.getMyColReply(replyids, pageRequest);
        /*if (pageInfo.getList() == null || pageInfo.getList().size() < 1 ){
            return new ResponseEntity(ReplyMessage.REPLYUSERNOTFOUND, HttpStatus.NOT_FOUND);
        }*/
        return new ResponseEntity<PageInfo<QuestionDetail>>(pageInfo, HttpStatus.OK);
    }

    public ResponseEntity<PageInfo<QuestionDetail>> getUserReplies(@ApiParam(value = "用户id",required=true ) @PathVariable("userid") Integer userid,
                                                                      @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                                                      @ApiParam(value = "页码。默认第一页（0开始）", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="1") Integer pageNumber) {
        // do some magic!
        String sort = "-createTime";
        Sort orders = SortUtils.assembleSort(sort);
        PageRequest pageRequest = new PageRequest(pageNumber,pageSize,orders);
        PageInfo<QuestionDetail> pageInfo = replyService.getUserReply(pageRequest, userid);
        /*if (pageInfo.getList() == null || pageInfo.getList().size() < 1 ){
            return new ResponseEntity(ReplyMessage.REPLYUSERNOTFOUND, HttpStatus.NOT_FOUND);
        }*/
        return new ResponseEntity<PageInfo<QuestionDetail>>(pageInfo,HttpStatus.OK);
    }

    public ResponseEntity<HttpMessage> likeReplyById(@ApiParam(value = "回复id",required=true ) @PathVariable("id") Integer id) {
        // do some magic!
        // 判断用户是否登录（401）
        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();
        if (currentUserLogin == null){
            return new ResponseEntity<HttpMessage>(ReplyMessage.REPLYLIKEFAILD401, HttpStatus.UNAUTHORIZED);
        }
        // 判断要点赞的回复是否存在（404）
        ReplyDetails replyDetails = replyService.findOne(id);
        if (replyDetails == null) {
            return new ResponseEntity<HttpMessage>(ReplyMessage.REPLYLIKEFAILD404, HttpStatus.NOT_FOUND);
        }
        // 判断用户是否点赞（400）<如何判断>
        Boolean isLike = MessageUtils.isMessageExist(id, MessageEventTypeEnum.THUMBUP, MessageMsgTypeEnum.ThumbUpAnswer);
        if (isLike) {
            return new ResponseEntity<HttpMessage>(ReplyMessage.REPLYLIKEFAILD400, HttpStatus.BAD_REQUEST);
        }

        // 如果存在，执行点赞操作，点赞数量加1（201）
        replyService.updateLikeById(id, replyDetails.getLikeNum() + 1);
        // 点赞后关联到用户，防止重复点赞
        MessageUtils.createMessage(id, MessageEventTypeEnum.THUMBUP, MessageMsgTypeEnum.ThumbUpAnswer);
        return new ResponseEntity<HttpMessage>(ReplyMessage.REPLYLIKESUCCESS, HttpStatus.CREATED);
    }

    public ResponseEntity<HttpMessage> unCollectReplyById(@ApiParam(value = "回复id",required=true ) @PathVariable("id") Integer id) {
        // do some magic!
        // 判断用户是否登录（401）
        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();
        /*if (currentUserLogin == null){
            return new ResponseEntity<HttpMessage>(HttpStatus.UNAUTHORIZED);
        }*/
        // 判断要收藏的回复是否存在（404）
        ReplyDetails replyDetails = replyService.findOne(id);
        if (replyDetails == null) {
            return new ResponseEntity<HttpMessage>(ReplyMessage.REPLYUNCOLLECTFAILD404, HttpStatus.NOT_FOUND);
        }
        // 判断用户是否收藏（400）
        int userid = currentUserLogin.getId();
        Boolean isCollect = MessageUtils.isMessageExist(id, MessageEventTypeEnum.COLLECTION, MessageMsgTypeEnum.CollectionAnswer);
        if (!isCollect) {
            return new ResponseEntity<HttpMessage>(ReplyMessage.REPLYUNCOLLECTFAILD400, HttpStatus.BAD_REQUEST);
        }

        // 如果存在，接着判断收藏数量是否小于1（500）
        if (replyDetails.getCollectNum() < 1) {
            return new ResponseEntity<HttpMessage>(ReplyMessage.REPLYUNCOLLECTFAILD500, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // 执行取消收藏操作，收藏数量减1（201）
        replyService.updateCollectById(id, replyDetails.getCollectNum() - 1);
        MessageUtils.cancelEventAction(id, MessageEventTypeEnum.COLLECTION, MessageMsgTypeEnum.CollectionAnswer);
        return new ResponseEntity<HttpMessage>(ReplyMessage.REPLYUNCOLLECTSUCCESS, HttpStatus.CREATED);
    }

    public ResponseEntity<HttpMessage> unLikeReplyById(@ApiParam(value = "回复id",required=true ) @PathVariable("id") Integer id) {
        // do some magic!
        // 判断用户是否登录（401）
        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();
        /*if (currentUserLogin == null){
            return new ResponseEntity<HttpMessage>(HttpStatus.UNAUTHORIZED);
        }*/

        // 判断要点赞的回复是否存在（404）
        ReplyDetails replyDetails = replyService.findOne(id);
        if (replyDetails == null) {
            return new ResponseEntity<HttpMessage>(ReplyMessage.REPLYUNLIKEFAILD404, HttpStatus.NOT_FOUND);
        }
        // 判断用户是否点赞（400）<如何判断>
        int userid = currentUserLogin.getId();
        Boolean isLike = MessageUtils.isMessageExist(id, MessageEventTypeEnum.THUMBUP, MessageMsgTypeEnum.ThumbUpAnswer);
        if (!isLike) {
            return new ResponseEntity<HttpMessage>(ReplyMessage.REPLYUNLIKEFAILD400, HttpStatus.BAD_REQUEST);
        }

        // 如果存在，接着判断点赞数量是否小于1（500）
        if (replyDetails.getLikeNum() < 1) {
            return new ResponseEntity<HttpMessage>(ReplyMessage.REPLYUNLIKEFAILD500, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // 执行取消点赞操作，点赞数量减1（201）
        replyService.updateLikeById(id, replyDetails.getLikeNum() - 1);
        MessageUtils.cancelEventAction(id, MessageEventTypeEnum.THUMBUP, MessageMsgTypeEnum.ThumbUpAnswer);
        return new ResponseEntity<HttpMessage>(ReplyMessage.REPLYUNLIKESUCCESS, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PageInfo<ReplyDetails>> getReplyByQuestionid(@ApiParam(value = "问题id",required=true ) @PathVariable("questionid") Integer questionid,
                                                                       @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                                                       @ApiParam(value = "页码。默认第一页（0开始）", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="1") Integer pageNumber) {
        /*if (!questionService.isExist(questionid)) {
            return new ResponseEntity(ReplyMessage.REPLYQUESTION400, HttpStatus.BAD_REQUEST);
        }*/
        String sort = "-createTime";
        Sort orders = SortUtils.assembleSort(sort);
        PageRequest pageRequest = new PageRequest(pageNumber,pageSize,orders);
        PageInfo<ReplyDetails> pageInfo = replyService.getReplyByQuestionid(pageRequest, questionid);
        /*if (pageInfo.getList() == null || pageInfo.getList().size() < 1 ){
            return new ResponseEntity(ReplyMessage.REPLYQUESTIONNOTFOUND, HttpStatus.NOT_FOUND);
        }*/
        return new ResponseEntity<PageInfo<ReplyDetails>>(pageInfo,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> deleteReplyById(@ApiParam(value = "回复id",required=true ) @PathVariable("id") Integer id) {
        ReplyDetails replyDetails = replyService.findOne(id);
        if (replyDetails == null) {
            return new ResponseEntity<HttpMessage>(ReplyMessage.REPLYDELETEFAILD404, HttpStatus.NOT_FOUND);
        }
        replyService.deleteById(id);
        return new ResponseEntity<HttpMessage>(ReplyMessage.REPLYDELETESUCCESS, HttpStatus.CREATED);
    }

}
