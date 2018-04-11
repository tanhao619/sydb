package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.zscq.*;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.PageRequest;

/**
 * Created by Administrator on 2017/5/12.
 */
public interface PortalIntellectualSaleBrandService {
    /**
     * 创建商标出售
     * @param trademark
     * @return
     */
    Integer createBrand(Trademark trademark);

    /**
     * 获取商标详情
     * @param id
     * @return
     */
    TrademarkDetail getIntelTrademarkById(Integer id);

    /**
     * 删除商标
     * @param id
     */
    void deleteIntelTrademarkById(Integer id);

    /**
     * 编辑商标
     * @param tid
     * @param intellectual
     */
    void updateIntelTrademark(Integer tid, Trademark intellectual);

    /**
     * 获取出售商标列表
     * @param q
     * @param pr
     * @return
     */
    PageInfo<IntellectualSaleSummary> getIntellectualSalesBrand(String q,Integer transactionType, PageRequest pr);

    /**
     * 通过商标出售
     * @param id
     */
    void approveIntellectualSaleBrand(Integer id, IntellectualMIni reviewReason);

    /**
     * 拒绝商标出售
     * @param id
     * @param reviewReason
     */
    void rejectIntellectualSaleBrand(Integer id, IntellectualMIni reviewReason);

    /**
     * 置顶
     * @param id
     */
    void topSaleBrand(Integer id,String topImg);

    /**
     * 取消置顶
     * @param id
     */
    void removeSaleBrand(Integer id);
}
