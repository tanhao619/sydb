package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.zscq.IndustryReportDetail;
import com.cdyoue.oddJobs.dto.zscq.IndustryReportSummary;
import com.cdyoue.oddJobs.entity.lgsq.PortalIndustryReportsEntity;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.Pageable;

/**
 * Created by admin on 2017/5/23.
 */


public interface IndustryReportService {
    /**
     * 创建产业链报告
     * @param industryReportDetail
     * @return id
     */
    Integer createReport(IndustryReportDetail industryReportDetail);

    /**
     * 更新产业链报告
     * @param id
     * @param industryReportDetail
     */
    Integer updateReport(Integer id, IndustryReportDetail industryReportDetail);

    /**
     * 查找产业链列表
     * @param q
     * @param requestPage
     * @return PageInfo
     */
    PageInfo<IndustryReportSummary> findByKeyWord(Pageable requestPage, String q);

    /**
     * 查找某个
     * @param
     * @param
     * @return PageInfo
     */
    IndustryReportDetail getReportById(Integer id);

    /**
     * 删除产业链报告
     * @param id
     */
    void deleteReport(Integer id);

    /**
     * 根据id获取产业链报告实体
     */
    PortalIndustryReportsEntity findOne(Integer id);
}
