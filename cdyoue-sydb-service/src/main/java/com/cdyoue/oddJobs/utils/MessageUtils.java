package com.cdyoue.oddJobs.utils;

import com.cdyoue.oddJobs.dao.lqsq.*;
import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.message.PersonalCenterMessageDto;
import com.cdyoue.oddJobs.en.MessageEventTypeEnum;
import com.cdyoue.oddJobs.en.MessageMsgTypeEnum;
import com.cdyoue.oddJobs.entity.lgsq.*;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserenterpriseEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserpersonalEntity;
import com.cdyoue.oddJobs.service.MessageRelService;
import com.cdyoue.oddJobs.service.MessageService;
import com.cdyoue.spring.page.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;
import org.springside.modules.utils.collection.ArrayUtil;
import org.springside.modules.utils.collection.CollectionUtil;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/4/20.
 */

public class MessageUtils {
    private  final static Logger logger = LoggerFactory.getLogger(MessageUtils.class);

    private static JdbcTemplate jdbcTemplate = SpringContextUtil.getBean(JdbcTemplate.class);

    // 消息Service 与 Responsitory
    private static MessageService messageService = SpringContextUtil.getBean(MessageService.class);
    private static MessageResponsitory messageResponsitory = SpringContextUtil.getBean(MessageResponsitory.class);

    // 消息rel  Service 与 Responsitory
    private static MessageRelService messageRelService = SpringContextUtil.getBean(MessageRelService.class);
    private static MessageRelResponsitory messageRelResponsitory = SpringContextUtil.getBean(MessageRelResponsitory.class);

    // 1-1  求购知识产权  lg_portal_intellectual_buy
    private static PortalIntellectualBuyResponsitory portalIntellectualBuyResponsitory = SpringContextUtil.getBean(PortalIntellectualBuyResponsitory.class);
    // 1-2  出售知识产权(商标)  lg_portal_intellectual_sale_brand
    private static PortalIntellectualSaleBrandResponsitory portalIntellectualSaleBrandResponsitory = SpringContextUtil.getBean(PortalIntellectualSaleBrandResponsitory.class);
    // 1-3  融资项目 lg_portal_project
    private static PortalProjectResponsitory portalProjectResponsitory = SpringContextUtil.getBean(PortalProjectResponsitory.class);
    // 1-4  场地信息   lg_portal_area_service （场地服务）
    private static PortalAreaServiceResponsitory portalAreaServiceResponsitory = SpringContextUtil.getBean(PortalAreaServiceResponsitory.class);
    // 1-5  新闻内容 lg_portal_news （新闻） 审核->新闻
    private static PortalNewsResponsitory portalNewsResponsitory = SpringContextUtil.getBean(PortalNewsResponsitory.class);
    // 1-6  活动 lg_portal_activity （活动）  审核->活动
    private static PortalActivityResponsitory portalActivityResponsitory = SpringContextUtil.getBean(PortalActivityResponsitory.class);
    // 1-7  个人认证（实名） 1-8个人认证（大咖） 1-9个人认证(导师)  1-10企业资质认证 lg_portal_real_name_info
    private static PortalRealNameInfoResponsitory portalRealNameInfoResponsitory = SpringContextUtil.getBean(PortalRealNameInfoResponsitory.class);
    // 1-11 出售知识产权(专利)  lg_portal_intellectual_sale_patent
    private static PortalIntellectualSalePatentResponsitory portalIntellectualSalePatentResponsitory = SpringContextUtil.getBean(PortalIntellectualSalePatentResponsitory.class);
    // 1-12 出售知识产权(著作权) lg_portal_intellectual_sale_work
    private static PortalIntellectualSaleWorkResponsitory portalIntellectualSaleWorkResponsitory = SpringContextUtil.getBean(PortalIntellectualSaleWorkResponsitory.class);

    // 2-1 承接需求 outsourcingproject表
    private static OutsourcingProjectResponsitory outsourcingProjectResponsitory = SpringContextUtil.getBean(OutsourcingProjectResponsitory.class);
    // 2-2 全职工作 enterpriserecruitment
    private static EnterpriserecruitmentResponsitory enterpriserecruitmentResponsitory = SpringContextUtil.getBean(EnterpriserecruitmentResponsitory.class);
    // 企业
    private static UserenterpriseResponsitory userenterpriseResponsitory = SpringContextUtil.getBean(UserenterpriseResponsitory.class);
    // 2-3 兼职工作  parttimejob
    private static ParttimejobResponsitory parttimejobResponsitory = SpringContextUtil.getBean(ParttimejobResponsitory.class);
    // 2-4 投递简历

    // 2-5 回答问题  question
    private static QuestionResponsitory questionResponsitory = SpringContextUtil.getBean(QuestionResponsitory.class);

    // 3-1关注
    // 关注能人（①发表了新的文章、②提出了新的问题） userpersonal表
   // ①发表了新的文章
    private static PortalCommonPageDetailResponsitory portalCommonPageDetailResponsitory = SpringContextUtil.getBean(PortalCommonPageDetailResponsitory.class);
    private static UserpersonalResponsitory userpersonalResponsitory = SpringContextUtil.getBean(UserpersonalResponsitory.class);
    // 3-2关注问题
    private static QuestionReplyResponsitory questionReplyResponsitory = SpringContextUtil.getBean(QuestionReplyResponsitory.class);

    // 用户Responsitory
    private static UserResponsitory userResponsitory = SpringContextUtil.getBean(UserResponsitory.class);
    /**
     *
     * @param receiverId    接收人id
     * @param eventId       事件id
     * @param eventTypeEnum 事件类型
     * @param msgTypeEnum   消息类型
     */
    public static  void createMessage(int receiverId, Integer eventId, MessageEventTypeEnum eventTypeEnum,
                                      MessageMsgTypeEnum msgTypeEnum) {
        PortalMessageEntity message = new PortalMessageEntity();

        /*
         * 操作人,即loginUser
         */
        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();
        if (currentUserLogin != null){
            UserEntity ue = new UserEntity();
            ue.setId(currentUserLogin.getId());
            message.setOpera(ue);

            String t = "nochongfu2";
        }else{
            logger.error("操作人id不能为null");
            return;
        }


        /*
         * 接收人
         */
        UserEntity receiverUser = new UserEntity();
        receiverUser.setId(receiverId);
        message.setReceiver(receiverUser);

        /*
         *需求id,为-1时是系统消息
         */
        message.setEventId(eventId);

        /*
         *事件类型：1审核，2邀请，3关注，4需求，5官方
         */
        message.setEventType(eventTypeEnum.getValue());

        /*
         * 消息类型
         *
         *审核：1求购，2出售，3发布融资项目（前台哪里发布），4场地，5新闻，6活动，7认证（实名，大咖，导师），8资质认证（企业）
         * 邀请：1承接需求，2工作（兼职），3投递简历，4回答问题
         * 关注：1文章，2问题，3关注的问题有了新的回答
         * 需求：1投递简历， 2报名兼职， 3承接项目
         * 官方通知：内推消息
         */
        message.setMsgType(msgTypeEnum.getMsgType());

        /*
         * 是否查看，0否1是
         */
        message.setLookStatus(false);

        /*
         *详情
         */
        message.setInfo(msgTypeEnum.getEventDescribe());

        /*
         * 创建时间
         */
        message.setCreateTime(new Timestamp(new Date().getTime()));

        PortalMessageEntity messageEt = messageService.save(message);

        //createMsgRel(eventTypeEnum,msgTypeEnum,messageEt);
    }



    /**
     * 无接收人
     * @param eventId       事件id
     * @param eventTypeEnum 事件类型
     * @param msgTypeEnum   消息类型
     */
    public static  void createMessage(Integer eventId, MessageEventTypeEnum eventTypeEnum,
                                      MessageMsgTypeEnum msgTypeEnum) {
        PortalMessageEntity message = new PortalMessageEntity();

        /*
         * 操作人,即loginUser
         */
        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();
        if (currentUserLogin != null){
            UserEntity ue = new UserEntity();
            ue.setId(currentUserLogin.getId());
            message.setOpera(ue);

            String t = "nochongfu1";
        }else{
            logger.error("操作人id不能为null");
            return;
        }


        /*
         *需求id,为-1时是系统消息
         */
        message.setEventId(eventId);

        /**
         *事件类型：1审核，2邀请，3关注，4需求，5官方
         */
        message.setEventType(eventTypeEnum.getValue());

        /*
         * 消息类型
         *
         *审核：1求购，2出售，3发布融资项目（前台哪里发布），4场地，5新闻，6活动，7认证（实名，大咖，导师），8资质认证（企业）
         * 邀请：1承接需求，2工作（兼职），3投递简历，4回答问题
         * 关注：1文章，2问题，3关注的问题有了新的回答
         * 需求：1投递简历， 2报名兼职， 3承接项目
         * 官方通知：内推消息
         */
        message.setMsgType(msgTypeEnum.getMsgType());

        /*
         * 是否查看，0否1是
         */
        message.setLookStatus(false);

        /**
         *详情
         */
        message.setInfo(msgTypeEnum.getEventDescribe());

        /*
         * 创建时间
         */
        message.setCreateTime(new Timestamp(new Date().getTime()));

        PortalMessageEntity messageEt = messageService.save(message);

        //createMsgRel(eventTypeEnum,msgTypeEnum,messageEt);
    }


    /**
     * 创建审核消息
     * @param receiverId    接收人id
     * @param eventId       事件id
     * @param eventTypeEnum 事件类型
     * @param msgTypeEnum   消息类型
     * @param   status      审核状态结果：1通过  2驳回（失败）
     */
    public static  void createAuditMessage(Integer receiverId, Integer eventId, MessageEventTypeEnum eventTypeEnum,
                                      MessageMsgTypeEnum msgTypeEnum, String status) {
        PortalMessageEntity message = new PortalMessageEntity();
        if (status == null && !status.trim().equals("1") && !status.trim().equals("2")){
            return;
        }
        /*
         * 操作人,即loginUser
         */
        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();
        if (currentUserLogin != null){
            UserEntity ue = new UserEntity();
            ue.setId(currentUserLogin.getId());
            message.setOpera(ue);

            String t = "nochongfu3";
        }else{
            logger.error("操作人id不能为null");
            return;
        }


        /*
         * 接收人
         */
        UserEntity receiverUser = new UserEntity();
        receiverUser.setId(receiverId);
        message.setReceiver(receiverUser);

        /*
         *需求id,为-1时是系统消息
         */
        message.setEventId(eventId);

        /*
         *事件类型：1审核
         */
        message.setEventType(eventTypeEnum.getValue());

        /*
         * 消息类型
         *审核：1求购，2出售，3发布融资项目（前台哪里发布），4场地，5新闻，6活动，7认证（实名，大咖，导师），8资质认证（企业）
         */
        message.setMsgType(msgTypeEnum.getMsgType());

        /*
         * 是否查看，0否1是
         */
        message.setLookStatus(false);

        /*
         *详情
         */
        message.setInfo(msgTypeEnum.getEventDescribe());

        /*
         * 创建时间
         */
        message.setCreateTime(new Timestamp(new Date().getTime()));
        /**
         * 额外信息：审核结果 1通过  2驳回（失败）
         */
        message.setExtra(status.trim());

        messageService.save(message);
    }


    /**
     * 不 存在这种情况！！！
     * 关注能人.问题等 一对多的处理表生成
     * 正常无脏数据情况下不 存在这种情况！原：关注能人、问题只会推送关注之后能人发的文章、提的问题、问题有回答的消息  在之后！！
     */
    private static void createMsgRel(MessageEventTypeEnum eventTypeEnum, MessageMsgTypeEnum msgTypeEnum, PortalMessageEntity messageEt){
        // 关注..等一对多
        if ( eventTypeEnum.getValue() == MessageEventTypeEnum.FOCUS.getValue()){
            if (msgTypeEnum.getMsgType() == MessageMsgTypeEnum.FocusTalent.getMsgType() ){
                // eventId即为 能人的userId
                // 能人id
                UserpersonalEntity entity = userpersonalResponsitory.findByUid(messageEt.getEventId());
                if (entity != null){
                    // 关注的能人的文章List
                    List<PortalCommonPageDetailEntity> articleLisr = portalCommonPageDetailResponsitory.findArticleByUserIdAndTime(entity.getUserId(),messageEt.getCreateTime());
                    if (!CollectionUtils.isEmpty(articleLisr)){
                        for (PortalCommonPageDetailEntity article : articleLisr) {
                            if (article != null){
                                PortalMessageRelEntity msgRelEntity = new PortalMessageRelEntity();
                                msgRelEntity.setMessageId(messageEt.getId());
                                msgRelEntity.setRelEventId(article.getId());
                                msgRelEntity.setLookStatus(false);
                                msgRelEntity.setType(1);
                                msgRelEntity.setCreateTime(new Date());
                                messageRelService.save(msgRelEntity);
                            }
                        }
                    }
                    // 关注的能人提出的问题List
                    List<QuestionEntity> questionList = questionResponsitory.findQuestionByUserIdAndTime(entity.getUserId(),messageEt.getCreateTime());
                    if (!CollectionUtils.isEmpty(questionList)){
                        for (QuestionEntity question : questionList) {
                            String qc = "";
                            PortalMessageRelEntity msgRelEntity = new PortalMessageRelEntity();
                            msgRelEntity.setMessageId(messageEt.getId());
                            msgRelEntity.setRelEventId(question.getId());
                            msgRelEntity.setLookStatus(false);
                            msgRelEntity.setType(2);
                            msgRelEntity.setCreateTime(new Date());
                            messageRelService.save(msgRelEntity);
                        }
                    }
                }
            }else if (msgTypeEnum.equals(MessageMsgTypeEnum.FocusQuestions)){

            }
        }
    }

    /**
     * 创建官方消息
     * @param eventTypeEnum 事件类型
     * @param msgTypeEnum   消息类型
     */
    public static  void createOfficialMessage(MessageEventTypeEnum eventTypeEnum,
                                      MessageMsgTypeEnum msgTypeEnum, String info,String link) {
        PortalMessageEntity message = new PortalMessageEntity();


         /*
         * 操作人,即loginUser
         */
        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();
        if (currentUserLogin != null){
            UserEntity ue = new UserEntity();
            ue.setId(currentUserLogin.getId());
            message.setOpera(ue);

            String t = "nochongfu";
        }else{
            logger.error("操作人id不能为null");
            return;
        }

        /*
         *需求id,默认为-1时是系统消息
         */
        message.setEventId(-1);

        /**
         *事件类型：1审核，2邀请，3关注，4需求，5官方
         */
        message.setEventType(eventTypeEnum.getValue());
        message.setMsgType(msgTypeEnum.getMsgType());
        /*
         * 是否查看，0否1是
         */
        message.setLookStatus(false);
        /**
         *详情
         */
        message.setInfo(info);
        /*
         * 创建时间
         */
        message.setCreateTime(new Timestamp(new Date().getTime()));
        /**
         * 额外信息 ：link
         */
        message.setExtra(link);
        messageService.save(message);
    }



    /**
     * 取消事件动作：取消点赞，取消收藏，取消关注
     * @param eventId
     * @param eventType
     * @param msgType
     */
    public static void cancelEventAction(Integer eventId, MessageEventTypeEnum eventType,
                                         MessageMsgTypeEnum msgType){
        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();
        if (currentUserLogin == null){
            return;
        }
        MessageEventTypeEnum thumbUp =  MessageEventTypeEnum.THUMBUP;
        MessageEventTypeEnum collection =  MessageEventTypeEnum.COLLECTION;
        MessageEventTypeEnum focus =  MessageEventTypeEnum.FOCUS;
        if (eventType == thumbUp || eventType == collection || eventType == focus){
            if (eventType != null && msgType != null){
                // 管理员
                if (currentUserLogin.getRole() == 2){
                    messageService.deleteAdminRelatedMessage(eventId,eventType.getValue(),msgType.getMsgType());
                }else{
                    // 非管理员
                    messageService.cancelEventAction(currentUserLogin.getId(),eventId,eventType.getValue(),msgType.getMsgType());
                }

            }
        }
    }


    /**
     * 判断message是否存在 没有接收人的消息判断：有点赞、收藏、关注
     * @param eventId
     * @param eventType
     * @param msgType
     */
    public static Boolean isMessageExist(Integer eventId, MessageEventTypeEnum eventType,
                                         MessageMsgTypeEnum msgType){
        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();
        // 管理员
        if (currentUserLogin != null && currentUserLogin.getRole() == 2){
            return isAdminMessageExist(eventId,eventType,msgType);
        }
        // 非管理员
        if (currentUserLogin != null){
            if (eventType != null && msgType != null){
                PortalMessageEntity message =  messageService.isMessageExist(currentUserLogin.getId(), eventId,
                        eventType.getValue(), msgType.getMsgType());
                if (message != null){
                    return true;
                }
            }
        }
        return  false;
    }



    /**
     * 判断message是否存在 没有接收人的消息判断：有点赞、收藏、关注
     * @param eventId
     * @param eventType
     * @param msgType
     * created by  liujie
     */
    public static Boolean isFocusReplyer(Integer oper,Integer eventId, MessageEventTypeEnum eventType,
                                         MessageMsgTypeEnum msgType){
        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();
        // 管理员
        if (currentUserLogin != null && currentUserLogin.getRole() == 2){
            return isAdminMessageExist(eventId,eventType,msgType);
        }
        // 非管理员
        if (currentUserLogin != null){
            if (eventType != null && msgType != null){
                PortalMessageEntity message =  messageService.isMessageExist(oper, eventId,
                        eventType.getValue(), msgType.getMsgType());
                if (message != null){
                    return true;
                }
            }
        }
        return  false;
    }


    /**
     * 判断message是否存在  有接收人的消息判断
     * @param eventId
     * @param eventType
     * @param msgType
     */
    public static Boolean isMessageExist(Integer receiverId ,Integer eventId, MessageEventTypeEnum eventType,
                                         MessageMsgTypeEnum msgType){
        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();
        // 管理员
        if (currentUserLogin != null && currentUserLogin.getRole() == 2){
            return isAdminMessageExist(eventId,eventType,msgType);
        }
        // 非管理员
        if (currentUserLogin != null){
            if (eventType != null && msgType != null){
                PortalMessageEntity message =  messageService.isMessageExist(receiverId, currentUserLogin.getId(), eventId,
                        eventType.getValue(), msgType.getMsgType());
                if (message != null){
                    return true;
                }
            }
        }
        return  false;
    }

    /**
     * 取消关注能人时删除能人相关的文章或问题
     */
    public  static void cancelMsgRelFoucs(Integer eventId,MessageEventTypeEnum eventType,MessageMsgTypeEnum msgType){
        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();
        if (currentUserLogin != null){
            PortalMessageEntity messageExist = messageService.isMessageExist(currentUserLogin.getId(), eventId,
                    eventType.getValue(), msgType.getMsgType());
            if (messageExist != null){
                messageRelService.deleteMsgRelByMid(messageExist.getId());
            }

        }

    }

    /**
     * 判断消息是否存在 针对管理员：即roleId = 2 的用户
     */
    public static Boolean isAdminMessageExist(Integer eventId, MessageEventTypeEnum eventType,
                                         MessageMsgTypeEnum msgType){

        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();
        if (currentUserLogin != null && currentUserLogin.getRole() == 2){
            List<PortalMessageEntity> adminMessageExistList = messageService.isAdminMessageExist(eventId, eventType.getValue(), msgType.getMsgType());
            if (CollectionUtil.isNotEmpty(adminMessageExistList)){
                return  true;
            }
        }
        return  false;
    }


    /**
     * 修改某个消息查看状态 final
     * @param messageId
     */
    public static void  changeLookStatus(Integer messageId,Integer relEvnetId,Integer quesReplyId,Integer type){
        if (type != null){
            if (type == -1){
                PortalMessageEntity messageEntity = messageResponsitory.findOne(messageId);
                if (messageEntity != null && messageEntity.getLookStatus() == false){// Message表
                    messageService.changeLookStatusByMid(messageId);
                }
            }else { // MessageRel表
                PortalMessageRelEntity messageRel = new PortalMessageRelEntity();
                if (type == 3 && quesReplyId != null){
                    messageRel = messageRelResponsitory.findMsgRelByMsgIdAndTypeAndrelEventId(messageId, quesReplyId, type);
                }else {
                    messageRel = messageRelResponsitory.findMsgRelByMsgIdAndTypeAndrelEventId(messageId, relEvnetId, type);
                }

                if (messageRel != null && messageRel.getLookStatus() == false){
                    messageRel.setLookStatus(true);
                    messageRelService.changeFouceLookStatus(messageRel);
                }
            }

        }


    }

    /**
     * 修改一对多的某类消息的查看状态
     *
     */
    public static void  changeLookStatusByType(Integer eventType){
        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();
        if ( currentUserLogin == null){
            return;
        }
        Integer[] oToMEventType = new Integer[]{3};
        List<Integer> oToMEventTypeList = ArrayUtil.asList(oToMEventType);

        if (oToMEventTypeList.contains(eventType)){
            messageRelService.changeOToMLookStatusByType(currentUserLogin.getId(),eventType);
        }else {
            messageService.changeLookStatusByType(currentUserLogin.getId(),eventType);
        }
    }


    /**
     * 根据eventTypeEnum、eventTypeEnum、operaId 获取message列表，用于相关event事件id类查询

     * @param eventTypeEnum
     * @param msgTypeEnum
     */
    public static List<Integer> getMessageEventIds(MessageEventTypeEnum eventTypeEnum, MessageMsgTypeEnum msgTypeEnum){
        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();
        if (currentUserLogin != null){
            List<Integer> messageEventIds = messageService.getMessageEventIds(currentUserLogin.getId(),
                    eventTypeEnum.getValue(), msgTypeEnum.getMsgType());
            return messageEventIds;
        }
        return null;
    }

    /**
     * 获取分页message
     * @param eventTypeEnum
     * @param msgTypeEnum
     */
    public static Page<PortalMessageEntity>  getPageMessage(MessageEventTypeEnum eventTypeEnum, MessageMsgTypeEnum msgTypeEnum,Pageable rqPage){
        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();
        if (currentUserLogin != null){
            Page<PortalMessageEntity> pageMessage = messageService.getPageMessage(currentUserLogin.getId(), eventTypeEnum.getValue(), msgTypeEnum.getMsgType(), rqPage);
            return pageMessage;
        }
        return null;
    }


    /**
     * 获取系统消息列表 生成在个人中心
     *
     */

    public static PageInfo<PersonalCenterMessageDto> getAllPersonalMessageInfo(Pageable pageable){
        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();
        if (currentUserLogin == null){
            return null;
        }
        /*
        * 分类处理 消息生成
        */
        // 申明messageDto集合，装存分类消息
        List<PersonalCenterMessageDto> messageDtoList = new ArrayList<PersonalCenterMessageDto>();
        // 拿到登录用户相关联messages 分门别类处理 Page<PersonalCenterMessageDto> loginUserMessageList
        Page<PortalMessageEntity> loginUserMessages = messageResponsitory.findLoginUserMessagesMy(currentUserLogin.getId(), pageable);

        if (!CollectionUtils.isEmpty(loginUserMessages.getContent())){
            for (PortalMessageEntity messageEt : loginUserMessages.getContent()) {
                int eventType = messageEt.getEventType();
                // 根据eventType 分门消息
                if (eventType == MessageEventTypeEnum.AUDIT.getValue()){
                    // 1审核
                    getAuditMessages(messageEt, messageDtoList);
                }else if (eventType == MessageEventTypeEnum.INVITATION.getValue())
                {
                    // 2邀请
                    getInvitationMessages(messageEt, messageDtoList);
                }else if (eventType == MessageEventTypeEnum.FOCUS.getValue()){
                    // 3 关注
                    //getFocusMessages(messageEt, messageDtoList);
                }else if (eventType == MessageEventTypeEnum.REQUIREMENT.getValue()||
                        eventType ==  MessageEventTypeEnum.APPLYFOR.getValue()){
                    // 需求 4 或 8
                    getRequirementMessages(messageEt, messageDtoList);
                }else if (eventType == MessageEventTypeEnum.OFFICIAL.getValue()){
                    // 5 官方 eventId 为-1 5-1
                    getOfficialMessages(messageEt, messageDtoList);
                } else if (eventType == MessageEventTypeEnum.COLLECTION.getValue()){
                    // 收藏
                    //getCollectionMessages(){}
                }else if (eventType == MessageEventTypeEnum.THUMBUP.getValue()){
                    // 点赞
                    // getThumbUpMessages(){}
                }else {
                    continue;
                }
            }

        }
        // 3 关注 单独处理
        Page<PortalMessageRelEntity> fouceMessageRels = messageResponsitory.findLoginUserFouceMessageRels(currentUserLogin.getId(), pageable);
        //countTotalMsgNumByType(MessageEventTypeEnum.FOCUS);
        List<PortalMessageEntity> fouceMessagesList = messageResponsitory.findLoginUserFouceMessages(currentUserLogin.getId());
        for (PortalMessageEntity messageEt : fouceMessagesList) {
            getFocusMessages(messageEt, messageDtoList);
        }

        sortTime(messageDtoList);
        System.out.println(loginUserMessages.getTotalElements());
//        PageImpl page = new PageImpl(messageDtoList);
//        public PageInfo(int pn, int psize, String orderBy, List content, long total) {
        PageInfo pageInfo = new PageInfo(pageable.getPageNumber(),pageable.getPageSize(),pageable.getSort().toString(),
                messageDtoList,loginUserMessages.getTotalElements());
//        pageInfo.setPageNum(pageable.getPageNumber());
//        pageInfo.setPageSize(pageable.getPageSize());
//        pageInfo.setList(messageDtoList);
//        pageInfo.setOrderBy(pageable.getSort().toString());
//        pageInfo.setTotal(loginUserMessages.getTotalElements());
//        pageInfo.setNavigatePages(pageable.getPageNumber());
        return pageInfo;
    }

    /*
     * 通过事件类型获取消息
     * messages of eventType 用于个人中心 eventType 门类
     *
     */
    public static PageInfo<PersonalCenterMessageDto> getMessageByeventType(MessageEventTypeEnum eventTypeEnum, Pageable pageable){
        int eventType = eventTypeEnum.getValue();
        List<PersonalCenterMessageDto> messageEventTypeDtoList = new  ArrayList<PersonalCenterMessageDto>();
        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();
        if (currentUserLogin != null){
            Page<PortalMessageEntity> loginUserMessagesByEventType = messageService.findLoginUserMessagesByEventType(currentUserLogin.getId(),
                    eventTypeEnum, pageable);
            for (PortalMessageEntity messageEt : loginUserMessagesByEventType.getContent()) {
                if (eventTypeEnum == MessageEventTypeEnum.AUDIT){// 1审计
                    getAuditMessages(messageEt,messageEventTypeDtoList);
                }else if (eventTypeEnum == MessageEventTypeEnum.INVITATION){//2 邀请
                    getInvitationMessages(messageEt,messageEventTypeDtoList);
                }else if (eventTypeEnum == MessageEventTypeEnum.FOCUS){ // 3关注
                    getFocusMessages(messageEt,messageEventTypeDtoList);
                }

            }
            if (eventTypeEnum == MessageEventTypeEnum.REQUIREMENT){ // 需求 =  4 + 8
/*                List<PortalMessageEntity> requirMList = messageService.findLoginUserMessagesByEventType(currentUserLogin.getId(),
                        MessageEventTypeEnum.REQUIREMENT, pageable);
                List<PortalMessageEntity> applyMList = messageService.findLoginUserMessagesByEventType(currentUserLogin.getId(),
                        MessageEventTypeEnum.APPLYFOR, pageable);
                for (PortalMessageEntity requirMessageEt : requirMList) {
                    getRequirementMessages(requirMessageEt, messageEventTypeDtoList);
                }
                for (PortalMessageEntity applyMessageEt : applyMList) {
                    getRequirementMessages(applyMessageEt, messageEventTypeDtoList);
                }*/
                Page<PortalMessageEntity> requirMessageList = messageResponsitory.findLoginUserRequirementMessagesByEventType(currentUserLogin.getId(), pageable);
                for (PortalMessageEntity messageEt : requirMessageList.getContent()) {
                    getRequirementMessages(messageEt, messageEventTypeDtoList);
                }
            }
            // 官方 单独处理 eventId 为-1 5-1
            if (eventTypeEnum == MessageEventTypeEnum.OFFICIAL){
                Page<PortalMessageEntity> officialMessages = messageService.findOfficialMessages(currentUserLogin.getId(),pageable);
                for (PortalMessageEntity messageEt : officialMessages.getContent()) {
                    getOfficialMessages(messageEt, messageEventTypeDtoList);
                }
            }
        }
        //sortTime(messageEventTypeDtoList);
        return new PageInfo<>(new PageImpl(messageEventTypeDtoList, pageable,messageEventTypeDtoList.size()));
        //return messageEventTypeDtoList;
    }

    /*
     * messages of 审核
     * 1求购知识产权，2出售知识产权，3发布融资项目（前台哪里发布），4场地，5新闻，6活动，7个人认证（实名），8个人认证（大咖）;
     * 9 个人认证（导师） 10 企业资质认证 11 出售知识产权(专利) 12 出售知识产权(著作权)  13 审核->需求
     */
    private static void getAuditMessages(PortalMessageEntity messageEt,List<PersonalCenterMessageDto> messageDtoList){
        //1求购知识产权 、2出售知识产权(商标) 11出售知识产权(专利) 12出售知识产权(著作权)

        if (messageEt != null){
            PersonalCenterMessageDto messageDto = encapMessageDto(messageEt);
            int msgType = messageEt.getMsgType();
            int buyMsgType =  MessageMsgTypeEnum.AuditBuyIntellectualProperty.getMsgType();
            int sellBrandType = MessageMsgTypeEnum.AuditSellIntellectualPropertyBrand.getMsgType();
            int sellPatentType = MessageMsgTypeEnum.AuditSellIntellectualPropertyPatent.getMsgType();
            int sellWorkType =  MessageMsgTypeEnum.AuditSellIntellectualPropertyWork.getMsgType();
            if (msgType == buyMsgType || msgType == sellBrandType || msgType == sellPatentType ||
                    msgType == sellWorkType ){
                // 通过eventId获取相关Entity
                String mssageDetail = "";
                if (msgType == buyMsgType){//1求购知识产权
                    PortalIntellectualBuyEntity entity = portalIntellectualBuyResponsitory.findOne(messageEt.getEventId());
                    // 清除残存 message 脏数据
                    clearThisMessage(entity, messageEt);
                    if (entity != null){
                        String name = entity.getName();
                        String reviewStatus = messageEt.getExtra();
                        mssageDetail = createAuditMssageDetail(messageEt, name,  "知识产权求购需求", reviewStatus);
                    }
                }//2出售知识产权(商标)
                else if (msgType == sellBrandType){
                    PortalIntellectualSaleBrandEntity entity = portalIntellectualSaleBrandResponsitory.findOne(messageEt.getEventId());
                    // 清除残存 message 脏数据
                    clearThisMessage(entity, messageEt);
                    if (entity != null){
                        String name = entity.getName();
                        String reviewStatus = messageEt.getExtra();
                        mssageDetail = createAuditMssageDetail(messageEt, name,  "知识产权出售需求", reviewStatus);
                    }
                }// 11出售知识产权(专利)
                else if (msgType == sellPatentType){
                    PortalIntellectualSalePatentEntity entity = portalIntellectualSalePatentResponsitory.findOne(messageEt.getEventId());
                    // 清除残存 message 脏数据
                    clearThisMessage(entity, messageEt);
                    if (entity != null){
                        String name = entity.getName();
                        String reviewStatus = messageEt.getExtra();
                        mssageDetail = createAuditMssageDetail(messageEt, name,  "知识产权出售需求", reviewStatus);
                    }
                }// 12出售知识产权(著作权)
                else if ( msgType == sellWorkType){
                    PortalIntellectualSaleWorkEntity entity = portalIntellectualSaleWorkResponsitory.findOne(messageEt.getEventId());
                    // 清除残存 message 脏数据
                    clearThisMessage(entity, messageEt);
                    if (entity != null){
                        String name = entity.getName();
                        String reviewStatus = messageEt.getExtra();
                        mssageDetail = createAuditMssageDetail(messageEt, name,  "知识产权出售需求", reviewStatus);
                    }
                }
                messageDto.setMessageDetail(mssageDetail);
            } // 3发布融资项目
            else if (msgType == MessageMsgTypeEnum.AuditPublishFinanceProject.getMsgType()){
                PortalProjectEntity entity = portalProjectResponsitory.findOne(messageEt.getEventId());
                // 清除残存 message 脏数据
                clearThisMessage(entity, messageEt);
                if (entity != null){
                    String name = entity.getName();
                    String reviewStatus = messageEt.getExtra();
                    String mssageDetail = createAuditMssageDetail(messageEt, name, "融资项目", reviewStatus);
                    messageDto.setMessageDetail(mssageDetail);
                }
            }// 4 审核->场地
            else if (msgType == MessageMsgTypeEnum.AuditPlace.getMsgType()){
                PortalAreaServiceEntity entity = portalAreaServiceResponsitory.findOne(messageEt.getEventId());
                // 清除残存 message 脏数据
                clearThisMessage(entity, messageEt);
                if (entity != null){
                    String name = entity.getName();
                    String reviewStatus = messageEt.getExtra();
                    String mssageDetail = createAuditMssageDetail(messageEt, name, "场地信息", reviewStatus);
                    messageDto.setMessageDetail(mssageDetail);
                }
            }// 5 新闻内容  审核->新闻
            else if (msgType == MessageMsgTypeEnum.AuditNews.getMsgType()){
                PortalNewsEntity entity = portalNewsResponsitory.findOne(messageEt.getEventId());
                // 清除残存 message 脏数据
                clearThisMessage(entity, messageEt);
                if (entity != null){
                    String title = entity.getTitle();
                    String reviewStatus = messageEt.getExtra();
                    String mssageDetail = createAuditMssageDetail(messageEt, title, "新闻内容", reviewStatus);
                    messageDto.setMessageDetail(mssageDetail);
                }
            }// 6 活动  审核->活动
            else if (msgType == MessageMsgTypeEnum.AuditActivity.getMsgType()){
                PortalActivityEntity entity = portalActivityResponsitory.findOne(messageEt.getEventId());
                // 清除残存 message 脏数据
                clearThisMessage(entity, messageEt);
                if (entity != null){
                    String title = entity.getTitle();
                    String reviewStatus = messageEt.getExtra();
                    String mssageDetail = createAuditMssageDetail(messageEt, title, "活动", reviewStatus);
                    messageDto.setMessageDetail(mssageDetail);
                }
            }//7 个人认证(实名)   8个人认证(大咖)  9个人认证(导师) 10企业资质认证
            else if (msgType == MessageMsgTypeEnum.AuditCertificationPersonRealname.getMsgType() ||
                    msgType == MessageMsgTypeEnum.AuditCertificationPersonDaka.getMsgType() ||
                    msgType == MessageMsgTypeEnum.AuditCertificationPersonMentor.getMsgType()||
                    msgType == MessageMsgTypeEnum.AuditCertificationEnterRealname.getMsgType() ){
                PortalRealNameInfoEntity entity = portalRealNameInfoResponsitory.findOne(messageEt.getEventId());
                // 清除残存 message 脏数据
                clearThisMessage(entity, messageEt);
                if (entity != null){ // 10企业资质认证
                    String reviewStatus = messageEt.getExtra();
                    Integer applyType = entity.getApplyType();
                    String applyTypeInfo = switchApplyType(applyType);

                    if (msgType == MessageMsgTypeEnum.AuditCertificationEnterRealname.getMsgType()){
                        UserenterpriseEntity entuser = userenterpriseResponsitory.findByUid(entity.getUserId());
                        // 清除残存 message 脏数据
                        clearThisMessage(entuser, messageEt);
                        if (entuser != null){// 10企业资质认证
                            String name = entuser.getName();
                            String mssageDetail = createAuditMssageDetail(messageEt, name, "企业资质认证", reviewStatus);
                            messageDto.setMessageDetail(mssageDetail);
                        }
                    }else{// 个人认证
                        UserpersonalEntity userpersonalEntity = userpersonalResponsitory.findByUid(entity.getUserId());
                        // 清除残存 message 脏数据
                        clearThisMessage(userpersonalEntity, messageEt);
                        if (userpersonalEntity!=null){
                            String name = userpersonalEntity.getName();
                            String mssageDetail = createAuditMssageDetail(messageEt, name, applyTypeInfo, reviewStatus);
                            messageDto.setMessageDetail(mssageDetail);
                        }

                    }


                }
            }else if (msgType == MessageMsgTypeEnum.AuditRequirement.getMsgType()){
                // 审核->需求
                OutsourcingProjectEntity entity = outsourcingProjectResponsitory.findOne(messageEt.getEventId());
                // 清除残存 message 脏数据
                clearThisMessage(entity, messageEt);
                if (entity != null){
                    String reviewStatus = messageEt.getExtra();
                    String title = entity.getName();
                    String mssageDetail = createAuditMssageDetail(messageEt, title, "需求", reviewStatus);
                    messageDto.setMessageDetail(mssageDetail);
                }
            }
            if (messageDto.getMessageDetail() != null){
                messageDtoList.add(messageDto);
            }
        }

    }


    /*
    * messages of  邀请
    * 邀请：1承接需求，2 全职工作， 3 兼职工作,4投递简历(没有，不做)，5回答问题;
    */
    private static void getInvitationMessages(PortalMessageEntity messageEt,List<PersonalCenterMessageDto> messageDtoList){
        if (messageEt != null) {
            PersonalCenterMessageDto messageDto = encapMessageDto(messageEt);
            int msgType = messageEt.getMsgType();
            // 1 承接需求 outsourcingproject表
            if (msgType == MessageMsgTypeEnum.InvitationAcceptRequirement.getMsgType()){
                OutsourcingProjectEntity entity = outsourcingProjectResponsitory.findOne(messageEt.getEventId());
                // 清除残存 message 脏数据
                clearThisMessage(entity, messageEt);
                if (entity != null){
                    UserEntity createBy = entity.getCreateBy();
                    // 清除残存 message 脏数据
                    clearThisMessage(createBy, messageEt);
                    if (createBy != null){
                        // 需求方名字
                        String requireUserName = getJdbcTemplateFiledValue(createBy.getId(), entity.getId());
                        // 清除残存 message 脏数据
                        clearThisMessage(requireUserName, messageEt);
                        // 需求名
                        String requirementName = entity.getName();
                        int reviewStatus = entity.getReviewStatus();
                        if (!"".equals(requireUserName)){
                            String mssageDetail = createInvitationMMssageDetail(messageEt, requireUserName, requirementName);
                            messageDto.setMessageDetail(mssageDetail);
                        }
                    }
                }
            }// 2 全职工作 enterpriserecruitment
            else if(msgType == MessageMsgTypeEnum.InvitationFullTimeJob.getMsgType()){
                EnterpriserecruitmentEntity entity = enterpriserecruitmentResponsitory.findOne(messageEt.getEventId());
                // 清除残存 message 脏数据
                clearThisMessage(entity, messageEt);
                if (entity != null){
                    UserenterpriseEntity userenterpriseEntity = userenterpriseResponsitory.findOne(entity.getEntId());
                    // 清除残存 message 脏数据
                    clearThisMessage(userenterpriseEntity, messageEt);
                    if (userenterpriseEntity != null){
                        // 全职工作发布企业
                        String priseName = userenterpriseEntity.getName();
                        // 全职工作名字
                        String fullJobName = entity.getJobName();
                        String mssageDetail = createInvitationMMssageDetail(messageEt, priseName, fullJobName);
                        messageDto.setMessageDetail(mssageDetail);
                    }
                }
            }// 3 兼职工作  parttimejob   ParttimejobResponsitory
            else if (msgType == MessageMsgTypeEnum.InvitationPartTimeJob.getMsgType()){
                ParttimejobEntity entity = parttimejobResponsitory.findOne(messageEt.getEventId());
                // 清除残存 message 脏数据
                clearThisMessage(entity, messageEt);
                if (entity != null){
                    UserenterpriseEntity userenterpriseEntity = userenterpriseResponsitory.findOne(entity.getEntId());
                    // 清除残存 message 脏数据
                    clearThisMessage(userenterpriseEntity, messageEt);
                    if (userenterpriseEntity != null){
                        // 兼职工作发布企业
                        String priseName = userenterpriseEntity.getName();
                        // 兼职工作名字
                        String partJobName = entity.getJobName();
                        String mssageDetail = createInvitationMMssageDetail(messageEt, priseName, partJobName);
                        messageDto.setMessageDetail(mssageDetail);
                    }
                }
            }// 4 邀请->投递简历
            else if (msgType == MessageMsgTypeEnum.InvitationSendResume.getMsgType()){
            }
            // 5 邀请->回答问题
            else if (msgType == MessageMsgTypeEnum.InvitationAnswerQuestions.getMsgType()){
                QuestionEntity entity = questionResponsitory.findOne(messageEt.getEventId());
                // 清除残存 message 脏数据
                clearThisMessage(entity, messageEt);
                if (entity != null){
                    UserpersonalEntity userpersonalEntity = userpersonalResponsitory.findByUid(entity.getUserId());
                    UserenterpriseEntity entUserEntity = userenterpriseResponsitory.findEntByUid(entity.getUserId());
                    if (userpersonalEntity == null && entUserEntity == null){
                        // 清除残存 message 脏数据
                        clearThisMessage(userpersonalEntity, messageEt);
                    }
                    if (userpersonalEntity != null){
                        // 问题发起人
                        String userName = userpersonalEntity.getName();
                        // 问题
                        String title = entity.getTitle();
                        String mssageDetail = createInvitationMMssageDetail(messageEt, userName, title);
                        messageDto.setMessageDetail(mssageDetail);
                    }else if (entUserEntity != null){
                        // 问题发起人
                        String userName = entUserEntity.getName();
                        // 问题
                        String title = entity.getTitle();
                        String mssageDetail = createInvitationMMssageDetail(messageEt, userName, title);
                        messageDto.setMessageDetail(mssageDetail);
                    }
                }
            }
            if (messageDto.getMessageDetail() != null ){
                messageDtoList.add(messageDto);
            }
        }
    }


    /*
    * messages of  关注
    * 关注：1文章，2问题(关注的问题有了新的回答),3话题,4活动;
    *
    * 关注：1.user表 能人(①刚刚发表了一篇新的文章，②提了一个新的问题)，2问题(关注的问题有了新的回答),3话题,4活动;
    */
    private static void getFocusMessages(PortalMessageEntity messageEt,List<PersonalCenterMessageDto> messageDtoList){
        if (messageEt != null) {
            //PersonalCenterMessageDto messageDto = encapMessageDto(messageEt);
            int msgType = messageEt.getMsgType();
            //  1 能人(①发表了新的文章 、 ②提出了新的问题)
            if (msgType == MessageMsgTypeEnum.FocusTalent.getMsgType()){
                // 关注能人没有接受人！！eventId就是关注的能人的id
                UserpersonalEntity entity = userpersonalResponsitory.findByUid(messageEt.getEventId());
                // 清除残存 message 脏数据
                clearThisMessage(entity, messageEt);
                if (entity != null){
                    String talentName = entity.getName();
                    // ①发表了新的文章
                    List<PortalCommonPageDetailEntity> articleLisr = portalCommonPageDetailResponsitory.findArticleByUserIdAndTime(entity.getUserId(),messageEt.getCreateTime());
                    // 清除残存文章 message 脏数据
                    messageRelService.deleteArticleListWasteMessageRel(messageEt.getId(),entity.getUserId(),messageEt.getCreateTime());
                    //  batchAdd添加能人新发布的文章到MessageRel表
                    List<PortalCommonPageDetailEntity> lostArtcleList= messageRelResponsitory.findLostArtcleIds(messageEt.getId(), entity.getUserId(), messageEt.getCreateTime());
                    batchAddLostArtcleMessageRel(messageEt.getId(),lostArtcleList,1);
                    // type 1 文章
                    //List<PortalMessageRelEntity> atrMessageRelList = messageRelResponsitory.findMsgRelByMessageIdAndType(messageEt.getId(), 1);
                    for (PortalCommonPageDetailEntity article : articleLisr) {
                        PersonalCenterMessageDto messageDto = encapMessageDto(messageEt);
                        // 重置msgType 用于前台关注的能人的文章与问题的区分
                        messageDto.setMsgTypeEnum(11);
                        String title = article.getTitle();
                        String mssageDetail = createFocusMessageDetail(messageEt, talentName, title,1);
                        messageDto.setMessageDetail(mssageDetail);
                        // 重置eventId 用于前台跳转
                        messageDto.setEventId(article.getId());
                        // 重置查看状态
                        PortalMessageRelEntity messageRel = messageRelResponsitory.findMsgRelByMsgIdAndTypeAndrelEventId(messageEt.getId(), article.getId(), 1);
                        if (messageRel != null){
                            messageDto.setLookStatus(messageRel.getLookStatus());
                        }
                        // 设置类型 用于changeStatus的Message瑜MessageRel表的区分
                        messageDto.setType(1);
                        // 重置messageTime 用于前台展示
                        messageDto.setMessageTime(article.getCreateTime());
                        messageDtoList.add(messageDto);
                    }
                    // ②提出了新的问题
                    List<QuestionEntity> questionList = questionResponsitory.findQuestionByUserIdAndTime(entity.getUserId(),messageEt.getCreateTime());
                    // type 2 提新的问题
                    // 清除残存问题 message 脏数据
                    messageRelService.deleteQuesListWasteMessageRel(messageEt.getId(),entity.getUserId(),messageEt.getCreateTime());
                    // 添加能人新提出的问题到MessageRel表
                    List<QuestionEntity> lostQuesList = messageRelResponsitory.findLostQuesIds(messageEt.getId(), entity.getUserId(), messageEt.getCreateTime());
                    batchAddLostQuesMessageRel(messageEt.getId(),lostQuesList,2);
                    //List<PortalMessageRelEntity> queMessageRelList = messageRelResponsitory.findMsgRelByMessageIdAndType(messageEt.getId(), 2);
                    for (QuestionEntity questionEntity : questionList) {
                        PersonalCenterMessageDto messageDto = encapMessageDto(messageEt);
                        // 重置msgType 用于前台关注的能人的文章与问题的区分
                        messageDto.setMsgTypeEnum(12);
                        String title = questionEntity.getTitle();
                        String mssageDetail = createFocusMessageDetail(messageEt, talentName, title,2);
                        messageDto.setMessageDetail(mssageDetail);
                        // 重置eventId 用于前台跳转
                        messageDto.setEventId(questionEntity.getId());
                        // 重置查看状态
                        PortalMessageRelEntity messageRel = messageRelResponsitory.findMsgRelByMsgIdAndTypeAndrelEventId(messageEt.getId(), questionEntity.getId(), 2);
                        if (messageRel != null){
                            messageDto.setLookStatus(messageRel.getLookStatus());
                        }
                        // 设置类型 用于changeStatus的Message瑜MessageRel表的区分
                        messageDto.setType(2);
                        // 重置messageTime 用于前台展示
                        messageDto.setMessageTime(questionEntity.getCreateTime());
                        messageDtoList.add(messageDto);
                    }
                }

            }//  2问题(关注的问题有了新的回答)
            else if(msgType == MessageMsgTypeEnum.FocusQuestions.getMsgType()){
                //PersonalCenterMessageDto messageDto = encapMessageDto(messageEt);
                QuestionEntity entity = questionResponsitory.findOne(messageEt.getEventId());
                // 清除残存 message 脏数据
                clearThisMessage(entity, messageEt);
                if (entity != null){
                    String title = entity.getTitle();
                    List<QuestionreplyEntity> replyQuestionList = questionReplyResponsitory.findReplyByQuestionId(entity.getId(),messageEt.getCreateTime());
                    // 清除残存文章 message 脏数据
                    messageRelService.deleteReplyQuesListWasteMessageRel(messageEt.getId(),entity.getId(),messageEt.getCreateTime());
                    //  batchAdd添加问题的新回答 MessageRel表
                    List<QuestionreplyEntity> lostQuesReplycleList = messageRelResponsitory.findLostQuesReplycleIds(messageEt.getId(), entity.getId(), messageEt.getCreateTime());
                    batchAddLostReplyMessageRel(messageEt.getId(),lostQuesReplycleList,3);
                    for (QuestionreplyEntity quesReply: replyQuestionList) {
                        PersonalCenterMessageDto messageDto = encapMessageDto(messageEt);
                        String strDate = switchTime(quesReply.getCreateTime(), 2);
                        String mssageDetail = createFocusMessageDetail(messageEt, strDate, title,null);
                        messageDto.setMessageDetail(mssageDetail);
                        // 重置eventId 用于前台跳转?
                        // 重置查看状态
                        PortalMessageRelEntity messageRel = messageRelResponsitory.findMsgRelByMsgIdAndTypeAndrelEventId(messageEt.getId(), quesReply.getId(), 3);
                        if (messageRel != null){
                            messageDto.setLookStatus(messageRel.getLookStatus());
                        }
                        // 设置类型 用于changeStatus的Message瑜MessageRel表的区分
                        messageDto.setType(3);
                        // 设置问题replyId,用于改变查看状态
                        messageDto.setQuesReplyId(quesReply.getId());
                        // 重置messageTime 用于前台展示
                        messageDto.setMessageTime(quesReply.getCreateTime());
                        messageDtoList.add(messageDto);
                    }

                }
            }
        }
    }

    /*
    * messages of  官方
    *
    */
    private static void getOfficialMessages(PortalMessageEntity messageEt,List<PersonalCenterMessageDto> messageDtoList){
        if (messageEt != null) {
            PersonalCenterMessageDto messageDto = encapMessageDto(messageEt);
            int msgType = messageEt.getMsgType();
            if (msgType == MessageMsgTypeEnum.OfficialNotification.getMsgType()){
                messageDto.setMessageDetail(messageEt.getInfo());
            }
            messageDtoList.add(messageDto);
        }
    }

    /*
   * messages of 需求
   * 需求：8-2投递简历， 8-1报名兼职， 4-3承接项目（需求）
   * // 登录用户id 匹配 receiverId
   *  全职 、兼职 receiverId = EntID 对应的用户id
   *  项目 receiverId = createBy
   */
    private static void getRequirementMessages(PortalMessageEntity messageEt,List<PersonalCenterMessageDto> messageDtoList){
        if (messageEt != null){
            PersonalCenterMessageDto messageDto = encapMessageDto(messageEt);
            int msgType = messageEt.getMsgType();
            // 2投递简历（全职） enterpriserecruitment
            if (messageEt.getEventType() == MessageEventTypeEnum.APPLYFOR.getValue()){
                if (msgType == MessageMsgTypeEnum.ApplyForFullTimeJob.getMsgType()){
                    // eventId对应全职表id
                    // 承接人（企业用户或个人用户）
                    UserEntity operaUser = messageEt.getOpera();
                    // 工作名称(全职)entity
                    EnterpriserecruitmentEntity entity = enterpriserecruitmentResponsitory.findOne(messageEt.getEventId());
                    // 清除残存 message 脏数据
                    clearThisMessage(entity, messageEt);
                    if (entity != null && operaUser != null){
                        String operaUserNam = userResponsitory.findUserNameDistPerAndEnt(operaUser.getId());
                        // 清除残存 message 脏数据
                        clearThisMessage(operaUserNam, messageEt);
                        // 全职jobName
                        String jobName = entity.getJobName();
                        String messageDetail = createRequirementMessageDetail(messageEt, operaUserNam, jobName);
                        messageDto.setMessageDetail(messageDetail);
                    }
                }else if (msgType == MessageMsgTypeEnum.ApplyForPartTimeJob.getMsgType()){
                    // 1报名兼职 parttimejob
                    // eventId对兼职表id
                    // 承接人（企业用户或个人用户）
                    UserEntity operaUser = messageEt.getOpera();
                    // 兼职entity
                    ParttimejobEntity entity = parttimejobResponsitory.findOne(messageEt.getEventId());
                    // 清除残存 message 脏数据
                    clearThisMessage(entity, messageEt);
                    if (operaUser != null && entity != null){
                        String operaUserNam = userResponsitory.findUserNameDistPerAndEnt(operaUser.getId());
                        // 清除残存 message 脏数据
                        clearThisMessage(operaUserNam, messageEt);
                        // 兼职jobName
                        String partJobName = entity.getJobName();
                        // PortalMessageEntity messageEt, String entityUserName, String entityName
                        String messageDetail = createRequirementMessageDetail(messageEt, operaUserNam, partJobName);
                        messageDto.setMessageDetail(messageDetail);
                        String quchong = "";
                    }

                }
            } else if (msgType == MessageMsgTypeEnum.RequirementAcceptProject.getMsgType()){
                // 3承接项目 需求 outsourcingproject表
                // eventId对需求表id
                // 承接人（企业用户或个人用户）
                UserEntity operaUser = messageEt.getOpera();
                //需求entity
                OutsourcingProjectEntity entity = outsourcingProjectResponsitory.findOne(messageEt.getEventId());
                // 清除残存 message 脏数据
                clearThisMessage(entity, messageEt);
                if (operaUser != null && entity != null){
                    String operaUserNam = userResponsitory.findUserNameDistPerAndEnt(operaUser.getId());
                    // 清除残存 message 脏数据
                    clearThisMessage(operaUserNam, messageEt);
                    // 兼职jobName
                    String  sourcingProjectName = entity.getName();
                    // PortalMessageEntity messageEt, String entityUserName, String entityName
                    String messageDetail = createRequirementMessageDetail(messageEt, operaUserNam, sourcingProjectName);
                    messageDto.setMessageDetail(messageDetail);
                }
            }
            if (messageDto.getMessageDetail() != null){
                messageDtoList.add(messageDto);
            }
        }
    }
    /**
     *  messages of 审核详情
     * @param messageEt message实体
     * @param entityName     对应Entity的名字
     * @param type      分类描述
     * @param reviewStatus 审核状态
     * @return
     */
    private static String createAuditMssageDetail(PortalMessageEntity messageEt, String entityName, String type, String reviewStatus){
        int msgType = messageEt.getMsgType();
        String reviewStatusInfo = switchReviewStatus(reviewStatus);
        int realname = MessageMsgTypeEnum.AuditCertificationPersonRealname.getMsgType();
        int daka = MessageMsgTypeEnum.AuditCertificationPersonDaka.getMsgType();
        int mentor = MessageMsgTypeEnum.AuditCertificationPersonMentor.getMsgType();
        int enterRealname =  MessageMsgTypeEnum.AuditCertificationEnterRealname.getMsgType();

        Integer[] t =   new Integer[]{realname,daka,mentor,enterRealname};// 认证
        List<Integer> integers = Arrays.asList(t);
        if (integers.contains(msgType) ){
            // 认证
            return "尊敬的用户您好，您的"+ entityName + type + reviewStatusInfo;
        }else{
            return "尊敬的用户您好，您发布的"+ entityName + type + reviewStatusInfo;
        }
    }

    /**
     * messages of 邀请详情
     * @param messageEt
     * @param entityUserName
     * @param entityName
     * @return
     */
    private static String createInvitationMMssageDetail(PortalMessageEntity messageEt, String entityUserName, String entityName){
        int msgType = messageEt.getMsgType();
        switch (msgType){
            case 1:
                return "尊敬的用户您好，"+ entityUserName + "邀请您承接" +entityName+"的需求";
            case 2:
                return "尊敬的用户您好，"+ entityUserName +"邀请您应聘"+ entityName +"的工作";
            case 3:
                return "尊敬的用户您好，"+ entityUserName +"邀请您应聘"+ entityName +"的兼职";
            case 4:
                return "";
            case 5:
                return "尊敬的用户您好，"+ entityUserName +"邀请您回答"+ entityName +"的问题";
            default:
                return "";
        }
    }


    /**
     * messages of 关注 详情
     * @param messageEt
     * @param entityUserName
     * @param entityName
     * @param talentType 能人类型 1 代表发表了新的文章 、 2代表提出了新的问题
     * @return
     */
    private static String createFocusMessageDetail(PortalMessageEntity messageEt, String entityUserName, String entityName,Integer talentType) {
        int msgType = messageEt.getMsgType();
        switch (msgType){
            case 1:
                if (talentType == 1){
                    return "尊敬的用户您好，您关注的"+ entityUserName + "刚刚发表了一篇新的文章《"+ entityName +"》";
                }else if(talentType == 2){
                    return "尊敬的用户您好，您关注的"+ entityUserName + "刚刚提了一个新的问题"+ entityName;
                }
            case 2:
                String strDate = entityUserName;
                return "尊敬的用户您好，您关注的问题"+ entityName +"于"+ strDate +"，有了新的回答";
            default:
                return "";
        }
    }

    /**
     * messages of 关注 需求
     * @param messageEt
     * @param entityUserName
     * @param entityName
     * @return
     */
    private static String createRequirementMessageDetail(PortalMessageEntity messageEt, String entityUserName, String entityName) {
        int msgType = messageEt.getMsgType();
        switch (msgType){
            case 1:
                return "尊敬的用户您好，"+ entityUserName + "刚刚应聘了您发布的"+ entityName +"的职位";// 全职
            case 2:
                return "尊敬的用户您好，"+ entityUserName + "刚刚应聘了您发布的"+ entityName +"的职位";// 兼职
            case 3:
                return "尊敬的用户您好，"+ entityUserName +"刚刚承接了您发布的"+entityName+"的需求";// 需求
            default:
                return "";
        }
    }
    /*
     * 分类审核状态
     * @param reviewStatus
     * @return
     */
    private static String switchReviewStatus(String reviewStatus){
        switch (reviewStatus){
            case "0":
                return "未审核";
            case "1":
                return  "审核通过";
            case "2":
                return "审核驳回";
            default:
                return "";
        }
    }

    /**
     *  分类认证状态
     * @param applyType
     * @return
     */
    private static String switchApplyType(Integer applyType){
        switch (applyType){
            case 1:
                return "实名认证";
            case 2:
                return  "大咖认证";
            case 3:
                return "导师认证";
            default:
                return "";
        }
    }

    /**
     * 封装 MessageDto
     * @param messageEt
     * @return
     */
    private static PersonalCenterMessageDto encapMessageDto(PortalMessageEntity messageEt){
        if (messageEt!=null){
            PersonalCenterMessageDto messageDto = new PersonalCenterMessageDto();
            //messageDto.setPortalMessageEntity(messageEt);
            if (messageEt.getEventId() != null){
                messageDto.setEventId(messageEt.getEventId());
            }
            if (!(messageEt.getEventType() == MessageEventTypeEnum.FOCUS.getValue())){
                messageDto.setLookStatus(messageEt.getLookStatus());
                // type用于查看状态的修改 -1 为Message表 1或2为MessageRel表
                messageDto.setType(-1);
                messageDto.setMessageTime(messageEt.getCreateTime());
            }

            messageDto.setEventTypeEnum(messageEt.getEventType());
            messageDto.setMsgTypeEnum(messageEt.getMsgType());
            messageDto.setMessageId(messageEt.getId());
            messageDto.setExtra(messageEt.getExtra());
            return  messageDto;
        }
        return null;
    }

    private static  String getJdbcTemplateFiledValue(Integer createBy, Integer outsourcingprojectId){

        String sql = "\n" +
                "SELECT  (case WHEN\t\n" +
                "\t\t\t\t\t(select name from userpersonal where UserId = project.createBy ) is not null\n" +
                "\t\t\t\t\tthen\n" +
                "\t\t\t\t\t(select name from userpersonal where UserId = project.createBy ) \n" +
                "\t\t\t\t\telse\n" +
                "\t\t\t\t\t(select name from userenterprise where UserId = project.createBy) \n" +
                "\t\t\t\t\tend ) as requirementerName\n" +
                "FROM \n" +
                "outsourcingproject project\n" +
                "WHERE createBy = ? and id =?";
        String relatedName = "";
        try {
            relatedName  = jdbcTemplate.queryForObject(sql, new Object[]{createBy, outsourcingprojectId}, String.class);
        } catch (Exception e) {
            return relatedName;
        }
        return relatedName;
    }

    //  通过eventType 跟msgType获取 message
    public static List<PersonalCenterMessageDto> getMessageByType(MessageEventTypeEnum eventTypeEnum, MessageMsgTypeEnum msgTypeEnum){
        List<PersonalCenterMessageDto> messageEventTypeDtoList = new  ArrayList<PersonalCenterMessageDto>();
        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();
        if (currentUserLogin != null){
            Integer loginId = currentUserLogin.getId();
            List<PortalMessageEntity> loginUserMessagesByEventType = messageService.findMessageByType(loginId, eventTypeEnum,msgTypeEnum, null);
            if (!CollectionUtils.isEmpty(loginUserMessagesByEventType)){
                for (PortalMessageEntity messageEt : loginUserMessagesByEventType) {
                    PersonalCenterMessageDto messageDto = encapMessageDto(messageEt);
                    messageEventTypeDtoList.add(messageDto);
                }
            }
        }
        return messageEventTypeDtoList;
    }

    /**
     * 批量插入丢失Artcle文章到MessageRel
     * @param messageId
     * @param lostArtcleList
     * @param type
     */
    private static void batchAddLostArtcleMessageRel(Integer messageId, List<PortalCommonPageDetailEntity> lostArtcleList, Integer type){
        if (!CollectionUtils.isEmpty(lostArtcleList)){
            List<PortalMessageRelEntity> messageRelList = new ArrayList<PortalMessageRelEntity>();
            for (PortalCommonPageDetailEntity lostArtcle : lostArtcleList) {
                if (lostArtcle != null){
                    PortalMessageRelEntity mssageRelEt = new PortalMessageRelEntity();
                    mssageRelEt.setMessageId(messageId);
                    mssageRelEt.setType(type);
                    mssageRelEt.setLookStatus(false);
                    mssageRelEt.setCreateTime(lostArtcle.getCreateTime());
                    mssageRelEt.setRelEventId(lostArtcle.getId());
                    messageRelList.add(mssageRelEt);
                    //messageRelResponsitory.save(messageRelList);
                }
            }
            messageRelService.save(messageRelList);

        }
    }

    /**
     * 批量插入丢失的能人Ques到MessageRel
     * @param messageId
     * @param lostQuestionList
     * @param type
     */
    private static void batchAddLostQuesMessageRel(Integer messageId,  List<QuestionEntity> lostQuestionList, Integer type){
        if (!CollectionUtils.isEmpty(lostQuestionList)){
            List<PortalMessageRelEntity> messageRelList = new ArrayList<PortalMessageRelEntity>();
            for (QuestionEntity lostQuestion : lostQuestionList) {
                if (lostQuestion != null){
                    PortalMessageRelEntity mssageRelEt = new PortalMessageRelEntity();
                    mssageRelEt.setMessageId(messageId);
                    mssageRelEt.setType(type);
                    mssageRelEt.setLookStatus(false);
                    mssageRelEt.setCreateTime(lostQuestion.getCreateTime());
                    mssageRelEt.setRelEventId(lostQuestion.getId());
                    messageRelList.add(mssageRelEt);
                    //messageRelService.save(mssageRelEt);
                    String t = "quchong5";
                }
            }
            messageRelService.save(messageRelList);
        }
    }

    /**
     * 批量插入丢失的能人Ques到MessageRel
     * @param messageId
     * @param lostQuesReplyList
     * @param type
     */
    private static void batchAddLostReplyMessageRel(Integer messageId, List<QuestionreplyEntity> lostQuesReplyList, Integer type){
        if (!CollectionUtils.isEmpty(lostQuesReplyList)){
            List<PortalMessageRelEntity> messageRelList = new ArrayList<PortalMessageRelEntity>();
            for (QuestionreplyEntity lostQuesReply : lostQuesReplyList) {
                if (lostQuesReply != null){
                    PortalMessageRelEntity mssageRelEt = new PortalMessageRelEntity();
                    mssageRelEt.setMessageId(messageId);
                    mssageRelEt.setType(type);
                    mssageRelEt.setLookStatus(false);
                    mssageRelEt.setCreateTime(lostQuesReply.getCreateTime());
                    mssageRelEt.setRelEventId(lostQuesReply.getId());
                    messageRelList.add(mssageRelEt);
                    //messageRelService.save(mssageRelEt);
                    String t = "quchong6";
                }
            }
            messageRelService.save(messageRelList);
        }
    }


    /**
     *  清除残存 message 脏数据
     * @return
     */
    public static void clearThisMessage(Object object, PortalMessageEntity messageEt){
        if ((object == null || object == "") && messageEt != null){
            messageService.deleteOne(messageEt.getId());
        }
    }

    /**
     * 处理时间
     * @param date
     * @param type 1：到日时间， 2：到分时间
     * @return
     */
    private static String switchTime(Date date,Integer type){
        if (date != null && type == 1){
            String strDate = new SimpleDateFormat("yyyy年MM月dd日").format(date);
            return strDate;
        }else if(date != null && type == 2){
            String strDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
            return strDate;
        }
        else{
            return "";
        }
    }

    /**
     *   统计未读的消息
      */
    public static  Integer countUnreadMsgNum(){
        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();
        if (currentUserLogin != null){
          return messageResponsitory.countUnreadMsgNum(currentUserLogin.getId());
        }else {
            return null;
        }
    }
    /**
     *  total消息
     */
    public static  Integer countTotalMsgNum(){
        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();
        if (currentUserLogin != null){
            return messageResponsitory.countTotalMsgNum(currentUserLogin.getId());
        }else {
            return null;
        }
    }

    /**
     * 分类统计未读消息
     */
    public static  Integer countUnreadNumByType(MessageEventTypeEnum eventType){
        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();
        if (currentUserLogin != null){
            return messageResponsitory.countUnreadNumByType(currentUserLogin.getId(),eventType.getValue());
        }else {
            return null;
        }
    }

    /**
     * 分类total消息
     */
    public static  Integer countTotalMsgNumByType(MessageEventTypeEnum eventType){
        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();
        if (currentUserLogin != null){
            return messageResponsitory.countTotalMsgNumByType(currentUserLogin.getId(),eventType.getValue());
        }else {
            return null;
        }
    }

    private  static void sortTime(List<PersonalCenterMessageDto> messageDtoList){
        Collections.sort(messageDtoList, new Comparator<PersonalCenterMessageDto>() {
            public int compare(PersonalCenterMessageDto arg0, PersonalCenterMessageDto arg1) {
                if (arg0.getMessageTime() != null && arg1.getMessageTime() != null){
                    long hits0 = arg0.getMessageTime().getTime();
                    long hits1 = arg1.getMessageTime().getTime();
                    if (hits1 > hits0) {
                        return 1;
                    } else if (hits1 == hits0) {
                        return 0;
                    } else {
                        return -1;
                    }
                }else {
                    return -1;
                }
            }
        });

    }

    public  static  Integer getQuesFoucsNum(Integer quesId,MessageEventTypeEnum eventTypeEnum, MessageMsgTypeEnum msgTypeEnum){
        if (quesId != null){
           return messageRelResponsitory.getQuesFoucsNum(quesId,eventTypeEnum.getValue(),msgTypeEnum.getMsgType());
        }else {
            return null;
        }
    }
}
