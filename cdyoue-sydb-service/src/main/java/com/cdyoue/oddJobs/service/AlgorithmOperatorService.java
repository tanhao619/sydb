package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.algorithm.AlgorithmBase;
import com.cdyoue.oddJobs.dto.algorithm.AlgorithmRequest;
import com.cdyoue.oddJobs.dto.algorithm.AlgorithmSumary;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.PageRequest;

/**
 * Created by liaoyoule on 2017/6/14.
 */
public interface AlgorithmOperatorService {
    PageInfo<AlgorithmBase> getAlgorithms(String q, PageRequest pr);

    /**
     * 删除算法
     * @param id
     */
    void delete(Integer id);

    /**
     * 查看算法详情
     * @param id
     * @return
     */
    AlgorithmSumary getAlgorithm(Integer id);

    /**
     * 添加算法
     * @param algorithmRequest
     */
    void createAlgorithm(AlgorithmRequest algorithmRequest);

    /**
     * 修改算法
     * @param id
     * @param algorithmRequest
     */
    void updateAlgorithm(Integer id, AlgorithmRequest algorithmRequest);
}
