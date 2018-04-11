package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.QuestionEntity;
import com.cdyoue.oddJobs.entity.lgsq.QuestionreplyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by dengshaojun on 2017/5/2.
 */
public interface QuestionReplyResponsitory extends JpaCustomResponsitory<QuestionreplyEntity, Integer> {

    @Query("select  count(qre.questionEntity.id) from QuestionreplyEntity qre where qre.questionEntity.id = ?1")
    int selectReplyNum(Integer id);

    @Modifying
    @Query("delete from QuestionreplyEntity qre where qre.questionEntity.id=?1")
    void deleteQuestionById(Integer id);

    @Query("select qre from QuestionreplyEntity qre where qre.userId = ?1 and qre.questionEntity.id = ?2")
    List<QuestionreplyEntity> findByUseridAndquestionid(Integer userid, Integer questionid);

    @Query("select distinct qe from QuestionreplyEntity qre inner join qre.questionEntity qe where qre.id in ?1")
    Page<QuestionEntity> findByIdIn(List<Integer> replyids, Pageable pageable);

    @Query("select qre from QuestionreplyEntity qre where qre.id in ?1")
    Page<QuestionreplyEntity> findByIdsIn(List<Integer> replyids, Pageable pageable);

    //@Query("select distinct q from QuestionEntity q, QuestionreplyEntity qre where  q.id = qre.questionId and qre.userId = ?1")
    @Query("select distinct qe from QuestionreplyEntity qre inner join qre.questionEntity qe where qre.userId = ?1 order by qre.createTime DESC ")
    Page<QuestionEntity> findReplyQuestionByUserId(Integer userid, Pageable page);

    @Query("select qre from QuestionreplyEntity qre where qre.userId = ?1 order by qre.createTime DESC ")
    Page<QuestionreplyEntity> findReplyByUserId(Integer userid, Pageable page);

    @Query("select distinct qe from QuestionreplyEntity qre inner join qre.questionEntity qe where qre.userId = ?1 and qre.questionEntity.title like ?2 order by qre.createTime DESC")
    Page<QuestionEntity> findReplyQuestionByUserIdStrKey(Integer userid,String q, Pageable page);

    @Query("select  count(qre.id) from QuestionreplyEntity qre where qre.questionEntity.portalTopicEntity.id = ?1")
    int selectReplyNumByTopicid(Integer id);

    @Query("select qre from QuestionreplyEntity qre where qre.questionEntity.id = ?1 order by qre.createTime DESC ")
    Page<QuestionreplyEntity> findByQuestionid(Integer questionid, Pageable pageable);

    @Query("select qrep from QuestionreplyEntity qrep where qrep.questionEntity.id = ?1 AND qrep.createTime >= ?2 order by qrep.createTime DESC ")
    List<QuestionreplyEntity> findReplyByQuestionId(Integer questionid,Date createTime);
}
