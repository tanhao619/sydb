package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.scfw.Incubator;
import com.cdyoue.oddJobs.dto.scfw.IncubatorInfo;

/**
 * Created by Administrator on 2017/5/11.
 */
public interface IncubatorService {
    IncubatorInfo getIncubatorById(Integer id);

    void updateArticle(Integer id, Incubator incubator);
}
