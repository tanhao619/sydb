package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dao.lqsq.PortalIncubatorLevelResponsitory;
import com.cdyoue.oddJobs.dto.scfw.Incubator;
import com.cdyoue.oddJobs.dto.scfw.IncubatorInfo;
import com.cdyoue.oddJobs.entity.lgsq.PortalIncubatorEntity;
import com.cdyoue.oddJobs.entity.lgsq.PortalIncubatorLevelEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/5/11.
 */
@Component
public class IncubatorMapper {
    @Autowired
    PortalIncubatorLevelResponsitory portalIncubatorLevelResponsitory;

    public IncubatorInfo entityToDto(PortalIncubatorEntity entity) {
        IncubatorInfo incubatorDTO = new IncubatorInfo();
        PortalIncubatorLevelEntity levelEntity = portalIncubatorLevelResponsitory.findOne(entity.getLevelId());
        BeanUtils.copyProperties(entity,incubatorDTO);
        incubatorDTO.setLogo(levelEntity.getCoverImg());
        return incubatorDTO;
    }

    public PortalIncubatorEntity dtoToRntity(Incubator incubator) {
        PortalIncubatorEntity entity = new PortalIncubatorEntity();
        BeanUtils.copyProperties(incubator,entity);
        return entity;
    }
}
