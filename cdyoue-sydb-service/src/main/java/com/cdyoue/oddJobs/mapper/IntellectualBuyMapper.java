package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dto.Contact;
import com.cdyoue.oddJobs.dto.zscq.Intellectual;
import com.cdyoue.oddJobs.dto.zscq.IntellectualDetail;
import com.cdyoue.oddJobs.entity.lgsq.PortalIntellectualBuyEntity;
import org.springframework.stereotype.Component;

/**
 * Created by tr on 2017/5/19.
 */

@Component
public class IntellectualBuyMapper {
    public PortalIntellectualBuyEntity intellectualToEntity(Intellectual intellectual) {
        PortalIntellectualBuyEntity pib = new PortalIntellectualBuyEntity();
        //知识产权类别
        pib.setIntellType(intellectual.getCategory());
        //求购标题
        pib.setName(intellectual.getTitle());
        //交易类型
        pib.setBusinessType(intellectual.getTransactionType());
        //专利类型
        pib.setPatentType(intellectual.getPatentType());
        //详细要求
        pib.setIntroduction(intellectual.getContent());
        //计划用途
        pib.setPlan(intellectual.getUsage());
        //联系人
        pib.setContact((intellectual.getContact().getPerson()));
        //联系电话
        pib.setTel(intellectual.getContact().getPhone());
        return pib;
    }

    public IntellectualDetail IntellectualBuyEntityToIntellectualDetail(PortalIntellectualBuyEntity pib){
        IntellectualDetail intellectualDetail = new IntellectualDetail();
        Intellectual i = new Intellectual();
        i.setTitle(pib.getName());
        i.setCategory(pib.getIntellType());
        i.setUsage(pib.getPlan());
        i.setTransactionType(pib.getBusinessType());
        i.setContent(pib.getIntroduction());
        Contact contact = new Contact();
        contact.setPerson(pib.getContact());
        contact.setPhone(pib.getTel());
        i.setContact(contact);
        i.setPatentType(pib.getPatentType());
        intellectualDetail.setPublishTime(pib.getPublishTime() == null ? null : pib.getPublishTime().toString());
        intellectualDetail.setViewCount(pib.getViewCount());
        intellectualDetail.setIntellectual(i);
        return intellectualDetail;
    }

    public PortalIntellectualBuyEntity intellectualToPortalIntellectualBuyEntity(Intellectual i) {
        PortalIntellectualBuyEntity ib = new PortalIntellectualBuyEntity();
        ib.setName(i.getTitle());
        ib.setIntroduction(i.getContent());
        ib.setPlan(i.getUsage());
        ib.setIntellType(i.getCategory());
        ib.setBusinessType(i.getTransactionType());
        ib.setPatentType(i.getPatentType());
        ib.setContact(i.getContact().getPerson());
        ib.setTel(i.getContact().getPhone());
        return ib;
    }
}
