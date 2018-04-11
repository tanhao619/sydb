package com.cdyoue.oddJobs.en;

/**
 * Created by liaoyoule on 2017/5/5.
 */
public enum AccountTypeEnum {
    EMAIL("email"),
    TEL("tel");

    private String accountType;
    AccountTypeEnum(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
