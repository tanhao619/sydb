package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.home.PageHome;
import com.cdyoue.oddJobs.dto.home.PageHomeRequest;
import com.cdyoue.oddJobs.dto.home.PageHomeSummary;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * Created by liaoyoule on 2017/5/6.
 */
public interface HomePageService {
    /**
     * 获取各个页面首页信息
     * @param page
     * @return
     */
    List<PageHomeSummary> listHomePage(String page);

    /**
     * 编辑栏目名称
     * @param code
     * @param name
     */
    void updateHomePageName(String code, String name);

    /**
     * 编辑栏目
     * @param id
     * @param page
     */
    void updateHomePage(Integer id, PageHomeRequest page);

    /**
     *上移
     * @param id
     */
    void moveUp(Integer id);

    /**
     * 下移动
     * @param id
     */
    void moveDown(Integer id);

    /**
     * 获取各个页面首页信息分页
     * @param page
     * @param q
     * @param pr
     * @return
     */
    PageInfo<PageHomeSummary> listHomePage(String page, String q, PageRequest pr);
}
