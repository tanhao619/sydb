package com.cdyoue.oddJobs.service.sydb;

import com.cdyoue.oddJobs.entity.syData.EquipmentApplyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Map;

/**
 * Created by Administrator on 2017/9/19.
 */
public interface EquipmentApplyService {
    Map<String,Object> getEquipmentApplyPageList(String q, PageRequest pr);
}
