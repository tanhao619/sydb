package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.common.Category;

import java.util.List;

/**
 * Created by liaoyoule on 2017/4/20.
 */
public interface CategoryService {
    /**
     * 查询类别
     * @param
     * @return
     */
    List<Category> findRequirementType();

    /**
     * 行业       互联网
                 企业服务
                 金融
                 设计
     * @return
     */
    List<Category> findTrades();
    /**
     * 查询行业
     *          度假
                旅游
                居民
                社区
     * @return
     */
    List<Category> findPros(Integer id);
}
