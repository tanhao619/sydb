package com.cdyoue.oddJobs.mapper.sydb;

import com.cdyoue.oddJobs.dto.zlcx.IndustryType;
import com.cdyoue.oddJobs.entity.syData.SyIndustryTypeEntity;
import org.springframework.stereotype.Component;

/**
 * Created by dengshaojun on 2017/09/20.
 */
@Component
public class IndustryMapper {

    public IndustryType entityToDto(SyIndustryTypeEntity entity) {
        IndustryType dto = new IndustryType();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

}
