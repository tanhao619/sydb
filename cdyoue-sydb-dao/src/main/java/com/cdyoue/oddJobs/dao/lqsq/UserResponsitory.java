package com.cdyoue.oddJobs.dao.lqsq;


import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by liaoyoule on 2017/4/20.
 */

public interface UserResponsitory extends JpaCustomResponsitory<UserEntity,Integer>{
    UserEntity findByEmail(String account);
    UserEntity findByEmailOrTel(String account, String account1);

    // 通过用户Id查找区分查找企业/个人用户名
    @Query(value = "SELECT ( case WHEN\n" +
            "\t\t\t\t\t( SELECT name FROM userpersonal WHERE UserId = ?1) is not NULL \n" +
            "\t\t\t\t\tTHEN\n" +
            "\t\t\t\t\t(SELECT name FROM userpersonal WHERE UserId = ?1)\n" +
            "\t\t\t\t\tELSE\n" +
            "\t\t\t\t\t(SELECT name from userenterprise WHERE UserId = ?1)\n" +
            "\t\t\t\tEND\n" +
            "       ) as username\n",nativeQuery = true)
    String findUserNameDistPerAndEnt(Integer uId);

    List<UserEntity> findByReviewStatus(Integer  reviewStatus);

    @Query(value = "select e FROM UserEntity e where e.userName=?1")
    UserEntity getOneByName(String uName);
}
