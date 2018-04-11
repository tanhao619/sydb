package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dao.lqsq.QuestionReplyResponsitory;
import com.cdyoue.oddJobs.dto.lgfc.QuestionUserReply;
import com.cdyoue.oddJobs.dto.lgfc.Reply4User;
import com.cdyoue.oddJobs.entity.lgsq.QuestionEntity;
import com.cdyoue.oddJobs.entity.lgsq.QuestionreplyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by dengshaojun on 2017/5/3.
 *
 */
@Component
public class QuestionUserReplyMapper {
    @Autowired
    private QuestionReplyResponsitory questionReplyResponsitory;
    @Autowired
    private Reply4UserMapper reply4UserMapper;


    /**
     * QuestionEntity转QuestionUserReply
     * @param questionEntity
     * @return questionUserReply
     */
    public QuestionUserReply QuestionEntityToQuestionUserReply(QuestionEntity questionEntity, Integer userid){
        QuestionUserReply questionUserReply=new QuestionUserReply();
        questionUserReply.setName(questionEntity.getTitle());
        questionUserReply.setContent(questionEntity.getIntroduction());
        List<QuestionreplyEntity> questionreplyEntityList = questionReplyResponsitory.findByUseridAndquestionid(userid, questionEntity.getId());
        List<Reply4User> reply4Users = questionreplyEntityList.stream().map(pre -> reply4UserMapper.replyEntity2Reply4User(pre)).collect(Collectors.toList());
        questionUserReply.setReplies(reply4Users);
        return questionUserReply;
    }

}
