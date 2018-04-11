package com.cdyoue.oddJobs.mapper.sydb;

import com.cdyoue.oddJobs.dao.lqsq.UserResponsitory;
import com.cdyoue.oddJobs.dto.zlcx.FinancingDTO.FinancingTop;
import com.cdyoue.oddJobs.entity.syData.FinancingEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/9/20.
 */
@Component
public class FinancingMapper {
    @Autowired
    UserResponsitory userResponsitory;



    public FinancingTop entityToTop(FinancingEntity financingEntity) {
        FinancingTop financingTop = new FinancingTop();
        BeanUtils.copyProperties(financingEntity,financingTop);
        financingTop.setCreateBy(userResponsitory.findOne(financingEntity.getCreateBy()).getUserName());
        return financingTop;
    }
}
