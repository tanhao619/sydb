package com.cdyoue.oddJobs.dao.lqsq;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Created by liaoyoule on 2017/4/21.
 */
@NoRepositoryBean
public interface JpaCustomResponsitory<T,ID extends Serializable> extends JpaRepository<T,ID>,JpaSpecificationExecutor<T> {

}
