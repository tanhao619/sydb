package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.ggfw.Govservice;
import com.cdyoue.oddJobs.entity.lgsq.GovServiceEntity;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.Pageable;

/**
 * Created by tr on 2017/5/15.
 */
public interface GovserviceService {
    /**
     * 关键字搜索政府服务
     * @param q 关键字
     * @param requestPage
     * @return 政府服务列表
     */
    PageInfo<Govservice> findByKeyWord(Pageable requestPage, String q);

    /**
     * 发布政府服务
     * @param news 政府服务实体
     * @return id 创建id
     */
    Integer createGovservice(Govservice news);

    /**
     * 判断是否存在
     * @param id 政府服务id
     * @return boolean
     */
    GovServiceEntity findGovserviceById(Integer id);

    /**
     * 判断是否是该用户发布的
     * @param id 政府服务id
     * @return boolean
     */
    boolean updateOrDeleteAuthority(Integer id);

    /**
     * 更新政府服务
     * @param id 政府服务id
     * @param govservice 政府服务实体
     * @return boolean
     */
    void updateGovservice(Integer id, Govservice govservice);

    /**
     * 删除政府服务
     * @param id 删除政府服务id
     * @return boolean
     */
    void deleteGovService(Integer id);

    /**
     * 根据id获取政府服务详情
     * @param id 政府服务id
     * @return 政府服务实体
     */
    Govservice getGovserviceById(Integer id);
}
