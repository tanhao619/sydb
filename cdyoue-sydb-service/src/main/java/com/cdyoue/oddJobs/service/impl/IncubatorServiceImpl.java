package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.PortalIncubatorResponsitory;
import com.cdyoue.oddJobs.dto.scfw.Incubator;
import com.cdyoue.oddJobs.dto.scfw.IncubatorInfo;
import com.cdyoue.oddJobs.entity.lgsq.PortalIncubatorEntity;
import com.cdyoue.oddJobs.mapper.IncubatorMapper;
import com.cdyoue.oddJobs.service.IncubatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/5/11.
 */
@Service
public class IncubatorServiceImpl implements IncubatorService {
    @Autowired
    PortalIncubatorResponsitory portalIncubatorResponsitory;
    @Autowired
    IncubatorMapper incubatorMapper;

    @Override//获取一个孵化器信息
    public IncubatorInfo getIncubatorById(Integer id) {
        IncubatorInfo incubatorDTO = new IncubatorInfo();
        PortalIncubatorEntity entity = portalIncubatorResponsitory.findOne(id);
        incubatorDTO = incubatorMapper.entityToDto(entity);
        return incubatorDTO;
    }

    @Override//修改一个孵化器信息
    public void updateArticle(Integer id, Incubator incubator) {
        PortalIncubatorEntity entity = incubatorMapper.dtoToRntity(incubator);
        entity.setId(id);
        portalIncubatorResponsitory.save(entity);
    }
}
