package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.EnterserviceResponsitory;
import com.cdyoue.oddJobs.dto.scfw.EnterServiceDetail;
import com.cdyoue.oddJobs.dto.scfw.EnterServiceSummary;
import com.cdyoue.oddJobs.entity.lgsq.PortalIntellectualInnovateEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.EnterserviceMapper;
import com.cdyoue.oddJobs.service.EnterserviceService;
import com.cdyoue.oddJobs.service.support.ServiceSupport;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tr on 2017/5/16.
 */

@Service
public class EnterserviceImpl extends ServiceSupport<PortalIntellectualInnovateEntity> implements EnterserviceService {

    @Autowired
    private EnterserviceResponsitory enterserviceResponsitory;
    @Autowired
    private EnterserviceMapper enterserviceMapper;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Class getJpaRepositoryClazz() {
        return EnterserviceResponsitory.class;
    }

    @Override
    public PageInfo<EnterServiceSummary> findByKeyWord(Pageable requestPage, String q, String typeName) {
        Integer type = null;
        if (typeName.equalsIgnoreCase("zscq")){
           type = 1;
        }else if (typeName.equalsIgnoreCase("scfw")){
            type = 2;
        }
        Page<PortalIntellectualInnovateEntity> rpPage = q == null ? enterserviceResponsitory.findWithoutKeyword(type, requestPage) : enterserviceResponsitory.findWithKeyword(type, q, requestPage);
        List<EnterServiceSummary> enterservices =  rpPage.getContent().stream().map(p ->
                enterserviceMapper.portalIntellectualInnovateToEnterServiceSummary(p)
        ).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(enterservices, requestPage, rpPage.getTotalElements()));
    }

    @Override
    public Integer createEnterservice(EnterServiceDetail enterServiceDetail) {
        PortalIntellectualInnovateEntity portalIntellectualInnovateEntity = enterserviceMapper.enterServiceDetailToPortalIntellectualInnovateEntity(enterServiceDetail);
        portalIntellectualInnovateEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return enterserviceResponsitory.save(portalIntellectualInnovateEntity).getId();
    }

    @Override
    public void updateEnterservice(Integer id, EnterServiceDetail enterServiceDetail) {
        PortalIntellectualInnovateEntity pe = enterserviceResponsitory.findOne(id);
        pe.setName(enterServiceDetail.getName());
        pe.setServiceInfo(enterServiceDetail.getServiceInfo());
        pe.setTel(enterServiceDetail.getTel());
        pe.setLink(enterServiceDetail.getLink());
        pe.setCoverImg(enterServiceDetail.getCoverImg());
        pe.setIntroduction(enterServiceDetail.getIntroduction());
        pe.setInfo(enterServiceDetail.getInfo());
        pe.setType(enterServiceDetail.getType().equalsIgnoreCase("zscq") ? 1 : 2);
        enterserviceResponsitory.save(pe);
    }

    @Override
    public void deleteEnterService(Integer id) {
        enterserviceResponsitory.delete(id);
    }

    @Override
    public EnterServiceDetail getEnterserviceById(Integer id) {
        try {
            return enterserviceMapper.portalIntellectualInnovateEntityToEnterServiceDetail(enterserviceResponsitory.getOne(id));
        }catch(Exception e){
            throw new NotFoundEntityException("数据不存在");
        }
    }

    @Override
    public boolean updateOrDeleteAuthority(Integer id) {
        PortalIntellectualInnovateEntity portalIntellectualInnovateEntity = enterserviceResponsitory.getOne(id);
        return SecurityUtils.getCurrentUserLogin().getId() == portalIntellectualInnovateEntity.getCreateBy().getId();
    }


}
