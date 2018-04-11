package com.cdyoue.oddJobs.service.sydb;

import com.cdyoue.oddJobs.dto.SyCooperativePartnerDTO;
import com.cdyoue.oddJobs.dto.SyCooperativePartnerMiniDTO;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Tanhao on 2017/9/26.
 */
public interface CooperativePartnerService {
    /**
     * 获取合作伙伴列表
     * @return
     */
    List<SyCooperativePartnerDTO> getPartner();

    /**
     * 新增合作伙伴
     * @param miniDTO
     */
    void insertPartner(SyCooperativePartnerMiniDTO miniDTO);

    /**
     * 分页获取合作伙伴列表
     * @param q
     * @param requestPage
     * @return
     */
    PageInfo<SyCooperativePartnerDTO> getPagePartners(String q, Pageable requestPage);

    /**
     * 删除合作伙伴信息
     * @param id
     */
    void deletePartner(Integer id);

    /**
     * 批量删除
     * @param ids
     */
    void deleteByIds(Integer[] ids);

    /**
     * 更新
     * @param miniDTO
     */
    void updatePartner(Integer id,SyCooperativePartnerMiniDTO miniDTO);

    /**
     * 获取详情
     * @param id
     * @return
     */
    SyCooperativePartnerDTO getPartnerDetail(Integer id);
}
