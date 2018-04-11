package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.ggfw.CommunitySummary;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.PageRequest;

import java.awt.print.Pageable;

/**
 * Created by luanyu on 2017/5/22.
 */
public interface CommunityService {
    //get all news & activities
    PageInfo<CommunitySummary> findCommunityList(PageRequest pr);
}
