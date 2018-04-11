package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.xqdt.BigProjectDetail;
import com.cdyoue.oddJobs.entity.lgsq.PortalBigProjectEntity;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * Created by luanyu on 2017/5/20.
 */
public interface BigPorjectService {
    //通过ID找寻一个大项目详细信息
    BigProjectDetail getBigProjectById(Integer id);
    //验证ID是否存在
    boolean getBigProjectCheck(Integer id);
    //创建一个大项目
    String createBigProject(BigProjectDetail activity);
    //更新一个大项目
    void updateBigProject(Integer id,BigProjectDetail activity);
    //置顶大项目
    void topBigProject(Integer id,String topImg);
    //取消置顶大项目
    void cancelTopBigProject(Integer id);
    //取消置顶所有大项目
    void cancelTopBigProjects();
    //查询所有大项目
    PageInfo<BigProjectDetail> findList(PageRequest pr, String q);
    //查询大项目创建人
    Integer getCreatorIdBy(Integer id);
    //删除大项目
    void deleteBigProject(Integer id);
    //查找置顶大项目详细信息
    BigProjectDetail getTopBigProject();

    List<PortalBigProjectEntity> findAll();
}
