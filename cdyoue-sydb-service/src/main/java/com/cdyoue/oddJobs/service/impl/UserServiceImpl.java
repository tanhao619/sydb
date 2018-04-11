package com.cdyoue.oddJobs.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cdyoue.oddJobs.annotion.authentication.RecordLogTools;
import com.cdyoue.oddJobs.constant.DataTypeConstant;
import com.cdyoue.oddJobs.dao.lqsq.*;
import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.UserSumary;
import com.cdyoue.oddJobs.dto.account.AccountMin;
import com.cdyoue.oddJobs.dto.account.AccountResetRequest;
import com.cdyoue.oddJobs.dto.account.AccountSumary;
import com.cdyoue.oddJobs.dto.captch.AccountCaptch;
import com.cdyoue.oddJobs.dto.captch.CaptchMemory;
import com.cdyoue.oddJobs.dto.common.message.AccountMessage;
import com.cdyoue.oddJobs.dto.common.message.AuthenticationMessage;
import com.cdyoue.oddJobs.dto.common.message.CommonMessage;
import com.cdyoue.oddJobs.dto.oauth.*;
import com.cdyoue.oddJobs.dto.requirement.RecommendTalentBase;
import com.cdyoue.oddJobs.dto.requirement.RecommendeTalentInfo;
import com.cdyoue.oddJobs.en.AccountTypeEnum;
import com.cdyoue.oddJobs.en.LoginTypeEnum;
import com.cdyoue.oddJobs.entity.lgsq.PortalRealNameInfoEntity;
import com.cdyoue.oddJobs.entity.lgsq.RoleEntity;
import com.cdyoue.oddJobs.entity.lgsq.UserRoleEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserenterpriseEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserpersonalEntity;
import com.cdyoue.oddJobs.exception.BadRequestMessageException;
import com.cdyoue.oddJobs.exception.InvalidTokenException;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.UserMapper;
import com.cdyoue.oddJobs.service.UserLogHistoryService;
import com.cdyoue.oddJobs.service.UserService;
import com.cdyoue.oddJobs.spec.Operator;
import com.cdyoue.oddJobs.spec.QueryRequest;
import com.cdyoue.oddJobs.spec.SpecificationHelper;
import com.cdyoue.oddJobs.utils.*;
import com.cdyoue.oddJobs.utils.jwt.Audience;
import com.cdyoue.oddJobs.utils.jwt.Encrypt;
import com.cdyoue.oddJobs.utils.jwt.JwtHelper;
import com.cdyoue.oddJobs.utils.jwt.TokenCache;
import com.cdyoue.oddJobs.utils.rsa.RSA_Encrypt;
import com.cdyoue.spring.page.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by liaoyoule on 2017/4/27.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserResponsitory userResponsitory;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PortalRequirementMessageResponsitory portalRequirementMessageResponsitory;

    @Value("${http.userUrl}")
    String userUrl;

    @Autowired
    private RSA_Encrypt encrypt;
    @Autowired
    private Audience audience;
    @Autowired
    JwtHelper jwtHelper;

    @Autowired
    private UserenterpriseResponsitory userenterpriseResponsitory;
    @Autowired
    private UserpersonalResponsitory userpersonalResponsitory;

    @Autowired
    private UserLogHistoryService userLogHistoryService;


    @Autowired
    private MailManager mailManager;

    @Autowired
    private SmsManager smsManager;

    @Autowired
    private PortalRealNameInfoResponsitory portalRealNameInfoResponsitory;

    @Autowired
    private SpecificationHelper<UserEntity> specificationHelper;


    @Autowired
    private UserRoleResponsitory userRoleResponsitory;

    @Value("${default.password}")
    private String defaultPassword;

    @Override
    public UserSumary findUserDetail(String account) {
        UserEntity byUserName = userResponsitory.findByEmail(account);
        if (byUserName == null) {
            return null;
        }
        UserSumary us = new UserSumary();
        BeanUtils.copyProperties(byUserName, us);
        return us;
    }

    @Override
    public List<RecommendTalentBase> findReqiureTalents(List<RecommendeTalentInfo> data, Integer reId) {
        List<RecommendTalentBase> rts = new ArrayList<>();
        data.forEach(rt -> {
            UserEntity ue = userResponsitory.findOne(rt.getUserid());
            if (ue != null) {
                RecommendTalentBase rtb = userMapper.userEntityToRecommendTalentBase(ue);
                //rtb.setActiveness(rt.getActiveness());
                Integer count = portalRequirementMessageResponsitory.findByReceiverIdAndReIdAndMsgTypeAndEventType(ue.getId(), reId, new Byte("1"), new Byte("2"));
                Integer status = count != null && count > 0 ? 2 : 1;
                rtb.setInviteStatus(status);
                rtb.setRecommendeddegree(rt.getRecommendeddegree());
                rts.add(rtb);
            }
        });
        return rts;
    }

    @Override
    public UserSumary findUserSumary(String account) {
        UserEntity ue = userResponsitory.findByEmailOrTel(account, account);

        if (ue == null) {
            return null;
        }
        UserSumary us = new UserSumary();
        BeanUtils.copyProperties(ue, us);
        us.setUserName(userMapper.userToEmployeerName(ue));
        return us;
    }

    @Override
    @Transactional
    public Integer registerByEmail(RegisterRequest registerRequest) {
        //验证邮箱
        String email = registerRequest.getEmail();
        Optional<UserSumary> userSumary = Optional.ofNullable(this.findUserSumary(email));
        if (userSumary.isPresent()) {
            throw new BadRequestMessageException(AuthenticationMessage.ACCOUNTREGISTEREMAILERROR);
        }

        UserEntity ue = new UserEntity();
        String salt = UUID.randomUUID().toString();

        ue.setPassword(Encrypt.getMd5(registerRequest.getPassword() + salt));
        ue.setSalt(salt);

        // 密码强度
//        Integer pwdLv = (registerRequest.getPassword().length() - 5) / 3;
//        pwdLv = (pwdLv > 5) ? 5 : pwdLv;
        ue.setPwdLv(PwdLvUtils.getPassWordLv(registerRequest.getPassword()));

        ue.setEmail(registerRequest.getEmail());
        ue.setReviewStatus(0);
        ue.setCreateTime(new Timestamp(System.currentTimeMillis()));
        Integer userType = registerRequest.getUserType();
        ue.setUserType(userType);

        String tokenDecode = encrypt.getToken(registerRequest.getEmail());
        String bank = "-1-";
        String dd = "-";
        String token = tokenDecode.replaceAll("\r\n", bank)
                .replaceAll("\\+", dd);
        ue.setRole(userType);
        UserEntity ueAf = userResponsitory.save(ue);
        if (null != ueAf) {
            ueAf.setIntegrityDegree(new BigDecimal(DataIntegrityUtils.getDataComp(ueAf.getId(),userType)));
            userResponsitory.save(ueAf);
        }
        String userName = registerRequest.getUserName();
        if (StringUtils.isNotBlank(userName)) {
            switch (userType) {
                case 0:
                    UserpersonalEntity upe = new UserpersonalEntity();
                    upe.setUserId(ueAf.getId());
                    upe.setName(userName);
                    upe.setNickName(userName);
                    UserpersonalEntity save = userpersonalResponsitory.save(upe);
                    ueAf.setIntegrityDegree(new BigDecimal(DataIntegrityUtils.getDataComp(ue,upe,null)));
                    userResponsitory.save(ueAf);
                    break;
                case 1:
                    UserenterpriseEntity ueeAF = userenterpriseResponsitory.findByName(userName);
                    if (ueeAF != null) {
                        throw new BadRequestMessageException(AuthenticationMessage.ACCOUNTREGISTERNAMEERROR);
                    }
                    UserenterpriseEntity uee = new UserenterpriseEntity();
                    uee.setUserId(ueAf.getId());
                    uee.setName(registerRequest.getUserName());
                    ueAf.setIntegrityDegree(new BigDecimal(DataIntegrityUtils.getDataComp(ue,null,uee)));
                    userResponsitory.save(ueAf);
                    userenterpriseResponsitory.save(uee);
                    break;
                default:
                    throw new BadRequestMessageException(AuthenticationMessage.ACCOUNTREGISTERTYPEERROR);
            }
        }

        mailManager.sendActiveMail(registerRequest.getEmail(), token);
        return ueAf.getId();
    }


    @Override
    @Transactional
    public void active(String token) {
        String bank = "-1-";
        String dd = "-";
        token = token.replaceAll(bank, "\r\n")
                .replaceAll(dd, "+");
        String account = encrypt.decrypt(token);
        UserEntity ue = userResponsitory.findByEmail(account);
        if (ue == null) {
            throw new InvalidTokenException();
        }

        if (ue.getReviewStatus() != null && ue.getReviewStatus() == 1) {
            throw new InvalidTokenException();
        }

        long time = ue.getCreateTime().getTime();
        if (System.currentTimeMillis() - time > 24 * 60 * 60 * 1000) {
            throw new BadRequestMessageException(AuthenticationMessage.ACCOUNTREGISTERTIMEOUTRROR);
        }

        ue.setReviewStatus(1);
        userResponsitory.save(ue);
    }


    @Override
    public UserEntity findOne(Integer id) {
        UserEntity userEntity = userResponsitory.findOne(id);
        return userEntity;
    }


    @Override
    public Boolean exists(Integer id) {
        return userResponsitory.exists(id);
    }





    @Override
    public void getUnbindCaptch(String type) {
        AccountMin am = this.unBindValid(type);


        UserEntity ue = am.getUserEntity();
        AccountTypeEnum accountType = am.getAccountTypeEnum();
        switch (accountType) {
            case TEL:
                smsManager.sendTelCaptcha(Long.parseLong(ue.getTel()));
                break;
            case EMAIL:
                mailManager.sendAccountCaptchaMail(ue.getEmail());
                break;
        }
    }


    @Override
    @Transactional
    public void unbind(String captch, String type) {
        AccountMin am = this.unBindValid(type);
        UserEntity ue = am.getUserEntity();
        AccountTypeEnum accountType = am.getAccountTypeEnum();

        AccountCaptch unbindCaptch = null;
        String key = null;
        switch (accountType) {
            case TEL:
                key = ue.getTel();
                unbindCaptch = TokenCache.getUnbindCaptch(key, accountType);
                ue.setTel(null);
                break;
            case EMAIL:
                key = ue.getEmail();
                unbindCaptch = TokenCache.getUnbindCaptch(key, accountType);
                ue.setEmail(null);
                break;
        }
        if (unbindCaptch == null) {
            throw new BadRequestMessageException(CommonMessage.CAPTCHINVALID);
        }

        if (!captch.equalsIgnoreCase(unbindCaptch.getCode() + "")) {
            throw new BadRequestMessageException(CommonMessage.CAPTCHINVALID);
        }

        if (System.currentTimeMillis() - unbindCaptch.getExpire() > 0) {
            throw new BadRequestMessageException(CommonMessage.CAPTCHTIMEOUT);
        }
        int i = ue.getIntegrityDegree().intValue() - 5;
        ue.setIntegrityDegree(new BigDecimal(i));
        UserEntity save = userResponsitory.save(ue);
        TokenCache.removeUnbindCaptch(key, accountType);
    }

    @Override
    public void getbindCaptch(AccountTypeEnum accountType, String account) {
        UserEntity ue = this.userResponsitory.findByEmailOrTel(account, account);
        if (ue != null) {
            throw new BadRequestMessageException(AccountMessage.ACCOUNT_DUPLICATE);
        }
        switch (accountType) {
            case TEL:
                smsManager.sendTelCaptcha(Long.parseLong(account));
                break;
            case EMAIL:
                mailManager.sendAccountCaptchaMail(account);
                break;
        }

    }

    @Override
    public void bind(String account, String captch, AccountTypeEnum accountType) {
        UserEntity ue = this.userResponsitory.findByEmailOrTel(account, account);
        if (ue != null) {
            throw new BadRequestMessageException(AccountMessage.ACCOUNT_DUPLICATE);
        }

        UserEntity ueBe = this.findOne(SecurityUtils.getCurrentUserLogin().getId());

        AccountCaptch unbindCaptch = null;
        switch (accountType) {
            case TEL:
                unbindCaptch = TokenCache.getUnbindCaptch(account, accountType);
                ueBe.setTel(account);
                break;
            case EMAIL:
                unbindCaptch = TokenCache.getUnbindCaptch(account, accountType);
                ueBe.setEmail(account);
                break;
        }
        if (unbindCaptch == null) {
            throw new BadRequestMessageException(CommonMessage.CAPTCHINVALID);
        }

        if (!captch.equalsIgnoreCase(unbindCaptch.getCode() + "")) {
            throw new BadRequestMessageException(CommonMessage.CAPTCHINVALID);
        }

        if (System.currentTimeMillis() - unbindCaptch.getExpire() > 0) {
            throw new BadRequestMessageException(CommonMessage.CAPTCHTIMEOUT);
        }
        //修改资料完整度
//        ueBe.setIntegrityDegree(new BigDecimal(DataIntegrityUtils.getDataComp(ueBe.getId(),ueBe.getUserType())));
        int i = ueBe.getIntegrityDegree().intValue() + 5;
        ueBe.setIntegrityDegree(new BigDecimal(i+""));
        userResponsitory.save(ueBe);
        TokenCache.removeUnbindCaptch(account, accountType);
    }

    @Override
    public PageInfo<AccountSumary> getAccounts(String account, Integer role, PageRequest pr) {
        List<QueryRequest> qrs = new ArrayList<>();
        if (StringUtils.isNotBlank(account)) {
            QueryRequest qr1Or = new QueryRequest();
            qr1Or.setF("email");
            qr1Or.setAndOr(false);
            qr1Or.setO(Operator.LIKE);
            qr1Or.setV(account);
            qrs.add(qr1Or);

            QueryRequest qr2Or = new QueryRequest();
            qr2Or.setF("tel");
            qr2Or.setAndOr(false);
            qr2Or.setO(Operator.LIKE);
            qr2Or.setV(account);
            qrs.add(qr2Or);
        }

        if (role != null) {
            QueryRequest qrEq = new QueryRequest();
            qrEq.setF("role");
            qrEq.setAndOr(true);
            qrEq.setT(DataTypeConstant.INTEGER);
            qrEq.setO(Operator.EQ);
            qrEq.setV(role + "");
            qrs.add(qrEq);
        }

        Specification<UserEntity> specifica = specificationHelper.getSpecifica(UserEntity.class, qrs);

        Page<UserEntity> uePage = userResponsitory.findAll(specifica, pr);
        List<UserEntity> ues = uePage.getContent();

        if (ues.size() == 0) {
            throw new NotFoundEntityException();
        }

        List<AccountSumary> ass = ues.stream().map((UserEntity ue) -> userMapper.userToAccountSumary(ue)).collect(Collectors.toList());

        return new PageInfo<>(new PageImpl(ass, pr, uePage.getTotalElements()));
    }

    @Override
    public void administrateAccount(Integer id, Integer type) {
        UserEntity ue = userResponsitory.findOne(id);
        if (ue == null) {
            throw new NotFoundEntityException();
        }
        switch (type) {
            case 0:
                ue.setReviewStatus(2);
                break;
            case 1:
                ue.setReviewStatus(1);
                break;
            case 2:
                String salt = UUID.randomUUID().toString();
                String decrypt = Encrypt.getMd5(defaultPassword.concat(salt));
                ue.setPassword(decrypt);
                ue.setSalt(salt);
                break;
            default:
                throw new BadRequestMessageException(CommonMessage.BADTYPE);
        }
        userResponsitory.save(ue);
    }

    @Override
    public UserSumary findUserById(Integer userId) {
        UserEntity ue = userResponsitory.findOne(userId);
        if(ue==null){
            throw new NotFoundEntityException();
        }
        UserSumary us = new UserSumary();
        BeanUtils.copyProperties(ue, us);
        us.setUserName(userMapper.userToEmployeerName(ue));
        return us;
    }

    @Override
    public void resetAccount(AccountResetRequest accountResetRequest) {
        Integer captcha = accountResetRequest.getCaptcha();
        CaptchMemory memory = TokenCache.getResetCaptch(captcha);
        if(memory == null){
            throw new BadRequestMessageException(CommonMessage.CAPTCHINVALID);
        }
        String account = memory.getAccount();
        UserEntity ue = this.userResponsitory.findByEmailOrTel(account, account);
        if(ue == null){
            throw new BadRequestMessageException(AccountMessage.ACCOUNTNOTFOUND);
        }
        String salt = UUID.randomUUID().toString();

        ue.setSalt(salt);
        ue.setPassword(Encrypt.getMd5(accountResetRequest.getPassword()+salt));
        userResponsitory.save(ue);

    }

    @Override
    public List<RoleBase> getAccountRoles(Integer id) {
        List<UserRoleEntity> ures = userRoleResponsitory.findByUserId(id);
        if(ures.size() == 0){
            throw new NotFoundEntityException();
        }



        return ures.stream().map(userRoleEntity -> {
            RoleBase rb = new RoleBase();
            RoleEntity role = userRoleEntity.getRole();
            if(role != null){
                rb.setId(role.getId());
                rb.setIntro(role.getIntro());
                rb.setName(role.getName());
            }
            return rb;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void accountDeliquesce() {
        List<UserEntity> ues = userResponsitory.findByReviewStatus(0);
        ues.stream().forEach((UserEntity ue) ->{
            Integer id = ue.getId();
            Timestamp time = ue.getCreateTime();
            if(System.currentTimeMillis()-time.getTime() <= 24*60*60*1000){
                return;
            }
            userResponsitory.delete(id);
            switch ( ue.getUserType()){
                case 0:
                    UserpersonalEntity upe = userpersonalResponsitory.findOneByUid(id);
                    if(upe == null){
                        return;
                    }
                    userpersonalResponsitory.deleteByUserId(id);
                    break;
                case 1:
                    UserenterpriseEntity uee = userenterpriseResponsitory.findOneByUid(id);
                    if(uee == null){
                        return;
                    }
                    userenterpriseResponsitory.deleteByUserId(id);
                    break;
            }

        });
    }

    @Override
    public List<MenuSumary> getTopMenusByUserId(Integer id) {
        return userMapper.getTopMenusByUserId(id);
    }


    private AccountMin unBindValid(String type) {
        AccountTypeEnum accountType = type.equalsIgnoreCase("email") ? AccountTypeEnum.EMAIL : type.equalsIgnoreCase("tel") ? AccountTypeEnum.TEL : null;
        if (accountType == null) {
            throw new BadRequestMessageException(AccountMessage.ACCOUNT_TYPE_INVALID);

        }
        UserMine um = SecurityUtils.getCurrentUserLogin();
        UserEntity ue = this.findOne(um.getId());
        if (ue == null || !(ue.getEmail() != null && StringUtils.isNotBlank(ue.getEmail()) && ue.getTel() != null && StringUtils.isNotBlank(ue.getTel()))) {
            throw new BadRequestMessageException(AccountMessage.ACCOUNT_INFO_IMPERFECT);
        }
        AccountMin am = new AccountMin();
        am.setAccountTypeEnum(accountType);
        am.setUserEntity(ue);
        return am;
    }

    //==============================================沈阳接口===================================================//

    /**
     * 手机注册
     * @param telRequest
     */
    @Override
    public Integer registerByTel(RegisterByTelRequest telRequest) {
        Integer reBool = 0;
        long tel = Long.parseLong(telRequest.getTel());

//        //验证验证码
        AccountCaptch accountCaptch = TokenCache.getTelCaptch(tel);

        if (accountCaptch.getCode() != telRequest.getCaptcha()) {
            return reBool = -1;//验证码错误
        }
        if (System.currentTimeMillis() - accountCaptch.getExpire() > 0) {
            return reBool = -2;//验证码超时
        }

        //验证用户
        Optional<UserSumary> userSumary = Optional.ofNullable(this.findUserSumary(tel + ""));
        if (userSumary.isPresent()) {
            return reBool = 2;
        }

        UserEntity ue = new UserEntity();
        String salt = UUID.randomUUID().toString();//生成盐值
        ue.setUserName(telRequest.getTel());//保存用户名，与手机一直
        ue.setPassword(Encrypt.getMd5(telRequest.getPassword() + salt));//保存密码
        ue.setPwdLv(1);//密码强度
        ue.setSalt(salt);//保存盐值
        ue.setTel(telRequest.getTel());//保存电话
        ue.setReviewStatus(1);//审核状态：0、未审核，1、已审核，2、不通过（全部为1）
        ue.setCreateTime(new Timestamp(System.currentTimeMillis()));
        ue.setUserType(1);//用户类型：0、个人用户（后台管理员），1、前台用户（企业用户）
        ue.setRole(1);//用户角色：0、个人用户，1、企业用户，2、超级管理员（此项目中没有1）
//        UserpersonalEntity upe = new UserpersonalEntity();
        UserenterpriseEntity upe = new UserenterpriseEntity();
        upe.setLegalPerson(telRequest.getUserName());
        ue.setIntegrityDegree(new BigDecimal(100));
        userResponsitory.save(ue);
        Integer id = ue.getId();
        upe.setUserId(ue.getId());
        userenterpriseResponsitory.save(upe);
        TokenCache.removeTelCaptch(tel);


        //生成北京户信息
        List<BasicNameValuePair> parmsList = new ArrayList<BasicNameValuePair>();
        parmsList.add(new BasicNameValuePair("userId", id+""));
        parmsList.add(new BasicNameValuePair("userType", "1"));

        String doPost = null;
        JSONObject jsonObject = new JSONObject();
        try {
            RecordLogTools.info("===================进入远程调用====================");
            doPost = HttpClientUtils.doPost(userUrl,parmsList);
            jsonObject = JSONObject.parseObject(doPost);
            RecordLogTools.info("===================结束远程调用====================");
        } catch (Exception e) {
            RecordLogTools.error("远程异常",e);
            userResponsitory.delete(id);
            return reBool = 1;//服务器异常
        }

        int code = Integer.parseInt(jsonObject.get("code")+"");
        if(code == 0){
            userResponsitory.delete(id);
            return reBool = 2;//账户已存在
        }
        return reBool;
    }

    /**
     * 用户token登录
     * @param loginPara
     * @param b
     *
     * @return
     */
    @Override
    public TokenSumary login(LoginPara loginPara, boolean b) {
//        clientID 判断是PC端登录还是移动端登录（沈阳系统暂不需要）
//        String clientId = loginPara.getClientId();
//        if (clientId == null || StringUtils.isBlank(clientId)) {
//            throw new BadRequestMessageException(AuthenticationMessage.ACCOUNTERRORCLIENT);
//        }
//        LoginTypeEnum loginType = clientId.compareTo(audience.getWebId()) != 0 ? clientId.compareTo(audience.getAppId()) != 0 ? null : LoginTypeEnum.APP : LoginTypeEnum.WEB;
//
//        if (loginType == null) {
//            throw new BadRequestMessageException(AuthenticationMessage.ACCOUNTERRORCLIENT);
//        }

        //验证用户名密码
        UserSumary userSumary = this.findUserSumary(loginPara.getUserName());
        if (userSumary == null) {//验证账号存在
            throw new BadRequestMessageException(AuthenticationMessage.ACCOUNTNOTFOUND);
        }
//        判断用户类型和登录类型是否匹配
//        角色：1、企业用户，2、超级管理员
//           b：区分先后台登录，true后台，false前台：
        if (!((userSumary.getRole() != 2 && !b) || (userSumary.getRole() == 2 && b))) {
            throw new BadRequestMessageException(AuthenticationMessage.ACCOUNTFORBIDDEN);
        }

        String md5Password = Encrypt.getMd5(loginPara.getPassword() + userSumary.getSalt());
        //验证密码是否正确
        if (md5Password.compareTo(userSumary.getPassword()) != 0) {
            throw new BadRequestMessageException(AuthenticationMessage.ACCOUNTNOTFOUND);
        }

//        沈阳系统审核状态全都为通过，不需要验证
//        if (userSumary.getReviewStatus() == 0) {
//            throw new BadRequestMessageException(AuthenticationMessage.ACCOUNTNOTACTIVATED);
//        }
//        if (userSumary.getReviewStatus() == 2) {
//            throw new BadRequestMessageException(AuthenticationMessage.GACCOUNTFORBIDDEN);
//        }

        JwtClaim jc = new JwtClaim();
        jc.setTokenType(LoginTypeEnum.WEB);//登录类型都为网页
        jc.setRole(userSumary.getRole());//用户角色
        jc.setUnique_name(loginPara.getUserName());//用户名
        jc.setUserType(userSumary.getUserType());//用户类型

        //拼装accessToken
        TokenSumary ts = jwtHelper.createJWT(userSumary.getId() + "", jc, audience.getBase64Secret());//生成token
        UserMine userMine = new UserMine();
        userMine.setId(userSumary.getId());//id
        userMine.setRole(userSumary.getRole());//用户角色
        userMine.setUserType(userSumary.getUserType());//用户类型
        userMine.setLoginTypeEnum(LoginTypeEnum.WEB);//登录端
        userMine.setName(userSumary.getUserName());//用户名
        if (b) {
            //添加左侧菜单？
            userMine.setTopMenus(userMapper.getTopMenusByUserId(userMine.getId()));
        }

        //获取认证信息
        PortalRealNameInfoEntity ayylyInfo = portalRealNameInfoResponsitory.findAyylyInfo(userSumary.getId(), new Byte("1"), 1);
        if (null != ayylyInfo) {
            ts.setApplyInfo(true);
        } else {
            ts.setApplyInfo(false);
        }
        ts.setUserMine(userMine);
//        userLogHistoryService.recordLoginHistory(ts);
        //返回信息包含：1、账户信息（UserMine）：id、用户角色、用户类型、登录端、用户名
//                      2、是否实名认证（setApplyInfo）
//                      3、token
        TokenMap.setToken(ts);
        return ts;
    }

    /**
     * 验证电话或者邮箱是否存在
     * @param tel
     * @param email
     * @return
     */
    @Override
    public Boolean checkAccount(String tel, String email) {
        UserEntity byEmailOrTel = userResponsitory.findByEmailOrTel(email, tel);
        if (byEmailOrTel == null){
            return false;
        }else{
            return true;
        }
    }

    /**
     * 忘记密码后修改密码
     * @param tel
     * @param password
     */
    @Override
    public void forgetPassword(String tel, String password) {
        UserEntity ue = userResponsitory.findByEmailOrTel(tel, tel);
        String salt = UUID.randomUUID().toString();
        ue.setPassword(Encrypt.getMd5(password + salt));//保存密码
        ue.setSalt(salt);//保存盐值
        userResponsitory.saveAndFlush(ue);
    }

    /**
     * 验证用户密码（提供给北京使用）
     * @param uid
     * @param password
     * @return
     */
    @Override
    public Integer checkUserPassword(Integer uid, String password) {
        Integer result = 0;
        UserEntity entity = userResponsitory.findOne(uid);
        if(entity == null){
            return result = -1; //用户不存在
        }else{
            String  salt = entity.getSalt();

            String check = Encrypt.getMd5(password + salt);
            if(check.equals(entity.getPassword())){
                return result;//验证成功
            }else{
                return result = 1;//密码不正确
            }
        }
    }
}
