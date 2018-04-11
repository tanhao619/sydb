package com.cdyoue.oddJobs.service.sydb;

import com.cdyoue.oddJobs.dto.zlcx.DeclarationProjectMessageBase;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * Created by sky on 2017/9/26.
 */
public interface DeclarationProjectMessageService {
    //根据企业用户id查询该企业申报的项目id,申报时间
    PageInfo<DeclarationProjectMessageBase> findDeclarationProjectIdAndTime(PageRequest pr, Integer userId);

}
