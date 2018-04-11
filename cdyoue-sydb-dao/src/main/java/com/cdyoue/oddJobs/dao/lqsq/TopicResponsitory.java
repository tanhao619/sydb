package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.PortalTopicEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2017/5/2.
 */
public interface TopicResponsitory extends  JpaCustomResponsitory<PortalTopicEntity,Integer> {
    @Query(nativeQuery=true,value = "SELECT lt.id,lt.name,lt.follow,lt.createBy,lt.createTime,lt.coverImg,lt.info,lt.viewCount FROM lg_portal_topic lt ORDER BY lt.follow DESC limit 5")
    List<PortalTopicEntity> getHotTopics();

    @Modifying
    @Query("UPDATE PortalTopicEntity lg SET lg.follow=lg.follow+1 WHERE lg.id=?1")
    void followTopicById(Integer id);

    @Modifying
    @Query("UPDATE PortalTopicEntity lg SET lg.follow=lg.follow-1 WHERE lg.id=?1")
    void unFollowTopicById(Integer id);

    @Query("SELECT t FROM PortalTopicEntity t ,PortalMessageEntity m,UserEntity u WHERE " +
            "m.opera.id = u.id AND m.eventId = t.id AND m.msgType = ?2 AND m.eventType = ?3 and u.id = ?1 AND t.name like ?4")
    Page<PortalTopicEntity> getMyTopics(Integer uid, int msgid, int eid,String q,  Pageable page);

    @Query("select t.follow from  PortalTopicEntity t where t.id=?1")
    Integer getFollowById(Integer id);

    @Modifying
    @Query("UPDATE PortalTopicEntity lg SET lg.viewCount=lg.viewCount+1 WHERE lg.id=?1")
    void addViewcount(Integer id);

    @Query("select u.userName from  UserEntity u where u.id=?1")
    String getUserName(Integer createBy);
}
