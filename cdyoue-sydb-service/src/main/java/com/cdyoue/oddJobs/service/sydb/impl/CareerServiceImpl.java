package com.cdyoue.oddJobs.service.sydb.impl;

import com.cdyoue.oddJobs.dao.syData.ExpertRepository;
import com.cdyoue.oddJobs.dto.zlcx.ExpertCareer;
import com.cdyoue.oddJobs.entity.syData.SyExpertCareerEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.sydb.CareerMapper;
import com.cdyoue.oddJobs.service.sydb.CareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dengshaojun on 2017/09/18.
 */
@Service
@Transactional
public class CareerServiceImpl implements CareerService {

    @Autowired
    private ExpertRepository expertRepository;

    @Autowired
    private CareerMapper careerMapper;

    @Override
    public List<ExpertCareer> findExpertCareer(Integer expertId) {
        List<SyExpertCareerEntity> list1 = expertRepository.findCareerByEid(expertId);
        if (list1.size() == 0) {
            throw new NotFoundEntityException("没有数据");
        }
        Collections.sort(list1, (p1, p2) -> p2.getBrief().compareTo(p1.getBrief()));
        List<ExpertCareer> list = list1.stream().map(p -> careerMapper.entityToExpertC(p)).collect(Collectors.toList());
        return list;
    }
}
