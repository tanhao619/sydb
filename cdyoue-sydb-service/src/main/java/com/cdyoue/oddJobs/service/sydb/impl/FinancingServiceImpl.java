package com.cdyoue.oddJobs.service.sydb.impl;

import com.cdyoue.oddJobs.dao.syData.FinancingResponsitory;
import com.cdyoue.oddJobs.dto.zlcx.FinancingDTO.FinancingTop;
import com.cdyoue.oddJobs.entity.syData.EquipmentEntity;
import com.cdyoue.oddJobs.entity.syData.FinancingEntity;
import com.cdyoue.oddJobs.mapper.sydb.FinancingMapper;
import com.cdyoue.oddJobs.service.support.ServiceSupport;
import com.cdyoue.oddJobs.service.sydb.FinancingService;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2017/9/20.
 */
@Service
public class FinancingServiceImpl extends ServiceSupport<FinancingEntity> implements FinancingService {
    @Autowired
    FinancingResponsitory financingResponsitory;
    @Autowired
    FinancingMapper financingMapper;

    @Value("${default.commonUpload.server}")
    private String serverPath;

    @Override
    public Class getJpaRepositoryClazz() {
        return FinancingResponsitory.class;
    }

    /**
     * 获取top
     * @return
     */
    @Override
    public FinancingTop getFinancingTop() {
        List<FinancingEntity> financingEntities = financingResponsitory.getFinancingTop();
        FinancingTop financingTop = financingMapper.entityToTop(financingEntities.get(0));
        return financingTop;
    }

    /**
     * 获取融资项目分页
     * @param pr
     * @return
     */
    @Override
    public PageInfo<FinancingTop> getFinancingPageList(PageRequest pr,Integer type,String q) {
        Page<FinancingEntity> rpPage = super.findByThreeLike("status",type+"","name",q,"summary",q,pr);
        List<FinancingTop> equipmentTops = new ArrayList<>();
        if(rpPage.getContent().size() != 0){
            equipmentTops = rpPage.getContent().stream().map(p -> financingMapper.entityToTop(p)).collect(Collectors.toList());
        }
        return new PageInfo<>(new PageImpl(equipmentTops, pr, rpPage.getTotalElements()));
    }

    /**
     * 获取融资后台分页
     * @param pr
     * @param q
     * @return
     */
    @Override
    public PageInfo<FinancingTop> getFinancingBackPageList(PageRequest pr, String q,String status) {
        Page<FinancingEntity> rpPage = super.findByTwoLike("name",q,"status",status,pr);
        List<FinancingTop> equipmentTops = new ArrayList<>();
        if(rpPage.getContent().size() != 0){
            equipmentTops = rpPage.getContent().stream().map(p -> financingMapper.entityToTop(p)).collect(Collectors.toList());
        }
        return new PageInfo<>(new PageImpl(equipmentTops, pr, rpPage.getTotalElements()));
    }

    /**
     * 获取融资项目详情
     * @param id
     * @return
     */
    @Override
    public FinancingEntity getFinancingById(Integer id) {
        FinancingEntity financingEntity = financingResponsitory.getFinancingById(id);
        if(financingEntity.getViewCount() == 0){
            financingEntity.setViewCount(1);
        }else {
            financingEntity.setViewCount(financingEntity.getViewCount()+1);
        }
        financingResponsitory.saveAndFlush(financingEntity);
        financingEntity.setFile(serverPath+financingEntity.getFile());
        return financingEntity;
    }

    /**
     * 审核项目
     * @param id
     * @param status
     */
    @Override
    public void uncollectFinancing(Integer id, Integer status) {
        FinancingEntity financingEntity = financingResponsitory.getFinancingById(id);
        financingEntity.setStatus(status);
        financingResponsitory.saveAndFlush(financingEntity);
    }

    /**
     * 顶置
     */
    @Override
    public void madeTop(FinancingEntity financingEntity) {
        FinancingEntity entity = financingResponsitory.getFinancingById(financingEntity.getId());
        entity.setTop(1);
        entity.setUpdateTime(new Date());
        entity.setUpdateBy(SecurityUtils.getCurrentUserLogin().getId());
        if(financingEntity.getTopImg()!= null){
            entity.setTopImg(financingEntity.getTopImg());
        }
        financingResponsitory.saveAndFlush(entity);
    }

    /**
     * 取消顶置
     */
    @Override
    public void cancelTop(Integer id) {
        FinancingEntity entity = financingResponsitory.getFinancingById(id);
        entity.setTop(0);
        entity.setUpdateBy(SecurityUtils.getCurrentUserLogin().getId());
        entity.setUpdateTime(new Date());
        financingResponsitory.saveAndFlush(entity);
    }

    /**
     * 删除融资
     * @param id
     */
    @Override
    public void deleteFinancing(List<Integer> id) {
        for (int i = 0;i<id.size();i++){
            financingResponsitory.delete(id.get(i));
        }
    }

    /**
     * 发布融资需求
     * @param financingEntity
     */
    @Override
    public void createFinancing(FinancingEntity financingEntity) {
        financingEntity.setCreateBy(SecurityUtils.getCurrentUserLogin().getId());
        financingEntity.setCreateTime(new Date());
        financingResponsitory.save(financingEntity);
    }

    /**
     * 获取我的融资列表
     * @param pr
     * @param id
     * @return
     */
    @Override
    public PageInfo<FinancingTop> getMyFinancingPageList(PageRequest pr, Integer id) {
        id= SecurityUtils.getCurrentUserLogin().getId();
        Page<FinancingEntity> rpPage = super.getMyFinancingPageList("createBy",id+"",pr);
        List<FinancingTop> equipmentTops = new ArrayList<>();
        if(rpPage.getContent().size() != 0){
            equipmentTops = rpPage.getContent().stream().map(p -> financingMapper.entityToTop(p)).collect(Collectors.toList());
        }
        return new PageInfo<>(new PageImpl(equipmentTops, pr, rpPage.getTotalElements()));
    }

    /**
     * 融资审核
     * @param financingEntity
     */
    @Override
    public void toExamine(FinancingEntity financingEntity) {
        FinancingEntity entity = financingResponsitory.getFinancingById(financingEntity.getId());
        entity.setUpdateBy(SecurityUtils.getCurrentUserLogin().getId());
        entity.setUpdateTime(new Date());
        entity.setStatus(financingEntity.getStatus());
        entity.setRemarks(financingEntity.getRemarks());
        financingResponsitory.save(entity);
    }
}
