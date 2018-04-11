package com.cdyoue.oddJobs.mapper;


import com.cdyoue.oddJobs.dao.lqsq.PortalAreaServiceResponsitory;
import com.cdyoue.oddJobs.dto.scfw.SpaceDetail;
import com.cdyoue.oddJobs.dto.scfw.SpaceSummary;
import com.cdyoue.oddJobs.entity.lgsq.PortalAreaServiceEntity;
import com.cdyoue.oddJobs.service.AreaService;
import com.cdyoue.oddJobs.service.UserEnterpriseService;
import com.cdyoue.oddJobs.service.UserService;
import com.cdyoue.oddJobs.service.UserpersonalService;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.oddJobs.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by sky on 2017/5/22.
 */
@Component
public class AreaServiceMapper {
    @Autowired
    private UserService userService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private PortalAreaServiceResponsitory portalAreaServiceResponsitory;
    @Autowired
    private UserpersonalService userpersonalService;
    @Autowired
    private UserEnterpriseService userEnterpriseService;

    public SpaceSummary portalAreaServiceEntityToSpaceSummary(PortalAreaServiceEntity portalAreaServiceEntity){
        SpaceSummary spaceSummary=new SpaceSummary();
        spaceSummary.setId(portalAreaServiceEntity.getId());
        spaceSummary.setCoverImgUrl(portalAreaServiceEntity.getCoverImg());//图片
        spaceSummary.setTitle(portalAreaServiceEntity.getName());//名称
        spaceSummary.setNumber(portalAreaServiceEntity.getSize());//个数
        spaceSummary.setRent(portalAreaServiceEntity.getPrice().toString());//单价
        spaceSummary.setType(portalAreaServiceEntity.getAreaType());//出租类型，场地或者是工位
        spaceSummary.setIntroduction(portalAreaServiceEntity.getIntroduction());//介绍
        spaceSummary.setAddress(portalAreaServiceEntity.getAddress());//地址
        spaceSummary.setPublishPepole(UserUtils.getUserName(portalAreaServiceEntity.getCreateBy()));
        spaceSummary.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(portalAreaServiceEntity.getCreateTime()));//创建时间
        spaceSummary.setReviewStatus(portalAreaServiceEntity.getReviewStatus());//审核状态
        spaceSummary.setViewCount(portalAreaServiceEntity.getViewCount());//阅读量
        spaceSummary.setLink("/H5/spacerentDetails.html?id=" + portalAreaServiceEntity.getId() + "&type=" + portalAreaServiceEntity.getAreaType());
        return spaceSummary;
    }

    public SpaceSummary portalAreaServiceEntityToSpaceSummaryAdmin(PortalAreaServiceEntity portalAreaServiceEntity){
        SpaceSummary spaceSummary=portalAreaServiceEntityToSpaceSummary(portalAreaServiceEntity);
        spaceSummary.setTop(portalAreaServiceEntity.getTop());
        spaceSummary.setTopImg(portalAreaServiceEntity.getTopImg());
        spaceSummary.setLink("/H5/spacerentDetails.html?id=" + portalAreaServiceEntity.getId() + "&type=" + portalAreaServiceEntity.getAreaType());
        return spaceSummary;
    }

    public SpaceDetail portalAreaServiceEntityToSpaceDetail(PortalAreaServiceEntity portalAreaServiceEntity){
        SpaceDetail spaceDetail=new SpaceDetail();
        spaceDetail.setTitle(portalAreaServiceEntity.getName());//名称
        spaceDetail.setArea(portalAreaServiceEntity.getAreaIdLvPre());//省级地域
        spaceDetail.setAreaNext(portalAreaServiceEntity.getAreaIdLvNext());//市级地域
        spaceDetail.setAddress(portalAreaServiceEntity.getAddress());//详细地址
        spaceDetail.setContact(portalAreaServiceEntity.getTel());//联系电话
        spaceDetail.setContactor(portalAreaServiceEntity.getContactor());//联系人
        spaceDetail.setCoverImgUrl(portalAreaServiceEntity.getCoverImg());//图片
        spaceDetail.setIntroduction(portalAreaServiceEntity.getIntroduction());//介绍
        spaceDetail.setNumber(portalAreaServiceEntity.getSize());//数量
        spaceDetail.setRent(portalAreaServiceEntity.getPrice().toString());//单价
        spaceDetail.setType(portalAreaServiceEntity.getAreaType());//类型
        return spaceDetail;
    }
    public PortalAreaServiceEntity spaceDetailToPortalAreaServiceEntity(SpaceDetail spaceDetail){
        PortalAreaServiceEntity portalAreaServiceEntity=new PortalAreaServiceEntity();
        portalAreaServiceEntity.setName(spaceDetail.getTitle());//名称
        portalAreaServiceEntity.setCoverImg(spaceDetail.getCoverImgUrl());//图片
        portalAreaServiceEntity.setIntroduction(spaceDetail.getIntroduction());//详情介绍
        portalAreaServiceEntity.setPrice(Double.valueOf(spaceDetail.getRent()));//单价
        portalAreaServiceEntity.setSize(spaceDetail.getNumber());//数量
        portalAreaServiceEntity.setCreateBy(SecurityUtils.getCurrentUserLogin().getId());//创建人ID
        portalAreaServiceEntity.setTel(spaceDetail.getContact());//联系电话
        portalAreaServiceEntity.setContactor(spaceDetail.getContactor());//联系人
        portalAreaServiceEntity.setAddress(spaceDetail.getAddress());//详细地址
        portalAreaServiceEntity.setAreaType(spaceDetail.getType());//类型
        portalAreaServiceEntity.setAreaIdLvPre(spaceDetail.getArea());//省级区域ID
        portalAreaServiceEntity.setAreaIdLvNext(spaceDetail.getAreaNext());//市级区域ID
        portalAreaServiceEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));//创建时间
        portalAreaServiceEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));//创建时间
        portalAreaServiceEntity.setViewCount(0);//浏览量，默认为0
        portalAreaServiceEntity.setReviewStatus(1);//审核状态，默认审核为1
        return portalAreaServiceEntity;
    }
    public PortalAreaServiceEntity spaceDetailToPortalAreaServiceEntityUpdate(Integer id,SpaceDetail spaceDetail){
        PortalAreaServiceEntity portalAreaServiceEntity=portalAreaServiceResponsitory.findOne(id);
        portalAreaServiceEntity.setName(spaceDetail.getTitle());//名称
        portalAreaServiceEntity.setCoverImg(spaceDetail.getCoverImgUrl());//图片
        portalAreaServiceEntity.setIntroduction(spaceDetail.getIntroduction());//详情介绍
        portalAreaServiceEntity.setPrice(Double.valueOf(spaceDetail.getRent()));//单价
        portalAreaServiceEntity.setSize(spaceDetail.getNumber());//数量
        portalAreaServiceEntity.setTel(spaceDetail.getContact());//联系电话
        portalAreaServiceEntity.setContactor(spaceDetail.getContactor());//联系人
        portalAreaServiceEntity.setAddress(spaceDetail.getAddress());//详细地址
        portalAreaServiceEntity.setAreaType(spaceDetail.getType());//类型
        portalAreaServiceEntity.setAreaIdLvPre(spaceDetail.getArea());//省级区域ID
        portalAreaServiceEntity.setAreaIdLvNext(spaceDetail.getAreaNext());//市级区域ID
        portalAreaServiceEntity.setViewCount(0);//浏览量，默认为0
        portalAreaServiceEntity.setReviewStatus(1);//审核状态，默认审核为1
//        portalAreaServiceEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));//修改时间
        portalAreaServiceEntity.setUpdateBy(SecurityUtils.getCurrentUserLogin().getId());//修改人ID
        return portalAreaServiceEntity;
    }
}
