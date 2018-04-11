package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.user.UserenterpriseEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by liaoyoule on 2017/4/24.
 */
public interface UserenterpriseResponsitory extends JpaCustomResponsitory<UserenterpriseEntity, Integer> {
    @Query("select u FROM UserenterpriseEntity u WHERE u.userId=?1")
    UserenterpriseEntity findOneByUid(int uId);

    @Query("select u.id FROM UserenterpriseEntity u WHERE u.userId=?1")
    Integer findIdByUid(Integer userId);

    @Query("select u.name FROM UserenterpriseEntity u WHERE u.userId=?1")
    String findNameByUid(Integer userId);

    @Query("select u FROM UserenterpriseEntity u WHERE u.userId=?1")
    UserenterpriseEntity findByUid(Integer uId);

    UserenterpriseEntity findByName(String userName);
    @Query("select u.id FROM UserenterpriseEntity u WHERE u.name=?1")
    Integer findIdByName(String name);
    @Modifying
    @Query("delete from UserenterpriseEntity uee where uee.userId = ?1")
    void deleteByUserId(Integer id);

    @Query("select ent FROM UserenterpriseEntity ent WHERE ent.userId=?1")
    UserenterpriseEntity findEntByUid(Integer uId);

    @Query("select ent.userId from UserenterpriseEntity ent where ent.name like ?1")
    List<Integer> findIdsByNameLike(String q);
}
