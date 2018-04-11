package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.PortalAttachmentResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.PortalProjectResponsitory;
import com.cdyoue.oddJobs.dto.Reason;
import com.cdyoue.oddJobs.dto.scfw.FinProjectDetail;
import com.cdyoue.oddJobs.dto.scfw.FinProjectSummary;
import com.cdyoue.oddJobs.en.MessageEventTypeEnum;
import com.cdyoue.oddJobs.en.MessageMsgTypeEnum;
import com.cdyoue.oddJobs.entity.lgsq.PortalAttachmentEntity;
import com.cdyoue.oddJobs.entity.lgsq.PortalProjectEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.FinprojectMapper;
import com.cdyoue.oddJobs.service.FinprojectService;
import com.cdyoue.oddJobs.service.support.ServiceSupport;
import com.cdyoue.oddJobs.utils.MessageUtils;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.page.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tr on 2017/5/23.
 */

@Service
public class FinprojectImpl extends ServiceSupport<PortalProjectEntity> implements FinprojectService {

    @Autowired
    private PortalProjectResponsitory portalProjectResponsitory;

    @Autowired
    private FinprojectMapper finprojectMapper;

    @Autowired
    private PortalAttachmentResponsitory portalAttachmentResponsitory;

    @Override
    public FinProjectDetail getFinProjectById(Integer id) {
        try {
            FinProjectDetail finProjectDetail = finprojectMapper.projectEntityToFinProjectDetail(getProjectEntityById(id));
            return finProjectDetail;
        }catch (Exception e){
            e.printStackTrace();
            throw new NotFoundEntityException("数据没找到");
        }
    }

    @Override
    public PortalProjectEntity getProjectEntityById(Integer id) {
        PortalProjectEntity one = portalProjectResponsitory.findOne(id);
        return one;
    }

    @Override
    @Transactional
    public void updateFinProject(Integer id, FinProjectDetail finproject) {
            PortalProjectEntity p = getProjectEntityById(id);
            if (p==null) throw new NotFoundEntityException("数据没找到");
            p.setName(finproject.getTitle());
            p.setFinance(finproject.getFinance());
            p.setIntroduction(finproject.getIntroduction());
            p.setInfo(finproject.getInfo());
            p.setImgUrl(finproject.getCoverImgUrl());
            //状态更新为待审核
            p.setReviewStatus((byte)0);
            if (finproject.getContact() != null) {
                p.setContact(finproject.getContact().getPerson());
                p.setEmail(finproject.getContact().getMail());
                p.setTel(finproject.getContact().getPhone());
            }
            Integer userId = SecurityUtils.getCurrentUserLogin().getId();
            p.setUpdateBy(userId);
            p.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            p.setId(id);
            portalProjectResponsitory.saveAndFlush(p);
//            portalAttachmentResponsitory.deleteAttach(p.getId(),new Byte("5"));
            //更新附件表中对应的信息
            String url = finproject.getAttachUrl();
            try {
                PortalAttachmentEntity pae  = portalAttachmentResponsitory.findByUrl(url);
                pae.setReferId(id);
                pae.setSourceType((byte)5);
                portalAttachmentResponsitory.saveAndFlush(pae);
                portalAttachmentResponsitory.deleteAttach(p.getId(),new Byte("5"),url);
             }catch (Exception e){
                e.printStackTrace();
                throw new NotFoundEntityException("该附件url对应的附件不存在");
            }
    }

    @Override
    public Integer createFinProject(Integer userId, FinProjectDetail finProjectDetail) {
        PortalProjectEntity p = finprojectMapper.finProjectDetailToEntity(finProjectDetail);
        p.setCreateBy(userId);
        p.setCreateTime(new Timestamp(System.currentTimeMillis()));
        p.setPublishTime(new Timestamp(System.currentTimeMillis()));
        //设置审核状态: 未审核
        p.setReviewStatus((byte)0);
        p.setViewCount(0);
        //保存并获取持久化类的id
        Integer referId = portalProjectResponsitory.saveAndFlush(p).getId();
        //更新附件表中对应的信息
        String url = finProjectDetail.getAttachUrl();
        PortalAttachmentEntity pae = portalAttachmentResponsitory.findByUrl(url);
        pae.setReferId(referId);
        pae.setSourceType((byte)5);
        portalAttachmentResponsitory.saveAndFlush(pae);
        return referId;
    }

    @Override
    public PageInfo<FinProjectSummary> getMyFinProjects(Integer userId, String q, Pageable pageRequest) {
        Page<PortalProjectEntity> rpPage = null;
        rpPage = StringUtils.isEmpty(q) ? portalProjectResponsitory.findByCreateBy(userId, pageRequest) : portalProjectResponsitory.findByCreateByAndNameLike(userId, q, pageRequest);
        List<FinProjectSummary> finProjectSummaries = rpPage.getContent().stream().map(p -> finprojectMapper.projectEntityToFinProjectSummary(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(finProjectSummaries, pageRequest, rpPage.getTotalElements()));
    }

    @Override
    @Transactional
    public void approveFinProject(Integer id, Reason finproject) {
        Integer uId = SecurityUtils.getCurrentUserLogin().getId();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        PortalProjectEntity entity = portalProjectResponsitory.findOne(id);
        byte code = 1;
        //通过后可以发布，设置发布时间
        entity.setPublishTime(new Timestamp(System.currentTimeMillis()));
        entity.setReviewStatus(code);
        entity.setReviewTime(time);
        entity.setReviewUserId(uId);
        entity.setReviewReason(finproject.getReason());
            //entity.setReviewReason();
        MessageUtils.createAuditMessage(entity.getCreateBy(), entity.getId(), MessageEventTypeEnum.AUDIT, MessageMsgTypeEnum.AuditPublishFinanceProject,"1");
        portalProjectResponsitory.save(entity);
    }

    @Override
    public void rejectFinProject(Integer id, Reason finproject) {
        try {
            Integer uId = SecurityUtils.getCurrentUserLogin().getId();
            Timestamp time = new Timestamp(System.currentTimeMillis());
            PortalProjectEntity entity = portalProjectResponsitory.findOne(id);
            byte code = 2;
            entity.setReviewStatus(code);
            entity.setReviewTime(time);
            entity.setReviewUserId(uId);
            entity.setReviewReason(finproject.getReason());
            MessageUtils.createAuditMessage(entity.getCreateBy(),id,MessageEventTypeEnum.AUDIT,MessageMsgTypeEnum.AuditPublishFinanceProject,"2");
            portalProjectResponsitory.save(entity);
        }catch (Exception e){
            e.printStackTrace();
            throw new NotFoundEntityException("数据没找到");
        }
    }

    @Override
    public FinProjectDetail downloadFinProjectById(Integer id) {
        try {
            FinProjectDetail finProjectDetail = finprojectMapper.projectEntityToFinProjectDetail(getProjectEntityById(id));
            String url = portalAttachmentResponsitory.findUrlByReferId(id,(byte)5);
            finProjectDetail.setAttachUrl(url);
            return finProjectDetail;
        }catch (Exception e){
            e.printStackTrace();
            throw new NotFoundEntityException("数据没找到");
        }
    }

    @Override
    public PageInfo<FinProjectSummary> findByKeyWord(Pageable requestPage, String q, Byte statusFilter) {
        if (statusFilter != null){
            Page<PortalProjectEntity> rpPage = null;
            rpPage = StringUtils.isEmpty(q) ? portalProjectResponsitory.findByStatusFilter(statusFilter, requestPage) : portalProjectResponsitory.findByKeywordAndStatus(q, statusFilter, requestPage);
            List<FinProjectSummary> finProjectSummaries = rpPage.getContent().stream().map(p -> finprojectMapper.projectEntityToFinProjectSummary(p)).collect(Collectors.toList());
            return new PageInfo<>(new PageImpl(finProjectSummaries, requestPage, rpPage.getTotalElements()));
        }else {
            Page<PortalProjectEntity> rpPage = super.findByStrLike("name", q, requestPage);
            List<FinProjectSummary> finProjectSummaries = rpPage.getContent().stream().map(p -> finprojectMapper.projectEntityToFinProjectSummary(p)).collect(Collectors.toList());
            return new PageInfo<>(new PageImpl(finProjectSummaries, requestPage, rpPage.getTotalElements()));
        }
    }

    @Override
    public void updateAttachment(Integer id, FinProjectDetail finProjectDetail) {
        //复制给附件表的referId与sourceType
        PortalAttachmentEntity pae = portalAttachmentResponsitory.getPortalAttachmentEntityByUrl(finProjectDetail.getAttachUrl());
        pae.setReferId(id);
        pae.setSourceType((byte) 5);
        portalAttachmentResponsitory.saveAndFlush(pae);
    }

    @Override
    public void deleteFinProject(Integer id) {
        portalProjectResponsitory.delete(id);
    }

    @Override
    public boolean deleteOrUpdateAuthority(Integer id, Integer userId) {
        PortalProjectEntity ppe = portalProjectResponsitory.findOne(id);
        if (ppe == null) {throw new EntityNotFoundException("数据没找到");}
        return userId.intValue() == ppe.getCreateBy().intValue();
    }


    @Override
    public Class getJpaRepositoryClazz() {
        return PortalProjectResponsitory.class;
    }
}
