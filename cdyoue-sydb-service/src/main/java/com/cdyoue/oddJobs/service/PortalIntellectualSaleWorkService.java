package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.zscq.*;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.PageRequest;

/**
 * Created by Administrator on 2017/5/12.
 */
public interface PortalIntellectualSaleWorkService {
    /**
     * 创建著作权出售
     * @param copyright
     * @return
     */
    Integer createWork(Copyright copyright);

    /**
     * 获取著作权详情
     * @param id
     * @return
     */
    CopyrightDetail getWorkById(Integer id);

    /**
     * 删除著作权
     * @param id
     */
    void deleteIntelCopyright(Integer id);

    /**
     * 编辑著作权
     * @param tid
     */
    void updateIntelCopyright(Integer tid, Copyright intellectual);

    /**
     * 获取出售著作权列表
     * @param q
     * @param pr
     * @return
     */
    PageInfo<IntellectualSaleSummary> getIntellectualSalesWork(String q,Integer transactionType, PageRequest pr);

    /**
     * 通过著作权出售
     * @param id
     * @param reviewReason
     */
    void approveIntellectualSaleWork(Integer id, IntellectualMIni reviewReason);

    /**
     * 拒绝著作权出售
     * @param id
     * @param reviewReason
     */
    void rejectIntellectualSaleWork(Integer id, IntellectualMIni reviewReason);

    /**
     * 置顶
     * @param id
     */
    void topSaleWork(Integer id,String topImg);

    /**
     * 取消置顶
     * @param id
     */
    void removeSaleWork(Integer id);
}
