package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.independent.IndependentMine;
import com.cdyoue.oddJobs.dto.independent.IndependentSumary;
import com.cdyoue.oddJobs.dto.independent.RequestIndependent;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.PageRequest;

/**
 * Created by liaoyoule on 2017/5/11.
 */
public interface PortalIndependentTextService {
    /**
     * 发布独立发布
     *
     * @param type
     * @param publish
     * @return
     */
    Integer save(Integer type, RequestIndependent publish);

    /**
     * 删除独立发布
     * @param id
     */
    void delete(Integer id);

    /**
     * 获取独立发布详情
     * @param id
     * @return
     */
    IndependentSumary findById(Integer id);

    /**
     * 获取独立发布列表
     *
     * @param type
     * @param q
     * @param pr
     * @return
     */
    PageInfo<IndependentMine> getIndependents(Integer type, String q, PageRequest pr);

    /**
     * 编辑独立发布
     * @param id
     * @param publish
     * @return
     */
    Integer updateIndependent(Integer id, RequestIndependent publish);

    /**
     * 增加阅读量
     * @param id
     * @return
     */
    Integer addViewNum(Integer id);
}
