package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.ggfw.Govproject;
import com.cdyoue.oddJobs.dto.ggfw.GovprojectSummary;
import com.cdyoue.oddJobs.entity.lgsq.PortalGovprojectReportEntity;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.Pageable;

/**
 * Created by tr on 2017/5/22.
 */
public interface GovprojectService {

    /**
     * 关键字搜索政府项目申报
     * @param q 关键字
     * @param requestPage
     * @return 政府项目申报列表
     */
    PageInfo<GovprojectSummary> findGovprojectByKeyWord (Pageable requestPage, String q, Integer timeFilter);

    /**
     * 创建政府项目申报
     * @param govproject
     * @return id
     */
    Integer createGovproject(Govproject govproject);

    /**
     * 根据id获取政府项目详情
     * @param id
     * @return Govproject
     */
    Govproject getGovprojectById(Integer id);

    /**
     * 根据id修改政府项目申报
     * @param id
     *
     */
    void updateGovproject(Integer id, Govproject govproject);

    /**
     * 根据id修获取实体
     * @param id
     *
     */
    PortalGovprojectReportEntity getGovprojectEntityById(Integer id);

    /**
     * 根据id删除政府项目
     * @param id
     *
     */
    void deleteGovProject(Integer id);
}
