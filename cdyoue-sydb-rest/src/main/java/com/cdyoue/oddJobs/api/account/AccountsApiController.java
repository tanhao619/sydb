package com.cdyoue.oddJobs.api.account;


import com.alibaba.fastjson.JSONObject;
import com.cdyoue.oddJobs.annotion.authentication.Login;
import com.cdyoue.oddJobs.annotion.authentication.Role;
import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.UserSumary;
import com.cdyoue.oddJobs.dto.account.*;
import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.common.message.AccountMessage;
import com.cdyoue.oddJobs.dto.common.message.ArticlesMessage;
import com.cdyoue.oddJobs.dto.common.message.AuthenticationMessage;
import com.cdyoue.oddJobs.dto.common.message.CommonMessage;
import com.cdyoue.oddJobs.dto.lgfc.TalentCareer;
import com.cdyoue.oddJobs.dto.lgfc.TalentEducation;
import com.cdyoue.oddJobs.dto.oauth.RoleBase;
import com.cdyoue.oddJobs.en.AccountTypeEnum;
import com.cdyoue.oddJobs.entity.lgsq.PortalTechnologyEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import com.cdyoue.oddJobs.exception.UnAuthenticationMessageException;
import com.cdyoue.oddJobs.factory.CaptchaFactory;
import com.cdyoue.oddJobs.service.AccountsService;
import com.cdyoue.oddJobs.service.TalentService;
import com.cdyoue.oddJobs.service.UserService;
import com.cdyoue.oddJobs.service.UserpersonalService;
import com.cdyoue.oddJobs.utils.*;
import com.cdyoue.oddJobs.utils.jwt.Encrypt;
import com.cdyoue.spring.common.SortUtils;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.sun.org.apache.xerces.internal.impl.xpath.regex.REUtil.matches;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-07T12:12:19.725Z")
@Controller
public class AccountsApiController implements AccountsApi {

    @Autowired
    private AccountsService accountsService;
    @Autowired
    private TalentService talentService;
    @Autowired
    MailManager mailManager;
    @Autowired
    private UserpersonalService userpersonalService;
    @Autowired
    private UserService userService;
    @Value("${default.commonUpload.driver}")
    String driver;
    @Value("${default.commonUpload.server}")
    String server;

    @Autowired
    private CaptchaFactory captchaFactory;



    @Override//获取我的企业账户信息
    public ResponseEntity<EnterAccountDetail> getMyEnterpriseInfo() {
        if (SecurityUtils.getCurrentUserLogin() == null) {
            throw new UnAuthenticationMessageException(AuthenticationMessage.USERUNLOGIN);
        }
//        UserMine userFrontLogin = SecurityUtils.getUserFrontLogin();
        try {
            Integer uId = SecurityUtils.getCurrentUserLogin().getId();
//            Integer uId = userFrontLogin.getId();
            EnterAccountDetail accountDTO = accountsService.getMyEnterpriseInfo(uId);

            return new ResponseEntity<EnterAccountDetail>(accountDTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(AccountMessage.NOTFOUND, HttpStatus.NOT_FOUND);
        }

    }

    @Override//获取我的个人账户信息
    public ResponseEntity<PersonAccountDetail> getMyPersonInfo() {
        if (SecurityUtils.getCurrentUserLogin() == null) {
            throw new UnAuthenticationMessageException(AuthenticationMessage.USERUNLOGIN);
        }
        try {
            UserMine userMine = SecurityUtils.getCurrentUserLogin();
            Integer uId = userMine.getId();
            PersonAccountDetail personDTO = accountsService.getMyPersonInfo(uId);
//            personDTO.setIntegrityDegree(BigDecimal.valueOf(getDataComp(uId,userMine.getUserType())));
            personDTO.setJob(DataIntegrityUtils.getUserTitle(uId));
            return new ResponseEntity<PersonAccountDetail>(personDTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(AccountMessage.NOTFOUND, HttpStatus.NOT_FOUND);
        }
    }

    private  Double getworkJob(String startTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startTimeDate = null;
        try {
            startTimeDate = sdf.parse(startTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DecimalFormat df=new DecimalFormat("0.0");
        String s = DurationFormatUtils.formatPeriod(startTimeDate.getTime(), System.currentTimeMillis(), "yyyy/MM");
        String[] sA = s.split("/");
        int workYear = Integer.parseInt(sA[0]);
        int workMouth = Integer.parseInt(sA[1]);

        System.out.println();
        Double workD = Double.parseDouble(df.format((float)workMouth/(float)12));
        Double wordDouble = workYear+workD;
        return wordDouble;
    }


    @Override//获取任意企业账户信息
    public ResponseEntity getEnterpriseInfo(@ApiParam(value = "用户ID", required = true) @PathVariable("id") Integer id) {
        try {
            if (accountsService.getMyEnterpriseInfo(id) == null) {
                return new ResponseEntity(AccountMessage.NOTFOUND, HttpStatus.NOT_FOUND);
            } else {
                EnterAccountDetail accountDTO = accountsService.getMyEnterpriseInfo(id);
                return new ResponseEntity<EnterAccountDetail>(accountDTO, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(AccountMessage.NOTFOUND, HttpStatus.NOT_FOUND);
        }


    }

    @Override//获取任意个人账户信息
    public ResponseEntity getPersonInfo(@ApiParam(value = "用户ID", required = true) @PathVariable("id") Integer id) {
        if (SecurityUtils.getCurrentUserLogin() == null) {
            throw new UnAuthenticationMessageException(AuthenticationMessage.USERUNLOGIN);
        }
        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();
        int role = SecurityUtils.getCurrentUserLogin().getRole();
        try {
            if (role != 2) {
                return new ResponseEntity(AccountMessage.FOUNDFORBIDDEN, HttpStatus.FORBIDDEN);
            } else if (accountsService.getMyPersonInfo(id) == null) {
                return new ResponseEntity(AccountMessage.NOTFOUND, HttpStatus.NOT_FOUND);
            } else {
                PersonAccountDetail personDTO = accountsService.getMyPersonInfo(id);
                return new ResponseEntity<PersonAccountDetail>(personDTO, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity(AccountMessage.NOTFOUND, HttpStatus.NOT_FOUND);
        }
    }

    @Override//编辑我的企业账号信息
    public ResponseEntity<HttpMessage> updateMyEnterAccount(@ApiParam(value = "企业信息", required = true) @RequestBody EnterEditRequest enterEditRequest) {
        if (SecurityUtils.getCurrentUserLogin() == null) {
            throw new UnAuthenticationMessageException(AuthenticationMessage.USERUNLOGIN);
        }
        try {
            Integer uId = SecurityUtils.getCurrentUserLogin().getId();
            accountsService.updateMyEnterAccount(enterEditRequest, uId);
            return new ResponseEntity<HttpMessage>(AccountMessage.UPDATESUCCESSFUL, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<HttpMessage>(AccountMessage.UPDATEFAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    @Login
    @Role(2)
    public ResponseEntity<HttpMessage> updateEnterAccount(  @ApiParam(value = "id", required = true) @PathVariable("id") Integer id,
                                                            @ApiParam(value = "企业信息", required = true) @RequestBody EnterAccountSumary enterAccountSumary) {
        accountsService.updateMyEnterAccount(enterAccountSumary, id);
        return new ResponseEntity<HttpMessage>(CommonMessage.UPDATESUCCESS, HttpStatus.OK);
    }

    @Override//编辑我的个人账号信息
    public ResponseEntity<HttpMessage> updateMyPersonAccount(@ApiParam(value = "个人信息", required = true)
                                                             @RequestBody PersonAccountSumary personAccountSumary) {
        if (SecurityUtils.getCurrentUserLogin() == null) {
            throw new UnAuthenticationMessageException(AuthenticationMessage.USERUNLOGIN);
        }
        try {
            Integer uId = SecurityUtils.getCurrentUserLogin().getId();

            accountsService.updateMyPersonAccount(personAccountSumary, uId);
            userpersonalService.updateDataComp(DataIntegrityUtils.countDataComp(personAccountSumary), uId);
            return new ResponseEntity<HttpMessage>(ArticlesMessage.PERSONACCOUNTUPDATE, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<HttpMessage>(AccountMessage.UPDATEFAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Login
    @Role(2)
    public ResponseEntity<HttpMessage> updatePersonAccount(@ApiParam(value = "用户id", required = true) @PathVariable("id") Integer id,
                                                           @ApiParam(value = "栏目名称", required = true) @RequestBody PersonAccountSumary personAccountSumary) {
        accountsService.updatePersonAccount(personAccountSumary, id);
        return new ResponseEntity<HttpMessage>(CommonMessage.UPDATESUCCESS,HttpStatus.OK);
    }

    @Override//发送邮件验证码
    public ResponseEntity<HttpMessage> sendMail(@ApiParam(value = "邮箱地址", required = true) @PathVariable("address") String address) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        javax.servlet.http.HttpServletRequest hsr = attributes.getRequest();
        HttpSession session = hsr.getSession();
        int yzm = (int) (Math.random() * 899999 + 100000);
        Date date = new Date();
        mailManager.sendVerificationMail(address, yzm + "");
        session.setAttribute(address, yzm + "");
        session.setAttribute(address + "Time", date);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    @Override//验证邮件验证码
    public ResponseEntity<HttpMessage> checkMail(@ApiParam(value = "验证码", required = true) @PathVariable("yzm") String nYzm,
                                                 @ApiParam(value = "验证码", required = true) @PathVariable("address") String address) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        javax.servlet.http.HttpServletRequest hsr = attributes.getRequest();
        HttpSession session = hsr.getSession();
        String yzm = (String) session.getAttribute(address);
        if (yzm == null) {
            return new ResponseEntity<HttpMessage>(AccountMessage.CHECKFAIlBYADDRESS, HttpStatus.BAD_REQUEST);
        } else {
            long yzmDate = ((Date) session.getAttribute(address + "Time")).getTime();
            long sysTime = new Date().getTime();
            int time = (int) ((sysTime - yzmDate) / 1000 / 60);
            if (time > 5) {
                session.removeAttribute(address);
                session.removeAttribute(address + "Time");
                return new ResponseEntity<HttpMessage>(AccountMessage.TIMEOUT, HttpStatus.BAD_REQUEST);
            } else if (!yzm.equals(nYzm)) {
                return new ResponseEntity<HttpMessage>(AccountMessage.CHECKFAIlBYYZM, HttpStatus.BAD_REQUEST);
            } else {
                session.removeAttribute(address);
                session.removeAttribute(address + "Time");
                return new ResponseEntity<HttpMessage>(AccountMessage.CHECKSUCCESSFUL, HttpStatus.OK);
            }
        }
    }

    @Override
    public ResponseEntity<HttpMessage> registerEnterprises(@ApiParam(value = "注册邮箱", required = true) @RequestBody String email) {
        boolean isExist = accountsService.existByEmail(email);
        if (isExist) {
            return new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    public ResponseEntity<HttpMessage> registerEnterprise(@ApiParam(value = "企业注册信息", required = true) @RequestBody EnterRegisterInfo enterInfo) {
        // do some magic!
        accountsService.addEnterprise(enterInfo);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    public ResponseEntity<HttpMessage> registerPerson(@ApiParam(value = "个人注册信息", required = true) @RequestBody PersonRegisterInfo personInfo) {
        // do some magic!
        accountsService.addPerson(personInfo);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }


    // 修改密码
    public ResponseEntity<HttpMessage> updatePassword(@ApiParam(value = "新密码", required = true) @RequestParam String newPwd) {
        /*JSONObject jsonObject = JSONObject.parseObject(newPwd);
        String str = jsonObject.getString("newPwd");*/
        String str = newPwd;

        if (SecurityUtils.getCurrentUserLogin() == null) {
            throw new UnAuthenticationMessageException(AuthenticationMessage.USERUNLOGIN);
        }
        UserEntity userEntity = accountsService.findByid(SecurityUtils.getCurrentUserLogin().getId());
        // 8位及8位以上，数字字母特殊字符组合
        /*Boolean a = matches("^(?![^a-zA-Z]+$)(?!\\D+$).{8,}$", str);
        if (!a) {
            return new ResponseEntity<HttpMessage>(AccountMessage.UPDPWDFAILD4001, HttpStatus.BAD_REQUEST);
        }*/
        String salt = UUID.randomUUID().toString();
        userEntity.setPassword(Encrypt.getMd5(str + salt));
        userEntity.setSalt(salt);
        // 重置密码强度
        userEntity.setPwdLv(PwdLvUtils.getPassWordLv(str));
        accountsService.updatePwd(userEntity);
        // 修改成功，注销登录（未实现）
        SecurityUtils.setCurrentUserLogin(null);
        return new ResponseEntity<HttpMessage>(AccountMessage.UPDPWDSUCCESS, HttpStatus.CREATED);
    }

    /*public static void main(String[] args) {
        Boolean a = matches("^(?![^a-zA-Z]+$)(?!\\D+$).{8,}$", "1fds1ggs");
        System.out.println(a);
    }*/

    /*public static void main(String[] args) {
        String s = "123123@";
        boolean matches = s.matches("[0-9]");

        boolean b = Pattern.compile("[a-z]").matcher(s).find();
        boolean c = Pattern.compile("(?i)[1-9]").matcher(s).find();
        s.endsWith("[1-9]");
        if ("哈哈哈哈x哈哈".matches(".*[a-zA-z].*")) {
            System.out.println("含有英文字符");
        } else {
            System.out.println("不含英文字符");
        }
        System.out.println(b);
//        String str="sdfsdf11111x";
//        String str2="sdfsf123z";
//        System.out.println(str.matches("[a-zA-Z]*"));
//        System.out.println(str2.matches("[a-zA-Z]*"));
//
//        System.out.println(str.endsWith("x"));
//        System.out.println(str2.endsWith("x"));
//        int lv =0;
//        if(s.length()<8){
//            lv = 1;
//        }else if(s.length()>8){
//            lv = 2;
//        }else {
//            if(Pattern.matches("[A-Za-z0-9]",s)){
//                lv ++;
//            }else if(Pattern.matches("[A-Z]",s)) {
//                lv ++;
//            }else if(Pattern.matches("[0-9]",s)){
//                lv ++;
//            }else if(containSpecialChar(str)) {
//                lv ++;
//            }
//        }
//        return lv;

    }*/

    public ResponseEntity<HttpMessage> updateMyCareer(@ApiParam(value = "", required = true) @PathVariable("id") Integer id, @ApiParam(value = "职业经历", required = true) @RequestBody TalentCareer talentCareer) {
        PortalTechnologyEntity ret = talentService.findCareerByIdAndUid(id);
        if (ret != null) {
            talentService.updateTalentCareer(id, talentCareer);
            return new ResponseEntity<HttpMessage>(HttpStatus.OK);
        } else {
            return new ResponseEntity<HttpMessage>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpMessage> updateMyEducation(@ApiParam(value = "", required = true) @PathVariable("id") Integer id, @ApiParam(value = "教育经历", required = true) @RequestBody TalentEducation talentEducation) {

        PortalTechnologyEntity portalTechnologyEntity = talentService.findTalentEduById(id);
        if (portalTechnologyEntity == null) return new ResponseEntity<HttpMessage>(HttpStatus.NOT_FOUND);
        boolean tags = talentService.updateAuthority(id);
        if (!tags) {
            return new ResponseEntity<HttpMessage>(HttpStatus.UNAUTHORIZED);
        }

        talentService.updateTalentEducation(id, talentEducation);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);

    }

    // 检查密码是否和原密码相同
    public ResponseEntity<Boolean> checkPassword(@ApiParam(value = "密码", required = true) @RequestParam String password) {
        // do some magic!
        //JSONObject jsonObject = JSONObject.parseObject(password);
        //String valPwd = jsonObject.getString("password");
        String valPwd = password;
        if (SecurityUtils.getCurrentUserLogin() == null) {
            throw new UnAuthenticationMessageException(AuthenticationMessage.USERUNLOGIN);
        }
        UserEntity userEntity = accountsService.findByid(SecurityUtils.getCurrentUserLogin().getId());
        String newPassword = Encrypt.getMd5(valPwd + userEntity.getSalt());
        String oldPassword = userEntity.getPassword();
        if (newPassword.compareTo(oldPassword) != 0) {
            return new ResponseEntity<Boolean>(false, HttpStatus.OK);
        }
//        boolean isEqual = userEntity.getPassword().equals(Encrypt.getMd5(password + userEntity.getPassword()));
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    // 获取当前登录用户密码安全等级
    public ResponseEntity<Integer> getPasswordSafeLevel() {
        // do some magic!
        if (SecurityUtils.getCurrentUserLogin() == null) {
            throw new UnAuthenticationMessageException(AuthenticationMessage.USERUNLOGIN);
        }
        Integer userid = SecurityUtils.getCurrentUserLogin().getId();
        UserEntity userEntity = userService.findOne(userid);
        Integer pwdLv = userEntity.getPwdLv();
        return new ResponseEntity<Integer>(pwdLv, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<HttpMessage> addMyCareer(@ApiParam(value = "职业经历", required = true) @RequestBody TalentCareer talentCareer) {
        Integer id = talentService.addMyCareer(talentCareer);
        return id == null ? new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST) : new ResponseEntity<HttpMessage>(HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<HttpMessage> addMyEducation(@ApiParam(value = "教育经历", required = true) @RequestBody TalentEducation talentEducation) {
        Integer id = talentService.addMyEducation(talentEducation);
        return id == null ? new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST) : new ResponseEntity<HttpMessage>(HttpStatus.CREATED);

    }

    @Override
    @Login
    public ResponseEntity<HttpMessage> getUnbindCaptch(@ApiParam(value = "", required = true, allowableValues = "email,tel") @PathVariable("type") String type) {
        userService.getUnbindCaptch(type);
        return new ResponseEntity<HttpMessage>(CommonMessage.SENDSUCCESS, HttpStatus.OK);
    }

    @Override
    @Login
    public ResponseEntity<HttpMessage> unbind(@ApiParam(value = "", required = true, allowableValues = "email,tel") @PathVariable("type") String type
            , @ApiParam(value = "", required = true) @RequestParam("captch") String captch
    ) {
        userService.unbind(captch, type);
        return new ResponseEntity<HttpMessage>(AccountMessage.ACCOUNT_UNBIND_SUCCESS, HttpStatus.OK);
    }

    @Override
    @Login
    public ResponseEntity<HttpMessage> getbindCaptch(
            @ApiParam(value = "", required = true, allowableValues = "email,tel") @PathVariable("type") String type,
            @ApiParam(value = "", required = true) @RequestParam("account") String account) {

        AccountTypeEnum accountType = type.equalsIgnoreCase("email") ? AccountTypeEnum.EMAIL : type.equalsIgnoreCase("tel") ? AccountTypeEnum.TEL : null;

        ValidateUtils.AccountValidate(account,accountType);
        userService.getbindCaptch(accountType, account);
        return new ResponseEntity<HttpMessage>(CommonMessage.SENDSUCCESS, HttpStatus.OK);
    }

    @Override
    @Login
    public ResponseEntity<HttpMessage> bind( @ApiParam(value = "", required = true, allowableValues = "email,tel") @PathVariable("type") String type
            , @ApiParam(value = "职业经历", required = true) @RequestBody BindSumary bindSumary ) {
        AccountTypeEnum accountType = type.equalsIgnoreCase("email") ? AccountTypeEnum.EMAIL : type.equalsIgnoreCase("tel") ? AccountTypeEnum.TEL : null;
        ValidateUtils.AccountValidate(bindSumary.getAccount(),accountType);
        userService.bind(bindSumary.getAccount(),bindSumary.getCaptch(),accountType);
        return new ResponseEntity<HttpMessage>(CommonMessage.SUCCESS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> qualification( @RequestBody EnterQulification enterQulification) {
        accountsService.applyQulification(enterQulification);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    @Override
    @Login
    @Role(2)
    public ResponseEntity<PageInfo<AccountSumary>> getAccounts(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                               @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                               @ApiParam(value = "用户类型") @RequestParam(value = "role", required = false) Integer role,
                                                               @ApiParam(value = "关键字(用户账号)") @RequestParam(value = "account", required = false) String account,
                                                               @ApiParam(value = "排序字段和方式 例如：/policies?sort=-create_time|-update_time", allowableValues = "createTime, -createTime") @RequestParam(value = "sort", required = false,defaultValue = "-createTime") String sort) {


        PageRequest pr = new PageRequest(pageNumber, pageSize, SortUtils.assembleSort(sort));
        return new ResponseEntity<PageInfo<AccountSumary>>(userService.getAccounts(account,role,pr),HttpStatus.OK);
    }

    @Override
    @Role(2)
    public ResponseEntity<List<RoleBase>> getAccountRoles(@ApiParam(value = "通过账户id获取账户角色") @PathVariable(value = "id") Integer id) {
        return new ResponseEntity<List<RoleBase>>(userService.getAccountRoles(id),HttpStatus.OK);
    }

    @Override
    @Login
    @Role(2)
    public ResponseEntity<HttpMessage> administrateAccount(
            @ApiParam(value = "", required = true) @PathVariable(value = "id") Integer id,
            @ApiParam(value = "0:禁止用户 1:解除禁止 2:重置密码", required = true, allowableValues = "0,1,2") @RequestParam("type") Integer type) {

        userService.administrateAccount(id,type);

        return new ResponseEntity<HttpMessage>(CommonMessage.SUCCESS,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> getResetCaptcha(@ApiParam(value = "", required = true, allowableValues = "email,tel") @RequestParam("type") String type,
                                                     @ApiParam(value = "", required = true) @RequestParam(value = "account")  String account) {


        AccountTypeEnum accountType = type.equalsIgnoreCase("email") ? AccountTypeEnum.EMAIL : type.equalsIgnoreCase("tel") ? AccountTypeEnum.TEL : null;
        ValidateUtils.AccountValidate(account,accountType);

        UserSumary userSumary = userService.findUserSumary(account);
        if(userSumary == null){
            return new ResponseEntity<HttpMessage>(AccountMessage.ACCOUNTNOTFOUND, HttpStatus.BAD_REQUEST);
        }
        captchaFactory.sendCaptcha(account,accountType);
        return new ResponseEntity<HttpMessage>(CommonMessage.SENDSUCCESS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> resetAccount(@ApiParam(value = "", required = true) @RequestBody()  @Valid AccountResetRequest accountResetRequest) {

        userService.resetAccount(accountResetRequest);
        return new ResponseEntity<HttpMessage>(CommonMessage.UPDATESUCCESS,HttpStatus.OK);
    }


    @Override
    public ResponseEntity<HttpMessage> deleteExperience(@PathVariable("id") Integer id) {
        boolean d = accountsService.deleteExperience(id);
        if (!d){
            return new ResponseEntity<HttpMessage>(CommonMessage.DELETEFAIL,HttpStatus.OK);
        }
        return new ResponseEntity<HttpMessage>(CommonMessage.DELETESUCCESS,HttpStatus.OK);
    }


}
