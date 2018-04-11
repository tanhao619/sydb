package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.zscq.IntellectualSaleMine;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.Pageable;

/**
 * Created by Administrator on 2017/5/18.
 */
public interface PortalIntellectualSaleMineService {
    /**
     * 获取我的出售列表
     * @param requestPage
     * @return
     */
    PageInfo<IntellectualSaleMine> getMyIntellectualSales(Pageable requestPage);
}
