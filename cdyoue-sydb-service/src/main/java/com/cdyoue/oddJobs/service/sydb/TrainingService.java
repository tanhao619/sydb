package com.cdyoue.oddJobs.service.sydb;

import com.cdyoue.oddJobs.dto.zlcy.TrainingCreate;
import com.cdyoue.oddJobs.dto.zlcy.TrainingDetail;
import com.cdyoue.oddJobs.dto.zlcy.TrainingSummary;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * Created by sky on 2017/9/23.
 */
public interface TrainingService {
    //通过ID找寻培训机构详细信息
    TrainingDetail getTrainingById(Integer id) ;
    //验证ID是否存在
    boolean getTrainingCheck(Integer id);
    //查询所有培训机构
    PageInfo<TrainingSummary> findList(PageRequest pr, String q);

    List<TrainingSummary> findTopList();

    void deleteByIds(Integer[] ids);

    Integer createTraining(TrainingCreate trainingCreate);

    void updateTraining(Integer id, TrainingCreate trainingCreate);

    void updateTrainingViewCount(Integer id);

    void topTraining(Integer id, String topImage);
}
