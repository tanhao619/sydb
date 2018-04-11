package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.xqdt.SuccaseDetail;
import com.cdyoue.oddJobs.dto.xqdt.SuccaseSummary;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.PageRequest;

/**
 * Created by luanyu on 2017/5/24.
 */
public interface SuccaseService {
    //通过ID找寻一个Succase详细信息
    SuccaseSummary getSuccaseById(Integer id);
    //验证ID是否存在
    boolean getSuccaseCheck(Integer id);
    //创建一个Succase
    String createSuccase(SuccaseDetail succase);
    //更新一个Succase
    void updateSuccase(Integer id,SuccaseDetail succase);
    //查询所有Succase
    PageInfo<SuccaseSummary> findList(PageRequest pr, String q);
    //查询Succase创建人
    Integer getCreatorIdBy(Integer id);
    //删除Succase
    void deleteSuccase(Integer id);
}
