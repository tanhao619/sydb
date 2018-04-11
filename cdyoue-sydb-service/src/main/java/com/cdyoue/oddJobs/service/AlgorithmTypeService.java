package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.algorithm.AlgorithmTypeRequest;
import com.cdyoue.oddJobs.dto.algorithm.AlgorithmTypeSumary;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.PageRequest;

/**
 * Created by liaoyoule on 2017/6/14.
 */
public interface AlgorithmTypeService {
    PageInfo<AlgorithmTypeSumary> getAlgorithmTypes(String q, PageRequest pr);

    void deleteAlgorithmTypes(Integer id);

    AlgorithmTypeSumary getAlgorithmType(Integer id);

    void createAlgorithmType(AlgorithmTypeRequest algorithmTypeRequest);

    void updateAlgorithmType(Integer id, AlgorithmTypeRequest algorithmTypeRequest);
}
