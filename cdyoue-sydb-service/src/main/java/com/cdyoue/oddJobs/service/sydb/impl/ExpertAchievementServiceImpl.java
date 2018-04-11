package com.cdyoue.oddJobs.service.sydb.impl;

import com.cdyoue.oddJobs.dao.syData.ExpertAchievementCollectRepository;
import com.cdyoue.oddJobs.dao.syData.ExpertAchievementRepository;
import com.cdyoue.oddJobs.dao.syData.ExpertRepository;
import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.zlcx.ExpertAchievementDetail;
import com.cdyoue.oddJobs.dto.zlcx.ExpertAchievementRequest;
import com.cdyoue.oddJobs.dto.zlcx.ExpertAchievementSummary;
import com.cdyoue.oddJobs.dto.zlcx.ExpertAchievementTop;
import com.cdyoue.oddJobs.entity.syData.SyExpertAchievementCollectEntity;
import com.cdyoue.oddJobs.entity.syData.SyExpertAchievementEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.sydb.ExpertAchievementMapper;
import com.cdyoue.oddJobs.service.sydb.ExpertAchievementService;
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
public class ExpertAchievementServiceImpl implements ExpertAchievementService {

    @Autowired
    private ExpertAchievementRepository expertAchievementRepository;

    @Autowired
    private ExpertRepository expertRepository;

    @Autowired
    private ExpertAchievementCollectRepository expertAchievementCollectRepository;

    @Autowired
    private ExpertAchievementMapper expertAchievementMapper;

    @Autowired
    private SpecificationHelper specificationHelper;

    @Override
    public ExpertAchievementDetail findExpertAchievement(Integer id) {
        SyExpertAchievementEntity entity = expertAchievementRepository.findOne(id);
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
        expertAchievementRepository.save(entity);
        return expertAchievementMapper.entityToDetail(entity);
    }

    @Override
    public List<ExpertAchievementSummary> findExpertAchievementByEid(Integer expertId) {
        List<SyExpertAchievementEntity> list1 = expertRepository.findExpertAchievementByEid(expertId);
        if (list1.size() == 0) {
            throw new NotFoundEntityException("没有数据");
        }
        Collections.sort(list1, (p1, p2) -> p2.getCreateTime().compareTo(p1.getCreateTime()));
        List<ExpertAchievementSummary> list = list1.stream().map(p -> expertAchievementMapper.entityToSummary(p)).collect(Collectors.toList());
        return list;
    }

    @Override
    public PageInfo<ExpertAchievementSummary> findExpertAchievementsPage(String q, Pageable pageRequest) {
        List<QueryRequest> queryRequest = new ArrayList<>();

        QueryRequest qe1 = new QueryRequest();
        qe1.setF("name");
        qe1.setO(Operator.LIKE);
        qe1.setV(q);
        queryRequest.add(qe1);

        Specification specifica = specificationHelper.getSpecifica(SyExpertAchievementEntity.class, queryRequest);

        Page<SyExpertAchievementEntity> page = expertAchievementRepository.findAll(specifica, pageRequest);
        if (page.getContent().size() == 0) {
            throw new NotFoundEntityException("没有数据");
        }
        List<ExpertAchievementSummary> list = page.getContent().stream().map(p -> expertAchievementMapper.entityToSummary(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(list, pageRequest, page.getTotalElements()));
    }

    @Override
    public List<ExpertAchievementTop> findExpertAchievementTop2() {
        List<SyExpertAchievementEntity> list = expertAchievementRepository.findTop2ByTopOrderByUpdateTimeDesc(new Byte("1"));
        if (list.size() == 0) {
            throw new NotFoundEntityException("没有数据");
        }
        return list.stream().map(p -> expertAchievementMapper.entityToTop(p)).collect(Collectors.toList());
    }

    @Override
    public void saveExpertAchievement(ExpertAchievementRequest request) {
        SyExpertAchievementEntity entity = expertAchievementMapper.requestToEntity(request);
        expertAchievementRepository.save(entity);
    }

    @Override
    public void updateExpertAchievement(Integer id, ExpertAchievementRequest request) {
        SyExpertAchievementEntity entity = expertAchievementRepository.findOne(id);
        if (entity == null) {
            throw new NotFoundEntityException("操作对象不存在");
        }
        entity = expertAchievementMapper.requestToEntity(request, entity);
        expertAchievementRepository.save(entity);
    }

    @Override
    public void deleteExpertAchievement(Integer[] ids) {
        List<SyExpertAchievementEntity> list = expertAchievementRepository.findAll(Arrays.asList(ids));
        if (list.size() == 0) {
            throw new NotFoundEntityException("操作对象不存在");
        }
        expertAchievementRepository.delete(list);
    }

    @Override
    public void updateTopExpertAchievement(Integer id, String topImgUrl) {
        SyExpertAchievementEntity entity = expertAchievementRepository.findOne(id);
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
        expertAchievementRepository.save(entity);
    }

    @Override
    public void collectExpertAchievement(Integer userId, Integer achievementId) {
        List<SyExpertAchievementCollectEntity> list = expertAchievementCollectRepository.findByUserIdAndAchievementId(userId, achievementId);
        SyExpertAchievementEntity entity = expertAchievementRepository.findOne(achievementId);
        if (entity == null) {
            throw new NotFoundEntityException("操作对象不存在");
        }
        if (list.size() > 0) {
            expertAchievementCollectRepository.delete(list);
            entity.setCollectCount(entity.getCollectCount() - list.size());
        } else {
            SyExpertAchievementCollectEntity collectEntity = new SyExpertAchievementCollectEntity();
            collectEntity.setUserId(userId);
            collectEntity.setAchievementId(achievementId);
            expertAchievementCollectRepository.save(collectEntity);
            entity.setCollectCount(entity.getCollectCount() + 1);
        }
        expertAchievementRepository.save(entity);
    }

    @Override
    public PageInfo<ExpertAchievementSummary> findExpertAchievementByUC(Integer userId, Pageable pageRequest) {
        List<Integer> achievementids = expertAchievementCollectRepository.findAchievementidsByUserId(userId);
        if (achievementids.size() == 0){
            throw new NotFoundEntityException("没有数据");
        }
        Page<SyExpertAchievementEntity> page = expertAchievementRepository.findByIds(achievementids, pageRequest);
        if (page.getContent().size() == 0) {
            throw new NotFoundEntityException("没有数据");
        }
        List<ExpertAchievementSummary> list = page.getContent().stream().map(p -> expertAchievementMapper.entityToSummary(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(list, pageRequest, page.getTotalElements()));
    }

}
