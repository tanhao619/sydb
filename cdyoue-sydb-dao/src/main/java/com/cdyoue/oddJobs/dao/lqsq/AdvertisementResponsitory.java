package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.PartalTopEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2017/5/10.
 */
public interface AdvertisementResponsitory extends JpaCustomResponsitory<PartalTopEntity,Integer>{
    @Query("select p FROM PartalTopEntity p WHERE p.orderBy=?1 AND p.pageNum=?2")
    PartalTopEntity findByIdAndView(Integer id, int pageNumber);

    @Query("select p.id FROM PartalTopEntity p WHERE p.pageNum=?1 AND p.orderBy=?2")
    int findId(int pageNumber, Integer id);

    @Query("select p FROM PartalTopEntity p WHERE p.pageNum=?1")
    List<PartalTopEntity> findPageAll(int pageNumber);
}
