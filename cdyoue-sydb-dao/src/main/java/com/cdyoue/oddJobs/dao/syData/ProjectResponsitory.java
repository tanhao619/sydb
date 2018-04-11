package com.cdyoue.oddJobs.dao.syData;

import com.cdyoue.oddJobs.dao.lqsq.JpaCustomResponsitory;
import com.cdyoue.oddJobs.entity.syData.ProjectEntity;
import com.cdyoue.oddJobs.entity.syData.SyDeclarationProjectMessageEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by sky on 2017/9/18.
 */
public interface ProjectResponsitory extends JpaCustomResponsitory<ProjectEntity, Integer> {

    @Query(value = "select pe.name from ProjectEntity pe where pe.id=?1")
    String findProjectName(Integer id);
//    @Query(value = "select sp from SyDeclarationProjectMessageEntity sp where sp.enterpriseUserId=?1")
//    SyDeclarationProjectMessageEntity findProjectMessageEntity(Integer userId);
}
