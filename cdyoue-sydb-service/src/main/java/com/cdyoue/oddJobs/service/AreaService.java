package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.AreaBanner;
import com.cdyoue.oddJobs.dto.AreaDTO;
import com.cdyoue.oddJobs.dto.Reason;
import com.cdyoue.oddJobs.dto.scfw.SpaceDetail;
import com.cdyoue.oddJobs.dto.scfw.SpaceSummary;
import com.cdyoue.oddJobs.dto.zscq.IntellectualTop;
import com.cdyoue.oddJobs.entity.lgsq.PortalAreaServiceEntity;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Administrator on 2017/5/11.
 */
public interface AreaService {
    List<AreaDTO> getArea();

    /**
     * 查询地域名字
     * @param id
     * @return
     */
    String getAreaName(Integer id);

    /**
     * 查询空间列表
     * @param q
     * @param requestPage
     * @return
     */
    PageInfo<SpaceSummary> getSpaceSummaryPage(Integer type,String q,Integer areaIdLvPre,Integer areaIdLvNext,Integer reviewStatus,Integer userId,Pageable requestPage);

    /**
     *  查询空间详情
     * @param id
     * @return
     */
    SpaceDetail getSpaceDetail(Integer id);

    /**
     * 删除空间
     * @param id
     */
    void deleteSpace(Integer id);

    /**
     * 发布空间
     * @param space
     */
    void save(SpaceDetail space);

    /**
     * 根据地域名字查询ID
     * @param spaceName
     * @return
     */
    Integer getIdBySpaceName(String spaceName);

    /**
     * 更新空间
     * @param space
     */
    void update(Integer id,SpaceDetail space);

    /**
     * 查询该空间
     * @param id
     * @return
     */
    PortalAreaServiceEntity findOne(Integer id);

    /**
     * 审核空间
     * @param id
     * @param status
     * @param reason
     */
    void reviewArea(Integer id, Integer status, Reason reason);

    /**
     * Validate whether the user has authority to delete the space service
     * @param userId
     * @param id
     * @return boolean
     */
    boolean deleteAuthority(Integer userId, Integer id);

    /**
     * 获取场地banner列表
     * @return
     */
    List<AreaBanner> getAreaBanners();

    /**
     * 批量删除
     * @param ids
     */
    void deleteAllSpaces(Integer[] ids);

    /**
     * 置顶
     * @param top
     */
    void siteTop(IntellectualTop top);

    /**
     * 取消置顶
     * @param id
     */
    void removeSiteTop(Integer id);
}
