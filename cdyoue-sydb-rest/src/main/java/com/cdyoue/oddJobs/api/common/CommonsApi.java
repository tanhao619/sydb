package com.cdyoue.oddJobs.api.common;


import com.cdyoue.oddJobs.config.QQInfo;
import com.cdyoue.oddJobs.dto.AreaDTO;
import com.cdyoue.oddJobs.dto.RequestMessageInfo;
import com.cdyoue.oddJobs.dto.common.AttachmentInfoSumary;
import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.lgfc.OddJobNews;
import com.cdyoue.oddJobs.dto.message.PersonalCenterMessageDto;
import com.cdyoue.oddJobs.dto.message.RespseMessageOffDto;
import com.cdyoue.oddJobs.dto.scfw.InvestAndFinNews;
import com.cdyoue.oddJobs.dto.xqdt.ProjectNews;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-03-01T08:13:03.220Z")

@Api(value = "commons", description = "the commons API")
public interface CommonsApi {


    @ApiOperation(value = "公共附件上传", notes = "公共附件上传", response = HttpMessage.class, tags = {"Commons",})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "successful operation", response = AttachmentInfoSumary.class),
            @ApiResponse(code = 400, message = "Returned if the request is invalid.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "Returned if the user is not authenticated.", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "Returned if the caller user does not have permission", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "demands not found", response = String.class),
            @ApiResponse(code = 500, message = "Returned if the user was not created because of other error.", response = HttpMessage.class)})
    @RequestMapping(value = "/common/upload",
            produces = { "application/json"}, method = RequestMethod.POST)
    ResponseEntity<AttachmentInfoSumary> commonUpload(@RequestParam("file") MultipartFile file);

    @ApiOperation(value = "获取地区列表", notes = "获取地区列表", response = AreaDTO.class, tags = {"Commons",})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "successful operation", response = AreaDTO.class),
            @ApiResponse(code = 400, message = "Returned if the request is invalid.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "Returned if the user is not authenticated.", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "Returned if the caller user does not have permission", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "demands not found", response = String.class),
            @ApiResponse(code = 500, message = "Returned if the user was not created because of other error.", response = HttpMessage.class)})
    @RequestMapping(value = "/common/erea",
            produces = { "application/json"}, method = RequestMethod.GET)
    ResponseEntity<List<AreaDTO>> getArea();

    @ApiOperation(value = "需求大厅 - 项目动态 - 最新两条 (-6完成)", notes = "需求大厅 - 项目动态", response = ProjectNews.class, tags = {"Commons",})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "successful operation", response = ProjectNews.class),
            @ApiResponse(code = 400, message = "Returned if the request is invalid.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "Returned if the user is not authenticated.", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "Returned if the caller user does not have permission", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "demands not found", response = String.class),
            @ApiResponse(code = 500, message = "Returned if the user was not created because of other error.", response = HttpMessage.class)})
    @RequestMapping(value = "/common/projectNews",
            produces = { "application/json"}, method = RequestMethod.GET)
    ResponseEntity<List<ProjectNews>> getProjectNews();

    @ApiOperation(value = "需求社区 - 零工动态 - 最新四条 (-12完成)", notes = "需求社区 - 零工动态", response = OddJobNews.class, tags = {"Commons",})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "successful operation", response = OddJobNews.class),
            @ApiResponse(code = 400, message = "Returned if the request is invalid.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "Returned if the user is not authenticated.", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "Returned if the caller user does not have permission", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "demands not found", response = String.class),
            @ApiResponse(code = 500, message = "Returned if the user was not created because of other error.", response = HttpMessage.class)})
    @RequestMapping(value = "/common/oddJobNews",
            produces = { "application/json"}, method = RequestMethod.GET)
    ResponseEntity<List<OddJobNews>> getOddJobNews();

    @ApiOperation(value = "双创服务 - 投融资动态 - 最新四条 (-12完成)", notes = "双创服务 - 投融资动态", response = InvestAndFinNews.class, tags = {"Commons",})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "successful operation", response = InvestAndFinNews.class),
            @ApiResponse(code = 400, message = "Returned if the request is invalid.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "Returned if the user is not authenticated.", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "Returned if the caller user does not have permission", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "demands not found", response = String.class),
            @ApiResponse(code = 500, message = "Returned if the user was not created because of other error.", response = HttpMessage.class)})
    @RequestMapping(value = "/common/investAndFinNews",
            produces = { "application/json"}, method = RequestMethod.GET)
    ResponseEntity<List<InvestAndFinNews>> getInvestAndFinNews();


    @ApiOperation(value = "获取系统消息列表", notes = "获取系统消息列表", response = PersonalCenterMessageDto.class, tags = {"Commons",})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "successful operation", response = PersonalCenterMessageDto.class),
            @ApiResponse(code = 400, message = "Returned if the request is invalid.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "Returned if the user is not authenticated.", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "Returned if the caller user does not have permission", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "demands not found", response = String.class),
            @ApiResponse(code = 500, message = "Returned if the user was not created because of other error.", response = HttpMessage.class)})
    @RequestMapping(value = "/common/sys/messages",
            produces = { "application/json"}, method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<PageInfo> getSysMessages(@RequestParam Integer pageNum,@RequestParam Integer pageSize);

    @ApiOperation(value = "PC设置系统消息已读", notes = "PC设置系统消息已读", response = PersonalCenterMessageDto.class, tags = {"Commons",})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "successful operation", response = PersonalCenterMessageDto.class),
            @ApiResponse(code = 400, message = "Returned if the request is invalid.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "Returned if the user is not authenticated.", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "Returned if the caller user does not have permission", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "demands not found", response = String.class),
            @ApiResponse(code = 500, message = "Returned if the user was not created because of other error.", response = HttpMessage.class)})
    @RequestMapping(value = "/common/look/messages/{msgId}",
            produces = { "application/json"}, method = RequestMethod.PUT)
    @ResponseBody
    ResponseEntity LookSysMessage(@PathVariable("msgId") Integer msgId,@RequestBody PersonalCenterMessageDto personalCenterMessageDto);

    @ApiOperation(value = "获取qq", notes = "获取qq", response = PersonalCenterMessageDto.class, tags = {"Commons",})
    @RequestMapping(value = "/common/customerService/qq",
            produces = { "application/json"}, method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<QQInfo> getQQData();

    @ApiOperation(value = "获取文件上传的基础路径", notes = "获取文件上传的基础路径", response = PersonalCenterMessageDto.class, tags = {"Commons",})
    @RequestMapping(value = "/common/customerService/fileBasePath",
            produces = { "application/json"}, method = RequestMethod.GET)
    @ResponseBody
    Map<String, String> getFileBasePath();

    @ApiOperation(value = "获取系统未读消息total", notes = "获取系统未读消息total", response = Integer.class, tags = {"Commons",})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "successful operation", response = Integer.class),
            @ApiResponse(code = 400, message = "Returned if the request is invalid.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "Returned if the user is not authenticated.", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "Returned if the caller user does not have permission", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "demands not found", response = String.class),
            @ApiResponse(code = 500, message = "Returned if the user was not created because of other error.", response = HttpMessage.class)})
    @RequestMapping(value = "/common/unlook/messages/total",
            produces = { "application/json"}, method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<Integer> UnLookSysMessage();

    @ApiOperation(value = "发布官方消息()", notes = "", tags = {"Commons",})
    @RequestMapping(value = "/common/sys/message",
            produces = { "application/json"}, method = RequestMethod.POST)
    @ResponseBody
    ResponseEntity<HttpMessage> putOfficialSysMessage(@RequestBody RequestMessageInfo requestMessage);

    @ApiOperation(value = "获取官方消息列表", notes = "获取官方消息列表", response = PersonalCenterMessageDto.class, tags = {"Commons",})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "successful operation", response = PersonalCenterMessageDto.class),
            @ApiResponse(code = 400, message = "Returned if the request is invalid.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "Returned if the user is not authenticated.", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "Returned if the caller user does not have permission", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "demands not found", response = String.class),
            @ApiResponse(code = 500, message = "Returned if the user was not created because of other error.", response = HttpMessage.class)})
    @RequestMapping(value = "/common/officials/messages",
            produces = { "application/json"}, method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<PageInfo> getOfficials(@RequestParam Integer pageNum,@RequestParam Integer pageSize,@RequestParam String q);

    @ApiOperation(value = "删除官方消息", notes = "删除官方消息", response = HttpMessage.class, tags = {"Commons",})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "successful operation", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "Returned if the request is invalid.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "Returned if the user is not authenticated.", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "Returned if the caller user does not have permission", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "demands not found", response = String.class),
            @ApiResponse(code = 500, message = "Returned if the user was not created because of other error.", response = HttpMessage.class)})
    @RequestMapping(value = "/common/officials/messages/{id}",
            produces = { "application/json"}, method = RequestMethod.DELETE)
    @ResponseBody
    ResponseEntity<HttpMessage> delOfficials(@PathVariable(value = "id")Integer id);

    @ApiOperation(value = "获取官方消息详情", notes = "获取官方消息详情", response = HttpMessage.class, tags = {"Commons",})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "successful operation", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "Returned if the request is invalid.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "Returned if the user is not authenticated.", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "Returned if the caller user does not have permission", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "demands not found", response = String.class),
            @ApiResponse(code = 500, message = "Returned if the user was not created because of other error.", response = HttpMessage.class)})
    @RequestMapping(value = "/common/officials/messages/{id}",
            produces = { "application/json"}, method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<RespseMessageOffDto> getMessageDetail(@PathVariable(value = "id")Integer id);
//    @ApiOperation(value = "获取系统消息详情", notes = "获取系统消息详情", response = PersonalCenterMessageDto.class, tags = {"Commons",})
//    @ApiResponses(value = {
//            @ApiResponse(code = 201, message = "successful operation", response = PersonalCenterMessageDto.class),
//            @ApiResponse(code = 400, message = "Returned if the request is invalid.", response = HttpMessage.class),
//            @ApiResponse(code = 401, message = "Returned if the user is not authenticated.", response = HttpMessage.class),
//            @ApiResponse(code = 403, message = "Returned if the caller user does not have permission", response = HttpMessage.class),
//            @ApiResponse(code = 404, message = "demands not found", response = String.class),
//            @ApiResponse(code = 500, message = "Returned if the user was not created because of other error.", response = HttpMessage.class)})
//    @RequestMapping(value = "/common/sys/messages/{eventId}",
//            produces = { "application/json"}, method = RequestMethod.GET)
//    @ResponseBody
//    ResponseEntity<PageInfo> getSysMessageDetail(@PathVariable("eventId") Integer eventId);
}
