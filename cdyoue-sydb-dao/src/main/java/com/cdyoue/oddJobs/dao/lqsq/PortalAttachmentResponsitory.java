package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.PortalAttachmentEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by liaoyoule on 2017/4/24.
 */
public interface PortalAttachmentResponsitory extends JpaCustomResponsitory<PortalAttachmentEntity,Integer> {
    @Query("SELECT pae.url FROM PortalAttachmentEntity pae where pae.referId = ?1 and pae.sourceType = ?2")
    String findUrlByReferId(Integer referId, Byte sourceType);


    @Query("SELECT pae FROM PortalAttachmentEntity pae where pae.url = ?1")
    PortalAttachmentEntity getPortalAttachmentEntityByUrl(String url);

    @Query("SELECT pae FROM PortalAttachmentEntity pae where pae.referId = ?1 and pae.sourceType = ?2")
    PortalAttachmentEntity findEntityByReferIdAndType(int id, byte b);

    PortalAttachmentEntity findByUrl(String url);

    @Modifying
    @Query("delete from PortalAttachmentEntity p where p.referId =?1 and p.sourceType = ?2 and p.url != ?3")
    void deleteAttach(int id, Byte aByte, String url);

    List<PortalAttachmentEntity> findByReferId(String referId);
}
