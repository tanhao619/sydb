package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.PortalMessageEntity;
import com.cdyoue.oddJobs.entity.lgsq.PortalMessageRelEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2017/5/3.
 */
public interface MessageResponsitory extends  JpaCustomResponsitory<PortalMessageEntity,Integer> {
    @Modifying
    @Query("delete from PortalMessageEntity p where  p.eventId = ?1 and p.opera.id= ?2 and p.eventType =?3 and p.msgType =?4")
    void deleteByUserIdAndEventId(Integer id, Integer uId, int eType, int mType);

    //获取eventIdList
//    @Query("select from PortalMessageEntity p where  p.opera.id= ?1 and p.eventType =?2 and p.msgType =?3")
//    List<PortalMessageEntity> findList(String uId, int eType, int mType);

    @Modifying
    @Query("DELETE FROM PortalMessageEntity WHERE opera.id=?1 AND eventId=?4 AND msgType=?2 AND eventType=?3")
    void delMark(Integer uid, int msgtype, int etype, Integer id);

    @Query("select p FROM PortalMessageEntity p WHERE p.opera.id=?1 AND p.eventId=?4 AND p.msgType=?2 AND p.eventType=?3")
    PortalMessageEntity findIt(Integer uid, int msgtype, int etype, Integer id);

    @Modifying
    @Query("DELETE FROM PortalMessageEntity WHERE opera.id=?1 AND eventId=?2 AND eventType=?3 AND msgType=?4")
    void cancelCollection(Integer operaId, int eventId, int eventType, Integer msgType);

    @Modifying
    @Query("DELETE FROM PortalMessageEntity WHERE opera.id=?1 AND eventId=?2 AND eventType=?3 AND msgType=?4")
    void cancelThumbUp(Integer operaId, int eventId, int eventType, Integer msgType);

    @Modifying
    @Query("DELETE FROM PortalMessageEntity WHERE opera.id=?1 AND eventId=?2 AND eventType=?3 AND msgType=?4")
    void cancelEventAction(Integer operaId, int eventId, int eventType, Integer msgType);

    @Modifying
    @Query("DELETE FROM PortalMessageEntity WHERE eventId=?1 AND eventType=?2 AND msgType=?3")
    void deleteAdminRelatedMessage( Integer eventId, Integer eventType, Integer msgType);

    @Query("select p FROM PortalMessageEntity p WHERE p.opera.id=?1 AND p.eventId=?2 AND p.eventType=?3 AND p.msgType=?4")
    PortalMessageEntity isMessageExist(int  operaId, int eventId, int eventType, int msgType);

    @Query("select p FROM PortalMessageEntity p WHERE p.receiver.id=?1 and p.opera.id=?2 AND p.eventId=?3 AND p.eventType=?4 AND p.msgType=?5")
    PortalMessageEntity isMessageExist(int receiverId,int  operaId, int eventId, int eventType, int msgType);

    @Query("select p.eventId FROM PortalMessageEntity p WHERE p.opera.id=?1  AND p.eventType=?2 AND p.msgType=?3")
    List<Integer> getMessageEventIds(int  operaId, int eventType, int msgType);

    @Query("select p.eventId FROM PortalMessageEntity p WHERE p.receiver.id=?1  AND p.eventType=?2 AND p.msgType=?3")
    List<Integer> getMyMessageEventIds(int  receiverId, int eventType, int msgType);

    @Query("select p FROM PortalMessageEntity p WHERE p.opera.id=?1  AND p.eventType=?2 AND p.msgType=?3")
    Page<PortalMessageEntity> getPageMessage(int  operaId, int eventType, int msgType, Pageable pr);

    @Query("SELECT p FROM PortalMessageEntity p WHERE p.receiver.id=?1 or (p.receiver.id IS NULL AND p.opera.id = ?1) and p.eventType in(1,2,4,8) ")
    Page<PortalMessageEntity> findLoginUserMessages(Integer loginUserId, Pageable pageable);

    @Query(value = "SELECT p FROM PortalMessageEntity p WHERE (p.receiver.id=?1 or (p.receiver.id IS NULL AND p.opera.id = ?1) and p.eventType in(1,2,4,8)) or (p.eventId = -1 and p.eventType = 5 and p.msgType =1 AND p.createTime >= (SELECT usr.createTime FROM UserEntity usr WHERE usr.id = ?1))")
    Page<PortalMessageEntity> findLoginUserMessagesMy(Integer loginUserId, Pageable pageable);

    @Query("SELECT p FROM PortalMessageEntity p WHERE (p.receiver.id=?1 or (p.receiver.id IS NULL AND p.opera.id = ?1)) AND p.eventType =?2 ORDER BY p.createTime DESC")
    Page<PortalMessageEntity> findLoginUserMessagesByEventType(Integer loginUserId, Integer eventType, Pageable pageable);
    // 需求
    @Query("SELECT p FROM PortalMessageEntity p  WHERE (p.receiver.id = ?1 or (p.receiver.id IS NULL AND  p.opera.id = ?1)) AND (p.eventType = 4 or p.eventType = 8) ORDER BY p.createTime DESC")
    Page<PortalMessageEntity> findLoginUserRequirementMessagesByEventType(Integer loginUserId, Pageable pageable);

    @Query("select p.opera.id from PortalMessageEntity p where p.eventId=?1 and p.eventType=?2 and p.msgType=?3")
    List<Integer> findUserCountByEventId(int eventId,int eventType, int msgType);

     // 官方数据 指定 eventId = -1 为官方数据
    @Query("select p FROM PortalMessageEntity p WHERE p.eventId = -1 and p.eventType = 5 and p.msgType =1 AND p.createTime >= (SELECT usr.createTime FROM UserEntity usr WHERE usr.id = ?1) ORDER BY p.createTime DESC")
    Page<PortalMessageEntity> findOfficialMessages(Integer loginUserId,Pageable pageable);

    // 通过eventType 跟msgType获取 message
    @Query("select p from PortalMessageEntity p where (p.receiver.id=?1 or (p.receiver.id IS NULL AND p.opera.id = ?1)) AND p.eventType=?2 and p.msgType=?3")
    List<PortalMessageEntity> findMessageByType(Integer loginUserId, Integer eventType, Integer msgType,Pageable pageable);

    //获取message 数目
    @Query("SELECT lm FROM PortalMessageEntity lm WHERE lm.eventId = ?1 AND lm.eventType = ?2 AND lm.msgType = ?3")
    List<PortalMessageEntity> findMyAttentionNum(Integer uId,int eventType,int msgType);

    //获取 所有 数目
    @Query("select p FROM PortalMessageEntity p WHERE p.eventId=?1 AND p.eventType=?2 AND p.msgType=?3")
    List<PortalMessageEntity> isMessageExist(Integer eventId, Integer eventType, Integer msgType);

    @Query(value = "select msgType, count(id) as unreadCount from lg_portal_message where lookStatus = 0 GROUP BY msgType", nativeQuery = true)
    Object[] getUnreadAppMessage();

    @Query("SELECT lm FROM PortalMessageEntity lm WHERE lm.msgType = ?1 AND lm.lookStatus = 0")
    Page<PortalMessageEntity> findByMsgType(Integer msgType, Pageable pageRequest);

    @Modifying
    @Query("update PortalMessageEntity pme set pme.lookStatus = 1 WHERE pme.msgType = ?1")
    int allMsgStatusReadByType(Integer msgType);

    /**
     * 修改某个消息的查看状态
     */
    @Modifying
    @Query(value = "UPDATE  lg_portal_message SET lookStatus = 1 WHERE id = ?1",nativeQuery = true)
    void changeLookStatusByMid(Integer messageId);

    /**
     * 修改某类消息的查看状态
     */
    @Modifying
    @Query(value = "UPDATE lg_portal_message  SET lookStatus = 1 WHERE id in(\n" +
            "\tSELECT id FROM (\n" +
            "\t\tSELECT id FROM lg_portal_message  WHERE (receiverId = ?1 or (receiverId IS NULL AND  operaId = ?1)) AND eventType = ?2 AND lookStatus = 0\n" +
            "\t) msg\n" +
            ")\n",nativeQuery = true)
    void changeLookStatusByType(Integer loginUserId,Integer eventType);

    // 统计未读的消息
    @Query(value = "SELECT COUNT(id) FROM (\n" +
            "\n" +
            "\n" +
            "SELECT id FROM lg_portal_message  WHERE (receiverId = ?1 or (receiverId IS NULL AND  operaId = ?1)) AND eventType in (1,2,4,8) AND lookStatus = 0\n" +
            "UNION ALL\n" +
            "SELECT id FROM lg_portal_message_rel  WHERE messageId in (\n" +
            "SELECT id FROM lg_portal_message   WHERE (receiverId = ?1 or (receiverId IS NULL AND  operaId = ?1)) AND eventType = 3\n" +
            ") AND lookStatus = 0 UNION ALL\n" +
            "SELECT id FROM lg_portal_message WHERE eventType = 5 AND msgType = 1 AND eventId = -1 AND lookStatus = 0 \n" +
            "\n" +
            ") mg", nativeQuery = true)
    Integer countUnreadMsgNum(Integer loginUserId);

    // total消息
    @Query(value = "SELECT COUNT(id) FROM (\n" +
            "\n" +
            "\n" +
            "SELECT id FROM lg_portal_message  WHERE (receiverId = ?1 or (receiverId IS NULL AND  operaId = ?1)) AND eventType in (1,2,4,8)\n" +
            "UNION ALL\n" +
            "SELECT id FROM lg_portal_message_rel  WHERE messageId in (\n" +
            "SELECT id FROM lg_portal_message   WHERE (receiverId = ?1 or (receiverId IS NULL AND  operaId = ?1)) AND eventType = 3\n" +
            ") UNION ALL\n" +
            "SELECT id FROM lg_portal_message WHERE eventType = 5 AND msgType = 1 AND eventId = -1 \n" +
            "\n" +
            ") mg", nativeQuery = true)
    Integer countTotalMsgNum(Integer loginUserId);

    // 分类统计未读消息
    @Query(value = "SELECT COUNT(id) FROM (\n" +
            "\n" +
            "\n" +
            "SELECT id FROM lg_portal_message  WHERE (receiverId = ?1 or (receiverId IS NULL AND  operaId = ?1)) AND eventType = ?2 AND lookStatus = 0  AND eventType not in (3,5)\n" +
            "UNION ALL\n" +
            "SELECT id FROM lg_portal_message_rel  WHERE messageId in (\n" +
            "SELECT id FROM lg_portal_message   WHERE (receiverId = ?1 or (receiverId IS NULL AND  operaId = ?1)) AND eventType = ?2 AND eventType not in(5)\n" +
            ") AND lookStatus = 0 UNION ALL\n" +
            "SELECT id FROM lg_portal_message WHERE eventType = 5 AND msgType = 1 AND eventId = -1 AND lookStatus = 0\n" +
            "\n" +
            ") mg\n", nativeQuery = true)
    Integer countUnreadNumByType(Integer loginUserId,Integer eventType);

    // 分类total消息
    // app不要eventType为8的
    @Query(value = "SELECT COUNT(id) FROM (\n" +
            "\n" +
            "\n" +
            "SELECT id FROM lg_portal_message  WHERE (receiverId = ?1 or (receiverId IS NULL AND  operaId = ?1)) AND eventType = ?2  AND eventType not in (3,5,8)\n" +
            "UNION ALL\n" +
            "SELECT id FROM lg_portal_message_rel  WHERE messageId in (\n" +
            "SELECT id FROM lg_portal_message   WHERE (receiverId = ?1 or (receiverId IS NULL AND  operaId = ?1)) AND eventType = ?2 AND eventType not in(5)\n" +
            ") UNION ALL\n" +
            "SELECT id FROM lg_portal_message WHERE eventType = 5 AND msgType = 1 AND eventId = -1\n" +
            "\n" +
            ") mg", nativeQuery = true)
    Integer countTotalMsgNumByType(Integer loginUserId,Integer eventType);

 /*   @Query(value = "SELECT * FROM lg_portal_message_rel WHERE messageId in(\n" +
            "\tSELECT id FROM lg_portal_message  WHERE receiverId IS NULL AND  operaId = ?1 AND eventType = 3 AND msgType in (1,2)\n" +
            ") \n-- #pageable\n",nativeQuery = true)*/
    @Query(value = "SELECT mr FROM PortalMessageRelEntity mr WHERE mr.messageId in(\n" +
            "\tSELECT m.id FROM PortalMessageEntity m  WHERE m.receiver.id IS NULL AND  m.opera.id  = ?1 AND m.eventType = 3 AND m.msgType in (1,2)\n" +
            ")")
    Page<PortalMessageRelEntity> findLoginUserFouceMessageRels(Integer loginUserId, Pageable pageable);

    @Query(value = "SELECT * FROM lg_portal_message  WHERE receiverId IS NULL AND  operaId = ?1 AND eventType = 3 AND msgType in (1,2)",nativeQuery = true)
    List<PortalMessageEntity> findLoginUserFouceMessages(Integer loginUserId);

    @Query("select p from PortalMessageEntity p where p.eventId=-1 and p.eventType = 5 and p.msgType =1 and p.info like concat('%',?1,'%')")
    Page<PortalMessageEntity> findOfficialMessagesForManage(String q, Pageable pr);
}
