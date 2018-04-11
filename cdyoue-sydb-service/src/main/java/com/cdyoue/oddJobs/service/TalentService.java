package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.lgfc.*;
import com.cdyoue.oddJobs.entity.lgsq.PortalTechnologyEntity;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by tr on 2017/5/8.
 */
public interface TalentService {
    /**
     * 增加教育信息
     */
    Integer addMyEducation(TalentEducation education);

    /**
     * 增加职业经历
     */
    Integer addMyCareer(TalentCareer talentCareer);

    /**
     * 根据id查询教育信息
     */
    PortalTechnologyEntity findTalentEduById(Integer id);

    /**
     * 更新教育信息
     */
    void updateTalentEducation(Integer id, TalentEducation talentEducation);

    /**
     * 根据id查询职业经历
     */
    PortalTechnologyEntity findTalentCareerById(Integer careerId);

    /**
     * 更新职业经历
     */
    void updateTalentCareer(Integer id, TalentCareer talentCareer);

    /**
     * 获取我的所有职业经历
     */
    PageInfo<TalentCareer> findAllCareers(Pageable requestPage);

    /**
     * 获取我的所有教育经历
     */
    PageInfo<TalentEducation> findAllEducations(Pageable requestPage);

    /**
     * 获取能人画像信息
     */
    TalentImage findTalentImageById(Integer id);

    /**
     * 判断该用户id与修改条目的用户id是否一致
     */
    boolean updateAuthority(Integer id);

    PortalTechnologyEntity findCareerByIdAndUid(Integer id);

    List<PortalTechnologyEntity> findByUid(Integer uid,Integer type);

    String findTitleByUidLimitEndTime(Integer uId);
}
