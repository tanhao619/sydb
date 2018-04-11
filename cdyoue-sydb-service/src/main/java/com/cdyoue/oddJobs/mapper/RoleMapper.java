package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dao.lqsq.UserResponsitory;
import com.cdyoue.oddJobs.dto.oauth.RoleSumary;
import com.cdyoue.oddJobs.entity.lgsq.RoleEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/5/8.
 */
@Component
public class RoleMapper {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserResponsitory userResponsitory;
    public RoleSumary roleEntityToRoleSumary(RoleEntity re) {

        RoleSumary rs = new RoleSumary();
        rs.setId(re.getId());
        rs.setName(re.getName());
        rs.setCreateTime(re.getCreateTime());
        UserEntity ue = userResponsitory.findOne(re.getCreateBy());
        if(ue != null){
            rs.setCreator(userMapper.userToEmployeerName(ue));
        }
        rs.setIntro(re.getIntro());
        rs.setMenu(userMapper.getTopMenus(re.getLeftMenus()));
        return rs;
    }




}
