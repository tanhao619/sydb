package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.QuestionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by sky on 2017/5/2.
 */
public interface QuestionResponsitory extends JpaCustomResponsitory<QuestionEntity,Integer>{

   @Query("select qe from QuestionEntity qe where qe.id in ?1")
    Page<QuestionEntity> findRecommendByIdIn(Integer[] recommendId,Pageable pageable);

    @Query("select qe from QuestionEntity qe where qe.id in ?1 and qe.title like ?2")
    Page<QuestionEntity> findRecommendByStrKeyIdIn(Integer[] recommendId,String q,Pageable pageable);

    @Query("select  count(qe.id) from QuestionEntity qe where qe.portalTopicEntity.id = ?1")
    int selectQueNumByTopicid(Integer id);

    @Query("select distinct qe from QuestionEntity qe where qe.id in ?1 and qe.title like ?2")
    Page<QuestionEntity> findMyCollectionsListByStrKey(List<Integer> focusQuestionIds,String q,Pageable pageable);

    @Query("select qe from QuestionEntity qe where qe.topicId = ?1")
    Page<QuestionEntity> findQuestionByTopicId(Integer id, Pageable pageable);

    //@Query("select q.id,q.title from QuestionEntity q where q.id in(select a.id from (select questionid as id, sum(qr.likeCount) from QuestionreplyEntity qr  group by questionid order by 2 desc limit 0,2)a)")

    //@Query("select q.id,q.Title from QuestionEntity q where q.id in(select a.id from (select count(1) ,questionid as id from QuestionreplyEntity group by questionid order by 1 desc limit 0,2)a)")
    @Query("select ques from QuestionEntity ques where ques.userId =?1")
    List<QuestionEntity> findQuestionByUserId(Integer userId);

    @Modifying
    @Query("update QuestionEntity q set q.topicId = ?1 where q.id in ?2")
    void updateTopicidByIds(Integer topicId, List<Integer> questionIds);

    @Query("select q from QuestionEntity q where q.id in ?1")
    List<QuestionEntity> findByIds(List<Integer> questionIds);

    @Query("select q from QuestionEntity q where q.id in ?1")
    Page<QuestionEntity> findInviteQuestionByIds(List<Integer> questionIds,Pageable pageable);

    @Query(value = "select * from (select userId as createBy, null as title, applyType as type, reviewTime as time FROM lg_portal_real_name_info where reviewStatus = 1 UNION \n" +
            "SELECT createBy, title, 4 as type, publishTime as time from lg_portal_common_page_detail where status = 1 UNION \n" +
            "select UserId as createBy, title, 5 as type, createTime as time from question) as t1 order by time DESC LIMIT 0,12", nativeQuery = true)
    Object[] getOddJobNews();

    @Modifying
    @Query("delete from QuestionEntity q where q.id in ?1")
    void customDelete(Integer id);

    @Query(value = "SELECT * FROM question WHERE userId = ?1 AND CreateTime >= ?2",nativeQuery = true)
    List<QuestionEntity> findQuestionByUserIdAndTime(Integer userId, Date createTime );
}
