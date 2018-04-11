package com.cdyoue.oddJobs.dao.syData;

import com.cdyoue.oddJobs.dao.lqsq.JpaCustomResponsitory;
import com.cdyoue.oddJobs.entity.syData.EquipmentApplyEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2017/9/19.
 */
public interface EquipmentApplyResponsitory extends JpaCustomResponsitory<EquipmentApplyEntity, Integer> {
    @Query(value = "select count(*) FROM EquipmentApplyEntity e where e.eId =?1")
    Integer getApplyTimeById(Integer id);

    @Query(value = "select * FROM sy_equipment_apply e where e.eid =?1",nativeQuery = true)
    List<EquipmentApplyEntity> getEquipmentApplyByEid(Integer id);

    @Modifying
    @Query("DELETE FROM EquipmentApplyEntity  where eid =?1")
    void deleteByEid(Integer integer);
}
