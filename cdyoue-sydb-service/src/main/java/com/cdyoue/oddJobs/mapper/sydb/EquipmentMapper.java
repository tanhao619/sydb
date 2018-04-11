package com.cdyoue.oddJobs.mapper.sydb;

import com.cdyoue.oddJobs.dao.lqsq.UserResponsitory;
import com.cdyoue.oddJobs.dao.syData.EquipmentApplyResponsitory;
import com.cdyoue.oddJobs.dao.syData.IndustryTypeResponsitory;
import com.cdyoue.oddJobs.dto.zlcx.EquipmentDTO.EquipmentDetail;
import com.cdyoue.oddJobs.dto.zlcx.EquipmentDTO.EquipmentTop;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import com.cdyoue.oddJobs.entity.syData.EquipmentEntity;
import com.cdyoue.oddJobs.entity.syData.SyIndustryTypeEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2017/9/18.
 */
@Component
public class EquipmentMapper {
    @Autowired
    IndustryTypeResponsitory industryTypeResponsitory;
    @Autowired
    UserResponsitory userResponsitory;
    @Autowired
    EquipmentApplyResponsitory equipmentApplyResponsitory;

    public EquipmentTop entityToTop(EquipmentEntity equipmentEntity) {
        EquipmentTop equipmentTop = new EquipmentTop();
        BeanUtils.copyProperties(equipmentEntity,equipmentTop);
        //添加创建人
        Integer uId = equipmentEntity.getCreateBy();
        UserEntity userEntity = userResponsitory.getOne(uId);
        if(userEntity !=null){
            equipmentTop.setCreateBy(userEntity.getUserName());
        }
        //添加申请次数
        Integer id = equipmentEntity.getId();
        equipmentTop.setApplyTime(equipmentApplyResponsitory.getApplyTimeById(id));
        return equipmentTop;
    }


    public EquipmentDetail entityToDetail(EquipmentEntity equipmentEntity) {
        //属性拷贝
        EquipmentDetail equipmentDetail = new EquipmentDetail();
        BeanUtils.copyProperties(equipmentEntity,equipmentDetail);

        //租赁状态、所属行业、创建人单独添加
        Integer status = equipmentEntity.getStatus();
        Integer type = equipmentEntity.getType();
        Integer uId = equipmentEntity.getCreateBy();
        //判断租赁状态
        if(status == 0){
            equipmentDetail.setStatus("未租赁");
        }else if(status == 1){
            equipmentDetail.setStatus("已租赁");
        }
        //添加所属行业
        SyIndustryTypeEntity syIndustryTypeEntity = industryTypeResponsitory.findOne(type);
        if(syIndustryTypeEntity !=null){
            equipmentDetail.setType(syIndustryTypeEntity.getName());
        }
        //添加创建人
        UserEntity userEntity = userResponsitory.getOne(uId);
        if(userEntity !=null){
            equipmentDetail.setCreateBy(userEntity.getUserName());
        }


        equipmentDetail.setCreateTime(equipmentEntity.getCreateTime().toString());
        return equipmentDetail;
    }

}
