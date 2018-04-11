package com.cdyoue.oddJobs.dao.syData;

import com.cdyoue.oddJobs.dao.lqsq.JpaCustomResponsitory;
import com.cdyoue.oddJobs.entity.syData.SyDeclarationProjectMessageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by sky on 2017/9/22.
 */
public interface ProjectLecturePeopleResponsitory extends JpaCustomResponsitory<SyDeclarationProjectMessageEntity, Integer> {
    @Query("SELECT s1 FROM SyDeclarationProjectMessageEntity s1,ProjectEntity s2 WHERE s1.projectId = s2.id AND s2.name LIKE ?1")
    Page<SyDeclarationProjectMessageEntity> findListByName(String q, Pageable requestPage);

    @Query("select pe.name from ProjectEntity pe where pe.id=?1")
    String getProjectNameById(Integer id);

    @Query(nativeQuery = true,value = "select u.name from userenterprise u where u.userId=?1")
    String getEnterpriseNameById(Integer id);
}
