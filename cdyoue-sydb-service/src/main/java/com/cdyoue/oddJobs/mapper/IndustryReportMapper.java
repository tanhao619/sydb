package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dao.lqsq.UserpersonalResponsitory;
import com.cdyoue.oddJobs.dto.zscq.IndustryReportDetail;
import com.cdyoue.oddJobs.dto.zscq.IndustryReportSummary;
import com.cdyoue.oddJobs.entity.lgsq.PortalIndustryReportsEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserpersonalEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by tr on 2017/5/24.
 */

@Component
public class IndustryReportMapper {
    @Autowired
    private UserpersonalResponsitory userpersonalResponsitory;

    public IndustryReportSummary industryReportEntityToSummary(PortalIndustryReportsEntity pir){
        IndustryReportSummary irs = new IndustryReportSummary();
        irs.setId(pir.getId());
        irs.setCoverImgUrl(pir.getCoverImg());
        irs.setName(pir.getName());
        irs.setLink("/H5/patentreportDetails.html?id=" + pir.getId());
        //获取用户个人信息表中用户姓名
        try {
            UserpersonalEntity upe = userpersonalResponsitory.findByUid(pir.getCreateBy().getId());
            irs.setPublishPepole(upe.getName());
        } catch (Exception e){
            irs.setPublishPepole(null);
        }
        irs.setViewsCount(pir.getViewCount());
        if (pir.getPublishTime() != null) irs.setPublishTime(pir.getPublishTime().toString());
        return irs;
    }

    public IndustryReportDetail industryReportEntityToDetail(PortalIndustryReportsEntity p) {
        IndustryReportDetail ird = new IndustryReportDetail();
        ird.setName(p.getName());
        ird.setCoverImgUrl(p.getCoverImg());
        ird.setIntroduction(p.getInfo());
        ird.setContent(p.getIntroduction());
        if (p.getPublishTime() != null) ird.setPublishTime(p.getPublishTime().toString());
        ird.setViewCount(p.getViewCount());
        return ird;
    }
}
