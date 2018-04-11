package com.cdyoue.oddJobs.service.sydb;

import com.cdyoue.oddJobs.dto.zlcy.*;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * Created by sky on 2017/9/23.
 */
public interface EnterpriseService {
    //通过ID找寻企业详细信息
    EnterpriseDetail getEnterpriseById(Integer id) ;
    //验证ID是否存在
    boolean getEnterpriseCheck(Integer id);
    //查询所有企业
    PageInfo<EnterpriseSummary> findList(PageRequest pr, String q);

    List<EnterpriseSummary> findTopList();
    //申请入驻基地
    Integer saveApplicationMessage(ApplicationEnterpriseSummary applicationEnterpriseSummary);

    //申请创业指导
    Integer saveDirectionMessage(ApplicationDirectionSummary applicationDirectionSummary);

    Integer createEnterprise(EnterpriseCreate enterpriseCreate);

    void deleteByIds(Integer[] ids);

    void updateEnterprise(Integer id, EnterpriseCreate enterpriseCreate);

    void updateEnterpriseViewCount(Integer id);

    void topEnterprise(Integer id,String topImage);
}
