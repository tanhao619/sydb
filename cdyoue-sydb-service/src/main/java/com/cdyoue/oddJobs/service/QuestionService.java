package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.lgfc.*;
import com.cdyoue.oddJobs.entity.lgsq.QuestionEntity;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


/**
 * Created by sky on 2017/5/2.
 */
public interface QuestionService {
    /**
     * 关键字搜索问题
     * @param q 关键字
     * @param requestPage
     * @return 问题列表
     */
    PageInfo<QuestionSummary> findByKeyWord(String q, Pageable requestPage);

    /**
     * 根据话题ID查询问题列表
     * @param id
     * @param requestPage
     * @return
     */

    PageInfo<QuestionSummary> findQuestionByTopicId(Integer id, Pageable requestPage);
    /**
     * 查找推荐的问题
     * @param data
     * @param q
     * @param requestPage
     * @return
     */
    PageInfo<QuestionSummary> findRecommendeQuestion(List<QuestionData> data, String q, Pageable requestPage);
    /**
     * 查询某个用户发布的问题列表
     * @param userid
     * @param q 关键字
     * @param requestPage
     * @return
     */
    PageInfo<QuestionSummary> findByKeyWordUserId(Integer userid,String q, Pageable requestPage);

    /**
     * 查询我发布的问题
     * @param userid
     * @param requestPage
     * @return
     */
    PageInfo<QuestionSummary> findCreateQuestionByUserId(Integer userid,String q,Pageable requestPage);

    /**
     * 查询我回答的问题
     * @param userid
     * @param requestPage
     * @return
     */
    Page<QuestionEntity> findReplyQuestionByUserId(Integer userid, Pageable requestPage);

    Page<QuestionEntity> findReplyQuestionByUserIdStrKey(Integer userid,String q, Pageable requestPage);
    /**
     * 获取Top问题，比如关注度高的，参与度高的
     * @param type 类型，关注度或者参与度
     * @return
     */
    List<QuestionMini> findTopQuestion(String type);

    /**
     * 查找某个问题
     * @param id
     * @return
     */
    QuestionEntity findOneQuestion(Integer id);
    /**
     * 查询问题的回答数量
     * @param id 问题的id
     * @return
     */
    int selectReplyNum(Integer id);

    /**
     * 创建问题
     * @param question 问题实体信息
     * @return
     */
    Integer createQuestion(QuestionRequest question);

    /**
     *  为指定话题创建问题
     * @param question
     * @return
     */
    Integer createQuestion4Topic(Integer topicid,QuestionRequest question);

    /**
     * 关注问题
     * @param id
     * @return
     */
    void followQuestion(Integer id);

    /**
     * 取消关注问题
     * @param id
     * @return
     */
    void unFollowQuestion(Integer id);

    /**
     * 编辑问题
     * @param id
     * @param question
     */
    void updateQuestion(Integer id,QuestionRequest question);

    /**
     * 删除问题
     * @param id
     */
    void deleteQuestion(Integer id);

    Boolean isExist(Integer id);
    /**
     * 获取我关注的问题列表
     */
    PageInfo<QuestionBase> findMyCollectionsList(Pageable pr);
    PageInfo<QuestionBase> findMyCollectionsListByStrKey(List<Integer> focusQuestionIds,String q,Pageable pr);
    /**
     * 查询问题详情
     * @param id
     * @return
     */
    QuestionDetails selectQuestionDetails(Integer id);

    /**
     * 根据UserId查询Question
     * @param userId
     * @return
     */
    List<QuestionEntity> findQuestionByUserId(Integer userId);

    /**
     * 获取我的问答邀请列表
     * @param userId
     * @param q
     * @param requestPage
     * @return
     */
    PageInfo<QuestionSummary> findMyInviteQuestion(Integer userId, String q, Pageable requestPage);
}
