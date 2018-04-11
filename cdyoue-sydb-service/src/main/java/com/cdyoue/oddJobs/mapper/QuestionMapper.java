package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dao.lqsq.*;
import com.cdyoue.oddJobs.dto.lgfc.*;
import com.cdyoue.oddJobs.en.MessageEventTypeEnum;
import com.cdyoue.oddJobs.en.MessageMsgTypeEnum;
import com.cdyoue.oddJobs.entity.lgsq.PortalMessageEntity;
import com.cdyoue.oddJobs.entity.lgsq.PortalRealNameInfoEntity;
import com.cdyoue.oddJobs.entity.lgsq.QuestionEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserpersonalEntity;
import com.cdyoue.oddJobs.utils.MessageUtils;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by sky on 2017/5/2.
 */
@Component
public class QuestionMapper {
    @Autowired
    private QuestionReplyResponsitory questionReplyResponsitory;
    @Autowired
    private QuestionResponsitory questionResponsitory;
    @Autowired
    private UserResponsitory userResponsitory;
    @Autowired
    private ReplyMapper replyMapper;
    @Autowired
    private UserenterpriseResponsitory userenterpriseResponsitory;
    @Autowired
    private UserpersonalResponsitory userpersonalResponsitory;
    @Autowired
    private PortalRealNameInfoResponsitory portalRealNameInfoResponsitory;

    /**
     * QuestionSummary转QuestionEntity
     *
     * @param questionSummary
     * @return questionEntity
     */
    public QuestionEntity questionSummaryToQuestionEntity(QuestionSummary questionSummary) {
        QuestionEntity questionEntity = new QuestionEntity();

        questionEntity.setId(questionSummary.getQuestionBase().getId());//id
        questionEntity.setCreateTime(Timestamp.valueOf(questionSummary.getQuestionBase().getPublishTime()));//创建发布时间
        questionEntity.setTitle(questionSummary.getQuestionBase().getName());//问题名字
        questionEntity.setIntroduction(questionSummary.getQuestionBase().getContent());//问题内容
        questionEntity.setUserId(SecurityUtils.getCurrentUserLogin().getId());//发布人id
        questionSummary.getQuestionBase().setFocus(MessageUtils.isMessageExist(questionEntity.getId(), MessageEventTypeEnum.FOCUS, MessageMsgTypeEnum.FocusQuestions));

        return questionEntity;
    }

    //查询发布问题的人
    public String getName(QuestionEntity questionEntity) {
        Integer userType = userResponsitory.findOne(questionEntity.getUserId()).getUserType();
        String name = null;
        if (userType == 0) {
            //普通用户,先判断是否实名认证
            PortalRealNameInfoEntity portalRealNameInfoEntity = new PortalRealNameInfoEntity();
            List<PortalRealNameInfoEntity> byUserId = portalRealNameInfoResponsitory.findByUserId(questionEntity.getUserId());
            //j解决多个个人标签无法登陆问题 如有 需要自行修改
            for (PortalRealNameInfoEntity p : byUserId) {
                if (p.getApplyType() == 1) {
                    portalRealNameInfoEntity = p;
                    break;
                }
            }
            if (portalRealNameInfoEntity != null && null != portalRealNameInfoEntity.getReviewStatus() && null != portalRealNameInfoEntity.getApplyType() && portalRealNameInfoEntity.getReviewStatus() == 1 && portalRealNameInfoEntity.getApplyType() == 1) {
                //实名认证并审核通过
                name = userpersonalResponsitory.findByUid(questionEntity.getUserId()).getName();
            } else {
                //没实名认证用昵称
                UserpersonalEntity u = userpersonalResponsitory.findByUid(questionEntity.getUserId());
                name = u.getNickName();
            }
        } else if (userType == 1) {
            //企业用户
            name = userenterpriseResponsitory.findNameByUid(questionEntity.getUserId());
        }
        return name;
    }

    /**
     * QuestionEntity转QuestionSummary
     *
     * @param questionEntity
     * @return questionSummary
     */
    public QuestionSummary questionEntityToQuestionSummary(QuestionEntity questionEntity) {
        QuestionSummary questionSummary = new QuestionSummary();
        QuestionBase questionBase = new QuestionBase();

        questionBase.setId(questionEntity.getId());
        questionBase.content(questionEntity.getIntroduction());
        questionBase.setName(questionEntity.getTitle());
        Integer TopicId=questionEntity.getTopicId();
        questionBase.setTopicId(TopicId);

        String name = getName(questionEntity);

        questionBase.setAuthor(name);//发布人名字
        questionBase.setPublishTime(new SimpleDateFormat("yyyy/MM/dd").format(questionEntity.getCreateTime()));
        questionBase.setFocus(MessageUtils.isMessageExist(questionEntity.getId(), MessageEventTypeEnum.FOCUS, MessageMsgTypeEnum.FocusQuestions));

        questionSummary.setQuestionBase(questionBase);
        questionSummary.setFollowNum(questionEntity.getFollowCount());//问题关注数
        int replyNum = questionReplyResponsitory.selectReplyNum(questionEntity.getId());
        questionSummary.setReplyNum(replyNum);//问题回答数
        List<PortalRealNameInfoEntity> byUseridAndReviewstatus = portalRealNameInfoResponsitory.findByUseridAndReviewstatus(questionEntity.getUserId(), new Integer(1).byteValue());

        List<Integer> certs = new ArrayList<>();
        byUseridAndReviewstatus.forEach(p -> {
                    certs.add(p.getApplyType());
                }
        );

        questionSummary.setCerts(certs);

        return questionSummary;
    }

    /**
     * QuestionEntity转QuestionDetail
     *
     * @param questionEntity
     * @return questionDetail
     */
    public QuestionDetail QuestionEntityToQuestionDetail(QuestionEntity questionEntity) {
        QuestionDetail questionDetail = new QuestionDetail();
        QuestionBase questionBase = new QuestionBase();

        if (questionEntity != null) {
            questionBase.setId(questionEntity.getId());
            questionBase.content(questionEntity.getIntroduction());
            questionBase.setName(questionEntity.getTitle());
            String name = getName(questionEntity);
            questionBase.setAuthor(name);//发布人名字
            questionBase.setPublishTime(new SimpleDateFormat("yyyy-MM-dd").format(questionEntity.getCreateTime()));
            questionBase.setFocus(MessageUtils.isMessageExist(questionEntity.getId(), MessageEventTypeEnum.FOCUS, MessageMsgTypeEnum.FocusQuestions));
            List<ReplyDetails> replyDetails = questionEntity.getQuestionreplys().stream().map(pre -> replyMapper.replyEntity2Replydetails(pre)).collect(Collectors.toList());
            questionDetail.setReplies(replyDetails);
            questionDetail.setQuestionBase(questionBase);

        }

        return questionDetail;
    }

    /**
     * QuestionRequest转QuestionEntity
     *
     * @param question 发布的问题
     * @return
     */
    public QuestionEntity questionRequestToQuestionEntity(QuestionRequest question) {
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setTitle(question.getTitle());//问题名字
        questionEntity.setIntroduction(question.getContent());//问题内容
        questionEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));//时间
        questionEntity.setUserId(SecurityUtils.getCurrentUserLogin().getId());//发布人id
        questionEntity.setDomainId(question.getDomainId()); //问题所属领域id
        questionEntity.setFollowCount(0);//问题关注数，刚发布默认为0

        return questionEntity;
    }

    //为指定话题创建问题，会多一个话题id字段
    public QuestionEntity questionRequestToQuestionEntityTopic(Integer topicid, QuestionRequest question) {
        QuestionEntity questionEntity = questionRequestToQuestionEntity(question);
        return questionEntity;
    }

    //编辑问题，关注数不会变
    public QuestionEntity questionRequestToQuestionEntityEdit(QuestionRequest question) {
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setTitle(question.getTitle());//问题名字
        questionEntity.setIntroduction(question.getContent());//问题内容
        questionEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));//时间
        questionEntity.setDomainId(question.getDomainId()); //问题所属领域id
        return questionEntity;
    }

    /**
     * QuestionEntity转QuestionMini
     *
     * @param questionEntity
     * @return
     */
    public QuestionMini questionEntityToQuestionMini(QuestionEntity questionEntity) {
        QuestionMini questionMini = new QuestionMini();
        questionMini.setId(questionEntity.getId());
        questionMini.setName(questionEntity.getTitle());
        return questionMini;
    }

    public QuestionBase questionEntityToQuestionBase(QuestionEntity questionEntity) {
        QuestionBase questionBase = new QuestionBase();

        String name = getName(questionEntity);

        questionBase.setAuthor(name);
        questionBase.setName(questionEntity.getTitle());
        questionBase.setId(questionEntity.getId());
        questionBase.setContent(questionEntity.getIntroduction());
        questionBase.setPublishTime(new SimpleDateFormat("yyyy/MM/dd").format(questionEntity.getCreateTime()));
        questionBase.setFocus(MessageUtils.isMessageExist(questionEntity.getId(), MessageEventTypeEnum.FOCUS, MessageMsgTypeEnum.FocusQuestions));//是否关注
        return questionBase;
    }

    public QuestionSummary messageToSummary(PortalMessageEntity entity) {
        QuestionSummary summary = new QuestionSummary();
        QuestionEntity entity1 = questionResponsitory.findOne(entity.getEventId());

        //如果关注的问题被删除，会报nullpointerException
        if (entity1 != null) {
            QuestionBase questionDTO = this.questionEntityToQuestionBase(entity1);
            summary.setQuestionBase(questionDTO);
            summary.setFollowNum(entity1.getFollowCount());
            summary.setReplyNum(questionReplyResponsitory.selectReplyNum(entity.getEventId()));
        }
        return summary;
    }

    public QuestionDetails questionEntityToQuestionDetails(QuestionEntity questionEntity) {
        QuestionDetails questionDetails = new QuestionDetails();
        QuestionBase questionBase = new QuestionBase();
        //查询发布问题的人
        String name = getName(questionEntity);

        questionBase.setId(questionEntity.getId());
        questionBase.setName(questionEntity.getTitle());
        questionBase.setPublishTime(new SimpleDateFormat("yyyy/MM/dd").format(questionEntity.getCreateTime()));
        questionBase.setContent(questionEntity.getIntroduction());
        questionBase.setAuthor(name);
        questionBase.setFocus(MessageUtils.isMessageExist(questionEntity.getId(), MessageEventTypeEnum.FOCUS, MessageMsgTypeEnum.FocusQuestions));
        questionDetails.setQuestionBase(questionBase);
        questionDetails.setReplyNum(questionEntity.getQuestionreplys().size());


        Integer quesFoucsNum = MessageUtils.getQuesFoucsNum(questionEntity.getId(), MessageEventTypeEnum.FOCUS, MessageMsgTypeEnum.FocusQuestions);
        questionDetails.setFocusNum(quesFoucsNum);
        return questionDetails;
    }
}
