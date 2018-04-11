package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dto.lgfc.Reply4User;
import com.cdyoue.oddJobs.entity.lgsq.QuestionreplyEntity;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * Created by dengshaojun on 2017/5/2.
 */
@Component
public class Reply4UserMapper {

    /**
     * ReplyEntity转Reply4User
     * @param questionreplyEntity
     * @return Reply4User
     */
    public Reply4User replyEntity2Reply4User(QuestionreplyEntity questionreplyEntity) {
        Reply4User reply4User = new Reply4User();
        reply4User.setCreateTime(new SimpleDateFormat("yyyy-MM-dd").format(questionreplyEntity.getCreateTime()));
        reply4User.setContext(questionreplyEntity.getContext());
        return reply4User;
    }

}
