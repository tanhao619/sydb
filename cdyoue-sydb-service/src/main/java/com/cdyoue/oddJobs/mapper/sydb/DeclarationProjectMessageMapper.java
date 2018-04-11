package com.cdyoue.oddJobs.mapper.sydb;

import com.cdyoue.oddJobs.dto.zlcx.DeclarationProjectMessageBase;
import com.cdyoue.oddJobs.entity.syData.SyDeclarationProjectMessageEntity;
import com.cdyoue.oddJobs.service.sydb.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * Created by sky on 2017/9/26.
 */
@Component
public class DeclarationProjectMessageMapper {
    @Autowired
    ProjectService projectService;

    public DeclarationProjectMessageBase SyDeclarationProjectMessageEntityToDeclarationProjectMessageBase(SyDeclarationProjectMessageEntity syDeclarationProjectMessageEntity){
        DeclarationProjectMessageBase declarationProjectMessageBase=new DeclarationProjectMessageBase();
        declarationProjectMessageBase.setProjectId(syDeclarationProjectMessageEntity.getProjectId());
        declarationProjectMessageBase.setCreateTime(new SimpleDateFormat("yyyy-MM-dd").format(syDeclarationProjectMessageEntity.getCreateTime()));
        //根据项目id查询项目名称
        String projectName=projectService.getNameById(syDeclarationProjectMessageEntity.getProjectId());
        declarationProjectMessageBase.setName(projectName);
        return declarationProjectMessageBase;
    }
}
