package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.zscq.PatentBigdataPriceComp;
import com.cdyoue.oddJobs.dto.zscq.PatentBigdataSummary;
import com.cdyoue.oddJobs.dto.zscq.PatentNationDetail;
import com.cdyoue.oddJobs.dto.zscq.PatentNationSummary;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by dengshaojun on 2017/5/22.
 */
public interface PatentService {

    PageInfo<PatentNationSummary> findByPatentname(String q, String type, Pageable pageRequest);

    PatentNationDetail findById(Integer id);

    PageInfo<PatentBigdataSummary> findBdsByPatentname(String q, Pageable pageRequest);

    List<PatentBigdataPriceComp> findBdpriByPatentname(String name);

    List<PatentNationSummary> findByIds(List<Integer> patentids);

    void deletePatentBdpById(Integer id);

    void deletePatentBdById(Integer id);

    void deletePatentById(Integer id);
}
