package com.cdyoue.oddJobs.service.sydb.impl;

import com.cdyoue.oddJobs.dao.syData.TrainingResponsitory;
import com.cdyoue.oddJobs.dto.zlcy.TrainingCreate;
import com.cdyoue.oddJobs.dto.zlcy.TrainingDetail;
import com.cdyoue.oddJobs.dto.zlcy.TrainingSummary;
import com.cdyoue.oddJobs.entity.syData.SyTrainingEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.sydb.TrainingMapper;
import com.cdyoue.oddJobs.service.support.ServiceSupport;
import com.cdyoue.oddJobs.service.sydb.TrainingService;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sky on 2017/9/23.
 */
@Service
public class TrainingServiceImpl extends ServiceSupport<SyTrainingEntity> implements TrainingService{
    @Autowired
    TrainingResponsitory trainingResponsitory;
    @Autowired
    TrainingMapper trainingMapper;



    @Override
    public TrainingDetail getTrainingById(Integer id) {
        SyTrainingEntity syTrainingEntity=trainingResponsitory.findOne(id);
        return trainingMapper.syTrainingEntityToTrainingDetail(syTrainingEntity);
    }

    @Override
    public boolean getTrainingCheck(Integer id) {
        if(trainingResponsitory.findOne(id)==null){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public PageInfo<TrainingSummary> findList(PageRequest pr, String q) {
        Page<SyTrainingEntity> rpPage = super.findByOneLike("name",q,pr);
        List<TrainingSummary> trainingSummaryList = rpPage.getContent().stream().map(p -> trainingMapper.syTrainingEntityToTrainingSummary(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(trainingSummaryList, pr, rpPage.getTotalElements()));
    }

    @Override
    public List<TrainingSummary> findTopList() {
        List<SyTrainingEntity> topList=trainingResponsitory.findTop4ByTopOrderByUpdateTimeDesc(1);
        if (topList.size() == 0) {
            throw new NotFoundEntityException("没有数据");
        }
        return topList.stream().map(p -> trainingMapper.syTrainingEntityToTrainingSummary(p)).collect(Collectors.toList());
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        List<SyTrainingEntity> syTrainingEntityList=trainingResponsitory.findAll(Arrays.asList(ids));
        trainingResponsitory.delete(syTrainingEntityList);
    }

    @Override
    public Integer createTraining(TrainingCreate trainingCreate) {
        SyTrainingEntity syTrainingEntity=trainingMapper.trainingCreateToSyTrainingEntity(trainingCreate);
        return  trainingResponsitory.save(syTrainingEntity).getId();
    }

    @Override
    public void updateTraining(Integer id, TrainingCreate trainingCreate) {
        SyTrainingEntity syTrainingEntity=trainingResponsitory.findOne(id);
        BeanUtils.copyProperties(trainingCreate,syTrainingEntity);
        syTrainingEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        syTrainingEntity.setCreateTime(syTrainingEntity.getUpdateTime());
        trainingResponsitory.saveAndFlush(syTrainingEntity);
    }

    @Override
    public void updateTrainingViewCount(Integer id) {
        SyTrainingEntity syTrainingEntity=trainingResponsitory.findOne(id);
        syTrainingEntity.setViewCount(syTrainingEntity.getViewCount()+1);
        trainingResponsitory.saveAndFlush(syTrainingEntity);
    }

    @Override
    public void topTraining(Integer id, String topImage) {
        SyTrainingEntity syTrainingEntity=trainingResponsitory.findOne(id);
        int top=syTrainingEntity.getTop();
        if(top==0){
            //置顶
            if(topImage!=null && topImage!=""){
                syTrainingEntity.setTopImage(topImage);
            }
            syTrainingEntity.setTop(1);
        }else {
            //取消置顶
            syTrainingEntity.setTop(0);
        }
        syTrainingEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        syTrainingEntity.setCreateTime(syTrainingEntity.getUpdateTime());
        trainingResponsitory.saveAndFlush(syTrainingEntity);
    }

    @Override
    public Class getJpaRepositoryClazz() {
        return TrainingResponsitory.class;
    }
}
