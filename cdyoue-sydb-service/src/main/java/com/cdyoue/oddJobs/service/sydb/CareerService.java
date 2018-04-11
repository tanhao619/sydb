package com.cdyoue.oddJobs.service.sydb;

import com.cdyoue.oddJobs.dto.zlcx.ExpertCareer;

import java.util.List;

/**
 * Created by dengshaojun on 2017/09/18.
 */
public interface CareerService {

    List<ExpertCareer> findExpertCareer(Integer expertId);
}
