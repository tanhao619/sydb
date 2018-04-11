package com.cdyoue.oddJobs.mapper.sydb;

import com.cdyoue.oddJobs.dto.zlcx.ExpertCareer;
import com.cdyoue.oddJobs.dto.zlcx.ExpertCareerRequest;
import com.cdyoue.oddJobs.entity.syData.SyExpertCareerEntity;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by dengshaojun on 2017/09/18.
 */
@Component
public class CareerMapper {

    public ExpertCareer entityToExpertC(SyExpertCareerEntity entity) {
        ExpertCareer ec = new ExpertCareer();
        ec.setId(entity.getId());
        ec.setBrief(entity.getBrief());
        ec.setDetail(entity.getDetail());
        return ec;
    }

    public SyExpertCareerEntity requestToEntity(ExpertCareerRequest request){
        SyExpertCareerEntity entity = new SyExpertCareerEntity();
        if (request.getId() != null && request.getId() != 0) {
            entity.setId(request.getId());
        }
        entity.setBrief(request.getBrief());
        entity.setDetail(request.getDetail());
        return entity;
    }

    public SyExpertCareerEntity requestToEntity(ExpertCareerRequest request, Integer expertId) {
        SyExpertCareerEntity entity = new SyExpertCareerEntity();
        if (request.getId() != null && request.getId() != 0) {
            entity.setId(request.getId());
        }
        entity.setExpertId(expertId);
        entity.setBrief(request.getBrief());
        entity.setDetail(request.getDetail());
        return entity;
    }
}
