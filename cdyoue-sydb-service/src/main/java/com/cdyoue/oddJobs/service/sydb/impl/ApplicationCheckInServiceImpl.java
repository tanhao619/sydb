package com.cdyoue.oddJobs.service.sydb.impl;

import com.cdyoue.oddJobs.dao.lqsq.UserenterpriseResponsitory;
import com.cdyoue.oddJobs.dao.syData.ApplicationCheckInRepository;
import com.cdyoue.oddJobs.dao.syData.ApplicationCheckinViewRepository;
import com.cdyoue.oddJobs.dto.zlcy.ApplicationCheckInDetail;
import com.cdyoue.oddJobs.dto.zlcy.ApplicationCheckInMini;
import com.cdyoue.oddJobs.entity.syData.SyApplicationCheckInMessageEntity;
import com.cdyoue.oddJobs.entity.syData.SyApplicationCheckinView;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.sydb.ApplicationCheckInMapper;
import com.cdyoue.oddJobs.service.sydb.ApplicationCheckInService;
import com.cdyoue.oddJobs.spec.Operator;
import com.cdyoue.oddJobs.spec.QueryRequest;
import com.cdyoue.oddJobs.spec.SpecificationHelper;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dengshaojun on 2017/10/12.
 */
@Service
@Transactional
public class ApplicationCheckInServiceImpl implements ApplicationCheckInService {

    @Autowired
    private ApplicationCheckInRepository applicationCheckInRepository;

    @Autowired
    private ApplicationCheckinViewRepository applicationCheckinViewRepository;

    @Autowired
    private ApplicationCheckInMapper applicationCheckInMapper;

    @Autowired
    private SpecificationHelper specificationHelper;

    @Override
    public PageInfo<ApplicationCheckInMini> findApplicationCheckIns(String q, PageRequest pageRequest) {
        List<QueryRequest> queryRequest = new ArrayList<>();
        QueryRequest qe = new QueryRequest();
        qe.setF("enterName");
        qe.setO(Operator.LIKE);
        qe.setV(q);
        queryRequest.add(qe);
        Specification specification = specificationHelper.getSpecifica(SyApplicationCheckinView.class, queryRequest);
        Page<SyApplicationCheckinView> page = applicationCheckinViewRepository.findAll(specification, pageRequest);
        if (page.getContent().size() == 0) {
            throw new NotFoundEntityException("数据不存在");
        }
        List<ApplicationCheckInMini> list =  page.getContent().stream().map(p -> applicationCheckInMapper.viewToMini(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(list, pageRequest, page.getTotalElements()));
    }

    @Override
    public ApplicationCheckInDetail findApplicationCheckIn(Integer id) {
        SyApplicationCheckInMessageEntity entity = applicationCheckInRepository.findOne(id);
        if (entity == null) {
            throw new NotFoundEntityException("数据不存在");
        }
        // 改变状态
        entity.setStatus(new Byte("1"));
        applicationCheckInRepository.save(entity);
        ApplicationCheckInDetail applicationCheckInDetail = applicationCheckInMapper.entityToDetail(entity);
        return applicationCheckInDetail;
    }

    @Override
    public void deleteApplicationCheckIn(Integer[] ids) {
        List<SyApplicationCheckInMessageEntity> list = applicationCheckInRepository.findAll(Arrays.asList(ids));
        if (list.size() == 0) {
            throw new NotFoundEntityException("操作对象不存在");
        }
        applicationCheckInRepository.delete(list);
    }
}
