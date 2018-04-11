package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.common.AppMsgResponse;
import com.cdyoue.oddJobs.dto.common.ResponseDetail;
import com.cdyoue.oddJobs.dto.message.PersonalCenterMessageDto;
import com.cdyoue.oddJobs.dto.message.RespseMessageOffDto;
import com.cdyoue.oddJobs.en.MessageEventTypeEnum;
import com.cdyoue.oddJobs.en.MessageMsgTypeEnum;
import com.cdyoue.oddJobs.entity.lgsq.PortalMessageEntity;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by liaoyoule on 2017/4/24.
 */
public interface MessageService {

    /**
     * 生成消息
     */
    PortalMessageEntity save(PortalMessageEntity PortalMessageEntity);

    /**
     * 取消收藏
     * @param operaId
     * @param eventId
     * @param eventType
     * @param msgType
     */
    void cancelCollection(Integer operaId, Integer eventId, Integer eventType,
                                   Integer msgType);

    /**
     * 取消点赞
     * @param operaId
     * @param eventId
     * @param eventType
     * @param msgType
     */
    void cancelThumbUp(Integer operaId, Integer eventId, Integer eventType,
                          Integer msgType);

    /**
     * 取消事件动作：点赞，收藏，关注
     * @param operaId
     * @param eventId
     * @param eventType
     * @param msgType
     */
    void cancelEventAction(Integer operaId, Integer eventId, Integer eventType,
                 Integer msgType);

    /**
     *
     * 事件动作： 针对管理员删除点赞、收藏、关注对象的 message对象清除
     * @param eventId
     * @param eventType
     * @param msgType
     */
    void deleteAdminRelatedMessage(Integer eventId, Integer eventType,
                              Integer msgType);

    /**
     * 判断消息是否存在  没有接收人的消息判断：有点赞、收藏、关注
     */
    PortalMessageEntity  isMessageExist(Integer operaId, Integer eventId, Integer eventType,
                                        Integer msgType);

    /**
     * 判断消息是否存在 有接收人的消息判断
     */
    PortalMessageEntity  isMessageExist(Integer receiverId,Integer operaId, Integer eventId, Integer eventType,
                                        Integer msgType);

    /**
     * 判断消息是否存在 针对管理员：即roleId = 2 的用户
     */
    List<PortalMessageEntity>  isAdminMessageExist(Integer eventId, Integer eventType,
                                        Integer msgType);

    /**
     * 获取分页message
     * @param operaId
     * @param eventType
     * @param msgType
     * @param rqPage
     * @return
     */
    Page<PortalMessageEntity> getPageMessage(Integer operaId,Integer eventType,
                                             Integer msgType,Pageable rqPage);


    List<Integer> getMessageEventIds(Integer operaId, Integer eventType,
                                     Integer msgType);

    /**
     * 查找所有message
     */
    List<PortalMessageEntity> findAll();

    /**
     * 查找所有与登录用户相关的message
     */
    Page<PortalMessageEntity> findLoginUserMessages(Integer loginUserId, Pageable pageable);

    /**
     * 根据事件分类、用户id查询 用户message
     * @param loginUserId 用户id
     * @param eventTypeEnums   事件分类
     * @return
     */
    Page<PortalMessageEntity> findLoginUserMessagesByEventType(Integer loginUserId, MessageEventTypeEnum eventTypeEnums, Pageable pageable);

    /**
     * 官方数据 指定 eventId = -1 为官方数据
     * @return
     */
    Page<PortalMessageEntity> findOfficialMessages(Integer loginUserId,Pageable pageable);

    /**
     * 通过eventType 跟msgType获取 message
     * @param loginUserId
     * @param eventTypeEnums
     * @param msgTypeEnum
     * @param pageable
     * @return
     */
    List<PortalMessageEntity> findMessageByType(Integer loginUserId, MessageEventTypeEnum eventTypeEnums, MessageMsgTypeEnum msgTypeEnum, Pageable pageable);

    /**
     * 获取每种类型的消息未读数量
     */
    List<AppMsgResponse> getUnreadAppMessage();

    /**
     * 根据类别消息，获取未读消息列表页面
     * @param msgType
     * @param pageRequest
     */
    PageInfo<ResponseDetail> getUnreadMsgSummary(Integer msgType, PageRequest pageRequest);


    /**
     * 消息已读状态
     * @param id
     */
    void msgStatusRead(Integer id);

    /**
     * 一类消息全部设置已读状态
     * @param msgType
     */
    int allMsgStatusReadByType(Integer msgType);

    /**
     * 修改某个消息的查看状态
     */
    void changeLookStatusByMid(Integer messageId);

    /**
     * 修改某类消息的查看状态
     */
    void changeLookStatusByType(Integer loginUserId,Integer eventType);

    /**
     * 删除消息：消息关联对象被删除，删除相应消息
     */
    void deleteOne(Integer msgId);

    PageInfo<RespseMessageOffDto> getOfficialsMessage(String q, Pageable pr);

    RespseMessageOffDto getMessageById(Integer id);
}
