package com.cdyoue.oddJobs.service.sydb.impl;

import com.cdyoue.oddJobs.dao.syData.EquipmentApplyResponsitory;
import com.cdyoue.oddJobs.entity.syData.EquipmentApplyEntity;
import com.cdyoue.oddJobs.service.support.ServiceSupport;
import com.cdyoue.oddJobs.service.sydb.EquipmentApplyService;
import com.cdyoue.oddJobs.spec.SpecificationHelper;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Created by Administrator on 2017/9/19.
 */
@Service
public class EquipmentApplyServiceImpl extends ServiceSupport<EquipmentApplyEntity> implements EquipmentApplyService {

    @Autowired
    private SpecificationHelper specificationHelper;

    @Override
    public Class getJpaRepositoryClazz() {
        return EquipmentApplyResponsitory.class;
    }

//    @Override
//    public Page<EquipmentApplyEntity> getEquipmentApplyPageList(String name, String q, PageRequest pr) {
//        Page<EquipmentApplyEntity> rpPage = super.getEquipmentApplyPageList("name",q,pr);
//        List<EquipmentApplyEntity> equipmentTops = rpPage.getContent().stream().collect(Collectors.toList());
//        return new PageInfo<>(new PageImpl(equipmentTops, pr, rpPage.getTotalElements()));
//
//    }


    @Override
    public Map<String, Object> getEquipmentApplyPageList(String q, PageRequest pr) {
        Map<String, Object> pageMap = new HashMap<>();
        Page<EquipmentApplyEntity> rpPage = super.getEquipmentApplyPageList("contacts",q,pr);
        List<EquipmentApplyEntity> equipmentTops = rpPage.getContent().stream().collect(Collectors.toList());
        pageMap.put("rpPage",rpPage);
        pageMap.put("equipmentTops",equipmentTops);
        return pageMap;
    }
}
