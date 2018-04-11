package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.lgfc.OddJobNews;
import com.cdyoue.oddJobs.dto.scfw.InvestAndFinNews;
import com.cdyoue.oddJobs.dto.xqdt.ProjectNews;

import java.util.List;

/**
 * Created by tr on 2017/6/7.
 */
public interface RollingNewsService {
    /**
     * 零工动态，显示四条
     */
    List<OddJobNews> getOddJobNews();

    /**
     * 投融资动态
     */
    List<InvestAndFinNews> getInvestAndFinNews();

    /**
     * 获取最新发布的需求（2条）
     * @return
     */
    List<ProjectNews> getProjectNews();
}
