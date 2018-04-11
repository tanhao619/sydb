package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.scfw.IncubatorDetail;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.PageRequest;

/**
 * Created by luanyu on 2017/5/18.
 */
public interface IncubatorsService {
    //通过ID找寻一个孵化器
    IncubatorDetail getIncubatorsById(Integer id);
    //验证ID是否存在
    boolean getIncubatorsCheck(Integer id);
    //创建一个活动
    String createIncubators(IncubatorDetail Incubators);
    //更新一个活动
    void updateIncubators(Integer id,IncubatorDetail Incubators);
    //查询所有活动
    PageInfo<IncubatorDetail> findList(PageRequest pr, String q);
    //删除活动
    void deleteIncubators(Integer id);
    //查询创建人
    Integer getCreatorIdBy(Integer id);
}
