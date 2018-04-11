package com.cdyoue.oddJobs.service.sydb.impl;

import com.cdyoue.oddJobs.dao.syData.EquipmentApplyResponsitory;
import com.cdyoue.oddJobs.dao.syData.EquipmentApplyViewResponsitory;
import com.cdyoue.oddJobs.dao.syData.EquipmentCollectResponsitory;
import com.cdyoue.oddJobs.dao.syData.EquipmentResponsitory;
import com.cdyoue.oddJobs.dto.zlcx.EquipmentDTO.EquipmentApplyDTO;
import com.cdyoue.oddJobs.dto.zlcx.EquipmentDTO.EquipmentDetail;
import com.cdyoue.oddJobs.dto.zlcx.EquipmentDTO.EquipmentInfo;
import com.cdyoue.oddJobs.dto.zlcx.EquipmentDTO.EquipmentTop;
import com.cdyoue.oddJobs.entity.syData.EquipmentApplyEntity;
import com.cdyoue.oddJobs.entity.syData.EquipmentCollectEntity;
import com.cdyoue.oddJobs.entity.syData.EquipmentEntity;
import com.cdyoue.oddJobs.entity.syData.SyEquipmentApplyView;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.sydb.EquipmentMapper;
import com.cdyoue.oddJobs.service.support.ServiceSupport;
import com.cdyoue.oddJobs.service.sydb.EquipmentApplyService;
import com.cdyoue.oddJobs.service.sydb.EquipmentService;
import com.cdyoue.oddJobs.spec.Operator;
import com.cdyoue.oddJobs.spec.QueryRequest;
import com.cdyoue.oddJobs.spec.SpecificationHelper;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2017/9/18.
 */

@Service
public class EquipmentServiceImpl extends ServiceSupport<EquipmentEntity> implements EquipmentService {
    //设备DAO
    @Autowired
    private EquipmentResponsitory equipmentResponsitory;
    //设备申请DAO
    @Autowired
    private EquipmentApplyResponsitory equipmentApplyResponsitory;

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Autowired
    private SpecificationHelper specificationHelper;

    @Autowired
    EquipmentApplyService equipmentApplyService;

    @Autowired
    EquipmentCollectResponsitory equipmentCollectResponsitory;

    @Autowired
    EquipmentApplyViewResponsitory  equipmentApplyViewResponsitory;

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public Class getJpaRepositoryClazz() {
        return EquipmentResponsitory.class;
    }


    /**
     * 查询首页顶置列表
     * @return
     */
    @Override
    public List<EquipmentTop> findTop() {
        List<EquipmentEntity>equipmentResponsitories =  equipmentResponsitory.findTop();//从数据库中查询到原始数据
        List<EquipmentTop> equipmentToplist = new ArrayList<>();
        for (int i = 0;i<equipmentResponsitories.size();i++){
            equipmentToplist.add(equipmentMapper.entityToTop(equipmentResponsitories.get(i)));
            if(i == 5){
                break;
            }
        }
        return equipmentToplist;
    }


    /**
     * 通过id查询一个设备详情
     * @param id
     * @return
     */
    @Override
    public EquipmentDetail getEquipmentById(Integer id) {
        EquipmentEntity equipmentEntity = equipmentResponsitory.getEquipmentById(id);
        //浏览量加一
        if(equipmentEntity.getViewCount() == null){
            equipmentEntity.setViewCount(1);
        }else {
            equipmentEntity.setViewCount(equipmentEntity.getViewCount()+1);
        }
        equipmentResponsitory.saveAndFlush(equipmentEntity);
        return equipmentMapper.entityToDetail(equipmentEntity);
    }

    /**
     * 获取设备分页列表
     * @param pr
     * @param type
     * @param q
     * @return
     */
    @Override
    public PageInfo<EquipmentTop> getEquipmentPageList(PageRequest pr, Integer type, String q) {
        Page<EquipmentEntity> rpPage = super.getEquipmentPageList("type", type+"","name",q,pr);
        List<EquipmentTop> equipmentTops = new ArrayList<>();
        if(rpPage.getContent().size() != 0){
            equipmentTops = rpPage.getContent().stream().map(p -> equipmentMapper.entityToTop(p)).collect(Collectors.toList());
        }
        return new PageInfo<>(new PageImpl(equipmentTops, pr, rpPage.getTotalElements()));
    }

    /**
     * 获取设备申请分页列表
     * @param pr
     * @param q
     * @return
     */
    @Override
    public PageInfo<SyEquipmentApplyView> getEquipmentApplyPageList(PageRequest pr, String q) {
        //生成分页page
        List<QueryRequest> queryRequest = new ArrayList<>();
        if(q != null){
            QueryRequest qe = new QueryRequest();
            qe.setF("name");
            qe.setO(Operator.LIKE);
            qe.setV(q);
            queryRequest.add(qe);
        }
        Class entityClazz = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Specification specifica = specificationHelper.getSpecifica(entityClazz, queryRequest);
        Page<SyEquipmentApplyView> page = equipmentApplyViewResponsitory.findAll(specifica, pr);
        if (page.getContent().size() == 0) {
            throw new NotFoundEntityException("数据不存在");
        }
        List<SyEquipmentApplyView> SyEquipmentApplyViews = new ArrayList<>();
        if(page.getContent().size() != 0){
            SyEquipmentApplyViews = page.getContent().stream().collect(Collectors.toList());
        }
        return new PageInfo<>(new PageImpl(SyEquipmentApplyViews, pr, page.getTotalElements()));
    }

    /**
     * 创建设备
     */
    @Override
    public void createEquipment(EquipmentEntity equipmentEntity) {
        equipmentEntity.setCreateBy(SecurityUtils.getCurrentUserLogin().getId());
        equipmentEntity.setCreateTime(new Date());
        equipmentResponsitory.save(equipmentEntity);
    }

    /**
     * 删除设备
     * @param id
     */
    @Override
    @Transactional
    public void deleteEquipment(List<Integer> id) {
        for (int i = 0;i < id.size();i++){
            equipmentResponsitory.delete(id.get(i));
        }
        for (int i = 0;i < id.size();i++){
            List<EquipmentApplyEntity> equipmentApplyByEid = equipmentApplyResponsitory.getEquipmentApplyByEid(id.get(i));
            if (equipmentApplyByEid != null){
                    equipmentApplyResponsitory.delete(equipmentApplyByEid);
            }
        }
    }


    /**
     * 编辑设备
     * @param equipmentInfo
     */
    @Override
    public void updateEquipment(EquipmentInfo equipmentInfo) {
        EquipmentEntity equipmentEntity = new EquipmentEntity();
        equipmentEntity =  equipmentResponsitory.findOne(equipmentInfo.getId());
        BeanUtils.copyProperties(equipmentInfo,equipmentEntity);
        equipmentEntity.setCreateTime(new Date());
        equipmentEntity.setCreateBy(SecurityUtils.getCurrentUserLogin().getId());
        equipmentResponsitory.saveAndFlush(equipmentEntity);
    }

    /**
     * 申请设备
     * @param equipmentApplyEntity
     */
    @Override
    public void applyEquipment(EquipmentApplyEntity equipmentApplyEntity) {
        equipmentApplyEntity.setCreateTime(new Date());
        equipmentApplyEntity.setCreateBy(SecurityUtils.getCurrentUserLogin().getId());

        equipmentApplyResponsitory.save(equipmentApplyEntity);
    }

    /**
     * 获取设备申请详情
     * @param id
     * @return
     */
    @Override
    public EquipmentApplyDTO getEquipmentApplyById(Integer id) {
        EquipmentApplyEntity one = equipmentApplyResponsitory.findOne(id);
        EquipmentApplyDTO equipmentApplyDTO = new EquipmentApplyDTO();
        BeanUtils.copyProperties(one,equipmentApplyDTO);
        EquipmentEntity equipmentById = equipmentResponsitory.getEquipmentById(one.geteId());
        equipmentApplyDTO.setName(equipmentById.getName());
        return equipmentApplyDTO;
    }

    /**
     * 删除设备申请
     * @param id
     */
    @Override
    public void deleteEquipmentApplyById(List<Integer> id) {
        if(id.size() != 0){
            for (int i = 0;i<id.size();i++){
                equipmentApplyResponsitory.delete(id.get(i));
            }
        }

    }

    /**
     * 取消顶置
     * @param id
     */
    @Override
    public void cancelTop(Integer id) {
        EquipmentEntity equipmentById = equipmentResponsitory.getEquipmentById(id);
        equipmentById.setTop(0);
        equipmentById.setUpdateBy(SecurityUtils.getCurrentUserLogin().getId());
        equipmentById.setUpdateTime(new Date());
        equipmentResponsitory.saveAndFlush(equipmentById);
    }

    /**
     * 置顶
     */
    @Override
    public void madeTop(EquipmentEntity equipmentEntity) {
        EquipmentEntity equipmentById = equipmentResponsitory.getEquipmentById(equipmentEntity.getId());
        equipmentById.setTop(1);
        equipmentById.setUpdateBy(SecurityUtils.getCurrentUserLogin().getId());
        equipmentById.setUpdateTime(new Date());
        String topImg = equipmentEntity.getTopImg();
        equipmentById.setTopImg(topImg);
        equipmentResponsitory.saveAndFlush(equipmentById);
    }

    /**
     * 收藏设备
     * @param id
     */
    @Override
    public void collectionEquipment(Integer id) {
        EquipmentCollectEntity entity = new EquipmentCollectEntity();
        entity.seteId(id);
        entity.setuId(SecurityUtils.getCurrentUserLogin().getId());
        equipmentCollectResponsitory.saveAndFlush(entity);

        EquipmentEntity one = equipmentResponsitory.findOne(id);
        List<EquipmentCollectEntity> equipmentCollectEntities = equipmentCollectResponsitory.findListByEid(id);
        if(equipmentCollectEntities != null){
            one.setFavorCount(equipmentCollectEntities.size());
        }

        equipmentResponsitory.saveAndFlush(one);
    }

    /**
     * 取消收藏设备
     * @param id
     */
    @Override
    public void cancelCollectionEquipment(Integer id) {
        EquipmentCollectEntity entity = equipmentCollectResponsitory.findOneByEidAndUid(id,SecurityUtils.getCurrentUserLogin().getId());
        if(entity != null){
            equipmentCollectResponsitory.delete(entity.getId());
        }


        EquipmentEntity one = equipmentResponsitory.findOne(id);
        List<EquipmentCollectEntity> equipmentCollectEntities = equipmentCollectResponsitory.findListByEid(id);
        if(equipmentCollectEntities != null){
            one.setFavorCount(equipmentCollectEntities.size());
        }
        equipmentResponsitory.saveAndFlush(one);
    }
}
