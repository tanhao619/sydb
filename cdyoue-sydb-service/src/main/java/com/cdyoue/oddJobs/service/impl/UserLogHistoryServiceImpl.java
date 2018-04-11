package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.PortalRealNameInfoResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.PortalUserPortraitRepository;
import com.cdyoue.oddJobs.dao.lqsq.UserLogHistoryResponsitory;
import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.UserSumary;
import com.cdyoue.oddJobs.dto.oauth.TokenSumary;
import com.cdyoue.oddJobs.en.LoginTypeEnum;
import com.cdyoue.oddJobs.entity.lgsq.PortalRealNameInfoEntity;
import com.cdyoue.oddJobs.entity.lgsq.PortalUserPortraitEntity;
import com.cdyoue.oddJobs.entity.lgsq.UserLogHistoryEntity;
import com.cdyoue.oddJobs.service.UserLogHistoryService;
import com.cdyoue.oddJobs.service.UserService;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.oddJobs.utils.jwt.TokenCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;

/**
 * Created by liaoyoule on 2017/5/15.
 */
@Service
public class UserLogHistoryServiceImpl implements UserLogHistoryService {
    @Autowired
    private UserLogHistoryResponsitory userLogHistoryResponsitory;

    @Autowired
    private PortalRealNameInfoResponsitory portalRealNameInfoResponsitory;
    @Autowired

    private UserService userService;
    @Autowired
    private PortalUserPortraitRepository portalUserPortraitRepository;

    @Override
    @Transactional
    public void recordLoginHistory(TokenSumary ts) {
        UserMine um = ts.getUserMine();//账户登录信息

        //查询是否存在登录信息
        UserLogHistoryEntity ulheP = userLogHistoryResponsitory.findByUserIdAndStatusAndTokenType(um.getId(), 2, ts.getToken_type());
        if (ulheP != null) {
            //更新history信息
            long expire = ulheP.getExpire();
            String msg = expire - System.currentTimeMillis() >= 0 ? "被挤下线" : "超时退出";
            ulheP.setLogoutReason(msg);
            ulheP.setStatus(1);
            ulheP.setLogoutTime(new Timestamp(System.currentTimeMillis()));
            userLogHistoryResponsitory.save(ulheP);
        }
        //生成登录信息
        UserLogHistoryEntity ulheF = new UserLogHistoryEntity();
        ulheF.setUserId(um.getId());
        ulheF.setStatus(2);
        ulheF.setExpire(ts.getExpires_in());
        ulheF.setLoginTime(new Timestamp(System.currentTimeMillis()));
        ulheF.setToken(ts.getAccess_token());
        ulheF.setTokenType(ts.getToken_type());
        ulheF.setFreshToken(ts.getRefresh_token());
        userLogHistoryResponsitory.save(ulheF);

        //保存操作信息
        PortalUserPortraitEntity ppe = new PortalUserPortraitEntity();
        ppe.setLoginTime(new Timestamp(System.currentTimeMillis()));
        ppe.setLoginType(ts.getToken_type().equalsIgnoreCase("app")?0:1);
        ppe.setOperation(0);
        ppe.setUserId(um.getId());
        portalUserPortraitRepository.save(ppe);

    }

    @Override
    public void logout(Integer id, String loginType) {
        //获取登录信息
        UserLogHistoryEntity ulheP = userLogHistoryResponsitory.findByUserIdAndStatusAndTokenType(id, 2, loginType);
        if (ulheP != null) {
            //更新token信息
            ulheP.setLogoutReason("正常退出");
            ulheP.setStatus(1);
            ulheP.setLogoutTime(new Timestamp(System.currentTimeMillis()));
            userLogHistoryResponsitory.save(ulheP);
        }
    }

    @Override
    public void update(String sujectId, TokenSumary ts) {
        UserLogHistoryEntity ulheP = userLogHistoryResponsitory.findByUserIdAndStatusAndTokenType(Integer.parseInt(sujectId), 2, ts.getToken_type());
        if (ulheP != null) {
            //更新token信息
            ulheP.setToken(ts.getAccess_token());
            ulheP.setFreshToken(ts.getRefresh_token());
            ulheP.setExpire(ts.getExpires_in());
            ulheP.setFreshTime(new Timestamp(System.currentTimeMillis()));
            userLogHistoryResponsitory.save(ulheP);
        }
    }

    @Override
    public TokenSumary getStoreToken(int key, LoginTypeEnum loginType) {
        UserLogHistoryEntity ulheP = userLogHistoryResponsitory.findByUserIdAndStatusAndTokenType(key, 2, loginType.getLoginType());
        TokenSumary ts = new TokenSumary();
        if (ulheP == null) {
            return null;
        }
        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();
        if (null != currentUserLogin) {
            PortalRealNameInfoEntity portalRealNameInfoEntity = portalRealNameInfoResponsitory.findAyylyInfo(currentUserLogin.getId(), new Byte("1"), 1);
            if (null != portalRealNameInfoEntity) {
                ts.setApplyInfo(true);
            } else {
                ts.setApplyInfo(false);
            }
        }

        ts.setToken_type(ulheP.getTokenType());
        ts.setRefresh_token(ulheP.getFreshToken());
        ts.setExpires_in(ulheP.getExpire());
        ts.setAccess_token(ulheP.getToken());
        UserMine um = new UserMine();

        UserSumary us = userService.findUserById(ulheP.getUserId());
        um.setId(us.getId());
        um.setUserType(us.getUserType());
        um.setName(us.getUserName());
        um.setRole(us.getRole());

        um.setLoginTypeEnum(loginType);
        ts.setUserMine(um);

        TokenCache.tokenStore(key, ts, loginType);
        return ts;
    }
}
