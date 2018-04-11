package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dao.lqsq.PortalNewsResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.UserenterpriseResponsitory;
import com.cdyoue.oddJobs.dto.common.KeyValue;
import com.cdyoue.oddJobs.dto.ggfw.CommunitySummary;
import com.cdyoue.oddJobs.dto.ggfw.NewsDetail;
import com.cdyoue.oddJobs.dto.ggfw.NewsRequest;
import com.cdyoue.oddJobs.dto.ggfw.NewsSummary;
import com.cdyoue.oddJobs.entity.lgsq.PortalNewsEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserenterpriseEntity;
import com.cdyoue.oddJobs.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by John on 2017/5/9.
 */
@Component
public class PortalNewsMapper {
    @Autowired
    private PortalNewsResponsitory portalNewsResponsitory;

    @Autowired
    private UserenterpriseResponsitory  userenterpriseEntity;

    public CommunitySummary entityToDto(PortalNewsEntity entity) {
        CommunitySummary dto = new CommunitySummary();
        DateFormat dtFormatter = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
        String creatName = UserUtils.getUserName(entity.getCreateBy());
        dto.setCreateBy(creatName);
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setSummary(entity.getSummary());
        dto.setCoverImg(entity.getCoverImg());
        dto.setPublishTime(dtFormatter.format(entity.getPublishTime()));
        dto.setType(1);
        dto.setViewCount(entity.getViewsCount());
        dto.setApproveStatus(entity.getReviewStatus().intValue());
        UserenterpriseEntity userenterprise = userenterpriseEntity.findOneByUid(entity.getCreateBy());  //database has set UserId as UNIQUE
        if (userenterprise != null ) {
            KeyValue unitinfo = new KeyValue();
            unitinfo.setName("unit");
            unitinfo.setValue(userenterprise.getName());
            dto.addAddPropertiesItem(unitinfo);
        }
        dto.setLink("/H5/newsDetails.html?id=" + entity.getId());
        return dto;
    }

    public NewsDetail newsEntityToNewsDetail(PortalNewsEntity portalNewsEntity) {
        NewsDetail newsDetail = new NewsDetail();
        NewsRequest newsRequest = new NewsRequest();
        newsRequest.setTitle(portalNewsEntity.getTitle());
        newsRequest.setAbstract(portalNewsEntity.getSummary());
        newsRequest.setContent(portalNewsEntity.getIntroduction());
        newsRequest.setCoverImgUrl(portalNewsEntity.getCoverImg());
        newsDetail.setNewsBase(newsRequest);
        // 根据企业用户id获取企业名称
        newsDetail.setInfoSource(userenterpriseEntity.findOneByUid(portalNewsEntity.getCreateBy()).getName());
        // 发布时间格式化为字符串
        newsDetail.setPublishTime(new SimpleDateFormat("yyyy/MM/dd").format(portalNewsEntity.getPublishTime()));
        newsDetail.setViewCount(portalNewsEntity.getViewsCount());
        return newsDetail;
    }

    public NewsSummary newsEntityToNewsSummary(PortalNewsEntity portalNewsEntity) {
        NewsSummary newsSummary = new NewsSummary();
        newsSummary.setId(portalNewsEntity.getId());
        newsSummary.setTitle(portalNewsEntity.getTitle());
        newsSummary.setCoverImg(portalNewsEntity.getCoverImg());
        newsSummary.setIntroduction(portalNewsEntity.getSummary());
        newsSummary.setApproveStatus(portalNewsEntity.getReviewStatus().intValue());
        // 根据企业用户id获取企业名称
        newsSummary.setPublishCompany(userenterpriseEntity.findOneByUid(portalNewsEntity.getCreateBy()).getName());
        // 发布时间格式化为字符串
        newsSummary.setPublishTime(new SimpleDateFormat("yyyy/MM/dd").format(portalNewsEntity.getPublishTime()));
        return newsSummary;
    }
}
