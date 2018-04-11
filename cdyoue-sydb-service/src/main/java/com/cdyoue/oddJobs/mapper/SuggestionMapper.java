package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dao.lqsq.UserResponsitory;
import com.cdyoue.oddJobs.dto.other.SuggestionDetail;
import com.cdyoue.oddJobs.dto.other.SuggestionRequest;
import com.cdyoue.oddJobs.dto.other.SuggestionSummary;
import com.cdyoue.oddJobs.entity.lgsq.SuggestionEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tr on 2017/6/12.
 */

@Component
public class SuggestionMapper {
    @Autowired
    private UserResponsitory userResponsitory;
    public SuggestionEntity suggestionRequestToEntity(Integer userId, SuggestionRequest suggestionRequest) {
        SuggestionEntity se = new SuggestionEntity();
        se.setContact(suggestionRequest.getContact());
        se.setContent(suggestionRequest.getContent());
        StringBuilder sb = new StringBuilder();
        for (String s : suggestionRequest.getPicUrls()){
            sb.append(s);
            sb.append(",");
        }
        se.setCoverImg(sb.substring(0, sb.lastIndexOf(",")));
        se.setCreateBy(userId);
        se.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return se;
    }

    public SuggestionDetail entityToDetail(SuggestionEntity se) {
        SuggestionDetail sd = new SuggestionDetail();
        sd.setContent(se.getContent());
        sd.setContact(se.getContact());
        UserEntity u = userResponsitory.findOne(se.getCreateBy());
        if (u.getUserType().intValue() == 0) sd.setCreateBy(u.getUserpersonalEntity().getName());
        if (u.getUserType().intValue() == 1) sd.setCreateBy(u.getUserenterpriseEntity().getName());
        List<String> list = new ArrayList<>();
        String[] urls = se.getCoverImg().split(",");
        for (int i = 0; i<urls.length; i++){
            list.add(urls[i]);
        }
        sd.setPicUrls(list);
        return sd;
    }

    public SuggestionSummary toSuggestionSummary(SuggestionEntity se) {
        SuggestionSummary ss = new SuggestionSummary();
        ss.setId(se.getId());
        ss.setContent(se.getContent());
        UserEntity u = userResponsitory.findOne(se.getCreateBy());
        if (u.getUserType().intValue() == 0) ss.setPublishPeople(u.getUserpersonalEntity().getName());
        if (u.getUserType().intValue() == 1) ss.setPublishPeople(u.getUserenterpriseEntity().getName());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ss.setPublishTime(sdf.format(se.getCreateTime()));
        return ss;
    }
}
