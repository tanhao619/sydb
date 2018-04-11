package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.UserenterpriseResponsitory;
import com.cdyoue.oddJobs.entity.lgsq.user.UserenterpriseEntity;
import com.cdyoue.oddJobs.service.UserEnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sky on 2017/5/10.
 */
@Service
public class UserEnterpriseServiceImpl implements UserEnterpriseService{

    @Autowired
    private UserenterpriseResponsitory userenterpriseResponsitory;
    @Override
    public UserenterpriseEntity findOne(Integer id) {
        UserenterpriseEntity userenterpriseEntity=userenterpriseResponsitory.findOne(id);
        return userenterpriseEntity;
    }

    @Override
    public String findNameByUserId(Integer id) {
        return userenterpriseResponsitory.findNameByUid(id);
    }

    @Override
    public int findEnterpriseIdByUserId(Integer id) {
        return userenterpriseResponsitory.findIdByUid(id);
    }

    @Override
    public Integer findIdByName(String name) {
        return userenterpriseResponsitory.findIdByName(name);
    }

    @Override
    public UserenterpriseEntity findByUid( Integer uid) {
        UserenterpriseEntity byUid = userenterpriseResponsitory.findByUid(uid);
        return byUid;
    }
}
