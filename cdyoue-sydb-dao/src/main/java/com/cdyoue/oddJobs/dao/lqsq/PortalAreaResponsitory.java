package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.PortalAreaEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2017/5/11.
 */

public interface PortalAreaResponsitory extends JpaCustomResponsitory<PortalAreaEntity, Integer>{
    @Query("select p.level FROM PortalAreaEntity p WHERE p.pid is null")
    List<Integer> findFristIdLevel();

    @Query("select p.name FROM PortalAreaEntity p WHERE p.pid =?1")
    List<String> findSecondName(Integer i);

    @Query("select p.name FROM PortalAreaEntity p WHERE p.level =?1")
    String findName(Integer i);

    @Query("select p.name FROM PortalAreaEntity p WHERE p.id =?1")
    String findNameById(Integer i);

    @Query("select p.id FROM PortalAreaEntity p WHERE p.name =?1")
    Integer findIdByName(String spaceName);

    @Query("select p.level FROM PortalAreaEntity p WHERE p.name =?1")
    Integer findLevel(String areaFirstLevel);

}
