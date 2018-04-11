package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.PortalInvestorResponsitory;
import com.cdyoue.oddJobs.dto.scfw.InvestorDetail;
import com.cdyoue.oddJobs.dto.scfw.InvestorSummary;
import com.cdyoue.oddJobs.entity.lgsq.PortalInvestorEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.PortalInvestorsMapper;
import com.cdyoue.oddJobs.service.InvestorsApiService;
import com.cdyoue.oddJobs.service.support.ServiceSupport;
import com.cdyoue.oddJobs.spec.QueryRequest;
import com.cdyoue.oddJobs.spec.SpecificationHelper;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.oddJobs.utils.SpringContextUtil;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.ParameterizedType;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sky on 2017/5/2.
 *
 */
@Service
public class InvestorsApiServiceImpl extends ServiceSupport<PortalInvestorEntity> implements InvestorsApiService {

    @Autowired
    private SpecificationHelper specificationHelper;

    @Autowired
    private PortalInvestorsMapper portalInvestorsMapper ;

    @Autowired
    private PortalInvestorResponsitory portalInvestorResponsitory;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public PageInfo<InvestorSummary> getInvestors(String q, Pageable requestPage) {
        Page<PortalInvestorEntity> rpPage = null;
        if (StringUtils.isEmpty(q)) {
            rpPage = findPortalInvestorEntitys(requestPage);
        } else {
            rpPage = portalInvestorResponsitory.findPortalInvestorEntitysWithQ(q, requestPage);
        }
        List<InvestorSummary> investorSummarys = rpPage.getContent().stream().map(p ->
                portalInvestorsMapper.investorToInvestorSummary(p))
                .collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(investorSummarys, requestPage, rpPage.getTotalElements()));

    }

    @Override
    public Integer createInvestor(InvestorDetail investorDetail) {
        PortalInvestorEntity portalInvestorEntity = portalInvestorsMapper.investorDetailToPortalInvestorEntity(investorDetail);
        UserEntity userEntity = new UserEntity();
        userEntity.setId(SecurityUtils.getCurrentUserLogin().getId());
        portalInvestorEntity.setCreateBy(userEntity);
        portalInvestorEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return portalInvestorResponsitory.save(portalInvestorEntity).getId();
    }

    @Override
    public InvestorDetail getInvestorById(Integer id) {
        try {
            PortalInvestorEntity portalInvestorEntity = portalInvestorResponsitory.findOne(id);
            return portalInvestorsMapper.portalInvestorEntityToInvestorDetail(portalInvestorEntity);
        }catch (Exception e) {
            throw new NotFoundEntityException("数据没找到");
        }
    }

    @Override
    public void deleteInvestor(Integer id) {
        portalInvestorResponsitory.delete(id);
    }

    @Override
    public void updateInvestor(Integer id, InvestorDetail investorDetail) {
        getInvestorById(id);
        PortalInvestorEntity portalInvestorEntity = portalInvestorsMapper.investorDetailToPortalInvestorEntity(investorDetail);
        String sql = "update lg_portal_investor set name=?, title=?, tel=?, email=?, deptName=?, focusArea=?,deptImg=?,introduction=?,link=?,headImg=? where id = ?";

        jdbcTemplate.update(sql, portalInvestorEntity.getName(),portalInvestorEntity.getTitle(), portalInvestorEntity.getTel(), portalInvestorEntity.getEmail(), portalInvestorEntity.getOrgName(), portalInvestorEntity.getFocusArea(), portalInvestorEntity.getLogoUrl(), portalInvestorEntity.getIntroduction(), portalInvestorEntity.getLink(), portalInvestorEntity.getHeadImg(), id);

    }

    @Override
    public Class getJpaRepositoryClazz() {
        return PortalInvestorResponsitory.class;
    }

    public Page<PortalInvestorEntity> findPortalInvestorEntitys( Pageable rqPage) {
        List<QueryRequest> queryRequest = new ArrayList<>();
        Class entityClazz = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        Specification specifica = specificationHelper.getSpecifica(entityClazz, queryRequest);
        Page<PortalInvestorEntity> page = ((JpaSpecificationExecutor) SpringContextUtil.getBean(getJpaRepositoryClazz())).findAll(specifica, rqPage);
        if (page.getContent().size() == 0) {
            throw new NotFoundEntityException("数据不存在");
        }
        return page;
    }
}
