package com.cdyoue.oddJobs.api.zlcy;



import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.common.message.TrainingMessage;
import com.cdyoue.oddJobs.dto.zlcy.TrainingCreate;
import com.cdyoue.oddJobs.dto.zlcy.TrainingDetail;
import com.cdyoue.oddJobs.dto.zlcy.TrainingSummary;
import com.cdyoue.oddJobs.service.sydb.TrainingService;
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

import java.util.List;


@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-18T06:53:23.085Z")

@Controller
public class TrainingApiController implements TrainingApi {
@Autowired
    TrainingService trainingService;

    @Override
    public ResponseEntity<TrainingDetail> getTrainingById(@ApiParam(value = "培训机构id", required = true) @PathVariable("id") Integer id) {
         if(trainingService.getTrainingCheck(id)){
             TrainingDetail dto = trainingService.getTrainingById(id);
             trainingService.updateTrainingViewCount(id);
             return new ResponseEntity<TrainingDetail>(dto,HttpStatus.OK);
         }else {
             return new ResponseEntity(TrainingMessage.NO_Training, HttpStatus.NOT_FOUND);
         }
    }

    @Override
    public ResponseEntity<PageInfo> getTrainings(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                 @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                 @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q) {
        Sort order = SortUtils.assembleSort("-top|-updateTime");
        PageRequest pr = new PageRequest(pageNumber,pageSize,order);
        PageInfo<TrainingSummary> pageInfo = trainingService.findList(pr,q);
        return new ResponseEntity<com.cdyoue.spring.page.PageInfo>(pageInfo, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TrainingSummary>> getTopTrainings() {
        List<TrainingSummary> topList=trainingService.findTopList();
        return new ResponseEntity<List<TrainingSummary>>(topList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> topTraining(@ApiParam(value = "培训机构id", required = true) @PathVariable("id") Integer id,
                                                   @ApiParam(value = "置顶图片") @RequestParam(value = "topImage",required = false) String topImage) {
        if(trainingService.getTrainingCheck(id)) {
            trainingService.topTraining(id,topImage);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity(TrainingMessage.NO_Training,HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<HttpMessage> deleteTraining(@ApiParam(value = "培训机构ids",required=true ) @RequestParam Integer[] ids) {
        trainingService.deleteByIds(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> createTraining(@ApiParam(value = "发布培训机构实体" ,required=true ) @RequestBody TrainingCreate trainingCreate) {
        Integer trainingId=trainingService.createTraining(trainingCreate);
        return trainingId!=null?new ResponseEntity<>(HttpStatus.CREATED):new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<HttpMessage> updateTraining(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
                                                      @ApiParam(value = "培训机构对象信息" ,required=true ) @RequestBody TrainingCreate trainingCreate) {
        if(trainingService.getTrainingCheck(id)){
            trainingService.updateTraining(id,trainingCreate);
            return new ResponseEntity<HttpMessage>(HttpStatus.CREATED);
        }else{
            return new ResponseEntity<HttpMessage>(TrainingMessage.NO_Training, HttpStatus.OK);
        }
    }
}
