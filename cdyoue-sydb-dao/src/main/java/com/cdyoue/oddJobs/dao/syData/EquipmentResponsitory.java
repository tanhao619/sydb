package com.cdyoue.oddJobs.dao.syData;

import com.cdyoue.oddJobs.dao.lqsq.JpaCustomResponsitory;

import com.cdyoue.oddJobs.entity.syData.EquipmentEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2017/9/18.
 */

public interface EquipmentResponsitory extends JpaCustomResponsitory<EquipmentEntity, Integer> {

    @Query(value = "select e FROM EquipmentEntity e where e.top =1 order by e.updateTime desc")
    List<EquipmentEntity> findTop();

    @Query(value = "select e FROM EquipmentEntity e where id =?1")
    EquipmentEntity getEquipmentById(Integer id);
}
