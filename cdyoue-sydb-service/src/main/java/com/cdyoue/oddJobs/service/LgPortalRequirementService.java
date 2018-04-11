package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.*;
import com.cdyoue.oddJobs.dto.requirement.*;
import com.cdyoue.oddJobs.dto.xqdt.ProjectNews;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by liaoyoule on 2017/4/20.
 */
public interface LgPortalRequirementService {
    /**
     * 关键字搜索 获取需求列表
     * @param q
     * @param requestPage
     * @return
     */
    PageInfo<RequireSummary> findByKeyWord(String q, Integer categoryId, Integer reviewStatus, Pageable requestPage);

    /**
     * 发布需求
     * @param reqiurement
     * @return
     */
    Integer save(RequireRequest reqiurement);

    /**
     * 删除需求
     * @param id
     */
    void deleteByPrimary(Integer id);

    /**
     * 根据需求id获取需求详情
     * @param id
     * @return
     */
    RequireComp getRequirementById(Integer id);

    /**
     *根据id关闭需求
     * @param id
     */
    void close(Integer id);

    /**
     *根据id审核通过需求
     * @param id
     * @param reason
     * @return
     */
    Integer approve(Integer id, Reason reason);

    /**
     * 根据id审核拒绝需求
     * @param id
     * @param reason
     * @return
     */
    Integer reject(Integer id, Reason reason);

    /**
     * 可以获取我发起的需求，我承接的需求，我收到的需求邀请，根据type来判断获取那种需求
     * @param type
     * @param pr
     * @return
     */
    PageInfo<RequireMine> getMyReqiures(String type,String query, PageRequest pr);

    /**
     * 邀请用户参与需求
     * @param id
     * @param userid
     */
    void inviteReqiurement(String id, Integer userid);

    /**
     * 根据id承接需求
     * @param id
     * @param contactInfo
     */
    void acceptReqiurement(Integer id, Contact contactInfo);

    /**
     * 根据id取消承接需求
     * @param id
     */
    void cancelReqiurement(Integer id);

    /**
     * 编辑需求
     * @param id
     * @param reqiurement
     * @return
     */
    Integer updateRequirements(Integer id, RequireRequest reqiurement);

    List<RecommandResponse> findRecommand(List<RequireData> data);

    /**
     * 根据id开启需求
     * @param id
     */
    void openReqiurement(Integer id);

    /**
     * 根据需求id获取承接需求人列表
     * @param id
     * @param requestPage
     * @return
     */
    PageInfo<AcceptPeopleSumary> findAcceptPeoples(Integer id, Pageable requestPage);

    /**
     * 获取用户发布的需求
     * @param userId
     * @param pr
     * @return
     */
    PageInfo<RequireMine> getReqiure(Integer userId, PageRequest pr);

    /**
     * 获取用户承接的需求
     * @param userId
     * @param pr
     * @return
     */
    PageInfo<RequireMini> getRequirementByUserId(Integer userId, Pageable pr);

    /**
     * 获取最新发布的需求（2条）
     * @return
     */
    List<ProjectNews> getProjectNews();

    /**
     * 获取我收到的需求邀请
     * @return
     */
    PageInfo<InviteMeRequireSummary> findInviteMeRequirement(Pageable requestPage);
}
