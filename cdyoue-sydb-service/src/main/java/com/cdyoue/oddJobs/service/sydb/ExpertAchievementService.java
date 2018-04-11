package com.cdyoue.oddJobs.service.sydb;

import com.cdyoue.oddJobs.dto.zlcx.ExpertAchievementDetail;
import com.cdyoue.oddJobs.dto.zlcx.ExpertAchievementRequest;
import com.cdyoue.oddJobs.dto.zlcx.ExpertAchievementSummary;
import com.cdyoue.oddJobs.dto.zlcx.ExpertAchievementTop;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by dengshaojun on 2017/09/18.
 */
public interface ExpertAchievementService {

    ExpertAchievementDetail findExpertAchievement(Integer id);

    List<ExpertAchievementSummary> findExpertAchievementByEid(Integer expertId);

    PageInfo<ExpertAchievementSummary> findExpertAchievementsPage(String q, Pageable pageRequest);

    List<ExpertAchievementTop> findExpertAchievementTop2();

    void saveExpertAchievement(ExpertAchievementRequest expertAchievementRequest);

    void updateExpertAchievement(Integer id, ExpertAchievementRequest expertAchievementRequest);

    void deleteExpertAchievement(Integer[] ids);

    void updateTopExpertAchievement(Integer id, String topImgUrl);

    void collectExpertAchievement(Integer userId, Integer achievementId);

    PageInfo<ExpertAchievementSummary> findExpertAchievementByUC(Integer userId, Pageable pageRequest);
}
