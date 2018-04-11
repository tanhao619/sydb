package com.cdyoue.oddJobs.service.sydb;

import com.cdyoue.oddJobs.dto.zlcx.*;
import com.cdyoue.oddJobs.entity.syData.SyDeclarationProjectMessageEntity;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by sky on 2017/9/18.
 */
public interface ProjectService {
    //通过ID找寻项目详细信息
    ProjectDetail getProjectById(Integer id) ;
    //验证ID是否存在
    boolean getProjectCheck(Integer id);
    //查询所有大项目
    PageInfo<ProjectSummary> findList(PageRequest pr, String q,String time);
    //删除项目
    void deleteByIds(Integer[] ids);
//保存申报项目信息
    Integer savePeople(DeclarationPeopleSummary declarationPeopleSummary);

   //查询名称
    String getNameById(Integer id);
   //创建项目申报
   Integer createProject(ProjectCreate projectCreate);

    void updateProject(Integer id, ProjectCreate projectCreate);

    /**
     * 项目申报列表
     * @param q
     * @param requestPage
     * @return
     */
    PageInfo<DeclarationProjectMessageDTO> getProjectsList(String q, Pageable requestPage);

    /**
     * 删除申报
     * @param id
     */
    void deleteProject(Integer id);

    /**
     * 批量删除申报
     * @param ids
     */
    void deleteAllProjects(Integer[] ids);

    /**
     * 项目详情
     * @param id
     * @return
     */
    DeclarationProjectMessageDTO getProjectDetail(Integer id);
//增加浏览量
    void updateProjectViewCount(Integer id);
    //查询申报信息，判断是否已经申请
//    SyDeclarationProjectMessageEntity findProjectMessage(Integer userId);
}
