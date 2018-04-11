package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.PortalInvestorResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.QuestionResponsitory;
import com.cdyoue.oddJobs.dto.lgfc.OddJobNews;
import com.cdyoue.oddJobs.dto.scfw.InvestAndFinNews;
import com.cdyoue.oddJobs.dto.xqdt.ProjectNews;
import com.cdyoue.oddJobs.mapper.PortalInvestorsMapper;
import com.cdyoue.oddJobs.mapper.RollingsNewsMapper;
import com.cdyoue.oddJobs.service.RollingNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tr on 2017/6/7.
 */

@Service
public class RollingsNewsServiceImpl implements RollingNewsService {
    @Autowired
    private PortalInvestorResponsitory portalInvestorResponsitory;

    @Autowired
    private PortalInvestorsMapper portalInvestorsMapper;

    @Autowired
    private RollingsNewsMapper rollingsNewsMapper;

    @Autowired
    private QuestionResponsitory questionResponsitory;

    @Override
    public List<OddJobNews> getOddJobNews() {
        Object[] entityPage = questionResponsitory.getOddJobNews();
        List<OddJobNews> list = new ArrayList<>();
        for (int i=0; i<entityPage.length; i++){
            list.add(rollingsNewsMapper.toOddJobNews(entityPage[i]));
        }
        return list;
    }

    @Override
    public List<InvestAndFinNews> getInvestAndFinNews() {
        Object[] entityPage = portalInvestorResponsitory.getInvestAndFinNews();
        List<InvestAndFinNews> list = new ArrayList<>();
        for (int i=0; i<entityPage.length; i++){
            list.add(rollingsNewsMapper.toInvestAndFinNews(entityPage[i]));
        }
        return list;
    }

    @Override
    public List<ProjectNews> getProjectNews() {
        return null;
    }
}
