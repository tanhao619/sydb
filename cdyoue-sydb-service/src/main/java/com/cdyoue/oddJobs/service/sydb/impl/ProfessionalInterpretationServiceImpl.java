package com.cdyoue.oddJobs.service.sydb.impl;

import com.cdyoue.oddJobs.dao.syData.ExpertRepository;
import com.cdyoue.oddJobs.dao.syData.ProfessionalInterpretationCollectRepository;
import com.cdyoue.oddJobs.dao.syData.ProfessionalInterpretationRepository;
import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.zlcx.*;
import com.cdyoue.oddJobs.entity.syData.SyProfessionalInterpretationCollectEntity;
import com.cdyoue.oddJobs.entity.syData.SyProfessionalInterpretationEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.sydb.ProfessionalInterpretationMapper;
import com.cdyoue.oddJobs.service.sydb.ProfessionalInterpretationService;
import com.cdyoue.oddJobs.spec.Operator;
import com.cdyoue.oddJobs.spec.QueryRequest;
import com.cdyoue.oddJobs.spec.SpecificationHelper;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dengshaojun on 2017/09/18.
 */
@Service
@Transactional
public class ProfessionalInterpretationServiceImpl implements ProfessionalInterpretationService {

    @Autowired
    private ProfessionalInterpretationRepository professionalInterpretationRepository;

    @Autowired
    private ExpertRepository expertRepository;

    @Autowired
    private ProfessionalInterpretationCollectRepository professionalInterpretationCollectRepository;

    @Autowired
    private ProfessionalInterpretationMapper professionalInterpretationMapper;

    @Autowired
    private SpecificationHelper specificationHelper;

    @Override
    public ProfessionalInterpretationDetail findProfessionalInterpretation(Integer id) {
        SyProfessionalInterpretationEntity entity = professionalInterpretationRepository.findOne(id);
        if (entity == null) {
            throw new NotFoundEntityException("没有数据");
        }
        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();
        // 企业用户和游客浏览加1
        if (currentUserLogin != null) {
            if (currentUserLogin.getRole() == 1) {
                entity.setViewCount(entity.getViewCount() + 1);
            }
        } else {
            entity.setViewCount(entity.getViewCount() + 1);
        }
        professionalInterpretationRepository.save(entity);
        return professionalInterpretationMapper.entityToDetail(entity);
    }

    @Override
    public List<ProfessionalInterpretationSummary> findExpertProfessionalInterpretation(Integer expertId) {
        List<SyProfessionalInterpretationEntity> list1 = expertRepository.findProfessionalInterpretationByEid(expertId);
        if (list1.size() == 0) {
            throw new NotFoundEntityException("没有数据");
        }
        Collections.sort(list1, (p1, p2) -> p2.getCreateTime().compareTo(p1.getCreateTime()));
        List<ProfessionalInterpretationSummary> list = list1.stream().map(p -> professionalInterpretationMapper.entityToSummary(p)).collect(Collectors.toList());
        return list;
    }

    @Override
    public PageInfo<ProfessionalInterpretationSummary> findExpertProfessionalInterpretationsPage(String q, Pageable pageRequest) {
        List<QueryRequest> queryRequest = new ArrayList<>();

        QueryRequest qe1 = new QueryRequest();
        qe1.setF("name");
        qe1.setO(Operator.LIKE);
        qe1.setV(q);
        queryRequest.add(qe1);

        Specification specification = specificationHelper.getSpecifica(SyProfessionalInterpretationEntity.class, queryRequest);

        Page<SyProfessionalInterpretationEntity> page = professionalInterpretationRepository.findAll(specification, pageRequest);
        if (page.getContent().size() == 0) {
            throw new NotFoundEntityException("没有数据");
        }
        List<ProfessionalInterpretationSummary> list = page.getContent().stream().map(p -> professionalInterpretationMapper.entityToSummary(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(list, pageRequest, page.getTotalElements()));
    }

    @Override
    public List<ProfessionalInterpretationTop> findProfessionalInterpretationTop3() {
        List<SyProfessionalInterpretationEntity> list = professionalInterpretationRepository.findTop3ByTopOrderByUpdateTimeDesc(new Byte("1"));
        if (list.size() == 0) {
            throw new NotFoundEntityException("没有数据");
        }
        return list.stream().map(p -> professionalInterpretationMapper.entityToTop(p)).collect(Collectors.toList());
    }

    @Override
    public void saveProfessionalInterpretation(ProfessionalInterpretationRequest request) {
        SyProfessionalInterpretationEntity entity = professionalInterpretationMapper.requestToEntity(request);
        professionalInterpretationRepository.save(entity);
    }

    @Override
    public void updateProfessionalInterpretation(Integer id, ProfessionalInterpretationRequest request) {
        SyProfessionalInterpretationEntity entity = professionalInterpretationRepository.findOne(id);
        if (entity == null) {
            throw new NotFoundEntityException("操作对象不存在");
        }
        entity = professionalInterpretationMapper.requestToEntity(request, entity);
        professionalInterpretationRepository.save(entity);
    }

    @Override
    public void deleteProfessionalInterpretation(Integer[] ids) {
        List<SyProfessionalInterpretationEntity> list = professionalInterpretationRepository.findAll(Arrays.asList(ids));
        if (list.size() == 0) {
            throw new NotFoundEntityException("操作对象不存在");
        }
        professionalInterpretationRepository.delete(list);
    }

    @Override
    public void topProfessionalInterpretation(Integer id, String topImgUrl) {
        SyProfessionalInterpretationEntity entity = professionalInterpretationRepository.findOne(id);
        if (entity == null) {
            throw new NotFoundEntityException("操作对象不存在");
        }
        Byte top = entity.getTop();
        switch (top) {
            case 0:
                entity.setTop(new Byte("1"));
                entity.setTopImg(topImgUrl);
                break;
            case 1:
                entity.setTop(new Byte("0"));
                break;
            default:
                entity.setTop(new Byte("0"));
        }
        entity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        professionalInterpretationRepository.save(entity);
    }

    @Override
    public void collectProfessionalInterpretation(Integer userId, Integer interpretationid) {
        List<SyProfessionalInterpretationCollectEntity> list = professionalInterpretationCollectRepository.findByUserIdAndInterpretationId(userId, interpretationid);
        SyProfessionalInterpretationEntity entity = professionalInterpretationRepository.findOne(interpretationid);
        if (entity == null) {
            throw new NotFoundEntityException("操作对象不存在");
        }
        if (list.size() > 0) {
            professionalInterpretationCollectRepository.delete(list);
            entity.setCollectCount(entity.getCollectCount() - list.size());
        } else {
            SyProfessionalInterpretationCollectEntity collectEntity = new SyProfessionalInterpretationCollectEntity();
            collectEntity.setUserId(userId);
            collectEntity.setInterpretationId(interpretationid);
            professionalInterpretationCollectRepository.save(collectEntity);
            entity.setCollectCount(entity.getCollectCount() + 1);
        }
        professionalInterpretationRepository.save(entity);
    }

    @Override
    public PageInfo<ProfessionalInterpretationSummary> findProfessionalInterpretationByUC(Integer userId, Pageable pageRequest) {
        List<Integer> interpretationids = professionalInterpretationCollectRepository.findInterpretationidsByUserId(userId);
        if (interpretationids.size() == 0){
            throw new NotFoundEntityException("没有数据");
        }
        Page<SyProfessionalInterpretationEntity> page = professionalInterpretationRepository.findByIds(interpretationids, pageRequest);
        if (page.getContent().size() == 0) {
            throw new NotFoundEntityException("没有数据");
        }
        List<ProfessionalInterpretationSummary> list = page.getContent().stream().map(p -> professionalInterpretationMapper.entityToSummary(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(list, pageRequest, page.getTotalElements()));
    }
}
