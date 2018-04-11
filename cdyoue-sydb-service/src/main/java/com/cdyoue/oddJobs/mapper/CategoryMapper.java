package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dto.common.Category;
import com.cdyoue.oddJobs.entity.lgsq.FunctioncategoryEntity;
import com.cdyoue.oddJobs.entity.lgsq.common.OutsourcingProjectTypeEntity;
import com.cdyoue.oddJobs.entity.lgsq.common.PortalDomainEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * Created by liaoyoule on 2017/4/26.
 */
@Component
public class CategoryMapper {

    public Category outsourcingProjectTypeEntityToCategory(OutsourcingProjectTypeEntity opte){
        Category category = new Category();
        BeanUtils.copyProperties(opte,category);
        return category;
    }


    public Category portalDomainEntityToCategory(PortalDomainEntity pde){
        Category category = new Category();
        BeanUtils.copyProperties(pde,category);
        return category;
    }

    public Category functioncategoryToCategory(FunctioncategoryEntity pde){
        Category category = new Category();
        BeanUtils.copyProperties(pde,category);
        return category;
    }


    public OutsourcingProjectTypeEntity categoryToOutsourcingProjectTypeEntity(Category category) {
        OutsourcingProjectTypeEntity opte = new OutsourcingProjectTypeEntity();
        opte.setId(category.getId());
        return opte;
    }
}
