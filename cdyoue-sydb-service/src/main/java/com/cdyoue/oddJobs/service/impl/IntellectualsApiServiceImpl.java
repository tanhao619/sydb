package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.constant.DataTypeConstant;
import com.cdyoue.oddJobs.dao.lqsq.PortalIntellectualBuyResponsitory;
import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.zscq.Intellectual;
import com.cdyoue.oddJobs.dto.zscq.IntellectualDetail;
import com.cdyoue.oddJobs.dto.zscq.IntellectualMine;
import com.cdyoue.oddJobs.dto.zscq.IntellectualSummary;
import com.cdyoue.oddJobs.en.MessageEventTypeEnum;
import com.cdyoue.oddJobs.en.MessageMsgTypeEnum;
import com.cdyoue.oddJobs.entity.lgsq.PortalIntellectualBuyEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.IntellectualBuyMapper;
import com.cdyoue.oddJobs.mapper.PortalIntellectualsMapper;
import com.cdyoue.oddJobs.service.IntellectualsApiService;
import com.cdyoue.oddJobs.service.support.ServiceSupport;
import com.cdyoue.oddJobs.spec.Operator;
import com.cdyoue.oddJobs.spec.QueryRequest;
import com.cdyoue.oddJobs.spec.SpecificationHelper;
import com.cdyoue.oddJobs.utils.BeanPropertieUtils;
import com.cdyoue.oddJobs.utils.MessageUtils;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.oddJobs.utils.SpringContextUtil;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.ParameterizedType;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by sky on 2017/5/2.
 *
 */
@Service
@Transactional
public class IntellectualsApiServiceImpl extends ServiceSupport<PortalIntellectualBuyEntity> implements IntellectualsApiService  {

    @Autowired
    private SpecificationHelper specificationHelper;

    @Autowired
    private PortalIntellectualsMapper portalIntellectualsMapper ;

    @Autowired
    private IntellectualBuyMapper intellectualBuyMapper;

    @Autowired
    private PortalIntellectualBuyResponsitory intellectualBuyResponsitory;


    @Override
    public PageInfo<IntellectualSummary> getIntellectuals(String q ,Integer intellType, Integer businessType, Pageable requestPage) {
        UserMine userMine = SecurityUtils.getCurrentUserLogin();
        Page<PortalIntellectualBuyEntity> rpPage = null;
        //管理员可以返回所有状态
        if (userMine != null){
            if (userMine.getRole() == 2) {
                rpPage = findPortalIntellectualBuyEntitys(q, intellType, businessType, null, requestPage);
            }else {
                //非管理员只能查看审核通过的
                rpPage = findPortalIntellectualBuyEntitys(q, intellType, businessType, 1, requestPage);
            }
        }else{
            //没登录
            rpPage = findPortalIntellectualBuyEntitys(q, intellType, businessType, 1, requestPage);
        }

        List<IntellectualSummary> IntellectualSummaries = rpPage.getContent().stream().map(p -> portalIntellectualsMapper.intellectualToIntellectualSummary(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(IntellectualSummaries, requestPage, rpPage.getTotalElements()));

    }


        @Override
        public Integer createIntellectualBuy(Intellectual intellectual) {
            PortalIntellectualBuyEntity pib = intellectualBuyMapper.intellectualToEntity(intellectual);
            Integer id = SecurityUtils.getCurrentUserLogin().getId();
            UserEntity u = new UserEntity();
            u.setId(id);
            pib.setCreateBy(u);
            pib.setViewCount(0);
            pib.setReviewStatus(0);
            pib.setTop(0);
            pib.setPublishTime(new Timestamp(System.currentTimeMillis()));
            pib.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            return intellectualBuyResponsitory.save(pib).getId();
        }

    @Override
    public PortalIntellectualBuyEntity findIntellectualBuy(Integer id) {
        try {
            return intellectualBuyResponsitory.findOne(id);
        }catch (Exception e) {
            throw new EntityNotFoundException("获取数据失败");
        }
    }

    @Override
    public void approveIntellectualBuy(PortalIntellectualBuyEntity pib ,String reason) {
        pib.setReviewStatus(1);
        pib.setReviewUserId(SecurityUtils.getCurrentUserLogin().getId());
        pib.setReviewTime(new Timestamp(System.currentTimeMillis()));
        pib.setReviewReason(reason);
        intellectualBuyResponsitory.save(pib);
        MessageUtils.createAuditMessage(pib.getCreateBy().getId(),pib.getId(),MessageEventTypeEnum.AUDIT,MessageMsgTypeEnum.AuditBuyIntellectualProperty,"1");
    }

    @Override
    public void rejectIntellectualBuy(PortalIntellectualBuyEntity pib,String reason) {
        pib.setReviewStatus(2);
        pib.setReviewUserId(SecurityUtils.getCurrentUserLogin().getId());
        pib.setReviewTime(new Timestamp(System.currentTimeMillis()));
        pib.setReviewReason(reason);
        MessageUtils.createAuditMessage(pib.getCreateBy().getId(),pib.getId(),MessageEventTypeEnum.AUDIT,MessageMsgTypeEnum.AuditBuyIntellectualProperty,"2");
        intellectualBuyResponsitory.save(pib);
    }

    @Override
    public PageInfo<IntellectualMine> findMyIntellectualBuy(Pageable requestPage, Integer intellType, Integer businessType) {
        try {
            Integer userId = SecurityUtils.getCurrentUserLogin().getId();
            Page<PortalIntellectualBuyEntity> rpPage = findMyIntellectualBuy(userId, intellType, businessType, requestPage);
            List<IntellectualMine> list = rpPage.getContent().stream().map(p -> portalIntellectualsMapper.intellectualBuyEntityToIntellectualMine(p)).collect(Collectors.toList());
            return new PageInfo<>(new PageImpl(list, requestPage, rpPage.getTotalElements()));
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public IntellectualDetail getIntellectualBuyById(Integer id) {
        try {
            PortalIntellectualBuyEntity pb = findIntellectualBuy(id);
            IntellectualDetail intellectualDetail = intellectualBuyMapper.IntellectualBuyEntityToIntellectualDetail(pb);
            intellectualBuyResponsitory.addViewcount(id);
            return intellectualDetail;
        } catch (Exception e){
            throw new NotFoundEntityException("数据不存在");
        }
    }

    private Page<PortalIntellectualBuyEntity> findMyIntellectualBuy(Integer userId, Integer intellType, Integer businessType, Pageable requestPage) {
        List<QueryRequest> queryRequest = new ArrayList<>();
        if (Optional.ofNullable(userId).isPresent()) {
            QueryRequest qe = new QueryRequest();
            qe.setF("createBy.id");
            qe.setO(Operator.EQ);
            qe.setV(userId + "");
            qe.setT(DataTypeConstant.INTEGER);
            queryRequest.add(qe);
        }

        if (Optional.ofNullable(intellType).isPresent()) {
            QueryRequest qeEr = new QueryRequest();
            qeEr.setF("intellType");
            qeEr.setV(intellType + "");
            qeEr.setT(DataTypeConstant.INTEGER);
            qeEr.setO(Operator.EQ);
            queryRequest.add(qeEr);
        }

        if (Optional.ofNullable(businessType).isPresent()) {
            QueryRequest qeEr = new QueryRequest();
            qeEr.setF("businessType");
            qeEr.setO(Operator.EQ);
            qeEr.setV(businessType + "");
            qeEr.setT(DataTypeConstant.INTEGER);
            queryRequest.add(qeEr);
        }


        Class entityClazz = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        Specification specifica = specificationHelper.getSpecifica(entityClazz, queryRequest);
        Page<PortalIntellectualBuyEntity> page = ((JpaSpecificationExecutor) SpringContextUtil.getBean(getJpaRepositoryClazz())).findAll(specifica, requestPage);
        if (page.getContent().size() == 0) {
            throw new NotFoundEntityException("数据不存在");
        }
        return page;
    }


    @Override
    public Class getJpaRepositoryClazz() {
        return PortalIntellectualBuyResponsitory.class;
    }

    public Page<PortalIntellectualBuyEntity> findPortalIntellectualBuyEntitys(String q, Integer intellType, Integer businessType, Integer reviewStatus, Pageable rqPage) {
        List<QueryRequest> queryRequest = new ArrayList<>();


        if (Optional.ofNullable(q).isPresent()) {
            QueryRequest qe = new QueryRequest();
            qe.setF("name");
            qe.setO(Operator.LIKE);
            qe.setV(q);
            queryRequest.add(qe);
        }

        if (Optional.ofNullable(intellType).isPresent()) {
            QueryRequest qi = new QueryRequest();
            qi.setF("intellType");
            qi.setV(intellType + "");
            qi.setO(Operator.EQ);
            qi.setT(DataTypeConstant.INTEGER);
            queryRequest.add(qi);
        }

        if (Optional.ofNullable(businessType).isPresent()) {
            QueryRequest qeEr = new QueryRequest();
            qeEr.setO(Operator.EQ);
            qeEr.setF("businessType");
            qeEr.setV(businessType + "");
            qeEr.setT(DataTypeConstant.INTEGER);
            queryRequest.add(qeEr);
        }

        if (Optional.ofNullable(reviewStatus).isPresent()) {
            QueryRequest qeEr = new QueryRequest();
            qeEr.setF("reviewStatus");
            qeEr.setV(reviewStatus + "");
            qeEr.setO(Operator.EQ);
            qeEr.setT(DataTypeConstant.INTEGER);
            queryRequest.add(qeEr);
        }

        Class entityClazz = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        Specification specifica = specificationHelper.getSpecifica(entityClazz, queryRequest);
        Page<PortalIntellectualBuyEntity> page = ((JpaSpecificationExecutor) SpringContextUtil.getBean(getJpaRepositoryClazz())).findAll(specifica, rqPage);
        if (page.getContent().size() == 0) {
            throw new NotFoundEntityException("数据不存在");
        }
        return page;
    }

    @Override
    public void deleteByPrimary(Integer id) {
        intellectualBuyResponsitory.delete(id);
    }

    @Override
    public void updateIntellectual(Integer id, Intellectual intellectual) {
        PortalIntellectualBuyEntity preBe = intellectualBuyMapper.intellectualToPortalIntellectualBuyEntity(intellectual);
        PortalIntellectualBuyEntity preFl = intellectualBuyResponsitory.findOne(id);
        if (preFl == null) {
            throw new NotFoundEntityException();
        }
        BeanPropertieUtils.copyPropertiesIgnoreNull(preBe, preFl);
        preFl.setId(id);
        preFl.setReviewStatus(0);
        intellectualBuyResponsitory.save(preFl);
    }
}
