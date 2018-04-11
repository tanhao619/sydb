package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.lgfc.QuestionDetail;
import com.cdyoue.oddJobs.dto.lgfc.ReplyDetails;
import com.cdyoue.oddJobs.entity.lgsq.QuestionreplyEntity;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * Created by dengshaojun on 2017/5/2.
 */
public interface ReplyService {

    void createReply4Question(Integer questionid, String reply);

    ReplyDetails findOne(Integer id);

    void updateCollectById(Integer id, int favorCount);

    void updateLikeById(Integer id, int likeCount);

    QuestionreplyEntity findById(Integer id);

    PageInfo<QuestionDetail> getUserReply(PageRequest pageRequest, Integer userid);

    PageInfo<QuestionDetail> getMyColReply(List<Integer> replyids, PageRequest pageRequest);

    PageInfo<ReplyDetails> getReplyByQuestionid(PageRequest pageRequest, Integer questionid);

    void deleteById(Integer id);
}
