package com.cdyoue.oddJobs.api.zlcx;
import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.common.message.ProjectLectureMessage;
import com.cdyoue.oddJobs.dto.zlcx.ProjectInterpretationBase;
import com.cdyoue.oddJobs.dto.zlcx.ProjectLectureCreate;
import com.cdyoue.oddJobs.dto.zlcx.ProjectLectureDetail;
import com.cdyoue.oddJobs.dto.zlcx.ProjectLectureSummary;
import com.cdyoue.oddJobs.service.sydb.ProjectLectureService;
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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-18T06:53:23.085Z")

@Controller
public class ProjectlectureApiController implements ProjectLectureApi{

        @Autowired
        ProjectLectureService projectLectureService;

        public ResponseEntity<ProjectLectureDetail> getProjectLectureById(@ApiParam(value = "项目讲座id",required=true ) @PathVariable("id") Integer id) {

            if(projectLectureService.getProjectLectureCheck(id)){
                ProjectLectureDetail dto = new ProjectLectureDetail();
                dto = projectLectureService.getProjectLectureById(id);
                projectLectureService.updateProjectLectureViewCount(id);
                return new ResponseEntity<ProjectLectureDetail>(dto,HttpStatus.OK);
            }else {
                return new ResponseEntity(ProjectLectureMessage.NO_ProjectLecture,HttpStatus.NOT_FOUND);
            }

        }


        public ResponseEntity<PageInfo> getProjectLecture( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                                     @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
                                                     @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q) {
            //Hardcode sort
            Sort order = SortUtils.assembleSort("-top|-publishTime");
            PageRequest pr = new PageRequest(pageNumber,pageSize,order);
            PageInfo<ProjectLectureSummary> pageInfo = projectLectureService.findList(pr,q);
            return new ResponseEntity<com.cdyoue.spring.page.PageInfo>(pageInfo,HttpStatus.OK);
        }
//获取置顶项目讲座
    @Override
    public ResponseEntity<ProjectLectureSummary> getTopProjectLecture() {
        ProjectLectureSummary projectLectureSummary=projectLectureService.getTopLecture();
        if(projectLectureSummary!=null){
            return new ResponseEntity<ProjectLectureSummary>(projectLectureSummary,HttpStatus.OK);
        }else{
            return new ResponseEntity(ProjectLectureMessage.NO_TopProjectLecture,HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ResponseEntity<HttpMessage> topProjectLecture(@ApiParam(value = "项目讲座id", required = true) @PathVariable("id") Integer id,
                                                         @ApiParam(value = "置顶图片") @RequestParam(value="topImage", required = false) String topImage) {
        if(projectLectureService.getProjectLectureCheck(id)) {
            projectLectureService.topLecture(id,topImage);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity(ProjectLectureMessage.NO_ProjectLecture,HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<HttpMessage> deleteProjectLecture(@ApiParam(value = "项目讲座ids",required=true ) @RequestParam Integer[] ids) {
        projectLectureService.deleteByIds(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> createProjectLecture(@ApiParam(value = "发布项目讲座实体" ,required=true ) @RequestBody ProjectLectureCreate projectLectureCreate) {
        Integer projectLectureId=projectLectureService.createProjectLecture(projectLectureCreate);
        return projectLectureId!=null?new ResponseEntity<>(HttpStatus.CREATED):new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<HttpMessage> updateProjectLecture(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
                                                            @ApiParam(value = "项目讲座对象信息" ,required=true ) @RequestBody ProjectLectureCreate projectLectureCreate) {
        if(projectLectureService.getProjectLectureCheck(id)){
            projectLectureService.updateProjectLecture(id,projectLectureCreate);
            return new ResponseEntity<HttpMessage>(HttpStatus.CREATED);
        }else{
            return new ResponseEntity<HttpMessage>(ProjectLectureMessage.NO_ProjectLecture, HttpStatus.OK);
        }
    }

}


