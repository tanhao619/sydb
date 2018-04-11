package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.MessageResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.QuestionReplyResponsitory;
import com.cdyoue.oddJobs.dto.lgfc.QuestionDetail;
import com.cdyoue.oddJobs.dto.lgfc.ReplyDetails;
import com.cdyoue.oddJobs.en.MessageEventTypeEnum;
import com.cdyoue.oddJobs.en.MessageMsgTypeEnum;
import com.cdyoue.oddJobs.entity.lgsq.QuestionEntity;
import com.cdyoue.oddJobs.entity.lgsq.QuestionreplyEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.QuestionMapper;
import com.cdyoue.oddJobs.mapper.ReplyMapper;
import com.cdyoue.oddJobs.service.ReplyService;
import com.cdyoue.oddJobs.utils.MessageUtils;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by dengshaojun on 2017/5/2.
 */
@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private QuestionReplyResponsitory questionReplyResponsitory;

    @Autowired
    private MessageResponsitory messageResponsitory;

    @Autowired
    private ReplyMapper replyMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    @Transactional
    public void createReply4Question(Integer questionid, String reply) {
        QuestionreplyEntity questionreplyEntity = new QuestionreplyEntity();
        questionreplyEntity.setContext(reply);
        questionreplyEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        questionreplyEntity.setFavorCount(0);
        questionreplyEntity.setLikeCount(0);
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setId(questionid);
        questionreplyEntity.setQuestionEntity(questionEntity);
        // 获取传过来的问题类型的id，然而并没有
        //questionreplyEntity.setQuestionTypeId(questiontypeid);
        questionreplyEntity.setUserId(SecurityUtils.getCurrentUserLogin().getId());
        questionReplyResponsitory.save(questionreplyEntity);
    }

    @Override
    public ReplyDetails findOne(Integer id) {
        QuestionreplyEntity questionreplyEntity = questionReplyResponsitory.findOne(id);
        if (questionreplyEntity == null) {
            return null;
        }
        return replyMapper.replyEntity2Replydetails(questionreplyEntity);
    }

    @Override
    @Transactional
    public void updateCollectById(Integer id, int favorCount) {
        QuestionreplyEntity questionreplyEntity = questionReplyResponsitory.findOne(id);
        questionreplyEntity.setFavorCount(favorCount);
        questionReplyResponsitory.save(questionreplyEntity);
    }

    @Override
    @Transactional
    public void updateLikeById(Integer id, int likeCount) {
        QuestionreplyEntity questionreplyEntity = questionReplyResponsitory.findOne(id);
        questionreplyEntity.setLikeCount(likeCount);
        questionReplyResponsitory.save(questionreplyEntity);
    }

    @Override
    public QuestionreplyEntity findById(Integer id) {
        return questionReplyResponsitory.findOne(id);
    }

    @Override
    public PageInfo<QuestionDetail> getUserReply(PageRequest pageRequest, Integer userid) {

        Page<QuestionEntity> questionEntityPage = questionReplyResponsitory.findReplyQuestionByUserId(userid, pageRequest);
        if (questionEntityPage.getContent().size() == 0) {
            throw new NotFoundEntityException("没有数据");
        }
        List<QuestionEntity> questionEntities = new ArrayList<>();
        for (QuestionEntity questionEntity:questionEntityPage.getContent()) {
            QuestionEntity questionEntity1 = new QuestionEntity();
            BeanUtils.copyProperties(questionEntity, questionEntity1);
            questionEntity1.setQuestionreplys(null);
            Set<QuestionreplyEntity> questionreplyEntities = new HashSet<>();
            for (Iterator<QuestionreplyEntity> it = questionEntity.getQuestionreplys().iterator(); it.hasNext();) {
                QuestionreplyEntity questionreplyEntity = it.next();
                if (questionreplyEntity.getUserId().equals(userid)) {
                    questionreplyEntities.add(questionreplyEntity);
                }
            }
            questionEntity1.setQuestionreplys(questionreplyEntities);
            questionEntities.add(questionEntity1);
        }
        List<QuestionDetail> questionDetails = questionEntities.stream().map(pre -> questionMapper.QuestionEntityToQuestionDetail(pre)).collect(Collectors.toList());
        return new PageInfo<QuestionDetail>(new PageImpl(questionDetails, pageRequest, questionEntityPage.getTotalElements()));

    }

    @Override
    public PageInfo<QuestionDetail> getMyColReply(List<Integer> replyids, PageRequest pageRequest) {
        Page<QuestionEntity> questionEntityPage = questionReplyResponsitory.findByIdIn(replyids, pageRequest);

        /*for (QuestionEntity questionEntity:questionEntityPage.getContent()) {
            for (Iterator<QuestionreplyEntity> it = questionEntity.getQuestionreplys().iterator(); it.hasNext();) {
                QuestionreplyEntity questionreplyEntity = it.next();
                if (!replyids.contains(questionreplyEntity.getId())) {
                    it.remove();  // ok
                }
            }

        }*/
        if (questionEntityPage.getContent().size() == 0) {
            throw new NotFoundEntityException("没有数据");
        }
        List<QuestionEntity> questionEntities = new ArrayList<>();
        for (QuestionEntity questionEntity:questionEntityPage.getContent()) {
            QuestionEntity questionEntity1 = new QuestionEntity();
            BeanUtils.copyProperties(questionEntity, questionEntity1);
            questionEntity1.setQuestionreplys(null);
            Set<QuestionreplyEntity> questionreplyEntities = new HashSet<>();
            for (Iterator<QuestionreplyEntity> it = questionEntity.getQuestionreplys().iterator(); it.hasNext();) {
                QuestionreplyEntity questionreplyEntity = it.next();
                if (replyids.contains(questionreplyEntity.getId())) {
                    questionreplyEntities.add(questionreplyEntity);
                }
            }
            questionEntity1.setQuestionreplys(questionreplyEntities);
            questionEntities.add(questionEntity1);
        }
        List<QuestionDetail> questionDetails = questionEntities.stream().map(pre -> questionMapper.QuestionEntityToQuestionDetail(pre)).collect(Collectors.toList());
        return new PageInfo<QuestionDetail>(new PageImpl(questionDetails, pageRequest, questionEntityPage.getTotalElements()));
    }

    @Override
    public PageInfo<ReplyDetails> getReplyByQuestionid(PageRequest pageRequest, Integer questionid) {
        Page<QuestionreplyEntity> questionreplyEntityPage = questionReplyResponsitory.findByQuestionid(questionid, pageRequest);
        if (questionreplyEntityPage.getContent().size() == 0) {
            throw new NotFoundEntityException("没有数据");
        }
        List<ReplyDetails> replyDetails = questionreplyEntityPage.getContent().stream().map(pre -> replyMapper.replyEntity2Replydetails(pre)).collect(Collectors.toList());
        return new PageInfo<ReplyDetails>(new PageImpl(replyDetails, pageRequest, questionreplyEntityPage.getTotalElements()));
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        questionReplyResponsitory.delete(id);
        MessageUtils.cancelEventAction(id, MessageEventTypeEnum.COLLECTION, MessageMsgTypeEnum.CollectionAnswer);
    }
}
