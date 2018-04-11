package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dto.zscq.PatentBigdataPriceComp;
import com.cdyoue.oddJobs.dto.zscq.PatentBigdataSummary;
import com.cdyoue.oddJobs.dto.zscq.PatentNationDetail;
import com.cdyoue.oddJobs.dto.zscq.PatentNationSummary;
import com.cdyoue.oddJobs.entity.lgsq.PatentEntity;
import com.cdyoue.oddJobs.entity.lgsq.PortalPatentDataEntity;
import com.cdyoue.oddJobs.entity.lgsq.PortalPricesInfoEntity;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dengshaojun on 2017/5/22.
 */
@Component
public class PatentMapper {

    /**
     * PatentEntity转PatentNationSummary
     *
     * @param patentEntity
     * @return patentNationSummary
     */
    public PatentNationSummary patentEntityToPatentNationSummary(PatentEntity patentEntity) {
        PatentNationSummary patentNationSummary = new PatentNationSummary();
        patentNationSummary.setId(patentEntity.getId());
        patentNationSummary.setName(patentEntity.getName());
        patentNationSummary.setApplicationNO(patentEntity.getRequestNo());
        patentNationSummary.setPublicNO(patentEntity.getPatentNo());
        if (patentEntity.getRequestTime() != null) {
            try {
                Date date = new SimpleDateFormat("yyyyMMdd").parse(patentEntity.getRequestTime());
                patentNationSummary.setApplicationDate(new SimpleDateFormat("yyyy/MM/dd").format(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (patentEntity.getPatentType() != null) {
            patentNationSummary.setPatentType(patentEntity.getPatentType().intValue());
        }
        patentNationSummary.setInventor(patentEntity.getInventor());
        if (patentEntity.getViewCount() != null) {
            patentNationSummary.setViewsCount(patentEntity.getViewCount());
        } else {
            patentNationSummary.setViewsCount(0);
        }
        patentNationSummary.setLink("/H5/nationalpatent.html?id=" + patentEntity.getId());
        return patentNationSummary;
    }


    /**
     * PatentEntity转PatentNationDetail
     *
     * @param patentEntity
     * @return PatentNationDetail
     */
    public PatentNationDetail patentEntityToPatentNationDetail(PatentEntity patentEntity) {
        PatentNationDetail patentNationDetail = new PatentNationDetail();
        patentNationDetail.setName(patentEntity.getName());
        patentNationDetail.setViewsCount(patentEntity.getViewCount());
        patentNationDetail.setPatentType(patentEntity.getPatentType().intValue());
        patentNationDetail.setApplicationNO(patentEntity.getRequestNo());
        if (patentEntity.getRequestTime() != null) {
            try {
                Date date = new SimpleDateFormat("yyyyMMdd").parse(patentEntity.getRequestTime());
                patentNationDetail.setApplicationDate(new SimpleDateFormat("yyyy/MM/dd").format(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (patentEntity.getPublishTime() != null) {
            try {
                Date date = new SimpleDateFormat("yyyyMMdd").parse(patentEntity.getPublishTime());
                patentNationDetail.setPublicDate(new SimpleDateFormat("yyyy/MM/dd").format(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        patentNationDetail.setPublicNO(patentEntity.getPatentNo());
        /*if (patentEntity.getCreateTime() != null) {

            patentNationDetail.setPublicDate(new SimpleDateFormat("yyyy/MM/dd").format(patentEntity.getCreateTime()));
        }*/
        patentNationDetail.setIpc(patentEntity.getIpcNo());
        patentNationDetail.setApplicant(patentEntity.getProposers());
        patentNationDetail.setInventor(patentEntity.getInventor());
        patentNationDetail.setContent(patentEntity.getIntroduction());
        return patentNationDetail;
    }

    /**
     * portalPatentDataEntity转PatentBigdataSummary
     *
     * @param portalPatentDataEntity
     * @return PatentBigdataSummary
     */
    public PatentBigdataSummary portalPatentDataEntityToPatentBigdataSummary(PortalPatentDataEntity portalPatentDataEntity) {
        PatentBigdataSummary patentBigdataSummary = new PatentBigdataSummary();
        patentBigdataSummary.setId(portalPatentDataEntity.getId());
        patentBigdataSummary.setName(portalPatentDataEntity.getName());
        patentBigdataSummary.setIndex(portalPatentDataEntity.getIndustryCode());
        patentBigdataSummary.setPriorityNO(portalPatentDataEntity.getPriorityCount().toString());
        patentBigdataSummary.setReferNO(portalPatentDataEntity.getRefTimes().toString());
        patentBigdataSummary.setTypeNO(portalPatentDataEntity.getTypeCodeCount().toString());
        patentBigdataSummary.setAge(portalPatentDataEntity.getPatentAge());
        patentBigdataSummary.setPrice(portalPatentDataEntity.getPriceApply());
//        patentBigdataSummary.setCreateTime(new SimpleDateFormat("yyyy-MM-dd").format(portalPatentDataEntity.getCreateTime()));
        return patentBigdataSummary;
    }

    /**
     * portalPricesInfoEntity转PatentBigdataPriceComp
     *
     * @param portalPricesInfoEntity
     * @return PatentBigdataPriceComp
     */
    public PatentBigdataPriceComp portalPricesInfoEntityToPatentBigdataPriceComp(PortalPricesInfoEntity portalPricesInfoEntity) {
        PatentBigdataPriceComp patentBigdataSummary = new PatentBigdataPriceComp();
        patentBigdataSummary.setSources(portalPricesInfoEntity.getSourceWebSite());
        patentBigdataSummary.setTrade(portalPricesInfoEntity.getBusinessType());
        patentBigdataSummary.setReferPrice(portalPricesInfoEntity.getBusinessPrice());
        patentBigdataSummary.setPublishDate(portalPricesInfoEntity.getUpdateTime());
        patentBigdataSummary.setId(portalPricesInfoEntity.getId());
        patentBigdataSummary.setLink(portalPricesInfoEntity.getLink());
        return patentBigdataSummary;
    }
}
