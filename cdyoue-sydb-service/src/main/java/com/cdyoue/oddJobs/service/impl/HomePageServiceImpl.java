package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.OutsourcingProjectTypeResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.PortalHomePageInfoRespository;
import com.cdyoue.oddJobs.dao.lqsq.PortalHomePagePropertyResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.PortalHomePageResponsitory;
import com.cdyoue.oddJobs.dto.common.KeyValue;
import com.cdyoue.oddJobs.dto.home.PageHome;
import com.cdyoue.oddJobs.dto.home.PageHomeRequest;
import com.cdyoue.oddJobs.dto.home.PageHomeSummary;
import com.cdyoue.oddJobs.entity.lgsq.MenuEntity;
import com.cdyoue.oddJobs.entity.lgsq.common.OutsourcingProjectTypeEntity;
import com.cdyoue.oddJobs.entity.lgsq.homePage.PortalHomePageEntity;
import com.cdyoue.oddJobs.entity.lgsq.homePage.PortalHomePageInfoEntity;
import com.cdyoue.oddJobs.entity.lgsq.homePage.PortalHomePagePropertyEntity;
import com.cdyoue.oddJobs.exception.CustomException;
import com.cdyoue.oddJobs.exception.LogicException;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.HomePageMapper;
import com.cdyoue.oddJobs.service.HomePageService;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.page.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by liaoyoule on 2017/5/6.
 */
@Service
public class HomePageServiceImpl implements HomePageService {
    @Autowired
    private PortalHomePageResponsitory portalHomePageResponsitory;
    @Autowired
    private HomePageMapper homePageMapper;
    @Autowired
    private PortalHomePageInfoRespository portalHomePageInfoRespository;
    @Autowired
    private PortalHomePagePropertyResponsitory portalHomePagePropertyResponsitory;
    @Autowired
    private OutsourcingProjectTypeResponsitory outsourcingProjectTypeResponsitory;
    @Override
    @Cacheable(value = "homePages",key ="#page")
    public List<PageHomeSummary> listHomePage(String page) {
        List<PortalHomePageEntity> phpes = portalHomePageResponsitory.findByCodeLikeOrderByCode(page.concat("%"));
        Boolean showCategoryId = page.equalsIgnoreCase("xqdt") ? true : false;

        return phpes.stream().map(phe -> {
            PageHomeSummary pageHomeSummary = homePageMapper.homeSummaryToPageHomeSummary(phe);
            if (showCategoryId) {
                OutsourcingProjectTypeEntity ospt = outsourcingProjectTypeResponsitory.findByName(phe.getName());
                if(ospt != null){
                    pageHomeSummary.setCategoryId(ospt.getId());
                }
            }
            return pageHomeSummary;

        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    @CacheEvict(value="homePages", allEntries=true)
    public void updateHomePageName(String code, String name) {
        PortalHomePageEntity phpe = portalHomePageResponsitory.findByCode(code);
        if (phpe == null) {
            throw new NotFoundEntityException();
        }

        phpe.setName(name);

        portalHomePageResponsitory.save(phpe);

    }

    @Override
    @Transactional
    @CacheEvict(value="homePages", allEntries=true)
    public void updateHomePage(Integer id, PageHomeRequest page) {
        PortalHomePageInfoEntity ppie = portalHomePageInfoRespository.findOne(id);
        if(ppie == null){
            throw new NotFoundEntityException();
        }
        ppie.setCoverImg(page.getCoverImg());
        ppie.setInfo(page.getIntro());
        ppie.setLink(page.getLink());
        ppie.setTitle(page.getTitle());
        List<KeyValue> addProperties = page.getAddProperties();
        ppie.setUpdateBy(SecurityUtils.getCurrentUserLogin().getId());
        ppie.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        portalHomePageInfoRespository.save(ppie);

        portalHomePagePropertyResponsitory.deleteByPid(id);
        if(addProperties!=null){
            List<PortalHomePagePropertyEntity> phps = addProperties.stream().map(p -> homePageMapper.keyValueToPortalHomePagePropertyEntity(p,id)).collect(Collectors.toList());
            portalHomePagePropertyResponsitory.save(phps);
        }
    }

    @Override
    @Transactional
    @CacheEvict(value="homePages", allEntries=true)
    public void moveUp(Integer id) {
        PortalHomePageInfoEntity phpi = portalHomePageInfoRespository.findByIdAndPid(id,33);
        if(phpi == null){
            throw new CustomException("首页导航栏目才能移动");
        }
        PageRequest pr = new PageRequest(0,1,new Sort(Sort.Direction.DESC,"sort"));

        Page<PortalHomePageInfoEntity> lastPhInfo = portalHomePageInfoRespository.findByPidAndSortLessThan(33, phpi.getSort(), pr);
        if(lastPhInfo.getContent().size() == 0){
            throw new LogicException("已经是第一位无法上移");
        }

        PortalHomePageInfoEntity lastPh = lastPhInfo.getContent().get(0);
        Double sort = phpi.getSort();
        phpi.setSort(lastPh.getSort());
        lastPh.setSort(sort);

        portalHomePageInfoRespository.save(phpi);
        portalHomePageInfoRespository.save(lastPh);

    }

    @Override
    @CacheEvict(value="homePages", allEntries=true)
    public void moveDown(Integer id) {
        PortalHomePageInfoEntity phpi = portalHomePageInfoRespository.findByIdAndPid(id,33);
        if(phpi == null){
            throw new CustomException("首页导航栏目才能移动");
        }
        PageRequest pr = new PageRequest(0,1,new Sort(Sort.Direction.ASC,"sort"));

        Page<PortalHomePageInfoEntity> nextPhInfo = portalHomePageInfoRespository.findByPidAndSortGreaterThan(33, phpi.getSort(), pr);
        if(nextPhInfo.getContent().size() == 0){
            throw new LogicException("已经是最后一位无法下移动");
        }

        PortalHomePageInfoEntity nextPh = nextPhInfo.getContent().get(0);
        Double sort = phpi.getSort();
        phpi.setSort(nextPh.getSort());
        nextPh.setSort(sort);

        portalHomePageInfoRespository.save(phpi);
        portalHomePageInfoRespository.save(nextPh);
    }
    @Override
    public PageInfo<PageHomeSummary> listHomePage(String page, String q, PageRequest pr) {


        Page<PortalHomePageInfoEntity> ppePage  = StringUtils.isNotBlank(q) ?portalHomePageResponsitory.findHomePage("%"+page+"%","%"+q+"%", pr):portalHomePageResponsitory.findHomePage("%"+page+"%", pr);

        List<PortalHomePageInfoEntity> ppes = ppePage.getContent();
        if(ppes.size() == 0){
            throw new NotFoundEntityException();
        }


        List<PageHomeSummary> phss = new ArrayList<>();
        ppes.stream().collect(Collectors.groupingBy(t -> t.getPortalHomePage(),
                Collectors.toList()
        )).forEach((k,v)->{
            PageHomeSummary phs = new PageHomeSummary();
            phs.setCode(k.getCode());
            phs.setName(k.getName());
            List<PageHome>  pageHomes =  v.stream().map(ppe -> {
                PageHome ph =   homePageMapper.portalHomePageInfoEntityToPageHome(ppe);
                ph.setName(k.getName());
                return ph;}).collect(Collectors.toList());
            phs.elements(pageHomes);
            phss.add(phs);
        });

        return new PageInfo<>(new PageImpl(phss.stream().sorted(Comparator.comparing(PageHomeSummary::getCode)).collect(Collectors.toList()), pr, ppePage.getTotalElements()));
    }
}
