package com.cdyoue.oddJobs.service.sydb.impl;

import com.cdyoue.oddJobs.dao.syData.ProjectLecturePeopleResponsitory;
import com.cdyoue.oddJobs.dto.zlcx.DeclarationProjectMessageBase;
import com.cdyoue.oddJobs.entity.syData.SyDeclarationProjectMessageEntity;
import com.cdyoue.oddJobs.mapper.sydb.DeclarationProjectMessageMapper;
import com.cdyoue.oddJobs.service.support.ServiceSupport;
import com.cdyoue.oddJobs.service.sydb.DeclarationProjectMessageService;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sky on 2017/9/26.
 */
@Service
public class DeclarationProjectMessageServiceImpl extends ServiceSupport<SyDeclarationProjectMessageEntity> implements DeclarationProjectMessageService{
    @Autowired
    DeclarationProjectMessageMapper declarationProjectMessageMapper;
    @Override
    public Class getJpaRepositoryClazz() {
        return ProjectLecturePeopleResponsitory.class;
    }

    @Override
    public PageInfo<DeclarationProjectMessageBase> findDeclarationProjectIdAndTime(PageRequest pr, Integer userId) {
        Page<SyDeclarationProjectMessageEntity> page=super.getDeclarationProjectPage("enterpriseUserId",userId,pr);
        List<DeclarationProjectMessageBase> list=page.getContent().stream().map(p ->declarationProjectMessageMapper.SyDeclarationProjectMessageEntityToDeclarationProjectMessageBase(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(list, pr, page.getTotalElements()));
    }
}
