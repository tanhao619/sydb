package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.MessageResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.QuestionReplyResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.QuestionResponsitory;
import com.cdyoue.oddJobs.dto.lgfc.*;
import com.cdyoue.oddJobs.en.MessageEventTypeEnum;
import com.cdyoue.oddJobs.en.MessageMsgTypeEnum;
import com.cdyoue.oddJobs.entity.lgsq.PortalMessageEntity;
import com.cdyoue.oddJobs.entity.lgsq.QuestionEntity;
import com.cdyoue.oddJobs.mapper.QuestionMapper;
import com.cdyoue.oddJobs.service.QuestionService;
import com.cdyoue.oddJobs.service.support.ServiceSupport;
import com.cdyoue.oddJobs.utils.MessageUtils;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sky on 2017/5/2.
 *
 */
@Service
public class QuestionServiceImpl extends ServiceSupport<QuestionEntity> implements QuestionService{

    @Autowired
    private QuestionReplyResponsitory questionReplyResponsitory;
    @Autowired
    QuestionResponsitory questionResponsitory;
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    private MessageResponsitory messageResponsitory;

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public Class getJpaRepositoryClazz() {
        return QuestionResponsitory.class;
    }

    @Override
    public PageInfo<QuestionSummary> findByKeyWord(String q, Pageable requestPage) {
        Page<QuestionEntity> rpPage = super.findByStrLike("title",q,requestPage);
        List<QuestionSummary> questionSummaries = new ArrayList<>();
        rpPage.forEach(p ->{
            {
                QuestionSummary questionSummary = new QuestionSummary();
                questionSummary=questionMapper.questionEntityToQuestionSummary(p);
                questionSummaries.add(questionSummary);
            }
        });

//        List<QuestionSummary> questionSummaries = rpPage.getContent().stream().map(p ->
//                questionMapper.questionEntityToQuestionSummary(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(questionSummaries,requestPage, rpPage.getTotalElements()));
    }

    @Override
    public PageInfo<QuestionSummary> findQuestionByTopicId(Integer id, Pageable requestPage) {
        Page<QuestionEntity> questionEntityPage=questionResponsitory.findQuestionByTopicId(id,requestPage);
        List<QuestionSummary> questionSummaries = questionEntityPage.getContent().stream().map(p -> questionMapper.questionEntityToQuestionSummary(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(questionSummaries,requestPage, questionEntityPage.getTotalElements()));
    }

    @Override
    //推荐问题
    public PageInfo<QuestionSummary> findRecommendeQuestion(List<QuestionData> data, String q, Pageable requestPage) {
        ArrayList<Integer> recommendId=new ArrayList<>();
        for(int i=0;i<data.size();i++){
            recommendId.add(data.get(i).getIssueid());
        }
        Integer[] reId=recommendId.toArray(new Integer[data.size()]);
      //Integer[] reId=new Integer[]{2};
       if(q==null||q.isEmpty()){
           Page<QuestionEntity> rpPage=questionResponsitory.findRecommendByIdIn(reId,requestPage);
           List<QuestionSummary> questionSummaries = rpPage.getContent().stream().map(p -> questionMapper.questionEntityToQuestionSummary(p)).collect(Collectors.toList());
           return new PageInfo<>(new PageImpl(questionSummaries,requestPage, rpPage.getTotalElements()));
       }else{
           Page<QuestionEntity> rpPage=questionResponsitory.findRecommendByStrKeyIdIn(reId,"%"+q+"%",requestPage);
           List<QuestionSummary> questionSummaries = rpPage.getContent().stream().map(p -> questionMapper.questionEntityToQuestionSummary(p)).collect(Collectors.toList());
           return new PageInfo<>(new PageImpl(questionSummaries,requestPage, rpPage.getTotalElements()));
       }

    }

    @Override
    public PageInfo<QuestionSummary> findByKeyWordUserId(Integer userid, String q, Pageable requestPage) {
        Page<QuestionEntity> rpPage = super.findByStrLikeUserId(userid,"title",q,requestPage);
        List<QuestionSummary> questionSummary = rpPage.getContent().stream().map(p -> questionMapper.questionEntityToQuestionSummary(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(questionSummary,requestPage, rpPage.getTotalElements()));
    }

    @Override
    public PageInfo<QuestionSummary> findCreateQuestionByUserId(Integer userid,String q, Pageable requestPage) {
        Page<QuestionEntity> rpPage = super.findByStrLikeUserId(userid,"title",q,requestPage);
        List<QuestionSummary> questionSummary = rpPage.getContent().stream().map(p -> questionMapper.questionEntityToQuestionSummary(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(questionSummary,requestPage, rpPage.getTotalElements()));
    }

    @Override
    public Page<QuestionEntity> findReplyQuestionByUserId(Integer userid, Pageable requestPage) {
        Page<QuestionEntity> replyPage=questionReplyResponsitory.findReplyQuestionByUserId(userid,requestPage);
        return replyPage;
    }

    @Override
    public Page<QuestionEntity> findReplyQuestionByUserIdStrKey(Integer userid, String q, Pageable requestPage) {
        Page<QuestionEntity> replyPage=questionReplyResponsitory.findReplyQuestionByUserIdStrKey(userid,"%"+q+"%",requestPage);
        return replyPage;
    }

    @Override
    public List<QuestionMini> findTopQuestion(String type) {
        if(type!=null){
            //关注最高前5个
            if(type.equals("FOLLOWS")){
                Sort order=new Sort(Sort.Direction.DESC,"FollowCount");
                Pageable pageable=new PageRequest(0,5,order);
                Page<QuestionEntity> qmPage=super.findByStrLike("followCount",null,pageable);
                List<QuestionMini> questionMinisList=qmPage.getContent().stream().map(p -> questionMapper.questionEntityToQuestionMini(p)).collect(Collectors.toList());
                return  questionMinisList;
                //点赞最高前5个
            }else if(type.equals("LIKES")){
                String sql = "select q.id,q.title from question q where q.id in(select a.id from (select questionid as id, sum(qr.likeCount) from questionreply qr  group by questionid order by 2 desc limit 0,5)a)";
                SqlRowSet resultSet =  jdbcTemplate.queryForRowSet(sql);
                List<QuestionMini> questionMinisList=new ArrayList<>();
                while (resultSet.next()){
                    QuestionMini questionMini=new QuestionMini();
                    questionMini.setId(resultSet.getInt("id"));
                    questionMini.setName(resultSet.getString("title"));
                    questionMinisList.add(questionMini);
                }
                return questionMinisList;
               //参与度最高前5个
            }else if(type.equals("REPLIES")){
                String sql = "select q.id,q.title from question q where q.id in(select a.id from (select count(1) ,questionid as id from questionreply group by questionid order by 1 desc limit 0,5)a)";
                SqlRowSet resultSet =  jdbcTemplate.queryForRowSet(sql);
                List<QuestionMini> questionMinisList=new ArrayList<>();
                while (resultSet.next()){
                    QuestionMini questionMini=new QuestionMini();
                    questionMini.setId(resultSet.getInt("id"));
                    questionMini.setName(resultSet.getString("title"));
                    questionMinisList.add(questionMini);
                }
                return questionMinisList;
            }
        }
        return null;
    }

    @Override
    public QuestionEntity findOneQuestion(Integer id) {
        return  questionResponsitory.findOne(id);
    }

    @Override
    public int selectReplyNum(Integer id) {
        int replyNum=questionReplyResponsitory.selectReplyNum(id);
        return replyNum;
    }

    @Override
    public Integer createQuestion(QuestionRequest question) {
        QuestionEntity questionEntity=questionMapper.questionRequestToQuestionEntity(question);
        return questionResponsitory.save(questionEntity).getId();
    }

    @Override
    public Integer createQuestion4Topic(Integer topicid,QuestionRequest question) {
        QuestionEntity questionEntity=questionMapper.questionRequestToQuestionEntityTopic(topicid,question);
        return questionResponsitory.save(questionEntity).getId();
    }

    @Override
    public void followQuestion(Integer id) {
        Integer userId= SecurityUtils.getCurrentUserLogin().getId();
        if(userId!=null){
            MessageUtils.createMessage(id,MessageEventTypeEnum.FOCUS, MessageMsgTypeEnum.FocusQuestions);
            QuestionEntity questionEntity=questionResponsitory.findOne(id);
            questionEntity.setFollowCount(questionEntity.getFollowCount()+1);//关注加1
            questionResponsitory.saveAndFlush(questionEntity);
        }
    }

    @Override
    public void unFollowQuestion(Integer id) {
        Integer userId= SecurityUtils.getCurrentUserLogin().getId();
        if(userId!=null){
            MessageUtils.cancelEventAction(id, MessageEventTypeEnum.FOCUS, MessageMsgTypeEnum.FocusQuestions);
            QuestionEntity questionEntity=questionResponsitory.findOne(id);
            questionEntity.setFollowCount(questionEntity.getFollowCount()-1);//关注减1
            questionResponsitory.saveAndFlush(questionEntity);
        }
    }

    @Override
    public void updateQuestion(Integer id, QuestionRequest question) {
        //先判断是否有数据
        QuestionEntity questionEntity=questionResponsitory.findOne(id);
        if(questionEntity!=null){
            QuestionEntity questionEntity2=questionMapper.questionRequestToQuestionEntityEdit(question);
            questionEntity.setTitle(questionEntity2.getTitle());
            questionEntity.setDomainId(questionEntity2.getDomainId());
            questionEntity.setIntroduction(questionEntity2.getIntroduction());
            questionEntity.setCreateTime(questionEntity2.getCreateTime());
            questionResponsitory.saveAndFlush(questionEntity);
        }
    }

    @Override
    public void deleteQuestion(Integer id) {
        questionResponsitory.delete(id);//删除问题
        //删除消息
//        Boolean isFollow = MessageUtils.isMessageExist(id, MessageEventTypeEnum.FOCUS, MessageMsgTypeEnum.FocusQuestions);
//        if(isFollow){
//            MessageUtils.cancelEventAction(id, MessageEventTypeEnum.FOCUS, MessageMsgTypeEnum.FocusQuestions);
//        }
//        questionReplyResponsitory.deleteQuestionById(id);//删除问题对应的回答
    }

    @Override//获取我关注的问题列表
    public PageInfo<QuestionBase> findMyCollectionsList(Pageable pr) {
        Page<PortalMessageEntity> rpPage = MessageUtils.getPageMessage(MessageEventTypeEnum.FOCUS, MessageMsgTypeEnum.FocusQuestions,pr);
            List<QuestionSummary> focusQuestion = rpPage.getContent().stream().map(p -> questionMapper.messageToSummary(p)).collect(Collectors.toList());
            PageInfo<QuestionBase> pageInfo=new PageInfo<>(new PageImpl(focusQuestion, pr, rpPage.getTotalElements()));
                return pageInfo;
    }

    @Override
    public PageInfo<QuestionBase> findMyCollectionsListByStrKey(List<Integer> focusQuestionIds,String q, Pageable pr) {
        Page<QuestionEntity> entityPage=questionResponsitory.findMyCollectionsListByStrKey(focusQuestionIds,"%"+q+"%",pr);
        List<QuestionSummary> focusQuestion = entityPage.getContent().stream().map(p -> questionMapper.questionEntityToQuestionSummary(p)).collect(Collectors.toList());
        PageInfo<QuestionBase> pageInfo=new PageInfo<>(new PageImpl(focusQuestion, pr, entityPage.getTotalElements()));
        return pageInfo;
    }

    @Override
    public Boolean isExist(Integer id) {
        return questionResponsitory.exists(id);
    }
    //问题详情
    @Override
    public QuestionDetails selectQuestionDetails(Integer id) {
        //查询问题详情
        QuestionEntity questionEntity=questionResponsitory.findOne(id);
        if(questionEntity==null){
            return null;
        }
        QuestionDetails questionDetails=questionMapper.questionEntityToQuestionDetails(questionEntity);
        return questionDetails;
    }

    @Override
    public  List<QuestionEntity> findQuestionByUserId(Integer userId) {
        return questionResponsitory.findQuestionByUserId(userId);
    }
//获取邀请回答的问题列表
    @Override
    public PageInfo<QuestionSummary> findMyInviteQuestion(Integer userId, String q, Pageable requestPage) {
        List<Integer> questionIds=messageResponsitory.getMyMessageEventIds(userId,MessageEventTypeEnum.INVITATION.getValue(),MessageMsgTypeEnum.InvitationAnswerQuestions.getMsgType());
        if(questionIds==null||questionIds.size()==0){
            return null;
        }else {
            Page<QuestionEntity> entityPage=questionResponsitory.findInviteQuestionByIds(questionIds,requestPage);
            List<QuestionSummary> focusQuestion = entityPage.getContent().stream().map(p -> questionMapper.questionEntityToQuestionSummary(p)).collect(Collectors.toList());
            PageInfo<QuestionSummary> pageInfo=new PageInfo<>(new PageImpl(focusQuestion, requestPage, entityPage.getTotalElements()));
            return pageInfo;
        }
    }
}
