package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.icnpp.policy.PolicyDetail;
import com.cdyoue.oddJobs.dto.icnpp.policy.PolicySumary;
import com.cdyoue.oddJobs.dto.other.InterfaceApiRequest;
import com.cdyoue.oddJobs.dto.other.PortalApiSumary;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.PageRequest;

/**
 * Created by dengshaojun on 2017/5/8.
 */
public interface IcnppService {

    PageInfo<PolicySumary> getPolices(String q, PageRequest pr);

    PolicyDetail getPolice(String id);
}
