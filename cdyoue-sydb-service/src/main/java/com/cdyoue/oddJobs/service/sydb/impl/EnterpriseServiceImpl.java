package com.cdyoue.oddJobs.service.sydb.impl;

import com.cdyoue.oddJobs.dao.syData.ApplicationCheckInRepository;
import com.cdyoue.oddJobs.dao.syData.ApplicationDirectionRepository;
import com.cdyoue.oddJobs.dao.syData.EnterpriseResponsitory;
import com.cdyoue.oddJobs.dto.zlcy.*;
import com.cdyoue.oddJobs.entity.syData.SyApplicationCheckInMessageEntity;
import com.cdyoue.oddJobs.entity.syData.SyApplicationDirectionMessageEntity;
import com.cdyoue.oddJobs.entity.syData.SyEnterpriseEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.sydb.EnterpriseMapper;
import com.cdyoue.oddJobs.service.support.ServiceSupport;
import com.cdyoue.oddJobs.service.sydb.EnterpriseService;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sky on 2017/9/23.
 */
@Service
public class EnterpriseServiceImpl extends ServiceSupport<SyEnterpriseEntity> implements EnterpriseService{
    @Autowired
    EnterpriseResponsitory enterpriseResponsitory;
    @Autowired
    EnterpriseMapper enterpriseMapper;
    @Autowired
    ApplicationCheckInRepository applicationCheckInRepository;
    @Autowired
    ApplicationDirectionRepository applicationDirectionRepository;


    @Override
    public EnterpriseDetail getEnterpriseById(Integer id) {
        SyEnterpriseEntity syEnterpriseEntity=enterpriseResponsitory.findOne(id);
        return enterpriseMapper.syTrainingEntityToTrainingDetail(syEnterpriseEntity);
    }

    @Override
    public boolean getEnterpriseCheck(Integer id) {
        if(enterpriseResponsitory.findOne(id)==null) {
            return false;
        }else {
            return true;
        }

    }

    @Override
    public PageInfo<EnterpriseSummary> findList(PageRequest pr, String q) {
        Page<SyEnterpriseEntity> rpPage = super.findByOneLike("name",q,pr);
        List<EnterpriseSummary> enterpriseSummaryList=rpPage.getContent().stream().map(p ->enterpriseMapper.syTrainingEntityToTrainingSummary(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(enterpriseSummaryList, pr, rpPage.getTotalElements()));
    }

    @Override
    public List<EnterpriseSummary> findTopList() {
        List<SyEnterpriseEntity> topList=enterpriseResponsitory.findTop4ByTopOrderByUpdateTimeDesc(1);
        if (topList.size() == 0) {
            throw new NotFoundEntityException("没有数据");
        }
        return topList.stream().map(p -> enterpriseMapper.syTrainingEntityToTrainingSummary(p)).collect(Collectors.toList());
    }
//申请入驻
    @Override
    public Integer saveApplicationMessage(ApplicationEnterpriseSummary applicationEnterpriseSummary) {
        SyApplicationCheckInMessageEntity syApplicationCheckInMessageEntity=new SyApplicationCheckInMessageEntity();
        syApplicationCheckInMessageEntity.setEnterpriseUserId(SecurityUtils.getCurrentUserLogin().getId());
        syApplicationCheckInMessageEntity.setContactPeople(applicationEnterpriseSummary.getContactPeople());
        syApplicationCheckInMessageEntity.setContactNumber(applicationEnterpriseSummary.getContactNumber());
        syApplicationCheckInMessageEntity.setEnterpriseLogo(applicationEnterpriseSummary.getEnterpriseLogo());
        syApplicationCheckInMessageEntity.setEnterpriseInfo(applicationEnterpriseSummary.getEnterpriseInfo());
        syApplicationCheckInMessageEntity.setEnterpriseUrl(applicationEnterpriseSummary.getEnterpriseUrl());
        syApplicationCheckInMessageEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        syApplicationCheckInMessageEntity.setUpdateTime(syApplicationCheckInMessageEntity.getCreateTime());
        syApplicationCheckInMessageEntity.setStatus(new Byte("0"));
        return applicationCheckInRepository.save(syApplicationCheckInMessageEntity).getId();
    }
//申请创业指导
    @Override
    public Integer saveDirectionMessage(ApplicationDirectionSummary applicationDirectionSummary) {
        SyApplicationDirectionMessageEntity syApplicationDirectionMessageEntity=new SyApplicationDirectionMessageEntity();
        BeanUtils.copyProperties(applicationDirectionSummary,syApplicationDirectionMessageEntity);
        syApplicationDirectionMessageEntity.setEnterpriseUserId(SecurityUtils.getCurrentUserLogin().getId());
        syApplicationDirectionMessageEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        syApplicationDirectionMessageEntity.setUpdateTime(syApplicationDirectionMessageEntity.getCreateTime());
        syApplicationDirectionMessageEntity.setStatus(new Byte("0"));
        return applicationDirectionRepository.save(syApplicationDirectionMessageEntity).getId();
    }

    @Override
    public Integer createEnterprise(EnterpriseCreate enterpriseCreate) {
        SyEnterpriseEntity syEnterpriseEntity=enterpriseMapper.enterpriseCreateToSyEnterpriseEntity(enterpriseCreate);
        return  enterpriseResponsitory.save(syEnterpriseEntity).getId();
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        List<SyEnterpriseEntity> syEnterpriseEntityList=enterpriseResponsitory.findAll(Arrays.asList(ids));
        enterpriseResponsitory.delete(syEnterpriseEntityList);
    }

    @Override
    public void updateEnterprise(Integer id, EnterpriseCreate enterpriseCreate) {
        SyEnterpriseEntity syEnterpriseEntity=enterpriseResponsitory.findOne(id);
        BeanUtils.copyProperties(enterpriseCreate,syEnterpriseEntity);
        syEnterpriseEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        syEnterpriseEntity.setCreateTime(syEnterpriseEntity.getUpdateTime());
        enterpriseResponsitory.saveAndFlush(syEnterpriseEntity);
    }

    @Override
    public void updateEnterpriseViewCount(Integer id) {
        SyEnterpriseEntity syEnterpriseEntity=enterpriseResponsitory.findOne(id);
        syEnterpriseEntity.setViewCount(syEnterpriseEntity.getViewCount()+1);
        enterpriseResponsitory.saveAndFlush(syEnterpriseEntity);
    }

    @Override
    public void topEnterprise(Integer id,String topImage) {
        SyEnterpriseEntity syEnterpriseEntity=enterpriseResponsitory.findOne(id);
        int top=syEnterpriseEntity.getTop();
        if(top==0){
            //置顶
            if(topImage!=null && topImage!=""){
                syEnterpriseEntity.setTopImage(topImage);
            }
            syEnterpriseEntity.setTop(1);
        }else {
            //取消置顶
            syEnterpriseEntity.setTop(0);
        }
        syEnterpriseEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        syEnterpriseEntity.setCreateTime(syEnterpriseEntity.getUpdateTime());
        enterpriseResponsitory.saveAndFlush(syEnterpriseEntity);
    }

    @Override
    public Class getJpaRepositoryClazz() {
        return EnterpriseResponsitory.class;
    }
}
