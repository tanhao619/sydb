package com.cdyoue.oddJobs.service.sydb;

import com.cdyoue.oddJobs.dto.zlcx.FinancingDTO.FinancingTop;
import com.cdyoue.oddJobs.entity.syData.FinancingEntity;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * Created by Administrator on 2017/9/20.
 */
public interface FinancingService {

    FinancingTop getFinancingTop();

    PageInfo<FinancingTop> getFinancingPageList(PageRequest pr,Integer type,String q);

    FinancingEntity getFinancingById(Integer id);

    void uncollectFinancing(Integer id, Integer status);

    void deleteFinancing(List<Integer> id);

    void createFinancing(FinancingEntity financingEntity);

    PageInfo<FinancingTop> getMyFinancingPageList(PageRequest pr, Integer id);

    PageInfo<FinancingTop> getFinancingBackPageList(PageRequest pr, String q, String status);

    void madeTop(FinancingEntity financingEntity);


    void toExamine(FinancingEntity financingEntity);

    void cancelTop(Integer id);
}
