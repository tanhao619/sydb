package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dto.independent.IndependentMine;
import com.cdyoue.oddJobs.dto.independent.IndependentSumary;
import com.cdyoue.oddJobs.dto.independent.RequestIndependent;
import com.cdyoue.oddJobs.entity.lgsq.PortalIndependentText;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import org.springframework.stereotype.Component;

/**
 * Created by liaoyoule on 2017/5/11.
 */
@Component
public class PortalIndependentTextMapper {
    public PortalIndependentText requestPublishTOPortalPublishEntity(RequestIndependent publish) {
        PortalIndependentText ppe = new PortalIndependentText();
        ppe.setBrief(publish.getBrief());
        ppe.setCoverImg(publish.getCoverImg());
        ppe.setIntro(publish.getIntro());
        ppe.setTitle(publish.getTitle());
        return ppe;
    }


    public IndependentSumary portalIndependentTextTOPublishSumary(PortalIndependentText pit) {
        IndependentSumary ps = new IndependentSumary();
        IndependentMine im = this.portalIndependentTextTOPublishMine(pit);
        ps.setIndependentMine(im);
        UserEntity updator = pit.getUpdator();

        if (updator != null) {
            ps.setUpdator(updator.getUserName());
            ps.setUpdateTime(pit.getUpdateTime());
        }


        return ps;
    }


    public IndependentMine portalIndependentTextTOPublishMine(PortalIndependentText pit) {
        IndependentMine pe = new IndependentMine();
        pe.setBrief(pit.getBrief());
        pe.setCoverImg(pit.getCoverImg());
        UserEntity creator = pit.getCreator();
        if (creator != null) {
            pe.setCreator(creator.getUserName());
            pe.setCreateTime(pit.getCreateTime());
        }
        pe.setViewNum(pit.getViewNum());
        pe.setIntro(pit.getIntro());
        pe.setTitle(pit.getTitle());
        pe.setId(pit.getId());
        return pe;
    }

}
