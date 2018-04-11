package com.cdyoue.oddJobs.service.sydb.impl;

import com.cdyoue.oddJobs.dao.syData.CooperativePartnerResponsitory;
import com.cdyoue.oddJobs.dto.SyCooperativePartnerDTO;
import com.cdyoue.oddJobs.dto.SyCooperativePartnerMiniDTO;
import com.cdyoue.oddJobs.entity.syData.SyCooperativePartnerEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.PortalIntellectualsMapper;
import com.cdyoue.oddJobs.service.support.ServiceSupport;
import com.cdyoue.oddJobs.service.sydb.CooperativePartnerService;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.oddJobs.utils.UserUtils;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Tanhao on 2017/9/26.
 */
@Service
@Transactional
public class CooperativePartnerServiceImpl  extends ServiceSupport<SyCooperativePartnerEntity> implements CooperativePartnerService {
    @Autowired
    private CooperativePartnerResponsitory partnerResponsitory;
    @Autowired
    private PortalIntellectualsMapper portalIntellectualsMapper;
    @Override
    public Class getJpaRepositoryClazz() {
        return CooperativePartnerResponsitory.class;
    }

    @Override
    public List<SyCooperativePartnerDTO> getPartner() {
        List<SyCooperativePartnerEntity> pars = partnerResponsitory.findAll();
        if(pars == null || pars.size()<1){
            throw new NotFoundEntityException("数据不存在");
        }
        List<SyCooperativePartnerDTO> partners = new ArrayList<>();
        for(int i = 0;i < pars.size();i++){
            SyCooperativePartnerDTO dto = new SyCooperativePartnerDTO();
            BeanUtils.copyProperties(pars.get(i),dto);
            partners.add(dto);
        }
        return partners;
    }

    @Override
    public void insertPartner(SyCooperativePartnerMiniDTO miniDTO) {
        SyCooperativePartnerEntity entity = new SyCooperativePartnerEntity();
        BeanUtils.copyProperties(miniDTO,entity);
        entity.setCreatBy(SecurityUtils.getCurrentUserLogin().getId());
        entity.setViewCount(0);
        entity.setPublishTime( new Date(System.currentTimeMillis()));
        entity.setUpdateTime( new Date(System.currentTimeMillis()));
        partnerResponsitory.save(entity);
    }

    @Override
    public PageInfo<SyCooperativePartnerDTO> getPagePartners(String q, Pageable requestPage) {
        Page<SyCooperativePartnerEntity>  rpPage = super.findByName(q, requestPage);
        List<SyCooperativePartnerDTO> IntellectualSummaries = rpPage.getContent().stream().map(p -> portalIntellectualsMapper.EntityToDTO(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(IntellectualSummaries, requestPage, rpPage.getTotalElements()));
    }

    @Override
    public void deletePartner(Integer id) {
        SyCooperativePartnerEntity entity = partnerResponsitory.findOne(id);
        if(entity == null){
            throw new NotFoundEntityException("数据不存在");
        }else {
            partnerResponsitory.delete(id);
        }
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        for(Integer id:ids){
            SyCooperativePartnerEntity entity = partnerResponsitory.findOne(id);
            if(entity == null){
                throw new NotFoundEntityException("数据不存在");
            }else {
                partnerResponsitory.delete(id);
            }
        }
    }

    @Override
    public void updatePartner(Integer id,SyCooperativePartnerMiniDTO miniDTO) {
        SyCooperativePartnerEntity entity = partnerResponsitory.findOne(id);
        if(entity == null){
            throw new NotFoundEntityException("数据不存在");
        }else {
            if(miniDTO!=null){
                if(miniDTO.getName()!=null && miniDTO.getName().length()>0){
                    entity.setName(miniDTO.getName());
                }if(miniDTO.getUrl()!=null && miniDTO.getUrl().length()>0){
                    entity.setUrl(miniDTO.getUrl());
                }if(miniDTO.getLogoImg()!=null && miniDTO.getLogoImg().length()>0){
                    entity.setLogoImg(miniDTO.getLogoImg());
                }
                entity.setUpdateTime(new Date(System.currentTimeMillis()));
                partnerResponsitory.saveAndFlush(entity);
            }
        }
    }

    @Override
    public SyCooperativePartnerDTO getPartnerDetail(Integer id) {
        SyCooperativePartnerEntity entity = partnerResponsitory.findOne(id);
        if(entity == null){
            throw new NotFoundEntityException("数据不存在");
        }else {
            SyCooperativePartnerDTO dto = new SyCooperativePartnerDTO();
            BeanUtils.copyProperties(entity,dto);
            entity.setViewCount(entity.getViewCount()+1);
            partnerResponsitory.saveAndFlush(entity);
            dto.setCreatBy(UserUtils.getUserName(entity.getCreatBy()));
            return dto;
        }
    }
}
