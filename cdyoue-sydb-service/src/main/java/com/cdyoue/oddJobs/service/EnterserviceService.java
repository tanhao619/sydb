package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.scfw.EnterServiceDetail;
import com.cdyoue.oddJobs.dto.scfw.EnterServiceSummary;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.Pageable;

/**
 * Created by tr on 2017/5/16.
 */
public interface EnterserviceService {
    /**
     * 关键字搜索服务机构
     * @param q 关键字
     * @param requestPage
     * @return 服务机构列表
     */
    PageInfo<EnterServiceSummary> findByKeyWord(Pageable requestPage, String q, String type);

    /**
     * 发布服务机构
     * @param enterServiceDetail 服务机构实体
     * @return id 创建id
     */
    Integer createEnterservice(EnterServiceDetail enterServiceDetail);


    /**
     * 更新服务机构
     * @param id 服务机构id
     * @param enterServiceDetail 政府机构实体
     * @return boolean
     */
    void updateEnterservice(Integer id, EnterServiceDetail enterServiceDetail);

    /**
     * 删除服务机构
     * @param id 服务机构id
     * @return boolean
     */
    void deleteEnterService(Integer id);

    /**
     * 根据id获取服务机构详情
     * @param id 服务机构id
     * @return 服务机构实体
     */
    EnterServiceDetail getEnterserviceById(Integer id);

    /**
     * 判断是否是该用户发布的
     * @param id 服务机构id
     * @return boolean
     */
    boolean updateOrDeleteAuthority(Integer id);
}
