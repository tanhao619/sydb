package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.lgfc.TalentAbility;
import com.cdyoue.oddJobs.dto.lgfc.TalentBase;
import com.cdyoue.oddJobs.dto.lgfc.TalentInfo;
import com.cdyoue.oddJobs.dto.lgfc.TalentSummary;
import com.cdyoue.oddJobs.entity.lgsq.PortalRealNameInfoEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserpersonalEntity;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by sky on 2017/5/2.
 */
public interface UserpersonalService {
    /**
     * 关键字搜索 获取能人列表
     * @param q
     * @param requestPage
     * @return
     */
    PageInfo<TalentBase> findByKeyWord(String q, Integer industry, Integer expertType, Pageable requestPage);

    /**
     * 关注能人
     * @param id
     * @return
     */
    void followTalent(Integer id);

    /**
     * 取消关注能人
     * @param id
     * @return
     */
    void unFollowTalent(Integer id);

    /**
     * 获取我关注的能人
     * @return
     */
    PageInfo<TalentAbility> getMyTalents(Pageable requestPage);

    /**
     * 获取能人基本信息
     * @param id
     * @return
     */
    TalentInfo getTalentBaseInfo(Integer id);

    /**
     * 获取能人经历信息
     * @param id
     * @param type
     * @return
     */
    List<TalentSummary> getTalentExperience(Integer id, Integer type);

    /**
     * 获取推荐应聘者信息列表
     * @param type
     * @return
     */
    List<TalentBase> getRecommandUsers(String type, Integer id);

    String getUserPersonalName(Integer id);

    void updateInvitedNum(Integer userId);

    void updateDataComp(Integer dataComp, Integer userId);

    UserpersonalEntity findByUid(Integer uid);

    UserEntity findUserById(Integer uid);

    List<PortalRealNameInfoEntity> findApplysByUid(Integer uid);
}
