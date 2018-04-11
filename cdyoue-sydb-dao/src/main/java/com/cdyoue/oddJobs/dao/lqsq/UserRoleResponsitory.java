package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.UserRoleEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2017/5/2.
 */
public interface UserRoleResponsitory extends  JpaCustomResponsitory<UserRoleEntity,Integer> {
    @Modifying
    @Query("delete from UserRoleEntity ure where ure.userId = ?1")
    void deleteByUserId(Integer id);

    List<UserRoleEntity> findByUserId(Integer id);
    @Modifying
    @Query("delete from UserRoleEntity ure where ure.role.id = ?1")
    void deleteByRoleId(Integer id);
}
