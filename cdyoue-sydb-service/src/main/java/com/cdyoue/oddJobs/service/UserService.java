package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.UserSumary;
import com.cdyoue.oddJobs.dto.account.AccountResetRequest;
import com.cdyoue.oddJobs.dto.account.AccountSumary;
import com.cdyoue.oddJobs.dto.oauth.*;
import com.cdyoue.oddJobs.dto.requirement.RecommendTalentBase;
import com.cdyoue.oddJobs.dto.requirement.RecommendeTalentInfo;
import com.cdyoue.oddJobs.en.AccountTypeEnum;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * Created by liaoyoule on 2017/4/27.
 */
public interface UserService {
    UserSumary findUserDetail(String account);

    List<RecommendTalentBase> findReqiureTalents(List<RecommendeTalentInfo> data,Integer reId);

    UserSumary findUserSumary(String userName);

    Integer registerByEmail(RegisterRequest registerRequest);

    void active(String token);
    UserEntity findOne(Integer id);
    Boolean exists(Integer id);

    Integer registerByTel(RegisterByTelRequest telRequest);

    TokenSumary login(LoginPara loginPara, boolean b);


    void getUnbindCaptch(String type);

    void unbind(String captch, String type);

    void getbindCaptch(AccountTypeEnum accountType, String account);

    void bind(String account, String captch, AccountTypeEnum accountType);

    /**
     * 获取账户列表--管理员
     * @param account
     * @param userType
     * @param pr
     * @return
     */
    PageInfo<AccountSumary> getAccounts(String account, Integer userType, PageRequest pr);

    /**
     * 账户禁止 解除禁止 重置密码
     * @param id
     * @param type
     */
    void administrateAccount(Integer id, Integer type);

    UserSumary findUserById(Integer userId);

    void resetAccount(AccountResetRequest accountResetRequest);

    List<RoleBase> getAccountRoles(Integer id);

    void accountDeliquesce();

    List<MenuSumary> getTopMenusByUserId(Integer id);

    Boolean checkAccount(String tel, String email);

    void forgetPassword(String tel, String password);

    Integer checkUserPassword(Integer uid, String password);
}
