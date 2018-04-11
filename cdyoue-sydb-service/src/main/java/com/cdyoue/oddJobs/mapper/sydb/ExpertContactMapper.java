package com.cdyoue.oddJobs.mapper.sydb;

import com.cdyoue.oddJobs.dao.lqsq.UserenterpriseResponsitory;
import com.cdyoue.oddJobs.dto.zlcx.ExpertContactRequest;
import com.cdyoue.oddJobs.dto.zlcx.ExpertContactSummary;
import com.cdyoue.oddJobs.entity.lgsq.user.UserenterpriseEntity;
import com.cdyoue.oddJobs.entity.syData.SyExpertContactEntity;
import com.cdyoue.oddJobs.entity.syData.SyExpertContactView;
import com.cdyoue.oddJobs.entity.syData.SyExpertEntity;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by dengshaojun on 2017/09/21.
 */
@Component
public class ExpertContactMapper {

    @Autowired
    private UserenterpriseResponsitory userenterpriseResponsitory;

    public SyExpertContactEntity requestToEntity(Integer expertId, ExpertContactRequest request) {
        SyExpertContactEntity entity = new SyExpertContactEntity();
        entity.setContactPeople(request.getContactPeople());
        entity.setContactTel(request.getContactTel());
        entity.setContent(request.getConsultationContent());
        entity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        SyExpertEntity expertEntity = new SyExpertEntity();
        expertEntity.setId(expertId);
        entity.setSyExpertEntity(expertEntity);
        entity.setEnterId(SecurityUtils.getCurrentUserLogin().getId());
        return entity;
    }

    public ExpertContactSummary viewToSummary(SyExpertContactView view) {
        ExpertContactSummary summary = new ExpertContactSummary();
        summary.setId(view.getId());
        summary.setCreateEnter(view.getEnterName());
        summary.setExpertName(view.getExpertName());
        summary.setCreateTime(view.getCreateTime().getTime());
        summary.setContactPeople(view.getContactPeople());
        summary.setContactTel(view.getContactTel());
        summary.setConsultationContent(view.getContent());
        return summary;
    }
}
