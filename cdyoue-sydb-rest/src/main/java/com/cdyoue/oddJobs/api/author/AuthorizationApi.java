package com.cdyoue.oddJobs.api.author;

import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.UserSumary;
import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.oauth.*;
import com.cdyoue.oddJobs.entity.syData.EquipmentEntity;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

/**
 * Created by liaoyoule on 2017/4/27.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-20T01:12:00.448Z")

@Api(value = "oauth", description = "此api 用于第一个spring 项目演示")
public interface AuthorizationApi {
    @ApiOperation(value = "获取用户信息(废弃)", notes = "", response = UserSumary.class, tags={ "oauth", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = UserSumary.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "xxx 没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/authorization",
            produces = { "application/json" },
            method = RequestMethod.GET)
    @ApiIgnore
    ResponseEntity<UserSumary> getAuthorizationDetail(@ApiParam(value = "", required = true) @RequestParam("account") String account);


    @ApiOperation(value = "前台邮箱注册账户(完成)", notes = "", response = HttpMessage.class, tags={ "oauth", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "xxx 没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/register",
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> registerByEmail(@ApiParam(value = "", required = true) @Valid @RequestBody RegisterRequest registerRequest);

    @ApiOperation(value = "获取所有角色(完成)", notes = "", response = HttpMessage.class, tags={ "oauth", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = RoleSumary.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "xxx 没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/roles",
            method = RequestMethod.GET)
    ResponseEntity<PageInfo<RoleSumary>> getRoles(
            @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @ApiParam(value = "关键字搜索") @RequestParam(value = "q", required = false) String q,
            @ApiParam(value = "排序字段和方式 例如：/policies?sort=-create_time|-update_time", allowableValues = "createTime, -createTime") @RequestParam(value = "sort", required = false,defaultValue = "-createTime") String sort

    );


    @ApiOperation(value = "获取角色详细(完成)", notes = "", response = HttpMessage.class, tags = {"oauth",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = RoleSumary.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "xxx 没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class)})
    @RequestMapping(value = "/role/{id}",
            method = RequestMethod.GET)
    ResponseEntity<RoleSumary> getRole(
            @ApiParam(value = "role id") @PathVariable(value = "id", required = true) Integer id
    );


    @ApiOperation(value = "新增角色(完成)", notes = "新增角色", response = HttpMessage.class, tags = {"oauth",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "xxx 没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class)})
    @RequestMapping(value = "/role",
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> createRole(  @ApiParam(value = "角色信息", required = true) @Valid @RequestBody RoleRequest roleRequest);



    @ApiOperation(value = "分配账户角色(完成)", notes = "分配账户角色", response = HttpMessage.class, tags = {"oauth",})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "需求没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class)})
    @RequestMapping(value = "/role/{ids}",
            produces = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> updateAccountRoles(
            @ApiParam(value = "ids 角色id 如果有多个用逗号隔开 例如 1,2,3", required = true) @PathVariable("ids") String ids,
            @ApiParam(value = "id", required = true) @RequestParam(value = "id",required = true) Integer id
    );


    @ApiOperation(value = "修改角色(完成)", notes = "新增角色", response = HttpMessage.class, tags = {"oauth",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "xxx 没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class)})
    @RequestMapping(value = "/role/{id}",
            method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updateRole(
            @ApiParam(value = "role id") @PathVariable(value = "id", required = true) Integer id,
            @ApiParam(value = "角色信息", required = true) @RequestBody RoleRequest roleRequest);



    @ApiOperation(value = "删除角色(完成)", notes = "删除角色", response = HttpMessage.class, tags = {"oauth",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "xxx 没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class)})
    @RequestMapping(value = "/role/{id}",
            method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteRole(
            @ApiParam(value = "role id") @PathVariable(value = "id", required = true) Integer id
    );

    @ApiOperation(value = "获取所有菜单", notes = "获取所有菜单", response = HttpMessage.class, tags = {"oauth",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = MenuSumary.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "xxx 没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class)})
    @RequestMapping(value = "/menus",
            method = RequestMethod.GET)
    ResponseEntity<PageInfo<MenuSumary>> getMenus(
            @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
//            @ApiParam(value = "关键字") @RequestParam(value = "q", required = false) String q,
            @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber
    );

    /***************
     *  沈阳接口  *
     * @param tel *
     * @return    *
     ***************/

    @ApiOperation(value = "获取短信验证码(完成)", notes = "", response = HttpMessage.class, tags={ "LoginAndRegister", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "xxx 没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/telCaptcha",
            method = RequestMethod.GET)
    ResponseEntity<HttpMessage> getTelCaptcha(@ApiParam(value = "", required = true) @PathVariable(value = "tel")  String tel);

    @ApiOperation(value = "验证短信验证码(完成)", notes = "", response = HttpMessage.class, tags={ "LoginAndRegister", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "xxx 没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/checkTelCaptcha/{tel}/{captcha}",
            method = RequestMethod.GET)
    ResponseEntity<HttpMessage> checkTelCaptcha(@ApiParam(value = "", required = true) @PathVariable(value = "tel")  String tel,
                                                @ApiParam(value = "", required = true) @PathVariable(value = "captcha")  String captcha);


    @ApiOperation(value = "前台个人注册手机注册账户（完成）", notes = "", response = HttpMessage.class, tags={ "LoginAndRegister", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "xxx 没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/telRegister",
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> registerByTel(@ApiParam(value = "", required = true) @Valid @RequestBody  RegisterByTelRequest telRequest);


    @ApiOperation(value = "前台用户根据账户密码获取token登陆(完成)", notes = "", response = String.class, tags={ "LoginAndRegister", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = TokenSumary.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "xxx 没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/login",
            method = RequestMethod.POST)
    ResponseEntity<TokenSumary> getAccessToken(@ApiParam(value = "", required = true) @RequestBody LoginPara loginPara);

    @ApiOperation(value = "后台用户根据账户密码获取token登陆(完成)", notes = "", response = TokenSumary.class, tags={ "LoginAndRegister", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = TokenSumary.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "xxx 没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/loginBack",
            method = RequestMethod.POST)
    ResponseEntity<TokenSumary> getAccessBackToken(@ApiParam(value = "", required = true) @RequestBody LoginPara loginPara);

    @ApiOperation(value = "前台用户退出", notes = "", response = HttpMessage.class, tags={ "LoginAndRegister", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = TokenSumary.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "xxx 没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/logout",
            method = RequestMethod.GET)
    ResponseEntity<HttpMessage> logout();

    @ApiOperation(value = "根据token fresh token(疑问)", notes = "", response = TokenSumary.class, tags={ "LoginAndRegister", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = TokenSumary.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "xxx 没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/refreshToken",
            method = RequestMethod.POST)
    ResponseEntity<TokenSumary> getAccessTokenByRefreshToken(@ApiParam(value = "", required = true) @RequestParam("freshToken")  String freshToken);

    @ApiOperation(value = "验证电话或者邮箱是否存在", notes = "", response = HttpMessage.class, tags={ "LoginAndRegister", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = boolean.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "xxx 没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/checkAccount",
            method = RequestMethod.GET)
    ResponseEntity<HttpMessage> checkAccount(@ApiParam(value = "15199090067",required = false) @RequestParam(value = "tel")  String tel,
                                       @ApiParam(value = "674449973@qq.com",required = false) @RequestParam(value = "email")  String email);

    @ApiOperation(value = "找回密码验证", notes = "", response = HttpMessage.class, tags={ "LoginAndRegister", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = boolean.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "xxx 没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/checkAccount/checkInput",
            method = RequestMethod.GET)
    ResponseEntity<HttpMessage> checkInput(@ApiParam(value = "15199090067",required = false) @RequestParam(value = "tel")  String tel,
                                             @ApiParam(value = "674449973@qq.com",required = false) @RequestParam(value = "email")  String email,
                                           @ApiParam(value = "674449973@qq.com",required = false) @RequestParam(value = "input")  String input,
                                           @ApiParam(value = "674449973@qq.com",required = false) @RequestParam(value = "testInput")  String testInput);


    @ApiOperation(value = "忘记密码(完成)", notes = "忘记密码", response = HttpMessage.class, tags={ "LoginAndRegister", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "文章没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/forgetPassword",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<HttpMessage> forgetPassword(@ApiParam(value = "15199090067",required = false) @RequestParam(value = "tel")  String tel,
                                                @ApiParam(value = "123456",required = false) @RequestParam(value = "password")  String password);

    @ApiOperation(value = "验证登录（北京）", notes = "提供给北京使用", response = HttpMessage.class, tags={ "LoginAndRegister", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "文章没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/checkLogin",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<HttpMessage> checkLogin(@ApiParam(value = "", required = true) @RequestParam(value = "token")  String token);

    @ApiOperation(value = "通过token获取用户ID（北京）", notes = "提供给北京使用", response = Integer.class, tags={ "LoginAndRegister", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "文章没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/checkLogin/getUid",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<UserInfo> getUidByTokenString(@ApiParam(value = "", required = true) @RequestParam(value = "token")  String token);

    @ApiOperation(value = "通过token获取用户信息（北京）", notes = "提供给北京使用", response = HttpMessage.class, tags={ "LoginAndRegister", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "文章没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/checkLogin/getUserMessage",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<UserInfo> getUserMessageByToken(@ApiParam(value = "", required = true) @RequestParam(value = "token")  String token);

    @ApiOperation(value = "验证用户密码（北京）", notes = "提供给北京使用", response = HttpMessage.class, tags={ "LoginAndRegister", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "文章没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/checkLogin/checkUserPassword",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<HttpMessage> checkUserPassword(@ApiParam(value = "", required = true) @RequestParam(value = "uid")  Integer uid,
                                               @ApiParam(value = "", required = true) @RequestParam(value = "password")  String password);

}
