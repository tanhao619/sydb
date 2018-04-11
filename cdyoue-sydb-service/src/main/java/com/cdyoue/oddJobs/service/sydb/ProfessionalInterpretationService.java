package com.cdyoue.oddJobs.service.sydb;

import com.cdyoue.oddJobs.dto.zlcx.*;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by dengshaojun on 2017/09/18.
 */
public interface ProfessionalInterpretationService {

    ProfessionalInterpretationDetail findProfessionalInterpretation(Integer id);

    List<ProfessionalInterpretationSummary> findExpertProfessionalInterpretation(Integer expertId);

    PageInfo<ProfessionalInterpretationSummary> findExpertProfessionalInterpretationsPage(String q, Pageable pageRequest);

    List<ProfessionalInterpretationTop> findProfessionalInterpretationTop3();

    void saveProfessionalInterpretation(ProfessionalInterpretationRequest professionalInterpretationRequest);

    void updateProfessionalInterpretation(Integer id, ProfessionalInterpretationRequest professionalInterpretationRequest);

    void deleteProfessionalInterpretation(Integer[] ids);

    void topProfessionalInterpretation(Integer id, String topImgUrl);

    void collectProfessionalInterpretation(Integer userId, Integer interpretationid);

    PageInfo<ProfessionalInterpretationSummary> findProfessionalInterpretationByUC(Integer userId, Pageable pageRequest);
}
