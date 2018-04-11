package com.cdyoue.oddJobs.mapper.sydb;

import com.cdyoue.oddJobs.dto.zlcy.TrainingCreate;
import com.cdyoue.oddJobs.dto.zlcy.TrainingDetail;
import com.cdyoue.oddJobs.dto.zlcy.TrainingSummary;
import com.cdyoue.oddJobs.entity.syData.SyTrainingEntity;
import com.cdyoue.oddJobs.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by sky on 2017/9/23.
 */
@Component
public class TrainingMapper {
    @Autowired
    UserService userService;
    public TrainingDetail syTrainingEntityToTrainingDetail(SyTrainingEntity syTrainingEntity){
        TrainingDetail trainingDetail=new TrainingDetail();
        trainingDetail.setInfo(syTrainingEntity.getInfo());
        trainingDetail.setLogo(syTrainingEntity.getLogo());
        trainingDetail.setName(syTrainingEntity.getName());
        trainingDetail.setIndustry(syTrainingEntity.getIndustry());
        trainingDetail.setAddress(syTrainingEntity.getAddress());
        trainingDetail.setContent(syTrainingEntity.getContent());
        return trainingDetail;
    }

    public TrainingSummary syTrainingEntityToTrainingSummary(SyTrainingEntity syTrainingEntity){
        TrainingSummary trainingSummary=new TrainingSummary();
        trainingSummary.setInfo(syTrainingEntity.getInfo());
        trainingSummary.setLogo(syTrainingEntity.getLogo());
        trainingSummary.setTopImage(syTrainingEntity.getTopImage());
        trainingSummary.setTop(syTrainingEntity.getTop());
        trainingSummary.setName(syTrainingEntity.getName());
        trainingSummary.setAddress(syTrainingEntity.getAddress());
        trainingSummary.setId(syTrainingEntity.getId());
        trainingSummary.setViewCount(syTrainingEntity.getViewCount());
        trainingSummary.setCreateTime(new SimpleDateFormat("yyyy-MM-dd").format(syTrainingEntity.getCreateTime()));
        Integer userId=syTrainingEntity.getPublishPeople();
        String name=userService.findOne(userId).getUserName();
        trainingSummary.setPublishPeople(name);
        return trainingSummary;
    }

    public SyTrainingEntity trainingCreateToSyTrainingEntity(TrainingCreate trainingCreate){
        SyTrainingEntity syTrainingEntity=new SyTrainingEntity();
        BeanUtils.copyProperties(trainingCreate,syTrainingEntity);
        syTrainingEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        syTrainingEntity.setUpdateTime(syTrainingEntity.getCreateTime());
        syTrainingEntity.setViewCount(0);
        syTrainingEntity.setTop(0);
        syTrainingEntity.setPublishPeople(1);
        return syTrainingEntity;
    }
}
