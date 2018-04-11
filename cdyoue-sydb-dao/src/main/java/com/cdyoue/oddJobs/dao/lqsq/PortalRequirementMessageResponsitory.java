package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.RequirementMessageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by liaoyoule on 2017/4/24.
 */
public interface PortalRequirementMessageResponsitory extends  JpaCustomResponsitory<RequirementMessageEntity,Integer> {
    @Query("select pme from RequirementMessageEntity pme where pme.opera.id = ?1 and  pme.msgType = ?2 and pme.eventType = ?3")
    Page<RequirementMessageEntity> findByOperaIdAndMsgTypeAndEventType(Integer id, Byte msgType, Byte eventType, Pageable page);
    @Query("select pme from RequirementMessageEntity pme where pme.receiver.id = ?1 and  pme.msgType = ?2 and pme.eventType = ?3")
    Page<RequirementMessageEntity> findByReceiverIdAndMsgTypeAndEventType(Integer id, Byte aByte, Byte aByte1, Pageable page);
    @Query("select pme from RequirementMessageEntity pme where pme.requirementEntity.id = ?1 and  pme.receiver.id = ?2")
    RequirementMessageEntity findByRequireIdAndReceiverId(Integer id, Integer userid);
    @Modifying
    @Query("delete from RequirementMessageEntity pme where  pme.opera.id = ?1 and pme.requirementEntity.id = ?2")
    void deleteByUserAndOpId(Integer userId, Integer id);

    @Query("select pme from RequirementMessageEntity pme where pme.requirementEntity.id = ?1 and  pme.msgType = ?2 and pme.eventType = ?3")
    List<RequirementMessageEntity> findAcceptPeoples(Integer id, Byte msgType, Byte eventType);


    @Query("select pme from RequirementMessageEntity pme where pme.requirementEntity.id = ?1 and  pme.msgType = ?2 and pme.eventType = ?3")
    Page<RequirementMessageEntity> findAcceptPeoples(Integer id, Byte msgType, Byte eventType, Pageable page);

    @Modifying
    @Query("delete  from RequirementMessageEntity pme where  pme.requirementEntity.id = ?1")
    void deleteByReId(Integer id);
    @Query("select count(pme.id) from RequirementMessageEntity pme where pme.opera.id =?1 and pme.requirementEntity.id = ?2 and pme.msgType = ?3 and pme.eventType = ?4")
    Integer findByOperaIdAndReIdAndMsgTypeAndEventType(Integer id, Integer reId, Byte aByte, Byte aByte1);

    @Query("select count(pme.id) from RequirementMessageEntity pme where pme.receiver.id =?1 and pme.requirementEntity.id = ?2 and pme.msgType = ?3 and pme.eventType = ?4")
    Integer findByReceiverIdAndReIdAndMsgTypeAndEventType(Integer id, Integer reId, Byte aByte, Byte aByte1);
    @Query("select count(pme.id) from RequirementMessageEntity pme where pme.requirementEntity.id = ?1 and  pme.msgType = ?2 and pme.eventType = ?3")
    Integer findAcceptPeoplesCount(Integer id, Byte aByte, Byte aByte1);

    @Query("select count(pme.id) from RequirementMessageEntity pme where pme.requirementEntity.id = ?1 and pme.eventType = 4 and pme.msgType = 3")
    Integer countAccRequire(Integer id);

    @Query("select pme from RequirementMessageEntity pme where pme.opera.id = ?1 and  pme.msgType = ?2 and pme.eventType = ?3")
    Page<RequirementMessageEntity> findAcceptRequirByUserId(Integer userId, Byte aByte, Byte aByte1, Pageable pageable);
}
