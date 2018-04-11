package com.cdyoue.oddJobs.api.zlcx;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.common.message.ProjectInterpretationMessage;
import com.cdyoue.oddJobs.dto.zlcx.ProjectInterpretationBase;
import com.cdyoue.oddJobs.dto.zlcx.ProjectInterpretationDetail;
import com.cdyoue.oddJobs.dto.zlcx.ProjectInterpretationSummary;
import com.cdyoue.oddJobs.service.sydb.ProjectInterpretationService;
import com.cdyoue.spring.common.SortUtils;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by sky on 2017/9/19.
 */
@Controller
public class ProjectInterpretationController implements ProjectInterpretationApi{
    @Autowired
    ProjectInterpretationService projectInterpretationService;


    @Override
    public ResponseEntity<ProjectInterpretationDetail> getProjectInterpretationById(@ApiParam(value = "项目解读id", required = true) @PathVariable("id") Integer id) {
        if(projectInterpretationService.getProjectInterpretationCheck(id)){
            ProjectInterpretationDetail dto = projectInterpretationService.getProjectInterpretationById(id);
            projectInterpretationService.updateViewCount(id);
            return new ResponseEntity<ProjectInterpretationDetail>(dto, HttpStatus.OK);
        }else {
            return new ResponseEntity(ProjectInterpretationMessage.NO_ProjectInterpretation,HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<PageInfo> getProjectInterpretation(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                             @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                             @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q) {
        Sort order = SortUtils.assembleSort("-publishTime");
        PageRequest pr = new PageRequest(pageNumber,pageSize,order);
        PageInfo<ProjectInterpretationSummary> pageInfo = projectInterpretationService.findList(pr,q);
        return new ResponseEntity<com.cdyoue.spring.page.PageInfo>(pageInfo,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> deleteProjectInterpretation(@ApiParam(value = "项目解读ids",required=true ) @RequestParam Integer[] ids) {
        projectInterpretationService.deleteByIds(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> createProjectInterpretation(@ApiParam(value = "发布项目解读实体" ,required=true ) @RequestBody ProjectInterpretationBase projectInterpretationBase) {
        Integer projectInterpretationId=projectInterpretationService.createProjectInterpretation(projectInterpretationBase);
        return projectInterpretationId!=null?new ResponseEntity<>(HttpStatus.CREATED):new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<HttpMessage> updateProjectInterpretation(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
                                                                   @ApiParam(value = "项目解读对象信息" ,required=true ) @RequestBody ProjectInterpretationBase projectInterpretationBase) {
        if(projectInterpretationService.getProjectInterpretationCheck(id)){
            projectInterpretationService.updateProjectInterpretation(id,projectInterpretationBase);
            return new ResponseEntity<HttpMessage>(HttpStatus.CREATED);
        }else{
            return new ResponseEntity<HttpMessage>(ProjectInterpretationMessage.NO_ProjectInterpretation, HttpStatus.OK);
        }
    }
}
