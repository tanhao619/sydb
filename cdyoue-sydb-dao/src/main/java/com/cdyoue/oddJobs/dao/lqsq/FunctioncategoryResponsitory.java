package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.FunctioncategoryEntity;

import java.util.List;

/**
 * Created by liaoyoule on 2017/5/2.
 */

public interface FunctioncategoryResponsitory extends  JpaCustomResponsitory<FunctioncategoryEntity,Integer>{

    List<FunctioncategoryEntity> findByParentId(Integer id);

    List<FunctioncategoryEntity> findByParentIdIsNull();
}
