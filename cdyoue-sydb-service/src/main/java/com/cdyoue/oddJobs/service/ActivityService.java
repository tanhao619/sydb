package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.ActivityBanner;
import com.cdyoue.oddJobs.dto.ggfw.ActivityDetail;
import com.cdyoue.oddJobs.dto.ggfw.ActivityRequest;
import com.cdyoue.oddJobs.dto.ggfw.ActivitySummary;
import com.cdyoue.oddJobs.dto.Reason;
import com.cdyoue.oddJobs.dto.zscq.IntellectualTop;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by luanyu on 2017/5/11.
 */
public interface ActivityService {
    //通过ID找寻一个活动详细信息
    ActivityDetail getActivityById(Integer id);
    //验证ID是否存在
    boolean getActivityCheck(Integer id);
    //创建一个活动
    String createActivity(ActivityRequest activity);
    //更新一个活动
    void updateActivity(Integer id,ActivityRequest activity);
    //关注活动
    void focusActivity(Integer id);
    //取消关注活动
    void cancelfocusActivity(Integer id);
    //查询我发布活动
    PageInfo<ActivitySummary> findMyList(PageRequest pr, String q);
    //查询所有活动
    PageInfo<ActivitySummary> findList(PageRequest pr, String q, String s);
    //查询我收藏的活动列表
    PageInfo<ActivitySummary> findMyCollectionsListByStrKey(List<Integer> focusQuestionIds, String q, Pageable pr);
    //查询活动创建人
    Integer getCreatorIdBy(Integer id);
    //查询活动状态
    Integer getStatus(Integer id);
    //通过审核
    void approveActivity(Integer id,Reason activity);
    //不通过审核
    void rejectActivity(Integer id,Reason activity);
    //删除活动
    void deleteActivity(Integer id);

    /**
     * 获取活动banner图
     * @return
     */
    List<ActivityBanner> getActivityBanners();

    /**
     * 批量删除活动
     * @param ids
     */
    void deleteAllActivities(Integer[] ids);

    /**
     * 置顶
     * @param top
     */
    void activityTop(IntellectualTop top);

    /**
     * 取消置顶
     * @param id
     */
    void removeActivityTop(Integer id);
}
