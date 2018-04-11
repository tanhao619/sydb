package com.cdyoue.oddJobs.service;


import com.cdyoue.oddJobs.dto.lgfc.TalentSummary;

import java.util.List;

/**
 * Created by liaoyoule on 2017/4/24.
 */
public interface PortalMessageService {
    /**
     * 查询承接需求的人才
     * @param id
     * @param aByte
     * @param eventType
     * @return
     */
    List<TalentSummary> findAcceptPeoples(Integer id, Byte aByte, Byte eventType);
}
