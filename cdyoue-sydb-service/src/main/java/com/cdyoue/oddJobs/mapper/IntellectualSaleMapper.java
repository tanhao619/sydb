package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dao.lqsq.UserResponsitory;
import com.cdyoue.oddJobs.dto.Contact;
import com.cdyoue.oddJobs.dto.zscq.*;
import com.cdyoue.oddJobs.entity.lgsq.*;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2017/5/12.
 */
@Component
public class IntellectualSaleMapper {

    @Autowired
    private UserResponsitory userResponsitory;

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    public CopyrightDetail WorkEntityToCopyrightDetail(PortalIntellectualSaleWorkEntity pre) {
        CopyrightDetail detail = new CopyrightDetail();
        Copyright copyright = new Copyright();
        Contact contact = new Contact();
        BeanUtils.copyProperties(pre,detail);
        detail.setPublishTime(format.format(pre.getPublishTime()));
        copyright.setTitle(pre.getName());
        copyright.setContent(pre.getIntroduction());
        copyright.setPrice(String.valueOf(pre.getPrice()));
        copyright.setTransactionType(pre.getBusinessType());
        contact.setPerson(pre.getContact());
        contact.setPhone(pre.getTel());

        copyright.setContact(contact);
        detail.setCopyright(copyright);

        return detail;
    }

    public PatentDetail PatentEntityToPatentDetail(PortalIntellectualSalePatentEntity pre) {
        PatentDetail detail = new PatentDetail();
        Patent patent = new Patent();
        Contact contact = new Contact();
        BeanUtils.copyProperties(pre,detail);
        detail.setPublishTime(format.format(pre.getPublishTime()));
        patent.setTransactionType(pre.getBusinessType());
        patent.setPatentType(pre.getPatentType());
        patent.setPatentNo(pre.getApplyCode());
        patent.setTitle(pre.getName());
        patent.setPrice(String.valueOf(pre.getPrice()));
        patent.setContent(pre.getIntroduction());
        patent.setTransactionType(pre.getBusinessType());
        contact.setPerson(pre.getContact());
        contact.setPhone(pre.getTel());

        patent.setContact(contact);
        detail.setPatent(patent);

        return detail;
    }

    public TrademarkDetail BrandEntityToTrademarkDetail(PortalIntellectualSaleBrandEntity pre) {
        TrademarkDetail detail = new TrademarkDetail();
        Trademark trademark = new Trademark();
        Contact contact = new Contact();
        BeanUtils.copyProperties(pre,detail);
        detail.setPublishTime(format.format(pre.getPublishTime()));
        trademark.setTitle(pre.getName());
        trademark.setTransactionType(pre.getBusinessType());
        trademark.setContent(pre.getIntroduction());
        trademark.setImageUrl(pre.getCoverImg());
        trademark.setPrice(String.valueOf(pre.getPrice()));
        trademark.setIntroduction(pre.getIntroduction());
        contact.setPerson(pre.getContact());
        contact.setPhone(pre.getTel());

        trademark.setContact(contact);
        detail.setTrademark(trademark);

        return detail;
    }

    //编辑著作权
    public PortalIntellectualSaleWorkEntity CopyrightToWorkEntity(Copyright intellectual) {
        PortalIntellectualSaleWorkEntity workEntity = new PortalIntellectualSaleWorkEntity();
        workEntity.setName(intellectual.getTitle());
        workEntity.setIntroduction(intellectual.getContent());
        workEntity.setPrice(Double.valueOf(intellectual.getPrice()));
        workEntity.setBusinessType(intellectual.getTransactionType());
        workEntity.setContact(intellectual.getContact().getPerson());
        workEntity.setTel(intellectual.getContact().getPhone());
        workEntity.setTel(intellectual.getContact().getPhone());

        return workEntity;
    }

    //编辑专利
    public PortalIntellectualSalePatentEntity PatentToPatentEntity(Patent intellectual) {
        PortalIntellectualSalePatentEntity patentEntity = new PortalIntellectualSalePatentEntity();
        patentEntity.setName(intellectual.getTitle());
        patentEntity.setPrice(Double.valueOf(intellectual.getPrice()));
        patentEntity.setBusinessType(intellectual.getTransactionType());
        patentEntity.setIntroduction(intellectual.getContent());
        patentEntity.setApplyCode(intellectual.getPatentNo());
        patentEntity.setPatentType(intellectual.getPatentType());
        patentEntity.setContact(intellectual.getContact().getPerson());
        patentEntity.setTel(intellectual.getContact().getPhone());

        return patentEntity;

    }

    //编辑商标
    public PortalIntellectualSaleBrandEntity TrademarkToBrandEntity(Trademark intellectual) {
        PortalIntellectualSaleBrandEntity brandEntity = new PortalIntellectualSaleBrandEntity();

        brandEntity.setName(intellectual.getTitle());
        brandEntity.setPrice(Double.valueOf(intellectual.getPrice()));
        brandEntity.setBusinessType(intellectual.getTransactionType());
        brandEntity.setIntroduction(intellectual.getContent());
        brandEntity.setCoverImg(intellectual.getImageUrl());
        brandEntity.setContact(intellectual.getContact().getPerson());
        brandEntity.setTel(intellectual.getContact().getPhone());

        return brandEntity;
    }


    //获取出售列表
    public IntellectualSaleSummary BrandEntityToSummary(PortalIntellectualSaleBrandEntity ps) {
        IntellectualSaleSummary summary = new IntellectualSaleSummary();
        Contact contact = new Contact();

        BeanUtils.copyProperties(ps,summary);
        summary.setType(1);
        summary.setCategory(1);
        if(ps.getPrice() != null) {
            summary.setPrice(String.valueOf(ps.getPrice()));
        }
        //发布人
        if (ps.getCreateBy() != null) {
            UserEntity userEntity = userResponsitory.findOne(ps.getCreateBy());
            if (userEntity.getUserpersonalEntity() != null) {
                summary.setPublisher(userEntity.getUserpersonalEntity().getName());
            } else if (userEntity.getUserenterpriseEntity() != null) {
                summary.setPublisher(userEntity.getUserenterpriseEntity().getName());
            }
        }
        //创建时间
        summary.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(ps.getPublishTime()));
        summary.setLink("/H5/patentsaleDetails.html?id=" + ps.getId() + "&type=1");

        contact.setPerson(ps.getContact());
        contact.setPhone(ps.getTel());
        summary.setContact(contact);
        summary.setTop(ps.getTop());
        summary.setTopImg(ps.getTopImg());
        return  summary;
    }

    public IntellectualSaleSummary PatentEntityToSummary(PortalIntellectualSalePatentEntity ps) {
        IntellectualSaleSummary summary = new IntellectualSaleSummary();
        Contact contact = new Contact();

        BeanUtils.copyProperties(ps,summary);
        summary.setType(2);
        summary.setCategory(2);
        if(ps.getPrice() != null) {
            summary.setPrice(String.valueOf(ps.getPrice()));
        }
        //发布人
        if (ps.getCreateBy() != null) {
            UserEntity userEntity = userResponsitory.findOne(ps.getCreateBy());
            if (userEntity.getUserpersonalEntity() != null) {
                summary.setPublisher(userEntity.getUserpersonalEntity().getName());
            } else if (userEntity.getUserenterpriseEntity() != null) {
                summary.setPublisher(userEntity.getUserenterpriseEntity().getName());
            }
        }
        //创建时间
        summary.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(ps.getPublishTime()));
        summary.setLink("/H5/patentsaleDetails.html?id=" + ps.getId() + "&type=2");
        contact.setPhone(ps.getTel());
        contact.setPerson(ps.getContact());
        summary.setContact(contact);
        summary.setTop(ps.getTop());
        summary.setTopImg(ps.getTopImg());
        return  summary;
    }

    public IntellectualSaleSummary WorkEntityToSummary(PortalIntellectualSaleWorkEntity ps) {
        IntellectualSaleSummary summary = new IntellectualSaleSummary();
        Contact contact = new Contact();

        BeanUtils.copyProperties(ps,summary);
        summary.setType(3);
        summary.setCategory(3);
        if(ps.getPrice() != null) {
            summary.setPrice(String.valueOf(ps.getPrice()));
        }
        //发布人
        if (ps.getCreateBy() != null) {
            UserEntity userEntity = userResponsitory.findOne(ps.getCreateBy());
            if (userEntity.getUserpersonalEntity() != null) {
                summary.setPublisher(userEntity.getUserpersonalEntity().getName());
            } else if (userEntity.getUserenterpriseEntity() != null) {
                summary.setPublisher(userEntity.getUserenterpriseEntity().getName());
            }
        }
        //创建时间
        summary.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(ps.getPublishTime()));
        summary.setLink("/H5/patentsaleDetails.html?id=" + ps.getId() + "&type=3");
        contact.setPhone(ps.getTel());
        contact.setPerson(ps.getContact());
        summary.setContact(contact);
        summary.setTop(ps.getTop());
        summary.setTopImg(ps.getTopImg());

        return  summary;
    }

    //最新发布出售的六条数据
    public IntellectualSaleSummary SummerEntityToSummary(PortalIntellectualSaleSummerEntity ps) {
        IntellectualSaleSummary summary = new IntellectualSaleSummary();
        summary.setId(ps.getId());
        summary.setName(ps.getName());
        summary.setCategory(ps.getNum());
        summary.setLink("/H5/patentsaleDetails.html?id=" + ps.getId() + "&type=" + ps.getNum());
        return summary;
    }

    public IntellectualSaleMine MineEntityToMine(PortalIntellectualSaleMineEntity ps) {
        IntellectualSaleMine saleMine = new IntellectualSaleMine();
        BeanUtils.copyProperties(ps,saleMine);
        saleMine.setApproveStatus(ps.getReviewStatus());
        saleMine.setLink("/H5/patentsaleDetails.html?id=" + ps.getId() + "&type=" + ps.getCategory());
        return saleMine;
    }
}
