package com.cdyoue.oddJobs.service.sydb;

import com.cdyoue.oddJobs.dto.zlcy.WebsiteRequest;
import com.cdyoue.oddJobs.dto.zlcy.WebsiteSummary;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Created by dengshaojun on 2017/10/10.
 */
public interface WebsiteService {

    PageInfo<WebsiteSummary> findWebsites(String q, Pageable pageRequest);

    void saveWebsite(WebsiteRequest websiteRequest);

    void updateWebsite(Integer id, WebsiteRequest websiteRequest);

    void deleteWebsite(Integer[] ids);

    List<WebsiteSummary> findWebsite(Sort order);
}
