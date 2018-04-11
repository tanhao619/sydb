package com.cdyoue.oddJobs.dto.account;

import com.cdyoue.oddJobs.en.AccountTypeEnum;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;

/**
 * Created by liaoyoule on 2017/5/31.
 */
public class AccountMin {
    private AccountTypeEnum accountTypeEnum;

    private UserEntity UserEntity;


    public AccountTypeEnum getAccountTypeEnum() {
        return accountTypeEnum;
    }

    public void setAccountTypeEnum(AccountTypeEnum accountTypeEnum) {
        this.accountTypeEnum = accountTypeEnum;
    }

    public com.cdyoue.oddJobs.entity.lgsq.user.UserEntity getUserEntity() {
        return UserEntity;
    }

    public void setUserEntity(com.cdyoue.oddJobs.entity.lgsq.user.UserEntity userEntity) {
        UserEntity = userEntity;
    }
}
