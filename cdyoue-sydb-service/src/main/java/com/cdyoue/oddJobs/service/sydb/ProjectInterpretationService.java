package com.cdyoue.oddJobs.service.sydb;

import com.cdyoue.oddJobs.dto.zlcx.ProjectInterpretationBase;
import com.cdyoue.oddJobs.dto.zlcx.ProjectInterpretationDetail;
import com.cdyoue.oddJobs.dto.zlcx.ProjectInterpretationSummary;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.PageRequest;

/**
 * Created by sky on 2017/9/19.
 */
public interface ProjectInterpretationService {
    //通过ID找寻项目详细信息
    ProjectInterpretationDetail getProjectInterpretationById(Integer id) ;
    //验证ID是否存在
    boolean getProjectInterpretationCheck(Integer id);
    //查询所有大项目
    PageInfo<ProjectInterpretationSummary> findList(PageRequest pr, String q);

    void deleteByIds(Integer[] ids);

    Integer createProjectInterpretation(ProjectInterpretationBase projectInterpretationBase);

    void updateProjectInterpretation(Integer id, ProjectInterpretationBase projectInterpretationBase);

    void updateViewCount(Integer id);
}
