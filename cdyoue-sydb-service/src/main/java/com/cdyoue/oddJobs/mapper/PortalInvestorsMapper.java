package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dao.lqsq.PortalDomainResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.UserpersonalResponsitory;
import com.cdyoue.oddJobs.dto.Contact;
import com.cdyoue.oddJobs.dto.scfw.*;
import com.cdyoue.oddJobs.entity.lgsq.PortalInvestorEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserpersonalEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by liaoyoule on 2017/4/21.
 */
@Component
public class PortalInvestorsMapper {

    @Autowired
    private PortalDomainResponsitory portalDomainResponsitory;

    @Autowired
    private UserpersonalResponsitory userpersonalResponsitory;

    /**
     * InvestorEntity 转 investorToInvestorSummary
     */

    public InvestorSummary investorToInvestorSummary(PortalInvestorEntity pre) {

        InvestorSummary is = new InvestorSummary();
        is.setId(pre.getId());

        Contact contact = new Contact();
        contact.setPerson(pre.getName());
        contact.setPhone(pre.getTel());
        contact.setMail(pre.getEmail());

        Investor investor = new Investor();
        investor.setName(pre.getName());
        investor.setHeadImg(pre.getHeadImg());

        if(pre.getTitle()!=null && !pre.getTitle().isEmpty()) {
            String[] titles = pre.getTitle().split("\\,");
            investor.setTitles(Arrays.asList(titles));
        }
        Organization organization = new Organization();
        organization.setLogoUrl(pre.getLogoUrl());
        organization.setName(pre.getOrgName());
        organization.setOrgLink(pre.getLink());

        List<Domain> list = new ArrayList<Domain>();
        if (pre.getFocusArea() !=null && !pre.getFocusArea().isEmpty()) {
            List<String> focusAreas = Arrays.asList(pre.getFocusArea().split("\\,"));
            for (int i = 0; i < focusAreas.size(); i++) {
                Domain domain = new Domain();
                Integer domainId = Integer.valueOf(focusAreas.get(i));
                domain.setId(domainId);
                String dName = portalDomainResponsitory.findOne(domainId).getName();
                domain.setName(dName);
                list.add(domain);
            }
            is.setDomains(list);
        }
        is.setContact(contact);
        is.setInvestor(investor);
        is.setOrganization(organization);
        is.setId(pre.getId());

            try {
                Integer userId = pre.getCreateBy().getId();
                UserpersonalEntity upe = userpersonalResponsitory.findByUid(userId);
                is.setCreateBy(upe.getName());
            }catch (Exception e){
                is.setCreateBy(null);
            }

        if (pre.getCreateTime() !=null)
        is.setCreateTime(pre.getCreateTime().toString());
        is.setViewCount(pre.getViewCount());

        return is;
    }

    /**
     * InvestorEntity 转 InvestorDetail
     */

    public InvestorDetail portalInvestorEntityToInvestorDetail(PortalInvestorEntity pre) {
        InvestorDetail ivd = new InvestorDetail();
        ivd.setName(pre.getName());
        ivd.setContent(pre.getIntroduction());
        ivd.setOrgLink(pre.getLink());
        ivd.setEmail(pre.getEmail());
        ivd.setOrgName(pre.getOrgName());
        ivd.setOrgImg(pre.getLogoUrl());
        ivd.setTel(pre.getTel());
        ivd.setHeadImg(pre.getHeadImg());
        if(pre.getTitle()!=null && !pre.getTitle().isEmpty()) {
            String[] titles = pre.getTitle().split("\\,");
            ivd.setTitles(Arrays.asList(titles));
        }
        Organization organization = new Organization();
        organization.setLogoUrl(pre.getLogoUrl());
        organization.setName(pre.getOrgName());
        organization.setOrgLink(pre.getLink());

        List<Domain> list = new ArrayList<Domain>();
        if (pre.getFocusArea() !=null && !pre.getFocusArea().isEmpty()) {
            List<String> focusAreas = Arrays.asList(pre.getFocusArea().split("\\,"));
            for (int i = 0; i < focusAreas.size(); i++) {
                Domain domain = new Domain();
                Integer domainId = Integer.valueOf(focusAreas.get(i));
                domain.setId(domainId);
                String dName = portalDomainResponsitory.findOne(domainId).getName();
                domain.setName(dName);
                list.add(domain);
            }
            ivd.setDomains(list);
        }
        return ivd;

    }

    /**
     * InvestorEntity 转 InvestorDetail
     */

    public PortalInvestorEntity investorDetailToPortalInvestorEntity(InvestorDetail investorDetail) {
        PortalInvestorEntity portalInvestorEntity = new PortalInvestorEntity();
        //投资人姓名
        portalInvestorEntity.setName(investorDetail.getName());
        List<String> titleList = investorDetail.getTitles();
        if (titleList.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (String title : titleList) {
                sb.append(title);
                sb.append(",");
            }
            portalInvestorEntity.setTitle(sb.substring(0, sb.lastIndexOf(",")));
        }

        //关注领域
        List<Domain> domainList = investorDetail.getDomains();
        if (domainList.size() > 0) {
            StringBuilder sb2 = new StringBuilder();
            for (Domain domain : domainList) {
                sb2.append(domain.getId());
                sb2.append(",");
            }
            portalInvestorEntity.setFocusArea(sb2.substring(0, sb2.lastIndexOf(",")));
        }

        //联系电话
        portalInvestorEntity.setTel(investorDetail.getTel());
        //联系邮箱
        portalInvestorEntity.setEmail(investorDetail.getEmail());
        //所属机构
        portalInvestorEntity.setOrgName(investorDetail.getOrgName());
        //机构LOGO
        portalInvestorEntity.setLogoUrl(investorDetail.getOrgImg());
        //机构链接
        portalInvestorEntity.setLink(investorDetail.getOrgLink());
        //详情内容
        portalInvestorEntity.setIntroduction(investorDetail.getContent());
        //投资人头像
        portalInvestorEntity.setHeadImg(investorDetail.getHeadImg());
        return portalInvestorEntity;
    }
}
