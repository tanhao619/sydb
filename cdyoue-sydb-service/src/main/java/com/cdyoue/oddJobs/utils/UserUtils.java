package com.cdyoue.oddJobs.utils;

import com.cdyoue.oddJobs.dao.lqsq.UserResponsitory;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;

/**
 * Created by dengshaojun on 2017/6/15.
 */
public class UserUtils {

    private static UserResponsitory userResponsitory = SpringContextUtil.getBean(UserResponsitory.class);

    /**
     * 方便取出用户名
     * @param id user表的主键
     * @return 对应个人用户/企业用户的name
     */
    public static String getUserName(Integer id) {
        UserEntity userEntity = userResponsitory.findOne(id);
        if (userEntity == null) {
            return "无此用户";
        }
        if (userEntity.getUserpersonalEntity() != null) {
            return userEntity.getUserpersonalEntity().getName();
        } else if (userEntity.getUserenterpriseEntity() != null) {
            return userEntity.getUserenterpriseEntity().getName();
        } else {
            return "未知用户类型";
        }
    }

}
