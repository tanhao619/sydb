package com.cdyoue.oddJobs.service.sydb;

import com.cdyoue.oddJobs.dto.zlcx.*;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by dengshaojun on 2017/09/18.
 */
public interface ExpertService {

    PageInfo<ExpertSummary> findExpertsPage(Integer industryType, String q, Pageable pageRequest);

    ExpertBaseinfo findExpert(Integer id);

    List<ExpertTop> findExpertTop3();

    void saveExpertContact(Integer id, ExpertContactRequest expertContactRequest);

    void saveExpert(ExpertRequest expertRequest);

    void updateExpert(Integer id, ExpertRequest expertRequest);

    void deleteExpert(Integer[] ids);

    void updateTopExpert(Integer id, String topImgUrl);

    List<ExpertMini> findAllExpert(String q);

    PageInfo<ExpertContactSummary> findExpertContactsPage(String q, Pageable pageRequest);

    void deleteExpertContact(Integer[] ids);
}
