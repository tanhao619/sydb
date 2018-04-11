package com.cdyoue.oddJobs.service.sydb;

import com.cdyoue.oddJobs.dto.SyCooperativePartnerDTO;
import com.cdyoue.oddJobs.dto.zscq.AssessDetailDTO;
import com.cdyoue.oddJobs.dto.zscq.AssessDetailSummer;
import com.cdyoue.oddJobs.entity.syData.SyPortalAssessEntity;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.Pageable;

/**
 * Created by Tanhao on 2017/9/22.
 */
public interface SyAssessService {
    /**
     * 新增知产评估
     * @param assessEntity
     */
    void insertAssess(AssessDetailSummer assessEntity);

    /**
     * 获得知产评估详情
     * @param id
     */
    AssessDetailDTO getAssessById(Integer id);

    /**
     * 获取列表
     * @param q
     * @param requestPage
     * @return
     */
    PageInfo<AssessDetailDTO> getAssessList(String q, Pageable requestPage);

    /**
     * 删除评估
     * @param id
     */
    void deleteAssess(Integer id);

    /**
     * 批量删除评估
     * @param ids
     */
    void deleteAllAssess(Integer[] ids);
}
