package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.other.InterfaceApiRequest;
import com.cdyoue.oddJobs.dto.other.PortalApiSumary;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.PageRequest;

/**
 * Created by dengshaojun on 2017/5/8.
 */
public interface ApiService {

    PageInfo<PortalApiSumary> getApis(String q, PageRequest pr);

    void delete(Integer id);

    PortalApiSumary getApis(Integer id);

    void updateApi(Integer id, InterfaceApiRequest interfaceApiRequest);

    void createApi(InterfaceApiRequest interfaceApiRequest);
}
