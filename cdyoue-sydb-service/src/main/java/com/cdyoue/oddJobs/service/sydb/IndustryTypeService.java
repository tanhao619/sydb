package com.cdyoue.oddJobs.service.sydb;

import com.cdyoue.oddJobs.dto.zlcx.IndustryType;

import java.util.List;

/**
 * Created by dengshaojun on 2017/09/20.
 */
public interface IndustryTypeService {

    List<IndustryType> findByType(Integer type);
}
