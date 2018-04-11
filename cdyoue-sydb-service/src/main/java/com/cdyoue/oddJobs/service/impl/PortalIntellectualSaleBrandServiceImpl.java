package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.PortalIntellectualSaleBrandResponsitory;
import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.zscq.*;
import com.cdyoue.oddJobs.en.MessageEventTypeEnum;
import com.cdyoue.oddJobs.en.MessageMsgTypeEnum;
import com.cdyoue.oddJobs.entity.lgsq.PortalIntellectualSaleBrandEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.IntellectualSaleMapper;
import com.cdyoue.oddJobs.service.PortalIntellectualSaleBrandService;
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
public class PortalIntellectualSaleBrandServiceImpl extends ServiceSupport<PortalIntellectualSaleBrandEntity> implements PortalIntellectualSaleBrandService {

    @Autowired
    private PortalIntellectualSaleBrandResponsitory brandResponsitory;
    @Autowired
    private IntellectualSaleMapper intellectualSaleMapper;
    @Override
    public Class getJpaRepositoryClazz() {
        return PortalIntellectualSaleBrandResponsitory.class;
    }

    @Override
    public Integer createBrand(Trademark trademark) {
        PortalIntellectualSaleBrandEntity brandEntity = new PortalIntellectualSaleBrandEntity();
        BeanUtils.copyProperties(trademark,brandEntity);
        BeanUtils.copyProperties(trademark.getContact(),brandEntity);
        brandEntity.setName(trademark.getTitle());
        brandEntity.setIntroduction(trademark.getContent());
        brandEntity.setCoverImg(trademark.getImageUrl());
        brandEntity.setBusinessType(trademark.getTransactionType());
        brandEntity.setPublishTime(new Timestamp(System.currentTimeMillis()));
        brandEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        brandEntity.setPrice(Double.valueOf(trademark.getPrice()));
        brandEntity.setTel(trademark.getContact().getPhone());
        brandEntity.setContact(trademark.getContact().getPerson());
        brandEntity.setViewCount(0);
        brandEntity.setReviewStatus(0);
        brandEntity.setTop(0);
        brandEntity.setCreateBy(SecurityUtils.getCurrentUserLogin().getId());
        PortalIntellectualSaleBrandEntity ps = brandResponsitory.save(brandEntity);
        return ps.getId();
    }

    @Override
    public TrademarkDetail getIntelTrademarkById(Integer id) {
        PortalIntellectualSaleBrandEntity pre = brandResponsitory.findOne(id);
        brandResponsitory.addViewcount(id);
        if (pre == null) {
            throw new NotFoundEntityException();
        }
        TrademarkDetail detail = intellectualSaleMapper.BrandEntityToTrademarkDetail(pre);
        return detail;
    }

    @Override
    public void deleteIntelTrademarkById(Integer id) {
        PortalIntellectualSaleBrandEntity preFl = brandResponsitory.findOne(id);
        if (preFl == null) {
            throw new NotFoundEntityException();
        }
        brandResponsitory.delete(id);
    }

    @Override
    public void updateIntelTrademark(Integer tid, Trademark intellectual) {
        PortalIntellectualSaleBrandEntity preBe = intellectualSaleMapper.TrademarkToBrandEntity(intellectual);
        PortalIntellectualSaleBrandEntity preFl = brandResponsitory.findOne(tid);
        if (preFl == null) {
            throw new NotFoundEntityException();
        }
        BeanPropertieUtils.copyPropertiesIgnoreNull(preBe, preFl);
        preFl.setId(tid);
        preFl.setReviewStatus(0);
        brandResponsitory.save(preFl);
    }

    @Override
    public PageInfo<IntellectualSaleSummary> getIntellectualSalesBrand(String q,Integer transactionType, PageRequest pr) {
        if(q==null||q.trim().equals("")){
            q="%%";
        }else{
            q="%"+q+"%";
        }
        Page<PortalIntellectualSaleBrandEntity> tePage = null;

        UserMine userMine = SecurityUtils.getCurrentUserLogin();
            if(userMine!=null&&userMine.getRole()==2){
                if(transactionType==null){
                    tePage = brandResponsitory.getIntellectualSalesBrand11(q, pr);
                }else {
                    tePage = brandResponsitory.getIntellectualSalesBrandd(q,transactionType, pr);
                }
        }else {
            if(transactionType==null){
                tePage = brandResponsitory.getIntellectualSalesBrand1(1,q, pr);
            }else {
                tePage = brandResponsitory.getIntellectualSalesBrand(1,q,transactionType, pr);
            }
        }

        List<IntellectualSaleSummary> saleSummaries = tePage.getContent().stream().map(p -> intellectualSaleMapper.BrandEntityToSummary(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(saleSummaries, pr, tePage.getTotalElements()));
    }

    @Override
    @Transactional
    public void approveIntellectualSaleBrand(Integer id, IntellectualMIni reviewReason) {
        PortalIntellectualSaleBrandEntity psb = brandResponsitory.findOne(id);
        Integer uid = SecurityUtils.getCurrentUserLogin().getId();
        if(psb!=null){
            psb.setReviewStatus(1);
            psb.setReviewReason(reviewReason.getReviewReason());
            psb.setReviewUserId(uid);
            psb.setReviewTime(new Date(System.currentTimeMillis()));
            MessageUtils.createAuditMessage(psb.getCreateBy(),id,MessageEventTypeEnum.AUDIT,MessageMsgTypeEnum.AuditSellIntellectualPropertyBrand,"1");
            brandResponsitory.save(psb);
//            MessageUtils.createMessage(id,psb.getCreateBy(), MessageEventTypeEnum.AUDIT, MessageMsgTypeEnum.AuditBuyIntellectualProperty);
        }else {
            throw new NotFoundEntityException();
        }
    }

    @Override
    public void rejectIntellectualSaleBrand(Integer id, IntellectualMIni reviewReason) {
        PortalIntellectualSaleBrandEntity psb = brandResponsitory.findOne(id);
        Integer uid = SecurityUtils.getCurrentUserLogin().getId();
        if(psb!=null){
            psb.setReviewStatus(2);
            psb.setReviewReason(reviewReason.getReviewReason());
            psb.setReviewUserId(uid);
            psb.setReviewTime(new Date(System.currentTimeMillis()));
            brandResponsitory.save(psb);
            MessageUtils.createAuditMessage(psb.getCreateBy(),id,MessageEventTypeEnum.AUDIT,MessageMsgTypeEnum.AuditSellIntellectualPropertyBrand,"2");

        }else {
            throw new NotFoundEntityException();
        }
    }

    @Override
    public void topSaleBrand(Integer id,String topImg) {
        Date date = new Date(System.currentTimeMillis());
        brandResponsitory.topSaleBrand(id,date);
        if (topImg!=null && topImg.length()>0){
            PortalIntellectualSaleBrandEntity pe = brandResponsitory.findOne(id);
            pe.setTopImg(topImg);
            brandResponsitory.save(pe);
        }
    }

    @Override
    public void removeSaleBrand(Integer id) {
        brandResponsitory.removeSaleBrand(id);
    }
}
