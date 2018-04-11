package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.PortalTechnologyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * Created by tr on 2017/5/8.
 */
public interface TalentResponsitory extends JpaCustomResponsitory<PortalTechnologyEntity,Integer> {
    //根据用户id与技能类型查询
    @Query("SELECT pt from PortalTechnologyEntity pt where pt.userId=?1 and pt.type=?2")
    Page<PortalTechnologyEntity> findByUserIdAndType(Integer userId, Integer type, Pageable pageable);

    //查询活跃度
    @Query("SELECT upe.activity from UserpersonalEntity upe where upe.userId=?1")
    Integer getActivityByUserId(Integer userId);

    //统计登录次数
    @Query("SELECT count(pupe.id) from PortalUserPortraitEntity pupe where pupe.userId=?1 group by pupe.loginType")
    List<Number> countTimes(Integer userId);

    //根据id获取用户id
    @Query("SELECT pt.userId from PortalTechnologyEntity pt where pt.id = ?1")
    Integer getUserIdById(Integer id);

    List<PortalTechnologyEntity> findByUserId(Integer id);

    PortalTechnologyEntity findByUserIdAndId(Integer uid, Integer id);

    PortalTechnologyEntity findById(Integer id);

    @Modifying
    @Query("delete from PortalTechnologyEntity pe where pe.id =?1")
    Integer deleteById(Integer id);

    @Query("select p from PortalTechnologyEntity p where p.userId = ?1 and p.id = ?2 and p.type = ?3")
    PortalTechnologyEntity findByUserIdAndIdAndType(int uId, Integer id, Integer eType);

    @Query("SELECT lt FROM PortalTechnologyEntity lt WHERE lt.userId = ?1 and lt.type=3")
    Page<PortalTechnologyEntity> findByUserTitle(Integer uId,Pageable requestPage);

    @Query("SELECT lt FROM PortalTechnologyEntity lt where lt.userId = ?1 and lt.type=?2 ORDER BY lt.endTime DESC")

    List<PortalTechnologyEntity> findByUidAndType(Integer uid, Integer type);

    @Query("SELECT lt FROM PortalTechnologyEntity lt where lt.userId = ?1 and lt.type = ?2")
    Page<PortalTechnologyEntity> findByMajor(Integer id,Integer type, Pageable pr);
}
