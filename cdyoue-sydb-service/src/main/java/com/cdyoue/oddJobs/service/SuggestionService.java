package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.other.SuggestionDetail;
import com.cdyoue.oddJobs.dto.other.SuggestionRequest;
import com.cdyoue.oddJobs.dto.other.SuggestionSummary;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.PageRequest;

/**
 * Created by tr on 2017/6/12.
 */
public interface SuggestionService {
    Integer createSuggestion(Integer userId, SuggestionRequest suggestionRequest);

    void deleteSuggestion(Integer id);

    SuggestionDetail getSuggestionById(Integer id);

    PageInfo<SuggestionSummary> getSuggestions(String q, PageRequest pageRequest);
}
