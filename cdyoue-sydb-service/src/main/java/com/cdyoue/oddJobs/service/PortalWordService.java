package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.lgfc.Word4Work;

/**
 * Created by dengshaojun on 2017/5/25.
 */
public interface PortalWordService {

    void save4Work(Integer id, Word4Work word4Work,Integer workType);

}
