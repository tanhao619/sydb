package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dto.common.KeyValue;
import com.cdyoue.oddJobs.dto.home.PageHome;
import com.cdyoue.oddJobs.dto.home.PageHomeSummary;
import com.cdyoue.oddJobs.entity.lgsq.homePage.PortalHomePageEntity;
import com.cdyoue.oddJobs.entity.lgsq.homePage.PortalHomePageInfoEntity;
import com.cdyoue.oddJobs.entity.lgsq.homePage.PortalHomePagePropertyEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by liaoyoule on 2017/5/10.
 */
@Component
public class HomePageMapper {
    @Autowired
    private UserMapper userMapper;
    public PageHomeSummary homeSummaryToPageHomeSummary(PortalHomePageEntity php){
        PageHomeSummary phs = new PageHomeSummary();
        phs.setName(php.getName());
        phs.setCode(php.getCode());
        Set<PortalHomePageInfoEntity> phis = php.getPageinfos();
        if(phis !=null){
            List<PageHome> kvs = phis.stream().map(p -> this.portalHomePageInfoEntityToPageHome(p)).collect(Collectors.toList());
            phs.elements(kvs);
        }
        return phs;
    }


    public PageHome portalHomePageInfoEntityToPageHome(PortalHomePageInfoEntity phpi){
        PageHome ph = new PageHome();
        ph.setCoverImg(phpi.getCoverImg());
        ph.setIntro(phpi.getInfo());
        ph.setLink(phpi.getLink());
        ph.setName(phpi.getName());
        ph.setSort(phpi.getSort());
        ph.setTitle(phpi.getTitle());
        ph.setId(phpi.getId());
        UserEntity creator = phpi.getCreator();
        if(creator!=null){
            ph.setCreateTime(phpi.getCreateTime());
            ph.setCreator(userMapper.userToEmployeerName(creator));
        }
        //key-value
        Set<PortalHomePagePropertyEntity> propertys = phpi.getPropertys();
        if(propertys !=null){
          List<KeyValue> kvs = propertys.stream().map(p -> this.portalHomePagePropertyEntityToKeyValue(p)).collect(Collectors.toList());
            ph.addProperties(kvs);
        }
        return ph;
    }





    public KeyValue portalHomePagePropertyEntityToKeyValue(PortalHomePagePropertyEntity phpp){
        KeyValue kv = new KeyValue();
        kv.setName(phpp.getName());
        kv.setValue(phpp.getValue());
        return kv;
    }
    public PortalHomePagePropertyEntity keyValueToPortalHomePagePropertyEntity(KeyValue kv,Integer id){
        PortalHomePagePropertyEntity php = new PortalHomePagePropertyEntity();
        php.setName(kv.getName());
        php.setPageInfoId(id);
        php.setValue(kv.getValue());
        return php;
    }

    public PageHomeSummary portalHomePageInfoEntityToPageHomeSummary(PortalHomePageInfoEntity ppe) {
        PageHomeSummary phs = new PageHomeSummary();
        PageHome pageHome = this.portalHomePageInfoEntityToPageHome(ppe);
        return null;

    }
}

