package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.PortalRequirementMessageResponsitory;
import com.cdyoue.oddJobs.dto.lgfc.TalentSummary;
import com.cdyoue.oddJobs.entity.lgsq.RequirementMessageEntity;
import com.cdyoue.oddJobs.mapper.PortalMessageMapper;
import com.cdyoue.oddJobs.service.PortalMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by liaoyoule on 2017/4/24.
 */
@Service
public class PortalMessageServiceImpl implements PortalMessageService {
    @Autowired
    private PortalRequirementMessageResponsitory portalRequirementMessageResponsitory;
    @Autowired
    private PortalMessageMapper portalMessageMapper;


    @Override
    public List<TalentSummary> findAcceptPeoples(Integer id, Byte msgType, Byte eventType) {
        List<RequirementMessageEntity> pmes =  portalRequirementMessageResponsitory.findAcceptPeoples(id,msgType,eventType);
        return  pmes.stream().map(pme -> portalMessageMapper.portalMessageToTalentSummary(pme)).collect(Collectors.toList());
    }
}
