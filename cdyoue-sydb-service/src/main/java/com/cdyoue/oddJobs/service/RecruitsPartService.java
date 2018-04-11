package com.cdyoue.oddJobs.service;


import com.cdyoue.oddJobs.dto.lgfc.RecruitmentsDetail;

import com.cdyoue.oddJobs.dto.lgfc.RecommandsJob;
import com.cdyoue.oddJobs.dto.lgfc.RecommendData;

import com.cdyoue.oddJobs.dto.lgfc.RecruitmentsPartInfo;
import com.cdyoue.oddJobs.dto.lgfc.RecruitmentsPartMini;
import com.cdyoue.oddJobs.dto.lgfc.*;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Administrator on 2017/5/9.
 */
public interface RecruitsPartService {
    /**
     * 发布兼职招聘
     * @param recruitments
     */
    void createPartRecruitment(RecruitmentsPartInfo recruitments);

    /**
     * 获取兼职列表
     * @param q
     * @param pr
     * @return
     */
    PageInfo<RecruitmentsPartMini> getPartRecruitments(String q, PageRequest pr);

    /**
     * 获取兼职的详细信息
     * @param id
     */
    RecruitmentsDetail getPartRecruitmentById(Integer id);

    /**
     * 获得推荐兼职
     * @param data
     * @param requestPage
     * @return
     */
    PageInfo<RecommandsJob> getRecommendJobs(List<RecommendData> data,Pageable requestPage);

    /**
     * 获得个人投递兼职
     * @param userId
     * @param pageable
     * @return
     */
    PageInfo<RecruitmentsPartJobs> getMyPartJobs(Integer userId, Pageable pageable);

    /**
     * 获得企业发布的兼职
     * @param userId
     * @param pageable
     * @return
     */
    PageInfo<RecruitmentsPartJobs> getUserEnterprisePartJobs(Integer userId,String q, Pageable pageable);

    /**
     * 获取发布企业的id
     * @param id
     * @return
     */
    Integer getFullRecruitmentEntId(Integer id);

    void deleteById(Integer id);

    /**
     * 获得邀请的兼职
     * @param userId
     * @param requestPage
     * @return
     */
    PageInfo<RecruitmentsPartJobs> getMyInvitePartJobs(Integer userId, Pageable requestPage);
}
