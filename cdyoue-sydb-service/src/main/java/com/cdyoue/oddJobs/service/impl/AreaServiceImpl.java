package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.PortalAreaResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.PortalAreaServiceResponsitory;
import com.cdyoue.oddJobs.dto.AreaBanner;
import com.cdyoue.oddJobs.dto.AreaDTO;
import com.cdyoue.oddJobs.dto.Reason;
import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.scfw.SpaceDetail;
import com.cdyoue.oddJobs.dto.scfw.SpaceSummary;
import com.cdyoue.oddJobs.dto.zscq.IntellectualTop;
import com.cdyoue.oddJobs.en.MessageEventTypeEnum;
import com.cdyoue.oddJobs.en.MessageMsgTypeEnum;
import com.cdyoue.oddJobs.entity.lgsq.PortalAreaServiceEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.AreaMapper;
import com.cdyoue.oddJobs.mapper.AreaServiceMapper;
import com.cdyoue.oddJobs.service.AreaService;
import com.cdyoue.oddJobs.service.support.ServiceSupport;
import com.cdyoue.oddJobs.utils.MessageUtils;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2017/5/11.
 */
@Service
@Transactional
public class AreaServiceImpl extends ServiceSupport<PortalAreaServiceEntity> implements AreaService {

    @Autowired
    PortalAreaResponsitory portalAreaResponsitory;
    @Autowired
    PortalAreaServiceResponsitory portalAreaServiceResponsitory;
    @Autowired
    AreaMapper areaMapper;
    @Autowired
    AreaServiceMapper areaServiceMapper;

    @Override
    public List<AreaDTO> getArea() {
        List<AreaDTO> areaDTOs = new ArrayList<>();//DTOlist
        Map<Integer,List<String>> area = new HashMap<Integer,List<String>>();//一级地区Level,与下属二级地区名称map
        List<Integer> fristAreaLevels = portalAreaResponsitory.findFristIdLevel();//一级地区LevelList
        for (Integer i:fristAreaLevels) {
            List<String>secondLevels  = portalAreaResponsitory.findSecondName(i);//二级地区NameList
            area.put(i,secondLevels);
        }
        areaDTOs = areaMapper.entityMapToDtoList(fristAreaLevels,area);//将entityMap转换成DTOList
        return areaDTOs;
}

    @Override
    public String getAreaName(Integer id) {
        return portalAreaResponsitory.findNameById(id);
    }

    //空间列表
    @Override
    public PageInfo<SpaceSummary> getSpaceSummaryPage(Integer type,String q,Integer areaIdLvPre,Integer areaIdLvNext,Integer reviewStatus,Integer userId,Pageable requestPage) {

        Page<PortalAreaServiceEntity> page=super.findSpace("areaType",type,"name",q,"areaIdLvPre",areaIdLvPre,"areaIdLvNext",areaIdLvNext,"reviewStatus",reviewStatus,"createBy",userId,requestPage);
        List<SpaceSummary> list;
        UserMine userMine= SecurityUtils.getCurrentUserLogin();
        if(userMine!=null&&userMine.getRole()==2){//超级管理员能查询更多字段
             list=page.getContent().stream().map(p -> areaServiceMapper.portalAreaServiceEntityToSpaceSummaryAdmin(p)).collect(Collectors.toList());
        }else {
            list=page.getContent().stream().map(p -> areaServiceMapper.portalAreaServiceEntityToSpaceSummary(p)).collect(Collectors.toList());
        }
        PageInfo<SpaceSummary> pageInfo=new PageInfo<>(new PageImpl(list, requestPage, page.getTotalElements()));
        return pageInfo;
    }

    //空间详情
    @Override
    public SpaceDetail getSpaceDetail(Integer id) {
        PortalAreaServiceEntity portalAreaServiceEntity=portalAreaServiceResponsitory.findOne(id);
        if(portalAreaServiceEntity!=null) {
            SpaceDetail spaceDetail = areaServiceMapper.portalAreaServiceEntityToSpaceDetail(portalAreaServiceEntity);
            portalAreaServiceResponsitory.addViewCount(id);
            return spaceDetail;
        }
        return null;
    }
//删除空间
    @Override
    public void deleteSpace(Integer id) {
        //删除之前先查询是否存在
        PortalAreaServiceEntity portalAreaServiceEntity=portalAreaServiceResponsitory.findOne(id);
        if(portalAreaServiceEntity!=null){
            portalAreaServiceResponsitory.delete(id);
        }
    }
//发布空间
    @Override
    public void save(SpaceDetail space) {
        PortalAreaServiceEntity portalAreaServiceEntity=areaServiceMapper.spaceDetailToPortalAreaServiceEntity(space);
        portalAreaServiceResponsitory.save(portalAreaServiceEntity);
    }

    @Override
    public Integer getIdBySpaceName(String spaceName) {
        return portalAreaResponsitory.findIdByName(spaceName);
    }

    //更新空间
    @Override
    public void update(Integer id,SpaceDetail space) {
        PortalAreaServiceEntity portalAreaServiceEntity=areaServiceMapper.spaceDetailToPortalAreaServiceEntityUpdate(id,space);
        portalAreaServiceResponsitory.saveAndFlush(portalAreaServiceEntity);
    }

    @Override
    public PortalAreaServiceEntity findOne(Integer id) {
        return portalAreaServiceResponsitory.findOne(id);
    }

    //审核空间
    @Override
    @Transactional
    public void reviewArea(Integer id, Integer status, Reason reason) {
        PortalAreaServiceEntity portalAreaServiceEntity = portalAreaServiceResponsitory.findOne(id);
        portalAreaServiceEntity.setReviewStatus(status);
        portalAreaServiceEntity.setReviewReason(reason.getReason());
        portalAreaServiceEntity.setReviewTime(new Timestamp(System.currentTimeMillis()));
        // 审核人员（后台登录的管理员id），怎么获取
        portalAreaServiceEntity.setReviewUserId(SecurityUtils.getCurrentUserLogin().getId());
        portalAreaServiceResponsitory.save(portalAreaServiceEntity);
        Boolean isReview = MessageUtils.isMessageExist(id, MessageEventTypeEnum.AUDIT, MessageMsgTypeEnum.AuditPlace);
        if (!isReview) {
            MessageUtils.createAuditMessage(portalAreaServiceEntity.getCreateBy(), portalAreaServiceEntity.getId(), MessageEventTypeEnum.AUDIT, MessageMsgTypeEnum.AuditPlace,status+"");
        }
    }

    @Override
    public Class getJpaRepositoryClazz() {
        return PortalAreaServiceResponsitory.class;
    }

    @Override
    public boolean deleteAuthority(Integer userId, Integer id) {
        PortalAreaServiceEntity portalAreaServiceEntity=portalAreaServiceResponsitory.findOne(id);
        if (portalAreaServiceEntity != null && portalAreaServiceEntity.getCreateBy().intValue() == userId){
            return true;
        }
        return false;
    }

    @Override
    public List<AreaBanner> getAreaBanners() {
        List<AreaBanner> banners = new ArrayList<>();
        Object[] list = portalAreaServiceResponsitory.getAreaBanners();
        if (list == null || list.length < 1) {
            throw new NotFoundEntityException("数据不存在");
        }else {
            for (int i = 0;i < list.length;i++){
                Object[] cel = (Object[]) list[i];
                AreaBanner banner = new AreaBanner();
                banner.setId((Integer) cel[0]);
                banner.setPrice(String.valueOf(cel[2]));
                banner.setName(String.valueOf(cel[1]));
                banner.setTopImg(String.valueOf(cel[3]));
                banner.setAddress(String.valueOf(cel[4]));
                banners.add(banner);
            }
        }
        return banners;
    }

    @Override
    public void deleteAllSpaces(Integer[] ids) {
        for(Integer id:ids){
            PortalAreaServiceEntity entity = portalAreaServiceResponsitory.findOne(id);
            if(entity == null){
                throw new NotFoundEntityException("数据不存在");
            }else {
                portalAreaServiceResponsitory.delete(id);
            }
        }
    }

    @Override
    public void siteTop(IntellectualTop top) {
        PortalAreaServiceEntity pe = portalAreaServiceResponsitory.findOne(top.getId());
        if (pe!=null){
            pe.setTop(1);
            pe.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            if(top.getTopImg().length()>0 && top.getTopImg()!=null){
                pe.setTopImg(top.getTopImg());
            }
            portalAreaServiceResponsitory.save(pe);
        }else {
            throw new NotFoundEntityException("数据不存在");
        }
    }

    @Override
    public void removeSiteTop(Integer id) {
        PortalAreaServiceEntity pe = portalAreaServiceResponsitory.findOne(id);
        if (pe!=null){
            pe.setTop(0);
            portalAreaServiceResponsitory.save(pe);
        }else {
            throw new NotFoundEntityException("数据不存在");
        }
    }
}
