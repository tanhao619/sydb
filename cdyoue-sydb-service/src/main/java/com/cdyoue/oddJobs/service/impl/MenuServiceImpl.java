package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.MenuResponsitory;
import com.cdyoue.oddJobs.dto.oauth.MenuSumary;
import com.cdyoue.oddJobs.entity.lgsq.MenuEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.UserMapper;
import com.cdyoue.oddJobs.service.MenuService;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

/**
 * Created by liaoyoule on 2017/6/12.
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuResponsitory menuResponsitory;
    @Autowired
    private UserMapper userMapper;
    @Override
    public PageInfo<MenuSumary> getMenus(PageRequest pr) {
        Page<MenuEntity> mePage = menuResponsitory.findMenus(pr);
        List<MenuEntity> mes = mePage.getContent();
        if (mes.size() == 0) {
            throw new NotFoundEntityException();
        }
        return  new PageInfo<>(new PageImpl(userMapper.getTopMenus(new HashSet<>(mes)), pr, mePage.getTotalElements())) ;

    }
}
