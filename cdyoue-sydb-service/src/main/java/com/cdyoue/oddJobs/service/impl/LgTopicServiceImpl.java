package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.MessageResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.QuestionResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.TopicResponsitory;
import com.cdyoue.oddJobs.dto.lgfc.*;
import com.cdyoue.oddJobs.entity.lgsq.PortalMessageEntity;
import com.cdyoue.oddJobs.entity.lgsq.PortalTopicEntity;
import com.cdyoue.oddJobs.entity.lgsq.QuestionEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.LgTopicMapper;
import com.cdyoue.oddJobs.service.LgTopicService;
import com.cdyoue.oddJobs.service.support.ServiceSupport;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2017/5/2.
 */
@Transactional
@Service
public class LgTopicServiceImpl extends ServiceSupport<PortalTopicEntity> implements LgTopicService {

    @Autowired
    private QuestionResponsitory questionResponsitory;

    @Autowired
    private TopicResponsitory topicResponsitory;
    @Autowired
    private MessageResponsitory messageResponsitory;
    @Autowired
    private LgTopicMapper lgTopicMapper;
    @Override
    public PageInfo<Topic> getMyReqiures(String q, PageRequest pr) {
        Page<PortalTopicEntity> tePage = super.findByName(q, pr);
        List<Topic> topics = tePage.getContent().stream().map(p -> lgTopicMapper.portalTopicEntityToTopics(p)).collect(Collectors.toList());
        /*for (Topic topic:topics) {
            Set<QuestionMini> questionMiniSet ;
            Set<QuestionMini> questionEntitySet = topic.getQuestionMinis();
            if (questionEntitySet != null || questionEntitySet.size() > 1){
                for (QuestionEntity questionEntity:questionEntitySet) {
                    questionEntity.setPortalTopicEntity(null);
                }
            } else {
                topic.setQuestionMinis(questionMiniSet);
            }
        }*/
        return new PageInfo<>(new PageImpl(topics, pr, tePage.getTotalElements()));
    }

    @Override
    public TopicMini getTopicDetailById(Integer id) {
        PortalTopicEntity pre = topicResponsitory.findOne(id);
        if (pre == null) {
            throw new NotFoundEntityException();
        }
        TopicMini topic = lgTopicMapper.portalTopicEntityToTopicMini(pre,id);
        topicResponsitory.addViewcount(id);
        return topic;
    }

    @Override
    public PortalTopicEntity findOne(Integer id) {
        return topicResponsitory.findOne(id);
    }

    @Override
    public List<TopicMini> getHotTopics() {
        List<PortalTopicEntity> t = topicResponsitory.getHotTopics();
        List<TopicMini> topics = t.stream().map(p -> LgTopicMapper.portalTopicEntityToTopicMini2(p)).collect(Collectors.toList());
        return topics;
    }

    @Override
    public void followTopicById(Integer id) {
        PortalTopicEntity preFl = topicResponsitory.findOne(id);
        if (preFl == null) {
            throw new NotFoundEntityException();
        }else {
            topicResponsitory.followTopicById(id);
        }
    }

    @Override
    public void unFollowTopicById(Integer id) {
        PortalTopicEntity preFl = topicResponsitory.findOne(id);
        if (preFl == null) {
            throw new NotFoundEntityException();
        }else{
            topicResponsitory.unFollowTopicById(id);
        }
    }

    @Override
    public void updateTopic(Integer tid, TopicRequest topicRequest) {

        PortalTopicEntity portalTopicEntity = topicResponsitory.findOne(tid);
        if (topicRequest.getCoverImg() != null && !topicRequest.getCoverImg().trim().equals("")) {
            portalTopicEntity.setCoverImg(topicRequest.getCoverImg());
        }
        portalTopicEntity.setInfo(topicRequest.getInfo());
        portalTopicEntity.setName(topicRequest.getName());
        portalTopicEntity.setQuestionEntities(null);
        topicResponsitory.save(portalTopicEntity);
        //让问题的ids关联话题
        List<Integer> questionIds = topicRequest.getQuesionIds();
        questionResponsitory.updateTopicidByIds(portalTopicEntity.getId(), questionIds);
    }

    @Override
    public void deleteTopicById(Integer id) {
        PortalTopicEntity preFl = topicResponsitory.findOne(id);
        if (preFl == null) {
            throw new NotFoundEntityException();
        }else {
            topicResponsitory.delete(id);
        }
    }

    @Override
    public PageInfo<Topic> getMyTopics(String q, PageRequest pr, Integer uid ,int msgid, int eid) {
        if(q==null||q.trim().equals("")){
            q="%%";
        }else{
            q="%"+q+"%";
        }
        Page<PortalTopicEntity> tePage = topicResponsitory.getMyTopics(uid,msgid,eid, q,pr);
            List<Topic> topics = tePage.getContent().stream().map(p -> lgTopicMapper.portalTopicEntityToTopic(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(topics, pr, tePage.getTotalElements()));
}

    @Override
    public void delMark(Integer uid, int msgtype, int etype, Integer id) {
//        PortalMessageEntity pme = messageResponsitory.findIt(uid,msgtype,etype,id);
//        if (pme == null) {
//            throw new NotFoundEntityException();
//        }else {
//        }
        messageResponsitory.delMark(uid,msgtype,etype,id);
    }

    @Override
    public void addMark(Integer uid, int msgtype, int etype, Integer id, Timestamp creatTime) {
        PortalMessageEntity portalMessageEntity = new PortalMessageEntity();
//        PortalMessageEntity pme = messageResponsitory.findIt(uid,msgtype,etype,id);
//        if (pme != null) {
//            throw new NotFoundEntityException();
//        }else {
        UserEntity user = new UserEntity();
        user.setId(uid);
        portalMessageEntity.setOpera(user);
        portalMessageEntity.setEventId(id);
        portalMessageEntity.setCreateTime(creatTime);
        portalMessageEntity.setEventType(etype);
        portalMessageEntity.setMsgType(msgtype);
        portalMessageEntity.setLookStatus(false);
        messageResponsitory.save(portalMessageEntity);
        }

    @Override
    public Integer save(TopicRequest topicRequest) {
        PortalTopicEntity portalTopicEntity = new PortalTopicEntity();
        portalTopicEntity.setCoverImg(topicRequest.getCoverImg());
        portalTopicEntity.setInfo(topicRequest.getInfo());
        portalTopicEntity.setName(topicRequest.getName());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        portalTopicEntity.setCreateTime(timestamp);
        portalTopicEntity.setFollow(0);
        portalTopicEntity.setViewCount(0);
        portalTopicEntity.setCreateBy(SecurityUtils.getCurrentUserLogin().getId());

        Set<QuestionEntity> questionEntitySet = new HashSet<>();
        for (Integer questionId: topicRequest.getQuesionIds()) {
            QuestionEntity questionEntity = questionResponsitory.findOne(questionId);
            if (questionEntity != null) {
                questionEntitySet.add(questionEntity);
            }
        }
        portalTopicEntity.setQuestionEntities(questionEntitySet);

        PortalTopicEntity preAf = topicResponsitory.save(portalTopicEntity);
        //让问题的ids关联话题

        //List<Integer> questionIds = topicRequest.getQuesionIds();
        //questionResponsitory.updateTopicidByIds(preAf.getId(), questionIds);
        return preAf.getId();
    }

    @Override
    public Integer getFollowById(Integer id) {
        return topicResponsitory.getFollowById(id);
    }


    @Override
    public Class getJpaRepositoryClazz() {
        return TopicResponsitory.class;
    }
}
