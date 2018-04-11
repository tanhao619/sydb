package com.cdyoue.oddJobs.api.author;

import com.alibaba.fastjson.JSONObject;
import com.cdyoue.oddJobs.annotion.authentication.Login;
import com.cdyoue.oddJobs.annotion.authentication.Role;
import com.cdyoue.oddJobs.constant.CustomConstant;
import com.cdyoue.oddJobs.constant.TrackCategories;
import com.cdyoue.oddJobs.dao.lqsq.UserenterpriseResponsitory;
import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.UserSumary;
import com.cdyoue.oddJobs.dto.captch.AccountCaptch;
import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.common.message.AccountMessage;
import com.cdyoue.oddJobs.dto.common.message.AuthenticationMessage;
import com.cdyoue.oddJobs.dto.common.message.CommonMessage;
import com.cdyoue.oddJobs.dto.oauth.*;
import com.cdyoue.oddJobs.en.LoginTypeEnum;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserenterpriseEntity;
import com.cdyoue.oddJobs.event.EventPiwikTracking;
import com.cdyoue.oddJobs.exception.BadRequestMessageException;
import com.cdyoue.oddJobs.service.MenuService;
import com.cdyoue.oddJobs.service.RoleService;
import com.cdyoue.oddJobs.service.UserLogHistoryService;
import com.cdyoue.oddJobs.service.UserService;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.oddJobs.utils.SmsManager;
import com.cdyoue.oddJobs.utils.SysContent;
import com.cdyoue.oddJobs.utils.TokenMap;
import com.cdyoue.oddJobs.utils.jwt.Audience;
import com.cdyoue.oddJobs.utils.jwt.JwtHelper;
import com.cdyoue.oddJobs.utils.jwt.TokenCache;
import com.cdyoue.spring.common.SortUtils;
import com.cdyoue.spring.page.PageInfo;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liaoyoule on 2017/4/27.
 */
@Controller
public class AuthorizationApiController implements AuthorizationApi {
    @Autowired
    private UserService userService;

    @Autowired
    private Audience audience;
    @Autowired
    UserenterpriseResponsitory userenterpriseResponsitory;

    @Autowired
    private UserLogHistoryService userLogHistoryService;


    @Autowired
    private SmsManager smsManager;

    @Autowired
    JwtHelper jwtHelper;


    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;
    @Autowired
    private EventPiwikTracking eventPiwikTracking;


    @Override
    public ResponseEntity<UserSumary> getAuthorizationDetail(@ApiParam(value = "", required = true) @RequestParam("account") String account) {
        UserSumary userSumary = userService.findUserDetail(account);


        return new ResponseEntity<UserSumary>(userSumary, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TokenSumary> getAccessToken(@ApiParam(value = "", required = true) @RequestBody LoginPara loginPara) {
        TokenSumary ts =  userService.login(loginPara,false);
        return new ResponseEntity(ts, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TokenSumary> getAccessBackToken(@ApiParam(value = "", required = true) @RequestBody LoginPara loginPara) {
        TokenSumary ts =  userService.login(loginPara,true);
        return new ResponseEntity(ts, HttpStatus.OK);
    }

    @Override
    @Login
    public ResponseEntity<HttpMessage> logout() {
        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();//获取当前登录信息
        LoginTypeEnum loginTypeEnum = currentUserLogin.getLoginTypeEnum();//获取登录端（都为网页）
        Integer id = currentUserLogin.getId();
        TokenCache.removeStoreToken(id, loginTypeEnum);//清空本条token?
        HttpServletRequest request = SysContent.getRequest();
        String header = request.getHeader("Authorization");
        String token = request.getHeader("Authorization").substring(3, header.length()).trim();
        TokenMap.remove(token);
        userLogHistoryService.logout(id,loginTypeEnum.getLoginType());
        return new ResponseEntity<HttpMessage>(AuthenticationMessage.LOGOUTSUCCESS,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TokenSumary> getAccessTokenByRefreshToken(@ApiParam(value = "", required = true) @RequestParam("freshToken") String freshToken) {
        if (freshToken == null || StringUtils.isBlank(freshToken)) {
            return new ResponseEntity<TokenSumary>(HttpStatus.BAD_REQUEST);
        }
        Claims claims = jwtHelper.parseJWT(freshToken, audience.getBase64Secret());

        String sujectId = claims.getSubject();

        String jcStr = JSONObject.toJSONString(claims.get("info"));
        JwtClaim jc = JSONObject.parseObject(jcStr, JwtClaim.class);
        TokenSumary storeToken = TokenCache.getStoreToken(Integer.parseInt(sujectId), jc.getTokenType());
        if(storeToken == null){
            storeToken = userLogHistoryService.getStoreToken(Integer.parseInt(claims.getSubject()), jc.getTokenType());
        }
        if (storeToken == null || !storeToken.getRefresh_token().equals(freshToken)) {
            return new ResponseEntity(AuthenticationMessage.FERESHTOKENINVALIDTOKEN, HttpStatus.BAD_REQUEST);
        }

        String issuer = claims.getIssuer();
        if (!CustomConstant.REFRESH_TOKEN_ISSUER.equalsIgnoreCase(issuer)) {
            return new ResponseEntity(AuthenticationMessage.FERESHTOKENINVALIDISSUREY, HttpStatus.BAD_REQUEST);

        }
        //判断是否过期
        long expiration = claims.getExpiration().getTime();
        //当前时间
        long current = System.currentTimeMillis();
        //时间戳
        long second = TimeUnit.MILLISECONDS.toSeconds(expiration - current);
        //如果时间戳小于等于0，证明已经失效
        if (second <= 0) {
            return new ResponseEntity(AuthenticationMessage.FERESHTOKENTIMEOUT, HttpStatus.BAD_REQUEST);
        }

        //拼装accessToken
        TokenSumary ts = jwtHelper.createJWT(sujectId, jc, audience.getBase64Secret());

        UserEntity ue = userService.findOne(Integer.parseInt(sujectId));
        if(ue==null){
            return new ResponseEntity(AuthenticationMessage.FERESHTOKENINVALIDTOKEN, HttpStatus.BAD_REQUEST);
        }
        UserMine userMine = new UserMine();
        userMine.setId(ue.getId());
        userMine.setRole(ue.getRole());
        userMine.setUserType(ue.getUserType());
        userMine.setLoginTypeEnum(jc.getTokenType());
        if(ue.getRole() == 2){
            userMine.setTopMenus(userService.getTopMenusByUserId(userMine.getId()));
        }

        ts.setUserMine(userMine);
        userLogHistoryService.update(sujectId,ts);

        return new ResponseEntity<TokenSumary>(ts, HttpStatus.OK);
    }



    @Override
    public ResponseEntity<HttpMessage> checkLogin(@ApiParam(value = "", required = true) @RequestParam(value = "token")  String token) {
        token = token.replaceAll("#@#",".");
        //result:1、token有效，0、token超时失效，-1、token不存在
        Integer result = TokenMap.checkTokenUtil(jwtHelper, token, audience);
        if(result == 1){
            return new ResponseEntity(AuthenticationMessage.LoginStatus, HttpStatus.OK);
        }else if(result == 0){
            return new ResponseEntity(AuthenticationMessage.TIMEOUT, HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity(AuthenticationMessage.INVALIDTOKEN, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<UserInfo> getUidByTokenString(@ApiParam(value = "", required = true) @RequestParam(value = "token")  String token) {
        TokenSumary tokenSumary = null;
//        token = token.replaceAll("#@#",".");
        tokenSumary = TokenMap.getToken(token);
        //result:1、token有效，0、token超时失效，-1、token不存在
        Integer result = TokenMap.checkTokenUtil(jwtHelper, token, audience);
        if(result == 1){
            UserInfo userInfo = new UserInfo();
            userInfo.setId(tokenSumary.getUserMine().getId());
            return new ResponseEntity(userInfo, HttpStatus.OK);
        }else if(result == 0){
            return new ResponseEntity(AuthenticationMessage.TIMEOUT, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity(AuthenticationMessage.INVALIDTOKEN, HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<UserInfo> getUserMessageByToken(@ApiParam(value = "", required = true) @RequestParam(value = "token")  String token) {
        TokenSumary tokenSumary = null;
//        token = token.replaceAll("#@#",".");
        tokenSumary = TokenMap.getToken(token);
        //result:1、token有效，0、token超时失效，-1、token不存在
        Integer result = TokenMap.checkTokenUtil(jwtHelper, token, audience);
        if(result == 1){
            UserInfo userInfo = new UserInfo();
            UserEntity entity = userService.findOne(tokenSumary.getUserMine().getId());
            UserenterpriseEntity oneByUid = userenterpriseResponsitory.findOneByUid(tokenSumary.getUserMine().getId());
            userInfo.setId(entity.getId());
            userInfo.setUserName(oneByUid.getName());
            userInfo.setEmail(entity.getEmail());
            return new ResponseEntity(userInfo, HttpStatus.OK);
        }else if(result == 0){
            return new ResponseEntity(AuthenticationMessage.TIMEOUT, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity(AuthenticationMessage.INVALIDTOKEN, HttpStatus.BAD_REQUEST);
        }


    }

    @Override
    public ResponseEntity<HttpMessage> checkUserPassword(@ApiParam(value = "", required = true) @RequestParam(value = "uid") Integer uid, @ApiParam(value = "", required = true) @RequestParam(value = "password") String password) {
        Integer result = userService.checkUserPassword(uid,password);
        if(result == -1){
            return new ResponseEntity(AuthenticationMessage.ACCOUNTNOTFOUND, HttpStatus.BAD_REQUEST);
        }else if(result == 0){
            return new ResponseEntity(AuthenticationMessage.PasswordTrue, HttpStatus.OK);
        }else if(result == 1){
            return new ResponseEntity(AuthenticationMessage.PasswordFalse, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<HttpMessage> registerByEmail(@ApiParam(value = "", required = true) @Valid @RequestBody RegisterRequest registerRequest) {
        //验证码
        Integer result = userService.registerByEmail(registerRequest);
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest hsr  =attributes.getRequest();
        if(result != null && hsr.getHeader("IP") == null){
            eventPiwikTracking.doTracking(TrackCategories.register,registerRequest.getEmail());
        }
        return result == null ? new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST) : new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> getTelCaptcha(@ApiParam(value = "", required = true) @RequestParam(value = "tel") String tel) {
        String regexp = "^1[34578]\\d{9}$";

        Pattern pattern = Pattern.compile(regexp);

        Matcher matcher = pattern.matcher(tel);
        if(!matcher.matches()){
            return new ResponseEntity<HttpMessage>(CommonMessage.ILLEGALTEL, HttpStatus.BAD_REQUEST);//手机号不合法
        }
//        UserSumary userSumary = userService.findUserSumary(tel);
//        if(userSumary != null){
//            return new ResponseEntity<HttpMessage>(AuthenticationMessage.ACCCOUNTISEXIST, HttpStatus.BAD_REQUEST);//用户已存在
//        }
        //发送验证码
        try {
            smsManager.sendTelCaptcha(Long.parseLong(tel));
            return new ResponseEntity<HttpMessage>(CommonMessage.SUCCESS,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<HttpMessage>(CommonMessage.TOOMANY,HttpStatus.BAD_REQUEST);
        }



    }



    @Override
    public ResponseEntity<HttpMessage> checkTelCaptcha(@ApiParam(value = "", required = true) @PathVariable(value = "tel") String tel, @ApiParam(value = "", required = true) @PathVariable(value = "captcha") String captcha) {
        long telNumber = Long.parseLong(tel);
        AccountCaptch accountCaptch = TokenCache.getTelCaptch(telNumber);
        String code = accountCaptch.getCode()+"";
        if (accountCaptch == null || !code.equals(captcha)) {
            throw new BadRequestMessageException(AccountMessage.TelTelCaptchERROR);//验证码错误
        }
        if (System.currentTimeMillis() - accountCaptch.getExpire() > 0) {
            throw new BadRequestMessageException(AccountMessage.TELTELCAPTCHTIMEOUT);//验证码失效
        }
        return new ResponseEntity<HttpMessage>(AccountMessage.CHECKSUCCESSFUL,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> registerByTel(@ApiParam(value = "", required = true) @Valid @RequestBody  RegisterByTelRequest telRequest) {

        Integer reBool = userService.registerByTel(telRequest);
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest hsr  =attributes.getRequest();
//        if(hsr.getHeader("IP") == null){
//            eventPiwikTracking.doTracking(TrackCategories.register,telRequest.getTel());
//        }
        if(reBool == 0){
            return new ResponseEntity<HttpMessage>(AuthenticationMessage.CREATESUCCESS,HttpStatus.OK);
        }else if(reBool == -1){
            return new ResponseEntity<HttpMessage>(AuthenticationMessage.CodeFalse,HttpStatus.INTERNAL_SERVER_ERROR);
        }else if(reBool == -2){
            return new ResponseEntity<HttpMessage>(AuthenticationMessage.CodeTimeOut,HttpStatus.INTERNAL_SERVER_ERROR);
        }else if(reBool == 1){
            return new ResponseEntity<HttpMessage>(AuthenticationMessage.ServerWrong,HttpStatus.INTERNAL_SERVER_ERROR);
        }else if(reBool == 2){
            return new ResponseEntity<HttpMessage>(AuthenticationMessage.ACCCOUNTISEXIST,HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            return new ResponseEntity<HttpMessage>(AuthenticationMessage.ServerWrong,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * 北京账户注册接口
     */
//    public void saveAccount(){
//
//
//    }

    public static void main(String[] args) {
        Integer userId= 103;
        Integer userType= 1;

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());


        JSONObject reqVO = new JSONObject();
        reqVO.put("userId", userId);
        reqVO.put("userType", userType);

        String jsonStr = JSONObject.toJSONString(reqVO);
        HttpEntity<String> formEntity = new HttpEntity<String>(jsonStr);
//        ResponseEntity<String> responseEntity  = restTemplate.postForEntity("http://192.168.0.168:9093/user/saveAccount", formEntity, String.class);
        ResponseEntity<JSONObject> exchange = restTemplate.exchange("http://syda.youedata.com/di/user/saveAccount", HttpMethod.POST, formEntity, JSONObject.class);
//        String body = responseEntity.getBody();

    }

    @Override
    @Login
    @Role(2)
    public ResponseEntity<PageInfo<RoleSumary>> getRoles(
            @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @ApiParam(value = "关键字搜索") @RequestParam(value = "q", required = false) String q,
            @ApiParam(value = "排序字段和方式 例如：/policies?sort=-create_time|-update_time", allowableValues = "createTime, -createTime") @RequestParam(value = "sort", required = false,defaultValue = "-createTime") String sort) {

        PageRequest pr = new PageRequest(pageNumber, pageSize, SortUtils.assembleSort(sort));
        return new ResponseEntity(roleService.getRoles(q,pr),HttpStatus.OK);
    }

    @Override
    @Login
    @Role(2)
    public ResponseEntity<RoleSumary> getRole( @ApiParam(value = "role id") @PathVariable(value = "id", required = true) Integer id) {
        return new ResponseEntity(roleService.getRole(id),HttpStatus.OK);
    }

    @Override
    @Login
    @Role(2)
    public ResponseEntity<HttpMessage> createRole( @ApiParam(value = "角色信息", required = true)@Valid @RequestBody RoleRequest roleRequest) {
        roleService.save(roleRequest);
        return new ResponseEntity(CommonMessage.CREATESUCCESS,HttpStatus.OK);
    }

    @Override
    @Login
    @Role(2)
    public ResponseEntity<HttpMessage> updateAccountRoles(@ApiParam(value = "ids 角色id 如果有多个用逗号隔开 例如 1,2,3", required = true) @PathVariable("ids") String ids,
                                                          @ApiParam(value = "id", required = true) @RequestParam(value = "id",required = true) Integer id) {
        roleService.updateAccountRoles(id,ids);
        return new ResponseEntity<HttpMessage>(CommonMessage.UPDATESUCCESS,HttpStatus.OK);
    }


    @Override
    @Login
    @Role(2)
    public ResponseEntity<HttpMessage> updateRole(
            @ApiParam(value = "role id") @PathVariable(value = "id", required = true) Integer id,
            @ApiParam(value = "角色信息", required = true) @RequestBody RoleRequest roleRequest) {
        roleService.update(id,roleRequest);
        return new ResponseEntity(CommonMessage.UPDATEFAIL,HttpStatus.OK);
    }

    @Override
    @Login
    @Role(2)
    public ResponseEntity<HttpMessage> deleteRole( @ApiParam(value = "role id") @PathVariable(value = "id", required = true) Integer id) {
        roleService.delete(id);
        return new ResponseEntity(CommonMessage.DELETESUCCESS,HttpStatus.OK);
    }


    @Override
    @Login
    @Role(2)
    public ResponseEntity<PageInfo<MenuSumary>> getMenus(
            @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
//            @ApiParam(value = "关键字") @RequestParam(value = "q", required = false) String q,
            @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber) {
        PageRequest pr = new PageRequest(pageNumber,pageSize);
        return new ResponseEntity(menuService.getMenus(pr),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> checkAccount(@ApiParam(value = "") @RequestParam(value = "tel",defaultValue = "") String tel,
                                                    @ApiParam(value = "") @RequestParam(value = "email",defaultValue = "") String email) {
        Boolean result = new Boolean(false);
        if((tel != null && tel != "") || (email != null && email != "")){
            result = userService.checkAccount(tel,email);
        }
        return new ResponseEntity(result,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> forgetPassword(@ApiParam(value = "15199090067", required = false) @RequestParam(value = "tel") String tel, @ApiParam(value = "123456", required = false) @RequestParam(value = "password") String password) {
        userService.forgetPassword(tel,password);
        return new ResponseEntity(HttpStatus.OK,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> checkInput(@ApiParam(value = "15199090067", required = false) @RequestParam(value = "tel") String tel,
                                                  @ApiParam(value = "674449973@qq.com", required = false) @RequestParam(value = "email") String email,
                                                  @ApiParam(value = "1234", required = false) @RequestParam(value = "input") String input,
                                                  @ApiParam(value = "4321", required = false) @RequestParam(value = "testInput") String testInput) {
        Integer result = 0;
        Boolean b = new Boolean(false);
        //查询用户，返回布尔值。若存在为true,不存在为false;
        if((tel != null && tel != "") || (email != null && email != "")){
            b = userService.checkAccount(tel,email);
        }

        if(b){
           if(input == null || input.equals("") ){
               result=1;//验证码为空
               return new ResponseEntity(result,HttpStatus.OK);
           }else{
               if(input.equals(testInput) && !input.equals("") && !testInput.equals("")){
                   return new ResponseEntity(result,HttpStatus.OK);//验证成功
               }else{
                   result=2;//验证码输入错误
                   return new ResponseEntity(result,HttpStatus.OK);
               }
           }
        }else {
            result=-1;//用户不存在
            return new ResponseEntity(result,HttpStatus.OK);
        }
    }
}

