package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dto.other.PortalApiSumary;
import com.cdyoue.oddJobs.entity.lgsq.PortalApiEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;

/**
 * Created by Administrator on 2017/5/8.
 */
@Component
public class ApiMapper {
    @Autowired
    private UserMapper userMapper;

    public PortalApiSumary portalApiEntityToPortalApiSumary(PortalApiEntity portalApiEntity) {
        PortalApiSumary pas = new PortalApiSumary();
        pas.setAuth(portalApiEntity.getAuth());
        pas.setCreateTime(new Date(portalApiEntity.getCreateTime().getTime()));
        pas.setDescript(portalApiEntity.getDescript());
        pas.setId(portalApiEntity.getId());
        pas.setMethod(portalApiEntity.getMethod());
        pas.setName(portalApiEntity.getName());

        pas.setParams(portalApiEntity.getParams());
        pas.setType(portalApiEntity.getType());
        pas.setUrl(portalApiEntity.getUrl());
        pas.setVersionCode(portalApiEntity.getVersionCode());

        UserEntity ue = portalApiEntity.getCreateBy();
        if(ue!=null){
            pas.setCreator(userMapper.userToEmployeerName(ue));
        }

        return pas;
    }
}
