package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.entity.lgsq.PortalMessageRelEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by liaoyoule on 2017/4/24.
 */
public interface MessageRelService {
    // 单个保存
    PortalMessageRelEntity save(PortalMessageRelEntity portalMessageRelEntity);
    // 批量插入
    List<PortalMessageRelEntity> save(List<PortalMessageRelEntity> portalMessageRelList);
    /**
     * 修改查看状态
     */
    void changeFouceLookStatus(PortalMessageRelEntity portalMessageRelEntity);

    /**
     * 取消关注能人时删除能人相关的文章或问题
     */
    void deleteMsgRelByMid(Integer messageId);

    /**
     *  Article
     *  脏数据能人的文章
     *  清除能人发布文章的废弃的MessageRel脏数据
     */
    void deleteArticleListWasteMessageRel(Integer messageId, Integer userId, Date createTime);

    /**
     *  Question
     * 脏数据能人的问题
     * 清除能人提出的问题废弃的MessageRel脏数据
      */
    void deleteQuesListWasteMessageRel(Integer messageId, Integer userId, Date createTime);

    /**
     *  questionReply
     * 清除关注的问题的回答的废弃的MessageRel脏数据
      */

    void deleteReplyQuesListWasteMessageRel(Integer messageId, Integer questionId, Date createTime);

    /**
     * 修改一对多的某类消息的查看状态
     */
    void changeOToMLookStatusByType(Integer loginUserId,Integer eventType);
}
