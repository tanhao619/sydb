package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dao.lqsq.PortalAreaResponsitory;
import com.cdyoue.oddJobs.dto.AreaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/11.
 */
@Component
public class AreaMapper {
    @Autowired
    PortalAreaResponsitory portalAreaResponsitory;

    public List<AreaDTO> entityMapToDtoList(List<Integer> fristAreaLevels, Map<Integer, List<String>> area) {
        List<AreaDTO> areaDTOs = new ArrayList<>();
        for (Integer i:fristAreaLevels) {
            AreaDTO areaDTO = new AreaDTO();
            areaDTO.setFristArea(portalAreaResponsitory.findName(i));
            areaDTO.setSecondArea(area.get(i));
            areaDTOs.add(areaDTO);
        }
        return areaDTOs;
    }
}
