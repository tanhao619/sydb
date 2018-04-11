package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.PortalIntellectualSaleResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.UserResponsitory;
import com.cdyoue.oddJobs.dto.Contact;
import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.zscq.IntellectualBuyBanner;
import com.cdyoue.oddJobs.dto.zscq.IntellectualSaleBanner;
import com.cdyoue.oddJobs.dto.zscq.IntellectualSaleSummary;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.service.PortalIntellectualSaleService;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.page.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dengshaojun on 2017/6/1.
 */
@Transactional
@Service
public class PortalIntellectualSaleServiceImpl implements PortalIntellectualSaleService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PortalIntellectualSaleResponsitory portalIntellectualSaleResponsitory;

    @Autowired
    private UserResponsitory userResponsitory;

    @Override
    // 获取知产出售列表，type参数不传查所有
    public PageInfo<IntellectualSaleSummary> getIntellectualSales( String q, Integer transactionType, PageRequest pr) {
        if (StringUtils.isBlank(q)) {
            q = "";
        }
        String businessType = "";
        if (transactionType != null) {
            businessType += transactionType;
        }
        Object[] entityPage = null;
        UserMine userMine = SecurityUtils.getCurrentUserLogin();
        if(userMine != null){
            if(userMine.getRole() == 2){
                entityPage = portalIntellectualSaleResponsitory.findIntellectualSalesAdmin("%".concat("").concat("%"),"%".concat(q).concat("%"), "%".concat(businessType).concat("%"),pr.getPageNumber()*pr.getPageSize(), pr.getPageSize());
            }else {
                entityPage = portalIntellectualSaleResponsitory.findIntellectualSales("%".concat("1").concat("%"),"%".concat(q).concat("%"), "%".concat(businessType).concat("%"),pr.getPageNumber()*pr.getPageSize(), pr.getPageSize());
            }
        }else {
            entityPage = portalIntellectualSaleResponsitory.findIntellectualSales("%".concat("1").concat("%"),"%".concat(q).concat("%"), "%".concat(businessType).concat("%"),pr.getPageNumber()*pr.getPageSize(), pr.getPageSize());
        }
        if (entityPage == null || entityPage.length < 1) {
            throw new NotFoundEntityException("数据不存在");
        }
        List<IntellectualSaleSummary> resultset = new ArrayList<>();
        for(int i=0;i<entityPage.length;i++){
            Object[] cells = (Object[]) entityPage[i];
            IntellectualSaleSummary intellectualSaleSummary = new IntellectualSaleSummary();
            intellectualSaleSummary.setId((Integer) cells[5]);
            intellectualSaleSummary.setBusinessType((Integer) cells[0]);
            intellectualSaleSummary.setCategory((new Integer(cells[1].toString())));
            intellectualSaleSummary.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cells[4]));
            intellectualSaleSummary.setIntroduction((String) cells[6]);
            intellectualSaleSummary.setName((String) cells[7]);
            intellectualSaleSummary.setPrice(String.valueOf(cells[8]));
            UserEntity userEntity = userResponsitory.findOne( cells[9] == null ? 0 :(Integer)(cells[9]));
            if (userEntity !=null && userEntity.getUserpersonalEntity() != null) {
                intellectualSaleSummary.setPublisher(userEntity.getUserpersonalEntity().getName());
            } else if (userEntity !=null && userEntity.getUserenterpriseEntity() != null) {
                intellectualSaleSummary.setPublisher(userEntity.getUserenterpriseEntity().getName());
            }
            intellectualSaleSummary.setReviewStatus((Integer) cells[10]);
            intellectualSaleSummary.setViewCount((Integer) cells[11]);

            BigInteger type = (BigInteger) cells[12];
            intellectualSaleSummary.setType( (type == null ? 0 :type.intValue()));
            Contact contact = new Contact();
            contact.setPhone((String) cells[3]);
            contact.setPerson((String) cells[2]);
            intellectualSaleSummary.setContact(contact);
            intellectualSaleSummary.setLink("/H5/patentsaleDetails.html?id=" + cells[5] + "&type=" + cells[1]);
            intellectualSaleSummary.setTop((Integer) cells[13]);
            intellectualSaleSummary.setTopImg((String)cells[14]);
            resultset.add(intellectualSaleSummary);
        }
        Integer counter = portalIntellectualSaleResponsitory.findCount("%".concat(q).concat("%"), "%".concat(businessType).concat("%"));
        if(counter==null)counter=0;
        PageInfo<IntellectualSaleSummary> pageInfo=new PageInfo<>(new PageImpl(resultset, pr, counter));
        return pageInfo;
    }

    @Override
    public List<IntellectualSaleBanner> getSaleBanners() {
        List<IntellectualSaleBanner> banners = new ArrayList<>();
        Object[] list = portalIntellectualSaleResponsitory.getBanners();
        if (list == null || list.length < 1) {
            throw new NotFoundEntityException("数据不存在");
        }else {
            for (int i = 0;i < list.length;i++){
                Object[] cel = (Object[]) list[i];
                IntellectualSaleBanner banner = new IntellectualSaleBanner();
                banner.setId((Integer)cel[0]);
                banner.setCategory(String.valueOf(cel[1]));
                banner.setTopImg(String.valueOf(cel[3]));
                banner.setName((String)cel[4]);
                banner.setPrice(String.valueOf(cel[5]));
                banners.add(banner);
            }
        }
        return banners;
    }

    @Override
    public List<IntellectualBuyBanner> getBuyBanners() {
        List<IntellectualBuyBanner> banners = new ArrayList<>();
        Object[] list = portalIntellectualSaleResponsitory.getBuyBanners();
        if (list == null || list.length < 1) {
            throw new NotFoundEntityException("数据不存在");
        }else {
            for (int i = 0;i < list.length;i++){
                Object[] cel = (Object[]) list[i];
                IntellectualBuyBanner banner = new IntellectualBuyBanner();
                banner.setId((Integer)cel[0]);
                banner.setName((String)cel[1]);
                banner.setTopImg(String.valueOf(cel[2]));
                banners.add(banner);
            }
        }
        return banners;
    }

    @Override
    public void buyTop(Integer id,String topImg) {
        Date date = new Date(System.currentTimeMillis());
        portalIntellectualSaleResponsitory.buyTop(id,date);
        if (topImg!=null && topImg.length()>0){
            portalIntellectualSaleResponsitory.buyTopImg(id,date,topImg);
        }
    }

    @Override
    public void removeBuyTop(Integer id) {
        portalIntellectualSaleResponsitory.removeBuyTop(id);
    }
}
