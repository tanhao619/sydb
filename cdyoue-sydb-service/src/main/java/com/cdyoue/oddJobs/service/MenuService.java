package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.oauth.MenuSumary;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.PageRequest;

/**
 * Created by liaoyoule on 2017/6/12.
 */
public interface MenuService {
    PageInfo<MenuSumary> getMenus(PageRequest pr) ;
}
