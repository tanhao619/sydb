package com.cdyoue.oddJobs.service.sydb.impl;

import com.cdyoue.oddJobs.dao.syData.AssessResponsitory;
import com.cdyoue.oddJobs.dto.SyCooperativePartnerDTO;
import com.cdyoue.oddJobs.dto.zscq.AssessDetailDTO;
import com.cdyoue.oddJobs.dto.zscq.AssessDetailSummer;
import com.cdyoue.oddJobs.entity.syData.SyPortalAssessEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.PortalIntellectualsMapper;
import com.cdyoue.oddJobs.service.support.ServiceSupport;
import com.cdyoue.oddJobs.service.sydb.SyAssessService;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Tanhao on 2017/9/22.
 */
@Service
@Transactional
public class SyAssessServiceImpl extends ServiceSupport<SyPortalAssessEntity> implements SyAssessService {
    @Autowired
    private AssessResponsitory assessResponsitory;
    @Override
    public Class getJpaRepositoryClazz() {
        return AssessResponsitory.class;
    }
    @Autowired
    private PortalIntellectualsMapper portalIntellectualsMapper;

    @Override
    public void insertAssess(AssessDetailSummer assessEntity) {
        SyPortalAssessEntity assessEn = new SyPortalAssessEntity();
        BeanUtils.copyProperties(assessEntity,assessEn);
        assessEn.setUpdateTime(new Date(System.currentTimeMillis()));
        assessEn.setPublishTime(new Date(System.currentTimeMillis()));
        assessResponsitory.save(assessEn);
    }

    @Override
    public AssessDetailDTO getAssessById(Integer id) {
        SyPortalAssessEntity  assessEntity = assessResponsitory.getDetail(id);
        AssessDetailDTO detailDTO = new AssessDetailDTO();
        BeanUtils.copyProperties(assessEntity,detailDTO);
        SimpleDateFormat smp = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String pt = smp.format(assessEntity.getPublishTime());
        detailDTO.setPublishTime(pt);
        return detailDTO;
    }

    @Override
    public PageInfo<AssessDetailDTO> getAssessList(String q, Pageable requestPage) {
        if(q==null||q.trim().equals("")){
            q="%%";
        }else{
            q="%"+q+"%";
        }
        Page<SyPortalAssessEntity> rpPage = assessResponsitory.findListByName(q, requestPage);
        List<AssessDetailDTO> assessDetailDTOs = rpPage.getContent().stream().map(p -> portalIntellectualsMapper.AssessEntityToDTO(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(assessDetailDTOs, requestPage, rpPage.getTotalElements()));
    }

    @Override
    public void deleteAssess(Integer id) {
        SyPortalAssessEntity entity = assessResponsitory.findOne(id);
        if(entity == null){
            throw new NotFoundEntityException("数据不存在");
        }else {
            assessResponsitory.delete(id);
        }
    }

    @Override
    public void deleteAllAssess(Integer[] ids) {
        for(Integer id:ids){
            SyPortalAssessEntity entity = assessResponsitory.findOne(id);
            if(entity == null){
                throw new NotFoundEntityException("数据不存在");
            }else {
                assessResponsitory.delete(id);
            }
        }
    }
}
