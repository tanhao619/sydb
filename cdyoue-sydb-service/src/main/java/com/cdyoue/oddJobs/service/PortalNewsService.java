package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.Reason;
import com.cdyoue.oddJobs.dto.ggfw.NewsDetail;
import com.cdyoue.oddJobs.dto.ggfw.NewsRequest;
import com.cdyoue.oddJobs.dto.ggfw.NewsSummary;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.Pageable;

/**
 * Created by John on 2017/5/9.
 */
public interface PortalNewsService {
    /**
     * 查询指定页面的新闻列表
     *
     * @param q
     * @param reviewStatus
     *@param requestPage  @return
     */
    PageInfo findNewsPage(String q, Integer reviewStatus, Pageable requestPage);

    void save(NewsRequest news);

    NewsDetail findById(Integer id);

    PageInfo<NewsSummary> findByUid(Integer userid, Pageable pageRequest);

    Boolean isExistById(Integer id);

    Boolean updateById(Integer id, NewsRequest newsRequest);

    Boolean deleteById(Integer id);

    void reviewNews(Integer id, Integer status, Reason reason);
}
