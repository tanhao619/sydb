package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.oauth.RoleRequest;
import com.cdyoue.oddJobs.dto.oauth.RoleSumary;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.PageRequest;

/**
 * Created by liaoyoule on 2017/6/12.
 */
public interface RoleService {

    PageInfo<RoleSumary> getRoles(String q, PageRequest pr);

    RoleSumary getRole(Integer id);

    void delete(Integer id);

    void save(RoleRequest roleRequest);

    void update(Integer id, RoleRequest roleRequest);

    void updateAccountRoles(Integer id, String ids);
}
