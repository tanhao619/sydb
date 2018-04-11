package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.lgfc.Topic;
import com.cdyoue.oddJobs.dto.lgfc.TopicMini;
import com.cdyoue.oddJobs.dto.lgfc.TopicRequest;
import com.cdyoue.oddJobs.entity.lgsq.PortalTopicEntity;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.PageRequest;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Administrator on 2017/5/2.
 */
public interface LgTopicService {
    /**
     * 关键字搜索 获取话题列表
     * @param q
     * @param pr
     * @return
     */
     PageInfo<Topic> getMyReqiures(String q, PageRequest pr);

    /**
     * 根据ID获取话题详情
     * @param id
     * @return
     */
    TopicMini getTopicDetailById(Integer id);

    /**
     * 根据话题ID查找话题
     * @param id
     * @return
     */
    PortalTopicEntity findOne(Integer id);

    /**
     * 获取热门话题
     * @return
     */
    List<TopicMini> getHotTopics();

    /**
     * 关注话题
     * @param id
     */
    void followTopicById(Integer id);

    /**
     * 取消关注话题
     * @param id
     */
    void unFollowTopicById(Integer id);

    /**
     * 更新话题
     * @param tid
     * @param topicRequest
     */
    void updateTopic(Integer tid, TopicRequest topicRequest);

    /**
     * 删除话题
     * @param id
     */
    void deleteTopicById(Integer id);

    /**
     * 获取我关注的话题
     * @param q
     * @param pr
     * @param uid
     * @param eid
     * @return
     */
    PageInfo<Topic> getMyTopics(String q, PageRequest pr,Integer uid ,int msgid, int eid);

    /**
     * 取消关注在message表中删除标志
     * @param uid
     * @param msgtype
     * @param etype
     * @param id
     */
    void delMark(Integer uid, int msgtype, int etype, Integer id);

    /**
     * 关注在message表中增加标志
     * @param uid
     * @param msgtype
     * @param etype
     * @param id
     */
    void addMark(Integer uid, int msgtype, int etype, Integer id, Timestamp creatTime);

    /**
     * 创建话题
     * @param topicRequest
     * @return
     */
    Integer save(TopicRequest topicRequest);

    /**
     * 获取关注数量
     * @param id
     * @return
     */
    Integer getFollowById(Integer id);
}
