package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.zscq.IntellectualBuyBanner;
import com.cdyoue.oddJobs.dto.zscq.IntellectualSaleBanner;
import com.cdyoue.oddJobs.dto.zscq.IntellectualSaleSummary;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * Created by dengshaojun on 2017/6/1.
 */
public interface PortalIntellectualSaleService {

    PageInfo<IntellectualSaleSummary> getIntellectualSales(String q, Integer transactionType, PageRequest pr);

    /**
     * 获取出售banner图
     * @return
     */
    List<IntellectualSaleBanner> getSaleBanners();

    /**
     * 获取求购banner图
     * @return
     */
    List<IntellectualBuyBanner> getBuyBanners();

    /**
     * 知产求购一键置顶
     * @param id
     */
    void buyTop(Integer id,String topImg);

    /**
     * 取消知产求购一键置顶
     * @param id
     */
    void removeBuyTop(Integer id);
}
