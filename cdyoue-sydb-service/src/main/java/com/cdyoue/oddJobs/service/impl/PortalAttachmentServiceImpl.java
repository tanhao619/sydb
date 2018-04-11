package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.PortalAttachmentResponsitory;
import com.cdyoue.oddJobs.dto.common.AttachmentInfoSumary;
import com.cdyoue.oddJobs.entity.lgsq.PortalAttachmentEntity;
import com.cdyoue.oddJobs.exception.PersistenceEntityException;
import com.cdyoue.oddJobs.mapper.PortalAttachmentMapper;
import com.cdyoue.oddJobs.service.PortalAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liaoyoule on 2017/4/24.
 */
@Service
public class PortalAttachmentServiceImpl implements PortalAttachmentService {
    @Autowired
    private PortalAttachmentResponsitory portalAttachmentResponsitory;
    @Autowired
    private PortalAttachmentMapper portalAttachmentMapper;
    @Override
    public AttachmentInfoSumary save(PortalAttachmentEntity portalAttachmentEntity) {
        PortalAttachmentEntity pae = portalAttachmentResponsitory.save(portalAttachmentEntity);
        if(pae == null){
            throw new PersistenceEntityException("附件上传错误");
        }
        return portalAttachmentMapper.portalAttachmentEntityToAttachmentInfoSumary(pae);
    }

    @Override
    public PortalAttachmentEntity saveAttachment(AttachmentInfoSumary attachmentInfoSumary) {
        PortalAttachmentEntity portalAttachmentEntity=portalAttachmentMapper.attachmentInfoSumaryToPortalAttachmentEntity(attachmentInfoSumary);
        return portalAttachmentResponsitory.saveAndFlush(portalAttachmentEntity);
    }
}
