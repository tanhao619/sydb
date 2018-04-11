package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.lgfc.RecruitmentsDetail;
import com.cdyoue.oddJobs.dto.lgfc.RecruitmentsFullInfo;
import com.cdyoue.oddJobs.dto.lgfc.*;

import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Administrator on 2017/5/8.
 */
public interface RecruitmentsService {

    /**
     * 发布全职招聘
     * @param recruitments
     */
    void createFullRecruitment(RecruitmentsFullInfo recruitments);


    /**
     * 获取全职列表
     * @param q
     * @param pr
     * @return
     */
    PageInfo getFullRecruitments(String q, PageRequest pr);


    /**
     * 获取全职详细信息
     * @param id
     */
    RecruitmentsDetail getFullRecruitmentById(Integer id);

    /**
     * 获得推荐全职
     * @param data
     * @param requestPage
     * @return
     */
    PageInfo<RecommandsJob> getRecommendJobs(List<RecommendData> data, Pageable requestPage);

    /**
     * 获得个人投递的全职
     * @param userId
     * @param pageable
     * @return
     */
    PageInfo<RecruitmentsFullJobs> getMyFullJobs(Integer userId,Pageable pageable);

    /**
     * 获得企业发布的全职
     * @param id
     * @param pageable
     * @return
     */
    PageInfo<RecruitmentsFullJobs> getUserEnterpriseFullJobs(Integer id,String q,Pageable pageable);

    /**
     * 获取发布的企业id
     * @param id
     * @return
     */
    Integer getFullRecruitmentEntId(Integer id);

    void deleteById(Integer id);

    /**
     * 获取我接收邀请的全职
     * @param userId
     * @param requestPage
     * @return
     */
    PageInfo<RecruitmentsFullJobs> getMyInviteFullJobs(Integer userId, Pageable requestPage);
}
