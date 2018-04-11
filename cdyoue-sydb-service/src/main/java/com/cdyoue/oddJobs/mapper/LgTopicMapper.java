package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dao.lqsq.QuestionReplyResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.QuestionResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.TopicResponsitory;
import com.cdyoue.oddJobs.dto.lgfc.*;
import com.cdyoue.oddJobs.en.MessageEventTypeEnum;
import com.cdyoue.oddJobs.en.MessageMsgTypeEnum;
import com.cdyoue.oddJobs.entity.lgsq.PortalTopicEntity;
import com.cdyoue.oddJobs.entity.lgsq.QuestionEntity;
import com.cdyoue.oddJobs.utils.MessageUtils;
import com.cdyoue.oddJobs.utils.UserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/5/2.
 */
@Component
public class LgTopicMapper {
    /**
     * portalTopicEntity 转 Topic
     * @param pre
     * @return
     */
    @Autowired
    private QuestionResponsitory questionResponsitory;
    @Autowired
    private TopicResponsitory topicResponsitory;
    @Autowired
    private QuestionReplyResponsitory questionReplyResponsitory;
    @Autowired
    private QuestionMapper questionMapper;
    public Topic portalTopicEntityToTopic(PortalTopicEntity pre) {
        Topic rs = new Topic();
        Set<QuestionMini> questionMinis = new HashSet<>();
        Set<QuestionEntity> questionEntitiesTemp = pre.getQuestionEntities();
//        if(questionEntitiesTemp.size()>3) {
//            int i = 0;
//            for (QuestionEntity questionEntity : questionEntitiesTemp) {
//                if (i < 3){
//                    questionEntities.add(questionEntity);
//                    i++;
//                }
//            }
//            pre.setQuestionEntities(questionEntities);
//        }
        for (QuestionEntity questionEntity : questionEntitiesTemp) {
            QuestionMini mini = questionMapper.questionEntityToQuestionMini(questionEntity);
            questionMinis.add(mini);
        }
        rs.setQuestionMinis(questionMinis);

        BeanUtils.copyProperties(pre,rs);
        SimpleDateFormat smf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String dd = smf.format(pre.getCreateTime());
        rs.setQuestionCounts(questionResponsitory.selectQueNumByTopicid(pre.getId()));
        rs.setReplyCounts(questionReplyResponsitory.selectReplyNumByTopicid(pre.getId()));
        rs.setCreateTime(dd);
        return rs;
    }
    public Topic portalTopicEntityToTopics(PortalTopicEntity pre) {
        Set<QuestionMini> questionMinis = new HashSet<>();
        Topic rs = new Topic();
        Set<QuestionEntity> questionEntitiesTemp = pre.getQuestionEntities();
        /*if(questionEntitiesTemp.size()>3) {
            int i = 0;
            for (QuestionEntity questionEntity : questionEntitiesTemp) {
                if (i < 3){
                    questionEntities.add(questionEntity);
                    i++;
                }
            }
            pre.setQuestionEntities(questionEntities);
        }*/
        for (QuestionEntity questionEntity : questionEntitiesTemp) {
            QuestionMini mini = questionMapper.questionEntityToQuestionMini(questionEntity);
            questionMinis.add(mini);
        }
        rs.setQuestionMinis(questionMinis);

        BeanUtils.copyProperties(pre,rs);
        SimpleDateFormat smf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String dd = smf.format(pre.getCreateTime());
        rs.setCreateTime(dd);

        //String creatName = topicResponsitory.getUserName(pre.getCreateBy());
        String creatName = UserUtils.getUserName(pre.getCreateBy());
        rs.setCreateBy(creatName);

        return rs;
    }
    /**
     * Topic 转 portalTopicEntity
     * @param te
     * @return
     */
    public static PortalTopicEntity TopicToportalTopicEntity(TopicBase te) {
        PortalTopicEntity pe = new PortalTopicEntity();
        BeanUtils.copyProperties(te,pe);
        pe.getCreateTime().valueOf(te.getCreateTime());
        return pe;
    }

    /**
     * portalTopicEntity  转 TopicMini
     * @param pre
     * @return
     */
    public TopicMini portalTopicEntityToTopicMini(PortalTopicEntity pre,Integer id) {
        TopicMini rs = new TopicMini();
        BeanUtils.copyProperties(pre,rs);
        Boolean isfollow = MessageUtils.isMessageExist(id, MessageEventTypeEnum.FOCUS, MessageMsgTypeEnum.FocusTopic);
        rs.setIsfollow(isfollow);
        return rs;
    }
    public static TopicMini portalTopicEntityToTopicMini2(PortalTopicEntity pre) {
        TopicMini rs = new TopicMini();
        BeanUtils.copyProperties(pre,rs);
        return rs;
    }
}
