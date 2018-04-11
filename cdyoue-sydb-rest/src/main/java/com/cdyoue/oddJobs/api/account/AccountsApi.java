package com.cdyoue.oddJobs.api.account;


import com.cdyoue.oddJobs.dto.account.*;
import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.lgfc.TalentCareer;
import com.cdyoue.oddJobs.dto.lgfc.TalentEducation;
import com.cdyoue.oddJobs.dto.oauth.RoleBase;
import com.cdyoue.oddJobs.dto.oauth.RoleSumary;
import com.cdyoue.oddJobs.dto.requirement.RequireMine;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-07T12:12:19.725Z")

@Api(value = "accounts", description = "the accounts API")
public interface AccountsApi {
    //==================================================沈阳接口=================================================//
    @ApiOperation(value = "获取当前登录的企业账号信息(完成)", notes = "获取企业自己的账号信息", response = EnterAccountDetail.class, tags = {"accounts",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = EnterAccountDetail.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "页面没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class)})
    @RequestMapping(value = "/accounts/enterprise/my",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<EnterAccountDetail> getMyEnterpriseInfo();

    @ApiOperation(value = "编辑自己企业信息(完成)", notes = "编辑自己企业信息", response = HttpMessage.class, tags = {"accounts",})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "需求没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class)})
    @RequestMapping(value = "/accounts/enterprise/{id}",
            produces = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updateEnterAccount(
            @ApiParam(value = "id", required = true) @PathVariable("id") Integer id,
            @ApiParam(value = "企业信息", required = true) @RequestBody EnterAccountSumary enterAccountSumary
    );


    @ApiOperation(value = "发送邮件验证码（完成）", notes = "发送邮件验证码", response = HttpMessage.class, tags = {"accounts",})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "页面没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class)})
    @RequestMapping(value = "/accounts/mail/vcode/send/{address}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<HttpMessage> sendMail(@ApiParam(value = "邮箱地址", required = true) @PathVariable("address") String address);

    @ApiOperation(value = "验证邮件验证码(完成)", notes = "验证邮件验证码", response = HttpMessage.class, tags = {"accounts",})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "页面没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class)})
    @RequestMapping(value = "/accounts/mail/vcode/verify/{address}/{yzm}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<HttpMessage> checkMail(@ApiParam(value = "验证码", required = true) @PathVariable("yzm") String yzm,
                                          @ApiParam(value = "邮箱地址", required = true) @PathVariable("yzm") String address);


    //===============================================================================================================

    @ApiOperation(value = "获取任意企业用户账号信息（完成）", notes = "根据id获取账号信息", response = EnterAccountDetail.class, tags = {"accounts",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = EnterAccountDetail.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "页面没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class)})
    @RequestMapping(value = "/accounts/enterprise/{id}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity getEnterpriseInfo(@ApiParam(value = "用户ID", required = true) @PathVariable("id") Integer id);

    @ApiOperation(value = "获取个人账号信息（完成）", notes = "获取个人自己的账号信息", response = PersonAccountDetail.class, tags = {"accounts",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = PersonAccountDetail.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "页面没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class)})
    @RequestMapping(value = "/accounts/person/my",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<PersonAccountDetail> getMyPersonInfo();

    @ApiOperation(value = "获取任意个人用户账号信息（完成）", notes = "根据id获取账号信息", response = PersonAccountDetail.class, tags = {"accounts",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = PersonAccountDetail.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "页面没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class)})
    @RequestMapping(value = "/accounts/person/{id}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity getPersonInfo(@ApiParam(value = "用户ID", required = true) @PathVariable("id") Integer id);

    @ApiOperation(value = "编辑自己企业信息(完成)", notes = "编辑自己企业信息", response = HttpMessage.class, tags = {"accounts",})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "需求没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class)})
    @RequestMapping(value = "/accounts/enterprise/my",
            produces = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updateMyEnterAccount(@ApiParam(value = "企业信息", required = true) @RequestBody EnterEditRequest enterEditRequest);

    @ApiOperation(value = "编辑个人账号信息(完成)", notes = "编辑个人账号信息", response = HttpMessage.class, tags = {"accounts",})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "需求没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class)})
    @RequestMapping(value = "/accounts/person/my",
            produces = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updateMyPersonAccount(@ApiParam(value = "栏目名称", required = true) @RequestBody PersonAccountSumary personAccountSumary);


    @ApiOperation(value = "根据用户id编辑个人账号信息(完成)", notes = "根据用户id编辑个人账号信息", response = HttpMessage.class, tags = {"accounts",})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "需求没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class)})
    @RequestMapping(value = "/accounts/person/{id}",
            produces = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updatePersonAccount(
            @ApiParam(value = "用户id", required = true) @PathVariable("id") Integer id,
            @ApiParam(value = "栏目名称", required = true) @RequestBody PersonAccountSumary personAccountSumary);


    @ApiOperation(value = "企业、个人注册输入邮箱（新增，已废弃）", notes = "验证邮箱是否存在", response = HttpMessage.class, tags = {"accounts",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "页面没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class)})
    @RequestMapping(value = "/accounts/user/register",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<HttpMessage> registerEnterprises(@ApiParam(value = "注册邮箱", required = true) @RequestBody String email);


    @ApiOperation(value = "企业注册（已废弃）", notes = "企业注册", response = HttpMessage.class, tags = {"accounts",})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "页面没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class)})
    @RequestMapping(value = "/accounts/enterprise/register",
            produces = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> registerEnterprise(@ApiParam(value = "企业注册信息", required = true) @RequestBody EnterRegisterInfo enterInfo);


    @ApiOperation(value = "个人注册（已废弃）", notes = "个人注册", response = HttpMessage.class, tags = {"accounts",})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "页面没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class)})
    @RequestMapping(value = "/accounts/person/register",
            produces = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> registerPerson(@ApiParam(value = "个人注册信息", required = true) @RequestBody PersonRegisterInfo personInfo);


    @ApiOperation(value = "修改账号密码（完成）", notes = "通过旧密码和新密码", response = HttpMessage.class, tags = {"accounts",})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "需求没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class)})
    @RequestMapping(value = "/accounts/password",
            produces = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updatePassword(@ApiParam(value = "新旧密码", required = true) @RequestParam String newPwd);

    @ApiOperation(value = "修改我的职业经历(完成)", notes = "修改我的职业经历", response = HttpMessage.class, tags = {"accounts",})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "用户没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class)})
    @RequestMapping(value = "/accounts/my/careers/{id}",
            produces = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updateMyCareer(@ApiParam(value = "", required = true) @PathVariable("id") Integer id, @ApiParam(value = "职业经历", required = true) @RequestBody TalentCareer talentCareer);


    @ApiOperation(value = "修改我的教育经历(完成)", notes = "修改我的教育经历", response = HttpMessage.class, tags = {"accounts",})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "用户没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class)})
    @RequestMapping(value = "/accounts/my/educations/{id}",
            produces = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updateMyEducation(@ApiParam(value = "", required = true) @PathVariable("id") Integer id, @ApiParam(value = "教育经历", required = true) @RequestBody TalentEducation talentEducation);


    @ApiOperation(value = "检查密码是否和原密码相同（完成）", notes = "检查密码是否和原密码相同", response = Boolean.class, tags = {"accounts",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "创建成功", response = Boolean.class)})
    @RequestMapping(value = "/accounts/checkpassword",
            produces = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Boolean> checkPassword(@ApiParam(value = "密码", required = true) @RequestParam String password);


    @ApiOperation(value = "获取当前登录用户密码安全等级（完成）", notes = "获取当前登录用户密码安全等级", response = Integer.class, tags = {"accounts",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "获取成功", response = Integer.class)})
    @RequestMapping(value = "/accounts/safelevel",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Integer> getPasswordSafeLevel();

    @ApiOperation(value = "新增我的职业经历(完成)", notes = "新增我的职业经历", response = HttpMessage.class, tags = {"accounts",})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "用户没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class)})
    @RequestMapping(value = "/accounts/my/career",
            produces = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> addMyCareer(@ApiParam(value = "职业经历", required = true) @RequestBody TalentCareer talentCareer);


    @ApiOperation(value = "新增我的教育经历(完成)", notes = "新增我的教育经历", response = HttpMessage.class, tags = {"accounts",})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "用户没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class)})
    @RequestMapping(value = "/accounts/my/educations",
            produces = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> addMyEducation(@ApiParam(value = "教育经历", required = true) @RequestBody TalentEducation talentEducation);


    /**
     * 解除绑定
     */
    @ApiOperation(value = "获取解除绑定验证码（完成）", notes = "获取解除绑定验证码", response = Integer.class, tags = {"accounts",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "获取成功", response = Integer.class)})
    @RequestMapping(value = "/accounts/{type}/unbindCaptch",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<HttpMessage> getUnbindCaptch(@ApiParam(value = "", required = true, allowableValues = "email,tel") @PathVariable("type") String type);

    @ApiOperation(value = "解除绑定（完成）", notes = "解除绑定", response = Integer.class, tags = {"accounts",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "获取成功", response = Integer.class)})
    @RequestMapping(value = "/accounts/{type}/unbind",
            produces = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> unbind(@ApiParam(value = "", required = true, allowableValues = "email,tel") @PathVariable("type") String type
            , @ApiParam(value = "", required = true) @RequestParam("captch") String captch
    );


    /**
     * 绑定
     */
    @ApiOperation(value = "获取绑定验证码（完成）", notes = "绑定验证码", response = Integer.class, tags = {"accounts",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "获取成功", response = Integer.class)})
    @RequestMapping(value = "/accounts/{type}/bindCaptch",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<HttpMessage> getbindCaptch(
            @ApiParam(value = "", required = true, allowableValues = "email,tel") @PathVariable("type") String type,
            @ApiParam(value = "", required = true) @RequestParam("account") String account
    );


    @ApiOperation(value = "账户更换设置--邮箱或手机（完成）", notes = "账户更换设置", response = Integer.class, tags = {"accounts",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "获取成功", response = Integer.class)})
    @RequestMapping(value = "/accounts/{type}/bind",
            produces = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> bind(
            @ApiParam(value = "", required = true, allowableValues = "email,tel") @PathVariable("type") String type
            ,  @ApiParam(value = "职业经历", required = true) @RequestBody BindSumary bindSumary
    );

    @ApiOperation(value = "企业资质认证申请（已废弃）", notes = "账户更换设置", response = Integer.class, tags = {"accounts",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "增加成功", response = Integer.class)})
    @RequestMapping(value = "/accounts/{type}/qualification",
            produces = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> qualification(
            @ApiParam(value = "资质认证信息", required = true) @RequestBody EnterQulification enterQulification
    );
    @ApiOperation(value = "删除经历（完成）", notes = "删除经历", response = Integer.class, tags = {"accounts",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "删除成功", response = Integer.class)})
    @RequestMapping(value = "/accounts//experience/{id}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteExperience(@PathVariable("id") Integer id
    );


    /**
     * ====================
     *  后台管理
     * ====================
     */







    @ApiOperation(value = "获取账户列表--管理员(完成)", notes = "获取所有账户", response = RequireMine.class, tags={ "accounts", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = AccountSumary.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "xxx 没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/accounts",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<PageInfo<AccountSumary>> getAccounts(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                        @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                        @ApiParam(value = "用户类型") @RequestParam(value = "role", required = false) Integer role,
                                                        @ApiParam(value = "关键字(用户账号)") @RequestParam(value = "account", required = false) String account,
                                                        @ApiParam(value = "排序字段和方式 例如：/policies?sort=-create_time|-update_time", allowableValues = "createTime, -createTime") @RequestParam(value = "sort", required = false,defaultValue = "-createTime") String sort);






    @ApiOperation(value = "获取账户角色--管理员(完成)", notes = "获取账户角色", response = RequireMine.class, tags={ "accounts", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = AccountSumary.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "xxx 没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/accounts/{id}/roles",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<RoleBase>> getAccountRoles(@ApiParam(value = "通过账户id获取账户角色") @PathVariable(value = "id") Integer id);




    @ApiOperation(value = "账户禁止 解除禁止 重置密码(完成)", notes = "账户禁止 解除禁止 重置密码", response = RequireMine.class, tags={ "accounts", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "xxx 没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/accounts/{id}",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> administrateAccount(
            @ApiParam(value = "", required = true) @PathVariable(value = "id") Integer id,
            @ApiParam(value = "0:禁止用户 1:解除禁止 2:重置密码", required = true, allowableValues = "0,1,2") @RequestParam("type") Integer type
    );











    /**
     * =============
     * 密码找回
     * =============
     */
    @ApiOperation(value = "获取重置验证码(完成)", notes = "", response = HttpMessage.class, tags={ "accounts", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "xxx 没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/accounts/resetCaptcha",
            method = RequestMethod.GET)
    ResponseEntity<HttpMessage> getResetCaptcha(
            @ApiParam(value = "", required = true, allowableValues = "email,tel") @RequestParam("type") String type,
            @ApiParam(value = "", required = true) @RequestParam(value = "account")  String account );





    @ApiOperation(value = "密码找回（完成）", notes = "密码找回", response = Integer.class, tags = {"accounts",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "删除成功", response = Integer.class)})
    @RequestMapping(value = "/accounts/resetting",
            produces = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> resetAccount(@ApiParam(value = "", required = true) @RequestBody()  AccountResetRequest accountResetRequest);




}