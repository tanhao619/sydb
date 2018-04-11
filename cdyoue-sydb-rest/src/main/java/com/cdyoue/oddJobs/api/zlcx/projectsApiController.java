package com.cdyoue.oddJobs.api.zlcx;


import com.cdyoue.oddJobs.dto.common.AttachmentInfoSumary;
import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.common.message.ProjectMessage;
import com.cdyoue.oddJobs.dto.zlcx.*;
import com.cdyoue.oddJobs.entity.lgsq.PortalAttachmentEntity;
import com.cdyoue.oddJobs.entity.syData.ProjectEntity;
import com.cdyoue.oddJobs.entity.syData.SyDeclarationProjectMessageEntity;
import com.cdyoue.oddJobs.service.PortalAttachmentService;
import com.cdyoue.oddJobs.service.sydb.DeclarationProjectMessageService;
import com.cdyoue.oddJobs.service.sydb.ProjectService;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.common.SortUtils;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-18T06:53:23.085Z")

@Controller
public class projectsApiController implements projectsApi {

    @Autowired
    ProjectService projectService;
    @Autowired
    private PortalAttachmentService portalAttachmentService;
    @Autowired
    DeclarationProjectMessageService declarationProjectMessageService;

    @Override
    public ResponseEntity<PageInfo> getProjectsList(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                    @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                    @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                                    @ApiParam(value = "排序字段和方式 例如：/sales?sort=createTime，默认按照发布时间排序。createTime = 发布时间最新", defaultValue = "-createTime") @RequestParam(value = "sort", required = false) String sort) {
        Sort orders = SortUtils.assembleSort(sort);
        Pageable requestPage = new PageRequest(pageNumber, pageSize, orders);
        PageInfo<DeclarationProjectMessageDTO> pageInfo = projectService.getProjectsList(q,requestPage);
        return new ResponseEntity<PageInfo>(pageInfo,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> deleteProject(@ApiParam(value = "", required = true) @PathVariable("id") Integer id) {
        projectService.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> deleteAllProjects(@ApiParam(value = "", required = true) @RequestParam("ids") Integer[] ids) {
        projectService.deleteAllProjects(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DeclarationProjectMessageDTO> getProjectDetail(@ApiParam(value = "申报项目id", required = true) @PathVariable("id") Integer id) {
        DeclarationProjectMessageDTO intellectualDetail = projectService.getProjectDetail(id);
        return new ResponseEntity<DeclarationProjectMessageDTO>(intellectualDetail, HttpStatus.OK);
    }

    public ResponseEntity<HttpMessage> declarationProject(@ApiParam(value = "申报人信息" ,required=true ) @RequestBody DeclarationPeopleSummary declarationPeopleSummary) {
        //只有企业
        //申报之前先判断是否已经申报
//        Integer userId=SecurityUtils.getCurrentUserLogin().getId();
//        SyDeclarationProjectMessageEntity syDeclarationProjectMessageEntity= projectService.findProjectMessage(userId);
//        if(syDeclarationProjectMessageEntity != null){
//            return new ResponseEntity(ProjectMessage.Already_Declaration,HttpStatus.BAD_REQUEST);
//        }
        Integer id = projectService.savePeople(declarationPeopleSummary);
        return id == null ? new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST) : new ResponseEntity<HttpMessage>(HttpStatus.CREATED);
    }
//保存上传的附件
    @Override
    public ResponseEntity<PortalAttachmentEntity> saveProjectAttachment(@ApiParam(value = "申报附件信息", required = true) @RequestBody AttachmentInfoSumary attachmentInfoSumary) {
        PortalAttachmentEntity portalAttachmentEntity1=portalAttachmentService.saveAttachment(attachmentInfoSumary);
        return new ResponseEntity<PortalAttachmentEntity>(portalAttachmentEntity1,HttpStatus.OK);
    }

    public ResponseEntity<ProjectDetail> getProjectById(@ApiParam(value = "项目申报id",required=true ) @PathVariable("id") Integer id) {

        if(projectService.getProjectCheck(id)){
            ProjectDetail dto = new ProjectDetail();
            dto = projectService.getProjectById(id);
            //浏览量加一
            projectService.updateProjectViewCount(id);
            return new ResponseEntity<ProjectDetail>(dto,HttpStatus.OK);
        }else {
            return new ResponseEntity(ProjectMessage.NO_Project,HttpStatus.NOT_FOUND);
        }

    }


    public ResponseEntity<PageInfo> getProjects( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
                                                 @ApiParam(value = "发文时间：latestWeek:最近一周，latestMonth:最近一个月", allowableValues = "latestWeek,latestMonth") @RequestParam(value = "time",required = false) String time,
                                                 @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q) {
        //Hardcode sort
        Sort order = SortUtils.assembleSort("-publishTime");
        PageRequest pr = new PageRequest(pageNumber,pageSize,order);
       PageInfo<ProjectSummary> pageInfo = projectService.findList(pr,q,time);
        return new ResponseEntity<com.cdyoue.spring.page.PageInfo>(pageInfo,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> deleteProject(@ApiParam(value = "项目申报ids",required=true ) @RequestParam Integer[] ids) {
            projectService.deleteByIds(ids);
            return new ResponseEntity<>(HttpStatus.OK);
    }
//发布项目申报
    @Override
    public ResponseEntity<HttpMessage> createProject(@ApiParam(value = "发布项目申报实体" ,required=true ) @RequestBody ProjectCreate projectCreate) {
        Integer projectId=projectService.createProject(projectCreate);
        return projectId!=null?new ResponseEntity<>(HttpStatus.CREATED):new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<HttpMessage> updateProject(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
                                                     @ApiParam(value = "项目对象信息" ,required=true ) @RequestBody ProjectCreate projectCreate) {
       if(projectService.getProjectCheck(id)){
           projectService.updateProject(id,projectCreate);
           return new ResponseEntity<HttpMessage>(HttpStatus.CREATED);
       }else{
           return new ResponseEntity<HttpMessage>(ProjectMessage.NO_Project, HttpStatus.OK);
       }

    }

    @Override
    public ResponseEntity<PageInfo> getMyProjects(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                     @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber) {
        //先查询出申报项目id和申报时间
        Integer userId = SecurityUtils.getCurrentUserLogin().getId();
        Sort order = SortUtils.assembleSort("-createTime");
        PageRequest pr = new PageRequest(pageNumber, pageSize, order);
        PageInfo<DeclarationProjectMessageBase> declarationProjectMessagePage = declarationProjectMessageService.findDeclarationProjectIdAndTime(pr, userId);
        if (declarationProjectMessagePage.getList().size() > 0) {
            return new ResponseEntity<com.cdyoue.spring.page.PageInfo>(declarationProjectMessagePage,HttpStatus.OK);
        } else {
            return new ResponseEntity(ProjectMessage.NO_Project, HttpStatus.NOT_FOUND);
        }

    }

}
