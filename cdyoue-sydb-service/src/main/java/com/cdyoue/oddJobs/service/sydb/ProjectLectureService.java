package com.cdyoue.oddJobs.service.sydb;

import com.cdyoue.oddJobs.dto.zlcx.ProjectLectureCreate;
import com.cdyoue.oddJobs.dto.zlcx.ProjectLectureDetail;
import com.cdyoue.oddJobs.dto.zlcx.ProjectLectureSummary;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.PageRequest;

/**
 * Created by sky on 2017/9/19.
 */
public interface ProjectLectureService {
    //通过ID找寻项目详细信息
    ProjectLectureDetail getProjectLectureById(Integer id) ;
    //验证ID是否存在
    boolean getProjectLectureCheck(Integer id);
    //查询所有大项目
    PageInfo<ProjectLectureSummary> findList(PageRequest pr, String q);

    Integer createProjectLecture(ProjectLectureCreate projectLectureCreate);

    void deleteByIds(Integer[] ids);

    void updateProjectLecture(Integer id, ProjectLectureCreate projectLectureCreate);
    //获取置顶讲座
    ProjectLectureSummary getTopLecture();

    void updateProjectLectureViewCount(Integer id);
//置顶讲座
    void topLecture(Integer id,String topImage);
}
