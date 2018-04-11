package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.PortalWordResponsitory;
import com.cdyoue.oddJobs.dto.lgfc.Word4Work;
import com.cdyoue.oddJobs.entity.lgsq.PortalWordEntity;
import com.cdyoue.oddJobs.service.PortalWordService;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * Created by dengshaojun on 2017/5/25.
 */
@Service
public class PortalWordServiceImpl implements PortalWordService {

    @Autowired
    private PortalWordResponsitory portalWordResponsitory;


    @Override
    public void save4Work(Integer id, Word4Work word4Work, Integer workType) {
        PortalWordEntity portalWordEntity = new PortalWordEntity();
        portalWordEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        portalWordEntity.setInfo(word4Work.getInfo());
        portalWordEntity.setWordType(workType);
        portalWordEntity.setTel(word4Work.getTel());
        portalWordEntity.setUserId(SecurityUtils.getCurrentUserLogin().getId());
        portalWordEntity.setEventId(id);
        portalWordResponsitory.save(portalWordEntity);
    }
}
