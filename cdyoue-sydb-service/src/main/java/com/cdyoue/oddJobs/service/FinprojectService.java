package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.Reason;
import com.cdyoue.oddJobs.dto.scfw.FinProjectDetail;
import com.cdyoue.oddJobs.dto.scfw.FinProjectSummary;
import com.cdyoue.oddJobs.entity.lgsq.PortalProjectEntity;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.Pageable;

/**
 * Created by tr on 2017/5/23.
 */

public interface FinprojectService {
    /**
     * 根据id查找项目详情
     * @param id
     * @return
     */
    FinProjectDetail getFinProjectById(Integer id);

    /**
     * 根据id查找项目实体
     * @param id
     * @return
     */
    PortalProjectEntity getProjectEntityById(Integer id);

    /**
     * 根据更新项目实体
     * @param id
     * @return
     */
    void updateFinProject(Integer id, FinProjectDetail finproject);

    /**
     * 创建融资项目
     * @param userId
     * @param finproject
     * @return Integer
     */
    Integer createFinProject(Integer userId, FinProjectDetail finproject);

    /**
     * 获取我发布的融资项目
     * @param userId
     * @return lists
     */
    PageInfo<FinProjectSummary> getMyFinProjects(Integer userId, String q, Pageable requestPage);

    /**
     * 通过融资项目
     * @param id
     * @return
     */
    void approveFinProject(Integer id, Reason finproject);
    /**
     * 拒绝通过融资项目
     * @param id
     * @return
     */
    void rejectFinProject(Integer id, Reason finproject);
    /**
     * 下载
     * @param id
     * @return ResponseEntity<InputStreamResource>
     */
    FinProjectDetail downloadFinProjectById(Integer id);

    /**
     * 获取融资项目列表(后台)
     * @param q
     * @return lists
     */
    PageInfo<FinProjectSummary> findByKeyWord(Pageable requestPage, String q, Byte statusFilter);

    /**
     * 更新附件表关联信息
     * @param id
     * @param f
     */
    void updateAttachment(Integer id, FinProjectDetail f);

    /**
     * 删除融资项目
     * @param id
     */
    void deleteFinProject(Integer id);

    /**
     * 个人或企业用户更新删除权限
     * @param id
     */
    boolean deleteOrUpdateAuthority(Integer id, Integer userId);
}
