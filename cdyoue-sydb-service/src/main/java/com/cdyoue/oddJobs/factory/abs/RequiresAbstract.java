package com.cdyoue.oddJobs.factory.abs;

import com.cdyoue.oddJobs.entity.lgsq.OutsourcingProjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * Created by liaoyoule on 2017/4/25.
 */
public abstract class RequiresAbstract {
    protected abstract Page<OutsourcingProjectEntity> getMyRequires(PageRequest pr, String query);
    protected abstract Boolean isBehaviorOf(Integer reId);
}
