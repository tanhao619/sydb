package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.PortalIntellectualSalePatentResponsitory;
import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.zscq.IntellectualMIni;
import com.cdyoue.oddJobs.dto.zscq.IntellectualSaleSummary;
import com.cdyoue.oddJobs.dto.zscq.Patent;
import com.cdyoue.oddJobs.dto.zscq.PatentDetail;
import com.cdyoue.oddJobs.en.MessageEventTypeEnum;
import com.cdyoue.oddJobs.en.MessageMsgTypeEnum;
import com.cdyoue.oddJobs.entity.lgsq.PortalIntellectualSalePatentEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.IntellectualSaleMapper;
import com.cdyoue.oddJobs.service.PortalIntellectualSalePatentService;
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
public class PortalIntellectualSalePatentServiceImpl extends ServiceSupport<PortalIntellectualSalePatentEntity> implements PortalIntellectualSalePatentService {
    @Autowired
    private PortalIntellectualSalePatentResponsitory patentResponsitory;

    @Autowired
    private IntellectualSaleMapper intellectualSaleMapper;


    @Override
    public Class getJpaRepositoryClazz() {
        return PortalIntellectualSalePatentResponsitory.class;
    }

    @Override
    public Integer createPatent(Patent patent) {
        PortalIntellectualSalePatentEntity patentEntity = new PortalIntellectualSalePatentEntity();
        BeanUtils.copyProperties(patent,patentEntity);
        BeanUtils.copyProperties(patent.getContact(),patentEntity);
        patentEntity.setName(patent.getTitle());
        patentEntity.setIntroduction(patent.getContent());
        patentEntity.setApplyCode(patent.getPatentNo());
        patentEntity.setBusinessType(patent.getTransactionType());
        patentEntity.setPatentType(patent.getPatentType());
        patentEntity.setPrice(Double.valueOf(patent.getPrice()));
        patentEntity.setContact(patent.getContact().getPerson());
        patentEntity.setTel(patent.getContact().getPhone());
        patentEntity.setViewCount(0);
        patentEntity.setPublishTime(new Timestamp(System.currentTimeMillis()));
        patentEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        patentEntity.setReviewStatus(0);
        patentEntity.setTop(0);
        patentEntity.setCreateBy(SecurityUtils.getCurrentUserLogin().getId());
        PortalIntellectualSalePatentEntity ps = patentResponsitory.save(patentEntity);
        return ps.getId();
    }

    @Override
    public PatentDetail getIntelPatentById(Integer id) {
        PortalIntellectualSalePatentEntity pre = patentResponsitory.findOne(id);
        patentResponsitory.addViewcount(id);
        if (pre == null) {
            throw new NotFoundEntityException();
        }
        PatentDetail detail = intellectualSaleMapper.PatentEntityToPatentDetail(pre);
        return detail;
    }

    @Override
    public void deleteIntelPatentById(Integer id) {
        PortalIntellectualSalePatentEntity preFl = patentResponsitory.findOne(id);
        if (preFl == null) {
            throw new NotFoundEntityException();
        }
        patentResponsitory.delete(id);
    }

    @Override
    public void updateIntelPatent(Integer tid, Patent intellectual) {
        PortalIntellectualSalePatentEntity preBe = intellectualSaleMapper.PatentToPatentEntity(intellectual);
        PortalIntellectualSalePatentEntity preFl = patentResponsitory.findOne(tid);
        if (preFl == null) {
            throw new NotFoundEntityException();
        }
        BeanPropertieUtils.copyPropertiesIgnoreNull(preBe, preFl);
        preFl.setId(tid);
        preFl.setReviewStatus(0);
        patentResponsitory.save(preFl);
    }

    @Override
    public PageInfo<IntellectualSaleSummary> getIntellectualSalesPatent(String q,Integer transactionType, PageRequest pr) {
        if(q==null||q.trim().equals("")){
            q="%%";
        }else{
            q="%"+q+"%";
        }
        Page<PortalIntellectualSalePatentEntity> tePage = null;

        UserMine userMine = SecurityUtils.getCurrentUserLogin();

            if(userMine!=null&&userMine.getRole()==2){
                if(transactionType==null){
                    tePage = patentResponsitory.getIntellectualSalesPatent11(q, pr);
                }else {
                    tePage = patentResponsitory.getIntellectualSalesPatentt(q,transactionType, pr);
                }

        }else {
            if(transactionType==null){
                tePage = patentResponsitory.getIntellectualSalesPatent1(1,q, pr);
            }else {
                tePage = patentResponsitory.getIntellectualSalesPatent(1,q,transactionType, pr);
            }
        }


        List<IntellectualSaleSummary> saleSummaries = tePage.getContent().stream().map(p -> intellectualSaleMapper.PatentEntityToSummary(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(saleSummaries, pr, tePage.getTotalElements()));

    }

    @Override
    public void approveIntellectualSalePatent(Integer id, IntellectualMIni reviewReason) {
        PortalIntellectualSalePatentEntity psb = patentResponsitory.findOne(id);
        Integer uid = SecurityUtils.getCurrentUserLogin().getId();
        if(psb!=null){
            psb.setReviewStatus(1);
            psb.setReviewUserId(uid);
            psb.setReviewReason(reviewReason.getReviewReason());
            psb.setReviewTime(new Date(System.currentTimeMillis()));
            MessageUtils.createAuditMessage(psb.getCreateBy(),id, MessageEventTypeEnum.AUDIT, MessageMsgTypeEnum.AuditSellIntellectualPropertyPatent,"1");
            patentResponsitory.save(psb);
        }else {
            throw new NotFoundEntityException();
        }
    }

    @Override
    public void rejectIntellectualSalePatent(Integer id, IntellectualMIni reviewReason) {
        PortalIntellectualSalePatentEntity psb = patentResponsitory.findOne(id);
        Integer uid = SecurityUtils.getCurrentUserLogin().getId();
        if(psb!=null){
            psb.setReviewStatus(2);
            psb.setReviewUserId(uid);
            psb.setReviewReason(reviewReason.getReviewReason());
            psb.setReviewTime(new Date(System.currentTimeMillis()));
            patentResponsitory.save(psb);
            MessageUtils.createAuditMessage(psb.getCreateBy(),id,MessageEventTypeEnum.AUDIT,MessageMsgTypeEnum.AuditSellIntellectualPropertyPatent,"2");
        }else {
            throw new NotFoundEntityException();
        }
    }

    @Override
    public void topSalePatent(Integer id,String topImg) {
        Date date = new Date(System.currentTimeMillis());
        patentResponsitory.topSalePatent(id,date);
        if (topImg!=null && topImg.length()>0){
            PortalIntellectualSalePatentEntity pe = patentResponsitory.findOne(id);
            pe.setTopImg(topImg);
            patentResponsitory.save(pe);
        }
    }

    @Override
    public void removeSalePatent(Integer id) {
        patentResponsitory.removeSalePatent(id);
    }
}
