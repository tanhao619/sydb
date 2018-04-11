package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.account.*;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;

/**
 * Created by dengshaojun on 2017/5/8.
 */
public interface AccountsService {

    void addEnterprise(EnterRegisterInfo enterInfo);

    void addPerson(PersonRegisterInfo personInfo);

    boolean existByEmail(String email);

    UserEntity findByid(Integer id);

    void updatePwd(UserEntity userEntity);

    EnterAccountDetail getMyEnterpriseInfo(int uId);

    PersonAccountDetail getMyPersonInfo(Integer uId);

    void updateMyEnterAccount(EnterAccountSumary EnterAccountSumary,Integer uId);

    void updateMyEnterAccount(EnterEditRequest enterEditRequest,Integer uId);

    void updateMyPersonAccount(PersonAccountSumary personAccountSumary,Integer uId);

    void applyQulification(EnterQulification enterQulification);

    boolean deleteExperience(Integer id);

    /**
     * 根据用户id编辑个人账号信息
     * @param personAccountSumary
     * @param id
     */
    void updatePersonAccount(PersonAccountSumary personAccountSumary, Integer id);
}
