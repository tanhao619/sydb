package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.scfw.InvestorDetail;
import com.cdyoue.oddJobs.dto.scfw.InvestorSummary;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.Pageable;

/**
 * Created by liaoyoule on 2017/4/27.
 */
public interface InvestorsApiService {
    /**
     *
     * @return
     */
    PageInfo<InvestorSummary> getInvestors(String q, Pageable requestPage);

    /**
     * 新增投资人
     */
    Integer createInvestor(InvestorDetail investorDetail);

    /**
     * 根据id获取投资人
     */
    InvestorDetail getInvestorById(Integer id);

    /**
     * 根据id删除投资人
     */
    void deleteInvestor(Integer id);

    /**
     * 根据id修改投资人
     */
    void updateInvestor(Integer id, InvestorDetail investorDetail);
}
