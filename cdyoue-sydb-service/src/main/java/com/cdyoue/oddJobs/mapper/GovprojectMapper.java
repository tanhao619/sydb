package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dao.lqsq.PortalAttachmentResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.UserpersonalResponsitory;
import com.cdyoue.oddJobs.dto.ggfw.Govproject;
import com.cdyoue.oddJobs.dto.ggfw.GovprojectSummary;
import com.cdyoue.oddJobs.entity.lgsq.PortalGovprojectReportEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserpersonalEntity;
import com.cdyoue.oddJobs.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * Created by tr on 2017/5/22.
 */

@Component
public class GovprojectMapper {
    @Autowired
    private PortalAttachmentResponsitory portalAttachmentResponsitory;

    @Autowired
    private UserpersonalResponsitory userpersonalResponsitory;
    /**
     * PortalGovprojectEntity转GovprojectSummary
     * @param ppr
     * @return govprojectSummary
     */
    public GovprojectSummary projectReportEntityToGovprojectSummary(PortalGovprojectReportEntity ppr) {
        GovprojectSummary govprojectSummary = new GovprojectSummary();
        govprojectSummary.setId(ppr.getId());
        if (ppr.getPublishTime() != null) govprojectSummary.setPublishTime(ppr.getPublishTime().toString());
        govprojectSummary.setTitle(ppr.getTitle());
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        govprojectSummary.setDuration(ppr.getDuration());
        //项目附件url，根据referId和sourceType查询attachment表
        String url = portalAttachmentResponsitory.findUrlByReferId(ppr.getId(), (byte)3);
        govprojectSummary.setAttachUrl(url);
        govprojectSummary.setLink("/H5/projectdeclarationDetails.html?id=" + ppr.getId());
        UserpersonalEntity upe = null;
        //查询userpersonal表查询对应的用户姓名
        try {
            upe = userpersonalResponsitory.findByUid(ppr.getCreateBy().getId());
            govprojectSummary.setCreateBy(upe.getName());
        } catch (Exception e){
            govprojectSummary.setCreateBy(null);
        }

        govprojectSummary.setViewscount(ppr.getViewCount());
        return govprojectSummary;
    }

    public PortalGovprojectReportEntity govprojectToPortalProjectReportEntity(Govproject govproject){
        PortalGovprojectReportEntity ppr = new PortalGovprojectReportEntity();
        ppr.setTitle(govproject.getGovprojectSummary().getTitle());
        ppr.setDuration(govproject.getGovprojectSummary().getDuration());
        ppr.setSourceFrom(govproject.getInforSource());
        ppr.setCode(govproject.getFileNO());
        ppr.setIntroduction(govproject.getIntroduction());
        return ppr;
    }

    public Govproject ProjectReportEntityToGovproject(PortalGovprojectReportEntity ppr){
        Govproject govproject = new Govproject();
        govproject.setIntroduction(ppr.getIntroduction());
        govproject.setFileNO(ppr.getCode());
        govproject.setInforSource(ppr.getSourceFrom());
        GovprojectSummary govprojectSummary = new GovprojectSummary();
        //项目附件url，根据referId和sourceType查询attachment表
        String url = portalAttachmentResponsitory.findUrlByReferId(ppr.getId(), (byte)3);
        govprojectSummary.setAttachUrl(url);
        govprojectSummary.setTitle(ppr.getTitle());
        govprojectSummary.setDuration(ppr.getDuration());
        govprojectSummary.setCreateBy(UserUtils.getUserName(ppr.getCreateBy().getId()));
        if (ppr.getPublishTime() != null)govprojectSummary.setPublishTime(ppr.getPublishTime().toString());
        govproject.setGovprojectSummary(govprojectSummary);
        return govproject;
    }
}
