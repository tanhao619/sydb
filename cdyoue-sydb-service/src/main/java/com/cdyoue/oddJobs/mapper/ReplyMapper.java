package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dao.lqsq.PortalRealNameInfoResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.UserResponsitory;
import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.lgfc.ReplyDetails;
import com.cdyoue.oddJobs.en.MessageEventTypeEnum;
import com.cdyoue.oddJobs.en.MessageMsgTypeEnum;
import com.cdyoue.oddJobs.entity.lgsq.PortalRealNameInfoEntity;
import com.cdyoue.oddJobs.entity.lgsq.QuestionreplyEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import com.cdyoue.oddJobs.utils.MessageUtils;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dengshaojun on 2017/5/2.
 */
@Component
public class ReplyMapper {

    @Autowired
    private UserResponsitory userResponsitory;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PortalRealNameInfoResponsitory portalRealNameInfoResponsitory;

    /**
     * ReplyEntity转ReplyDetails
     * @param questionreplyEntity
     * @return ReplyDetails
     */
    public ReplyDetails replyEntity2Replydetails(QuestionreplyEntity questionreplyEntity) {
        ReplyDetails replyDetails = new ReplyDetails();
        if (questionreplyEntity != null) {
            replyDetails.setId(questionreplyEntity.getId());
            replyDetails.setCollectNum(questionreplyEntity.getFavorCount());
            replyDetails.setLikeNum(questionreplyEntity.getLikeCount());
            replyDetails.setContent(questionreplyEntity.getContext());
            replyDetails.setReplierId(questionreplyEntity.getUserId());
            replyDetails.setReplierTime(new SimpleDateFormat("yyyy/MM/dd").format(questionreplyEntity.getCreateTime()));
            UserEntity userEntity = userResponsitory.findOne(questionreplyEntity.getUserId());
            if (userEntity != null){
                if (userEntity.getUserpersonalEntity() != null) {
                    replyDetails.setReplierHeadimg(userEntity.getUserpersonalEntity().getHeadImg());
                    replyDetails.setReplierName(userEntity.getUserpersonalEntity().getName());
                } else if (userEntity.getUserenterpriseEntity() != null) {
                    replyDetails.setReplierHeadimg(userEntity.getUserenterpriseEntity().getHeadImg());
                    replyDetails.setReplierName(userEntity.getUserenterpriseEntity().getName());
                }
                List<PortalRealNameInfoEntity> byUseridAndReviewstatus = portalRealNameInfoResponsitory.findByUseridAndReviewstatus(questionreplyEntity.getUserId(), new Integer(1).byteValue());
                List<Integer> certs = new ArrayList<>();
                byUseridAndReviewstatus.forEach(p ->{
                    certs.add(p.getApplyType());
                });
                replyDetails.setCerts(certs);
            }
            Boolean collect = MessageUtils.isMessageExist(questionreplyEntity.getId(), MessageEventTypeEnum.COLLECTION, MessageMsgTypeEnum.CollectionAnswer);
            replyDetails.setCollect(collect);
            Boolean like = MessageUtils.isMessageExist(questionreplyEntity.getId(), MessageEventTypeEnum.THUMBUP, MessageMsgTypeEnum.ThumbUpAnswer);
            replyDetails.setLike(like);
            Boolean focus = MessageUtils.isMessageExist(questionreplyEntity.getQuestionEntity().getId(), MessageEventTypeEnum.FOCUS, MessageMsgTypeEnum.FocusQuestions);
            replyDetails.setFocus(focus);
            Boolean focusReplyer = SecurityUtils.getCurrentUserLogin() == null?false:MessageUtils.isFocusReplyer(SecurityUtils.getCurrentUserLogin().getId(),questionreplyEntity.getUserId(), MessageEventTypeEnum.FOCUS, MessageMsgTypeEnum.FocusTalent);
            replyDetails.setFocusReplyer(focusReplyer);
//            UserMine userMine= SecurityUtils.getCurrentUserLogin();
//            if(userMine==null){
//                //没登录，默认没关注
//                replyDetails.setFocusReplyer(false);
//            }else {
//                int userId=userMine.getId();
//                Boolean focusReplyer=MessageUtils.isMessageExist(userId,questionreplyEntity.getUserId(),MessageEventTypeEnum.FOCUS,MessageMsgTypeEnum.FocusTalent);
//                replyDetails.setFocusReplyer(focusReplyer);
//            }
        }
        return replyDetails;
    }

}
