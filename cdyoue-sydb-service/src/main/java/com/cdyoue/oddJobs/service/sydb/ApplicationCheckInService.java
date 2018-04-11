package com.cdyoue.oddJobs.service.sydb;

import com.cdyoue.oddJobs.dto.zlcy.ApplicationCheckInDetail;
import com.cdyoue.oddJobs.dto.zlcy.ApplicationCheckInMini;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.PageRequest;

/**
 * Created by dengshaojun on 2017/10/12.
 */
public interface ApplicationCheckInService {

    PageInfo<ApplicationCheckInMini> findApplicationCheckIns(String q, PageRequest pageRequest);

    ApplicationCheckInDetail findApplicationCheckIn(Integer id);

    void deleteApplicationCheckIn(Integer[] ids);
}
