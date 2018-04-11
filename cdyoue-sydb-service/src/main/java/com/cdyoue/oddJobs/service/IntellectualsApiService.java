package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.zscq.Intellectual;
import com.cdyoue.oddJobs.dto.zscq.IntellectualDetail;
import com.cdyoue.oddJobs.dto.zscq.IntellectualMine;
import com.cdyoue.oddJobs.dto.zscq.IntellectualSummary;
import com.cdyoue.oddJobs.entity.lgsq.PortalIntellectualBuyEntity;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.Pageable;

/**
 * Created by liaoyoule on 2017/4/27.
 */
public interface IntellectualsApiService {
    /**
     *
     * @return
     */
    PageInfo<IntellectualSummary> getIntellectuals(String q,Integer intellType, Integer businessType, Pageable requestPage);

    /**
     * 发布知产求购
     * @param intellectual
     * @return id
     */
    Integer createIntellectualBuy(Intellectual intellectual);

    /**
     * 获取某个知产求购
     * @param id
     * @return portalIntellectualBuyEntity
     */
    PortalIntellectualBuyEntity findIntellectualBuy(Integer id);

    /**
     * 批准知产求购
     * @param pib
     *
     */
    void approveIntellectualBuy(PortalIntellectualBuyEntity pib,String reason);

    /**
     * 拒绝知产求购
     * @param pib
     *
     */
    void rejectIntellectualBuy(PortalIntellectualBuyEntity pib,String reason);

    /**
     * 获取我发布的知产求购
     * @param intellType
     * @param requestPage
     */
    PageInfo<IntellectualMine> findMyIntellectualBuy(Pageable requestPage, Integer intellType, Integer businessType);

    /**
     * 获取知产求购详情
     * @param id
     */
    IntellectualDetail getIntellectualBuyById(Integer id);

    void deleteByPrimary(Integer id);

    void updateIntellectual(Integer id,Intellectual intellectual);
}
