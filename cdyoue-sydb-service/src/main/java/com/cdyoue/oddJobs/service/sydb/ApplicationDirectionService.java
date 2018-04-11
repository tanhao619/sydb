package com.cdyoue.oddJobs.service.sydb;

import com.cdyoue.oddJobs.dto.zlcy.ApplicationDirectionDetail;
import com.cdyoue.oddJobs.dto.zlcy.ApplicationDirectionMini;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.PageRequest;

/**
 * Created by dengshaojun on 2017/10/12.
 */
public interface ApplicationDirectionService {

    PageInfo<ApplicationDirectionMini> findApplicationDirections(String q, PageRequest pageRequest);

    ApplicationDirectionDetail findApplicationDirection(Integer id);

    void deleteApplicationDirection(Integer[] ids);
}
