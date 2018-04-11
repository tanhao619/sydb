package com.cdyoue.oddJobs.mapper.sydb;

import com.cdyoue.oddJobs.dao.lqsq.UserpersonalResponsitory;
import com.cdyoue.oddJobs.dto.zlcy.WebsiteRequest;
import com.cdyoue.oddJobs.dto.zlcy.WebsiteSummary;
import com.cdyoue.oddJobs.entity.lgsq.user.UserpersonalEntity;
import com.cdyoue.oddJobs.entity.syData.SyWebsiteEntity;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by dengshaojun on 2017/10/11.
 */
@Component
public class WebsiteMapper {

    @Autowired
    private UserpersonalResponsitory userpersonalResponsitory;

    public WebsiteSummary entityToSummary(SyWebsiteEntity entity) {
        WebsiteSummary summary = new WebsiteSummary();
        summary.setId(entity.getId());
        summary.setName(entity.getName());
        summary.setContent(entity.getContent());
        UserpersonalEntity userpersonalEntity = userpersonalResponsitory.findOneByUid(entity.getUserId());
        if (userpersonalEntity != null) {
            summary.setPublisher(userpersonalEntity.getName());
        }
        summary.setCreateTime(entity.getCreateTime().getTime());
        return summary;
    }

    public SyWebsiteEntity requestToEntity(WebsiteRequest request) {
        SyWebsiteEntity entity = new SyWebsiteEntity();
        entity.setName(request.getName());
        entity.setContent(request.getContent());
        entity.setUserId(SecurityUtils.getCurrentUserLogin().getId());
        entity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return entity;
    }

    public SyWebsiteEntity requestToEntity(WebsiteRequest request, SyWebsiteEntity entity) {
        entity.setName(request.getName());
        entity.setContent(request.getContent());
        return entity;
    }
}
