package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.RoleResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.UserRoleResponsitory;
import com.cdyoue.oddJobs.dto.oauth.RoleRequest;
import com.cdyoue.oddJobs.dto.oauth.RoleSumary;
import com.cdyoue.oddJobs.entity.lgsq.MenuEntity;
import com.cdyoue.oddJobs.entity.lgsq.RoleEntity;
import com.cdyoue.oddJobs.entity.lgsq.UserRoleEntity;
import com.cdyoue.oddJobs.exception.LogicalException;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.RoleMapper;
import com.cdyoue.oddJobs.service.RoleService;
import com.cdyoue.oddJobs.spec.Operator;
import com.cdyoue.oddJobs.spec.QueryRequest;
import com.cdyoue.oddJobs.spec.SpecificationHelper;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.page.PageInfo;
import com.google.common.base.Splitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by liaoyoule on 2017/6/12.
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleResponsitory roleResponsitory;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private SpecificationHelper specificationHelper;

    @Autowired
    private UserRoleResponsitory userRoleResponsitory;


    @Override

    public PageInfo<RoleSumary> getRoles(String q, PageRequest pr) {

        List<QueryRequest> qrs = new ArrayList<>();

        QueryRequest qrL = new QueryRequest();
        qrL.setF("name");
        qrL.setV(q);
        qrL.setO(Operator.LIKE);
        qrs.add(qrL);

        Specification specifica = specificationHelper.getSpecifica(RoleEntity.class, qrs);

        Page<RoleEntity> rePage = roleResponsitory.findAll(specifica, pr);
        List<RoleEntity> re = rePage.getContent();
        if (re.size() == 0) {
            throw new NotFoundEntityException();
        }
        List rss = re.stream().map(r -> roleMapper.roleEntityToRoleSumary(r)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(rss, pr, rePage.getTotalElements()));
    }

    @Override
    public RoleSumary getRole(Integer id) {
        RoleEntity re = roleResponsitory.findOne(id);
        if (re == null) {
            throw new NotFoundEntityException();
        }
        return roleMapper.roleEntityToRoleSumary(re);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if (id == 1) {
            throw new LogicalException("超级管理员不能删除");
        }
        roleResponsitory.delete(id);
        userRoleResponsitory.deleteByRoleId(id);
    }

    @Override
    @Transactional
    public void save(RoleRequest roleRequest) {
        RoleEntity re = new RoleEntity();
        re.setCreateBy(SecurityUtils.getCurrentUserLogin().getId());
        re.setCreateTime(new Timestamp(System.currentTimeMillis()));
        re.setIntro(roleRequest.getIntro());
        re.setName(roleRequest.getName());

        List<MenuEntity> nes = roleRequest.getMenus().stream().map(integer -> {
            MenuEntity me = new MenuEntity();
            me.setId(integer);
            return me;
        }).collect(Collectors.toList());

        re.setLeftMenus(new HashSet<>(nes));


        roleResponsitory.save(re);

    }

    @Override
    public void update(Integer id, RoleRequest roleRequest) {
        RoleEntity re = roleResponsitory.findOne(id);
        if (re == null) {
            throw new NotFoundEntityException();
        }
        re.setName(roleRequest.getName());
        re.setIntro(roleRequest.getIntro());
        re.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        re.setUpdateBy(SecurityUtils.getCurrentUserLogin().getId());
        List<MenuEntity> nes = roleRequest.getMenus().stream().map(integer -> {
            MenuEntity me = new MenuEntity();
            me.setId(integer);
            return me;
        }).collect(Collectors.toList());

        re.setLeftMenus(new HashSet<>(nes));

        roleResponsitory.save(re);

    }

    @Override
    @Transactional
    public void updateAccountRoles(Integer id, String ids) {
        userRoleResponsitory.deleteByUserId(id);
        List<UserRoleEntity> ures = Splitter.on(",").trimResults().splitToList(ids).stream().map(
                (String rid) -> {
                    UserRoleEntity ure = new UserRoleEntity();
                    ure.setUserId(id);
                    RoleEntity re = new RoleEntity();
                    re.setId(Integer.parseInt(rid));
                    ure.setRole(re);
                    return ure;
                }
        ).collect(Collectors.toList());

        userRoleResponsitory.save(ures);

    }
}
