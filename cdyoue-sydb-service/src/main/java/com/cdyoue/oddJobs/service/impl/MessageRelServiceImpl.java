package com.cdyoue.oddJobs.service.impl;


import com.cdyoue.oddJobs.dao.lqsq.MessageRelResponsitory;
import com.cdyoue.oddJobs.entity.lgsq.PortalMessageRelEntity;
import com.cdyoue.oddJobs.service.MessageRelService;
import com.cdyoue.oddJobs.service.support.ServiceSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.List;

/**
 * Created by liaoyoule on 2017/4/24.
 */
@Service
public class MessageRelServiceImpl extends ServiceSupport<PortalMessageRelEntity> implements MessageRelService {
    @Autowired
    private MessageRelResponsitory messageRelResponsitory;

    /**
     * 单个添加
     * @param portalMessageRelEntity
     * @return
     */
    @Override
    @Transactional
    public PortalMessageRelEntity save(PortalMessageRelEntity portalMessageRelEntity) {
        return messageRelResponsitory.save(portalMessageRelEntity);
    }


    /**
     * 批量插入
     * @param portalMessageRelList
     * @return
     */
    @Override
    @Transactional
    public List<PortalMessageRelEntity> save(List<PortalMessageRelEntity> portalMessageRelList) {
        return messageRelResponsitory.save(portalMessageRelList);
    }


    /**
     * 修改查看状态
     * @param portalMessageRelEntity
     */
    @Override
    @Transactional
    public void changeFouceLookStatus(PortalMessageRelEntity portalMessageRelEntity) {
        messageRelResponsitory.save(portalMessageRelEntity);
    }
    /**
     * 取消关注能人时删除能人相关的文章或问题
     */
    @Override
    @Transactional
    public void deleteMsgRelByMid(Integer messageId) {
        messageRelResponsitory.deleteMsgRelByMid(messageId);
    }

    /**
     *  清除能人发布文章的废弃的MessageRel脏数据
     */
    @Override
    @Transactional
    public void deleteArticleListWasteMessageRel(Integer messageId, Integer userId, Date createTime) {
        messageRelResponsitory.deleteArticleListWasteMessageRel(messageId,userId,createTime);
    }

    /**
     * 脏数据能人的问题
     * 清除能人提出的问题废弃的MessageRel脏数据
     */
    @Override
    @Transactional
    public void deleteQuesListWasteMessageRel(Integer messageId, Integer userId, Date createTime) {
        messageRelResponsitory.deleteQuesListWasteMessageRel(messageId,userId,createTime);
    }

    @Override
    @Transactional
    public void deleteReplyQuesListWasteMessageRel(Integer messageId, Integer questionId, Date createTime) {
        messageRelResponsitory.deleteReplyQuesListWasteMessageRel(messageId,questionId,createTime);
    }

    @Override
    @Transactional
    public void changeOToMLookStatusByType(Integer loginUserId, Integer eventType) {
        messageRelResponsitory.changeOToMLookStatusByType(loginUserId,eventType);
    }

    @Override
    public Class getJpaRepositoryClazz() {
        return MessageRelResponsitory.class;
    }
}
