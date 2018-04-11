package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.common.AttachmentInfoSumary;
import com.cdyoue.oddJobs.entity.lgsq.PortalAttachmentEntity;

/**
 * Created by liaoyoule on 2017/4/24.
 */
public interface PortalAttachmentService {
    /**
     * 公共附件上传
     * @param portalAttachmentEntity
     * @return
     */
    AttachmentInfoSumary save(PortalAttachmentEntity portalAttachmentEntity);

    PortalAttachmentEntity saveAttachment(AttachmentInfoSumary attachmentInfoSumary);
}
