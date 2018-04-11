package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dto.algorithm.*;
import com.cdyoue.oddJobs.entity.lgsq.AlgorithmOperatorEntity;
import com.cdyoue.oddJobs.entity.lgsq.AlgorithmTypeEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by liaoyoule on 2017/6/15.
 */
@Component
public class AlgorithmMapper {
    @Autowired
    private UserMapper userMapper;

    public AlgorithmBase AlgorithmOperatorEntityToAlgorithmBase(AlgorithmOperatorEntity aoe) {

        AlgorithmBase ab = new AlgorithmBase();

        ab.setCreateTime(aoe.getCreateTime());
        UserEntity ue = aoe.getCreateBy();
        if (ue != null) {
            ab.setCreator(userMapper.userToEmployeerName(ue));
        }
        ab.setId(aoe.getId());
        ab.setName(aoe.getName());
        ab.setDataUrl(aoe.getDataUrl());
        ab.setOperatorUrl(aoe.getOperatorUrl());
        ab.setIntro(aoe.getIntro());
        AlgorithmTypeEntity type = aoe.getType();
        if (type != null) {
            AlgorithmTypeBase atb = new AlgorithmTypeBase();
            atb.setId(type.getId());
            atb.setName(type.getName());
            ab.setType(atb);
        }
        return ab;
    }

    public AlgorithmSumary AlgorithmOperatorEntityToAlgorithmSumary(AlgorithmOperatorEntity aoe) {
        AlgorithmSumary as = new AlgorithmSumary();
        AlgorithmBase algorithmBase = this.AlgorithmOperatorEntityToAlgorithmBase(aoe);
        as.setAlgorithmBase(algorithmBase);
        as.setDataUrl(aoe.getDataUrl());
        as.setOperatorUrl(aoe.getOperatorUrl());
        return as;
    }

    public AlgorithmOperatorEntity algorithmRequestToAlgorithmOperatorEntity(AlgorithmRequest algorithmRequest) {
        AlgorithmOperatorEntity aoe = new AlgorithmOperatorEntity();
        aoe.setDataUrl(algorithmRequest.getDataUrl());
        aoe.setIntro(algorithmRequest.getIntro());
        aoe.setOperatorUrl(algorithmRequest.getOperatorUrl());
        aoe.setName(algorithmRequest.getName());
        AlgorithmTypeEntity ate = new AlgorithmTypeEntity();
        ate.setId(algorithmRequest.getType());
        aoe.setType(ate);
        return aoe;
    }

    public  AlgorithmTypeSumary algorithmTypeEntityToAlgorithmTypeSumary(AlgorithmTypeEntity aoe) {
        AlgorithmTypeSumary ats = new AlgorithmTypeSumary();
        AlgorithmTypeBase atb = new AlgorithmTypeBase();
        atb.setName(aoe.getName());
        atb.setId(aoe.getId());
        ats.setTypeBase(atb);
        ats.setCreateTime(aoe.getCreateTime());
        ats.setIntro(aoe.getIntro());
        UserEntity createBy = aoe.getCreateBy();
        ats.setUpdateTime(aoe.getUpdateTime());
        if(createBy != null){
            ats.setCreator(userMapper.userToEmployeerName(createBy));
        }

        return ats;

    }
}
