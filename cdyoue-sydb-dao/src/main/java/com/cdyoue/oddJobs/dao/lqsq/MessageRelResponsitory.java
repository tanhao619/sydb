package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.PortalCommonPageDetailEntity;
import com.cdyoue.oddJobs.entity.lgsq.PortalMessageRelEntity;
import com.cdyoue.oddJobs.entity.lgsq.QuestionEntity;
import com.cdyoue.oddJobs.entity.lgsq.QuestionreplyEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/3.
 */
public interface MessageRelResponsitory extends  JpaCustomResponsitory<PortalMessageRelEntity,Integer> {

    // 通过messageId 跟 type查询PortalMessageRelEntity
    @Query(value = "SELECT * FROM lg_portal_message_rel WHERE messageId = ?1 AND type = ?2",nativeQuery = true)
    List<PortalMessageRelEntity> findMsgRelByMessageIdAndType(Integer messageId, Integer type);

    // 通过messageId 、relEventId跟 type查询PortalMessageRelEntity
    @Query(value = "SELECT * FROM lg_portal_message_rel WHERE messageId = ?1 AND relEventId = ?2 AND type = ?3",nativeQuery = true)
    PortalMessageRelEntity findMsgRelByMsgIdAndTypeAndrelEventId(Integer messageId, Integer relEventId,Integer type);

    /**
     * 取消关注能人时删除能人相关的文章或问题
     */
    @Modifying
    @Query(value = "DELETE FROM lg_portal_message_rel WHERE messageId = ?1",nativeQuery = true)
    void deleteMsgRelByMid(Integer messageId);

    // 清除能人发布的文章废弃的MessageRel脏数据
    @Query(value = "DELETE FROM lg_portal_message_rel WHERE messageId = ?1 AND type = 1  AND relEventId NOT in \n" +
            "(select id from lg_portal_common_page_detail WHERE createBy = ?2 AND pageType=3 AND createTime >= ?3 AND status = 1)",nativeQuery = true)
    @Modifying
    void deleteArticleListWasteMessageRel(Integer messageId, Integer userId, Date createTime);



    // 清除能人提出的问题废弃的MessageRel脏数据
    @Query(value = "DELETE FROM lg_portal_message_rel WHERE messageId = ?1 AND type = 2  AND relEventId NOT in\n" +
            "            (SELECT id FROM question WHERE userId = ?2 AND CreateTime >= ?3)",nativeQuery = true)
    @Modifying
    void deleteQuesListWasteMessageRel(Integer messageId, Integer userId, Date createTime);


    // 清除关注的问题的回答的废弃的MessageRel脏数据
    @Query(value = "DELETE FROM lg_portal_message_rel WHERE messageId = ?1 AND type = 3 AND relEventId NOT in(\n" +
            " SELECT id FROM questionreply WHERE QuestionId = ?2 AND CreateTime >= ?3 \n" +
            ")",nativeQuery = true)
    @Modifying
    void deleteReplyQuesListWasteMessageRel(Integer messageId, Integer questionId, Date createTime);

    // 查找能人新添加的问题的ids
    @Query("SELECT p FROM QuestionEntity p WHERE p.userId = ?2 AND p.createTime >= ?3 AND p.id not IN(\n" +
            "\n" +
            "SELECT mr.relEventId FROM PortalMessageRelEntity mr WHERE mr.messageId = ?1 AND mr.type = 2\n" +
            ")\n")
    List<QuestionEntity> findLostQuesIds(Integer messageId, Integer userId, Date createTime);


    // 查找能人新添加的文章的ids
    @Query("select p from PortalCommonPageDetailEntity p WHERE p.createBy = ?2 AND p.pageType= 3 AND p.createTime >= ?3 AND p.status = 1 AND p.id not in(\n" +
            "\tSELECT mr.relEventId FROM PortalMessageRelEntity mr WHERE mr.messageId = ?1 AND mr.type = 1 \n" +
            ")")
    List<PortalCommonPageDetailEntity> findLostArtcleIds(Integer messageId, Integer userId, Date createTime);

    // 查找问题新回答的ids
    @Query("\n" +
            "SELECT p FROM QuestionreplyEntity p WHERE p.questionEntity.id =  ?2 AND  p.createTime >= ?3 AND p.id not in(\n" +
            "\t\n" +
            " SELECT mr.relEventId FROM PortalMessageRelEntity mr WHERE mr.messageId = ?1 AND mr.type = 3\n" +
            ")")
    List<QuestionreplyEntity> findLostQuesReplycleIds(Integer messageId, Integer questionId, Date createTime);

    @Modifying
    @Query(value = "UPDATE lg_portal_message_rel  SET lookStatus = 1 WHERE id in (\n" +
            "select id  from (\n" +
            "\tSELECT id  FROM lg_portal_message_rel WHERE  messageId in(\n" +
            "\t\t\tSELECT id FROM lg_portal_message  WHERE (receiverId = ?1 or (receiverId IS NULL AND  operaId = ?1)) AND eventType = ?2\n" +
            "\t) AND lookStatus = 0 ) rel\n" +
            ")",nativeQuery = true)
    void changeOToMLookStatusByType(Integer loginUserId,Integer eventType);
    // 获取问题关注数
    @Query(value = "SELECT COUNT(DISTINCT operaId) num FROM lg_portal_message WHERE   eventType = ?2 AND msgType = ?3 AND eventId = ?1",nativeQuery = true)
    Integer getQuesFoucsNum(Integer quesId, Integer eventType, Integer msgType);
}
