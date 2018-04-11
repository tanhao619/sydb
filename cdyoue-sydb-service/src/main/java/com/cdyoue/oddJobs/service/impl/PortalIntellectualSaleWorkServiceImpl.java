package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.PortalIntellectualSaleWorkResponsitory;
import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.zscq.*;
import com.cdyoue.oddJobs.en.MessageEventTypeEnum;
import com.cdyoue.oddJobs.en.MessageMsgTypeEnum;
import com.cdyoue.oddJobs.entity.lgsq.PortalIntellectualSaleWorkEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.IntellectualSaleMapper;
import com.cdyoue.oddJobs.service.PortalIntellectualSaleWorkService;
import com.cdyoue.oddJobs.service.support.ServiceSupport;
import com.cdyoue.oddJobs.utils.BeanPropertieUtils;
import com.cdyoue.oddJobs.utils.MessageUtils;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2017/5/12.
 */
@Transactional
@Service
public class PortalIntellectualSaleWorkServiceImpl extends ServiceSupport<PortalIntellectualSaleWorkEntity> implements PortalIntellectualSaleWorkService{
    @Autowired
    private PortalIntellectualSaleWorkResponsitory workResponsitory;
    @Autowired
    private IntellectualSaleMapper intellectualSaleMapper;

    @Override
    public Class getJpaRepositoryClazz() {
        return PortalIntellectualSaleWorkResponsitory.class;
    }

    @Override
    public Integer createWork(Copyright copyright) {
        PortalIntellectualSaleWorkEntity workEntity = new PortalIntellectualSaleWorkEntity();
        BeanUtils.copyProperties(copyright,workEntity);
        BeanUtils.copyProperties(copyright.getContact(),workEntity);
        workEntity.setName(copyright.getTitle());
        workEntity.setBusinessType(copyright.getTransactionType());
        workEntity.setIntroduction(copyright.getContent());
        workEntity.setPrice(Double.valueOf(copyright.getPrice()));
        workEntity.setContact(copyright.getContact().getPerson());
        workEntity.setTel(copyright.getContact().getPhone());
        workEntity.setViewCount(0);
        workEntity.setPublishTime(new Timestamp(System.currentTimeMillis()));
        workEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        workEntity.setReviewStatus(0);
        workEntity.setTop(0);
        workEntity.setCreateBy(SecurityUtils.getCurrentUserLogin().getId());
        PortalIntellectualSaleWorkEntity ps = workResponsitory.save(workEntity);
        return ps.getId();
    }

    @Override
    public CopyrightDetail getWorkById(Integer id) {
        PortalIntellectualSaleWorkEntity pre = workResponsitory.findOne(id);
        workResponsitory.addViewcount(id);
        if (pre == null) {
            throw new NotFoundEntityException();
        }
        CopyrightDetail detail = intellectualSaleMapper.WorkEntityToCopyrightDetail(pre);
        return detail;
    }

    @Override
    public void deleteIntelCopyright(Integer id) {
        PortalIntellectualSaleWorkEntity preFl = workResponsitory.findOne(id);
        if (preFl == null) {
            throw new NotFoundEntityException();
        }
            workResponsitory.delete(id);
    }

    @Override
    public void updateIntelCopyright(Integer tid, Copyright intellectual) {
        PortalIntellectualSaleWorkEntity preBe = intellectualSaleMapper.CopyrightToWorkEntity(intellectual);
        PortalIntellectualSaleWorkEntity preFl = workResponsitory.findOne(tid);
        if (preFl == null) {
            throw new NotFoundEntityException();
        }
        BeanPropertieUtils.copyPropertiesIgnoreNull(preBe, preFl);
        preFl.setId(tid);
        preFl.setReviewStatus(0);
        workResponsitory.save(preFl);
    }

    @Override
    public PageInfo<IntellectualSaleSummary> getIntellectualSalesWork(String q,Integer transactionType, PageRequest pr) {
        if(q==null||q.trim().equals("")){
            q="%%";
        }else{
            q="%"+q+"%";
        }
        Page<PortalIntellectualSaleWorkEntity> tePage = null;

        UserMine userMine = SecurityUtils.getCurrentUserLogin();
            if(userMine!=null&&userMine.getRole()==2){
                if(transactionType==null){
                    tePage = workResponsitory.getIntellectualSalesWork11(q, pr);
                }else {
                    tePage = workResponsitory.getIntellectualSalesWorkk(q,transactionType, pr);
                }
        }else {
            if(transactionType==null){
                tePage = workResponsitory.getIntellectualSalesWork1(1,q, pr);
            }else {
                tePage = workResponsitory.getIntellectualSalesWork(1,q,transactionType, pr);
            }
        }

        List<IntellectualSaleSummary> saleSummaries = tePage.getContent().stream().map(p -> intellectualSaleMapper.WorkEntityToSummary(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(saleSummaries, pr, tePage.getTotalElements()));
    }

    @Override
    @Transactional
    public void approveIntellectualSaleWork(Integer id, IntellectualMIni reviewReason) {
        PortalIntellectualSaleWorkEntity psb = workResponsitory.findOne(id);
        Integer uid = SecurityUtils.getCurrentUserLogin().getId();
        if(psb!=null){
            psb.setReviewTime(new Date(System.currentTimeMillis()));
            psb.setReviewStatus(1);
            psb.setReviewReason(reviewReason.getReviewReason());
            psb.setReviewUserId(uid);
//            Boolean isReview = MessageUtils.isMessageExist(id, MessageEventTypeEnum.AUDIT, MessageMsgTypeEnum.AuditSellIntellectualPropertyWork);
//            if (!isReview) {
                MessageUtils.createAuditMessage(psb.getCreateBy(),id, MessageEventTypeEnum.AUDIT, MessageMsgTypeEnum.AuditSellIntellectualPropertyWork,"1");
//            }

            workResponsitory.save(psb);
        }else {
            throw new NotFoundEntityException();
        }
    }

    @Override
    @Transactional
    public void rejectIntellectualSaleWork(Integer id, IntellectualMIni reviewReason) {
        PortalIntellectualSaleWorkEntity psb = workResponsitory.findOne(id);
        Integer uid = SecurityUtils.getCurrentUserLogin().getId();
        if(psb!=null){
            psb.setReviewStatus(2);
            psb.setReviewUserId(uid);
            psb.setReviewReason(reviewReason.getReviewReason());
            psb.setReviewTime(new Date(System.currentTimeMillis()));
            workResponsitory.save(psb);
//            Boolean isReview = MessageUtils.isMessageExist(id, MessageEventTypeEnum.AUDIT, MessageMsgTypeEnum.AuditSellIntellectualPropertyWork);
//            if (!isReview) {
                MessageUtils.createAuditMessage(psb.getCreateBy(),id,MessageEventTypeEnum.AUDIT,MessageMsgTypeEnum.AuditSellIntellectualPropertyWork,"2");
//            }
        }else {
            throw new NotFoundEntityException();
        }
    }

    @Override
    public void topSaleWork(Integer id,String topImg) {
        Date date = new Date(System.currentTimeMillis());
        workResponsitory.topSaleWork(id,date);
        if (topImg!=null && topImg.length()>0){
            PortalIntellectualSaleWorkEntity pe = workResponsitory.findOne(id);
            pe.setTopImg(topImg);
            workResponsitory.save(pe);
        }
    }

    @Override
    public void removeSaleWork(Integer id) {
        workResponsitory.removeSaleWork(id);
    }
}
