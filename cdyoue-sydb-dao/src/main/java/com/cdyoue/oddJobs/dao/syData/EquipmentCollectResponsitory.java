package com.cdyoue.oddJobs.dao.syData;

import com.cdyoue.oddJobs.dao.lqsq.JpaCustomResponsitory;
import com.cdyoue.oddJobs.entity.syData.EquipmentApplyEntity;
import com.cdyoue.oddJobs.entity.syData.EquipmentCollectEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2017/9/19.
 */
public interface EquipmentCollectResponsitory extends JpaCustomResponsitory<EquipmentCollectEntity, Integer> {
    @Query(value = "select e FROM sy_equipment_collect e where e.eid =?1 and e.uId =?2",nativeQuery = true)
    EquipmentCollectEntity findOneByEidAndUid(Integer id, Integer uId);

    @Query(value = "select e FROM sy_equipment_collect e where e.eid =?1",nativeQuery = true)
    List<EquipmentCollectEntity> findListByEid(Integer id);
}
