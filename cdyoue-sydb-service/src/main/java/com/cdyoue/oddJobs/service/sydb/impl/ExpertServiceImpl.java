package com.cdyoue.oddJobs.service.sydb.impl;

import com.cdyoue.oddJobs.constant.DataTypeConstant;
import com.cdyoue.oddJobs.dao.syData.*;
import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.zlcx.*;
import com.cdyoue.oddJobs.entity.syData.SyExpertContactEntity;
import com.cdyoue.oddJobs.entity.syData.SyExpertContactView;
import com.cdyoue.oddJobs.entity.syData.SyExpertEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.sydb.ExpertContactMapper;
import com.cdyoue.oddJobs.mapper.sydb.ExpertMapper;
import com.cdyoue.oddJobs.service.sydb.ExpertService;
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
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dengshaojun on 2017/09/18.
 */
@Service
@Transactional
public class ExpertServiceImpl implements ExpertService {

    @Autowired
    private SpecificationHelper specificationHelper;

    @Autowired
    private ExpertRepository expertRepository;

    @Autowired
    private ExpertContactRepository expertContactRepository;

    @Autowired
    private ExpertContactViewRepository expertContactViewRepository;

    @Autowired
    private ExpertAchievementRepository expertAchievementRepository;

    @Autowired
    private ProfessionalInterpretationRepository professionalInterpretationRepository;

    @Autowired
    private ExpertMapper expertMapper;

    @Autowired
    private ExpertContactMapper expertContactMapper;

    @Override
    public PageInfo<ExpertSummary> findExpertsPage(Integer industryType, String q, Pageable pageRequest) {
        List<QueryRequest> queryRequest = new ArrayList<>();

        QueryRequest qe1 = new QueryRequest();
        qe1.setF("name");
        qe1.setO(Operator.LIKE);
        qe1.setV(q);
        queryRequest.add(qe1);

        QueryRequest qeEr = new QueryRequest();
        qeEr.setF("syIndustryTypeEntity.id");
        qeEr.setO(Operator.EQ);
        qeEr.setV(industryType + "");
        qeEr.setT(DataTypeConstant.INTEGER);
        queryRequest.add(qeEr);

        Specification specification = specificationHelper.getSpecifica(SyExpertEntity.class, queryRequest);

        Page<SyExpertEntity> page = expertRepository.findAll(specification, pageRequest);
        if (page.getContent().size() == 0) {
            throw new NotFoundEntityException("数据不存在");
        }
        List<ExpertSummary> list = page.getContent().stream().map(p -> expertMapper.entityToSummary(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(list, pageRequest, page.getTotalElements()));
    }

    @Override
    public ExpertBaseinfo findExpert(Integer id) {
        SyExpertEntity entity = expertRepository.findOne(id);
        if (entity == null) {
            throw new NotFoundEntityException("数据不存在");
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
        expertRepository.save(entity);
        ExpertBaseinfo expertBaseinfo = expertMapper.entityToBaseinfo(entity);
        return expertBaseinfo;
    }

    @Override
    public List<ExpertTop> findExpertTop3() {
        List<SyExpertEntity> list = expertRepository.findTop3ByTopOrderByUpdateTimeDesc(new Byte("1"));
        if (list.size() == 0) {
            throw new NotFoundEntityException("数据不存在");
        }
        return list.stream().map(p -> expertMapper.entityToTop(p)).collect(Collectors.toList());
    }

    @Override
    public void saveExpertContact(Integer expertId, ExpertContactRequest request) {
        SyExpertEntity expertEntity = expertRepository.findOne(expertId);
        if (expertEntity == null) {
            throw new NotFoundEntityException("操作对象不存在");
        }
        SyExpertContactEntity entity = expertContactMapper.requestToEntity(expertId, request);
        expertContactRepository.save(entity);
        expertEntity.setContactCount(expertEntity.getContactCount() + 1);
        expertRepository.save(expertEntity);
    }

    @Override
    public void saveExpert(ExpertRequest request) {
        SyExpertEntity entity = expertMapper.requestToEntity(request);
        expertRepository.save(entity);
    }

    @Override
    public void updateExpert(Integer id, ExpertRequest request) {
        SyExpertEntity entity = expertRepository.findOne(id);
        if (entity == null) {
            throw new NotFoundEntityException("操作对象不存在");
        }
        entity = expertMapper.requestToEntity(request, entity);
        expertRepository.save(entity);
    }

    @Override
    public void deleteExpert(Integer[] ids) {
        List<SyExpertEntity> list = expertRepository.findAll(Arrays.asList(ids));
        if (list.size() == 0) {
            throw new NotFoundEntityException("操作对象不存在");
        }
        list.forEach(p -> {
            p.getSyExpertAchievementEntitySet().forEach(ea -> {
                ea.setSyExpertEntity(null);
                expertAchievementRepository.save(ea);
            });
            p.getSyProfessionalInterpretationEntitySet().forEach(pi -> {
                pi.setSyExpertEntity(null);
                professionalInterpretationRepository.save(pi);
            });
            //expertAchievementRepository.updateForeign(p.getId());
            //professionalInterpretationRepository.updateForeign(p.getId());
        });
        expertRepository.delete(list);
    }

    @Override
    public void updateTopExpert(Integer id, String topImgUrl) {
        SyExpertEntity entity = expertRepository.findOne(id);
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
        expertRepository.save(entity);
    }

    @Override
    public List<ExpertMini> findAllExpert(String q) {
        List<QueryRequest> queryRequest = new ArrayList<>();
        QueryRequest qe1 = new QueryRequest();
        qe1.setF("name");
        qe1.setO(Operator.LIKE);
        qe1.setV(q);
        queryRequest.add(qe1);
        Specification specification = specificationHelper.getSpecifica(SyExpertEntity.class, queryRequest);
        List<SyExpertEntity> list1 = expertRepository.findAll(specification);
        if (list1.size() == 0) {
            throw new NotFoundEntityException("数据不存在");
        }
        List<ExpertMini> list = list1.stream().map(p -> expertMapper.entityToMini(p)).collect(Collectors.toList());
        return list;
    }

    @Override
    public PageInfo<ExpertContactSummary> findExpertContactsPage(String q, Pageable pageRequest) {
        List<QueryRequest> queryRequest = new ArrayList<>();
        QueryRequest qe = new QueryRequest();
        qe.setF("expertName");
        qe.setO(Operator.LIKE);
        qe.setV(q);
        queryRequest.add(qe);
        Specification specification = specificationHelper.getSpecifica(SyExpertContactView.class, queryRequest);
        Page<SyExpertContactView> page = expertContactViewRepository.findAll(specification, pageRequest);
        if (page.getContent().size() == 0) {
            throw new NotFoundEntityException("数据不存在");
        }
        List<ExpertContactSummary> list = page.getContent().stream().map(p -> expertContactMapper.viewToSummary(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(list, pageRequest, page.getTotalElements()));
    }

    @Override
    public void deleteExpertContact(Integer[] ids) {
        List<SyExpertContactEntity> list = expertContactRepository.findAll(Arrays.asList(ids));
        if (list.size() == 0) {
            throw new NotFoundEntityException("操作对象不存在");
        }
        expertContactRepository.delete(list);
    }

}
