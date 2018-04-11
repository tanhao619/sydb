package com.cdyoue.oddJobs.service.sydb.impl;

import com.cdyoue.oddJobs.dao.syData.IndustryTypeResponsitory;
import com.cdyoue.oddJobs.dto.zlcx.IndustryType;
import com.cdyoue.oddJobs.entity.syData.SyIndustryTypeEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.sydb.IndustryMapper;
import com.cdyoue.oddJobs.service.sydb.IndustryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dengshaojun on 2017/09/20.
 */
@Service
@Transactional
public class IndustryTypeServiceImpl implements IndustryTypeService {

    @Autowired
    private IndustryTypeResponsitory industryTypeResponsitory;

    @Autowired
    private IndustryMapper industryMapper;

    @Override
    public List<IndustryType> findByType(Integer type) {
        List<SyIndustryTypeEntity> list = industryTypeResponsitory.findByType(type);
        if (list.size() == 0) {
            throw new NotFoundEntityException("没有数据");
        }
        return list.stream().map(p -> industryMapper.entityToDto(p)).collect(Collectors.toList());
    }
}
