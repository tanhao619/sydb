package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.entity.lgsq.user.UserenterpriseEntity;

/**
 * Created by sky on 2017/5/10.
 */
public interface UserEnterpriseService {
    /**
     * 根据企业ID查找
     * @param id
     * @return
     */
    UserenterpriseEntity findOne(Integer id);

    /**
     *  根据用户ID查询用户名
     * @param id
     * @return
     */
   String findNameByUserId(Integer id);
    /**
     * 根据userId查找对应的企业ID
     * @param id
     * @return
     */
    int findEnterpriseIdByUserId(Integer id);

    /**
     * 根据名字查ID
     * @param contactor
     * @return
     */
    Integer findIdByName(String contactor);

    UserenterpriseEntity findByUid(Integer uid);
}
