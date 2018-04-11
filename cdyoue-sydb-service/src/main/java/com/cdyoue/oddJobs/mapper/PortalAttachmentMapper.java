package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dto.common.AttachmentInfoSumary;
import com.cdyoue.oddJobs.entity.lgsq.PortalAttachmentEntity;
import org.springframework.stereotype.Component;

/**
 * Created by liaoyoule on 2017/4/24.
 */
@Component
public class PortalAttachmentMapper {
    public AttachmentInfoSumary portalAttachmentEntityToAttachmentInfoSumary(PortalAttachmentEntity pae){
        AttachmentInfoSumary sumary = new AttachmentInfoSumary();
        sumary.setId(pae.getId());
        sumary.setName(pae.getName());
        sumary.setUrl(pae.getUrl());
        return sumary;
    }
    public PortalAttachmentEntity attachmentInfoSumaryToPortalAttachmentEntity(AttachmentInfoSumary attachmentInfoSumary){
        PortalAttachmentEntity portalAttachmentEntity = new PortalAttachmentEntity();
        portalAttachmentEntity.setName(attachmentInfoSumary.getName());
        portalAttachmentEntity.setUrl(attachmentInfoSumary.getUrl());
        return portalAttachmentEntity;
    }
}
