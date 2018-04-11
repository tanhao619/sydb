package com.cdyoue.oddJobs.api.scfw;


import com.cdyoue.oddJobs.dto.Reason;
import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.common.message.CommonMessage;
import com.cdyoue.oddJobs.dto.scfw.FinProjectDetail;
import com.cdyoue.oddJobs.dto.scfw.FinProjectSummary;
import com.cdyoue.oddJobs.service.FinprojectService;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.common.SortUtils;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-23T00:52:52.589Z")

@Controller
public class FinprojectsApiController implements FinprojectsApi {

    @Autowired
    private FinprojectService finprojectService;

    public ResponseEntity<HttpMessage> approveFinproject(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,@ApiParam(value = "Reason" ,required=false ) @RequestBody Reason Reason) {
        Integer role =  SecurityUtils.getCurrentUserLogin().getRole();
        if(role!=2){
            return new ResponseEntity<HttpMessage>(HttpStatus.FORBIDDEN);
        }
        finprojectService.approveFinProject(id,Reason);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    public ResponseEntity<HttpMessage> createFinProject(@ApiParam(value = "项目实体信息" ,required=true ) @RequestBody FinProjectDetail finproject) {
        UserMine userMine = SecurityUtils.getCurrentUserLogin();
        if (userMine == null) return new ResponseEntity<HttpMessage>(HttpStatus.UNAUTHORIZED);
        //是企业用户且登录，可以发布项目
        Integer userId = userMine.getId();
        //没有上传文件，不能创建项目
        //if (StringUtils.isEmpty(finproject.getAttachUrl())) return new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST);
        Integer id = finprojectService.createFinProject(userId, finproject);
        return id == null ? new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST) : new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    public ResponseEntity<HttpMessage> deleteFinProject(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id) {
        // do some magic!
        UserMine userMine=SecurityUtils.getCurrentUserLogin();
        Integer userId = userMine.getId();
        Integer userRole=userMine.getRole();
            if(userRole==2){
                //超级管理员可以删除任意条目
                finprojectService.deleteFinProject(id);
                return new ResponseEntity<HttpMessage>(HttpStatus.OK);
            }
            if(finprojectService.deleteOrUpdateAuthority(id,userId)) {
                //个人或企业用户只能对自己发布的删除
                finprojectService.deleteFinProject(id);
                return new ResponseEntity<HttpMessage>(HttpStatus.OK);
            }
        return new ResponseEntity<HttpMessage>(HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<FinProjectDetail> downloadAttachById(@ApiParam(value = "项目id",required=true ) @PathVariable("id") Integer id){

        FinProjectDetail finProjectDetail = finprojectService.downloadFinProjectById(id);
        return new ResponseEntity<FinProjectDetail>(finProjectDetail, HttpStatus.OK);
    }

    public ResponseEntity<FinProjectDetail> getFinProjectById(@ApiParam(value = "项目id",required=true ) @PathVariable("id") Integer id) {
        FinProjectDetail finProjectDetail = finprojectService.getFinProjectById(id);
        return new ResponseEntity<FinProjectDetail>(finProjectDetail, HttpStatus.OK);
    }

    public ResponseEntity getFinProjects(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                                   @ApiParam(value = "页码。默认第0页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
                                                   @ApiParam(value = "审核状态，待审核:0，审核通过:1，审核不通过:2", required = false, allowableValues = "0, 1, 2") @RequestParam(value = "statusFilter", required = false) Byte statusFilter,
                                                   @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q) {
        // do some magic!
        UserMine userMine=SecurityUtils.getCurrentUserLogin();
        Pageable requestPage=null;
        if(userMine!=null&&userMine.getRole()==2){//管理员
            Sort orders = SortUtils.assembleSort("-createTime");
            requestPage = new PageRequest(pageNumber, pageSize,orders);
        }else {
            Sort orders = SortUtils.assembleSort("-publishTime");
            requestPage = new PageRequest(pageNumber, pageSize,orders);
        }
        PageInfo<FinProjectSummary> pageInfo = finprojectService.findByKeyWord(requestPage, q, statusFilter);
        if (pageInfo.getList() == null || pageInfo.getList().size() < 1) return new ResponseEntity<HttpMessage>(CommonMessage.GETFINPROJECTNOTFOUND, HttpStatus.NOT_FOUND);
        return new ResponseEntity<PageInfo>(pageInfo, HttpStatus.OK);
    }

    public ResponseEntity getMyFinProjects( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第0页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
         @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q) {
        //用户未登录状态返回401
        if (SecurityUtils.getCurrentUserLogin() == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        Integer userId = SecurityUtils.getCurrentUserLogin().getId();
        Pageable requestPage = new PageRequest(pageNumber, pageSize, Sort.Direction.DESC,"createTime");
        PageInfo<FinProjectSummary> pageInfo = finprojectService.getMyFinProjects(userId, q, requestPage);
        if (pageInfo.getList() == null || pageInfo.getList().size() < 1) return new ResponseEntity<HttpMessage>(CommonMessage.GETFINPROJECTNOTFOUND, HttpStatus.NOT_FOUND);
        return new ResponseEntity<PageInfo>(pageInfo, HttpStatus.OK);
    }

    public ResponseEntity<HttpMessage> rejectFinproject(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,@ApiParam(value = "Reason" ,required=false ) @RequestBody Reason Reason) {
        Integer role =  SecurityUtils.getCurrentUserLogin().getRole();
        if(role!=2){
            return new ResponseEntity<HttpMessage>(HttpStatus.FORBIDDEN);
        }
        finprojectService.rejectFinProject(id,Reason);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    public ResponseEntity<HttpMessage> updateFinProject(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
        @ApiParam(value = "项目对象" ,required=true ) @RequestBody FinProjectDetail finproject) {
        UserMine userMine=SecurityUtils.getCurrentUserLogin();
        Integer userId = userMine.getId();
        Integer userRole=userMine.getRole();
        if(userRole==2){
            //超级管理员可以修改任意条目
            finprojectService.updateFinProject(id, finproject);
            return new ResponseEntity<HttpMessage>(HttpStatus.OK);
        }
        if(finprojectService.deleteOrUpdateAuthority(id,userId)) {
            //个人或企业用户只能对自己发布的删除
            finprojectService.updateFinProject(id, finproject);
            return new ResponseEntity<HttpMessage>(HttpStatus.OK);
        }
        return new ResponseEntity<HttpMessage>(HttpStatus.UNAUTHORIZED);
    }

}
