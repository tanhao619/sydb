package com.cdyoue.oddJobs.service.sydb;

import com.cdyoue.oddJobs.dto.zlcx.EquipmentDTO.EquipmentApplyDTO;
import com.cdyoue.oddJobs.dto.zlcx.EquipmentDTO.EquipmentDetail;
import com.cdyoue.oddJobs.dto.zlcx.EquipmentDTO.EquipmentInfo;
import com.cdyoue.oddJobs.dto.zlcx.EquipmentDTO.EquipmentTop;
import com.cdyoue.oddJobs.entity.syData.EquipmentApplyEntity;
import com.cdyoue.oddJobs.entity.syData.EquipmentEntity;
import com.cdyoue.oddJobs.entity.syData.SyEquipmentApplyView;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * Created by Administrator on 2017/9/18.
 */
public interface EquipmentService {
    List<EquipmentTop> findTop();

    EquipmentDetail getEquipmentById(Integer id);

    PageInfo<EquipmentTop> getEquipmentPageList(PageRequest pr, Integer type, String q);

    void createEquipment(EquipmentEntity equipmentEntity);

    void deleteEquipment(List<Integer> id);


    void applyEquipment(EquipmentApplyEntity equipmentApplyEntity);

    EquipmentApplyDTO getEquipmentApplyById(Integer id);

    PageInfo<SyEquipmentApplyView> getEquipmentApplyPageList(PageRequest pr, String q);

    void cancelTop(Integer id);

    void updateEquipment(EquipmentInfo equipmentInfo);

    void collectionEquipment(Integer id);

    void cancelCollectionEquipment(Integer id);

    void deleteEquipmentApplyById(List<Integer> id);

    void madeTop(EquipmentEntity equipmentEntity);
}
