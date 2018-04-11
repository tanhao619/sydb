package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.zscq.*;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.PageRequest;

/**
 * Created by Administrator on 2017/5/12.
 */
public interface PortalIntellectualSalePatentService {
    /**
     * 专利商标出售
     * @param patent
     * @return
     */
    Integer createPatent(Patent patent);

    /**
     * 获取专利详情
     * @param id
     * @return
     */
    PatentDetail getIntelPatentById(Integer id);

    /**
     * 删除专利
     * @param id
     */
    void deleteIntelPatentById(Integer id);

    /**
     * 编辑专利
     * @param tid
     * @param intellectual
     */
    void updateIntelPatent(Integer tid, Patent intellectual);

    /**
     * 获取出售专利列表
     * @param q
     * @param pr
     * @return
     */
    PageInfo<IntellectualSaleSummary> getIntellectualSalesPatent(String q,Integer transactionType, PageRequest pr);

    /**
     * 通过专利出售
     * @param id
     * @param reviewReason
     */
    void approveIntellectualSalePatent(Integer id, IntellectualMIni reviewReason);

    /**
     * 拒绝专利出售
     * @param id
     * @param reviewReason
     */
    void rejectIntellectualSalePatent(Integer id, IntellectualMIni reviewReason);

    /**
     * 置顶
     * @param id
     */
    void topSalePatent(Integer id,String topImg);

    /**
     * 取消置顶
     * @param id
     */
    void removeSalePatent(Integer id);
}
