package com.cdyoue.oddJobs.service.impl;


import com.cdyoue.oddJobs.dao.lqsq.MessageRelResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.MessageResponsitory;
import com.cdyoue.oddJobs.dto.common.AppMsgResponse;
import com.cdyoue.oddJobs.dto.common.ResponseDetail;
import com.cdyoue.oddJobs.dto.message.PersonalCenterMessageDto;
import com.cdyoue.oddJobs.dto.message.RespseMessageOffDto;
import com.cdyoue.oddJobs.en.MessageEventTypeEnum;
import com.cdyoue.oddJobs.en.MessageMsgTypeEnum;
import com.cdyoue.oddJobs.entity.lgsq.PortalMessageEntity;
import com.cdyoue.oddJobs.entity.lgsq.PortalMessageRelEntity;
import com.cdyoue.oddJobs.mapper.PortalMessageMapper;
import com.cdyoue.oddJobs.service.MessageService;
import com.cdyoue.oddJobs.service.support.ServiceSupport;
import com.cdyoue.oddJobs.utils.MessageUtils;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liaoyoule on 2017/4/24.
 */
@Service
public class MessageServiceImpl extends ServiceSupport<PortalMessageEntity> implements MessageService {

    @Autowired
    private MessageRelResponsitory messageRelResponsitory;

    @Autowired
    private MessageResponsitory messageResponsitory;

    @Autowired
    private PortalMessageMapper portalMessageMapper;

    @Override
    public PortalMessageEntity save(PortalMessageEntity PortalMessageEntity) {
//        messageResponsitory.findByEventTypeAndMsgTypeAnd
        return messageResponsitory.save(PortalMessageEntity);
    }



    /*
    *取消收藏
    */
    @Transactional
    @Override
    public void cancelCollection(Integer operaId, Integer eventId, Integer eventType, Integer msgType) {
        messageResponsitory.cancelCollection(operaId, eventId, eventType, msgType);
    }

    /*
    *取消点赞
    */
    @Transactional
    @Override
    public void cancelThumbUp(Integer operaId, Integer eventId, Integer eventType, Integer msgType) {
        messageResponsitory.cancelThumbUp(operaId, eventId, eventType, msgType);
    }

    /*
    * 取消事件动作：点赞，收藏，关注
    */
    @Transactional
    @Override
    public void cancelEventAction(Integer operaId, Integer eventId, Integer eventType, Integer msgType) {
        messageResponsitory.cancelEventAction(operaId, eventId, eventType, msgType);
    }

    @Override
    @Transactional
    public void deleteAdminRelatedMessage(Integer eventId, Integer eventType, Integer msgType) {
        messageResponsitory.deleteAdminRelatedMessage(eventId,eventType,msgType);
    }

    /**
     * 判断消息是否存在  没有接收人的消息判断：有点赞、收藏、关注
     */
    @Override
    public PortalMessageEntity isMessageExist(Integer operaId, Integer eventId, Integer eventType, Integer msgType) {
        return messageResponsitory.isMessageExist(operaId, eventId, eventType, msgType);
    }

    /**
     * 判断消息是否存在 有接收人的消息判断
     */
    @Override
    public PortalMessageEntity isMessageExist(Integer receiverId, Integer operaId, Integer eventId, Integer eventType, Integer msgType) {
        return messageResponsitory.isMessageExist(receiverId, operaId, eventId, eventType, msgType);
    }

    /**
     * 判断消息是否存在 针对管理员：即roleId = 2 的用户
     */
    @Override
    public List<PortalMessageEntity> isAdminMessageExist(Integer eventId, Integer eventType, Integer msgType) {
            return messageResponsitory.isMessageExist(eventId, eventType, msgType);
    }

    @Override
    public Page<PortalMessageEntity> getPageMessage(Integer operaId, Integer eventType, Integer msgType, Pageable rqPage) {
        return messageResponsitory.getPageMessage(operaId, eventType, msgType, rqPage);
    }

    @Override
    public List<Integer> getMessageEventIds(Integer operaId, Integer eventType, Integer msgType) {
        return messageResponsitory.getMessageEventIds(operaId, eventType, msgType);
    }

    @Override
    public List<PortalMessageEntity> findAll() {
        return messageResponsitory.findAll();
    }

    @Override
    public Page<PortalMessageEntity> findLoginUserMessages(Integer loginUserId, Pageable pageable) {
        return messageResponsitory.findLoginUserMessages(loginUserId, pageable);
    }

    //根据事件分类、用户id查询 用户message
    @Override
    public Page<PortalMessageEntity> findLoginUserMessagesByEventType(Integer loginUserId, MessageEventTypeEnum eventTypeEnum, Pageable pageable) {
        return messageResponsitory.findLoginUserMessagesByEventType(loginUserId, eventTypeEnum.getValue(), pageable);
    }

    // 官方数据 指定 eventId = -1 为官方数据
    @Override
    public Page<PortalMessageEntity> findOfficialMessages(Integer loginUserId,Pageable pageable) {
        return messageResponsitory.findOfficialMessages(loginUserId,pageable);
    }

    // 通过eventType 跟msgType获取 message

    @Override
    public List<PortalMessageEntity> findMessageByType(Integer loginUserId, MessageEventTypeEnum eventTypeEnum, MessageMsgTypeEnum msgTypeEnum, Pageable pageable) {
        return messageResponsitory.findMessageByType(loginUserId, eventTypeEnum.getValue(), msgTypeEnum.getMsgType(), pageable);
    }

    @Override
    public List<AppMsgResponse> getUnreadAppMessage() {
        List<AppMsgResponse> list = new ArrayList<>();
        //审核
        Integer audit = MessageUtils.countUnreadNumByType(MessageEventTypeEnum.AUDIT);
        AppMsgResponse auditLook = new AppMsgResponse();
        auditLook.setMsgType(MessageEventTypeEnum.AUDIT.getValue());
        auditLook.setUnreadCount(audit);
        list.add(auditLook);
        //关注
        Integer focus = MessageUtils.countUnreadNumByType(MessageEventTypeEnum.FOCUS);
        AppMsgResponse focusLook = new AppMsgResponse();
        focusLook.setMsgType(MessageEventTypeEnum.FOCUS.getValue());
        focusLook.setUnreadCount(focus);
        list.add(focusLook);
        //邀请
        Integer invitation = MessageUtils.countUnreadNumByType(MessageEventTypeEnum.INVITATION);
        AppMsgResponse invitationLook = new AppMsgResponse();
        invitationLook.setMsgType(MessageEventTypeEnum.INVITATION.getValue());
        invitationLook.setUnreadCount(invitation);
        list.add(invitationLook);
        //需求
        Integer requirement = MessageUtils.countUnreadNumByType(MessageEventTypeEnum.REQUIREMENT);
        AppMsgResponse requirementLook = new AppMsgResponse();
        requirementLook.setMsgType(MessageEventTypeEnum.REQUIREMENT.getValue());
        requirementLook.setUnreadCount(requirement);
        list.add(requirementLook);
        //应聘
//        Integer applyfor = MessageUtils.countUnreadNumByType(MessageEventTypeEnum.APPLYFOR);
//        AppMsgResponse applyforLook = new AppMsgResponse();
//        applyforLook.setMsgType(MessageEventTypeEnum.APPLYFOR.getValue());
//        applyforLook.setUnreadCount(applyfor);
//        list.add(applyforLook);

        //官方
        Integer official = MessageUtils.countUnreadNumByType(MessageEventTypeEnum.OFFICIAL);
        AppMsgResponse officialLook = new AppMsgResponse();
        officialLook.setMsgType(MessageEventTypeEnum.OFFICIAL.getValue());
        officialLook.setUnreadCount(official);
        list.add(officialLook);
        return list;
    }

    @Override
    public PageInfo<ResponseDetail> getUnreadMsgSummary(Integer msgType, PageRequest pageRequest) {
        Pageable pageable = new PageRequest(pageRequest.getPageNumber(),pageRequest.getPageSize(),pageRequest.getSort());
        PageInfo<PersonalCenterMessageDto> pageInfoEntity = null;
        if (msgType == MessageEventTypeEnum.FOCUS.getValue()) {
            pageInfoEntity = MessageUtils.getMessageByeventType(MessageEventTypeEnum.FOCUS, pageRequest);
        } else if (msgType == MessageEventTypeEnum.AUDIT.getValue()) {
            pageInfoEntity = MessageUtils.getMessageByeventType(MessageEventTypeEnum.AUDIT, pageRequest);
        } else if (msgType == MessageEventTypeEnum.INVITATION.getValue()) {
            pageInfoEntity = MessageUtils.getMessageByeventType(MessageEventTypeEnum.INVITATION, pageRequest);
        } else if (msgType == MessageEventTypeEnum.REQUIREMENT.getValue()) {
            pageInfoEntity = MessageUtils.getMessageByeventType(MessageEventTypeEnum.REQUIREMENT, pageRequest);
        } else if (msgType == MessageEventTypeEnum.OFFICIAL.getValue()) {
            pageInfoEntity = MessageUtils.getMessageByeventType(MessageEventTypeEnum.OFFICIAL, pageRequest);
        }
        List<ResponseDetail> responseDetails = new ArrayList<>();
        if (pageInfoEntity != null) {
            pageInfoEntity.getList().forEach(p ->
            {
                ResponseDetail r=  portalMessageMapper.toResponseDetailByDto(p);
                responseDetails.add(r);
            });
        }
//        new PageImpl<ResponseDetail>(responseDetails,pageInfoEntity.getSize(),pageable)
        return new PageInfo<ResponseDetail>(new PageImpl<ResponseDetail>(responseDetails,pageable,pageInfoEntity.getSize()));
    }

    @Override
    public void msgStatusRead(Integer id) {
//        PortalMessageEntity pme = messageResponsitory.findOne(id);
//        pme.setLookStatus(true);
//        messageResponsitory.saveAndFlush(pme);
//        MessageUtils.changeLookStatus(id,);
    }

    @Override
    @Transactional
    public int allMsgStatusReadByType(Integer msgType) {
        return messageResponsitory.allMsgStatusReadByType(msgType);
    }

    /**
     * 修改某个消息的查看状态
     */
    @Override
    @Transactional
    public void changeLookStatusByMid(Integer messageId) {
        messageResponsitory.changeLookStatusByMid(messageId);
    }

    /**
     * 修改某类消息的查看状态
     */
    @Override
    @Transactional
    public void changeLookStatusByType(Integer loginUserId, Integer eventType) {
        messageResponsitory.changeLookStatusByType(loginUserId,eventType);
    }

    /**
     * 删除消息：消息关联对象被删除，删除相应消息
     */
    @Override
    @Transactional
    public void deleteOne(Integer messageId) {
        messageResponsitory.delete(messageId);
    }

    /**
     * 获取系统消息（官方）
     * @param q
     * @param pr
     * @return
     */
    @Override
    public PageInfo<RespseMessageOffDto> getOfficialsMessage(String q,Pageable pr) {
        Page<PortalMessageEntity> officialMessagesForManage = messageResponsitory.findOfficialMessagesForManage(q, pr);
        List<RespseMessageOffDto> respseMessageOffDtos = new ArrayList<>();
        if (officialMessagesForManage != null){
            if (officialMessagesForManage.getContent().size()!=0){
                officialMessagesForManage.getContent().forEach(p->{
                    RespseMessageOffDto respseMessageOffDto=  portalMessageMapper.toRespseMessageOffDto(p);
                    respseMessageOffDtos.add(respseMessageOffDto);
                });
            }
        }
        return new PageInfo<>(new PageImpl<RespseMessageOffDto>(respseMessageOffDtos,pr,officialMessagesForManage.getTotalElements()));
    }

    /**
     * 获取系统消息详情
     * @param id
     * @return
     */
    @Override
    public RespseMessageOffDto getMessageById( Integer id) {
        PortalMessageEntity one = messageResponsitory.findOne(id);
        RespseMessageOffDto respseMessageOffDto = portalMessageMapper.toRespseMessageOffDto(one);
        return respseMessageOffDto;
    }

    @Override
    public Class getJpaRepositoryClazz() {
        return MessageResponsitory.class;
    }

}
